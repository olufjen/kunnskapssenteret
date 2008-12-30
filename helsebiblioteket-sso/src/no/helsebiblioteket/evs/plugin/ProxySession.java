package no.helsebiblioteket.evs.plugin;

import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import no.helsebiblioteket.util.ServletUtil;


/**
 * This servlet creates a session on the EZProxy proxy server.
 * <br/>
 * This servlet awaits a get/post from a browser. If the request contains a valid ticket (obfuscated timestamp), the
 * servlet connects to the EZProxy server using a http post. This post causes EZproxy to create a new
 * user session and the returning http headers contain a redirect url with a session id that is just piped back
 * to the browser by this servlet. The browser will then contact the EZProxy server to get a session cookie set.
 *
 * Parameters for this servlet should be configured in /WEB-INF/classes/proxysession.properties
 * <ul>
 * <li>login.url=index.jsp : Url for the page to redirect to if the EZProxy authorization fails</li>
 * <li>logup.url=logup.jsp : Url for the page to redirect to if the EZProxy authorization fails and the parameter group is returned from EZProxy</li>
 * <li>request.url=redirect : The parameter name for url
 * <br/><i>The url to redirect to after we have created a EZProxy session</i></li>
 * <li>session.user=HB_USR : The logged in user parameter in session</li>
 * <li>session.group=HB_GRP : The logged in user group parameter in session</li>
 * <li>proxy.url=http://localhost/login : The url to EZProxy</li>
 * <li>proxy.timeout=12000 : Timeout trying to connect to EZProxy</li>
 * <li>proxy.user=authuser : EZProxy username</li>
 * <li>proxy.pwd=authpass : EZProxy password</li>
 * <li>proxy.usegroup=true : Enable use of authorization of groups in EZProx</li>
 * <br/><i>(group=&lt;value&gt; will be added to the proxy request url</i>
 * </ul>
 */
public class ProxySession extends HttpServlet {

    // init parameters
    private static final String PROPERTY_FILE_PATH = "/WEB-INF/classes/";
    private static final String PROPERTY_FILE = "proxysession.properties";

    private static final String LOGIN_URL = "login.url";
    private static final String LOGUP_URL = "logup.url";
    private static final String REQUEST_URL = "request.url";

    private static final String SESSION_USER = "session.user";
    private static final String SESSION_GROUP = "session.group";

    private static final String PROXY_URL = "proxy.url";
    private static final String PROXY_TIMEOUT = "proxy.timeout";
    private static final String PROXY_USER = "proxy.user";
    private static final String PROXY_PWD = "proxy.pwd";
    private static final String PROXY_USE_GROUP = "proxy.usegroup";

    private static final String PROXY_LOGUP_PARAM = "logup";

    private String loginUrl;
    private String logupUrl;
    private String requestUrl;
    private String sessionUser;
    private String sessionGroup;

    private String proxyUrl;
    private int proxyTimeout;
    private String proxyUser;
    private String proxyPassword;
    private boolean proxyUseGroup;

    private static final Logger logger = Logger.getLogger(ProxySession.class);

    /**
     * Called by the servlet container to indicate to a servlet that the
     * servlet is being placed into service.  See {@link HttpServlet#init}.
     *
     * <p>This implementation stores the {@link ServletConfig}
     * object it receives from the servlet container for later use.
     *
     * @param config the <code>ServletConfig</code> object
     * that contains configutation information for this servlet
     *
     * @exception ServletException if an exception occurs that
     * interrupts the servlet's normal operation
     **/
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        getProperties(config);
    }

    /**
     * Process the HTTP request.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logup = request.getParameter(PROXY_LOGUP_PARAM);

        if (logup != null) {
            ServletUtil.redirect(response, logupUrl);
        } else {
            String destinationUrl = request.getQueryString();

            logger.debug("Destination url: " + destinationUrl);
            String group = validateUser(request);

            if (group != null && createProxySession(response, destinationUrl, group)) {
                 // A-OK!!
            } else {
                redirectToLoginPage(response, destinationUrl);
            }
        }
    }

    // todo this is never used - move out to a util class for debuging?
    private void debugRequest(HttpServletRequest req) {
        if (logger.isDebugEnabled()) {
            logger.debug("QueryString: " + req.getQueryString());
            Map m = req.getParameterMap();
            for (Object o : m.keySet()) {
                Object v = m.get(o);
                logger.debug("k:" + o + " ;v:" + (v instanceof String ? (String)v : v));
            }
        }
    }

    /**
     * Validate user
     * Check if there is a group attribute in session
     * @param request The current request
     * @return String The logged in users group found in session or null if nothing found
     */
    private String validateUser(HttpServletRequest request) {
        logger.debug("validateing user");
        // code to validate if the user has logged in

        HttpSession session = request.getSession();
        String ret = null;

        if (session != null) {
            Object oGroup = session.getAttribute(sessionGroup);

            if (oGroup != null
                && oGroup instanceof String
                && !"".equals(oGroup)) {

                ret = (String)oGroup;
                logger.debug("User validation ok");
            }

            logger.debug(sessionGroup + "=" + oGroup);
        }

        logger.debug("User validation return:" + ret);

        return ret;
    }

    /**
     * Redirect to login page
     *
     * @param response
     */
    private void redirectToLoginPage(HttpServletResponse response, String destinationUrl) {
        String url = null;

        try {
            url = proxyUrl + "?" + destinationUrl;
            url = URLEncoder.encode(url, "UTF-8");

            logger.debug("Encoded destinationUrl: " + url);

        } catch (UnsupportedEncodingException e) {
            logger.warn("Failed encode proxyUrl", e);
        }

        url = loginUrl + "?" + requestUrl + "=" + url;

        ServletUtil.redirect(response, url);

    }

    /**
     * Creates a session on the EZProxy proxy server.
     *
     * Contacts the proxy server and extracts the http-headers returned. These headers
     * contain a http redirect with a proxy server session id. This redirect is to be
     * returnd to the user --> headers returned from proxy are piped unaltered to the
     * user.
     *
     * @param clientResponse The response to the client
     * @param destinationUrl The destination url
     * @param group The group parameter to send to the proxy
     * @return boolean true if the proxy session could be created
     */
    private boolean createProxySession(HttpServletResponse clientResponse, String destinationUrl, String group) {
        logger.debug("Attempting to create proxy session");

        boolean sessionCreated = false;

        // Don't follow redirect returned from proxy. This redirect is to be given to user.
        HttpURLConnection.setFollowRedirects(false);

        try {
            // Create a URLConnection to proxy server
            //URL proxyServerUrl = new URL("http://164.9.140.196/login?user=authuser&pass=authpass&&group=testgroup&url=" + destinationUrl + "&group=" + group);
            String url = proxyUrl + "?user=" + proxyUser + "&pass=" + proxyPassword
                    + (proxyUseGroup && (group != null) ? "&group=" + group : null)
                    + "&" + destinationUrl;


            URL proxyServerUrl = new URL(url);

            logger.debug("Proxy session creation url: " + proxyServerUrl);

            HttpURLConnection proxyServer = (HttpURLConnection)proxyServerUrl.openConnection();

            if (proxyTimeout > 0) {
                proxyServer.setConnectTimeout(proxyTimeout);
            }

            proxyServer.connect();

            logger.debug("Connection to proxy created.");

            // Set statuscode on response based on statuscode from proxy server
            clientResponse.setStatus(proxyServer.getResponseCode());

            // Get the response headers from the proxy server and add to client response.
            // Skip the first headerField as it contains the server response.
            for (int i = 1; ; i++) {
                String headerName = proxyServer.getHeaderFieldKey(i);
                String headerValue = proxyServer.getHeaderField(i);

                if (headerName == null && headerValue == null) {
                    // No more headers
                    break;
                }
                if (headerName == null) {
                    // The header value contains the server's HTTP version
                }

                logger.debug("header: " + headerName + "=" + headerValue);

                clientResponse.setHeader(headerName, headerValue);
            }

            sessionCreated = true;

        } catch (IOException e) {
            logger.warn("Failed to create session on proxy.", e);
        }

        logger.debug("createProxySession completed");

        return sessionCreated;
    }

    /**
     * Process the HTTP doPost request.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    /**
     * Process the HTTP doGet request.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    /**
     * Read properties from file proxysession.properties in folder /WEB-INF/classes.
     *
     * @param config
     */
    private void getProperties(ServletConfig config) {
        try {
            ServletContext ctx = config.getServletContext();
            InputStream in = ctx.getResourceAsStream(PROPERTY_FILE_PATH + PROPERTY_FILE);
            Properties props = new Properties();
            props.load(in);

            loginUrl = props.getProperty(LOGIN_URL);
            logupUrl = props.getProperty(LOGUP_URL);
            requestUrl = props.getProperty(REQUEST_URL);
            sessionUser = props.getProperty(SESSION_USER);
            sessionGroup = props.getProperty(SESSION_GROUP);
            proxyUrl = props.getProperty(PROXY_URL);
            String pt = props.getProperty(PROXY_TIMEOUT);

            if (pt != null && !"".equals(pt)) {
                try {
                    this.proxyTimeout = Integer.parseInt(pt);
                } catch (NumberFormatException e) {
                    logger.warn(PROXY_TIMEOUT + " not correctly defined in " + PROPERTY_FILE_PATH + PROPERTY_FILE);
                }
            }

            proxyUser = props.getProperty(PROXY_USER);
            proxyPassword = props.getProperty(PROXY_PWD);
            proxyUseGroup = "true".equalsIgnoreCase(props.getProperty(PROXY_USE_GROUP));

            in.close();

            logger.debug("properties loaded: " + props.toString());
            logger.debug("variables set:"
                    + " loginUrl=" + loginUrl
                    + " logupUrl=" + logupUrl
                    + " requestUrl=" + requestUrl
                    + " proxyUrl=" + proxyUrl
                    + " sessionUser=" + sessionUser
                    + " sessionGroup=" + sessionGroup
                    + " proxyTimeout=" + proxyTimeout
                    + " proxyUser=" + proxyUser
                    + " proxyPassword=" + proxyPassword
                    + " proxyUseGroup=" + proxyUseGroup
                    );

        } catch (NullPointerException e) {
            logger.warn("Could not open property file: + " + PROPERTY_FILE, e);
        } catch (IOException e) {
            logger.warn("Could not open properties file: " + PROPERTY_FILE, e);
        }
    }

}
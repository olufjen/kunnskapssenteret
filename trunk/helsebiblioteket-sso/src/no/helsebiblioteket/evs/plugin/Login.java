package no.helsebiblioteket.evs.plugin;

import com.wmdata.util.ldap.LDAPLookupUtil;
import com.wmdata.util.ldap.LDAPUser;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
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
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Provides a servie for login / logout
 * <br/>
 * Login a user by comparing username and password in LDAP,
 * then store the username and group information in the session
 * and redirect to the requested url if the login succeeded.<br/>
 * If the login failed, the user is redirected to an error url with
 * an error parameter.<br/>
 *
 * Parameter for this servlet should be configured in /WEB-INF/classes/login.properties
 * <li>request.user=uid : The parameter name for user
 * <li>request.pwd=password : The parameter name for pwd
 * <li>request.url=redirect : The parameter name for url
 * <br/><i>The url to redirect to after an action succeeds</i></li>
 * <li>request.logout=logout : The parameter name for logout<br/>
 * <i>Add the parameter &lt;request.logout&gt;=&lt;SomethingOtherThanNothing&gt; to do a logout</i></li>
 * <li>session.user=HB_USR : The logged in user parameter in session</li>
 * <li>session.group=HB_GRP : The logged in user group parameter in session</li>
 * <li>error.url=login.jsp : Url to redirect to when login fails</li>
 * <li>error.parameter=error_user_login : Parameter name to add to response when action create fails</li>
 * <li>error.missuserpwd=104 : Value of an error.parameter if user or pwd parameter is missing</li>
 * <li>error.faildauth=105 : Value of an error.parameter if authentication failed</li>
 * <li>error.wronguserpwd=106 : Value of an error.parameter if the username or password is wrong</li>
 * </ul>
 *
 * User: <a href="mailto:majks@wmdata.com">Marius Jakobsen</a>
 * Date: 08.okt.2007
 * Time: 17:01:20
 */
public class Login extends HttpServlet {
    // request parameters
    private static final String REQUEST_USER = "request.user";
    private static final String REQUEST_PWD  = "request.pwd";
    private static final String REQUEST_URL  = "request.url";
    private static final String REQUEST_LOGOUT = "request.logout";

    // init parameters
    private static final String PROPERTY_FILE_PATH = "/WEB-INF/classes/";
    private static final String PROPERTY_FILE      = "login.properties";
    private static final String SESSION_USER       = "session.user";
    private static final String SESSION_GROUP      = "session.group";

    private static final String ERROR_URL            = "error.url";
    private static final String ERROR_PARAMETER      = "error.parameter";
    private static final String ERROR_MISS_USER_PWD  = "error.missuserpwd";
    private static final String ERROR_FAILED_AUTH    = "error.faildauth";
    private static final String ERROR_WRONG_USER_PWD = "error.wronguserpwd";

    // variables to hold values from property file
    private String requestUser;
    private String requestPwd;
    private String requestUrl;
    private String requestLogout;
    private String sessionUser;
    private String sessionGroup;
    private String errorUrl;
    private String errorParameter;
    private String errorMissingUserPwd;
    private String errorFailedAuth;
    private String errorWrongUserPwd;
    
    private static final Logger logger = Logger.getLogger(Login.class);

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
        String destinationUrl = request.getParameter(requestUrl);
        logger.debug("Destination url: "+ requestUrl + "=" + destinationUrl);

        String logout = request.getParameter(requestLogout);
        if (logout != null && !"".equals(logout)) {
            doLogout(request);
            redirectToDestinationUrl(response, destinationUrl);            
        } else {

            String validUser = validateUser(request);

            if ("".equals(validUser)) {
                 redirectToDestinationUrl(response, destinationUrl);
            } else {
                redirectToErrorUrl(response, validUser, destinationUrl);
            }
        }
    }

    private void doLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object u = session.getAttribute(sessionUser);
        Object g = session.getAttribute(sessionGroup);

        logger.debug("Logging out [user:" + u + ";group:" + g + "]");

        session.removeAttribute(sessionUser);
        session.removeAttribute(sessionGroup);
    }

    /**
     * Validate the user in this request and store user info to session
     * Return errorcode as String or null if the user not could be validated
     *
     * @param request
     * @return String Will return "" if the user is found and could be stored in the session
     */
    private String validateUser(HttpServletRequest request) {
        String ret;
        String user = request.getParameter(requestUser);
        String pwd = request.getParameter(requestPwd);

        logger.debug("Validating user: " + requestUser + "=" + user + "; " + requestPwd + "=" + (pwd != null && !"".equals(pwd) ? "pwd=OK" : "pwd=Err"));
        if (user == null || pwd == null || "".equals(user) || "".equals(pwd)) {
            ret = errorMissingUserPwd;
        } else {
            try {
                LDAPUser ldapUser = LDAPLookupUtil.getInstance().getLDAPUserByUid(user);
                if (ldapUser == null || !pwd.equals(ldapUser.getUserPassword())) {
                    ret = errorWrongUserPwd;
                } else {
                    HttpSession session = request.getSession();
                    String sUid = ldapUser.getUid();
                    String sGrp = ldapUser.getEmployeeType();
                    session.setAttribute(sessionUser, sUid);
                    session.setAttribute(sessionGroup, sGrp);

                    logger.debug("User validated: " + sUid + ";" + sGrp);
                    ret = "";
                }
            } catch (NamingException e) {
                ret = errorFailedAuth;
                logger.warn("Failed to validate user", e);
            } catch (Exception e) {
                ret = errorFailedAuth;
                logger.warn("Failed to validate user", e);
            }
        }

        return ret;
    }

    private void redirectToDestinationUrl(HttpServletResponse response, String url) {
        redirect(response, url);
    }

    private void redirectToErrorUrl(HttpServletResponse response, String code, String destinationUrl) {
        String url = null, err, dest;

        err = code != null && !"".equals(code) ? errorParameter + "=" + code : "";

        try {
            destinationUrl = URLEncoder.encode(destinationUrl,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.debug("Failed to encode destinationUrl: " + destinationUrl);
        }

        dest = destinationUrl != null && !"".equals((destinationUrl)) ? requestUrl + "=" + destinationUrl : "";

        if (!"".equals(err) && !"".equals(dest))
            url = err + "&" + dest;
        else if (!"".equals(err))
            url = err;
        else if (!"".equals(dest))
            url = dest;

        url = errorUrl + "?" + url;

        logger.debug("Error url: " + url);

        redirect(response, url);
    }

    private void redirect(HttpServletResponse response, String url) {
        if (!response.isCommitted()) {
            try {
                logger.debug("Redirecting to url: " + url);
                url = response.encodeRedirectURL(url);
                response.sendRedirect(url);

            } catch (IOException e) {
                logger.warn("Failed redirect to url", e);
            }
        } else {
            logger.warn("Response already committed, can't redirect to url");
        }
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
     * Read properties from file login.properties in folder /WEB-INF/classes.
     *
     * @param config
     */
    private void getProperties(ServletConfig config) {
        try {
            ServletContext ctx = config.getServletContext();
            InputStream in = ctx.getResourceAsStream(PROPERTY_FILE_PATH + PROPERTY_FILE);
            Properties props = new Properties();
            props.load(in);

            requestUser = props.getProperty(REQUEST_USER);
            requestPwd = props.getProperty(REQUEST_PWD);
            requestUrl = props.getProperty(REQUEST_URL);
            requestLogout = props.getProperty(REQUEST_LOGOUT);
            sessionUser = props.getProperty(SESSION_USER);
            sessionGroup = props.getProperty(SESSION_GROUP);
            errorUrl = props.getProperty(ERROR_URL);
            errorParameter = props.getProperty(ERROR_PARAMETER);
            errorMissingUserPwd = props.getProperty(ERROR_MISS_USER_PWD);
            errorFailedAuth = props.getProperty(ERROR_FAILED_AUTH);
            errorWrongUserPwd = props.getProperty(ERROR_WRONG_USER_PWD);

            in.close();

            logger.debug("properties loaded: " + props.toString());
            logger.debug("variables set:"
                    + " requestUser=" + requestUser
                    + " requestPwd=" + requestPwd
                    + " requestUrl=" + requestUrl
                    + " requestLogout=" + requestLogout
                    + " sessionUser=" + sessionUser
                    + " sessionGroup=" + sessionGroup
                    + " errorUrl=" + errorUrl
                    + " errorMissingUserPwd=" + errorMissingUserPwd
                    + " errorFailedAuth=" + errorFailedAuth
                    + " errorWrongUserPwd=" + errorWrongUserPwd
                    );

        } catch (NullPointerException e) {
            logger.warn("Could not open property file: + " + PROPERTY_FILE, e);
        } catch (IOException e) {
            logger.warn("Could not open properties file: " + PROPERTY_FILE, e);
        }
    }
}

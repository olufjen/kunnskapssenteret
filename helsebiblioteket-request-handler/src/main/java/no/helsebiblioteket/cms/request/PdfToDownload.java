package no.helsebiblioteket.cms.request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.helsebiblioteket.cms.content.RequestedContent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.plugin.ext.http.HttpInterceptor;

/**
 * 
 * @author <A HREF="mailto:karine.haug@edb.com">kah</A>
 * 
 */
public class PdfToDownload extends HttpInterceptor {
	private final Log log = LogFactory.getLog(getClass());

	/**
	 * Adding download parameter to pdf files
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res) {
		boolean download = Boolean.parseBoolean(req.getParameter("download")) ||
		Boolean.parseBoolean(req.getParameter("_download"));

		if (!download) {
			String contentKey = getContentKeyFromUri(req.getRequestURI());

			try {
				RequestedContent rc = new RequestedContent(Integer.parseInt(contentKey));

				if (rc.isPdf()) {
					req.getParameterMap().put("_download", new String[]{"true"});
					log.info("Added _download=true to request " + req.getRequestURI());
				}
			} catch (Exception e) {
				log.error("Request failed for content key " + contentKey);
			}
		}
		return true;
	}

	/**
	 * 
	 * @param requestURI
	 * @return the content key found in the uri
	 */
	private String getContentKeyFromUri(String requestURI) {
		Pattern pattern = Pattern.compile("/_attachment/\\d+");
		Matcher matcher = pattern.matcher(requestURI);

		if(matcher.find()) {
			return matcher.group().substring(13);
		}
		return null;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1)
	throws Exception {

	}
}

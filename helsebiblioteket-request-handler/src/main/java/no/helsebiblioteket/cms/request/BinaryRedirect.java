package no.helsebiblioteket.cms.request;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.helsebiblioteket.cms.content.RequestedBinaryContent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.client.ClientException;
import com.enonic.cms.api.plugin.ext.http.HttpController;

/**
 * 
 * @author <A HREF="mailto:karine.haug@edb.com">kah</A>
 *
 */
public class BinaryRedirect extends HttpController {
	private final Log log = LogFactory.getLog(getClass());

	/**
	 * Redirecting binary url's to the new url format for files
	 */
	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String contentKey = null;
		String binaryKey = req.getParameter("id");

		try {
			RequestedBinaryContent rbc = new RequestedBinaryContent(Integer.parseInt(binaryKey));
			contentKey = rbc.getContentKey();
		} catch (NumberFormatException e) {
			log.error("Invalid binary key: " + binaryKey);
		} catch (ClientException e) {
			log.error("Failed to get content for binary key " + binaryKey);
		} catch (Exception e) {
			log.error("Request failed for binary key " + binaryKey);
		} finally {
			boolean download = Boolean.parseBoolean(req.getParameter("download"));
			String redirect = generateRedirectUri(contentKey, binaryKey, download);
			log.info("Redirecting " + req.getRequestURI() + "?" + req.getQueryString() + " to " + redirect);
			redirect(res, redirect);
		}
	}

	/**
	 * 
	 * @param contentKey
	 * @param binaryKey
	 * @param download
	 * @return uri on the new file url format
	 */
	private String generateRedirectUri(String contentKey, String binaryKey, boolean download) {
		String redirect = "/_attachment/" + contentKey + "/binary/" + binaryKey;

		if (download) {
			redirect = redirect + "?_download=true";
		}
		return redirect;
	}

	/**
	 * 
	 * @param res
	 * @param redirect
	 */
	private void redirect(HttpServletResponse res, String redirect) {
		res.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		try {
			res.sendRedirect(redirect);
		} catch (IOException e) {
			log.error("Failed redirecting to " + redirect, e);
		}
	}
}

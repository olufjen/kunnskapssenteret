package no.kunnskapssenteret.evs.plugins.cache;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.kunnskapssenteret.evs.util.Base64;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.plugin.HttpControllerPlugin;

public class GetFileByCategoryKeyAndNamePlugin extends HttpControllerPlugin {
	private Log logger = LogFactory.getLog(getClass());
	private static final String CATEGORY_KEY = "categorykey";
	private static final String FILE_NAME = "filename";
	private Integer categoryKey;
	private String fileName;
	private Client evsClient;
	private String username;
	private String password;
	
	private void initEvsClient() {
		if (this.evsClient == null) {
			this.evsClient = ClientFactory.getLocalClient();
			this.evsClient.login(this.username, this.password);
		}
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		initEvsClient();
		String categoryKeyFromUrl = request.getParameter(CATEGORY_KEY);
		String fileNameFromUrl = request.getParameter(FILE_NAME);
		try {
			this.categoryKey = Integer.valueOf(categoryKeyFromUrl);
		} catch (NumberFormatException nfe) {
			logger.error("Wrong format for categorykey. Expected an integer", nfe);
		}
		this.fileName = fileNameFromUrl;
		if (this.fileName == null || this.categoryKey == null) {
			logger.error("Both filename and categorykey must be set");
			return;
		}
		String binaryAsString = GetContentByCategoryKeyAndName.getInstance().getBinaryAsStringByCategoryAndName(this.evsClient, this.categoryKey, this.fileName);
		if (null == binaryAsString || "".equals(binaryAsString)) {
			binaryAsString = "No file found for categorykey " + this.categoryKey + " and file name '" + this.fileName + "'";
		}
		response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + this.fileName + "\"" );
        OutputStream outputStream = response.getOutputStream();
        Base64.decode(binaryAsString, outputStream);
        outputStream.flush();
        outputStream.close();
	}
}
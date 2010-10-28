package no.helsebiblioteket.evs.plugins.mcmaster;

import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.ImportContentsParams;

public abstract class GetDisciplinesGenericTask extends McMasterFeed {
	private Log logger = LogFactory.getLog(GetDisciplinesGenericTask.class);
	protected Client cmsClient;
	protected String cmsUsername;
	protected String cmsPassword;
	
	protected String cmsImportDisciplinesName;
	protected Integer cmsDisciplinesArchiveKey;
	
	protected enum TaskPropertyKeys {
		cmsUsername, 
		cmsPassword,  
		cmsArticleDisciplineAndRatingsKey,
		cmsDisciplinesArchiveKey,
		cmsImportDisciplinesName,
		serviceKey, 
		serviceIV
	}
	
	public GetDisciplinesGenericTask() {
	}
	
	protected void initLocalProperties(Properties taskProperties) {
		if (taskProperties != null && taskProperties.size() > 0) {
			this.cmsUsername = taskProperties.getProperty(TaskPropertyKeys.cmsUsername.name());
			this.cmsPassword = taskProperties.getProperty(TaskPropertyKeys.cmsPassword.name());
			this.cmsImportDisciplinesName = taskProperties.getProperty(TaskPropertyKeys.cmsImportDisciplinesName.name());			
			
			Integer archiveKeyTmp = null;
			try {
				archiveKeyTmp = Integer.parseInt(taskProperties.getProperty(TaskPropertyKeys.cmsDisciplinesArchiveKey.name()));
				this.cmsDisciplinesArchiveKey = archiveKeyTmp;
			} catch (NumberFormatException nfe) {
				logger.error("Wrong datatype for cmsArticlePatientPopulationsArchiveKey. Value was '" + archiveKeyTmp + "'. Expected a valid Integer");
			}
			super.serviceKey = taskProperties.getProperty(TaskPropertyKeys.serviceKey.name());
			super.serviceIV = taskProperties.getProperty(TaskPropertyKeys.serviceIV.name());
		}
	}
	
	protected void initEvsClient() {
		if (this.cmsClient == null) {
			this.cmsClient = ClientFactory.getLocalClient();
			this.cmsClient.login(this.cmsUsername, this.cmsPassword);
		}
	}
	
	protected String destroyEvsClient() {
		String result = null;
		if (this.cmsClient != null) {
			result = this.cmsClient.logout();
			this.cmsClient = null;
		}
		return result;
	}
	
	protected void importContent() {
		ImportContentsParams params = new ImportContentsParams();
		params.categoryKey = this.cmsDisciplinesArchiveKey;
		params.importName = this.cmsImportDisciplinesName;
		params.data = fixResponseString(getServiceResponseAsString());
		params.publishFrom = new Date();
		this.cmsClient.importContents(params);
	}
	
	protected abstract String getServiceResponseAsString();

	private String fixResponseString(String responseString) {
		String fixedResponseString = responseString;
		if (! fixedResponseString.contains("<?xml")) {
			fixedResponseString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + fixedResponseString;
		}
		fixedResponseString = fixedResponseString.replaceAll("<diffgr:diffgram xmlns:diffgr=\"urn:schemas-microsoft-com:xml-diffgram-v1\" xmlns:msdata=\"urn:schemas-microsoft-com:xml-msdata\">","");
		fixedResponseString = fixedResponseString.replaceAll("</diffgr:diffgram>", "");
		fixedResponseString = fixedResponseString.replaceAll("diffgr:", "");
		fixedResponseString = fixedResponseString.replaceAll("msdata:", "");
		return fixedResponseString;
	}
}
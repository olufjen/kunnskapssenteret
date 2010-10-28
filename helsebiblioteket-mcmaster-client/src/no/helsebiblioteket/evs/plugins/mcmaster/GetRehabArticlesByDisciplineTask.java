package no.helsebiblioteket.evs.plugins.mcmaster;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetRehabArticlesByDiscipline;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetRehabArticlesByDisciplineResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.plugin.TaskPlugin;

public class GetRehabArticlesByDisciplineTask extends GetArticlesGenericByDisciplineTask {
	private Log logger = LogFactory.getLog(GetRehabArticlesByDisciplineTask.class);
	private static List<String> compositeKeyXpathList = null;
	static {
		compositeKeyXpathList = new ArrayList<String>();
		compositeKeyXpathList.add("/NewDataSet/RehabDisciplineAndRatingsTbl");
		compositeKeyXpathList.add("/NewDataSet/RehabArticleCommentsTbl");
		compositeKeyXpathList.add("/NewDataSet/ArticlePatientPopulationTbl");
		compositeKeyXpathList.add("/NewDataSet/ArticleCategoryTbl");
		compositeKeyXpathList.add("/NewDataSet/ArticleSettingRehabTbl");
	}
	private static List<String> potentialInvalidXmlNodeNameList = null;
	static {
		GetRehabArticlesByDisciplineTask.potentialInvalidXmlNodeNameList = new ArrayList<String>();
		GetRehabArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Title");
		GetRehabArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Author");
		GetRehabArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Source");
		GetRehabArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Abstract");
		GetRehabArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Comments");
	}
	
	public GetRehabArticlesByDisciplineTask() {
	}
	
	@Override
	public void execute(Properties taskProperties) {
		synchronized (GetRehabArticlesByDisciplineTask.class) {
			super.initLocalProperties(taskProperties);
			super.initEvsClient();
			super.importAllContent(GetRehabArticlesByDisciplineTask.compositeKeyXpathList, GetRehabArticlesByDisciplineTask.potentialInvalidXmlNodeNameList);
			super.destroyEvsClient();
		}
	}

	protected String getServiceResponseAsString(int disciplineId) {
		McMasterWSClient mcMasterWsClient = initMcMasterClient();
		String result = null;
		GetRehabArticlesByDisciplineResponse response = getRehabArticlesByDisciplineResponse(mcMasterWsClient, disciplineId);
		result = response.getGetRehabArticlesByDisciplineResult().getExtraElement().toString();
		return result;
	}
	
	private GetRehabArticlesByDisciplineResponse getRehabArticlesByDisciplineResponse(McMasterWSClient mcMasterWsClient, int disciplineId) {
		String encodedKey = super.generateDynamicServiceKey();
		GetRehabArticlesByDiscipline rehabArticlesByDiscipline = new GetRehabArticlesByDiscipline();
		rehabArticlesByDiscipline.setSKey(encodedKey);
		rehabArticlesByDiscipline.setIDiscipline(disciplineId);
		GetRehabArticlesByDisciplineResponse rehabArticlesByDisciplineResponse = null;
		try {
			rehabArticlesByDisciplineResponse = mcMasterWsClient.getRehabArticlesByDiscipline(rehabArticlesByDiscipline);
		} catch (RemoteException e) {
			logger.error("Unable to invoke mcmaster webservice 'GetRehabArticlesByDiscipline' with the following parameters: skey=" + this.serviceKey + " and disciplineId=" + disciplineId + ". Error message: " + e.getMessage(), e);
		}
		return rehabArticlesByDisciplineResponse;
	}
	
	public static void main(String args[]) {
		Properties properties = new Properties();
		properties.setProperty(TaskPropertyKeys.cmsUsername.name(), "batchuser");
		properties.setProperty(TaskPropertyKeys.cmsPassword.name(), "password");
		
		properties.setProperty(TaskPropertyKeys.cmsImportArticleCommentsName.name(), "McMaster article comments");
		properties.setProperty(TaskPropertyKeys.cmsArticleCommentsArchiveKey.name(), "801");
		
		properties.setProperty(TaskPropertyKeys.cmsImportArticleDisciplineAndRatingsName.name(), "McMaster discipline and ratings");
		properties.setProperty(TaskPropertyKeys.cmsArticleDisciplineAndRatingsKey.name(), "800");
		
		properties.setProperty(TaskPropertyKeys.cmsImportArticlePatientPopulationsName.name(), "McMaster patient population");
		properties.setProperty(TaskPropertyKeys.cmsArticlePatientPopulationsArchiveKey.name(), "802");
		
		properties.setProperty(TaskPropertyKeys.cmsImportArticlesName.name(), "McMaster articles rehab");
		
		properties.setProperty(TaskPropertyKeys.cmsArticleArchiveKeyList.name(), "798,798,798,798,798,798,798,798,798,798,798,798,798,798,798,798,798,798,798,798,798");
		properties.setProperty(TaskPropertyKeys.serviceDisciplineIdList.name(), "1,6,8,7,9,10,23,11,12,13,2,14,3,4,5,16,17,18,21,19,20");
		
		properties.setProperty(TaskPropertyKeys.serviceKey.name(), "EDzvuye3drZopliXsbW2eIvFvfmKFwliLIiEV9d0orA=");
		properties.setProperty(TaskPropertyKeys.serviceIV.name(), "O3QMXrEUjxkp48o15CSHBA==");
		
		//properties.setProperty("serviceKey", "B6D0DEE3D4DFBFE4DCE88FD2D5DCD4ECB2D3");
		GetRehabArticlesByDisciplineTask task = new GetRehabArticlesByDisciplineTask();
		task.cmsClient = ClientFactory.getRemoteClient("http://www-t.helsebiblioteket.no/rpc/bin", false);
		task.cmsClient.login("batchuser", "password");
		task.execute(properties);
	}
}
package no.helsebiblioteket.evs.plugins.mcmaster;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetNurseArticlesByDiscipline;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetNurseArticlesByDisciplineResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.client.ClientFactory;

public class GetNurseArticlesByDisciplineTask extends GetArticlesGenericByDisciplineTask {
	private Log logger = LogFactory.getLog(GetNurseArticlesByDisciplineTask.class);
	private static List<String> compositeKeyXpathNodeList = null;
	static {
		GetNurseArticlesByDisciplineTask.compositeKeyXpathNodeList = new ArrayList<String>();
		GetNurseArticlesByDisciplineTask.compositeKeyXpathNodeList.add("/NewDataSet/NurseDisciplineAndRatingsTbl");
		GetNurseArticlesByDisciplineTask.compositeKeyXpathNodeList.add("/NewDataSet/NurseArticleCommentsTbl");
		GetNurseArticlesByDisciplineTask.compositeKeyXpathNodeList.add("/NewDataSet/ArticlePatientPopulationTbl");
		GetNurseArticlesByDisciplineTask.compositeKeyXpathNodeList.add("/NewDataSet/ArticleCategoryTbl");
		GetNurseArticlesByDisciplineTask.compositeKeyXpathNodeList.add("/NewDataSet/ArticleSettingNurseTbl");
	}
	private static List<String> potentialInvalidXmlNodeNameList = null;
	static {
		GetNurseArticlesByDisciplineTask.potentialInvalidXmlNodeNameList = new ArrayList<String>();
		GetNurseArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Title");
		GetNurseArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Author");
		GetNurseArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Source");
		GetNurseArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Abstract");
		GetNurseArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Comments");
	}
	
	public GetNurseArticlesByDisciplineTask() {
	}
	
	@Override
	public void execute(Properties taskProperties) {
		java.util.Date start = new java.util.Date();
		logger.info(this.getClass().getName() + " starting at " + start);
		synchronized (GetNurseArticlesByDisciplineTask.class) {
			super.initLocalProperties(taskProperties);
			super.initEvsClient();
			super.importAllContent(GetNurseArticlesByDisciplineTask.compositeKeyXpathNodeList, GetNurseArticlesByDisciplineTask.potentialInvalidXmlNodeNameList);
			super.destroyEvsClient();
		}
		java.util.Date end = new java.util.Date();
		logger.info(this.getClass().getName() + " done at " +  end + ", millisec spent " + (end.getTime() - start.getTime()));

	}

	protected String getServiceResponseAsString(int disciplineId, Calendar date) {
		McMasterWSClient mcMasterWsClient = initMcMasterClient();
		String result = null;
		GetNurseArticlesByDisciplineResponse response = getNurseArticlesByDisciplineResponse(mcMasterWsClient, disciplineId, date);
		result = response.getGetNurseArticlesByDisciplineResult().getExtraElement().toString();
		return result;
	}
	
	private GetNurseArticlesByDisciplineResponse getNurseArticlesByDisciplineResponse(McMasterWSClient mcMasterWsClient, int disciplineId, Calendar date) {
		String encodedKey = super.generateDynamicServiceKey();
		GetNurseArticlesByDiscipline nurseArticlesByDiscipline = new GetNurseArticlesByDiscipline();
		nurseArticlesByDiscipline.setSKey(encodedKey);
		nurseArticlesByDiscipline.setIDiscipline(disciplineId);
		nurseArticlesByDiscipline.setDtDate(date);
		
		GetNurseArticlesByDisciplineResponse nurseArticlesByDisciplineResponse = null;
		try {
			nurseArticlesByDisciplineResponse = mcMasterWsClient.getNurseArticlesByDiscipline(nurseArticlesByDiscipline);
		} catch (RemoteException e) {
			logger.error("Unable to invoke mcmaster webservice 'GetNurseArticlesByDiscipline' with the following parameters: skey=" + this.serviceKey + " and disciplineId=" + disciplineId + ". Error message: " + e.getMessage(), e);
		}
		return nurseArticlesByDisciplineResponse;
	}
	
	public static void main(String args[]) {
		Properties properties = new Properties();
		properties.setProperty(TaskPropertyKeys.cmsUsername.name(), "batchuser");
		properties.setProperty(TaskPropertyKeys.cmsPassword.name(), "password");
		
		properties.setProperty(TaskPropertyKeys.cmsImportArticleCommentsName.name(), "McMaster article comments");
		properties.setProperty(TaskPropertyKeys.cmsArticleCommentsArchiveKey.name(), "885"); //801
		
		properties.setProperty(TaskPropertyKeys.cmsImportArticleDisciplineAndRatingsName.name(), "McMaster discipline and ratings");
		properties.setProperty(TaskPropertyKeys.cmsArticleDisciplineAndRatingsKey.name(), "887"); // 800
		
		properties.setProperty(TaskPropertyKeys.cmsImportArticlePatientPopulationsName.name(), "McMaster patient population");
		properties.setProperty(TaskPropertyKeys.cmsArticlePatientPopulationsArchiveKey.name(), "886"); //802
		
		properties.setProperty(TaskPropertyKeys.cmsImportArticlesName.name(), "McMaster articles nurse");
		
		properties.setProperty(TaskPropertyKeys.cmsArticleArchiveKeyList.name(), "879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879,879");
		properties.setProperty(TaskPropertyKeys.serviceDisciplineIdList.name(), "3,4,11,5,42,10,7,43,8,9,45,47,48,49,13,52,54,15,16,57,19,20,21,23,24,63,25,93,27,28,29,30,69,70,71,82,33,34");
		
		properties.setProperty(TaskPropertyKeys.serviceKey.name(), " =");
		properties.setProperty(TaskPropertyKeys.serviceIV.name(), " ==");
		
		//properties.setProperty("serviceKey", "B6D0DEE3D4DFBFE4DCE88FD2D5DCD4ECB2D3");
		GetNurseArticlesByDisciplineTask task = new GetNurseArticlesByDisciplineTask();
		task.cmsClient = ClientFactory.getRemoteClient("http://www-t.helsebiblioteket.no/rpc/bin", false);
		task.cmsClient.login("batchuser", "password");
		task.execute(properties);
	}
}
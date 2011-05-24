package no.helsebiblioteket.evs.plugins.mcmaster;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetArticlesByDiscipline;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetArticlesByDisciplineResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.client.ClientFactory;

public class GetDoctorArticlesByDisciplineTask extends GetArticlesGenericByDisciplineTask {
	private Log logger = LogFactory.getLog(GetDoctorArticlesByDisciplineTask.class);
	private static List<String> compositeKeyXpathNodeList = null;
	static {
		GetDoctorArticlesByDisciplineTask.compositeKeyXpathNodeList = new ArrayList<String>();
		GetDoctorArticlesByDisciplineTask.compositeKeyXpathNodeList.add("/NewDataSet/DisciplineAndRatingsTbl");
		GetDoctorArticlesByDisciplineTask.compositeKeyXpathNodeList.add("/NewDataSet/ArticleCommentsTbl");
		GetDoctorArticlesByDisciplineTask.compositeKeyXpathNodeList.add("/NewDataSet/ArticlePatientPopulationTbl");
		GetDoctorArticlesByDisciplineTask.compositeKeyXpathNodeList.add("/NewDataSet/ArticleCategoryTbl");
	}
	private static List<String> potentialInvalidXmlNodeNameList = null;
	static {
		GetDoctorArticlesByDisciplineTask.potentialInvalidXmlNodeNameList = new ArrayList<String>();
		GetDoctorArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Title");
		GetDoctorArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Author");
		GetDoctorArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Source");
		GetDoctorArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Abstract");
		GetDoctorArticlesByDisciplineTask.potentialInvalidXmlNodeNameList.add("Comments");
	}
	
	public GetDoctorArticlesByDisciplineTask() {
	}
	
	@Override
	public void execute(Properties taskProperties) {
		java.util.Date start = new java.util.Date();
		logger.info(this.getClass().getName() + " starting at " + start);
		synchronized (GetDoctorArticlesByDisciplineTask.class) {
			super.initLocalProperties(taskProperties);
			super.initEvsClient();
			super.importAllContent(GetDoctorArticlesByDisciplineTask.compositeKeyXpathNodeList, GetDoctorArticlesByDisciplineTask.potentialInvalidXmlNodeNameList);
			super.destroyEvsClient();
		}
		java.util.Date end = new java.util.Date();
		logger.info(this.getClass().getName() + " done at " +  end + ", millisec spent " + (end.getTime() - start.getTime()));
	}

	protected String getServiceResponseAsString(int disciplineId, Calendar date) {
		McMasterWSClient mcMasterWsClient = initMcMasterClient();
		String result = null;
		GetArticlesByDisciplineResponse response = getArticlesByDisciplineResponse(mcMasterWsClient, disciplineId, date);
		result = response.getGetArticlesByDisciplineResult().getExtraElement().toString();
		return result;
	}
	
	private GetArticlesByDisciplineResponse getArticlesByDisciplineResponse(McMasterWSClient mcMasterWsClient, int disciplineId, Calendar date) {
		String encodedKey = super.generateDynamicServiceKey();
		GetArticlesByDiscipline articlesByDiscipline = new GetArticlesByDiscipline();
		articlesByDiscipline.setSKey(encodedKey);
		articlesByDiscipline.setIDiscipline(disciplineId);
		articlesByDiscipline.setDtDate(date);
		
		GetArticlesByDisciplineResponse articlesByDisciplineResponse = null;
		try {
			articlesByDisciplineResponse = mcMasterWsClient.getArticlesByDiscipline(articlesByDiscipline);
		} catch (RemoteException e) {
			logger.error("Unable to invoke mcmaster webservice 'GetArticlesByDiscipline' with the following parameters: skey=" + this.serviceKey + " and disciplineId=" + disciplineId + ". Error message: " + e.getMessage(), e);
		}
		return articlesByDisciplineResponse;
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
		properties.setProperty(TaskPropertyKeys.cmsArticlePatientPopulationsArchiveKey.name(), "886"); // 802
		
		properties.setProperty(TaskPropertyKeys.cmsImportArticlesName.name(), "McMaster articles doctor");
		
		properties.setProperty(TaskPropertyKeys.cmsArticleArchiveKeyList.name(), "878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878,878");
		properties.setProperty(TaskPropertyKeys.serviceDisciplineIdList.name(), "279,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,17,18,19,20,21,22,23,24,25,251,253,254,255,256,261,262,263,264,265,266,267,268,269,270,271,272,273,274,275,276,277,280,283,284,285,286,287,288,289,290,292,296");
		
		properties.setProperty(TaskPropertyKeys.serviceKey.name(), "");
		properties.setProperty(TaskPropertyKeys.serviceIV.name(), "");
		
		//properties.setProperty("serviceKey", "B6D0DEE3D4DFBFE4DCE88FD2D5DCD4ECB2D3");
		GetDoctorArticlesByDisciplineTask task = new GetDoctorArticlesByDisciplineTask();
		task.cmsClient = ClientFactory.getRemoteClient("http://www-t.helsebiblioteket.no/rpc/bin", false);
		task.cmsClient.login("batchuser", "password");
		task.execute(properties);
	}
}
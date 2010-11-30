package no.helsebiblioteket.evs.plugins.mcmaster;

import java.rmi.RemoteException;
import java.util.Properties;

import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetRehabDisciplines;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetRehabDisciplinesResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.plugin.TaskPlugin;

public class GetRehabDisciplinesTask extends GetDisciplinesGenericTask {
	private Log logger = LogFactory.getLog(GetRehabDisciplinesTask.class);
	
	public GetRehabDisciplinesTask() {
	}
	
	protected String getServiceResponseAsString() {
		McMasterWSClient mcMasterWsClient = initMcMasterClient();
		String result = null;
		GetRehabDisciplinesResponse response = getRehabDisciplinesResponse(mcMasterWsClient);
		result = response.getGetRehabDisciplinesResult().getExtraElement().toString();
		return result;
	}
	
	private GetRehabDisciplinesResponse getRehabDisciplinesResponse(McMasterWSClient mcMasterWsClient) {
		String encodedKey = super.generateDynamicServiceKey();
		GetRehabDisciplines rehabDisciplines = new GetRehabDisciplines();
		rehabDisciplines.setSKey(encodedKey);
		GetRehabDisciplinesResponse disciplinesResponse = null;
		try {
			disciplinesResponse = mcMasterWsClient.getRehabDisciplines(rehabDisciplines);
		} catch (RemoteException e) {
			logger.error("Unable to invoke mcmaster webservice 'getRehabDisciplines' with the following parameters: skey=" + this.serviceKey + ". Error message: " + e.getMessage(), e);
		}
		return disciplinesResponse;
	}
	
	@Override
	public void execute(Properties taskProperties) {
		java.util.Date start = new java.util.Date();
		logger.info(this.getClass().getName() + " starting at " + start);
		synchronized (GetRehabDisciplinesTask.class) {
			super.initLocalProperties(taskProperties);
			super.initEvsClient();
			super.importContent();
			super.destroyEvsClient();
		}
		java.util.Date end = new java.util.Date();
		logger.info(this.getClass().getName() + " done at " +  end + ", millisec spent " + (end.getTime() - start.getTime()));

	}
	
	public static void main(String args[]) {
		Properties properties = new Properties();
		properties.setProperty(TaskPropertyKeys.cmsUsername.name(), "batchuser");
		properties.setProperty(TaskPropertyKeys.cmsPassword.name(), "password");
		
		properties.setProperty(TaskPropertyKeys.cmsImportDisciplinesName.name(), "McMaster rehab disciplines");
		properties.setProperty(TaskPropertyKeys.cmsDisciplinesArchiveKey.name(), "804");
		
		properties.setProperty(TaskPropertyKeys.serviceKey.name(), "=");
		properties.setProperty(TaskPropertyKeys.serviceIV.name(), "==");
		
		//properties.setProperty("serviceKey", "B6D0DEE3D4DFBFE4DCE88FD2D5DCD4ECB2D3");
		GetRehabDisciplinesTask task = new GetRehabDisciplinesTask();
		task.cmsClient = ClientFactory.getRemoteClient("http://www-t.helsebiblioteket.no/rpc/bin", false);
		task.cmsClient.login("batchuser", "password");
		task.execute(properties);
	}
}
package no.helsebiblioteket.evs.plugins.mcmaster;

import java.rmi.RemoteException;
import java.util.Properties;

import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetDisciplines;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetDisciplinesResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.plugin.TaskPlugin;

public class GetDoctorDisciplinesTask extends GetDisciplinesGenericTask {
	private Log logger = LogFactory.getLog(GetDoctorDisciplinesTask.class);
	
	public GetDoctorDisciplinesTask() {
	}
	
	protected String getServiceResponseAsString() {
		McMasterWSClient mcMasterWsClient = initMcMasterClient();
		String result = null;
		GetDisciplinesResponse response = getDisciplinesResponse(mcMasterWsClient);
		result = response.getGetDisciplinesResult().getExtraElement().toString();
		return result;
	}
	
	private GetDisciplinesResponse getDisciplinesResponse(McMasterWSClient mcMasterWsClient) {
		String encodedKey = super.generateDynamicServiceKey();
		GetDisciplines disciplines = new GetDisciplines();
		disciplines.setSKey(encodedKey);
		GetDisciplinesResponse disciplinesResponse = null;
		try {
			disciplinesResponse = mcMasterWsClient.getDisciplines(disciplines);
		} catch (RemoteException e) {
			logger.error("Unable to invoke mcmaster webservice 'getDisciplines' with the following parameters: skey=" + this.serviceKey + ". Error message: " + e.getMessage(), e);
		}
		return disciplinesResponse;
	}
	
	@Override
	public void execute(Properties taskProperties) {
		java.util.Date start = new java.util.Date();
		logger.info(this.getClass().getName() + " starting at " + start);
		synchronized (GetDoctorDisciplinesTask.class) {
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
		
		properties.setProperty(TaskPropertyKeys.cmsImportDisciplinesName.name(), "McMaster disciplines");
		properties.setProperty(TaskPropertyKeys.cmsDisciplinesArchiveKey.name(), "881");//803
		
		properties.setProperty(TaskPropertyKeys.serviceKey.name(), "");
		properties.setProperty(TaskPropertyKeys.serviceIV.name(), "");
		
		//properties.setProperty("serviceKey", "B6D0DEE3D4DFBFE4DCE88FD2D5DCD4ECB2D3");
		GetDoctorDisciplinesTask task = new GetDoctorDisciplinesTask();
		task.cmsClient = ClientFactory.getRemoteClient("http://www-t.helsebiblioteket.no/rpc/bin", false);
		task.cmsClient.login("batchuser", "password");
		task.execute(properties);
		System.out.println("ALL DONE");
	}
}
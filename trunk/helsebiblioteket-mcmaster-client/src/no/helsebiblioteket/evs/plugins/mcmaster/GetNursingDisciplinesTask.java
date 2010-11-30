package no.helsebiblioteket.evs.plugins.mcmaster;

import java.rmi.RemoteException;
import java.util.Properties;

import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetNurseDisciplines;
import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient.GetNurseDisciplinesResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.plugin.TaskPlugin;

public class GetNursingDisciplinesTask extends GetDisciplinesGenericTask {
	private Log logger = LogFactory.getLog(GetNursingDisciplinesTask.class);
	
	public GetNursingDisciplinesTask() {
	}
	
	protected String getServiceResponseAsString() {
		McMasterWSClient mcMasterWsClient = initMcMasterClient();
		String result = null;
		GetNurseDisciplinesResponse response = getNurseDisciplinesResponse(mcMasterWsClient);
		result = response.getGetNurseDisciplinesResult().getExtraElement().toString();
		return result;
	}
	
	private GetNurseDisciplinesResponse getNurseDisciplinesResponse(McMasterWSClient mcMasterWsClient) {
		String encodedKey = super.generateDynamicServiceKey();
		GetNurseDisciplines nurseDisciplines = new GetNurseDisciplines();
		nurseDisciplines.setSKey(encodedKey);
		GetNurseDisciplinesResponse disciplinesResponse = null;
		try {
			disciplinesResponse = mcMasterWsClient.getNurseDisciplines(nurseDisciplines);
		} catch (RemoteException e) {
			logger.error("Unable to invoke mcmaster webservice 'getNurseDisciplines' with the following parameters: skey=" + this.serviceKey + ". Error message: " + e.getMessage(), e);
		}
		return disciplinesResponse;
	}
	
	@Override
	public void execute(Properties taskProperties) {
		java.util.Date start = new java.util.Date();
		logger.info(this.getClass().getName() + " starting at " + start);
		synchronized (GetNursingDisciplinesTask.class) {
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
		
		properties.setProperty(TaskPropertyKeys.cmsImportDisciplinesName.name(), "McMaster nurse disciplines");
		properties.setProperty(TaskPropertyKeys.cmsDisciplinesArchiveKey.name(), "882"); //805
		
		properties.setProperty(TaskPropertyKeys.serviceKey.name(), " =");
		properties.setProperty(TaskPropertyKeys.serviceIV.name(), " ==");
		
		//properties.setProperty("serviceKey", "B6D0DEE3D4DFBFE4DCE88FD2D5DCD4ECB2D3");
		GetNursingDisciplinesTask task = new GetNursingDisciplinesTask();
		task.cmsClient = ClientFactory.getRemoteClient("http://www-t.helsebiblioteket.no/rpc/bin", false);
		task.cmsClient.login("batchuser", "password");
		task.execute(properties);
	}
}
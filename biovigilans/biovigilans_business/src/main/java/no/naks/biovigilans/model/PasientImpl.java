package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author olj
 *
 * Denne klassen representerer en konkret pasient
 */
/**
 * @author olj
 *
 */
/**
 * @author olj
 *
 */
public class PasientImpl extends AbstractPasient implements Pasient {

	
	public PasientImpl() {
		super();
/*		
		kjonn = new String("x");
		aldersGruppe = new String("y");
		antiStoff = new String("z");
*/		
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		patientFields = new HashMap();
		antiStoff = new String[4];
	}

	public void setParams(){
		Long id = getPasient_Id();
		if (id == null){
			params = new Object[]{getAldersGruppe(),getKjonn(),getAntiStoff(),getInneliggendePoli(),getAvdeling()};
		}else
			params = new Object[]{getAldersGruppe(),getKjonn(),getAntiStoff(),getInneliggendePoli(),getAvdeling(),getPasient_Id()};
		
	}
	

	/**
	 * setPatientfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setPatientfieldMaps(String[]userFields){

		keys = userFields;
		patientFields.put(userFields[0],kjonn);
		patientFields.put(userFields[1],kjonn);
		patientFields.put(userFields[2],aldersGruppe);
		patientFields.put(userFields[3],antiStoff[0] );
		patientFields.put(userFields[4],antiStoff[1] );
		patientFields.put(userFields[5],antiStoff[2] );
		patientFields.put(userFields[6],antiStoff[3] );
		
		patientFields.put(userFields[7],inneliggendePoli );
		patientFields.put(userFields[8],inneliggendePoli );
		patientFields.put(userFields[9],inneliggendePoli );
		
	}
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til felt som tilhører pasient
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField,String userValue){
		if (patientFields.containsKey(userField) && userValue != null){
			patientFields.put(userField,userValue);	
	
		}
	}
	/* savetoPatient
	 * Denne rutinen lagrer skjerbildefelter til databasefelter
	 * @see no.naks.biovigilans.model.Pasient#savetoPatient()
	 */
	public void savetoPatient(){
		setKjonn(null);
		setAldersGruppe(null);
		setAntiStoff(null);
	}
}

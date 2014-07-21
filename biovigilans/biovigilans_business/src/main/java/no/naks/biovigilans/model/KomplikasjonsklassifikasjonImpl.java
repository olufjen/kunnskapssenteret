package no.naks.biovigilans.model;

import java.sql.Types;

public class KomplikasjonsklassifikasjonImpl extends
		AbstractKomplikasjonsklassifikasjon implements Komplikasjonsklassifikasjon {

	public KomplikasjonsklassifikasjonImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
	}

	public void setParams(){
		Long id = getKlassifikasjonsid();
		if (id == null){
			params = new Object[]{};
		}else
			params = new Object[]{getKlassifikasjonsid()};
		
	}

	/**
	 * setkomplikasjonklassifikasjonFieldsMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter 
	 */
	public void setkomplikasjonklassifikasjonFieldsMaps(String[]userFields){
		keys = userFields;
		
		komplikasjonklassifikasjonFields.put(userFields[0],getKlassifikasjon());
	}

}

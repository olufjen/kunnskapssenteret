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



}

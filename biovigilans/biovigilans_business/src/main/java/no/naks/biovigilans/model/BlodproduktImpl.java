package no.naks.biovigilans.model;

import java.sql.Types;

public class BlodproduktImpl extends AbstractBlodprodukt implements Blodprodukt {

	public BlodproduktImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
	}

	public void setParams(){
		Long id = getBlodProduktId();
		if (id == null){
			params = new Object[]{};
		}else
			params = new Object[]{getBlodProduktId()};
		
	}	
}

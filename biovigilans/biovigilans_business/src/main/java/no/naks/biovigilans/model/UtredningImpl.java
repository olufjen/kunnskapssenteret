package no.naks.biovigilans.model;

import java.sql.Types;

public class UtredningImpl extends AbstractUtredning implements Utredning {

	public UtredningImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
	}
	
	public void setParams(){
		Long id = getUtredningId();
		if (id == null){
			params = new Object[]{};
		}else
			params = new Object[]{getUtredningId()};
		
	}	

}

package no.naks.biovigilans.model;

import java.sql.Types;

/**
 * @author olj
 *
 * Denne klassen representerer en konkret pasient
 */
public class PasientImpl extends AbstractPasient implements Pasient {

	public PasientImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
	}

	public void setParams(){
		Long id = getPasient_Id();
		if (id == null){
			params = new Object[]{getAldersGruppe(),getKjonn(),getAntiStoff(),getInneliggendePoli(),getAvdeling()};
		}else
			params = new Object[]{getAldersGruppe(),getKjonn(),getAntiStoff(),getInneliggendePoli(),getAvdeling(),getPasient_Id()};
		
	}
}

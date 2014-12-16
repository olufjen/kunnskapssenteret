package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Forebyggende tiltak mot pasient
 * @author olj
 *
 */
public class ForebyggendetiltakImpl extends AbstractTiltak implements Tiltak {
	
	private String tiltakvalg;
	private String tiltakbeskrivelse;
	private Long forebyggendetiltakid;
	private Map<String,String> forebyggendeTiltakFields;
	private String[] keys;

	
	
	public ForebyggendetiltakImpl() {
		super();
		forebyggendeTiltakFields = new HashMap();
		types = new int[] {Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
	}
	public String getTiltakvalg() {
		return tiltakvalg;
	}
	public void setTiltakvalg(String tiltakvalg) {
		this.tiltakvalg = tiltakvalg;
	}
	public String getTiltakbeskrivelse() {
		return tiltakbeskrivelse;
	}
	public void setTiltakbeskrivelse(String tiltakbeskrivelse) {
		this.tiltakbeskrivelse = tiltakbeskrivelse;
	}
	public Long getForebyggendetiltakid() {
		return forebyggendetiltakid;
	}
	public void setForebyggendetiltakid(Long forebyggendetiltakid) {
		this.forebyggendetiltakid = forebyggendetiltakid;
	}
	public Map<String, String> getForebyggendeTiltakFields() {
		return forebyggendeTiltakFields;
	}
	public void setForebyggendeTiltakFields(
			Map<String, String> forebyggendeTiltakFields) {
		this.forebyggendeTiltakFields = forebyggendeTiltakFields;
	}
	
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	
	@Override
	public void setParams() {
		Long id = getForebyggendetiltakid();
		if (id == null){
			params = new Object[]{};
		}else
			params = new Object[]{};
	
		
	}
	
	
	

}

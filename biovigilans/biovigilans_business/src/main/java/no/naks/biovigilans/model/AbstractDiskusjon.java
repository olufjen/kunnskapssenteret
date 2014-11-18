package no.naks.biovigilans.model;
import java.util.Date;



/**
 * Inneholder diskusjonen knyttet til en melding, og som pågår mellom melder og Mottaker/vurderer
 * 
 */

public class AbstractDiskusjon{

	private Date datoforkommentar;
	private java.lang.String kommentar;
	private Long diskusjonid;
	public Date getDatoforkommentar() {
		return datoforkommentar;
	}
	public void setDatoforkommentar(Date datoforkommentar) {
		this.datoforkommentar = datoforkommentar;
	}
	public java.lang.String getKommentar() {
		return kommentar;
	}
	public void setKommentar(java.lang.String kommentar) {
		this.kommentar = kommentar;
	}
	public Long getDiskusjonid() {
		return diskusjonid;
	}
	public void setDiskusjonid(Long diskusjonid) {
		this.diskusjonid = diskusjonid;
	}
	
	
}
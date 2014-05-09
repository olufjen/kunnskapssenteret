package no.naks.biovigilans.model;

public interface Sykdom {

	public long getSykdomId();
	public void setSykdomId(long sykdomId);
	public String getSykdomnsnavn();
	public void setSykdomnsnavn(String sykdomnsnavn);
	public String getSymptomer();
	public void setSymptomer(String symptomer);
	public String getDiagnosekode();
	public void setDiagnosekode(String diagnosekode);
	
}

package no.naks.biovigilans.model;

public interface Komplikasjondiagnosepasient {

	public String getDiagnoseklassifikasjon();
	public void setDiagnoseklassifikasjon(String diagnoseklassifikasjon);
	public String getDiagnosekommentar();
	public void setDiagnosekommentar(String diagnosekommentar);
	public Long getKomplikasjonsdiagnoseId();
	public void setKomplikasjonsdiagnoseId(Long komplikasjonsdiagnoseId);
}

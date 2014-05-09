package no.naks.biovigilans.model;

public interface Utredning {
	public Long getUtredningId();
	public void setUtredningId(Long utredningId);
	public String getUtredningsklassifikasjon();
	public void setUtredningsklassifikasjon(String utredningsklassifikasjon);
	public String getUtredningbeskrivelse();
	public void setUtredningbeskrivelse(String utredningbeskrivelse);
	

}

package no.helsebiblioteket.admin.domain.parameter;

public class ProxyHitParameter {
	private String fromIP;
	private String toDomain;
	private String period;
	private int hits;
	
	public void inc(){this.hits++;}

	public ProxyHitParameter(){ }
	public ProxyHitParameter(String fromIP, String toDomain, String period,
			int hits) {
		super();
		this.fromIP = fromIP;
		this.toDomain = toDomain;
		this.period = period;
		this.hits = hits;
	}
	
	@Override
	public String toString() {
		return this.fromIP + ", " + this.toDomain + ", " + this.period + ", " + hits;
	}

	public String getFromIP() {
		return fromIP;
	}
	public void setFromIP(String fromIP) {
		this.fromIP = fromIP;
	}
	public String getToDomain() {
		return toDomain;
	}
	public void setToDomain(String toDomain) {
		this.toDomain = toDomain;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
}

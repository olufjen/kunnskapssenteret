package no.helsebiblioteket.admin.domain;


public class IpAddressSingle {
	// Local values
	private IpAddress ipAddressSingle;
	private IpAddressSet ipAddressSet = new IpAddressSet();

	public IpAddressSingle() { }
	public IpAddressSingle(IpAddress ipAddressSingle) {
		this.ipAddressSingle = ipAddressSingle;
	}

	// Helpers
	@Override
	public String toString() {
		return ipAddressSingle + ", " + ipAddressSet;
	}

	// GET/SET
	public IpAddress getIpAddressSingle() {
		return ipAddressSingle;
	}
	public void setIpAddressSingle(IpAddress ipAddressSingle) {
		this.ipAddressSingle = ipAddressSingle;
	}
	public IpAddressSet getIpAddressSet() {
		return ipAddressSet;
	}
	public void setIpAddressSet(IpAddressSet ipAddressSet) {
		this.ipAddressSet = ipAddressSet;
	}
}

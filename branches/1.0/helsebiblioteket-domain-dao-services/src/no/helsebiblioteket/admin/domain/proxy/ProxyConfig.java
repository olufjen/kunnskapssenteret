package no.helsebiblioteket.admin.domain.proxy;

public class ProxyConfig implements Comparable<ProxyConfig> {
	private String group;
	private boolean domainCookieOnly = false;
	private boolean excludeDomain = false;
	private String[] domainJavaScript = new String[0];
	private String[] hostJavaScript = new String[0];
	private String[] find = new String[0];
	private String[] replace = new String[0];

	@Override
	public int compareTo(ProxyConfig o) {
		return this.group.compareTo(o.group);
	}

	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public boolean isDomainCookieOnly() {
		return domainCookieOnly;
	}
	public void setDomainCookieOnly(boolean domainCookieOnly) {
		this.domainCookieOnly = domainCookieOnly;
	}
	public boolean isExcludeDomain() {
		return excludeDomain;
	}
	public void setExcludeDomain(boolean excludeDomain) {
		this.excludeDomain = excludeDomain;
	}
	public String[] getDomainJavaScript() {
		return domainJavaScript;
	}
	public void setDomainJavaScript(String[] domainJavaScript) {
		this.domainJavaScript = domainJavaScript;
	}
	public String[] getHostJavaScript() {
		return hostJavaScript;
	}
	public void setHostJavaScript(String[] hostJavaScript) {
		this.hostJavaScript = hostJavaScript;
	}
	public String[] getFind() {
		return find;
	}
	public void setFind(String[] find) {
		this.find = find;
	}
	public String[] getReplace() {
		return replace;
	}
	public void setReplace(String[] replace) {
		this.replace = replace;
	}
}

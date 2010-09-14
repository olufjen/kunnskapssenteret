package no.helsebiblioteket.admin.domain.proxy;

public class ConfigGroup implements Comparable<ConfigGroup>{
	private String group;
	private String title;
	private String url;
	private String[] hosts = new String[0];
	private String domain;
	private boolean excludeDomain;
	private String[] domainJavaScript = new String[0];
	private String[] hostJavaScript = new String[0];
	private boolean domainCookieOnly;
	private String[] find = new String[0];
	private String[] replace = new String[0];

	@Override
	public int compareTo(ConfigGroup o) {
		return this.group.compareTo(o.group);
	}

	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String[] getHosts() {
		return hosts;
	}
	public void setHosts(String[] hosts) {
		this.hosts = hosts;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public boolean isExcludeDomain() {
		return excludeDomain;
	}
	public void setExcludeDomain(boolean excludeDomain) {
		this.excludeDomain = excludeDomain;
	}
	public boolean isDomainCookieOnly() {
		return domainCookieOnly;
	}
	public void setDomainCookieOnly(boolean domainCookieOnly) {
		this.domainCookieOnly = domainCookieOnly;
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
}

package no.helsebiblioteket.admin.service.importexport.ldap.domain;

/**
 * Common view on a user in LDAP
 * @since 2006.06.21
 * @author Marius Jakobsen <a href="mailto:majks@wmdata.no">majks@wmdata.no</a>
 *
 */
public class LDAPUser {
    private String cn;
    private String sureName;
    private String givenName;
    private String email;
    private String dn;
    private String uid;
    private String userPassword;
    private String o;
    private String employeeType;
    private String employeeNumber;
    private String mobile;
    private String pager;
        
    public LDAPUser() {
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getDn() {
        return dn;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;

        LDAPUser ldapUser = (LDAPUser) o1;

        if (cn != null ? !cn.equals(ldapUser.cn) : ldapUser.cn != null) return false;
        if (dn != null ? !dn.equals(ldapUser.dn) : ldapUser.dn != null) return false;
        if (email != null ? !email.equals(ldapUser.email) : ldapUser.email != null) return false;
        if (employeeNumber != null ? !employeeNumber.equals(ldapUser.employeeNumber) : ldapUser.employeeNumber != null)
            return false;
        if (employeeType != null ? !employeeType.equals(ldapUser.employeeType) : ldapUser.employeeType != null)
            return false;
        if (givenName != null ? !givenName.equals(ldapUser.givenName) : ldapUser.givenName != null) return false;
        if (o != null ? !o.equals(ldapUser.o) : ldapUser.o != null) return false;
        if (sureName != null ? !sureName.equals(ldapUser.sureName) : ldapUser.sureName != null) return false;
        if (uid != null ? !uid.equals(ldapUser.uid) : ldapUser.uid != null) return false;
        if (userPassword != null ? !userPassword.equals(ldapUser.userPassword) : ldapUser.userPassword != null) return false;
        if (mobile != null ? !mobile.equals(ldapUser.mobile) : ldapUser.mobile != null) return false;
        if (pager != null ? !pager.equals(ldapUser.pager) : ldapUser.pager != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (cn != null ? cn.hashCode() : 0);
        result = 31 * result + (sureName != null ? sureName.hashCode() : 0);
        result = 31 * result + (givenName != null ? givenName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (dn != null ? dn.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (o != null ? o.hashCode() : 0);
        result = 31 * result + (employeeType != null ? employeeType.hashCode() : 0);
        result = 31 * result + (employeeNumber != null ? employeeNumber.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (pager != null ? pager.hashCode() : 0);
        return result;
    }
}
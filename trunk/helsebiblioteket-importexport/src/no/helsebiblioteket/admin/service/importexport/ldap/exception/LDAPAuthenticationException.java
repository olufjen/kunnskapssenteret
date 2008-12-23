package no.helsebiblioteket.admin.service.importexport.ldap.exception;

/**
 * @since 2007.10.01
 * @author Marius Jakobsen <a href="mailto:majks@wmdata.no">majks@wmdata.no</a>
 */
public class LDAPAuthenticationException extends Exception  {

    public LDAPAuthenticationException(Throwable cause) {
        super("LDAP authentication failed", cause);
    }
    
    public LDAPAuthenticationException(String message) {
        super("LDAP authentication failed: \'" + message + "\'");
    }
    
}
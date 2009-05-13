package no.helsebiblioteket.admin.service.importexport.ldap.exception;

/**
 * User: WMMAJKS
 * Date: 12.okt.2007
 * Time: 10:11:50
 */
@SuppressWarnings("serial")
public class LDAPUserAlreadyExistException extends Exception {
    private static final String DEFAULT_MESSAGE = "LDAP user already exists";

    public LDAPUserAlreadyExistException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public LDAPUserAlreadyExistException(String message) {
        super(DEFAULT_MESSAGE + ": \'" + message + "\'");
    }

    public LDAPUserAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }
}

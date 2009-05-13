package no.helsebiblioteket.admin.service.importexport.ldap.exception;

/**
 * User: WMMAJKS
 * Date: 15.okt.2007
 * Time: 12:18:28
 */
@SuppressWarnings("serial")
public class LDAPUserWithEmailAlreadyExistException extends Exception {
    private static final String DEFAULT_MESSAGE = "LDAP user with same email already exists";

    public LDAPUserWithEmailAlreadyExistException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public LDAPUserWithEmailAlreadyExistException(String message) {
        super(DEFAULT_MESSAGE + ": \'" + message + "\'");
    }

    public LDAPUserWithEmailAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }
}

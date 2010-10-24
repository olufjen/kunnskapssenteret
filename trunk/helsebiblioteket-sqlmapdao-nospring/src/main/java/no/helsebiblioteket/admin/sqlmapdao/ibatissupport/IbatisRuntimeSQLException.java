/**
 * Utilities for faking Spring after Spring amputation
 */

package no.helsebiblioteket.admin.sqlmapdao.ibatissupport;

public class IbatisRuntimeSQLException extends IbatisNestedRuntimeException {

    /**
     * Constructor for DataAccessException.
    :             * @param msg the detail message
     */
    public IbatisRuntimeSQLException(String msg) {
        super(msg);
    }

    /**
     * Constructor for DataAccessException.
     * @param msg the detail message
     * @param cause the root cause (usually from using a underlying
     * data access API such as JDBC)
     */
    public IbatisRuntimeSQLException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

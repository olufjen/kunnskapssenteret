/**
 * Utilities for faking Spring after Spring amputation
 */

package no.helsebiblioteket.admin.sqlmapdao.ibatissupport;

public abstract class NestedExceptionUtils {

    public static String buildMessage(String message, Throwable cause) {
        if (cause != null) {
            StringBuffer buf = new StringBuffer();
            if (message != null) {
                buf.append(message).append("; ");
            }
            buf.append("nested exception is ").append(cause);
            return buf.toString();
        } else {
            return message;
        }
    }
}

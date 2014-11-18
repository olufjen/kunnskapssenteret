package no.naks.rammeverk.kildelag.dao;

import java.util.Map;

/**
 * Inconvenience iface for wrapping StoredProcedure class
 * @author jms
 *
 */
public interface FunctionExec {
	public Map execute(Map paramsMap);
}

package no.naks.rammeverk.kildelag.dao;

import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

/**
 * Inconvenience class for wrapping StoredProcedure class. Works with procedures and functions.
 * When working with functions, the return value is treated as an out-parameter, and thus it
 * must be declared in the outputDeclarationParamsMap parameter to the constructor, 
 * the parameter name is then up to the caller to decide.
 * 
 * @author jms
 * 
 */

public class FunctionExecImpl extends StoredProcedure implements FunctionExec {
	
	/**
	 * Constructor managing params declarations and compilation of statement.
	 * 
	 * @param ds - the datasource.
	 * @param procName - name of function or procedure to run.
	 * @param inputDeclarationParamsMap - name and type of input parameters. 
	 * Must match actual parameter values given in the execute method.
	 * @param outputDeclarationParamsMap - name and type of output params from the procedure.
	 */
	
	public FunctionExecImpl(DataSource ds, String procName, Map inputDeclarationParamsMap, Map outputDeclarationParamsMap) {
		
		super(ds, procName);
		
		// declare input params
		for (Object paramName : inputDeclarationParamsMap.keySet()) {
			declareParameter(new SqlParameter((String) paramName, (Integer) inputDeclarationParamsMap.get(paramName)));
		}
		
		// declare output params
		for (Object paramName : outputDeclarationParamsMap.keySet()) {
			declareParameter(new SqlOutParameter((String) paramName, (Integer) outputDeclarationParamsMap.get(paramName)));
		}
		
		compile();
	}

	/**
	 * Perform execution of the function/procedure after preparation in the constuctor.
	 * @param paramsValueMap - map of values. Their keys must match those in 
	 * inputDeclarationParamsMap parameter to the constructor, and value for each key
	 * must match type in inputDeclarationparamsMap parameter to the constructor.
	 * @return - a Map matching the outputDeclarationParamsMap given in the constructor.
	 */
	
	public Map execute(Map paramsValueMap) {
		return super.execute(paramsValueMap);
	}

}

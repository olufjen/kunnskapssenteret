/**
 * 
 */
package no.naks.rammeverk.kildelag.model;

import java.sql.Types;

/**
 * @author ojn
 *
 */
public class LineKeyImpl extends AbstractLineKey {

	/**
	 * 
	 */
	public LineKeyImpl() {
		super();
		types = new int[] {Types.INTEGER,Types.INTEGER};
		utypes = new int[] {Types.INTEGER,Types.INTEGER,Types.INTEGER};		
	}

	
}

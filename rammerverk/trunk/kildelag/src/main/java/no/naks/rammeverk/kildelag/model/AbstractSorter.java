
package no.naks.rammeverk.kildelag.model;

import java.io.Serializable;

/**
 *
 * Denne klassen er ansvarlig for å håndtere sortering på lister etter gitte sorteringkolonner.
 *
 * @author ojn
 *
 */
public abstract class AbstractSorter implements Serializable {
	private String sortColumn = "x";
	private boolean ascending = true;
	/**
	 * @return the ascending
	 */
	public boolean isAscending() {
		return ascending;
	}
	/**
	 * @param ascending the ascending to set
	 */
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
	/**
	 * @return the sortColumn
	 */
	public String getSortColumn() {
		return sortColumn;
	}
	/**
	 * @param sortColumn the sortColumn to set
	 */
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	

}

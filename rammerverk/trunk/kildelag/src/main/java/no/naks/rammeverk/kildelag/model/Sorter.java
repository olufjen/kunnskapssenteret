
package no.naks.rammeverk.kildelag.model;

/**
 *
 * Dette grensesnittet representerer en sorterer
 *
 * @author ojn
 *
 */
public interface Sorter {

	/**
	 * @return the ascending
	 */
	public boolean isAscending();
	/**
	 * @param ascending the ascending to set
	 */
	public void setAscending(boolean ascending);
	/**
	 * @return the sortColumn
	 */
	public String getSortColumn();
	/**
	 * @param sortColumn the sortColumn to set
	 */
	public void setSortColumn(String sortColumn);
}


package no.naks.rammeverk.kildelag.model;

/**
 * 
 * Dette grensesnittet representerer klasser som inneholde n?kler fra linjetabeller
 * @author ojn
 *
 */
public interface LineKey {

	/**
	 * @return the primaryKey
	 */
	public Long getPrimaryKey();
	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(Long primaryKey);
	/**
	 * @return the tableoneKey
	 */
	public Long getTableoneKey();
	/**
	 * @param tableoneKey the tableoneKey to set
	 */
	public void setTableoneKey(Long tableoneKey);
	/**
	 * @return the tabletwoKey
	 */
	public Long getTabletwoKey();
	/**
	 * @param tabletwoKey the tabletwoKey to set
	 */
	public void setTabletwoKey(Long tabletwoKey);
	/**
	 * @return the params
	 */
	public Object[] getParams();
	/**
	 * @param params the params to set
	 */
	public void setParams(Object[] params);
	public void setParams();
	/**
	 * @return the types
	 */
	public int[] getTypes();
	/**
	 * @param types the types to set
	 */
	public void setTypes(int[] types);

	/**
	 * @return the utypes
	 */
	public int[] getUtypes();
	/**
	 * @param utypes the utypes to set
	 */
	public void setUtypes(int[] utypes);
}

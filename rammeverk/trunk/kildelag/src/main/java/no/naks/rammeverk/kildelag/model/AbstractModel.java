package no.naks.rammeverk.kildelag.model;

/**
 * Denne klassen representerer baseklassen for alle modellobjekter
 * Den inneholder definisjoner for databasefelt, samt verdier for disse 
 * databasefelt n?r subklassen blir skapt
 * @author ojn
 *
 */
public abstract class AbstractModel {
	protected int[] types;
	protected int[] utypes;
	protected Object[] params;
	
	/**
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}
	/**
	 * @return the types
	 */
	public int[] getTypes() {
		return types;
	}
	/**
	 * @param types the types to set
	 */
	public void setTypes(int[] types) {
		this.types = types;
	}

	/**
	 * @return the utypes
	 */
	public int[] getUtypes() {
		return utypes;
	}
	/**
	 * @param utypes the utypes to set
	 */
	public void setUtypes(int[] utypes) {
		this.utypes = utypes;
	}
	
}


package no.naks.rammeverk.kildelag.model;

import java.io.Serializable;

/**
 * @author ojn
 *
 */
public abstract class AbstractPrimarykeys extends AbstractModel implements
		Serializable,PrimaryKeys {
	private Long primaryKey;
	private Long tableoneKey;
	private Long tabletwoKey;
	
	public Long getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}
	public Long getTableoneKey() {
		return tableoneKey;
	}
	public void setTableoneKey(Long tableoneKey) {
		this.tableoneKey = tableoneKey;
	}
	public Long getTabletwoKey() {
		return tabletwoKey;
	}
	public void setTabletwoKey(Long tabletwoKey) {
		this.tabletwoKey = tabletwoKey;
	}
	public void setParams() {
		Long id = getPrimaryKey();
		if (id == null){
			params = new Object[]{getTableoneKey(),getTabletwoKey()};
		}else
			params = new Object[]{getTableoneKey(),getTabletwoKey(),getPrimaryKey()};

	}
}

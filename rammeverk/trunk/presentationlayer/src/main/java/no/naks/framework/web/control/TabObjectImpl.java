package no.naks.framework.web.control;

/**
 * Denne klassen representerer TabObject - dynamiske tab/faner i en nettside
 * @author olj
 *
 */
public class TabObjectImpl implements TabObject {

	private String tabName;
	private int tabPosition;
	
	public TabObjectImpl() {
		super();
		this.tabName = "fane";
		this.tabPosition = 1;
	}

	public TabObjectImpl(String tabName, int tabPosition) {
		super();
		this.tabName = tabName;
		this.tabPosition = tabPosition;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public int getTabPosition() {
		return tabPosition;
	}

	public void setTabPosition(int tabPosition) {
		this.tabPosition = tabPosition;
	}

	@Override
	public void tabAction() {
		

	}

}

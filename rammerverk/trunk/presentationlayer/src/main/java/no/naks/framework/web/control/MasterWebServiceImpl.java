package no.naks.framework.web.control;

import no.naks.framework.web.control.DomainDictionary;

/**
 * This class is the super class for all web service classes
 * It contains a reference to the DomainDictionary.
 * 
 * @author ojn
 *
 */
public class MasterWebServiceImpl implements MasterWebService{
	protected DomainDictionary masterDictionary;
	

	/**
	 * 
	 */
	public MasterWebServiceImpl() {
		super();
		
	}


	public DomainDictionary getMasterDictionary() {
		return masterDictionary;
	}


	public void setMasterDictionary(DomainDictionary masterDictionary) {
		this.masterDictionary = masterDictionary;
	}


}

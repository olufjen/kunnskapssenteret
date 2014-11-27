package no.naks.nhn.model;

import java.io.Serializable;

import no.kith.xmlstds.emok.codes.CS;
import no.kith.xmlstds.emok.codes.CV;


/**
 * @author olj
 * Denne klassen representerer person som melder
 */
public class PersonImpl extends AbstractPerson implements Person,Serializable {
	
	private String sCode = "2.16.578.1.12.4.1.1.8401";
	
	public PersonImpl() {
		super();
		yrke = new CS();
		yrke.setDN(sCode);
	}





}

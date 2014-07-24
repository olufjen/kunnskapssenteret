package no.naks.biovigilans.web.model;

import java.util.Date;
import java.util.Map;

import no.naks.biovigilans.model.Blodprodukt;
import no.naks.biovigilans.model.BlodproduktImpl;
import no.naks.biovigilans.model.Pasientkomplikasjon;
import no.naks.biovigilans.model.Transfusjon;
import no.naks.biovigilans.model.TransfusjonImpl;

/**
 * @author olj
 * Denne klassen representerer Transfusjonopplysninger gitt av bruker
 * 
 */
public class TransfusjonWebModel extends VigilansModel {


	private Blodprodukt blodProdukt;
	private Blodprodukt annenBlodprodukt;
	private Transfusjon transfusjon;
	
	public TransfusjonWebModel() {
		super();
		
		blodProdukt = new BlodproduktImpl();
		annenBlodprodukt = new BlodproduktImpl();
		transfusjon = new TransfusjonImpl();

	}

	/**
	 * distributeTerms
	 * Denne rutinene fordeler felter fra skjermbildet til modellobjekter
	 * Feltene fra skjermbildet er definert i filen tables.properties
	 */
	public void distributeTerms(){
		String[] formFields = getFormNames();
		String[] blodProduktFields = {formFields[27],formFields[28],formFields[29],formFields[30],formFields[31],formFields[32],
				formFields[33],formFields[34],formFields[35],formFields[36],formFields[37]};
		String[] egenskaperFields = {formFields[50],formFields[51],formFields[52],formFields[53],formFields[54],formFields[55],formFields[56]}; 
		blodProdukt.setBlodProduktfieldMaps(blodProduktFields);
		blodProdukt.setEgenskaperfieldMaps(egenskaperFields);
		blodProdukt.setKeyvalues();
		String[] transfusjonsFields = {formFields[18],formFields[19],formFields[20],formFields[21],formFields[22],formFields[23],formFields[24],formFields[25],formFields[26]};
		
		transfusjon.settransfusjonsFieldsMaps(transfusjonsFields);
		
		String[] annenblodProduktfields = {formFields[38],formFields[39],formFields[40],formFields[41],formFields[42],formFields[43],
				formFields[44],formFields[45],formFields[46],formFields[47],formFields[48],formFields[49]};
	}

	/**
	 * saveValues
	 * Denne rutinen lagrer feltverdier for transfusjons(pasient)komplikasjoner som er angitt av bruker 
	 */
	public void saveValues() {
		String[] formFields = getFormNames(); // Inneholder navn på input felt i skjermbildet
		Map<String,String> userEntries = getFormMap(); // formMap inneholder verdier angitt av bruker
		for (String field : formFields){
			String userEntry = userEntries.get(field);
			if (userEntry != null && !userEntry.equals(""))
				System.out.println("Key: "+ field + " Innhold =" + userEntry);
			blodProdukt.saveField(field, userEntry);
			transfusjon.saveField(field, userEntry);
		}
		blodProdukt.setAntallEnheter(-1);
		blodProdukt.setBlodprodukt(null);
		blodProdukt.setProduktetsegenskap(null);
		transfusjon.setHastegrad(null);
		transfusjon.setIndikasjon(null);
		transfusjon.setTransDato(null);
		transfusjon.setTransfusjonsklokkeslett(null);
		transfusjon.getBlodProdukter().put(blodProdukt.getBlodprodukt(), blodProdukt);
		
	}
		
	
}

package no.naks.biovigilans.web.model;

import java.util.Date;
import java.util.Map;

import no.naks.biovigilans.model.Blodprodukt;
import no.naks.biovigilans.model.BlodproduktImpl;
import no.naks.biovigilans.model.Komplikasjonsklassifikasjon;
import no.naks.biovigilans.model.KomplikasjonsklassifikasjonImpl;
import no.naks.biovigilans.model.Pasient;
import no.naks.biovigilans.model.Pasientkomplikasjon;
import no.naks.biovigilans.model.PasientkomplikasjonImpl;
import no.naks.biovigilans.model.Symptomer;
import no.naks.biovigilans.model.SymptomerImpl;
import no.naks.biovigilans.model.Transfusjon;
import no.naks.biovigilans.model.TransfusjonImpl;
import no.naks.biovigilans.model.Utredning;
import no.naks.biovigilans.model.UtredningImpl;

/**
 * @author olj
 * Denne klassen representerer Transfusjonopplysninger gitt av bruker.
 * Det omfatter Transfusjon, Blodprodukt, og Pasientkomplikasjon som definert i modellen
 * 
 */
public class TransfusjonWebModel extends VigilansModel {


	private Blodprodukt blodProdukt;
	private Blodprodukt annenBlodprodukt;
	private Transfusjon transfusjon;
	private Pasientkomplikasjon pasientKomplikasjon;
	private Symptomer symptomer;
	private Komplikasjonsklassifikasjon komplikasjonsklassifikasjon;
	private Utredning utredning;
	private Pasient pasient;
	
	public TransfusjonWebModel() {
		super();
		
		blodProdukt = new BlodproduktImpl();
		annenBlodprodukt = new BlodproduktImpl();
		transfusjon = new TransfusjonImpl();
		pasientKomplikasjon = new PasientkomplikasjonImpl();
		symptomer = new SymptomerImpl();
		komplikasjonsklassifikasjon = new KomplikasjonsklassifikasjonImpl();
		utredning = new UtredningImpl();
		
		
	}


	/**
	 * distributeTerms
	 * Denne rutinene fordeler felter fra skjermbildet til modellobjekter
	 * Feltene fra skjermbildet er definert i filen tables.properties
	 */
	public void distributeTerms(){
		String[] formFields = getFormNames();
		String[] blodProduktFields = {formFields[27],formFields[28],formFields[29],formFields[30],formFields[31],formFields[32],
				formFields[33],formFields[34],formFields[35],formFields[36],formFields[37],formFields[56],formFields[57],formFields[58]};
		String[] egenskaperFields = {formFields[49],formFields[50],formFields[51],formFields[52],formFields[53],formFields[54],formFields[55]}; 
		blodProdukt.setBlodProduktfieldMaps(blodProduktFields);
		blodProdukt.setEgenskaperfieldMaps(egenskaperFields);
		blodProdukt.setKeyvalues();
		String[] transfusjonsFields = {formFields[18],formFields[19],formFields[20],formFields[21],formFields[22],formFields[23],formFields[24],formFields[25],formFields[26]};
		
		transfusjon.settransfusjonsFieldsMaps(transfusjonsFields);
		
		String[] annenblodProduktfields = {formFields[38],formFields[39],formFields[40],formFields[41],formFields[42],formFields[43],
				formFields[44],formFields[45],formFields[46],formFields[47],formFields[48],formFields[49],formFields[56],formFields[57],formFields[58]};
		annenBlodprodukt.setBlodProduktfieldMaps(annenblodProduktfields);
		annenBlodprodukt.setKeyvalues();
		String[] pasientKomplikasjonsfields = {formFields[129],formFields[130],formFields[131],formFields[132],formFields[133],formFields[134],
				formFields[135],formFields[136],formFields[137],formFields[138],formFields[139],formFields[140],formFields[141],formFields[142],
				formFields[90],formFields[91],formFields[92],formFields[93],formFields[94],formFields[95],
				formFields[96],formFields[97],formFields[98],formFields[99],formFields[100],formFields[101],formFields[102],formFields[103],
				formFields[104],formFields[105],formFields[106],formFields[107],formFields[108],formFields[109],formFields[110],formFields[111],formFields[112]};
		pasientKomplikasjon.setPatientkomplicationfieldMaps(pasientKomplikasjonsfields);
		String[] symptomerFields = {formFields[64],formFields[65],formFields[66],formFields[67],formFields[68],formFields[69],formFields[70],
				formFields[71],formFields[72],formFields[73],formFields[74],formFields[75],formFields[76],formFields[77],formFields[78],formFields[79],
				formFields[80],formFields[81],formFields[82],formFields[83],formFields[84],formFields[85],formFields[86],formFields[87],
				formFields[88],formFields[89]};
		symptomer.setsymptomerfieldMaps(symptomerFields);
		String[] klassifikasjonsFields = {formFields[90],formFields[91],formFields[92],formFields[93],formFields[94],formFields[95],
				formFields[96],formFields[97],formFields[98],formFields[99],formFields[100],formFields[101],formFields[102],formFields[103],
				formFields[104],formFields[105],formFields[106],formFields[107],formFields[108],formFields[109],formFields[110],formFields[111],formFields[112]};
		komplikasjonsklassifikasjon.setkomplikasjonklassifikasjonFieldsMaps(formFields);
		String[] utredningFields = {formFields[113],formFields[114],formFields[115],formFields[116],formFields[117],formFields[118],formFields[119],
				formFields[120],formFields[121],formFields[122],formFields[123],formFields[124],formFields[125],formFields[126],formFields[127]};
		utredning.setutredningfieldMaps(utredningFields);
		
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
			annenBlodprodukt.saveField(field, userEntry);
			transfusjon.saveField(field, userEntry);
			pasientKomplikasjon.saveField(field, userEntry);
			symptomer.saveField(field, userEntry);
			komplikasjonsklassifikasjon.saveField(field, userEntry);
			utredning.saveField(field, userEntry);
		}
		blodProdukt.setAntallEnheter(-1);
		blodProdukt.setBlodprodukt(null);
		blodProdukt.setProduktetsegenskap(null);
		blodProdukt.setTappetype(null);
		
		annenBlodprodukt.setAntallEnheter(-1);
		annenBlodprodukt.setBlodprodukt(null);
//		annenBlodprodukt.setTappetype(null);
//		annenBlodprodukt.setProduktetsegenskap(null);
		transfusjon.setHastegrad(null);
		transfusjon.setIndikasjon(null);
		transfusjon.setTransDato(null);
		transfusjon.setTransfusjonsklokkeslett(null);
		transfusjon.getBlodProdukter().put(blodProdukt.getBlodprodukt(), blodProdukt);
		pasientKomplikasjon.setAlvorlighetsgrad(null);
		pasientKomplikasjon.setKliniskresultat(null);
		pasientKomplikasjon.setArsakssammenheng(null);
		pasientKomplikasjon.setKlassifikasjon(null);
//		pasientKomplikasjon.produceClassification(komplikasjonsklassifikasjon); Denne tabellen er ikke i bruk! Kun en klassifikasjon pr komplikasjon
		pasientKomplikasjon.produceSymptoms(symptomer);
		utredning.saveUtredning();
		pasientKomplikasjon.setUtredning(utredning);
		transfusjon.getPasientKomplikasjoner().put(pasientKomplikasjon.getAlvorlighetsgrad(), pasientKomplikasjon);
		
	}


	public Transfusjon getTransfusjon() {
		return transfusjon;
	}


	public void setTransfusjon(Transfusjon transfusjon) {
		this.transfusjon = transfusjon;
	}


	public Blodprodukt getBlodProdukt() {
		return blodProdukt;
	}


	public void setBlodProdukt(Blodprodukt blodProdukt) {
		this.blodProdukt = blodProdukt;
	}


	public Blodprodukt getAnnenBlodprodukt() {
		return annenBlodprodukt;
	}


	public void setAnnenBlodprodukt(Blodprodukt annenBlodprodukt) {
		this.annenBlodprodukt = annenBlodprodukt;
	}


	public Pasientkomplikasjon getPasientKomplikasjon() {
		return pasientKomplikasjon;
	}


	public void setPasientKomplikasjon(Pasientkomplikasjon pasientKomplikasjon) {
		this.pasientKomplikasjon = pasientKomplikasjon;
	}


	public Symptomer getSymptomer() {
		return symptomer;
	}


	public void setSymptomer(Symptomer symptomer) {
		this.symptomer = symptomer;
	}


	public Komplikasjonsklassifikasjon getKomplikasjonsklassifikasjon() {
		return komplikasjonsklassifikasjon;
	}


	public void setKomplikasjonsklassifikasjon(
			Komplikasjonsklassifikasjon komplikasjonsklassifikasjon) {
		this.komplikasjonsklassifikasjon = komplikasjonsklassifikasjon;
	}


	public Utredning getUtredning() {
		return utredning;
	}


	public void setUtredning(Utredning utredning) {
		this.utredning = utredning;
	}


	public Pasient getPasient() {
		return pasient;
	}


	public void setPasient(Pasient pasient) {
		this.pasient = pasient;
		this.transfusjon.setPasient_Id(this.pasient.getPasient_Id());
	}
		
	
}

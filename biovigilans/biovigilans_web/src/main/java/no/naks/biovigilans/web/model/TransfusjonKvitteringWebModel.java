package no.naks.biovigilans.web.model;

import java.util.Map;

/**
 * @author olj
 *
 * Denne klassen benyttes til åvise en kvitteringsside for hva melder har registrert fra en transfusjonskomplikasjon
 * 
 */
public class TransfusjonKvitteringWebModel extends VigilansModel {

	private String sykdomBehandling;
	private String sykdomBetydning;
	private String avdeling;
	private String tidTransfusjon;
	private String datoTransfusjon;
	private String indikasjonTransfusjon;
	
	
	public TransfusjonKvitteringWebModel() {
		super();


	}


	public String getSykdomBehandling() {
		Map<String,String> userEntries = getFormMap();
		String field = "p-antistoff-behandling";
		sykdomBehandling = userEntries.get(field);
		if (sykdomBehandling == null)
			sykdomBehandling = "-";
		return sykdomBehandling;
	}


	public void setSykdomBehandling(String sykdomBehandling) {
		this.sykdomBehandling = sykdomBehandling;
	}


	public String getSykdomBetydning() {
		Map<String,String> userEntries = getFormMap();
		String field = "p-annen-sykdom";
		sykdomBetydning = userEntries.get(field);
		if (sykdomBetydning == null)
			sykdomBetydning = "-";
		return sykdomBetydning;
	}


	public void setSykdomBetydning(String sykdomBetydning) {
		this.sykdomBetydning = sykdomBetydning;
	}


	public String getAvdeling() {
		Map<String,String> userEntries = getFormMap();
		String field = "p-avdeling-list";
		avdeling = userEntries.get(field);
		if (avdeling == null)
			avdeling = "-";
		return avdeling;
	}


	public void setAvdeling(String avdeling) {
		this.avdeling = avdeling;
	}


	public String getTidTransfusjon() {
		Map<String,String> userEntries = getFormMap();
		String field = "transfusjon-time";
		tidTransfusjon = userEntries.get(field);
		if (tidTransfusjon == null)
			tidTransfusjon = "-";
		return tidTransfusjon;
	}


	public void setTidTransfusjon(String tidTransfusjon) {
		this.tidTransfusjon = tidTransfusjon;
	}


	public String getDatoTransfusjon() {
		return datoTransfusjon;
	}


	public void setDatoTransfusjon(String datoTransfusjon) {
		this.datoTransfusjon = datoTransfusjon;
	}


	public String getIndikasjonTransfusjon() {
		Map<String,String> userEntries = getFormMap();
		String field = "p-antistoff-transfusjon";
		indikasjonTransfusjon = userEntries.get(field);
		if (indikasjonTransfusjon == null)
			indikasjonTransfusjon = "-";
		return indikasjonTransfusjon;
	}


	public void setIndikasjonTransfusjon(String indikasjonTransfusjon) {
		this.indikasjonTransfusjon = indikasjonTransfusjon;
	}

	
}

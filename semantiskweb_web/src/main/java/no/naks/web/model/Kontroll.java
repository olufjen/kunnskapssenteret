package no.naks.web.model;

import java.io.Serializable;
import java.util.Date;

import no.naks.emok.model.IBasismelding;

public class Kontroll implements Serializable {
	
	private boolean hasError;
	private boolean blHvorhvem;
	private boolean blHendelse;
	private boolean blKontaktInfo;
	private boolean spesialistHidden;
	private boolean sykehusHidden;
	private boolean datoHidden;
	private boolean klokkeHidden;
	private boolean aarHidden;
	private String onsker;

	public Kontroll() {
		super();
	}
	

	public String getOnsker() {
		return onsker;
	}


	public void setOnsker(String onsker) {
		this.onsker = onsker;
	}


	public boolean isSpesialistHidden() {
		return spesialistHidden;
	}


	public void setSpesialistHidden(boolean spesialistHidden) {
		this.spesialistHidden = spesialistHidden;
	}


	public boolean isSykehusHidden() {
		return sykehusHidden;
	}


	public void setSykehusHidden(boolean sykehusHidden) {
		this.sykehusHidden = sykehusHidden;
	}


	public boolean isDatoHidden() {
		return datoHidden;
	}


	public void setDatoHidden(boolean datoHidden) {
		this.datoHidden = datoHidden;
	}


	public boolean isKlokkeHidden() {
		return klokkeHidden;
	}


	public void setKlokkeHidden(boolean klokkeHidden) {
		this.klokkeHidden = klokkeHidden;
	}


	public boolean isAarHidden() {
		return aarHidden;
	}


	public void setAarHidden(boolean aarHidden) {
		this.aarHidden = aarHidden;
	}


	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public boolean isBlHvorhvem() {
		return blHvorhvem;
	}

	public void setBlHvorhvem(boolean blHvorhvem) {
		this.blHvorhvem = blHvorhvem;
	}

	public boolean isBlHendelse() {
		return blHendelse;
	}

	public void setBlHendelse(boolean blHendelse) {
		this.blHendelse = blHendelse;
	}


	public boolean isBlKontaktInfo() {
		return blKontaktInfo;
	}


	public void setBlKontaktInfo(boolean blKontaktInfo) {
		this.blKontaktInfo = blKontaktInfo;
	}

	
}

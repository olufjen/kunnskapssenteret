package no.naks.emok.model;

import java.util.Date;

import no.naks.nhn.model.Person;
import no.naks.services.nhn.client.Organization;

public interface IMelding {

	public abstract Date getDatoformeldingen();

	public abstract void setDatoformeldingen(Date datoformeldingen);

	public abstract Person getEntydigidmelder();

	public abstract void setEntydigidmelder(Person entydigidmelder);

	public abstract String getEntydigidk();

	public abstract void setEntydigidk(String entydigidk);

	public abstract int getMelder_enhet();

	public abstract void setMelder_enhet(int melder_enhet);

	public abstract Organization getNhnadresse();

	public abstract void setNhnadresse(Organization nhnadresse);

	public abstract int getVedlegg();

	public abstract void setVedlegg(int vedlegg);

}
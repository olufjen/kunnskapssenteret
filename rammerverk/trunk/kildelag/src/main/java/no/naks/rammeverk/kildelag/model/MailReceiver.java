package no.naks.rammeverk.kildelag.model;

import java.util.Date;

/**
 * Dette grensesnittet representerer objekter som kan representere "email text" meldinger
 * og mottakere av epost
 * @author olj
 *
 */
public interface MailReceiver {

	/**
	 * Denne rutinen returnerer en e-post adresse
	 * @return
	 */
	public String getEmail();
	public int getDay();
	public int getHour();
	public Date getSendDate();
	public String getLanguage();
	public String getType();
	public String getSubject();
	public String getDescription();
}

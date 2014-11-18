package no.naks.rammeverk.kildelag.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import no.naks.rammeverk.kildelag.model.MailReceiver;

/**
 * Dette grensesnittet sørger for at Mailer får informasjon om 
 * Hvilke eposter som skal sendes til mottakere ved gitte tidpunkter
 * @author olj
 *
 */
public interface MailCollector {

	public List<MailReceiver> collectReceivers(String language,String type);
	public List<MailReceiver> collectEmails();
	public HashMap collectReminderEmails(Date entryDate, String countryCode);
	public List<MailReceiver> getReminderReceivers(Date entryDate);
	public List<MailReceiver> getReminderReceivers(Date entryDate,Date agreementDate);
	public String getForgetMailSalutation();
	public String getForgetMailBottom();
	public String getReminderFlag();
	
}

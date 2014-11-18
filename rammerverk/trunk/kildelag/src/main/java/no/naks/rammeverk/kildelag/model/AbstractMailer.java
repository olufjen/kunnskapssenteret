package no.naks.rammeverk.kildelag.model;

import java.io.Serializable;
import java.util.List;

import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * AbstractMail
 * Denne klassen representerer en e-post som blir sendt til et gitt utvalg
 * Instansierte objekter av denne klassen kan ogs? s?rge for ? sende seg selv
 * i et samarbeid med
 * @author olj
 *
 */
public abstract class AbstractMailer extends AbstractModel implements Serializable,Mailer {

	private String mailSubject;
	private String mailText;
	private String mailSalutation;
	private String mailBottom;
	private String mailSenderaddress;
	protected MailReceiver message; // Aktuell melding som skal sendes
	
	protected List<MailReceiver> selection; // Mail receivers
	protected List<MailReceiver> messages; // Mail receivers meldinger som kan sendes
	protected String mailerType;
	protected String reminderFlag; // Bestemmer hvor ofte reminder epost skal sjekkes
	protected static Log log = LogFactory.getLog(AbstractMailer.class);
	public AbstractMailer() {
		super();
//		mailSenderaddress = "oluf.jensen@nokc.no";
		mailSalutation = "Hei";
		mailBottom = "";
		mailerType = "reminder";
//		reminderFlag = "0";
	}
	
	public MailReceiver getMessage() {
		return message;
	}

	public void setMessage(MailReceiver message) {
		this.message = message;
		this.mailSubject = "";
		this.mailText = "";
		if (message != null){
			this.mailSubject = message.getSubject();
			this.mailText = message.getDescription();
		}
	}

	public String getReminderFlag() {
		return reminderFlag;
	}

	public void setReminderFlag(String reminderFlag) {
		this.reminderFlag = reminderFlag;
	}

	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailText() {
		return mailText;
	}
	public void setMailText(String mailText) {
		this.mailText = mailText;
	}
	public String getMailSalutation() {
		return mailSalutation;
	}
	public void setMailSalutation(String mailSalutation) {
		this.mailSalutation = mailSalutation;
	}
	public String getMailBottom() {
		return mailBottom;
	}
	public void setMailBottom(String mailBottom) {
		this.mailBottom = mailBottom;
	}
	public List getSelection() {
		return selection;
	}
	public void setSelection(List selection) {
		this.selection = selection;
	}
	public String getMailSenderaddress() {
		return mailSenderaddress;
	}
	public void setMailSenderaddress(String mailSenderaddress) {
		this.mailSenderaddress = mailSenderaddress;
	}
	public List<MailReceiver> getMessages() {
		return messages;
	}
	public void setMessages(List<MailReceiver> messages) {
		this.messages = messages;
	}

	public String getMailerType() {
		return mailerType;
	}

	public void setMailerType(String mailerType) {
		this.mailerType = mailerType;
	}

	
	
	
}


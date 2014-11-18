package no.naks.rammeverk.kildelag.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.quartz.SchedulerException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import no.naks.rammeverk.kildelag.dao.CallSchedulerJob;

import no.naks.rammeverk.kildelag.dao.MailCollector;

/**
 * MailsenderImpl
 * Denne klasses sørger for å sende e-post til mottakere representert i et utvalg
 * En mailsender kan være av ulik type: 1 - reminder, 2 - 
 * @author olj
 *
 */
/**
 * @author olj
 *
 */
public class MailerImpl extends AbstractMailer implements Mailer,
		Serializable {

	private JavaMailSenderImpl javaMailSender;
	
	private static String flag="on";
	private String schedulerFlag;
	private String schedule; // Tid for når utsendelse skal skje
	private String reminderSubject ="Påminnelse - registrering av deltakere"; 
	//private EmailDAOImpl emailDAORef;
	CallSchedulerJob callSchedulerJob = new CallSchedulerJob();
	private MailCollector mailCollector ;
	private Mailer mailSender;
	public MailerImpl() {
		super();
		setMailSenderaddress("pasopp@kunnskapssenteret.no");
		setMessage(null);
	
	}

	public MailerImpl(JavaMailSenderImpl javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
		setMailSenderaddress("pasopp@kunnskapssenteret.no");
		setMessage(null);
	}

	/* (non-Javadoc)
	 * Denne rutinen returnerer en liste over meldingstekster
	 * @see no.naks.rammeverk.kildelag.model.Mailer#getEmailTexts()
	 */
	public List<MailReceiver> getEmailTexts() {
		
		return getMessages();
	}
	


	@Override
	public List<MailReceiver> getReminderReceivers(Date entryDate) {
		
		return getSelection();
	}

	public void sendEmails(String language,String type) {
	
		Date date = new Date();
		boolean messagesSent = false;
		for (MailReceiver mailMessage:messages){
			findEmail(mailMessage);
			List<MailReceiver> receivers = getSelection();
			if (getMessage() != null && receivers != null && !receivers.isEmpty()){
				messagesSent = true;
				try{
					String emailAddress = "oluf.jensen@kunnskapssenteret.no";
					MimeMessage message = javaMailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message,true);
					helper.setTo(emailAddress);
					helper.setFrom(getMailSenderaddress());
					String antMail = String.valueOf(receivers.size());
					helper.setSubject("Sending emails to "+antMail);
					helper.setText(" MailerImpl This is an automatically genereated email from studien (nokc)\n Participants to send email to\nEmailType: "+getMessage().getType()+"\t" +date );
					javaMailSender.send(message);
				}catch(MessagingException e){
					log.error(e.getMessage());	
				}

				for (MailReceiver participant:receivers){
					String email = participant.getEmail();
					MimeMessage message = javaMailSender.createMimeMessage();
					MimeMessageHelper helper;
					log.info ("MailImpl: sender til "+email);	
					String enClosure = "";
					if (participant.getType() != null)
						enClosure = participant.getType();
					try {
						helper = new MimeMessageHelper(message,true);
						helper.setTo(email);
						helper.setFrom(	getMailSenderaddress());
						helper.setSubject(getMailSubject());
						helper.setText(getMailSalutation() + "<br>"+
								getMailText()+" " + enClosure +
								getMailBottom() , true);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}



		//			javaMailSender.send(message);
				}
			}
		}
		if (!messagesSent){
			log.info ("MailImpl:sendEmails no emails sent today");
		}
		
		
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public JavaMailSenderImpl getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	private void checkScheduler() throws SchedulerException, ParseException{
		if(schedulerFlag.equalsIgnoreCase("on") && flag.equalsIgnoreCase("on") ){
//			setSelection(emailDAORef.getEmailTexts());
//			callSchedulerJob.setMailSender(emailDAORef);
		//	setSelection(mailCollector.collectEmails());
			callSchedulerJob.setMailCollector(mailCollector);
			callSchedulerJob.jobQue();
			flag ="off";
			setSchedulerFlag("off");			
		}
	}
	public String getSchedulerFlag() {
		return schedulerFlag;
	}
	public void setSchedulerFlag(String schedulerFlag) throws SchedulerException, ParseException {
		this.schedulerFlag = schedulerFlag;
		checkScheduler();
	}


	public MailCollector getMailCollector() {
		return mailCollector;
	}

	/* setMailCollector
	 * Denne rutinen setter opp klassen som knytter tjenesten til prosjektet som
	 * ønsker å benytte tjenesten. Klassen som prosjektet gir til tjenesten gjennom dette
	 * kallet, må være av typen MailCollector.
	 * 
	 * @see no.naks.rammeverk.kildelag.model.Mailer#setMailCollector(no.naks.rammeverk.kildelag.dao.MailCollector)
	 */
	public void setMailCollector(MailCollector mailCollector) {
		this.mailCollector = mailCollector;
		setMailBottom(mailCollector.getForgetMailBottom());
		reminderFlag = mailCollector.getReminderFlag();
		if (reminderFlag == null)
			reminderFlag = "1";
		Date entryDate = new Date();
		Date agreementDate = new Date();
		GregorianCalendar lastWeek = new GregorianCalendar();
		Date idag = new Date();
		lastWeek.setTime(idag);
		lastWeek.add(GregorianCalendar.DAY_OF_WEEK, -7);
		agreementDate = lastWeek.getTime();
		setMessages(this.mailCollector.collectEmails());
		if (mailerType.equals("reminder")){
			//setSelection(this.mailCollector.getReminderReceivers(entryDate,agreementDate)); // Reminderweekselect dvs 1. purring
			List<MailReceiver> localMessages = new ArrayList();
		
			for (MailReceiver message:messages){
				String mType = message.getType();
				if (mType.equals("ikkesv") || mType.equals("iksiden"))
					localMessages.add(message);
				log.info ("MailImpl epost: "+ message.getSubject());
			}
			messages.clear();
			messages.addAll(localMessages);
		}
		/*
		for (MailReceiver mailMessage:messages){ // Dette er satt opp for å teste utsendelse
			findEmail(mailMessage);					// Kommenteres bort under produksjon
		}
		*/
		log.info ("MailImpl: Satt opp epost");
	}

	public Mailer getMailSender() {
		return mailSender;
	}


	public void setMailSender(Mailer mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public String getEmailSender() {
		
		return getMailSenderaddress();
	}

	@Override
	public void setEmailSender(String emailSender) {
		setMailSenderaddress(emailSender);
		
	}


	/**
	 * findEmail
	 * Denne rutinen finner frem til hvilken epost som skal sendes ved dagens utsendelse
	 * 
	 */
	private void findEmail(MailReceiver message){
		//setMessage(null);
		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar lastWeek = new GregorianCalendar();
		Date idag = new Date();
		Date sistUke = null;
		today.setTime(idag);
		lastWeek.setTime(idag);
		lastWeek.add(GregorianCalendar.DAY_OF_WEEK, -7); // Hvor lang tid tilbake?
		sistUke = lastWeek.getTime();
		GregorianCalendar sDate = new GregorianCalendar();
		int weekofyear = sDate.get(GregorianCalendar.WEEK_OF_YEAR) - 1;
		sDate.add(GregorianCalendar.WEEK_OF_YEAR, -weekofyear);
		Date temp2weeksDate = sDate.getTime();
		int month = today.get(GregorianCalendar.MONTH);
		int dayofmonth = today.get(GregorianCalendar.DAY_OF_MONTH);
		int ukedag = today.get(GregorianCalendar.DAY_OF_WEEK) - 1; // første ukedag søndag !!!!
		String mType = message.getType();
		if (reminderFlag == null)
			reminderFlag = "1";
		if (reminderFlag.equals("1") || message.getDay() == ukedag){
			setMessage(message);
			if (mType.equals("iksiden")){
				sistUke = lastWeek.getTime();
				setSelection(this.mailCollector.getReminderReceivers(temp2weeksDate,temp2weeksDate)); // reminderselect
				log.info ("MailImpl: utvalg iksiden");
			}
			if (mType.equals("ikkesv")){
				if (message.getSubject().equalsIgnoreCase(reminderSubject)){
					setSelection(this.mailCollector.getReminderReceivers(idag,null)); //remindernoagreementselect
					log.info ("MailImpl: utvalg ikkesv deltakere ikke registrert");
				} else{
					setSelection(this.mailCollector.getReminderReceivers(temp2weeksDate,sistUke)); 
					log.info ("MailImpl: utvalg ikkesv deltakere har ikke svart");
				}
			}
		}
		if (this.message == null){
			if (message.getSendDate() != null)
					sDate.setTime(message.getSendDate());
			if (sDate.get(GregorianCalendar.MONTH) == month && sDate.get(GregorianCalendar.DAY_OF_MONTH) == dayofmonth){
					setMessage(message);
					mType = message.getType();
					if (mType.equals("iksiden")){
						sistUke = lastWeek.getTime();
						setSelection(this.mailCollector.getReminderReceivers(temp2weeksDate,temp2weeksDate)); // reminderselect
						log.info ("MailImpl: utvalg iksiden (2)");
					}
					if (mType.equals("ikkesv")){
						if (message.getSubject().equalsIgnoreCase(reminderSubject)){
							setSelection(this.mailCollector.getReminderReceivers(temp2weeksDate,null));
							log.info ("MailImpl: utvalg ikkesv deltakere ikke registrert (2)");
						} else{
							setSelection(this.mailCollector.getReminderReceivers(temp2weeksDate,sistUke));
							log.info ("MailImpl: utvalg ikkesv deltakere har ikke svart (2)");
						}
					}			
		
			}
		}
		
	}
		
}

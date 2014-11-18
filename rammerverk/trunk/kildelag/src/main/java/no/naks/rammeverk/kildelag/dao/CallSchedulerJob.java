/**
 * 
 */
package no.naks.rammeverk.kildelag.dao;

import no.naks.rammeverk.kildelag.dao.EmailScheduler;
import no.naks.rammeverk.kildelag.model.MailReceiver;
import no.naks.rammeverk.kildelag.model.Mailer;
import no.naks.rammeverk.kildelag.model.MailerImpl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Denne klassen fungerer som en "Timer" som sender epost til et sett mottakere
 * ved gitte tidspunkt
 * @author qaa
 *
 */
public class CallSchedulerJob extends QuartzJobBean {
	private static final String APPLICATION_CONTEXT_KEY = "applicationContext";
	Log log = LogFactory.getLog(CallSchedulerJob.class);
	Date date = new Date();
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	private Mailer mailSender;
	private Mailer mailReminder;
	private MailCollector mailCollector;
	
	@Override
	protected void executeInternal(JobExecutionContext arg)
			throws JobExecutionException {
		try {
			log.info("Callschedulerjob run at " + date );
			jobQue();
	    } catch (Exception ex) {
	    	log.info("Callschedulerjob exception" + date );
            ex.printStackTrace();
        }
	}

	



	public Mailer getMailSender() {
		return mailSender;
	}


	public void setMailSender(Mailer mailSender) {
		this.mailSender = mailSender;

	}

	public Mailer getMailReminder() {
		return mailReminder;
	}


	public void setMailReminder(Mailer mailReminder) {
		this.mailReminder = mailReminder;
	}


	public MailCollector getMailCollector() {
		return mailCollector;
	}


	public void setMailCollector(MailCollector mailCollector) {
		this.mailCollector = mailCollector;
	}


	/**
	 * jobQue
	 * Denne rutinen setter opp en mail tjeneste for å sende e-post til gitte tidspunkt
	 * 
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public void jobQue() throws SchedulerException, ParseException{
		List<MailReceiver> emailTexts = null;
		if (mailSender != null){
			emailTexts = mailSender.getEmailTexts();
		}else{
			log.info("Callschedulerjob in jobQue no mailsender" );
		}
				

		//	emailTexts = mailCollector.collectEmails();
		log.info (" Call scheduler: In jobQue() function total emailtext = "+emailTexts.size());
		Date date = new Date() ;
		GregorianCalendar today = new GregorianCalendar();
		today.setTime(date);
	    int day = date.getDay();
	    int dayOfMonth = date.getDate();
		int year = date.getYear() +1900 ;
		int month = date.getMonth()+1;
		int mint = date.getMinutes();
		//Mailer mailReminder = null;
		if (mailReminder == null){
			mailReminder = new MailerImpl(mailSender.getJavaMailSender());
			log.info("Callschedulerjob no mailreminder");
		}
	
		mailReminder.setMailSenderaddress(mailSender.getMailSenderaddress());
		mailReminder.setMailCollector((MailCollector)mailSender);
		int remmonth = today.get(GregorianCalendar.MONTH)+ 1;
		int thisHour = today.get(GregorianCalendar.HOUR_OF_DAY);
		int thisDay = today.get(GregorianCalendar.DAY_OF_WEEK);
		String tidpunkt = mailReminder.getSchedule();
		String reminderSchedule = tidpunkt + " " + today.get(GregorianCalendar.DAY_OF_MONTH) + " " + remmonth  + " " + "?" + " " + today.get(GregorianCalendar.YEAR);
		String rjobName = "reminderJob";
////     String klokke = tidpunkt.substring(5,7);
//		int setHour = Integer.parseInt(klokke);
		EmailScheduler emailrScheduler = new EmailScheduler(mailReminder);
//		if (thisDay == GregorianCalendar.MONDAY && thisHour < 12) // Skal scheduler gå hver dag eller en gang i uka?
//		if (thisHour <= setHour)	// 
			emailrScheduler.startService(null, "nor", "rem", reminderSchedule,rjobName);
		for (MailReceiver mailMessage:emailTexts){ // For alle eposter 
			String mailType = mailMessage.getType();
	
			int mailReceiverDay = mailMessage.getDay();
			int mailReceiverHour = mailMessage.getHour();
			Date sDate = mailMessage.getSendDate();
			GregorianCalendar sendDate = new GregorianCalendar();
			if (sDate != null)
				sendDate.setTime(sDate);
			boolean nowTime = false;
			if (sDate != null && mailReceiverDay == 0){
				//mailReceiverDay = sDate.getDay();
				nowTime = today.get(GregorianCalendar.MONTH) == sendDate.get(GregorianCalendar.MONTH) && today.get(GregorianCalendar.DATE) == sendDate.get(GregorianCalendar.DATE); 
			}
			int hour = date.getHours();
			if(mailReceiverDay==day && mailReceiverHour > hour || (mailReceiverDay == 0 && nowTime) ){
				String language = mailMessage.getLanguage();
				String type= mailMessage.getType();
				if(! type.equalsIgnoreCase("remove")  ){
					mint +=2;
					String jobName = type + language ;
					log.info(jobName + " email scheduler time is set ");
				//                        Second Minutes Hour dayofMonth        Month        query   Year
					String setSchedule = "0 0"+" "+mailReceiverHour+" "+ dayOfMonth +" "+ month +" "+ "?" + " "+year;
				//	mailSender.setSelection(selection)
	//				EmailScheduler emailScheduler = new EmailScheduler(); // En emailScheduler for hver epost
	//				emailScheduler.startService(mailSender, language, type, setSchedule, jobName);
	
				}
			}
		}
	}
}

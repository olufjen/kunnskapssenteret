/**
 * 
 */
package no.naks.rammeverk.kildelag.dao;

import java.util.Date;

import no.naks.rammeverk.kildelag.model.Mailer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author qaa
 *  
 */
public class EmailReportJob extends QuartzJobBean  {
	Log log = LogFactory.getLog(CallSchedulerJob.class);
	Date date = new Date();
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	private Mailer mailSender;
	private Mailer mailReminder;
	private String language;
	private String type;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		log.info("Emailreportjob run sending emails " +  language + " " + date );
		if (mailSender != null)
			mailSender.sendEmails(language,type);
	    if (mailReminder != null){
	    	mailReminder.sendEmails(language, type);
	    }
	}

	public Mailer getMailSender() {
		return mailSender;
	}

	public void setMailSender(Mailer mailSender) {
		this.mailSender = mailSender;
	}

	public void setLanguage( String language ){
		this.language = language;
	}
	
	public void setType(String type){
		this.type = type;
	}

	public Mailer getMailReminder() {
		return mailReminder;
	}

	public void setMailReminder(Mailer mailReminder) {
		this.mailReminder = mailReminder;
	}


	
	
}

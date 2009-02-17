package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.service.EmailService;

public class EmailServiceWeb extends BasicWebService implements EmailService{
	protected static final Log logger = LogFactory.getLog(EmailServiceWeb.class);
	private QName sendEmailName;
	@Override
	public Log getLogger() { return this.logger; }
	@Override
	public Boolean sendEmail(Email email) {
		Object[] args = new Object[] { email };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.sendEmailName, args, returnTypes);
	}
	public void setSendEmailName(QName sendEmailName) {
		this.sendEmailName = sendEmailName;
	}
}

package no.helsebiblioteket.admin.translator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.User;


public class EmailMessageTranslator {
	private Log logger = LogFactory.getLog(getClass());
	
	public String translate(String emailMessage, User user) {
		String tmp = emailMessage;
		if (user != null) {
			tmp = tmp.replaceAll("##username##", user.getUsername());
	     	tmp = tmp.replaceAll("##password##", user.getPassword());
	     	if (user.getPerson() != null) {
		     	tmp = tmp.replace("##name##", user.getPerson().getName());
		     	if (user.getPerson().getContactInformation() != null) {
		     		tmp = tmp.replace("##email##", user.getPerson().getContactInformation().getEmail());
		     	}
	     	}
		}
		tmp = tmp.replaceAll("\n", "\n");
     	tmp = tmp.replaceAll("\\x5cn", "\n");
		return tmp;
	}
}
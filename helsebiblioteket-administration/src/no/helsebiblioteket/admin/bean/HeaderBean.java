package no.helsebiblioteket.admin.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HeaderBean {
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
	
	public String actionLogOut() {
		logger.debug("method 'actionLogOut' invoked");
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
		}
	return "logout";
	} 
}
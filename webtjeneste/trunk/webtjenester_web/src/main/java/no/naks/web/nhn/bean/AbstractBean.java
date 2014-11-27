
package no.naks.web.nhn.bean;
        
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import no.naks.web.nhn.control.TableWebService;

/**
 * This class is the abstract superclass for all Java Beans in this project
 * It contains the references to the control service interfaces available for this
 * project. 
 * Each backing bean has their own implementation of these services.
 * 
 * @author ojn
 *
 */

public abstract class AbstractBean {


	protected TableWebService tableWebService;



	public AbstractBean() {
		super();
	
	}

	protected void setSessionObject(String key, Object object) {
		((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute(key, object);
	}
	
	protected Object getSessionObject(String key) {
		return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute(key);
	}

	




}

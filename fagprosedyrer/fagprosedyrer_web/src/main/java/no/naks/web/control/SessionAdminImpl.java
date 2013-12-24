package no.naks.web.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.restlet.Request;
import org.restlet.ext.servlet.ServletUtils;

/**
 * Denne klassen administerer session objekter for Restlet Resurser
 * @author olj
 *
 */
public class SessionAdminImpl implements SessionAdmin {

	public SessionAdminImpl() {
		super();
		  System.out.println("SessionAdmin started");
	}

	@Override
	public Object getSessionObject(Request request,String idKey) {
	     HttpServletRequest req = ServletUtils.getRequest(request);
	     HttpSession session = req.getSession();
	     Object result = session.getAttribute(idKey);
		return result;
	}

	@Override
	public void setSessionObject(Request request, Object o, String idKey) {
		  HttpServletRequest req = ServletUtils.getRequest(request);
		  HttpSession session = req.getSession();
		  session.setAttribute(idKey, o);

	}

}

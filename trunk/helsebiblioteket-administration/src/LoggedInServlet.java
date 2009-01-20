import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.translator.OrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;


public class LoggedInServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("<?xml version=\"1.0\" ?>");
    	buffer.append("<loggedin>");
    	buffer.append("<node>value</node>");
    	buffer.append("</loggedin>");
		OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream());
		writer.append(buffer);
		writer.close();
    }
}
package no.helsebiblioteket.evs.plugin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RegisterUserController controller = new RegisterUserController();
		Map<String, String> parameterNames = new HashMap<String, String>();
		controller.setParameterNames(parameterNames);
		parameterNames.put("saveName", "save");
		parameterNames.put("cancelName", "cancel");
		parameterNames.put("confirmName", "confirm");
		parameterNames.put("emailFromAddressText", "");
		parameterNames.put("emailFromNameText", "");
		parameterNames.put("emailMessageText", "");
		parameterNames.put("emailSubjectText", "");
		parameterNames.put("saveValue", "true");
		parameterNames.put("cancelValue", "Avbryt");
		parameterNames.put("confirmValue", "true");
		
		parameterNames.put("usertype", "usertype");
		parameterNames.put("hprnumber", "hprnumber");
		parameterNames.put("studentnumber", "studentnumber");
		parameterNames.put("dateofbirth", "dateofbirth");
		parameterNames.put("firstname", "firstname");
		parameterNames.put("lastname", "surname");
		parameterNames.put("employer", "org");
		parameterNames.put("studentansatt", "studentansatt");
		parameterNames.put("newsletter", "newsletter");
		parameterNames.put("survey", "survey");
		parameterNames.put("emailaddress", "email");
		parameterNames.put("repeatemail", "repeatemail");
		
		parameterNames.put("username", "username");
		parameterNames.put("password", "password");
		parameterNames.put("passwordrepeat", "confirmpassword");
		parameterNames.put("position", "position");
		parameterNames.put("positiontext", "positiontext");
		
		controller.setResultSessionVarName("TEST");
		
		
		// TODO: NEW!
		parameterNames.put("altposition", "altposition");
		
		
		String url = req.getParameter("url");
		String got0 = req.getParameter("goto");
		System.out.println("url=" + url);
		System.out.println("goto=" + got0);

		
		
		try {
			controller.handleRequest(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println("OK");
		
		
		
		
		
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getParameter("url");
		
		System.out.println("url=" + url);
		
		url = "http://www.helsebiblioteket.no/test+alle+sammen?t=1";
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<a href =http://localhost:8080/testapp/MyServlet?url=\"" + URLEncoder.encode(url, "UTF-8") + "\">klikk</a>");
		out.println("OK");
	}
}

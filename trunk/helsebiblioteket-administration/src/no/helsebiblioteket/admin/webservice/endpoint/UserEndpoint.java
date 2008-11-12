package no.helsebiblioteket.admin.webservice.endpoint;

import java.text.SimpleDateFormat;
import java.util.Date;

import no.helsebiblioteket.admin.dao.UserDao;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;

public class UserEndpoint extends AbstractJDomPayloadEndpoint {
	private XPath startDateExpression;
    private XPath endDateExpression;
    private XPath nameExpression;
    private final UserDao userDao;
    public UserEndpoint(UserDao userDao) throws JDOMException {
    	this.userDao = userDao;
    	Namespace namespace = Namespace.getNamespace("hr", "http://mycompany.com/hr/schemas");
    	startDateExpression = XPath.newInstance("//hr:StartDate");
    	startDateExpression.addNamespace(namespace);
    	endDateExpression = XPath.newInstance("//hr:EndDate");
    	endDateExpression.addNamespace(namespace);
    	nameExpression = XPath.newInstance("concat(//hr:FirstName,' ',//hr:LastName)");
    	nameExpression.addNamespace(namespace);
    }
    protected Element invokeInternal(Element userRequest) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date startDate = dateFormat.parse(startDateExpression.valueOf(userRequest));
    	Date endDate = dateFormat.parse(endDateExpression.valueOf(userRequest));
    	String name = nameExpression.valueOf(userRequest);
//    	this.userDao.findUserByUsername(startDate, endDate, name);
    	return null;
    }
}

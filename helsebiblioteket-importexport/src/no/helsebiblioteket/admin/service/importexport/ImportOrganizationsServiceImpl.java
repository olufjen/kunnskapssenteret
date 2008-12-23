package no.helsebiblioteket.admin.service.importexport;

/*
 * TODO:
 * implement class as service, not with mainclass
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationNameCategory;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ImportOrganizationsServiceImpl {
	private static final String NODE_GROUP = "group";
    private static final String NODE_DESC = "desc";
    private static final String NODE_FROM = "from";
    private static final String NODE_TO = "to";
    private static final String NODE_CONTACT = "contact";
    private static final String NODE_EMAIL = "email";
    private static final String NODE_POSTAL_ADDRESS = "address";
    private static final String NODE_POSTAL_CODE = "postal_code";
    private static final String NODE_POSTAL_LOCATION = "postal_location";
    private static final String NODE_PHONE = "phone";
    
    private static final String XPATH_EXPR = "/iprange/range";
    private static final String XML_DOC = "IpRangeDaoXml.xml";

    private Map<String, Organization> organizationMap;
    private String xmlDoc;

    private static final Logger logger = Logger.getLogger(ImportOrganizationsServiceImpl.class.getName());

    public ImportOrganizationsServiceImpl() {
        xmlDoc = XML_DOC;
        organizationMap = new HashMap<String, Organization>();
    }

    public ImportOrganizationsServiceImpl(String xml) {
        xmlDoc = xml;
        organizationMap = new HashMap<String, Organization>();
    }

    public Map<String, Organization> getIpRanges() {

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList rangeNodes = null;

        try {
            // This one is cached in jboss, can't make reload work
            //InputStream is = this.getClass().getResourceAsStream(xmlDoc);
            // This one does not cache - yhiiihaaa!!
            URL r = this.getClass().getResource(xmlDoc);
            rangeNodes = (NodeList)xpath.evaluate(XPATH_EXPR, new InputSource(r.openStream()), XPathConstants.NODESET);
            
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Could not load xml config: " + xmlDoc, e);            
        } catch (IOException e) {
        	logger.log(Level.WARNING, "Could not load xml config: " + xmlDoc, e);
        } catch (XPathExpressionException e) {
        	logger.log(Level.WARNING, "Could not load xml config: " + xmlDoc, e);
        }

        organizationMap.clear();

        if (rangeNodes == null) {
        	logger.log(Level.WARNING, "No ranges configured");
        }

        organizationMap = populateIpRangeList(rangeNodes);

        logger.log(Level.FINER, "Ip range list loaded");

        return organizationMap;
    }

    private Map<String, Organization> populateIpRangeList(NodeList nodeList) {
        Map<String, Organization> organizationMap = new HashMap<String, Organization>();
        Organization organization = null;
        
        OrganizationType orgTypeHPR = new OrganizationType();
        orgTypeHPR.setKey("health_enterprise");
        
        OrganizationType orgTypeStud = new OrganizationType();
        orgTypeStud.setKey("teaching");
        
        OrganizationType orgTypeEmp = new OrganizationType();
        orgTypeEmp.setKey("others");
        
        
        for(int i = 0 ; i < nodeList.getLength(); i++){
            Node n = nodeList.item(i);
            String orgNameNorwegian = findNodeByName(n, NODE_DESC).getTextContent();
            if (organizationMap.get(orgNameNorwegian) != null) {
            	organization = organizationMap.get(orgNameNorwegian);
            } else {
            	logger.log(Level.FINE, "\nAdding organization: " + orgNameNorwegian);
            	organization = new Organization();
            }            
            Node found = null;
            found = findNodeByName(n, NODE_GROUP);
            if (found != null) {
            	if ("1".equals(found.getTextContent())) {
            		organization.setType(orgTypeEmp);
            	} else if ("2".equals(found.getTextContent())) {
            		organization.setType(orgTypeStud);
            	} else if ("3".equals(found.getTextContent())) {
            		organization.setType(orgTypeHPR);
            	}
            }
            found = findNodeByName(n, NODE_DESC);
            if (found != null) {
            	if (organization.getNameList() == null) {
            		OrganizationName orgName = new OrganizationName();
                    orgName.setCategory(OrganizationNameCategory.NORMAL);
                    orgName.setLocale(new Locale("no"));
            		orgName.setName(found.getTextContent());
            		List<OrganizationName> orgNameList = new ArrayList<OrganizationName>();
            		orgNameList.add(orgName);
            		organization.setNameList(orgNameList);
            	}
            } 
            found = findNodeByName(n, NODE_FROM);
            if (found != null) {
            	String addressFrom = null, addressTo = null;
                try {
                    addressFrom = InetAddress.getByName(found.getTextContent()).toString();
                    addressTo = InetAddress.getByName(findNodeByName(n, NODE_FROM).getTextContent()).toString();
                    IpRange range = new IpRange(addressFrom, addressTo);
                    if (organization.getIpRangeList() == null) {
                    	organization.setIpRangeList(new ArrayList<IpRange>());
                    	if (!organization.getIpRangeList().contains(range)) {
                    		organization.getIpRangeList().add(range);
                    		logger.log(Level.FINE, "Adding range: " + addressFrom + " - " + addressTo);
                    	}
                    }
                } catch (UnknownHostException e) {
                	logger.log(Level.WARNING, "Could not resolve ip-range from either: " + addressFrom + " or " + addressTo);
                }
            }
            found = findNodeByName(n, NODE_CONTACT);
            if (found != null) {
            	Person contactPerson = new Person();
            	contactPerson.setFirstName(found.getTextContent());
            	organization.setContactPerson(contactPerson);
            }
            ContactInformation contactInformation = new ContactInformation();
            if ((found = findNodeByName(n, NODE_EMAIL)) != null) {
            	contactInformation.setEmail(found.getTextContent());
            }
            if ((found = findNodeByName(n, NODE_PHONE)) != null) {
            	contactInformation.setTelephoneNumber(found.getTextContent());
            }
            if ((found = findNodeByName(n, NODE_POSTAL_CODE)) != null) {
            	contactInformation.setPostalCode(found.getTextContent());
            }
            if ((found = findNodeByName(n, NODE_POSTAL_LOCATION)) != null) {
            	contactInformation.setPostalLocation(found.getTextContent());
            }
            if ((found = findNodeByName(n, NODE_POSTAL_ADDRESS)) != null) {
            	contactInformation.setPostalAddress(found.getTextContent());
            }
        	organization.setContactInformation(contactInformation);
        	organizationMap.put(orgNameNorwegian, organization);
        }
        return organizationMap;
    }
    
    private Node findNodeByName(Node parent, String lookupString) {
    	Node foundNode = null;
    	NodeList nodeList = parent.getChildNodes();
    	for (int j = 0; j < parent.getChildNodes().getLength(); j++) {
    		if (lookupString.equals((foundNode = nodeList.item(j)).getNodeName())) {
    			break;
    		}
    	}
    	return foundNode;
    }
}
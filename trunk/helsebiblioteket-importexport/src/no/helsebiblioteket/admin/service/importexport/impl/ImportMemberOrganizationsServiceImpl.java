package no.helsebiblioteket.admin.service.importexport.impl;

/*
 * Import service for member organizations
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.factory.ContactInformationFactory;
import no.helsebiblioteket.admin.factory.ProfileFactory;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.service.importexport.ImportMemberOrganizationsService;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ImportMemberOrganizationsServiceImpl implements ImportMemberOrganizationsService {
	private static final long serialVersionUID = 165074489687094057L;
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

    private Map<String, MemberOrganization> organizationMap;
    private String xmlDoc;

    private OrganizationService organizationService;
    private UserService userService;
    
    private static final Logger logger = Logger.getLogger(ImportMemberOrganizationsServiceImpl.class.getName());

    public ImportMemberOrganizationsServiceImpl() {
        xmlDoc = XML_DOC;
        organizationMap = new HashMap<String, MemberOrganization>();
    }

    public ImportMemberOrganizationsServiceImpl(String xml) {
        xmlDoc = xml;
        organizationMap = new HashMap<String, MemberOrganization>();
    }

    public void setOrganizationService(OrganizationService organizationService) {
    	this.organizationService = organizationService;
    }
    
    public void importAllMemberOrganizations() {
    	Collection<MemberOrganization> memberOrganizationList = getAllMemberOrganizations().values();
    	
    	OrganizationType health_enterprise = ((ValueResultOrganizationType)
    			this.organizationService.getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise)).getValue();
    	Position none = ((ValueResultPosition)this.userService.getPositionByKey(PositionTypeKey.none, health_enterprise)).getValue();
    	
    	for (MemberOrganization organization : memberOrganizationList) {
    		// TODO: Are these values ok?
    		organization.getOrganization().setDescription("");
    		organization.getOrganization().setNameEnglish("");
    		organization.getOrganization().setNameShortEnglish("");
    		organization.getOrganization().setNameShortNorwegian("");

    		if(organization.getOrganization().getContactInformation() == null){
    			organization.getOrganization().setContactInformation(new ContactInformation());
    		}
    		
    		if(organization.getOrganization().getContactPerson() == null){
    			organization.getOrganization().setContactPerson(new Person());
    		}
    		organization.getOrganization().getContactPerson().setPosition(none);
    		ContactInformation contactInformation = ContactInformationFactory.factory.completeContactInformation();
    		organization.getOrganization().getContactPerson().setContactInformation(contactInformation);
    		Profile profile = ProfileFactory.factory.completeProfile();
    		organization.getOrganization().getContactPerson().setProfile(profile);
    		
    		organizationService.insertMemberOrganization(organization);
    	}
    }
    
    private Map<String, MemberOrganization> getAllMemberOrganizations() {

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList rangeNodes = null;

        try {
            // This one is cached in jboss, can't make reload work
            InputStream is = this.getClass().getResourceAsStream(xmlDoc);
            InputSource source = new InputSource(is);

            // TODO: Use this instead!
            // This one does not cache - yhiiihaaa!!
//            URL r = this.getClass().getResource(xmlDoc);
//            InputSource source = new InputSource(r.openStream());
            
            
            rangeNodes = (NodeList)xpath.evaluate(XPATH_EXPR, source, XPathConstants.NODESET);
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Could not load xml config: " + xmlDoc, e);            
            // TODO: And then you need this.
//        } catch (IOException e) {
//        	logger.log(Level.WARNING, "Could not load xml config: " + xmlDoc, e);
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

    private Map<String, MemberOrganization> populateIpRangeList(NodeList nodeList) {
        Map<String, MemberOrganization> organizationMap = new HashMap<String, MemberOrganization>();
        MemberOrganization organization = null;
        
        SingleResultOrganizationType orgTypeHPRRes = organizationService.getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise);
        OrganizationType orgTypeHPR = (orgTypeHPRRes instanceof ValueResultOrganizationType) ?
        		((ValueResultOrganizationType)orgTypeHPRRes).getValue() :
        			null;
        SingleResultOrganizationType orgTypeStudRes = organizationService.getOrganizationTypeByKey(OrganizationTypeKey.teaching);
        OrganizationType orgTypeStud = (orgTypeStudRes instanceof ValueResultOrganizationType) ?
        		((ValueResultOrganizationType)orgTypeStudRes).getValue() :
        			null;
        SingleResultOrganizationType orgTypeEmpRes = organizationService.getOrganizationTypeByKey(OrganizationTypeKey.other);
        OrganizationType orgTypeEmp = (orgTypeEmpRes instanceof ValueResultOrganizationType) ?
        		((ValueResultOrganizationType)orgTypeEmpRes).getValue() :
        			null;
        
        boolean hasContactInformationValue = false;
        boolean hasContactPersonValue = false;
        String nodeValue = null;
        
        for(int i = 0 ; i < nodeList.getLength(); i++){
            Node n = nodeList.item(i);
            String orgNameNorwegian = findNodeByName(n, NODE_DESC).getTextContent();
            if (organizationMap.get(orgNameNorwegian) != null) {
            	organization = organizationMap.get(orgNameNorwegian);
            } else {
            	logger.log(Level.FINE, "\nAdding organization: " + orgNameNorwegian);
            	organization = new MemberOrganization();
            }            
            Node found = null;
            found = findNodeByName(n, NODE_GROUP);
            if (found != null) {
            	if ("1".equals(found.getTextContent())) {
            		organization.getOrganization().setType(orgTypeEmp);
            	} else if ("2".equals(found.getTextContent())) {
            		organization.getOrganization().setType(orgTypeStud);
            	} else if ("3".equals(found.getTextContent())) {
            		organization.getOrganization().setType(orgTypeHPR);
            	}
            }
            found = findNodeByName(n, NODE_DESC);
            if (found != null && found.getTextContent().trim().length() > 0) {
           		organization.getOrganization().setNameNorwegian(found.getTextContent().trim());
//            	if (organization.getNameList() == null) {
//            		OrganizationName orgName = new OrganizationName();
//                    orgName.setCategory(OrganizationNameCategory.NORMAL);
//                    orgName.setLanguageCode("no");
//            		orgName.setName(found.getTextContent());
//            		List<OrganizationName> orgNameList = new ArrayList<OrganizationName>();
//            		orgNameList.add(orgName);
//            		organization.setNameList(orgNameList);
//            	}
            } 
            found = findNodeByName(n, NODE_FROM);
            if (found != null && found.getTextContent().trim().length() > 0) {
            	String addressFrom = null, addressTo = null;
                addressFrom = found.getTextContent().toString();
                addressTo = findNodeByName(n, NODE_TO).getTextContent().toString().trim();
                if (addressTo.length() > 0) {
                	addressTo = addressTo.replace("/", "");
                } else {
                	addressTo = null;
                }
                addressFrom.replace("/", "");

                if(addressTo != null){
                    IpAddressRange range = new IpAddressRange(
                    		new IpAddress(addressFrom),
                    		new IpAddress(addressTo));
//                    range.setIpAddressSet(new IpAddressSet());
                    insertRange(organization, range);
                } else {
            		organization.getIpAddressSingleList();
            		IpAddressSingle single = new IpAddressSingle(
            				new IpAddress(addressFrom));
//            		single.setIpAddressSet(new IpAddressSet());
            		insertSingle(organization, single);
                }
            }
            
            
            found = findNodeByName(n, NODE_CONTACT);
            if (found != null && found.getTextContent().trim().length() > 0) {
            	Person contactPerson = new Person();
            	contactPerson.setFirstName(found.getTextContent());
            	organization.getOrganization().setContactPerson(contactPerson);
            }
            ContactInformation contactInformation = new ContactInformation();
            if ((found = findNodeByName(n, NODE_EMAIL)) != null) {
            	if (found.getTextContent().trim().length() > 0) {
            		hasContactInformationValue = true;
            		contactInformation.setEmail(found.getTextContent());
            	}
            }
            if ((found = findNodeByName(n, NODE_PHONE)) != null) {
            	if (found.getTextContent().trim().length() > 0) {
            		hasContactInformationValue = true;
            		contactInformation.setTelephoneNumber(found.getTextContent());
            	}
            }
            if ((found = findNodeByName(n, NODE_POSTAL_CODE)) != null) {
            	if (found.getTextContent().trim().length() > 0) {
            		hasContactInformationValue = true;
            		contactInformation.setPostalCode(found.getTextContent());
            	}
            }
            if ((found = findNodeByName(n, NODE_POSTAL_LOCATION)) != null) {
            	if (found.getTextContent().trim().length() > 0) {
            		hasContactInformationValue = true;
            		contactInformation.setPostalLocation(found.getTextContent());
            	}
            }
            if ((found = findNodeByName(n, NODE_POSTAL_ADDRESS)) != null) {
            	if (found.getTextContent().trim().length() > 0) {
            		hasContactInformationValue = true;
            		contactInformation.setPostalAddress(found.getTextContent());
            	}
            }
            if (hasContactInformationValue) {
            	organization.getOrganization().setContactInformation(contactInformation);
            }
        	organizationMap.put(orgNameNorwegian, organization);
        	
        	hasContactInformationValue = false;
            hasContactPersonValue = false;
        }
        return organizationMap;
    }
    
    private void insertSingle(MemberOrganization organization,  IpAddressSingle single) {
    	if(organization.getIpAddressSingleList() == null){
    		organization.setIpAddressSingleList(new IpAddressSingle[0]);
    	}
    	IpAddressSingle[] list = new IpAddressSingle[organization.getIpAddressSingleList().length
    	                                             + 1];
    	int i=0;
    	for (IpAddressSingle ipAddressSingle : organization.getIpAddressSingleList()) {
 			list[i++] = ipAddressSingle;
		}
    	list[list.length-1] = single;
    	organization.setIpAddressSingleList(list);
		logger.log(Level.ALL, "Adding single IP: " + single.getIpAddressSingle().getAddress());
	}

	private void insertRange(MemberOrganization organization, IpAddressRange range) {
        if (organization.getIpAddressRangeList() == null) {
        	organization.setIpAddressRangeList(new IpAddressRange[0]);
        }
        IpAddressRange[] list = new IpAddressRange[organization.getIpAddressRangeList().length
                                                   + 1];
        int i=0;
        for (IpAddressRange ipAddressRange : organization.getIpAddressRangeList()) {
			list[i++] = ipAddressRange;
		}
        list[list.length-1] = range;
        organization.setIpAddressRangeList(list);
		logger.log(Level.ALL, "Adding range: " + range.getIpAddressFrom().getAddress() +
				" - " + range.getIpAddressTo().getAddress());
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

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
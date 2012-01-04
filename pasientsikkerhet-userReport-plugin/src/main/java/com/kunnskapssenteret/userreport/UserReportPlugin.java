package com.kunnskapssenteret.userreport;


import java.util.List;
import java.util.logging.Logger;

import org.jdom.Document;
import org.jdom.Element;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.GetUserParams;
import com.enonic.cms.api.client.model.GetUsersParams;
/*
 * 
 *  Filnavn: UserReportPlugin.java              
 *	Formål: henter ut alle brukere fra en navngitt brukerbase                                                                            
 * 	Datakilde: 
 * 			<datasources>
 *				<datasource>
 *					<methodname>userreport.getAllUsers</methodname>
 *						<parameters>
 *							<parameter>Nyhetsbrev</parameter>
 *						</parameters>
 *				</datasource>
 *			</datasources>                                                       
 *
 *	Opprettet: Reidun og Qadeer 14.10.2011                
 * 
 * */

public class UserReportPlugin {

private Client client = null;
//private static Logger LOG = Logger.getLogger(UserReportPlugin.class.getName());//navnet skrives ut før loggen

	@SuppressWarnings("null")
	public Document getAllUsers(String groupname) throws Exception{
		final Client client = ClientFactory.getLocalClient();
		final GetUsersParams users = new GetUsersParams();
		users.userStore=groupname;
		Document userList = client.getUsers(users);  //inneholder hele brukerbasen             

        Element newUserListRoot = new Element("users");
        Document newUserList = new Document(newUserListRoot);
        
        final List<?> elements = userList.getRootElement().getChildren("user"); //liste med <user> elementer fra brukerbasen
        for(Object o : elements) {
        	final Element e = (Element) o;
        	final String userKey = "#" + e.getAttributeValue("key");
        	final GetUserParams userParam = new GetUserParams();
	        userParam.user = userKey;
	        userParam.includeCustomUserFields = true; // legger til spesiallagde felter
	 
	        Document userDoc = client.getUser(userParam);
        	newUserList.getRootElement().addContent(userDoc.detachRootElement());
        }
        
        
		//LOG.info("Finnished");
		return newUserList;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
}

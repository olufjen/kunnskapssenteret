package no.naks.nhn.service;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ArrayList;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;

import javax.xml.namespace.QName;


//import org.apache.axis2.AxisFault;


import no.naks.services.nhn.client.adr.CommunicationPartyStub.CommunicationParty;

import no.naks.services.nhn.client.ArrayOfDepartment;
import no.naks.services.nhn.client.AxisClientImpl;
import no.naks.services.nhn.client.AxisClientProxy;
import no.naks.services.nhn.client.AxisClientProxyImpl;

import no.naks.services.nhn.client.CommunicationParty_Service;

import no.naks.services.nhn.client.ICommunicationPartyService;

import no.naks.services.nhn.client.Organization;

import no.naks.services.nhn.client.AxisClient;

import no.naks.services.nhn.client.ObjectFactory;


/**
 * NHNServiceClient
 * Denne klassen benytter web services fra Norsk Helsenett for hente inn alle organisasjoner som finnes i NHN sitt adresseregister
 * 
 * @author olj
 *
 */
public class NHNServiceClientImpl implements NHNServiceClient {

	
	protected String authenticatorUser;
	protected String authenticatorPassword;
	protected boolean production;
	protected  CommunicationParty_Service service = null;
	protected  ICommunicationPartyService nhnclient = null;
	protected boolean nhnFlag = false; //Settes fra tableWebswervice, normalt true 
	protected int maxAdr = 1000;
	protected ObjectFactory factory = null;
	protected QName serviceName;
	protected URL wsdl_location;
	protected URL baseUrl;
	protected String prodUrl = "https://ws.nhn.no/Basic/v1/AR";
	protected JAXBElement<String> organisationName = null;
	protected JAXBElement<ArrayOfDepartment> valgteAvdelinger = null;
	protected List<CommunicationParty> allOrganisations = null;
	protected List<CommunicationParty> allParties = null;
	private static Class<?> stringClazz = new String().getClass(); 
	
	private AxisClient nhnAxisClient = null;
//	private AxisFault ax1;
	public NHNServiceClientImpl() {
		super();
	//	serviceName = new QName("http://register.nhn.no/CommunicationParty", "CommunicationParty");
	//	baseUrl = no.naks.services.nhn.client.CommunicationParty_Service.class.getResource(".");
		System.out.println("NHNServiceclient ver 1.2 created");
	//	nhnFlag = true;
	
		// TODO Auto-generated constructor stub
	}



	public int getMaxAdr() {
		return maxAdr;
	}

	public String getProdUrl() {
		return prodUrl;
	}



	public void setProdUrl(String prodUrl) {
		this.prodUrl = prodUrl;
	}



	public void setMaxAdr(int maxAdr) {
		this.maxAdr = maxAdr;
	}

	public List<CommunicationParty> getAllOrganisations() {
		return allOrganisations;
	}

	public void setAllOrganisations(List<CommunicationParty> allOrganisations) {
		this.allOrganisations = allOrganisations;
	}

	public boolean isProduction() {
		return production;
	}

	public void setProduction(boolean production) {
		this.production = production;
	}

	public boolean isNhnFlag() {
		return nhnFlag;
	}

	public void setNhnFlag(boolean nhnFlag) {
		this.nhnFlag = nhnFlag;
		if (nhnFlag)
			System.out.println("NHNServiceclient henter fra adresseregister");
		else
			System.out.println("NHNServiceclient henter ikke fra adresseregister");
	}

	public String getAuthenticatorUser() {
		return authenticatorUser;
	}

	public void setAuthenticatorUser(String authenticatorUser) {
		this.authenticatorUser = authenticatorUser;
	}

	public String getAuthenticatorPassword() {
		return authenticatorPassword;
	}

	public void setAuthenticatorPassword(String authenticatorPassword) {
		this.authenticatorPassword = authenticatorPassword;
	}
	

	public JAXBElement<String> getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(JAXBElement<String> organisationName) {
		this.organisationName = organisationName;
	}
	

	public JAXBElement<ArrayOfDepartment> getValgteAvdelinger() {
		return valgteAvdelinger;
	}

	public void setValgteAvdelinger(JAXBElement<ArrayOfDepartment> valgteAvdelinger) {
		this.valgteAvdelinger = valgteAvdelinger;
	}

	public void initializeTables(){
	//	allParties = collectNHN(); Brukes ikke enda !! 
	
		allOrganisations = collectNHNParties();
		System.out.println("NHNServiceclient collected organisations");
	}

	/**
	 * collectNHNParties
	 * Denne rutinene returnerer en liste over organisasjoner fra NHN adresseregister
	 * @return Liste over organisasjoner
	 */
	public List<CommunicationParty> collectNHNParties(){
		
		List<CommunicationParty> organisations = null;
		nhnAxisClient = new AxisClientImpl("");
		String axUrl = prodUrl;
//		nhnAxisClient.connectToNHN(arg0)
		if (production){
			nhnAxisClient.setNHN_TEST(prodUrl);
			System.out.println("NHNServiceclient collecting from production "+axUrl);
		}
		if (!production){
			axUrl = nhnAxisClient.getNHN_TEST();
			System.out.println("NHNServiceclient collecting from test "+ axUrl);
		}
		/* 
		 * Dette kallet kobler seg til nhn og henter organisasjoner
		 */
		if (nhnFlag)
			organisations = nhnAxisClient.connectToNHN(axUrl);

		return organisations;

	}
}

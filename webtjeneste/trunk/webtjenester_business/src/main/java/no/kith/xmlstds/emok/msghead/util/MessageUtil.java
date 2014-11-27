package no.kith.xmlstds.emok.msghead.util;

import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import no.kith.xmlstds.emok.codes.CS;
import no.kith.xmlstds.emok.codes.CV;
import no.kith.xmlstds.emok.msghead.Address;
import no.kith.xmlstds.emok.msghead.Document;
import no.kith.xmlstds.emok.msghead.HealthcareProfessional;
import no.kith.xmlstds.emok.msghead.Ident;
import no.kith.xmlstds.emok.msghead.MsgHead;
import no.kith.xmlstds.emok.msghead.MsgInfo;
import no.kith.xmlstds.emok.msghead.ObjectFactory;
import no.kith.xmlstds.emok.msghead.Organisation;
import no.kith.xmlstds.emok.msghead.Receiver;
import no.kith.xmlstds.emok.msghead.RefDoc;
import no.kith.xmlstds.emok.msghead.Sender;
import no.kith.xmlstds.emok.msghead.RefDoc.Content;


/**
 * Denne klassen er en hjelpeklasse til å lage Meldingshodeobjekter og Document objekter
 * 
 * @author olj
 *
 */
public class MessageUtil {
	protected ObjectFactory headObjectFactory;
	protected MsgHead meldingsHode;
	protected Document document;
	protected MsgInfo messageInfo;
	protected RefDoc refDoc;
	protected Receiver receiver;
	protected Sender sender;
	protected Organisation receiverOrganisation;
	protected Organisation senderOrganisation;
	protected Organisation senderDepartment;
	protected Ident receiverIdent;
	protected Ident senderIdent;
	protected Ident senderDepIdent;
	protected HealthcareProfessional healthcarePerson;
	protected HealthcareProfessional healthcareLeader;
	protected Address receiverAdress;
	protected Content refDocument;

	public MessageUtil() {
		super();
		GregorianCalendar genDate = new GregorianCalendar();
		XMLGregorianCalendar xmlcal = null;
		try {
			xmlcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(genDate);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		headObjectFactory = new ObjectFactory();
		meldingsHode = (MsgHead)headObjectFactory.createMsgHead();
		document = (Document)headObjectFactory.createDocument();
		messageInfo = (MsgInfo)headObjectFactory.createMsgInfo();
		refDoc = (RefDoc)headObjectFactory.createRefDoc();
		receiverAdress = (Address)headObjectFactory.createAddress();
		receiverAdress.setCity("OSLO");
		receiverAdress.setPostalCode("0130");
		receiverAdress.setPostbox("P O Box 7004");
		receiverAdress.setStreetAdr("Pilestredet Park 7");
		receiverOrganisation = (Organisation)headObjectFactory.createOrganisation();
		receiverOrganisation.setOrganisationName("NASJONALT KUNNSKAPSSENTER FOR HELSETJENESTEN");
		receiverOrganisation.setAddress(receiverAdress);
		
		senderOrganisation = (Organisation)headObjectFactory.createOrganisation();
		senderDepartment = (Organisation)headObjectFactory.createOrganisation();
		
		receiverIdent = (Ident)headObjectFactory.createIdent();
		senderIdent = (Ident)headObjectFactory.createIdent();
		senderDepIdent = (Ident)headObjectFactory.createIdent();
		receiver = (Receiver)headObjectFactory.createReceiver();
	
		sender = (Sender) headObjectFactory.createSender();
		healthcarePerson = (HealthcareProfessional)headObjectFactory.createHealthcareProfessional();
		healthcareLeader = (HealthcareProfessional)headObjectFactory.createHealthcareProfessional();
		refDocument = (Content)headObjectFactory.createRefDocContent();
//		senderOrganisation.setHealthcareProfessional(value)
		CS mType = new CS();							// Type melding
		mType.setV("EMOKBM");
		mType.setDN("BASISMELDING");
		messageInfo.setType(mType);
		messageInfo.setMIGversion("v1.2 2006-05-24");
		messageInfo.setGenDate(xmlcal);
		UUID uuid = UUID.randomUUID();
		String msId = uuid.toString();
		messageInfo.setMsgId(msId);
		CS refMsgType = new CS();	
		// Ref type
		CS refAck = new CS();
		refAck.setV("N");
		refAck.setDN("Nei");
		messageInfo.setAck(refAck);
		
		refMsgType.setV("XML");
		refMsgType.setDN("XML-instans");
		refDoc.setMsgType(refMsgType);
		refDoc.setContent(refDocument);
		
		document.setRefDoc(refDoc);
		receiver.setOrganisation(receiverOrganisation);
		meldingsHode.setMsgInfo(messageInfo);
		meldingsHode.getDocument().add(document);
		
/*
 * OBS Dette er verdier som må hentes fra NHN	
 * Kunnskapssenterets adresseoppslag	
 */
		CV receiverType = new CV();
		receiverType.setS("2.16.578.1.12.4.1.1.9051");
		receiverType.setV("HER");
		receiverType.setDN("HER-id");
		receiverType.setOT("");
		receiverIdent.setTypeId(receiverType);
		receiverIdent.setId("94816");/*prodid=94816 testid=91058*/
		receiverOrganisation.getIdent().add(receiverIdent);
				
		CV senderType = new CV();
		senderType.setS("2.16.578.1.12.4.1.1.9051");
		senderType.setV("HER");
		senderType.setDN("Her-id");
		senderIdent.setTypeId(senderType);
		senderOrganisation.getIdent().add(senderIdent);
		CV senderDepType = new CV();
		senderDepType.setS("2.16.578.1.12.4.1.1.9051");
		senderDepType.setV("HER");
		senderDepType.setDN("Her_id");
		senderDepIdent.setTypeId(senderDepType);
		senderDepartment.getIdent().add(senderDepIdent);
		receiverOrganisation.setOrganisationName("NASJONALT KUNNSKAPSENTER FOR HELSETJENESTEN");
		receiver.setOrganisation(receiverOrganisation);
		
		sender.setOrganisation(senderOrganisation);
		messageInfo.setReceiver(receiver);
		
		
		
	}


	public HealthcareProfessional getHealthcareLeader() {
		return healthcareLeader;
	}


	public void setHealthcareLeader(HealthcareProfessional healthcareLeader) {
		this.healthcareLeader = healthcareLeader;
	}


	public Organisation getSenderDepartment() {
		return senderDepartment;
	}

	public void setSenderDepartment(Organisation senderDepartment) {
		this.senderDepartment = senderDepartment;
	}

	public HealthcareProfessional getHealthcarePerson() {
		return healthcarePerson;
	}

	public void setHealthcarePerson(HealthcareProfessional healthcarePerson) {
		this.healthcarePerson = healthcarePerson;
	}

	public ObjectFactory getHeadObjectFactory() {
		return headObjectFactory;
	}

	public void setHeadObjectFactory(ObjectFactory headObjectFactory) {
		this.headObjectFactory = headObjectFactory;
	}

	public MsgHead getMeldingsHode() {
		return meldingsHode;
	}

	public void setMeldingsHode(MsgHead meldingsHode) {
		this.meldingsHode = meldingsHode;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public MsgInfo getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(MsgInfo messageInfo) {
		this.messageInfo = messageInfo;
	}

	public RefDoc getRefDoc() {
		return refDoc;
	}

	public void setRefDoc(RefDoc refDoc) {
		this.refDoc = refDoc;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Organisation getReceiverOrganisation() {
		return receiverOrganisation;
	}

	public void setReceiverOrganisation(Organisation receiverOrganisation) {
		this.receiverOrganisation = receiverOrganisation;
	}

	public Organisation getSenderOrganisation() {
		return senderOrganisation;
	}

	public void setSenderOrganisation(Organisation organization) {
		this.senderOrganisation = organization;
	}

	public Ident getReceiverIdent() {
		return receiverIdent;
	}

	public void setReceiverIdent(Ident receiverIdent) {
		this.receiverIdent = receiverIdent;
	}
	
	
}

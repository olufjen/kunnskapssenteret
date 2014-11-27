package no.naks.nhn.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamResult;


import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.XmlMappingException;

import no.kith.xmlstds.emok.codes.CS;
import no.kith.xmlstds.emok.codes.URL;
import no.kith.xmlstds.emok.lm.ObjectFactory;
import no.kith.xmlstds.emok.msghead.Ident;
import no.kith.xmlstds.emok.msghead.MsgHead;
import no.kith.xmlstds.emok.msghead.Organisation;
import no.kith.xmlstds.emok.msghead.RefDoc;
import no.kith.xmlstds.emok.msghead.TeleCom;
import no.kith.xmlstds.emok.msghead.RefDoc.Content;
import no.kith.xmlstds.emok.msghead.util.CustomXMLGregorianCalendar;
import no.kith.xmlstds.emok.msghead.util.MessageUtil;
import no.naks.emok.model.IBasismelding;
import no.naks.nhn.model.Person;
import no.naks.services.nhn.client.Department;
import no.naks.services.nhn.client.Organization;

public class MelderServiceImpl implements MelderService {
	
	protected MessageUtil messageUtil;
	protected Marshaller marshaller;
	protected boolean production;
	private String FILE_NAME = "c:\\temp\\basismelding.xml";
	private String catalog;
	private String opSys = "DOS";
	private String sysCommand = "dir";
	private String prodid= "94816";
	private String testId = "91058";
	private String fName = "";
	private int counter = 0;
	private no.kith.xmlstds.emok.lm.Basismelding toxmlMelding = null;
	
	public MelderServiceImpl() {
		super();
	
		
	}
	
	public boolean isProduction() {
		return production;
	}

	public void setProduction(boolean production) {
		this.production = production;
	}

	public String getSysCommand() {
		return sysCommand;
	}

	public void setSysCommand(String sysCommand) {
		this.sysCommand = sysCommand;
	}

	public String getOpSys() {
		return opSys;
	}

	public void setOpSys(String opSys) {
		this.opSys = opSys;
	}

	public String getFILE_NAME() {
		return FILE_NAME;
	}

	public void setFILE_NAME(String fILE_NAME) {
	
		FILE_NAME = catalog+fILE_NAME;
		fName = catalog+fILE_NAME;
//		System.out.println("File "+FILE_NAME);
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	private void writexmlMessage() throws FileNotFoundException{
		
		RefDoc refDoc = messageUtil.getRefDoc();
		counter++;
		if (counter > 100)
			counter = 1;
		String cTs = Integer.toString(counter);
		Calendar cal = Calendar.getInstance(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String dateFlag = sdf.format(cal.getTime())+cTs; 
		if (FILE_NAME.endsWith(".xml"))
			FILE_NAME = fName;
		FILE_NAME = FILE_NAME+dateFlag+".xml";
		MsgHead refHead = messageUtil.getMeldingsHode();
		System.out.println("Melderservice: skriver xml");
		
/*
 * Execute linux		
 */
		String[] command =  new String[4];command[0] = "cmd";command[1] = "/C";command[2] = sysCommand ;command[3] = "c:\\";
		
		Process p = null;
		String s = null;
		if (opSys.equals("DOS")){
			System.out.println("Melderservice: utfører DOS command "+sysCommand);
			try {
				p = Runtime.getRuntime().exec(command);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Melderservice:cannot perform dos command "+ e1.getMessage());
			}
		}
		if(opSys.equals("LINUX")){
			System.out.println("Melderservice: utfører linux command "+sysCommand);
			try {
				p = Runtime.getRuntime().exec(sysCommand);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Melderservice:cannot perform linux command "+ e1.getMessage());
			}
		}
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream())); try {
			while ((s = stdInput.readLine()) != null)
			{         System.out.println(s); }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
/*
 * End execute linux		
 */
		
	    FileOutputStream os = null;
	    try {
			os = new FileOutputStream(FILE_NAME);
	    	} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Melderservice:cannot create output file "+ e.getMessage());
						return;
			}
	
/*	      
	        try {
	            try {
					os = new FileOutputStream(FILE_NAME);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Melderservice:cannot create output file "+ e.getMessage());
					return;
				}
	            try {
					this.marshaller.marshal(refHead, new StreamResult(os));
				} catch (XmlMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Melderservice:cannot marshal "+ e.getMessage());
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Melderservice:cannot write to output file "+ e.getMessage());
					return;
				}
	        } finally {
	            if (os != null) {
	                try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Melderservice:cannot close file "+ e.getMessage());
						return;
					}
	            }
	        }
*/
		
		JAXBContext jaxbContext = null;
		javax.xml.bind.Marshaller basisMarshaller = null;
		
	
		try {
			//jaxbContext = JAXBContext.newInstance("no.kith.xmlstds.emok.lm.Basismelding:no.kith.xmlstds.emok.msghead");
			jaxbContext = JAXBContext.newInstance(refHead.getClass(),toxmlMelding.getClass());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Melderservice:cannot create JAXBContext "+ e.getMessage()+e.getErrorCode());
			return;
		}
		try {
			basisMarshaller = (javax.xml.bind.Marshaller) jaxbContext.createMarshaller();
			basisMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			basisMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
		} catch (JAXBException e) {
			System.out.println("Melderservice:cannot create basismarshaller "+ e.getMessage()+e.getErrorCode());
			e.printStackTrace();
		}
	
			try {
				basisMarshaller.marshal(refHead,os);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
	}
	

	public Marshaller getMarshaller() {
		return marshaller;
	}
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
	/* (non-Javadoc)
	 * @see no.naks.nhn.service.MelderService#sendMelding(no.naks.emok.model.Basismelding, no.naks.nhn.model.Person)
	 * sendMelding
	 * Denne rutinen sørger for at meldingen fra web skjema blir lagret som xml format
	 */
	@Override
	public void sendMelding(IBasismelding melding, Person melder,Person leder) {
		messageUtil = new MessageUtil();
		ObjectFactory messageFactory = new ObjectFactory();
	//	System.out.println("MelderService ny versjon");
		boolean hasLeader = false;
		toxmlMelding = (no.kith.xmlstds.emok.lm.Basismelding)messageFactory.createBasismelding();
		toxmlMelding.setForslagTiltak(melding.getForslagtiltak());
		toxmlMelding.setAlvorlighetsgrad(melding.getAlvorlighetsgrad());
		toxmlMelding.setArsaksbeskrivelse(melding.getArsaksbeskrivelse());
		toxmlMelding.setOnskerHjelp(melding.isOnskerhjelp());
		TimeZone tz = TimeZone.getTimeZone("europe/oslo");

	//	GregorianCalendar c = new GregorianCalendar(tz);
		GregorianCalendar c = new GregorianCalendar();
		if (melding.getTidforhendelsen() != null)
			c.setTime(melding.getTidforhendelsen());
		else
			System.out.println("MelderService setter dagens dato - dato for hendelse ikke oppgitt");
		
		messageUtil.getHealthcarePerson().setFamilyName(melder.getEPost());
		messageUtil.getHealthcarePerson().setGivenName(melder.getNavn());
		List<TeleCom> meldertelefoner = messageUtil.getHealthcarePerson().getTeleCom();
		URL meldertelefon = new URL();
		CS melderteleType = new CS();
		melderteleType.setV("H");
		melderteleType.setDN("H");
		meldertelefon.setV(melder.getTelefonNummer());
		TeleCom melderTelecom = new TeleCom();
		melderTelecom.setTeleAddress(meldertelefon);
		melderTelecom.setTypeTelecom(melderteleType);
		meldertelefoner.add(melderTelecom);
		
		  
		if (leder.getEPost()!= null && !leder.getEPost().equals("")){
			messageUtil.getHealthcareLeader().setFamilyName(leder.getEPost());
			messageUtil.getHealthcareLeader().setGivenName(leder.getNavn());
			List<TeleCom> telefoner = messageUtil.getHealthcareLeader().getTeleCom();
			URL telefon = new URL();
			CS teleType = new CS();
			teleType.setV("H");
			teleType.setDN("H");
			telefon.setV(leder.getTelefonNummer());
			TeleCom lederTelecom = new TeleCom();
			lederTelecom.setTeleAddress(telefon);
			lederTelecom.setTypeTelecom(teleType);
			telefoner.add(lederTelecom);
			messageUtil.getSenderOrganisation().setHealthcareProfessional(messageUtil.getHealthcareLeader());
			hasLeader = true;
		}
		if (leder.getEPost()== null || leder.getEPost().equals("")){
			messageUtil.getSenderOrganisation().setHealthcareProfessional(messageUtil.getHealthcarePerson());
		}
		
		
		
	//	c.get(Calendar.HOUR_OF_DAY); 
		XMLGregorianCalendar xmlcal = null;
	   
		try {
		   xmlcal =   DatatypeFactory.newInstance().newXMLGregorianCalendarTime(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND),0); 
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:MM");
		CustomXMLGregorianCalendar customCal = new CustomXMLGregorianCalendar(xmlcal);
		System.out.println("MelderServiceImpl tid for hendelsen  " + sdf.format(c.getTime())+ " xml "+ xmlcal.toString()+ " " + xmlcal.toXMLFormat() + " Custom " + customCal.toXMLFormat());
	//	toxmlMelding.setDatoForHendelsen(xmlcal);
	//	XMLGregorianCalendar xmlCcal = customCal;
		toxmlMelding.setKlokkeslettHendelse(customCal);
//		toxmlMelding.setKlokkeslettHendelse(c.getTime());
		// To remove time from date:
		// user gregorian calendar and set hourofday etc to 0 **** Fungerer ikke !!!!
		Date tidFor = melding.getTidforhendelsen();
		if (tidFor == null){
			tidFor = c.getTime();
		}
		Date truncDate = DateUtils.truncate(tidFor,Calendar.DATE);
		
		Calendar tFor = GregorianCalendar.getInstance();
		tFor.setTime(tidFor);
		tFor.set(Calendar.HOUR_OF_DAY, 0); tFor.set(Calendar.MINUTE, 0); tFor.set(Calendar.SECOND, 0); tFor.set(Calendar.MILLISECOND, 0); 
		Date tidFor3 = tFor.getTime();
		System.out.println("MelderServiceImpl dato for hendelsen  opprinnelig " + tidFor.toString()+ " tidfor3 " + tidFor3.toString()+ " trunc "+truncDate.toString());
		toxmlMelding.setDatoForHendelsen(truncDate);
		toxmlMelding.setDognkodeHendelse(melding.getDognkode());
		
		DateTime dt = new DateTime();
		
	
		GregorianCalendar fAr = new GregorianCalendar();
		int nFar = melding.getArfodt();
		if (nFar == 0)
			nFar = 1800; 
		fAr.set(nFar,0, 1);
		DateTime fYear = dt.year().setCopy(nFar);
		
		XMLGregorianCalendar xmlFar = null;
		try {
			xmlFar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xmlFar.setYear(nFar);
		Date yearB = fYear.toDate();
		String messageSid = melding.getEntydigidk();
		messageUtil.getRefDoc().setId(messageSid);
		toxmlMelding.setFodselsar(xmlFar); // Fødselsår som xmlGregorian
//		toxmlMelding.setFodselsar(fAr.getTime()); // Fødselsår som Date
//		toxmlMelding.setFodselsar(yearB); //Fødselsår som Date fra DateTime
		toxmlMelding.setHelsetilsynetVarslet(false);
		toxmlMelding.setHendelsesbeskrivelse(melding.getHendelsesbeskrivelse());
		toxmlMelding.setHvordanOppdaget(melding.getHvordanoppdaget());
		toxmlMelding.setKjonn(melding.getKjonn());
		toxmlMelding.setKanLareAv(melding.isKanlareav());
//		toxmlMelding.setKlokkeslettHendelse(value)
		toxmlMelding.setKonsekvenser(melding.getKonsekvenser());
		toxmlMelding.setRolle(melding.getRolle());
		toxmlMelding.setStedforhendelsen(melding.getStedforhendelsen());
		toxmlMelding.setTypeUhell(melding.getTypeuhell());
		toxmlMelding.setUtforteStrakstiltak(melding.getUtfortestrakstiltak());
		Organization senderOrg = melding.getNhnadresse();
		if (senderOrg != null && senderOrg.getDisplayName() != null){
			messageUtil.getSenderOrganisation().setOrganisationName(senderOrg.getDisplayName().getValue());
		//	System.out.println("Melderservice: dep: "+ senderOrg.getDepartments());
			if (senderOrg.getDepartments()!= null){
				List departments = senderOrg.getDepartments().getValue().getDepartment();
				if (departments != null && departments.size() > 0){
					Department department = (Department)departments.get(0);
					Organisation senderDepartment = messageUtil.getSenderDepartment();
					String depName = department.getDisplayName().getValue();
					if (depName == null || depName.equals(""))
						depName = "ukjent";
					senderDepartment.setOrganisationName(depName);
					Ident senderDepIdent = senderDepartment.getIdent().get(0);
					if (department.getHerId() != null){
						senderDepIdent.getTypeId().setDN(department.getHerId().toString());
						senderDepIdent.setId(department.getHerId().toString());
			
					}
					if (hasLeader)
						senderDepartment.setHealthcareProfessional(messageUtil.getHealthcarePerson());
					messageUtil.getSenderOrganisation().setOrganisation(senderDepartment);
				}
			}
		
		}
		
		Ident senderIdent = (Ident)messageUtil.getSenderOrganisation().getIdent().get(0);
		if (senderOrg != null && senderOrg.getHerId() != null){
			senderIdent.getTypeId().setDN(senderOrg.getHerId().toString());
			senderIdent.setId(senderOrg.getHerId().toString());
		//	System.out.println("Melderservice: HERID "+senderOrg.getHerId()+" "+melding.getMelder_enhet() + " SATT "+senderIdent.getId());
		}
		
	//	messageUtil.setSenderOrganisation(melding.getNhnadresse());
		if (!production){
			messageUtil.getReceiverIdent().setId(testId);
			
		}
		System.out.println("Melderservice: Receiver id "+messageUtil.getReceiverIdent().getId());
		messageUtil.getRefDoc().getContent().getAny().add(toxmlMelding);
		messageUtil.getSender().setOrganisation(messageUtil.getSenderOrganisation());
		messageUtil.getMessageInfo().setSender(messageUtil.getSender());
		try {
			writexmlMessage();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageUtil = null;
		toxmlMelding = null;
/*
 * Lager identisk melding dersom også leder er oppgitt		
 */


	}

	@Override
	public void lagreMelding(IBasismelding melding, Person melder) {
		
		
	}



}

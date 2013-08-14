package no.naks.web.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBElement;

import org.apache.axis2.deployment.resolver.AARFileBasedURIResolver;
import org.springframework.beans.factory.annotation.Autowired;

import no.naks.emok.model.IBasismelding;
import no.naks.services.nhn.client.ArrayOfDepartment;
import no.naks.services.nhn.client.Department;
import no.naks.services.nhn.client.ObjectFactory;
import no.naks.services.nhn.client.Organization;

public class HvorHvem implements Serializable {
	
	private String caseNr = "";
	private String kjonnKode = "";
	private String kjonn = "";
	private String klokkeSlett = "";
	private String spesialSted = "";
	private String stedforHendelsen = "";
	protected String valgtOrganisasjon = ""; //Teksfelt for valgt organisasjon
	protected String valgtDepartment = "";	 // Tekstfelt for valgt avdeling
	private String dognkode ="dag";
	private String seksjon = "";
	private String arfodt = "";
	private Date tidForhendelsen = null;
	
	private int ihours = 0;
	private int iminutes = 0;
	
	private boolean datoHidden;
	private boolean klokkeHidden;
	private boolean aarHidden;
	private boolean spesialistHidden = true;
	private boolean sykehusHidden;
	
	private boolean ikkeSykehus=false;

	private IBasismelding melding;

	private Department department = null;
	
	
	private transient ObjectFactory factory = null;
	
	private JAXBElement<String> departmentName = null;
	
	private ArrayOfDepartment avdelinger = null;
	
	private JAXBElement<ArrayOfDepartment> valgteAvdelinger;
	private Organization foretakOrganisasjon = null; // Organisasjon skapt når bruker har skrevet navnet eller valgt fra et utvalg. 
	private JAXBElement<String> organisationName = null;
	
	//Error message variables
	
	private boolean blDato = false;
	private boolean blSpesialSted = false;
    private boolean blValgtOrganisasjon= false;
    private boolean blBirthInput = false;
    private boolean blArFodt = false;
    private boolean blArFormat = false;
    private boolean blklokke = false;
//    private boolean blklokkeSlett = false;
    
	protected boolean blKontrollBtn; 
	
	public HvorHvem() {
		super();
		blKontrollBtn = false;
		System.out.println("HvorHvem created");
		
	}
	
	
	
	public boolean isBlKontrollBtn() {
		return blKontrollBtn;
	}

	public void setBlKontrollBtn(boolean blKontrollBtn) {
		this.blKontrollBtn = blKontrollBtn;
	}

	
	public boolean isBlDato() {
		return blDato;
	}

	public void setBlDato(boolean blDato) {
		this.blDato = blDato;
	}

	public boolean isBlSpesialSted() {
		return blSpesialSted;
	}
	public void setBlSpesialSted(boolean blSpesialSted) {
		this.blSpesialSted = blSpesialSted;
	}

	public boolean isBlValgtOrganisasjon() {
		return blValgtOrganisasjon;
	}
	public void setBlValgtOrganisasjon(boolean blValgtOrganisasjon) {
		this.blValgtOrganisasjon = blValgtOrganisasjon;
	}


	public void datoHiddenValue(ValueChangeEvent val){
		String strDatoHidden = (String)val.getNewValue();
		boolean blDatoHidden=true;
		if(strDatoHidden != null && strDatoHidden !=""){
			blDatoHidden = Boolean.parseBoolean(strDatoHidden);
		}
		setDatoHidden(blDatoHidden) ;
	}
	public void klokkenHiddenValue(ValueChangeEvent val){
		String strKlokkenHidden = (String)val.getNewValue();
		boolean blKlokkenHidden=true;
		if(strKlokkenHidden != null && strKlokkenHidden !=""){
			blKlokkenHidden = Boolean.parseBoolean(strKlokkenHidden);
		}
		setKlokkeHidden(blKlokkenHidden) ;

	}
	public void aarHiddenValue(ValueChangeEvent val){
		String strAarHidden = (String)val.getNewValue();
		boolean blAarHidden=true;
		if(strAarHidden != null && strAarHidden !=""){
			blAarHidden = Boolean.parseBoolean(strAarHidden);
		}
		setAarHidden(blAarHidden) ;
	}
	
	public void kjonnValue (AjaxBehaviorEvent val){
		String kjonn = kjonnKode;
		if(kjonn != null){
			int kjonnId = Integer.parseInt(kjonn);
			if(kjonnId ==1 ||kjonnId ==2)
				blBirthInput = true;
			else
				blBirthInput = false;
		}
	}
	
	public void sykehusHiddenValue(ValueChangeEvent val){
		String strsykHidden = (String)val.getNewValue();
		sykehusHidden=true;
		if(strsykHidden != null && strsykHidden !=""){
			sykehusHidden = Boolean.parseBoolean(strsykHidden);
		}

	}
	
	public String getKjonn() {
		this.kjonn = melding.getKjonn().getDN();
		return kjonn;
	}


	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}


	public boolean isBlBirthInput() {
		return blBirthInput;
	}


	public void setBlBirthInput(boolean blBirthInput) {
		this.blBirthInput = blBirthInput;
	}


	public boolean isDatoHidden() {
		return datoHidden;
	}


	public void setDatoHidden(boolean datoHidden) {
		this.datoHidden = datoHidden;
	}


	public boolean isKlokkeHidden() {
		return klokkeHidden;
	}


	public void setKlokkeHidden(boolean klokkeHidden) {
		this.klokkeHidden = klokkeHidden;
	}


	public boolean isAarHidden() {
		return aarHidden;
	}


	public void setAarHidden(boolean aarHidden) {
		this.aarHidden = aarHidden;
	}


	public boolean isSpesialistHidden() {
		return spesialistHidden;
	}


	public void setSpesialistHidden(boolean spesialistHidden) {
		this.spesialistHidden = spesialistHidden;
	}


	public boolean isSykehusHidden() {
		return sykehusHidden;
	}


	public void setSykehusHidden(boolean sykehusHidden) {
		this.sykehusHidden = sykehusHidden;
	}


	public IBasismelding getMelding() {
		return melding;
	}

	public void setMelding(IBasismelding melding) {
		this.melding = melding;
	}

	public boolean isBlArFodt() {
		return blArFodt;
	}
	public void setBlArFodt(boolean blArFodt) {
		this.blArFodt = blArFodt;
	}


	public boolean isBlArFormat() {
		return blArFormat;
	}


	public void setBlArFormat(boolean blArFormat) {
		this.blArFormat = blArFormat;
	}


//	public boolean isBlklokkeSlett() {
//		return blklokkeSlett;
//	}
//
//
//	public void setBlklokkeSlett(boolean blklokkeSlett) {
//		this.blklokkeSlett = blklokkeSlett;
//	}


	public boolean isBlklokke() {
		return blklokke;
	}


	public void setBlklokke(boolean blklokke) {
		this.blklokke = blklokke;
	}


	public String getCaseNr() {
		return caseNr;
	}
	public void setCaseNr(String caseNr) {
		this.caseNr = caseNr;
	}
	public String getKjonnKode() {
		return kjonnKode;
	}
	public void setKjonnKode(String kjonnKode) {
		this.kjonnKode = kjonnKode;
		melding.getKjonn().setV(kjonnKode);
		melding.getKjonn();
	}
	public String getKlokkeSlett() {
		return klokkeSlett;
	}
	public void setKlokkeSlett(String klokkeSlett) {
		this.klokkeSlett = klokkeSlett;
	//	sethoursminutes();
	//	setFullDate();
	}
	public String getSpesialSted() {
		return spesialSted;
	}
	public void setSpesialSted(String spesialSted) {
		this.spesialSted = spesialSted;
		factory = new ObjectFactory();
		if (ikkeSykehus){
			Organization organisation = new Organization();
			organisation.setHerId(0);
	//		organisationName = tableWebService.getOrganisationName();
			if (organisationName == null)
				organisationName = factory.createOrganizationCreateDisplayName("");
			if (organisationName != null)
				organisationName.setValue(this.spesialSted);
			organisation.setDisplayName(organisationName);
			melding.setStedforhendelsen(this.spesialSted);
	
			if (department == null)
				department = new Department();
			if (departmentName == null){
				valgtDepartment = "ukjent";
				departmentName = factory.createDepartmentCreateDisplayName(valgtDepartment);
			}
			departmentName.setValue(valgtDepartment);
			department.setDisplayName(departmentName);
			department.setHerId(new Integer(0));
			if (avdelinger == null)
				avdelinger = factory.createArrayOfDepartment();
			avdelinger.getDepartment().add(department);
			if (valgteAvdelinger == null)
				valgteAvdelinger = factory.createArrayOfDepartment(null);
			valgteAvdelinger.setValue(avdelinger);
			organisation.setDepartments(valgteAvdelinger);

			melding.setMelder_enhet(0);
			melding.setNhnadresse(organisation);
		}
	
	}
	public String getValgtOrganisasjon() {
		return valgtOrganisasjon;
	}
	public void setValgtOrganisasjon(String valgtOrganisasjon) {
		this.valgtOrganisasjon = valgtOrganisasjon;
		factory = new ObjectFactory();
		if (foretakOrganisasjon == null)
			foretakOrganisasjon = new Organization();
		foretakOrganisasjon.setHerId(0);
		organisationName = factory.createOrganizationCreateDisplayName("");
		if (organisationName != null)
			organisationName.setValue(this.valgtOrganisasjon);
		foretakOrganisasjon.setDisplayName(organisationName);
		
		melding.setMelder_enhet(0);
		melding.setNhnadresse(foretakOrganisasjon);
	
	}
	public String getValgtDepartment() {
		return valgtDepartment;
	}
	public void setValgtDepartment(String valgtDepartment) {
		this.valgtDepartment = valgtDepartment;
		factory = new ObjectFactory();
		if (department == null)
			department = new Department();
			
		if (departmentName == null)
			departmentName = factory.createDepartmentCreateDisplayName(valgtDepartment);
		departmentName.setValue(valgtDepartment);
		department.setDisplayName(departmentName);
		department.setHerId(new Integer(0));
		
		if (avdelinger == null)
			avdelinger = factory.createArrayOfDepartment();
		avdelinger.getDepartment().add(department);
	
		if (valgteAvdelinger == null)
			valgteAvdelinger = factory.createArrayOfDepartment(null);
		valgteAvdelinger.setValue(avdelinger);
		foretakOrganisasjon.setDepartments(valgteAvdelinger);

	}
	public String getDognkode() {
		return dognkode;
	}
	public void setDognkode(String dognkode) {
		this.dognkode = dognkode;
		melding.getDognkode().setDN(dognkode);
		dognkodeIndex();
	}
	public String getSeksjon() {
		return seksjon;
	}
	public void setSeksjon(String seksjon) {
		this.seksjon = seksjon;
		this.stedforHendelsen = seksjon;
		if (melding.getStedforhendelsen() == null)
			melding.setStedforhendelsen("");
		melding.setStedforhendelsen(melding.getStedforhendelsen()+" "+this.stedforHendelsen);
		
	}
	public String getArfodt() {
		return arfodt;
	}
	public void setArfodt(String arfodt) {
		this.arfodt = arfodt;
		Integer naar = null;
		if(arfodt != null && arfodt !=""){
			try {
				naar = new Integer(Integer.parseInt(arfodt));
			}catch (NumberFormatException ec){
				naar = new Integer(0);
				
			}
		
			
			melding.setArfodt(naar.intValue());
		}
		
	}
	public Date getTidForhendelsen() {
		return tidForhendelsen;
	}
	public void setTidForhendelsen(Date tidForhendelsen) {
		this.tidForhendelsen = tidForhendelsen;
		melding.setTidforhendelsen(tidForhendelsen);
	}
	
	public boolean isIkkeSykehus() {
		return ikkeSykehus;
	}
	public void setIkkeSykehus(boolean ikkeSykehus) {
		this.ikkeSykehus = ikkeSykehus;
		if(ikkeSykehus){
			valgtOrganisasjon="";
			valgtDepartment="ukjent";
			seksjon="";
		}else{
			spesialSted="";
		}
	}
	
	/**
	 * sethoursminutes
	 * Denne rutinen lager heltallsverdier av klokkeslett for hendelsen
	 * Den kalles fra setKlokkeslett. Dette skjer når bruker oppgir klokkeslett for hendelsen
	 */
	private void sethoursminutes(){
		String hours = "00";
		String minutes = "00";		
		if (!klokkeSlett.equals("")){
			char kolon = ':';
			int inx = klokkeSlett.indexOf(kolon);
			if (inx > 0){
				hours = klokkeSlett.substring(0,inx);
				minutes = klokkeSlett.substring(inx+1);
				ihours = Integer.parseInt(hours);
				iminutes = Integer.parseInt(minutes);
			}
		}
	}
	/**
	 * setFullDate
	 * Denne rutinen setter klokkeslett til tid for hendelsen
	 * Den kalles fra setKlokkeslett. Dette skjer når bruker oppgir klokkeslett for hendelsen
	 */
	private void setFullDate(){
		if (ihours > 0){
			GregorianCalendar localTime = new GregorianCalendar();
			
			localTime.setTime(tidForhendelsen);
			localTime.set(localTime.HOUR,ihours);
			localTime.set(localTime.MINUTE, iminutes);
			tidForhendelsen = localTime.getTime();
			melding.setTidforhendelsen(tidForhendelsen);
			String tiden = DateFormat.getDateInstance().format(tidForhendelsen);
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:MM");

			System.out.println("Hvorhvem tid for hendelsen "+ tiden + " cal " + sdf.format(localTime.getTime()));
		}
	}
	/**
	 * dognkodeIndex
	 * Denne rutinen setter index for døgnkode:
	 *  ! = Morgen, " = 2 = Ettermiddag, 3 = Kveld, 4 = Natt, 5 Vet ikke
	 *  Kalles fra Tablemaintenancebean når døgnkode settes
	 */
	private void dognkodeIndex(){
		String dognKode = melding.getDognkode().getDN();
		if (dognKode != null && !dognKode.equals("")){
			if (dognKode.equals("Morgen"))
				melding.getDognkode().setV("1");
			if (dognKode.equals("Ettermiddag"))
				melding.getDognkode().setV("2");
			if (dognKode.equals("Kveld"))
				melding.getDognkode().setV("3");
			if (dognKode.equals("Natt"))
				melding.getDognkode().setV("4");
			if (dognKode.equals("Vet ikke"))
				melding.getDognkode().setV("5");
		}
	}

  	
	public Boolean isHvorHvemError(){
		blKontrollBtn = true;
		boolean error = false;
		if(tidForhendelsen!= null ){
			Date today = new Date();
			if(tidForhendelsen.after(today)){
				blDato = true;
				error = true;
			}else{
				blDato = false;
			}
			
		}
		
		if(ikkeSykehus){
			blValgtOrganisasjon = false;
			blSpesialSted = false;
			
			if(spesialSted == null || spesialSted.trim().isEmpty()){
				error = true;
				blSpesialSted=true;
			}
		}else{
			blSpesialSted =  false;
			blValgtOrganisasjon = false;
			
			if(valgtOrganisasjon == null || valgtOrganisasjon.trim().isEmpty()){
				blValgtOrganisasjon = true;
				error = true;
			}
		}
		
		//Check validation of BirthInput value
		if(blBirthInput){
			if(arfodt == null || arfodt.trim().isEmpty() ){
				blArFodt = true;
				error=true;
			}else{
				blArFodt = false;
				try{
					int iArfodt = Integer.parseInt(arfodt);
					Date todate = new Date(); 
				    Calendar cal = Calendar.getInstance();
				    cal.setTime(todate);
				    int thisYear = cal.get(Calendar.YEAR);
				    
				    if(iArfodt < 1900 || iArfodt > thisYear ){
				    	blArFormat = true;
				    	error= true;
				    }else{
				    	blArFormat = false;
				    }
					
				}catch(Exception e){
					blArFormat = true;
					error = true;
				}
			}
		}
		
		// check validation of klokkeslett(tt:mm) format
		try{
			if (!klokkeSlett.trim().isEmpty()){
				blklokke = false;
				int length =  klokkeSlett.length();
				if(length != 5){
					blklokke = true;
					error = true;
				}else{
					String[] klokkeSplit = klokkeSlett.split(":");
					if(klokkeSplit.length != 2){
						blklokke = true;
						error = true;
					}else{
					 
						  int tt = Integer.parseInt(klokkeSplit[0]);
						  int mm = Integer.parseInt(klokkeSplit[1]);
						  
						  if(tt < 0 || tt > 23 || mm < 0 || mm > 60){
							  blklokke = true;
							  error = true;
						  }
					 
					}
				}
			}
		 }catch(Exception e){
			  blklokke = true;
			  error = true;
		  }
		
		return error;
		
	}
	
}

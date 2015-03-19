package no.naks.biovigilans.web.server.resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.naks.biovigilans.model.AnnenkomplikasjonImpl;
import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.biovigilans.web.model.AnnenKomplikasjonwebModel;
import no.naks.biovigilans.web.model.DonasjonwebModel;
import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;
import no.naks.biovigilans.web.model.MelderwebModel;
import no.naks.biovigilans.web.model.TransfusjonWebModel;
import no.naks.biovigilans.web.model.VigilansModel;

import org.restlet.Request;
import org.restlet.data.Disposition;
import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.OutputRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import freemarker.template.SimpleScalar;

/**
 * RapporterLeveranseServerResourceHTML
 * @author olj
 *
 * Denne klassen er knyttet til leveranse.html
 * Det er den endelige kvitteringssiden for en melding til Hemovigilans
 */
public class RapporterLeveranseServerResourceHTML extends SessionServerResource {

	private static final int ALIGN_RIGHT = 0;

	private static final String PARAM_ORDER_TYPE = null;

	private String meldingsId = "melding";

	private Date dato = null;
	private String datoStr = "";
	
	
	public String getDatoStr() {
		return datoStr;
	}


	public void setDatoStr(String datoStr) {
		this.datoStr = datoStr;
	}


	public String getMeldingsId() {
		return meldingsId;
	}


	public void setMeldingsId(String meldingsId) {
		this.meldingsId = meldingsId;
	}


	public Date getDato() {
		return dato;
	}


	public void setDato(Date dato) {
		this.dato = dato;
	}


	/**
	 * getHemovigilans
	 * Denne rutinen henter inn nødvendige session objekter og  setter opp nettsiden for å ta i mot
	 * en rapportert hendelse
	 * @return
	 */
	@Get
	public Representation getHemovigilans() {


	     Reference reference = new Reference(getReference(),"..").getTargetRef();
	     Request request = getRequest();
	     String meldingsNokkel = null;
	     String datoLevert = null;
/*
 * Hent alle session objekter
 */
	//     String messageType = checkMessageType();
	     Map<String, Object> dataModel = new HashMap<String, Object>();
	     melderwebModel =(MelderwebModel) sessionAdmin.getSessionObject(getRequest(),melderId);
		 transfusjon = (TransfusjonWebModel) sessionAdmin.getSessionObject(getRequest(),transfusjonId);
    	 giverModel = (GiverKomplikasjonwebModel) sessionAdmin.getSessionObject(getRequest(),giverkomplikasjonId);
    	 annenModel = (AnnenKomplikasjonwebModel) sessionAdmin.getSessionObject(getRequest(),andreHendelseId);
    	 donasjon = (DonasjonwebModel) sessionAdmin.getSessionObject(getRequest(),donasjonId);
/*    	 
	     if (melderwebModel != null){
	    	 melderwebModel.saveValues();
	    	 melderWebService.saveMelder(melderwebModel);
	    	 SaveSkjema();
	    	 sessionAdmin.setSessionObject(getRequest(), melderwebModel, melderId);
	    	 dataModel.put(melderId, melderwebModel);
	     }
*/	     
	     VigilansModel melding = checkMessageType();

	 
//	     setTransfusjonsObjects();
//	     setAndreHendelser();
	
//    	 melderwebModel.setFormNames(sessionParams);
//    	 melderwebModel.distributeTerms();
    	 if (messageType.equals("transfusjon")){
    		 Vigilansmelding vigilansmelding = (Vigilansmelding) transfusjon.getPasientKomplikasjon();
    		 try {
				transfusjon.setMeldingLevert(vigilansmelding.getMeldingsdato());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 transfusjon.setMeldingsNokkel(vigilansmelding.getMeldingsnokkel());
    		 meldingsNokkel = vigilansmelding.getMeldingsnokkel();
    		 datoLevert = transfusjon.getMeldLevert();
    		 dataModel.put(meldingsId, transfusjon);
    	 }
    	 if (messageType.equals("giver")){
    		 Vigilansmelding vigilansmelding = (Vigilansmelding) giverModel.getGiverKomplikasjon();
  
				try {
					giverModel.setMeldingLevert(vigilansmelding.getMeldingsdato()); // OBS nullpointer !!
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
    		 giverModel.setMeldingsNokkel(vigilansmelding.getMeldingsnokkel());
    		 meldingsNokkel = vigilansmelding.getMeldingsnokkel();
    		 datoLevert = giverModel.getMeldLevert();
    		 dataModel.put(meldingsId, giverModel);
    	 }
    	 if (messageType.equals("annen")){
    		 Vigilansmelding vigilansmelding = (Vigilansmelding) annenModel.getAnnenKomplikasjon();
    		 try {
				annenModel.setMeldingLevert(vigilansmelding.getMeldingsdato());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 annenModel.setMeldingsNokkel(vigilansmelding.getMeldingsnokkel());
    		 meldingsNokkel = vigilansmelding.getMeldingsnokkel();
    		 datoLevert = annenModel.getMeldLevert();
    		 dataModel.put(meldingsId, annenModel);
    	 }
    	 if (messageType.equals("none")){
    		 annenModel = new AnnenKomplikasjonwebModel();
    		 try {
				annenModel.setMeldingLevert(new Date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 annenModel.setMeldingsNokkel("Ingen melding levert");
    		 meldingsNokkel = "Ingen melding levert";
    		 datoLevert = annenModel.getMeldLevert();
    		 dataModel.put(meldingsId, annenModel);
    	 }
    	    SimpleScalar simple = new SimpleScalar(meldingsNokkel);
    	    dataModel.put(nokkelId,simple);
    	    SimpleScalar datoSimple = new SimpleScalar(datoLevert);
    	    dataModel.put(datoId, datoSimple);
    	    String melderEpost = melderwebModel.getMelder().getMelderepost();
    	    if(melderEpost != null || !melderEpost.equals("")){
    	    	 emailWebService.setMailTo(melderEpost);
  //  	    	 emailWebService.sendEmail(meldingsNokkel); //Kommentert bort til stage !!
    	    }
    	   
//    	 dato = melding.getVigilans().getDatoforhendelse();
    	invalidateSessionobjects();
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/leveranse.html"));
	     Representation pasientkomplikasjonFtl = clres2.get();
	     TemplateRepresentation  templatemapRep = new TemplateRepresentation(pasientkomplikasjonFtl,dataModel,
	                MediaType.TEXT_HTML);
    	 return templatemapRep;
	}
	
	@Post
	 public Representation generatePDF(Form form) throws DocumentException, IOException {
	
		final String sourceFile ="c:\\ITextTest.pdf";
		Representation response =
				new OutputRepresentation(MediaType.APPLICATION_PDF) {
				@Override
				public void write(OutputStream outputStream) {
					
					try {
						Document doc = new Document();
						PdfWriter.getInstance(doc, new FileOutputStream(sourceFile)); 
						doc.open(); 
						doc.add(new Paragraph("Welcome to Page 1.")); 
						Paragraph paraRight = new Paragraph();
						paraRight.setAlignment(ALIGN_RIGHT);
						paraRight.add("The para is aligned to right side");
						doc.add(paraRight); 
						doc.setMargins(70, 70, 175, 175); 
						doc.newPage(); 
						doc.add(new Paragraph("Portrait page having larger margins")); 
						doc.close();
						File file = new File(sourceFile);
						byte[] bytes = new byte[(int) file.length()];
						outputStream.write(bytes);
						System.out.print(doc);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				};
				
				/*Disposition attachment = new Disposition(Disposition.TYPE_ATTACHMENT); 
				attachment.setFilename(sourceFile); 
				response.setDisposition(attachment);*/ 
		
	    // TemplateRepresentation  templatemapRep = (TemplateRepresentation) getHemovigilans();
   	 return  response;
		
	}
} 

package no.naks.web.nhn.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import no.naks.web.nhn.control.WebPagesService;
import no.naks.web.nhn.control.WebPagesServiceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;



public class PdfCreator extends MasterBean {

	protected WebPagesService webPagesService;
	
		
	public WebPagesService getWebPagesService() {
		return webPagesService;
	}
	public void setWebPagesService(WebPagesService webPagesService) {
		this.webPagesService = webPagesService;
	}

	public String createPDF() {
		webPagesService = (WebPagesService)getSessionObject("pdfWebpagesService");
		if(webPagesService == null){
			webPagesService = new WebPagesServiceImpl();
		}
		Log log = LogFactory.getLog(this.getClass().getName());
		log.debug("entered createPDF");
	    FacesContext context = FacesContext.getCurrentInstance(); 
	    HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();  
	    response.setContentType("application/pdf");  
	    response.setHeader("Content-disposition", "attachment; filename=meldeSkjema.pdf");
	    try {
	    	Chunk nextPage = new Chunk();
		    nextPage.setNewPage();
		    
	    	// First table Hvor skjedde hendelsen?
	    /*	Paragraph heading1 = new Paragraph();
	    	heading1 = getHeading1("Sammendrag");
	      */  
	        Paragraph heading2 = new Paragraph();
	        heading2 = getHeading2("Hvor/når/hvem");
	        
	        Paragraph heading3 = new Paragraph();
	        heading3 = getHeading3("Hvor skjedde hendelsen?");
	      
	        PdfPTable table1 = getTable();
	        PdfPCell cellHeading;
	        cellHeading = getCellHeading("Valg/felt");
	        table1.addCell(cellHeading);
	        cellHeading = getCellHeading("Du har svart/valgt");
	        table1.addCell(cellHeading);
	        
	        PdfPCell cell;
	        cell=getCell("Hvor skjedde hendelsen");
	        table1.addCell(cell);
	        String skjedde = webPagesService.getSpesialSted();
	        cell=getCell(skjedde);
	        table1.addCell(cell);
	     
	        cell=getCell("Sykehus");
	        table1.addCell(cell);
	        String valgtOrganisasjon = webPagesService.getValgtOrganisasjon();
	        cell=getCell(valgtOrganisasjon);
	        table1.addCell(cell);
	        
	        cell=getCell("Avdeling");
	        table1.addCell(cell);
	        String valgtDepartment = webPagesService.getValgtDepartment();
	        cell=getCell(valgtDepartment);
	        table1.addCell(cell);
	        
	        cell=getCell("Seksjon, sengepost o.l.");
	        table1.addCell(cell);
	        String seksjon = webPagesService.getStedforHendelsen();
	        cell=getCell(seksjon);
	        table1.addCell(cell);
	        
	        
	        //Second Table Når skjedde hendelsen?
	        
	        Paragraph heading3T2 = new Paragraph();
	        heading3T2 = getHeading3("Når skjedde hendelsen?");
	        
	        PdfPTable table2 = getTable();
	        PdfPCell cellHeadingT2;
	        cellHeadingT2 = getCellHeading("Valg/felt");
	        table2.addCell(cellHeadingT2);
	        cellHeadingT2 = getCellHeading("Du har svart/valgt");
	        table2.addCell(cellHeadingT2);
	        
	        PdfPCell cellT2;
	        cellT2=getCell("Dato");
	        table2.addCell(cellT2);
	        String tidForhendelsen;
	        if(webPagesService.getTidForhendelsen() != null){
	        	SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
	        	tidForhendelsen = dateFormat.format(webPagesService.getTidForhendelsen());
	        }else{
	        	tidForhendelsen="";
	        }
	        cellT2=getCell(tidForhendelsen);
	        table2.addCell(cellT2);
	        
	        cellT2=getCell("Tid på døgnet start for hendelsen");
	        table2.addCell(cellT2);
	        String dognkode ;
	        if(webPagesService.getMelding().getDognkode().getDN()!=null){
	        	dognkode= webPagesService.getMelding().getDognkode().getDN();
	        }else{
	        	dognkode="";
	        }
	        cellT2=getCell(dognkode);
	        table2.addCell(cellT2);
	        
	        cellT2=getCell("Klokkeslett start for hendelsen");
	        table2.addCell(cellT2);
	        String klokkeSlett ;
	        if(webPagesService.getKlokkeSlett()!=null){
	        	klokkeSlett = webPagesService.getKlokkeSlett();
	        }else{
	        	klokkeSlett="";
	        }
	        cellT2=getCell(klokkeSlett);
	        table2.addCell(cellT2);

	        // 3rd Table
	        Paragraph heading3T3 = new Paragraph();
	        heading3T3 = getHeading3("Om Pasienten");
	        
	        PdfPTable table3 = getTable();
	        PdfPCell cellHeadingT3;
	        cellHeadingT3 = getCellHeading("Valg/felt");
	        table3.addCell(cellHeadingT3);
	        cellHeadingT3 = getCellHeading("Du har svart/valgt");
	        table3.addCell(cellHeadingT3);
	        
	        PdfPCell cellT3;
	        cellT3=getCell("Kjønn");
	        table3.addCell(cellT3);
	        String kjonn;
	        String strKjonnId = webPagesService.getKjonnKode();
			if(strKjonnId != null && strKjonnId !=""){
				int iKjonnId = Integer.parseInt(strKjonnId);
				if(iKjonnId==1){
					kjonn = "Mann";
				}else if(iKjonnId == 2){
					kjonn =  "Kvinne";
				}else if(iKjonnId == 3){
					kjonn = "Flere pasienter involvert";
				}else{
					kjonn = "Ingen pasienter involvert";
				}
			}else{
				kjonn = "";
			}
	        cellT3=getCell(kjonn);
	        table3.addCell(cellT3);
	        
	        cellT3=getCell("Fødselsår");
	        table3.addCell(cellT3);
	        String aarFodt;
	        Integer aar = new Integer(webPagesService.getMelding().getArfodt());
			String tAr = webPagesService.getTempAr();
			if (!tAr.equals(""))
				aarFodt = tAr;
			if(aar==0)
				aarFodt = "";
			else
				aarFodt = aar.toString();
	        
	        cellT3=getCell(aarFodt);
	        table3.addCell(cellT3);
	        
	        
	        //Table 4
	        Paragraph heading3T4 = new Paragraph();
	        heading3T4= getHeading3("Ytterligere informasjon");
	        
	        PdfPTable table4 = getTable();
	        PdfPCell cellHeadingT4;
	        cellHeadingT4 = getCellHeading("Valg/felt");
	        table4.addCell(cellHeadingT4);
	        cellHeadingT4 = getCellHeading("Du har svart/valgt");
	        table4.addCell(cellHeadingT4);
	        
	        PdfPCell cellT4;
	        cellT4=getCell("Saksnummer");
	        table4.addCell(cellT4);
	        String Saksnummer;
	        if(webPagesService.getCaseNr() != null){
	        	Saksnummer =  webPagesService.getCaseNr();
	        }else{
	        	Saksnummer="";
	        }
	        cellT4=getCell(Saksnummer);
	        table4.addCell(cellT4);
	        
	        
	        //Table 5 hendelse
	        Paragraph heading2T5 = new Paragraph();
	        heading2T5= getHeading2("Hendelse");
	        
	        PdfPTable table5 = getTable();
	        PdfPCell cellHeadingT5;
	        cellHeadingT5 = getCellHeading("Valg/felt");
	        table5.addCell(cellHeadingT5);
	        cellHeadingT5 = getCellHeading("Du har svart/valgt");
	        table5.addCell(cellHeadingT5);
	        
	        PdfPCell cellT5;
	        cellT5=getCell("Beskriv hva som har skjedd");
	        table5.addCell(cellT5);
	        String beskriv;
	        if(webPagesService.getOrgHendelse() != null){
	        	beskriv =  webPagesService.getOrgHendelse() ;
	        }else{
	        	beskriv="";
	        }
	        cellT5=getCell(beskriv);
	        table5.addCell(cellT5);
	        
	        cellT5=getCell("Hvilke umiddelbare konsekvenser fikk dette?");
	        table5.addCell(cellT5);
	        String konsekvenser;
	        if(webPagesService.getMelding().getKonsekvenser() != null){
	        	konsekvenser =  webPagesService.getMelding().getKonsekvenser() ;
	        }else{
	        	konsekvenser="";
	        }
	        cellT5=getCell(konsekvenser);
	        table5.addCell(cellT5);
	        
	        cellT5=getCell("Alvorlighet");
	        table5.addCell(cellT5);
	        String alvorlighet;
	        if(webPagesService.getMelding().getAlvorlighetsgrad().getDN() != null){
	        	alvorlighet =  webPagesService.getMelding().getAlvorlighetsgrad().getDN();
	        }else{
	        	alvorlighet="";
	        }
	        cellT5=getCell(alvorlighet);
	        table5.addCell(cellT5);
	        
	        cellT5=getCell("Beskriv tiltak som ble gjennomført for å begrense skaden");
	        table5.addCell(cellT5);
	        String skaden;
	        if(webPagesService.getMelding().getUtfortestrakstiltak() != null){
	        	skaden =  webPagesService.getMelding().getUtfortestrakstiltak();
	        }else{
	        	skaden="";
	        }
	        cellT5=getCell(skaden);
	        table5.addCell(cellT5);
	        
	        cellT5=getCell("Hvordan ble hendelsen oppdaget?");
	        table5.addCell(cellT5);
	        String oppdaget;
	        if(webPagesService.getMelding().getHvordanoppdaget() != null){
	        	oppdaget =  webPagesService.getMelding().getHvordanoppdaget();
	        }else{
	        	oppdaget="";
	        }
	        cellT5=getCell(oppdaget);
	        table5.addCell(cellT5);
	        
	        //Table 6
	        Paragraph heading2T6 = new Paragraph();
	        heading2T6= getHeading2("Diskusjon");
	        
	        PdfPTable table6 = getTable();
	        PdfPCell cellHeadingT6;
	        cellHeadingT6 = getCellHeading("Valg/felt");
	        table6.addCell(cellHeadingT6);
	        cellHeadingT6 = getCellHeading("Du har svart/valgt");
	        table6.addCell(cellHeadingT6);
	        
	        PdfPCell cellT6;
	        cellT6=getCell("Hvorfor tror du hendelsen skjedde?");
	        table6.addCell(cellT6);
	        String tror;
	        if(webPagesService.getMelding().getArsaksbeskrivelse() != null){
	        	tror =  webPagesService.getMelding().getArsaksbeskrivelse() ;
	        }else{
	        	tror="";
	        }
	        cellT6=getCell(tror);
	        table6.addCell(cellT6);
	        
	        cellT6=getCell("Beskriv tiltak som bør gjøres for at noe lignende ikke skal skje igjen");
	        table6.addCell(cellT6);
	        String tiltak;
	        if(webPagesService.getMelding().getForslagtiltak() != null){
	        	tiltak =  webPagesService.getMelding().getForslagtiltak() ;
	        }else{
	        	tiltak="";
	        }
	        cellT6=getCell(tiltak);
	        table6.addCell(cellT6);
	        
	        cellT6=getCell("Kan andre i Helse-Norge lære av denne hendelsen? På hvilken måte?");
	        table6.addCell(cellT6);
	        String andre;
	        if(webPagesService.getLareavHendelse() != null){
	        	andre =  webPagesService.getLareavHendelse() ;
	        }else{
	        	andre="";
	        }
	        cellT6=getCell(andre);
	        table6.addCell(cellT6);
	        
	        //Table 7
	        Paragraph heading2T7 = new Paragraph();
	        heading2T7= getHeading2("Kontaktinformasjon");
	        
	        PdfPTable table7 = getTable();
	        PdfPCell cellHeadingT7;
	        cellHeadingT7 = getCellHeading("Valg/felt");
	        table7.addCell(cellHeadingT7);
	        cellHeadingT7 = getCellHeading("Du har svart/valgt");
	        table7.addCell(cellHeadingT7);
	        
	        PdfPCell cellT7;
	        cellT7=getCell("Jeg er");
	        table7.addCell(cellT7);
	        String jegEr;
	        if(webPagesService.getMelding().getRolle() != null){
	        	jegEr =  webPagesService.getMelding().getRolle() ;
	        }else{
	        	jegEr="";
	        }
	        cellT7=getCell(jegEr);
	        table7.addCell(cellT7);
	        
	        cellT7=getCell("Navn");
	        table7.addCell(cellT7);
	        String navn;
	        if(webPagesService.getPerson().getNavn() != null){
	        	navn =  webPagesService.getPerson().getNavn() ;
	        }else{
	        	navn="";
	        }
	        cellT7=getCell(navn);
	        table7.addCell(cellT7);
	        
	        cellT7=getCell("E-post (arbeid)");
	        table7.addCell(cellT7);
	        String epost;
	        if(webPagesService.getPerson().getEPost() != null){
	        	epost =  webPagesService.getPerson().getEPost() ;
	        }else{
	        	epost="";
	        }
	        cellT7=getCell(epost);
	        table7.addCell(cellT7);
	        
	        cellT7=getCell("Telefonnummer");
	        table7.addCell(cellT7);
	        String telefonnummer;
	        if(webPagesService.getPerson().getTelefonNummer() != null){
	        	telefonnummer =  webPagesService.getPerson().getTelefonNummer() ;
	        }else{
	        	telefonnummer="";
	        }
	        cellT7=getCell(telefonnummer);
	        table7.addCell(cellT7);
	        
	        cellT7=getCell("Leder Navn");
	        table7.addCell(cellT7);
	        String lederNavn;
	        if(webPagesService.getLeder().getNavn() != null){
	        	lederNavn =  webPagesService.getLeder().getNavn() ;
	        }else{
	        	lederNavn="";
	        }
	        cellT7=getCell(lederNavn);
	        table7.addCell(cellT7);
	        
	        cellT7=getCell("Leder E-post (arbeid)");
	        table7.addCell(cellT7);
	        String lederEpost;
	        if(webPagesService.getLeder().getEPost() != null){
	        	lederEpost =  webPagesService.getLeder().getEPost() ;
	        }else{
	        	lederEpost="";
	        }
	        cellT7=getCell(lederEpost);
	        table7.addCell(cellT7);
	        
	        cellT7=getCell("Leder Telefonnummer");
	        table7.addCell(cellT7);
	        String lederTel;
	        if(webPagesService.getLeder().getTelefonNummer() != null){
	        	lederTel =  webPagesService.getLeder().getTelefonNummer() ;
	        }else{
	        	lederTel="";
	        }
	        cellT7=getCell(lederTel);
	        table7.addCell(cellT7);
	        
	        cellT7=getCell("Jeg ønsker at Kunnskapssenteret raskt tar kontakt for råd og veiledning i denne saken");
	        table7.addCell(cellT7);
	        String onsker;
	        boolean blOnskerhjelp = webPagesService.getMelding().isOnskerhjelp();
			if(blOnskerhjelp){
				onsker = "Ja";
			}else{
				onsker = "Nei";
			}
	       
	        cellT7=getCell(onsker);
	        table7.addCell(cellT7);
	        
	        Document document = new Document();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PdfWriter.getInstance(document, baos);
	        document.open();
	        
	        //Add image
	        ExternalContext externalContext = context.getExternalContext();
	        String basePath = externalContext.getRealPath("/");
	        String imagePath = basePath +"resources/images/logo.png";
	        Image image = Image.getInstance(imagePath);
	        image.setAlignment(image.ALIGN_LEFT | image.TEXTWRAP);
	        image.scaleAbsolute(180f, 40f);
	        image.setAlignment(image.ALIGN_TOP);
	        Paragraph p = new Paragraph();
	        p.setAlignment(Element.ALIGN_LEFT);
	        p.add(image);
	        p.setSpacingAfter(80);
	        document.add(p);
	
	        // Table 1
	 //       document.add(heading1);
	        document.add(heading2);
	        document.add(heading3);
	        document.add(table1);
	        log.info("tabl1 created");
	        // Table 2
	        document.add(heading3T2);
	        document.add(table2);
	        log.info("tabl2 created");

	        
	        //Table 3
	        document.add(heading3T3);
	        document.add(table3);
	        log.info("tabl3 created");

	        
	        //Table 4
	        document.add(heading3T4);
	        document.add(table4);
	        log.info("tabl4 created");

	        
	        //Table 5
	        document.add(heading2T5);
	        document.add(table5);
	        log.info("tabl5 created");

	        //Table 6
	        document.add(heading2T6);
	        document.add(table6);
	        log.info("tabl6 created");

	        
	        //Table 7
	        document.add(nextPage);
	        document.add(heading2T7);
	        document.add(table7);
	        log.info("tabl7 created");

	        // close the document
	        document.close();

	        // setting some response headers
	        response.setHeader("Expires", "0");
	        response.setHeader("Cache-Control",
	            "must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        // setting the content type
	        response.setContentType("application/pdf");
	        // the contentlength
	        response.setContentLength(baos.size());
	        // write ByteArrayOutputStream to the ServletOutputStream
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	        os.close();
	        log.debug("flushed and closed the outputstream");
	    }
	    catch(DocumentException e) {
	        log.error("error: "+e);
			System.out.println("Pdf file generate error: " + e);

	    }
	    catch (IOException e) {
	        log.error("error: "+e);
			System.out.println("Pdf file generate error: " + e);

	    }
	    catch (Exception ex) {
	        log.debug("error: " + ex.getMessage());
			System.out.println("Pdf file generate error: " + ex);

	    }
	    context.responseComplete();
	    log.debug("context.responseComplete()");
	    return "";
	}
	
	
	private PdfPTable getTable(){
		int nrOfCell =2;
        PdfPTable table=new PdfPTable(nrOfCell);
        table.setSpacingBefore(5);
        table.setSpacingAfter(5);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        return table;
	}
	
	private PdfPCell getCellHeading(String headingTxt){
		BaseColor color = new BaseColor(0xF2, 0xF2, 0xF2);
        Chunk headText = new Chunk(headingTxt);
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
        headText.setFont(font) ;
        Phrase pharse = new Phrase(headText);
        PdfPCell heading  = new PdfPCell(pharse) ;
        heading.setBackgroundColor(color);
        
		return heading;
	}
	
	private PdfPCell getCell(String txt){
		Chunk clText = new Chunk(txt);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10);
		clText.setFont(font) ;
        Phrase phrase = new Phrase(clText);
        PdfPCell cell = new PdfPCell(phrase);
        
        return cell;
	}
	
	private Paragraph getHeading1(String txt){
		Paragraph p = new Paragraph();
		Chunk chunk = new Chunk(txt);
        chunk.setUnderline(0.2f, -2f);
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
        chunk.setFont(font);
        p.setAlignment(Element.ALIGN_LEFT);
        p.setSpacingAfter(20);
        p.add(chunk);
        
        return p;
	}
	private Paragraph getHeading2(String txt){
		Paragraph p = new Paragraph();
        Chunk chunk = new Chunk(txt,  
        		new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
        p.setAlignment(Element.ALIGN_LEFT);
        p.add(chunk);
        return p;
	}
	private Paragraph getHeading3(String txt){
		Paragraph p = new Paragraph();
        Chunk chunk = new Chunk(txt,  
        		new Font(Font.FontFamily.TIMES_ROMAN, 12));
        p.setAlignment(Element.ALIGN_LEFT);
        p.add(chunk);
        
        return p;
	}
}

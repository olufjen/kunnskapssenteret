package no.naks.web.nhn.bean;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.naks.web.nhn.control.WebPagesService;
import no.naks.web.nhn.control.WebPagesServiceImpl;

public class KvitteringBean extends MasterBean {

	protected WebPagesService webPagesService;
	protected String kjonn;
	protected String aarFodt;
	protected  String dognkode ;
	protected String beskriv;
	protected String konsekvenser;
	protected  String alvorlighet;
	protected  String skaden;
	protected String oppdaget;
	protected  String arsaksbeskrivelse;
	protected  String tiltak;
	protected  String andre;
	protected String onsker;
	protected  boolean blKontrollPage = false;   
	public  KvitteringBean()   {
	
		try{
			if(getSessionObject("webService") != null){
				webPagesService = (WebPagesService)getSessionObject("webService");
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.removeAttribute("webService");
				setSessionObject("pdfWebpagesService", webPagesService);
				setSessionObject("kntPage", "true");
			}
		}catch(Exception ex){
			
		}
	}

	
	public WebPagesService getWebPagesService() {
		return webPagesService;
	}
	
	public void setWebPagesService(WebPagesService webPagesService) {
		this.webPagesService = webPagesService;
	}
	
	public String getKjonn(){
		kjonn = "";
	   if(webPagesService != null){
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
			}
	   }
		return kjonn;
		
	}

	public String getAarFodt() {
		if(webPagesService != null ){
			Integer aar = new Integer(webPagesService.getMelding().getArfodt());
			String tAr = webPagesService.getTempAr();
			if (!tAr.equals(""))
				aarFodt = tAr;
			if(aar==0)
				aarFodt = "";
			else
				aarFodt = aar.toString();
		}
		return aarFodt;
	}
	
	public String getDognkode(){
		dognkode="";
		if(webPagesService != null && webPagesService.getMelding().getDognkode().getDN()!=null){
        	dognkode= webPagesService.getMelding().getDognkode().getDN();
        }
        return dognkode;
	}
	
	public String getBeskriv(){
	    if(webPagesService != null && webPagesService.getOrgHendelse() != null){
	    	beskriv =  webPagesService.getOrgHendelse();
	    }else{
	    	beskriv="";
	    }
	    
	    return beskriv;
	}

	public String getKonsekvenser() {
		if(webPagesService != null && webPagesService.getMelding().getKonsekvenser() != null){
        	konsekvenser =  webPagesService.getMelding().getKonsekvenser() ;
        }else{
        	konsekvenser="";
        }
		
		return konsekvenser;
	}

	public String getAlvorlighet() {
		 if(webPagesService != null && webPagesService.getMelding().getAlvorlighetsgrad().getDN() != null){
        	alvorlighet =  webPagesService.getMelding().getAlvorlighetsgrad().getDN();
        }else{
        	alvorlighet="";
        }
		return alvorlighet;
	}

	public String getSkaden() {
		 if(webPagesService != null && webPagesService.getMelding().getUtfortestrakstiltak() != null){
	        	skaden =  webPagesService.getMelding().getUtfortestrakstiltak();
	        }else{
	        	skaden="";
	        }
		return skaden;
	}

	public String getOppdaget() {
		 if(webPagesService != null && webPagesService.getMelding().getHvordanoppdaget() != null){
        	oppdaget =  webPagesService.getMelding().getHvordanoppdaget();
        }else{
        	oppdaget="";
        }
		return oppdaget;
	}

	public String getArsaksbeskrivelse() {
		 if(webPagesService != null && webPagesService.getMelding().getArsaksbeskrivelse() != null){
			 arsaksbeskrivelse =  webPagesService.getMelding().getArsaksbeskrivelse() ;
        }else{
        	arsaksbeskrivelse="";
        }
		return arsaksbeskrivelse;
	}

	public String getTiltak() {
		 if(webPagesService != null && webPagesService.getMelding().getForslagtiltak() != null){
        	tiltak =  webPagesService.getMelding().getForslagtiltak() ;
        }else{
        	tiltak="";
        }
		
		return tiltak;
	}

	public String getAndre() {
		
		 if(webPagesService != null && webPagesService.getLareavHendelse() != null){
	        	andre =  webPagesService.getLareavHendelse() ;
	        }else{
	        	andre="";
	        }
		return andre;
	}

	public String getOnsker() {
		onsker = "Nei";
		if(webPagesService != null ){ 
			boolean blOnskerhjelp = webPagesService.getMelding().isOnskerhjelp();
			if(blOnskerhjelp){
				onsker = "Ja";
			}
		}
		return onsker;
	}


	public boolean isBlKontrollPage() {
		return blKontrollPage;
	}


	public void setBlKontrollPage(boolean blKontrollPage) {
		this.blKontrollPage = blKontrollPage;
	}


	/*@Override
	public void destroy() {
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			
			HttpServletRequest req = (HttpServletRequest) request;
		    HttpServletResponse res = (HttpServletResponse) response;
		    boolean blKvittering =req.getRequestURI().startsWith(req.getContextPath() + "/innhold/kvittering.faces");
		    boolean blKontroll =req.getRequestURI().startsWith(req.getContextPath() + "/innhold/kontroll.faces");
		   
		    if(blKvittering){
		    	  setBlKontrollPage(true);
		    }
		    if(blKontroll){
			    if (!req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip JSF resources (CSS/JS/Images/etc)
			        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			        res.setDateHeader("Expires", 0); // Proxies.
			      
			     
			    }
		    }
		   
		    if (blKontroll && blKontrollPage ) { // Skip JSF resources (CSS/JS/Images/etc)
		    	res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		        res.setDateHeader("Expires", 0); // Proxies.
			    res.sendRedirect(req.getContextPath() + "/innhold/home.xhtml");
			    setBlKontrollPage(false);
			}
		}catch(Exception ex){
			
		}
	   chain.doFilter(request, response);
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	*/
	
	
}

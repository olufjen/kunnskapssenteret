package no.naks.web.server.resource;

import java.io.File;

import org.restlet.Context;
import org.restlet.data.LocalReference;
import org.restlet.data.Reference;
import org.restlet.resource.Directory;

public class ProsedyreDirectory extends Directory {

	private static Context prosedyreContext;
	
	public ProsedyreDirectory(Context context, Reference rootLocalReference) {
		super(context, rootLocalReference);
		// TODO Auto-generated constructor stub
	}

	public ProsedyreDirectory(Context context, String rootUri) {
		super(context, rootUri);
		String uri = rootUri;
		// TODO Auto-generated constructor stub
		//   Reference reference = new Reference(getReference(),"..").getTargetRef();
	     LocalReference pakke = LocalReference.createClapReference(getClass().getPackage());
	//     LocalReference localUri = new LocalReference(reference);
	     LocalReference localFilerefx = new LocalReference("no/naks/web/server/resource");
	     String ps = "/innmelding/css/style.css";
	 //    LocalReference localFileref = LocalReference.createFileReference(ps);
	    LocalReference localFileref = LocalReference.createClapReference(LocalReference.CLAP_CLASS, ps);
	  //   File file = localFileref.getFile();
	//     System.out.println("Directory created");
	
	    
	//     setRootRef(pakke);
	     setRootRef(localFileref);
	     setIndexName("style.css");
	     setListingAllowed(true);
	}

	public Context getProsedyreContext() {
		return prosedyreContext;
	}

	public void setProsedyreContext(Context prosedyreContext) {
		this.prosedyreContext = prosedyreContext;
	}


	
}

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
		// TODO Auto-generated constructor stub
		//   Reference reference = new Reference(getReference(),"..").getTargetRef();
	     LocalReference pakke = LocalReference.createClapReference(getClass().getPackage());
	//     LocalReference localUri = new LocalReference(reference);
	     LocalReference localFileref = new LocalReference("/no/naks/server/resource/css/");
	     File file = localFileref.getFile();
	//     System.out.println("Directory created");
	     setListingAllowed(true);
	}

	public Context getProsedyreContext() {
		return prosedyreContext;
	}

	public void setProsedyreContext(Context prosedyreContext) {
		this.prosedyreContext = prosedyreContext;
	}


	
}

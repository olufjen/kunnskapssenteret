package no.helsebiblioteket.doctohtml;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.ImportContentsParams;
import com.enonic.cms.api.plugin.ext.TaskHandler;

public class DocToHtmlImport extends TaskHandler{
	private static final Namespace XMLNS = Namespace.getNamespace("http://www.w3.org/1999/xhtml");
	private Log log = LogFactory.getLog(DocToHtmlImport.class);
	private Client client;
	private String directoryPath;
	private String cmsUsername;
	private String cmsPassword;
	private int categoryKey;

	private enum TaskPropery {
		directoryPath,
		cmsUsername,
		cmsPassword,
		categoryKey,
	}

	@Override
	public void execute(Properties props) throws Exception {
		initProperties(props);
		initClient();

		File directory = new File(directoryPath);
		importHtmlToCms(directory.listFiles());
	}

	private void importHtmlToCms(File[] files) throws JDOMException, IOException {
		log.info("Importing files");

		SAXBuilder builder = new SAXBuilder();
		XMLOutputter out = new XMLOutputter();
		String fileName = "";

		for (File htmlFile : files) {
			try {
				fileName = htmlFile.getName();
				log.info("Importing " + fileName);

				Document document = builder.build(htmlFile);
				Element htmlElement = document.getRootElement();
				htmlElement.getChild("body", XMLNS).removeChild("h1", XMLNS);

				String html = out.outputString(htmlElement);

				ImportContentsParams params = new ImportContentsParams();
				params.categoryKey = categoryKey;
				params.importName = "DocToHtml";
				params.publishFrom = new Date();
				params.data = html;

				client.importContents(params);

			} catch (JDOMParseException e) {
				log.error("Failed to parse file: " + fileName, e);
			} 
		}
		log.info("File import done");
	}

	private void initClient() {
		log.info("Logging in: " + cmsUsername);
		client = ClientFactory.getLocalClient();
		client.login(cmsUsername, cmsPassword);
	}

	private void initProperties(Properties props) {
		directoryPath = props.getProperty(TaskPropery.directoryPath.name());
		cmsUsername = props.getProperty(TaskPropery.cmsUsername.name());
		cmsPassword = props.getProperty(TaskPropery.cmsPassword.name());
		categoryKey = Integer.parseInt(props.getProperty(TaskPropery.categoryKey.name()));
	}
}
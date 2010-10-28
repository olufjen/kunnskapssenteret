package no.kunnskapssenteret.evs.plugins.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

import no.kunnskapssenteret.evs.util.Base64;
import no.kunnskapssenteret.evs.util.Helper;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.Text;
import org.jdom.filter.ContentFilter;
import org.jdom.filter.ElementFilter;
import org.jdom.filter.Filter;
import org.jdom.xpath.XPath;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.CreateFileContentParams;
import com.enonic.cms.api.client.model.DeleteContentParams;
import com.enonic.cms.api.client.model.GetBinaryParams;
import com.enonic.cms.api.client.model.GetContentByCategoryParams;
import com.enonic.cms.api.client.model.UpdateFileContentParams;
import com.enonic.cms.api.client.model.content.ContentStatus;
import com.enonic.cms.api.client.model.content.file.FileBinaryInput;
import com.enonic.cms.api.client.model.content.file.FileContentDataInput;
import com.enonic.cms.api.client.model.content.file.FileDescriptionInput;
import com.enonic.cms.api.client.model.content.file.FileNameInput;


public class Test {
	Client client;
	
	public static void main(String args[]) throws IOException {
		//Test test = new Test();
		//test.client = ClientFactory.getRemoteClient("http://www-t.helsebiblioteket.no/rpc/bin", false);
		//test.client.login("batchuser", "password");
		//test.testUpdateFile();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateAsString = null;
        dateAsString = formatter.format(new Date());
        OutputStream os = new ByteArrayOutputStream();
        System.out.println("dateAsString: " + dateAsString);
        InputStream is = new ByteArrayInputStream(dateAsString.getBytes("UTF-8"));
        StringWriter writer = new StringWriter();
        Base64.encode(is, writer);
        String dateAsStringBase64Encoded = writer.toString();
        System.out.println("dateAsStringBase64Encoded: " + dateAsStringBase64Encoded);
        String dateAsStringBase64EncodedUrlEncoded = URLEncoder.encode(dateAsStringBase64Encoded, "UTF-8");
        System.out.println("dateAsStringBase64EncodedUrlEncoded: " + dateAsStringBase64EncodedUrlEncoded);
        System.out.println(". urlencoded: " + URLEncoder.encode(".", "UTF-8"));
	}
	
	private void testUpdateFile() {
		initClient();
		UpdateFileContentParams updateParams = new UpdateFileContentParams();
		FileDescriptionInput fileDescription = new FileDescriptionInput("testbeskrivelse");
		FileContentDataInput contentData = new FileContentDataInput();
		FileBinaryInput inputFile = new FileBinaryInput("<innhold>innhold i fil som streng</innhold>".getBytes(), "McMaster mostaccessedmentalhealth webservice-response.xml");
		contentData.name = new FileNameInput("McMaster mostaccessedmentalhealth webservice-response.xml");
		contentData.description = fileDescription;
		contentData.binary = inputFile;
		updateParams.publishFrom = new Date();
		updateParams.contentKey = 32062;
		updateParams.fileContentData = contentData;
		updateParams.status = ContentStatus.STATUS_APPROVED;
		//updateParams.createNewVersion = true;
		//updateParams.setAsCurrentVersion = true;
		client.updateFileContent(updateParams);
	}
	
	private void testCreateFile() {
		//private static void createFile(Client client, byte myfile[],		
		//		  String binaryname, int categorykey, String description) {	
		//}
		initClient();
		CreateFileContentParams crf = new CreateFileContentParams();  
		FileBinaryInput fbi = new FileBinaryInput("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<testdata>test</testdata>".getBytes(), "testfil.xml");
		FileNameInput fni = new FileNameInput("testfil.xml");
		FileContentDataInput fcd = new FileContentDataInput();
		FileDescriptionInput fdi = new FileDescriptionInput("testbeskrivelse");
		fcd.description = fdi;
		crf.categoryKey = Integer.valueOf(719);  
		crf.publishFrom = new Date();
		crf.publishTo = null;
		crf.status = ContentStatus.STATUS_APPROVED;  
		fcd.binary = fbi;
		fcd.name = fni;
		crf.fileContentData = fcd;  
		client.createFileContent(crf);
	}
	
	private void initClient() {
		//Client client = ClientFactory.getRemoteClient("http://www-t.helsebiblioteket.no/rpc/bin", false);
		if (this.client == null) {
			Client client = ClientFactory.getLocalClient();
			client.login("batchuser", "password");
			this.client = client;
		}
	}
	
	private int testGetBinaryContentKeyByCategoryAndName(int category, String name) {
		initClient();
		return testGetBinaryKeyOrBinaryContentKeyByCategoryAndNameAndXpath(category, name, "//contents/content/@key");
	}
	
	private int testGetBinaryKeyOrBinaryContentKeyByCategoryAndNameAndXpath(int category, String name, String xpath) {
		initClient();
		int key = -1;
		GetContentByCategoryParams gcbcp = new GetContentByCategoryParams();
		gcbcp.includeData = false;
		gcbcp.categoryKeys = new int[] { category };
		gcbcp.query = "contentdata/name = '" + name + "'";
		Document doc = client.getContentByCategory(gcbcp);
		try {
			Helper.prettyPrint(doc);
		} catch (IOException ioe) {
			System.out.println("feil");
		}
		XPath keyXPath = null;
		List<Attribute> keys = null;
		try {
			keyXPath = XPath.newInstance(xpath);
			keys = keyXPath.selectNodes(doc);
		} catch (JDOMException jde) {
			System.out.println("sdfsdf");
		}
		for (Attribute a : keys) {
			key = Integer.valueOf(a.getValue());
		}
		return key;
	}
	
	private int testGetBinaryKeyByCategoryAndName(int category, String name) {
		return testGetBinaryKeyOrBinaryContentKeyByCategoryAndNameAndXpath(category, name, "//contents/content/binaries/binary/@key");
	}
	
	private void testDeleteFile() {
		initClient();
		DeleteContentParams dcp = new DeleteContentParams();
		dcp.contentKey = testGetBinaryContentKeyByCategoryAndName(719, "testfil.xml");
		client.deleteContent(dcp);
	}
}
package no.kunnskapssenteret.evs.plugins.cache;

import java.io.IOException;
import java.util.List;

import no.kunnskapssenteret.evs.util.Helper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.model.GetContentBinaryParams;
import com.enonic.cms.api.client.model.GetContentByCategoryParams;

public class GetContentByCategoryKeyAndName {
	private Log logger = LogFactory.getLog(GetContentByCategoryKeyAndName.class);
	private static GetContentByCategoryKeyAndName instance = null;
	
	protected GetContentByCategoryKeyAndName() {
		// Exists only to defeat instantiation.
	}
	   
	public static GetContentByCategoryKeyAndName getInstance() {
		if(instance == null) {
	    	instance = new GetContentByCategoryKeyAndName();
		}
	    return instance;
	}
	
	public Integer getBinaryContentKeyByCategoryAndName(Client client, Integer category, String name) {
		return getBinaryKeyOrBinaryContentKeyByCategoryAndNameAndXpath(client, category, name, "//contents/content/@key");
	}
	
	private Integer getBinaryKeyOrBinaryContentKeyByCategoryAndNameAndXpath(Client client, Integer category, String name, String xpath) {
		Integer key = null;
		GetContentByCategoryParams gcbcp = new GetContentByCategoryParams();
		gcbcp.includeData = false;
		gcbcp.categoryKeys = new int[] { category };
		gcbcp.query = "contentdata/name = '" + name + "'";
		Document doc = client.getContentByCategory(gcbcp);
		XPath keyXPath = null;
		List<Attribute> keys = null;
		try {
			keyXPath = XPath.newInstance(xpath);
			keys = keyXPath.selectNodes(doc);
		} catch (JDOMException jde) {
			logger.error("Could not perform xpath search while trying to get binary key / content key. Error message: " + jde.getMessage(), jde);
		}
		for (Attribute a : keys) {
			key = Integer.valueOf(a.getValue());
		}
		return key;
	}
	
	public String getBinaryAsStringByCategoryAndName(Client client, Integer category, String name) {
		String result = null;
		Integer contentKey = getBinaryContentKeyByCategoryAndName(client, category, name);
		GetContentBinaryParams params = new GetContentBinaryParams();
		if (contentKey == null) {
			return null;
		}
		params.contentKey = contentKey;
		Document doc = client.getContentBinary(params);
		XPath dataXPath = null;
		List<Element> dataList = null; // only one element
		try {
			dataXPath = XPath.newInstance("//binary/data");
			dataList = dataXPath.selectNodes(doc);
		} catch (JDOMException jde) {
			logger.error("Could not perform xpath search while trying to get binary data. Error message: " + jde.getMessage(), jde);
		}
		for (Element e : dataList) {
			result = e.getValue();
		}
		return result;
	}
	
	public Integer getBinaryKeyByCategoryAndName(Client client, Integer category, String name) {
		return getBinaryKeyOrBinaryContentKeyByCategoryAndNameAndXpath(client, category, name, "//contents/content/binaries/binary/@key");
	}
}
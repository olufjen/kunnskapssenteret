package no.helsebiblioteket.admin.translator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.tools.doclets.internal.toolkit.resources.doclets;

import no.helsebiblioteket.admin.domain.Profile;

public class ProfileToXMLTranslator {
	public void translate(Profile profile, Document document, Element element){
		Element profileElement = document.createElement("profile");
		profileElement.appendChild(UserToXMLTranslator.element(document, "survey", profile.getParticipateSurvey()));
		profileElement.appendChild(UserToXMLTranslator.element(document, "newsletter", profile.getReceiveNewsletter()));
		
		// TODO: Complete this!
		profile.getLastChanged();
		
		if(element == null){
			document.appendChild(profileElement);
		} else {
			element.appendChild(profileElement);
		}
		
	}
}

package no.helsebiblioteket.admin.translator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.Profile;

public class ProfileToXMLTranslator {
	public void translate(Profile profile, Document document, Element element){
		Element profileElement = document.createElement("profile");
		profileElement.appendChild(UserToXMLTranslator.element(document, "survey", profile.getParticipateSurvey()));
		profileElement.appendChild(UserToXMLTranslator.element(document, "newsletter", profile.getReceiveNewsletter()));
		
		// TODO Fase2: Complete this!
//		profile.getLastChanged();
		
		if(element == null){
			document.appendChild(profileElement);
		} else {
			element.appendChild(profileElement);
		}
		
	}
}

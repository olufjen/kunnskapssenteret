package no.helsebiblioteket.admin.translator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.Position;

public class PositionToXMLTranslator {
	public void translate(Position position, Document document, Element element){
		Element positionElement = document.createElement("position");
		if (position != null) {
			positionElement.appendChild(UserToXMLTranslator.cDataElement(document, "description", position.getDescription()));
			positionElement.appendChild(UserToXMLTranslator.cDataElement(document, "key", position.getKey().toString()));
			positionElement.appendChild(UserToXMLTranslator.cDataElement(document, "name", position.getName()));		
		}
//		position.getId();
//		position.getOrganizationTypeId();
		
		if(element == null){
			document.appendChild(positionElement);
		} else {
			element.appendChild(positionElement);
		}
	}
}
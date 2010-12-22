package no.helsebiblioteket.admin.factory;

import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;

public class PositionFactory {
	public static PositionFactory factory = new PositionFactory();
	private PositionFactory(){}
	public Position createPosition(){
		Position position = new Position();
		position.setDescription("");
		position.setKey(PositionTypeKey.none);
		position.setName("");
//		position.setOrganizationType(organizationType)
		return position;
	}
	public Position completePosition(OrganizationType organizationType){
		Position position = createPosition();
		position.setOrganizationType(organizationType);
		return position;
	}
}

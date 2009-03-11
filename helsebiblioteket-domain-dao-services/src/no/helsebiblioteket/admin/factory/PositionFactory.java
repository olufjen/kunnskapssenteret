package no.helsebiblioteket.admin.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;

public class PositionFactory {
	public static PositionFactory factory = new PositionFactory();
	private PositionFactory(){}
	public Position createPosition(){
		Position position = new Position();
		position.setDescription("");
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

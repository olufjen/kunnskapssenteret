package no.helsebiblioteket.admin.translator;

import no.helsebiblioteket.admin.domain.LoggedInUser;
import no.helsebiblioteket.admin.domain.User;

public class UserToLoggedInUserTranslator {
	public LoggedInUser translate(User user){
		LoggedInUser loggedInUser = new LoggedInUser();
		loggedInUser.setEmail(user.getPerson().getContactInformation().getEmail());
		loggedInUser.setEmployer(user.getPerson().getEmployer());
		loggedInUser.setFirstName(user.getPerson().getFirstName());
		loggedInUser.setHprNumber(user.getPerson().getHprNumber());
		loggedInUser.setId(user.getId());
		loggedInUser.setIsStudent(user.getPerson().getIsStudent());
		loggedInUser.setLastName(user.getPerson().getLastName());
		loggedInUser.setParticipateSurvey(user.getPerson().getProfile().getParticipateSurvey());
		if(user.getPerson().getPosition() != null && user.getPerson().getPosition().getKey() != null){
			loggedInUser.setPositionDescription(user.getPerson().getPosition().getDescription());
			loggedInUser.setPositionKey(user.getPerson().getPosition().getKey().getValue());
			loggedInUser.setPositionName(user.getPerson().getPosition().getName());
		}
		loggedInUser.setPositionText(user.getPerson().getPositionText());
		loggedInUser.setReceiveNewsletter(user.getPerson().getProfile().getReceiveNewsletter());
		if(user.getRoleList().length > 0){
			loggedInUser.setRoleKey(user.getRoleList()[0].getKey().getValue());
			loggedInUser.setRoleName(user.getRoleList()[0].getName());
		}
		loggedInUser.setStudentNumber(user.getPerson().getStudentNumber());
		loggedInUser.setUsername(user.getUsername());
		return loggedInUser;
	}
}

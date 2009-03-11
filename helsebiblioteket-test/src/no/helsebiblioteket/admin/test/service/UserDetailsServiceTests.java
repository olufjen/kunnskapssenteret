package no.helsebiblioteket.admin.test.service;

import java.util.Random;

import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.util.Assert;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class UserDetailsServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testloadUserByUsername(){
		// Random value
		String randomValue = "" + new Random().nextInt(1000000000);
		String username = "username_" + randomValue;
		
		// 'static' values
		Position kiropraktor = ((ValueResultPosition) beanFactory.getUserService().getPositionByKey(PositionTypeKey.kiropraktor)).getValue();
		OrganizationType teaching = ((ValueResultOrganizationType) beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.teaching)).getValue();

		// New objects
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(teaching, kiropraktor);
		User user = UserFactory.factory.completeUser(organization, kiropraktor);
		user.setUsername(username);
		
		// Inserts
		organization.setOrganization(((ValueResultOrganization)this.beanFactory.getOrganizationService().insertMemberOrganization(organization)).getValue());
		user.setOrganization(organization.getOrganization());
		user = ((ValueResultUser)this.beanFactory.getUserService().insertUser(user)).getValue();
		
		// Service
		UserDetailsService userDetailsService = beanFactory.getUserDetailsService();
		
//		TEST: public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		Assert.notNull(userDetails, "No user found");
		Assert.isTrue(userDetails.getUsername().equals(username), "User not the same");
	}

}

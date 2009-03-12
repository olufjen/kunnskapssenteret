package no.helsebiblioteket.admin.test.service;

import java.util.Random;

import org.springframework.security.userdetails.UserDetailsService;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
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
		OrganizationType health_enterprise = ((ValueResultOrganizationType) beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise)).getValue();
		Position kiropraktor = ((ValueResultPosition) beanFactory.getUserService().getPositionByKey(PositionTypeKey.kiropraktor, health_enterprise)).getValue();

		// New objects
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(health_enterprise, kiropraktor);
		User user = UserFactory.factory.completeUser(organization, kiropraktor);
		user.setUsername(username);
		
		// Inserts
		organization = ((ValueResultMemberOrganization)this.beanFactory.getOrganizationService().insertMemberOrganization(organization)).getValue();
		user.setOrganization(organization.getOrganization());
		user = ((ValueResultUser)this.beanFactory.getUserService().insertUser(user)).getValue();
		
		// Service
		UserDetailsService userDetailsService = beanFactory.getUserDetailsService();
		
//		TEST: public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		// TODO: UserDetails støttes ikke av Axis2.
//		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//		Assert.notNull(userDetails, "No user found");
//		Assert.isTrue(userDetails.getUsername().equals(username), "User not the same");
	}
}

package no.helsebiblioteket.admin.test.service;

import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class OrganizationServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	private String findMeName = "findMe_" + new Random().nextInt(1000000000);
	public void testAll(){
		testGetOrganizationTypeListAll();
		testGetOrganizationTypeByKey();
		testGetOrganizationListAll();
		testGetOrganizationTypeByKey();
		testGetOrganizationListAll();
		testFindOrganizationsBySearchString();
		testGetOrganizationByListItem();
		testGetOrganizationListByIpAddress();
		testUpdateOrganization();
		testInsertSupplierOrganization();
	}
	@org.junit.Test
	private void testInsertSupplierOrganization() {
		
		
//		TEST: public SingleResultOrganization insertSupplierOrganization(SupplierOrganization supplierOrganization);

		
	}
	@org.junit.Test
	public void testGetOrganizationTypeListAll(){
//		TEST: public ListResultOrganizationType getOrganizationTypeListAll(String DUMMY);
		ListResultOrganizationType list = beanFactory.getOrganizationService().getOrganizationTypeListAll("");
		Assert.isTrue(list.getList().length == 5, "Wrong number of organization types");
	}
	@org.junit.Test
	public void testGetOrganizationTypeByKey(){
//		TEST: public SingleResultOrganizationType getOrganizationTypeByKey(OrganizationTypeKey key);
		SingleResultOrganizationType result = beanFactory.getOrganizationService().getOrganizationTypeByKey(
				OrganizationTypeKey.content_supplier);
		Assert.isTrue(result instanceof ValueResultOrganizationType, "No result");
		Assert.isTrue(((ValueResultOrganizationType)result).getValue().getKey().getValue().equals(
				OrganizationTypeKey.content_supplier.getValue()), "Wrong organization type");
	}
	@org.junit.Test
	public void testGetOrganizationListAll(){
//		TEST: public PageResultOrganizationListItem getOrganizationListAll(PageRequest request);
		int numRes = 5;
		PageRequest request = new PageRequest(0, numRes);
		PageResultOrganizationListItem result = beanFactory.getOrganizationService().getOrganizationListAll(request);
		Assert.isTrue(result.getResult().length >= numRes, "Returned too few");
		Assert.isTrue(result.getResult().length <= numRes, "Returned too many");
	}
	@org.junit.Test
	public void testFindOrganizationsBySearchString(){
		OrganizationService organizationService = beanFactory.getOrganizationService();
		organizationService.insertMemberOrganization(createMemberOrganization());
		PageRequest request = new PageRequest(0, 5);
		String sub = findMeName.substring(3);
//		TEST: public PageResultOrganizationListItem findOrganizationsBySearchString(String searchString, PageRequest request);
		PageResultOrganizationListItem result = organizationService.getOrganizationListBySearchString(request, sub, false);
		Assert.notNull(result, "Null result");
		Assert.notEmpty(result.getResult(), "Empty result");
		Assert.isTrue(result.getResult().length == 1, "Too many organizations");
		Assert.isTrue(result.getResult()[0].getNameEnglish().equals(findMeName), "Not the same");
	}
	@org.junit.Test
	public void testGetOrganizationByListItem(){
		OrganizationService organizationService = beanFactory.getOrganizationService();
		MemberOrganization organization = createMemberOrganization();
		organization = ((ValueResultMemberOrganization)organizationService.insertMemberOrganization(organization)).getValue();
		OrganizationListItem listItem = new OrganizationListItem();
		listItem.setId(organization.getOrganization().getId());
		
//		TEST: public SingleResultOrganization getOrganizationByListItem(OrganizationListItem organizationListItem);
		SingleResultOrganization result = organizationService.getOrganizationByListItem(listItem);
		Assert.notNull(result, "Null result");
		Assert.isTrue(result instanceof ValueResultMemberOrganization, "No result");
		Assert.isTrue(((ValueResultMemberOrganization)result).getValue().getOrganization().getNameEnglish().equals(
				this.findMeName), "Wrong organization");
	}
	public void testGetOrganizationListByIpAddress(){
		String name1 = this.findMeName;
		String name2 = this.findMeName + "_2";

		MemberOrganization organization1 = createMemberOrganization();
		MemberOrganization organization2 = createMemberOrganization();
		organization1.getOrganization().setNameEnglish(name1);
		organization2.getOrganization().setNameEnglish(name2);
		
		String randomIp0 = "" + new Random().nextInt(1000);
		String randomIp1 = "" + new Random().nextInt(1000);
		String randomIp2 = "" + new Random().nextInt(1000);
		
		IpAddress ipAddress = new IpAddress();
		String prefix = randomIp0 + "." + randomIp1 + "." + randomIp2 + ".";
		ipAddress.setAddress(prefix + "027");
		IpAddressRange ipRange = new IpAddressRange();
		ipRange.setIpAddressFrom(new IpAddress(prefix + "025"));
		ipRange.setIpAddressTo(new IpAddress(prefix + "027"));
		IpAddressSingle ipSingle = new IpAddressSingle();
		ipSingle.setIpAddressSingle(ipAddress);

		IpAddressRange[] ipRangeList = new IpAddressRange[1];
		ipRangeList[0] = ipRange;
		IpAddressSingle[] ipSingleList = new IpAddressSingle[1];
		ipSingleList[0] = ipSingle;

		organization1.setIpAddressRangeList(ipRangeList);
		organization2.setIpAddressSingleList(ipSingleList);

		OrganizationService organizationService = beanFactory.getOrganizationService();
//		TEST: public SingleResultOrganization insertOrganization(Organization organization);
		organization1 = ((ValueResultMemberOrganization)organizationService.insertMemberOrganization(
				organization1)).getValue();
		organization2 = ((ValueResultMemberOrganization)organizationService.insertMemberOrganization(
				organization2)).getValue();

//		TEST: public ListResultIpAddressSet addIpAddressRanges(Organization organization, IpAddressRange[] ipAddressRanges);
		IpAddressSet[] resIp1 = organizationService.addIpAddressRanges(
				organization1.getOrganization(), organization1.getIpAddressRangeList()).getList();
//		TEST: public ListResultIpAddressSet addIpAddresses(Organization organization, IpAddressSingle[] ipAddressSingles);
		IpAddressSet[] resIp2 = organizationService.addIpAddresses(
				organization2.getOrganization(), organization2.getIpAddressSingleList()).getList();

//		TEST: public ListResultOrganizationListItem getOrganizationListByIpAdress(IpAddress ipAddress);
		ListResultOrganizationListItem result = organizationService.getOrganizationListByIpAddress(ipAddress);
		
		Assert.notNull(result, "Null result");
		Assert.isTrue(result.getList().length == 2, "Wrong number of results");
		Assert.isTrue(result.getList()[0].getNameEnglish().equals(name1) ||
				result.getList()[1].getNameEnglish().equals(name1), "Name 1 not found");
		Assert.isTrue(result.getList()[0].getNameEnglish().equals(name2) ||
				result.getList()[1].getNameEnglish().equals(name2), "Name 2 not found");

//		TEST: public Boolean deleteIpAddresses(IpAddressSet[] ipAddressSets);
		organizationService.deleteIpAddresses(resIp1);
		organizationService.deleteIpAddresses(resIp2);
	}
	@org.junit.Test
	public void testUpdateOrganization(){
		MemberOrganization organization = createMemberOrganization();
		
		String firstName = this.findMeName + "NO-first";
		String secondName = this.findMeName + "NO-second";
		
		organization.getOrganization().setNameNorwegian(firstName);
		organization.getOrganization().getContactInformation().setPostalLocation(firstName);
		organization.getOrganization().getContactPerson().setFirstName(firstName);
		
		OrganizationService organizationService = beanFactory.getOrganizationService();
		organization = ((ValueResultMemberOrganization)organizationService.insertMemberOrganization(
				organization)).getValue();
		Assert.isTrue(organization.getOrganization().getNameNorwegian().equals(firstName), "Name NO not saved");
		Assert.isTrue(organization.getOrganization().getContactInformation().getPostalLocation().equals(firstName), "Post Loc not saved");
		Assert.isTrue(organization.getOrganization().getContactPerson().getFirstName().equals(firstName), "Contact person not saved");
		
		organization.getOrganization().setNameNorwegian(secondName);
		organization.getOrganization().getContactInformation().setPostalLocation(secondName);
		organization.getOrganization().getContactPerson().setFirstName(secondName);
		
//		TEST: public Boolean updateOrganization(Organization organization);

		organization.setOrganization(((ValueResultOrganization)organizationService.updateOrganization(
				organization.getOrganization())).getValue());

		Assert.isTrue(organization.getOrganization().getNameNorwegian().equals(secondName), "Name NO not updated");
		Assert.isTrue(organization.getOrganization().getContactInformation().getPostalLocation().equals(secondName), "Post Loc not updated");
		Assert.isTrue(organization.getOrganization().getContactPerson().getFirstName().equals(secondName), "Contact person not updated");
	}

	
	private OrganizationType createOrganizationType(){
		return ((ValueResultOrganizationType)beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise)).getValue();
	}
	private Position createPosition(OrganizationType organizationType){
		return ((ValueResultPosition)beanFactory.getUserService().getPositionByKey(PositionTypeKey.ortoptist, organizationType)).getValue();
	}
	private MemberOrganization createMemberOrganization(){
		OrganizationType type = createOrganizationType();
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(
				type, createPosition(type));
		organization.getOrganization().setNameEnglish(findMeName);
		return organization;
	}
}

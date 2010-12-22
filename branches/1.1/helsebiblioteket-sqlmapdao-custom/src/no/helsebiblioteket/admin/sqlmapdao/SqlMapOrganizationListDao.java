package no.helsebiblioteket.admin.sqlmapdao;


import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.OrganizationListDao;
import no.helsebiblioteket.admin.dao.join.OrgUnitNameJoin;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;
import no.helsebiblioteket.admin.sqlmapdao.input.OrganizationTypeInput;
import no.helsebiblioteket.admin.sqlmapdao.input.SearchStringInput;

public class SqlMapOrganizationListDao extends IbatisSqlMapClientDaoSupport implements OrganizationListDao {
	private List<OrganizationListItem> translateList(List<OrgUnitNameJoin> list){
		List<OrganizationListItem> result = new ArrayList<OrganizationListItem>();
		if(list.size()==0){ return result; }
		OrgUnitNameJoin join = list.get(0);
		OrganizationListItem working = init(join);
		insertName(join, working);
		insertIp(join, working);
		boolean firstName = true;
		OrgUnitNameJoin prev = join;
		for (int i=1; i<list.size(); i++) {
			join = list.get(i);
			if( ! prev.getOrgUnitId().equals(join.getOrgUnitId())){
				result.add(working);
				working = init(join);
				insertName(join, working);
				insertIp(join, working);
				firstName = true;
			} else if( ! (prev.getNameCategory().equals(join.getNameCategory())) &&
					prev.getNameLanguage().equals(join.getNameLanguage())){
				insertName(join, working);
				firstName = false;
			} else if(firstName){
				insertIp(join, working);
			}
			prev = join;
		}
		result.add(working);
		return result;
	}
	private void insertIp(OrgUnitNameJoin join, OrganizationListItem working) {
		if(join.getIpAddressFrom() != null && ( ! join.getIpAddressFrom().equals(""))){
			String[] addedFrom = new String[working.getIpAddressesFrom().length + 1];
			String[] addedTo = new String[working.getIpAddressesTo().length + 1];
			for(int i=0;i<addedFrom.length-1;i++){
				addedFrom[i] = working.getIpAddressesFrom()[i];
				addedTo[i] = working.getIpAddressesTo()[i];
			}
			addedFrom[addedFrom.length-1] = join.getIpAddressFrom();
			if(join.getIpAddressFrom().equals(join.getIpAddressTo())){
				addedTo[addedTo.length-1] = "";
			} else {
				addedTo[addedTo.length-1] = join.getIpAddressTo();
			}
			working.setIpAddressesFrom(addedFrom);
			working.setIpAddressesTo(addedTo);
		}
	}
	private OrganizationListItem init(OrgUnitNameJoin join){
		OrganizationListItem working = new OrganizationListItem();
		working.setId(join.getOrgUnitId());
		working.setNameEnglish("");
		working.setNameNorwegian("");
		working.setNameShortEnglish("");
		working.setNameShortNorwegian("");
		
		working.setTypeText(join.getOrganizationTypeDescription());
		working.setTypeKey(join.getOrganizationTypeKey());
		
		working.setIpAddressesFrom(new String[0]);
		working.setIpAddressesTo(new String[0]);
		
		return working;
	}
	private void insertName(OrgUnitNameJoin join, OrganizationListItem working){
		if(join.getNameCategory()==OrganizationNameCategory.NORMAL && join.getNameLanguage()==LanguageCategory.en){
			working.setNameEnglish(join.getOrganizationName());
		} else
		if(join.getNameCategory()==OrganizationNameCategory.NORMAL && join.getNameLanguage()==LanguageCategory.no){
			working.setNameNorwegian(join.getOrganizationName());
		} else
		if(join.getNameCategory()==OrganizationNameCategory.SHORT && join.getNameLanguage()==LanguageCategory.en){
			working.setNameShortEnglish(join.getOrganizationName());
		} else
		if(join.getNameCategory()==OrganizationNameCategory.SHORT && join.getNameLanguage()==LanguageCategory.no){
			working.setNameShortNorwegian(join.getOrganizationName());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationListItem> getOrganizationListPagedSearchString(String searchString, int from, int max) {
		List<Integer> orgUnitIds = getSqlMapClientTemplate().queryForList(
				"getOrganizationIdDistinctSearchString",
				new SearchStringInput("%" + searchString + "%", searchString),  from, max);
		if(orgUnitIds.size()==0){
			return new ArrayList<OrganizationListItem>();
		}
		List<OrgUnitNameJoin> foundOrganizations = getSqlMapClientTemplate().queryForList(
				"getOrganizationListSearchString",
				new SearchStringInput("%" + searchString + "%", searchString, orgUnitIds.get(0), orgUnitIds.get(orgUnitIds.size()-1)));
		return translateList(foundOrganizations);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationListItem> getOrganizationListByIpAddress(IpAddress ipAddress) {
		List<OrgUnitNameJoin> list = getSqlMapClientTemplate().queryForList("getOrganizationListByIpAddress", ipAddress);
		return translateList(list);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationListItem> getOrganizationListByAccessDomain(String accessDomain) {
		List<OrgUnitNameJoin> list = getSqlMapClientTemplate().queryForList("getOrganizationListByAccessDomain", accessDomain);
		return translateList(list);
	}
	@Override
	public Integer getOrganizationNumberSearchString(String searchString) {
		return (Integer) getSqlMapClientTemplate().queryForObject("getOrganizationCountSearchString", new SearchStringInput("%" + searchString + "%", searchString));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationListItem> getOrganizationListByTypes(
			List<OrganizationTypeKey> types, int from, int max) {
		
		List<Integer> orgUnitIds = getSqlMapClientTemplate().queryForList(
				"getOrganizationIdDistinctByTypes",
				new OrganizationTypeInput(translateTypes(types), 0, 0));
		List<OrganizationListItem> someOrganizations =
			new ArrayList<OrganizationListItem>();
		if(orgUnitIds.size()==0){
			return someOrganizations;
		}
		List<OrgUnitNameJoin> foundOrganizations = getSqlMapClientTemplate().queryForList(
				"getOrganizationListByTypes",
				new OrganizationTypeInput(translateTypes(types),
						orgUnitIds.get(0), orgUnitIds.get(orgUnitIds.size()-1)));
		return translateList(foundOrganizations);
	}
	@Override
	public Integer getOrganizationNumberByTypes(List<OrganizationTypeKey> types) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"getOrganizationCountByTypes",
				new OrganizationTypeInput(translateTypes(types), 0, 0));
	}
	private List<String> translateTypes(List<OrganizationTypeKey> types){
		List<String> result = new ArrayList<String>();
		for (OrganizationTypeKey key : types) {
			result.add(key.getValue());
		}
		return result;
	}
}

package no.helsebiblioteket.admin.sqlmapdao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.OrganizationListDao;
import no.helsebiblioteket.admin.dao.join.OrgUnitNameJoin;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.sqlmapdao.input.SearchStringInput;

public class SqlMapOrganizationListDao extends SqlMapClientDaoSupport implements OrganizationListDao {
	private List<OrganizationListItem> translateList(List<OrgUnitNameJoin> list){
		List<OrganizationListItem> result = new ArrayList<OrganizationListItem>();
		if(list.size()==0){ return result; }
		OrgUnitNameJoin join = list.get(0);
		OrganizationListItem working = init(join);
		insertName(join, working);
		for (int i=1; i<list.size(); i++) {
			join = list.get(i);
			if( ! working.getId().equals(join.getOrgUnitId())){
				result.add(working);
				working = init(join);
			}
			insertName(join, working);
		}
		result.add(working);
		return result;
	}
	private OrganizationListItem init(OrgUnitNameJoin join){
		OrganizationListItem working = new OrganizationListItem();
		working.setId(join.getOrgUnitId());
		working.setKey(join.getOrganizationTypeKey());
		working.setNameEnglish("");
		working.setNameNorwegian("");
		working.setNameShortEnglish("");
		working.setNameShortNorwegian("");
		return working;
	}
	private void insertName(OrgUnitNameJoin join, OrganizationListItem working){
		if(join.getNameCategory()==OrganizationNameCategory.NORMAL && join.getNameLanguage()==LanguageCategory.en){
			working.setNameEnglish(join.getOrganizationName());
		}
		if(join.getNameCategory()==OrganizationNameCategory.NORMAL && join.getNameLanguage()==LanguageCategory.no){
			working.setNameNorwegian(join.getOrganizationName());
		}
		if(join.getNameCategory()==OrganizationNameCategory.SHORT && join.getNameLanguage()==LanguageCategory.en){
			working.setNameShortEnglish(join.getOrganizationName());
		}
		if(join.getNameCategory()==OrganizationNameCategory.SHORT && join.getNameLanguage()==LanguageCategory.no){
			working.setNameShortNorwegian(join.getOrganizationName());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationListItem> getOrganizationListPagedSearchString(String searchString, int from, int max) {
		// TODO: Pages will be of different sizes!
		List<Integer> orgUnitIds = getSqlMapClientTemplate().queryForList("getOrganizationIdDistinctSearchString",
				"%" + searchString + "%", from, max);
		List<OrganizationListItem> someOrganizations = new ArrayList<OrganizationListItem>();
		if(orgUnitIds.size()==0){
			return someOrganizations;
		}
		List<OrgUnitNameJoin> foundOrganizations = getSqlMapClientTemplate().queryForList("getOrganizationListSearchString",
				new SearchStringInput("%" + searchString + "%", orgUnitIds.get(0), orgUnitIds.get(orgUnitIds.size()-1)));
		return translateList(foundOrganizations);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationListItem> getOrganizationListByIpAddress(IpAddress ipAddress) {
		List<OrgUnitNameJoin> list = getSqlMapClientTemplate().queryForList("getOrganizationListByIpAddress", ipAddress);
		return translateList(list);
	}
}

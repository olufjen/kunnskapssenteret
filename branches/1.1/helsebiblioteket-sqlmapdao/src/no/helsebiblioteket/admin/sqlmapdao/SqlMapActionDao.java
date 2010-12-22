package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.ActionDao;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.line.ActionLine;

public class SqlMapActionDao extends SqlMapClientDaoSupport implements ActionDao {
	@Override
	public void insertAction(ActionLine actionLine) {
		getSqlMapClientTemplate().insert("insertAction", actionLine);
		ActionLine tmp = (ActionLine) getSqlMapClientTemplate().queryForObject("getActionLineByActionId", actionLine.getId());
		actionLine.setActionPerformed(tmp.getActionPerformed());
	}
	@Override
	public void updateAction(ActionLine actionLine) {
		getSqlMapClientTemplate().update("updateAction", actionLine);
		ActionLine tmp = (ActionLine) getSqlMapClientTemplate().queryForObject("getActionLineByActionId", actionLine.getId());
		actionLine.setActionPerformed(tmp.getActionPerformed());
	}
	@Override
	public void deleteAction(ActionLine actionLine) {
		getSqlMapClientTemplate().delete("deleteAction", actionLine);
	}
	@Override
	public ActionLine getActionLineById(Integer id) {
		return (ActionLine) getSqlMapClientTemplate().queryForObject("getActionLineByActionId", id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ActionLine> getActionListByUser(User user) {
		return getSqlMapClientTemplate().queryForList("getActionListByUserId", user.getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ActionLine> getActionListByOrganization(Organization organization) {
		return getSqlMapClientTemplate().queryForList("getActionListByOrganizationId", organization.getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ActionLine> getActionListByResource(Resource resource) {
		return getSqlMapClientTemplate().queryForList("getActionListByResourceId", resource.getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ActionLine> getActionListByAccessType(AccessType accessType) {
		return getSqlMapClientTemplate().queryForList("getActionListByAccessTypeId", accessType.getId());
	}
}

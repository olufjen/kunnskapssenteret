package no.helsebiblioteket.admin.sqlmapdao;


import java.util.List;

import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.UserExportDao;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.parameter.UserExportParameter;

public class SqlMapUserExportDao extends IbatisSqlMapClientDaoSupport implements UserExportDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserExportList(UserExportParameter parameter) {
		return (List<User>) getSqlMapClientTemplate().queryForList("getUserExportList", parameter);
	}
}
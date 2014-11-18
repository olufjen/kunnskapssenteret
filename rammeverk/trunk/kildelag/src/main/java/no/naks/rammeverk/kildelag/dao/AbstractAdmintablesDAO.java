package no.naks.rammeverk.kildelag.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * This is the abstract base class for all DAO service objects.
 * Through its superclass it provides for the necessary DataSource interface.
 *  @author ojn
 *
 */
public abstract class AbstractAdmintablesDAO extends JdbcDaoSupport {
	private static Log log = LogFactory.getLog(AbstractAdmintablesDAO.class);
	protected void initDao() throws Exception {
		super.initDao();
	}
	protected Long getPrimaryKey(String sql,String[]tabledef) {
		PrimarykeySelect primaryKey = new PrimarykeySelect(getDataSource(),sql,tabledef);
		List primaryKeys = new ArrayList();
		try {
			primaryKeys = primaryKey.execute();
			}catch (DataAccessException de) {
				log.info(de.getMessage());
			}
		Long id = (Long)primaryKeys.get(0);
		return id;
	}
}

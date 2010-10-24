/**
 * Utilities for faking Spring after Spring amputation
 */
package no.helsebiblioteket.admin.sqlmapdao.ibatissupport;

import javax.sql.DataSource;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.servlet.http.HttpServletRequest;

public abstract class IbatisSqlMapClientDaoSupport {

    private IbatisSqlMapClientTemplate sqlMapClientTemplate;

    public IbatisSqlMapClientDaoSupport() {
        super();
       // ClassLoader cl = Thread.currentThread().getContextClassLoader();
       // Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        sqlMapClientTemplate = new IbatisSqlMapClientTemplate();
       // Thread.currentThread().setContextClassLoader(cl);
    }

    /**
     * Return the JDBC DataSource used by this DAO.
     */
    public final DataSource getDataSource() {
        return (this.sqlMapClientTemplate != null ? this.sqlMapClientTemplate.getDataSource() : null);
    }

    /**
     * Return the iBATIS Database Layer SqlMapClient that this template works with.
     */
    public final SqlMapClient getSqlMapClient() {
        return this.sqlMapClientTemplate.getSqlMapClient();
    }

    /**
     * Set the SqlMapClientTemplate for this DAO explicitly,
     * as an alternative to specifying a SqlMapClient.
     * @see #setSqlMapClient
     */
    public final void setSqlMapClientTemplate(IbatisSqlMapClientTemplate sqlMapClientTemplate) {
        if (sqlMapClientTemplate == null) {
            throw new IllegalArgumentException("Cannot set sqlMapClientTemplate to null");
        }
        this.sqlMapClientTemplate = sqlMapClientTemplate;
    }

    /**
     * Return the SqlMapClientTemplate for this DAO,
     * pre-initialized with the SqlMapClient or set explicitly.
     */
    public final IbatisSqlMapClientTemplate getSqlMapClientTemplate() {
        return sqlMapClientTemplate;
    }
}

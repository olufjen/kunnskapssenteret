/**
 * Utilities for faking Spring after Spring amputation
 */

package no.helsebiblioteket.admin.sqlmapdao.ibatissupport;

import com.ibatis.common.resources.Resources;
import com.ibatis.common.util.PaginatedList;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.ibatis.sqlmap.client.event.RowHandler;
import com.ibatis.sqlmap.engine.execution.BatchException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

public class SqlMapClientTemplate implements SqlMapClient {

    private static final SqlMapClient sqlMap;

    static {
        try {
            String resource = "META-INF/ibatis/sqlMap-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing ibatis.", e);
        }
    }

    public static SqlMapClient getSqlMapInstance() {
        return sqlMap;
    }

    public SqlMapClient getSqlMapClient() {
        return SqlMapClientTemplate.getSqlMapInstance();
    }

    public SqlMapSession openSession() {
        return sqlMap.openSession();
    }

    public SqlMapSession openSession(Connection cnctn) {
        return sqlMap.openSession(cnctn);
    }

    public SqlMapSession getSession() {
        return sqlMap.getSession();
    }

    public void flushDataCache() {
        sqlMap.flushDataCache();
    }

    public void flushDataCache(String string) {
        sqlMap.flushDataCache(string);
    }

    public Object insert(String string, Object o) throws RuntimeSQLException {
        try {
            return sqlMap.insert(string, o);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public Object insert(String string) throws RuntimeSQLException {
        try {
            return sqlMap.insert(string);

        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public int update(String string, Object o) throws RuntimeSQLException {
        try {
            return sqlMap.update(string, o);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public int update(String string) throws RuntimeSQLException {
        try {
            return sqlMap.update(string);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public int delete(String string, Object o) throws RuntimeSQLException {
        try {
            return sqlMap.delete(string, o);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public int delete(String string) throws RuntimeSQLException {
        try {
            return sqlMap.delete(string);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public Object queryForObject(String string, Object o) throws RuntimeSQLException {
        try {
            return sqlMap.queryForObject(string, o);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public Object queryForObject(String string) throws RuntimeSQLException {
        try {
            return sqlMap.queryForObject(string);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public Object queryForObject(String string, Object o, Object o1) throws RuntimeSQLException {
        try {
            return sqlMap.queryForObject(string, o, o1);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public List queryForList(String string, Object o) throws RuntimeSQLException {
        try {
            return sqlMap.queryForList(string, o);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public List queryForList(String string) {
        try {
            return sqlMap.queryForList(string);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public List queryForList(String string, Object o, int i, int i1) throws RuntimeSQLException {
        try {
            return sqlMap.queryForList(string, o, i, i1);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public List queryForList(String string, int i, int i1) throws RuntimeSQLException {
        try {
            return sqlMap.queryForList(string, i, i1);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public void queryWithRowHandler(String string, Object o, RowHandler rh) throws RuntimeSQLException {
        try {
            sqlMap.queryWithRowHandler(string, o, rh);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public void queryWithRowHandler(String string, RowHandler rh) throws RuntimeSQLException {
        try {
            sqlMap.queryWithRowHandler(string, rh);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public PaginatedList queryForPaginatedList(String string, Object o, int i) throws RuntimeSQLException {
        try {
            return sqlMap.queryForPaginatedList(string, o, i);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public PaginatedList queryForPaginatedList(String string, int i) throws RuntimeSQLException {
        try {
            return sqlMap.queryForPaginatedList(string, i);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public Map queryForMap(String string, Object o, String string1) throws RuntimeSQLException {
        try {
            return sqlMap.queryForMap(string, o, string1);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public Map queryForMap(String string, Object o, String string1, String string2) throws RuntimeSQLException {
        try {
            return sqlMap.queryForMap(string, o, string1, string2);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public void startBatch() throws SQLException {
        try {
            sqlMap.startBatch();
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public int executeBatch() throws SQLException {
        try {
            return sqlMap.executeBatch();
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public List executeBatchDetailed() throws SQLException, BatchException {
        try {
            return sqlMap.executeBatchDetailed();
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public void startTransaction() throws SQLException {
        try {
            sqlMap.startTransaction();
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public void startTransaction(int i) throws SQLException {
        try {
            sqlMap.startTransaction(i);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public void commitTransaction() throws SQLException {
        try {
            sqlMap.commitTransaction();
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public void endTransaction() throws SQLException {
        try {
            sqlMap.endTransaction();
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public void setUserConnection(Connection cnctn) throws SQLException {
        try {
            sqlMap.setUserConnection(cnctn);
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public Connection getUserConnection() throws SQLException {
        try {
            return sqlMap.getUserConnection();
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public Connection getCurrentConnection() throws SQLException {
        try {
            return sqlMap.getCurrentConnection();
        } catch (SQLException sex) {
            throw new RuntimeSQLException("SQL error performing ibatis operation.", sex);
        }
    }

    public DataSource getDataSource() {

        return sqlMap.getDataSource();

    }
}

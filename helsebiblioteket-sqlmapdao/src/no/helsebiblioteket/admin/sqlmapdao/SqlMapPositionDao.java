package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;

public class SqlMapPositionDao extends SqlMapClientDaoSupport implements PositionDao {
	public void insertPosition(Position position) {
		getSqlMapClientTemplate().insert("insertPosition", position);
	}
	public void updatePosition(Position position) {
		getSqlMapClientTemplate().update("updatePosition", position);
	}
	public void deletePosition(Position position) {
		getSqlMapClientTemplate().delete("deletePosition", position);
	}
	public Position getPositionByKey(PositionTypeKey key) {
		return (Position) getSqlMapClientTemplate().queryForObject("getPositionByKey", key.toString());
	}
	@SuppressWarnings("unchecked")
	public List<Position> getPositionListAll() {
		return (List<Position>)getSqlMapClientTemplate().queryForList("getPositionListAll");
	}
}

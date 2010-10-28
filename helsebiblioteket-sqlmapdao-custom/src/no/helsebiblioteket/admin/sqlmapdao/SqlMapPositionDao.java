package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;

public class SqlMapPositionDao extends IbatisSqlMapClientDaoSupport implements PositionDao {
	@Override
	public void insertPosition(Position position) {
		getSqlMapClientTemplate().insert("insertPosition", position);
	}
	@Override
	public void updatePosition(Position position) {
		getSqlMapClientTemplate().update("updatePosition", position);
	}
	@Override
	public void deletePosition(Position position) {
		getSqlMapClientTemplate().delete("deletePosition", position);
	}
	@Override
	public Position getPositionByKey(PositionTypeKey key) {
		return (Position) getSqlMapClientTemplate().queryForObject("getPositionByKey", key.toString());
	}
	@Override
	public Position getPositionById(Integer id) {
		return (Position) getSqlMapClientTemplate().queryForObject("getPositionById", id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Position> getPositionListAll() {
		return (List<Position>)getSqlMapClientTemplate().queryForList("getPositionListAll");
	}
}

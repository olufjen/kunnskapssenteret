package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcPositionDao extends SimpleJdbcDaoSupport implements PositionDao {
	
	
	
	protected final Log logger = LogFactory.getLog(getClass());
	public List<Position> getAllPositions() {
        logger.info("fetching all roles");
        List<Position> positions = getSimpleJdbcTemplate().query(
                "select descr, name, key from tbl_position_type_reg", 
                new PositionMapper());
        return positions;
	}
    private static class PositionMapper implements ParameterizedRowMapper<Position> {
        public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Position position = new Position();
        	position.setDescription(rs.getString("descr"));
//        	position.setKey(rs.getString("key"));
        	position.setName(rs.getString("name"));
            return position;
        }
    }
	@Override
	public void deletePosition(Position position) {
		
	}
	@Override
	public List<Position> getPositionListAll() {
		return null;
	}
	@Override
	public void insertPosition(Position position) {
		
	}
	@Override
	public void updatePosition(Position position) {
		
	}
	@Override
	public Position getPositionByKey(PositionTypeKey key) {
		return null;
	}
	@Override
	public Position getPositionById(Integer id) {
		return null;
	}
}

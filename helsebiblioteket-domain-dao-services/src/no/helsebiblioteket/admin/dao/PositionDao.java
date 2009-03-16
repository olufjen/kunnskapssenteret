package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;

public interface PositionDao {
	// Edit
	public void insertPosition(Position position);
	public void updatePosition(Position position);
	public void deletePosition(Position position);
	
	// Fetch
	public Position getPositionByKey(PositionTypeKey positionTypeKey);
	public Position getPositionById(Integer id);

	public List<Position> getPositionListAll();
}
package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Position;

public interface PositionDao {
	// Edit
	public void insertPosition(Position position);
	public void updatePosition(Position position);
	public void deletePosition(Position position);
	
	// Fetch
	public List<Position> getPositionListAll();
}

package no.helsebiblioteket.admin.test.dao;

import java.util.List;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.test.BeanFactory;

public class PositionDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testPosition(){
		// We do not test insertPosition, updatePosition, deletePosition.
		// They are not in use.
		PositionDao positionDao = beanFactory.getPositionDao();
		Position position = positionDao.getPositionByKey(PositionTypeKey.ambulansearbeider);
		Assert.notNull(position, "ambulansearbeider missing");
		List<Position> list = positionDao.getPositionListAll();
		Assert.isTrue(list.size()>=29, "Not all positions in database");
	}
}

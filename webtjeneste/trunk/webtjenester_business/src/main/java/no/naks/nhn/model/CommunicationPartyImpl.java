package no.naks.nhn.model;

import java.sql.Types;

public class CommunicationPartyImpl extends AbstractCommunicationParty {

	public CommunicationPartyImpl() {
		super();
		types = new int[] {Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
		utypes = new int[] {Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER};

	}

}

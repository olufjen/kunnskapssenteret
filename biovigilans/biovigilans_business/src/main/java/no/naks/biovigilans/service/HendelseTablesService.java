package no.naks.biovigilans.service;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.biovigilans.dao.HendelsehemovigilansDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;



/**
 * Dette grensesnittet håndterer hendelser
 *
 */
public interface HendelseTablesService {
	
	public HendelsehemovigilansDAO getHendelsehemovigilansDAO();

	public void setHendelsehemovigilansDAO(
			HendelsehemovigilansDAO hendelsehemovigilansDAO);


}

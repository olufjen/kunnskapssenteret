package no.naks.biovigilans.service;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.biovigilans.dao.HendelsehemovigilansDAO;
import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;



/**
 * Dette grensesnittet håndterer lagring/oppdatering av Vigilansmeldinger
 *
 */
public interface HendelseTablesService {
	
	public HendelsehemovigilansDAO getHendelsehemovigilansDAO();

	public void setHendelsehemovigilansDAO(
			HendelsehemovigilansDAO hendelsehemovigilansDAO);

	public void saveVigilansmelding(Vigilansmelding melding);
}

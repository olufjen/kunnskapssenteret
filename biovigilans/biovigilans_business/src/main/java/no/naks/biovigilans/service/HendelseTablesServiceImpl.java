package no.naks.biovigilans.service;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.biovigilans.dao.HendelsehemovigilansDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;


/**
 * Denne klassen er en implementasjon av klassen HendelseTablesService
 * 
 * Følgende tabeller blir berørt i db:
 * 		
 **/
public class HendelseTablesServiceImpl implements HendelseTablesService {
	
	private HendelsehemovigilansDAO hendelseBiovigilansDAO;

	public HendelsehemovigilansDAO getInnmeldtProesdyreDAO() {
		return hendelseBiovigilansDAO;
	}

	public void setInnmeldtProesdyreDAO(HendelsehemovigilansDAO hendelseBiovigilansDAO) {
		this.hendelseBiovigilansDAO = hendelseBiovigilansDAO;
	}
	
	

}
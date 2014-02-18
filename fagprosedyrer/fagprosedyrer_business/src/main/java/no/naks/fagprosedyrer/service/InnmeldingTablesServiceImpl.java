package no.naks.fagprosedyrer.service;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.fagprosedyrer.dao.InnmeldtprosedyreDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;


/**
 * Denne klassen er en implementasjon av klassen InnmeldingTablesService
 * 
 * Følgende tabeller blir berørt i db:
 * 		
 **/
public class InnmeldingTablesServiceImpl implements InnmeldingTablesService {
	
	private InnmeldtprosedyreDAO innmeldtProesdyreDAO;

	public InnmeldtprosedyreDAO getInnmeldtProesdyreDAO() {
		return innmeldtProesdyreDAO;
	}

	public void setInnmeldtProesdyreDAO(InnmeldtprosedyreDAO innmeldtProesdyreDAO) {
		this.innmeldtProesdyreDAO = innmeldtProesdyreDAO;
	}
	
	

}
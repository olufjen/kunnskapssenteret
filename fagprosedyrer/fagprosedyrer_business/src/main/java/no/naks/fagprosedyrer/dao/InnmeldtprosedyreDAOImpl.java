package no.naks.fagprosedyrer.dao;

import java.sql.Array;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import no.naks.rammeverk.kildelag.dao.FunctionExec;
import no.naks.rammeverk.kildelag.dao.FunctionExecImpl;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;
import no.naks.rammeverk.kildelag.model.LineKey;
import no.naks.rammeverk.kildelag.model.LineKeyImpl;
import no.naks.rammeverk.kildelag.dao.LineKeySelect;


/**
 * Denne klassen lagrer og henter innmeldte prosedyrer til databasen 
 * @author ojn 
 *
 */
public class InnmeldtprosedyreDAOImpl extends AbstractAdmintablesDAO implements InnmeldtprosedyreDAO {

	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	private String insertQuestionareSQL;
	private String updateQuestionareSQL;

	private String selectQuestionareSQL;

	private String[] sporsmallinjeTableDefs; 
	private String[] subjectTableDefs;
	private String[] indexTableDefs;

	private Tablesupdate tablesUpdate = null;
;
	private InnmeldtprosedyreSelect questionSelect = null;



	


}

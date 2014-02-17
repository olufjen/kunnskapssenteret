package no.naks.skjemabank.service;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.skjemabank.model.Division;
import no.naks.skjemabank.model.Enterprise;
import no.naks.skjemabank.model.Examination;
import no.naks.skjemabank.model.ExaminationOwner;
import no.naks.skjemabank.model.Index;
import no.naks.skjemabank.model.Informant;
import no.naks.skjemabank.model.InputType;
import no.naks.skjemabank.model.InstitutionType;
import no.naks.skjemabank.model.LiteraryReference;
import no.naks.skjemabank.model.Owner;
import no.naks.skjemabank.model.PatientGroup;
import no.naks.skjemabank.model.ExaminationType;
import no.naks.skjemabank.model.QuestionareAccount;
import no.naks.skjemabank.model.Region;
import no.naks.skjemabank.model.Subject;
import no.naks.skjemabank.model.SubjectLine;
import no.naks.skjemabank.model.User;


/**
 * Denne klassen er et grensesnitt som lister opp alle tjenester/rutiner for
 * følgende tabeller i db:
 * 		- PatientGruoup
 * 		- InputType
 *
 */
public interface QuestionTablesService {
	
	public List collectInputType();
	public void saveInputType(InputType inputType);
	
	
	public List collectExaminationOwners();
	public void saveExaminationOwner(ExaminationOwner exam);
	
	public List collectInstitutionTypes();
	public void saveInstitutionType(InstitutionType institut);
	
	public List collectInformants();
	public void saveInformant(Informant informant);
	
	public List collectSubjects(Long skjemaId);
	public List collectAllSubjects();
	
	public void saveSubject(Subject subject);
	public void updateSubjectline(SubjectLine subjectLine);
	public void insertSubjectline(SubjectLine subjectLine);
	public void deleteSubjectline(Long questionId);
	public void deleteSubjectlineSubject(Long sId);
	public void deleteSubject(Long subjectId);
	public void deleteNokkelordskjemalinje(Long informantid);
	public List collectOwners();
	public void saveOwner(Owner owner);
	
	public List collectUsers();
	public void saveUser(User user);
	
	public List collectregions();
	public void saveRegion(Region reg);
	
	public List collectdivisions();
	public void saveDivision(Division div);
	
	public List collectEnterprises();
	public void saveEnterprise(Enterprise enterprise);
	
	public List collectExaminationList();
	public void saveExamination(Examination examination);
	
	public List collectIndexList();
	public void saveIndex(Index index);
	
	public List collectQuestionareAccountList();
	public void saveQuestionareAccount(QuestionareAccount questionareAccount );
	
	public List collectLiteraryReferenceList();
	public void saveLiteraryReference(LiteraryReference literaryReference);

	public List getSubjectlineforSubjects(Long qId);
	public List collectNokkelord(Long qId);
}

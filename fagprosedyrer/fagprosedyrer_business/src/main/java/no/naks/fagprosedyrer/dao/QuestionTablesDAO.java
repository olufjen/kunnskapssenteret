/**
 * 
 */
package no.naks.skjemabank.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;


import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;
import no.naks.skjemabank.model.Division;
import no.naks.skjemabank.model.Enterprise;
import no.naks.skjemabank.model.Examination;
import no.naks.skjemabank.model.ExaminationOwner;
import no.naks.skjemabank.model.ExaminationType;
import no.naks.skjemabank.model.Index;
import no.naks.skjemabank.model.Informant;
import no.naks.skjemabank.model.InputType;
import no.naks.skjemabank.model.InstitutionType;
import no.naks.skjemabank.model.LiteraryReference;
import no.naks.skjemabank.model.Owner;
import no.naks.skjemabank.model.PatientGroup;
import no.naks.skjemabank.model.QuestionareAccount;
import no.naks.skjemabank.model.Region;
import no.naks.skjemabank.model.Subject;
import no.naks.skjemabank.model.SubjectLine;
import no.naks.skjemabank.model.User;

/**
 * @author ojn
 * Interface for administrative tables in Questionare database
 * 
 * Berører følgende tabeller i db:
 * 		- PatientGroup
 * 		- InputType
 *		-undersokelseseier
 *		-undersokelsestype
 *		-informant
 *		-institusjonstype
 *		-eier av sporreskjemaet
 */

public interface QuestionTablesDAO {
	
	
//---------------------------------------------------------------------------------
	public String[] getPrimaryKeyTablesDefs();

	public void setPrimaryKeyTablesDefs(String[] primaryKeyTablesDefs);

	public Tablesupdate getTablesUpdate();

	public void setTablesUpdate(Tablesupdate tablesUpdate);

//---------------------------------------	
		
	public InputTypeSelect getInputTypeSelect();

	public void setInputTypeSelect(InputTypeSelect inputTypeSelect);

	public String[] getInputTypeTableDefs();

	public void setInputTypeTableDefs(String[] inputTypeTableDefs);

	public String getInsertInputTypeSQL();

	public void setInsertInputTypeSQL(String insertInputTypeSQL);

	public String getPrimaryKeyInputTypeSQL();

	public void setPrimaryKeyInputTypeSQL(String primaryKeyInputTypeSQL);

	public String getSelectInputTypeSQL();

	public void setSelectInputTypeSQL(String selectInputTypeSQL);

	public String getUpdateInputTypeSQL();

	public void setUpdateInputTypeSQL(String updateInputTypeSQL);
		
	public List collectInputType();
	
	public void saveInputType (InputType inputType);
	
	
//-----------------------------------
	public String getInsertExaminationOwnerSQL();

	public void setInsertExaminationOwnerSQL(String insertExaminationOwnerSQL);

	public ExaminationOwnerSelect getExaminationOwnerSelect();

	public void setExaminationOwnerSelect(ExaminationOwnerSelect examinationOwnerSelect);

	public String[] getExaminationOwnerTableDefs();

	public void setExaminationOwnerTableDefs(String[] examinationOwnerTableDefs);

	public String getPrimaryKeyExaminationOwnerSQL();

	public void setPrimaryKeyExaminationOwnerSQL(String primaryKeyExaminationOwnerSQL);
	
	public void setUpdateExaminationOwnerSQL(String updateExaminationOwnerSQL);
	
	public List collectExaminationOwners();
	
	public void saveExaminationOwner(ExaminationOwner examination);

	public String getSelectExaminationOwnerSQL();

	public void setSelectExaminationOwnerSQL(String selectExaminationOwnerSQL);
	
	public String getUpdateExaminationOwnerSQL();
	
	//--------------------------- the methods that will be implemeted in the daoimpl
	
	public String getInsertInstitutionTypeSQL();

	public void setInsertInstitutionTypeSQL(String insertInstitutionTypeSQL);

	public InstitutionTypeSelect getInstitutionTypeSelect();

	public void setInstitutionTypeSelect(InstitutionTypeSelect iinstitutionTypeSelect);

	public String[] getInstitutionTypeTableDefs();

	public void setInstitutionTypeTableDefs(String[] institutionTypeTableDefs);

	public String getPrimaryKeyInstitutionTypeSQL();

	public void setPrimaryKeyInstitutionTypeSQL(String primaryKeyInstitutionTypeSQL);
	
	public void setUpdateInstitutionTypeSQL(String updateInstitutionTypeSQL);
	
	public String getUpdateInstitutionTypeSQL(); 
	
	public List collectInstitutionTypes();
	
	public void saveInstitutionType(InstitutionType nstitutionType);

	public String getSelectInstitutionTypeSQL();

	public void setSelectInstitutionTypeSQL(String selectInstitutionTypeSQL);
	
	// the methods that the interface requer
	
	public String getInsertInformantSQL();

	public void setInsertInformantSQL(String insertInformantSQL);

	public InformantSelect getInformantSelect();

	public void setInformantSelect(InformantSelect informantSelect);

	public String[] getInformantTableDefs();

	public void setInformantTableDefs(String[] informantTableDefs);

	public String getPrimaryKeyInformantSQL();

	public void setPrimaryKeyInformantSQL(String primaryKeyInformantSQL);
	
	public void setUpdateInformantSQL(String updateInformantSQL);
	
	public String getUpdateInformantSQL(); 
	
	public List collectInformants();
	
	public void saveInformant(Informant informant);

	public String getSelectInformantSQL();

	public void setSelectInformantSQL(String selectInformantSQL);
	
	//---------------the owners of the questionarekjema----------------
	
	public String getInsertOwnerSQL();

	public void setInsertOwnerSQL(String insertOwnerSQL);

	public OwnerSelect getOwnerSelect();

	public void setOwnerSelect(OwnerSelect ownerSelect);

	public String[] getOwnerTableDefs();

	public void setOwnerTableDefs(String[] ownerTableDefs);

	public String getPrimaryKeyOwnerSQL();

	public void setPrimaryKeyOwnerSQL(String primaryKeyOwnerSQL);
	
	public void setUpdateOwnerSQL(String updateOwnerSQL);
	
	public String getUpdateOwnerSQL(); 
	
	public List collectOwners();
	
	public void saveOwner(Owner owner);

	public String getSelectOwnerSQL();

	public void setSelectOwnerSQL(String selectOwnerSQL);
	
	//----------------------------subject getter and setter services fromDAO
	
	public String getInsertSubjectSQL();

	public void setInsertSubjectSQL(String insertSubjectSQL);

	public SubjectSelect getSubjectSelect();

	public void setSubjectSelect(SubjectSelect subjectSelect);

	public String[] getSubjectTableDefs();

	public void setSubjectTableDefs(String[] subjectTableDefs);

	public String getPrimaryKeySubjectSQL();

	public void setPrimaryKeySubjectSQL(String primaryKeySubjectSQL);
	
	public void setUpdateSubjectSQL(String updateSubjectSQL);
	
	public String getUpdateSubjectSQL(); 
	
	public List collectSubjects(Long skjemaId);
	public List collectAllSubjects();
	
	public void saveSubject(Subject subject);
	public void updateSubjectline(SubjectLine subjectLine);
	public void insertSubjectline(SubjectLine subjectLine);
	public String getSelectSubjectSQL();
	public void deleteSubjectline(Long questionId);
	public void deleteSubjectlineSubject(Long sId);
	public void deleteSubject(Long subjectId);
	public void deleteNokkelordskjemalinje(Long informantid);

	public void setSelectSubjectSQL(String selectSubjectSQL);
	
	//------------------------bruker Dao tjenester
	
	public String getInsertUserSQL();

	public void setInsertUserSQL(String insertUserSQL);

	public UserSelect getUserSelect();

	public void setUserSelect(UserSelect userSelect);

	public String[] getUserTableDefs();

	public void setUserTableDefs(String[] userTableDefs);

	public String getPrimaryKeyUserSQL();

	public void setPrimaryKeyUserSQL(String primaryKeyUserSQL);
	
	public void setUpdateUserSQL(String updateUserSQL);
	
	public String getUpdateUserSQL(); 
	
	public List collectUsers();
	
	public void saveUser(User user);

	public String getSelectUserSQL();

	public void setSelectUserSQL(String selectUserSQL);
	
	//----the services that the object region gives to the entire enviromment --
	
	public String getInsertRegionSQL();

	public void setInsertRegionSQL(String insertRegionSQL);

	public RegionSelect getRegionSelect();

	public void setRegionSelect(RegionSelect regionSelect);

	public String[] getRegionTableDefs();

	public void setRegionTableDefs(String[] regionTableDefs);

	public String getPrimaryKeyRegionSQL();

	public void setPrimaryKeyRegionSQL(String primaryKeyRegionSQL);
	
	public void setUpdateRegionSQL(String updateRegionSQL);
	
	public String getUpdateRegionSQL(); 
	
	public List collectregions();
	
	public void saveRegion(Region reg);

	public String getSelectRegionSQL();

	public void setSelectRegionSQL(String selectRegionSQL);
	
	//------------the services that the object division gives to the entire enviromment
	
	public String getInsertDivisionSQL();

	public void setInsertDivisionSQL(String insertDivisionSQL);

	public DivisionSelect getDivisionSelect();

	public void setDivisionSelect(DivisionSelect divSelect);

	public String[] getDivisionTableDefs();

	public void setDivisionTableDefs(String[] divisionTableDefs);

	public String getPrimaryKeyDivisionSQL();

	public void setPrimaryKeyDivisionSQL(String primaryKeyDivisionSQL);
	
	public void setUpdateDivisionSQL(String updateDivisionSQL);
	
	public String getUpdateDivisionSQL(); 
	
	public List collectdivisions();
	
	public void saveDivision(Division div);

	public String getSelectDivisionSQL();

	public void setSelectDivisionSQL(String selectDivisionSQL);

	//-------------the servises for the EnterPrise 
	
	public String getInsertEnterpriseSQL();

	public void setInsertEnterpriseSQL(String insertEnterpriseSQL);

	public EnterpriseSelect getEnterpriseSelect();

	public void setEnterpriseSelect(EnterpriseSelect enterpriseSelect);

	public String[] getEnterpriseTableDefs();

	public void setEnterpriseTableDefs(String[] enterpriseTableDefs);

	public String getPrimaryKeyEnterpriseSQL();

	public void setPrimaryKeyEnterpriseSQL(String primaryKeyEnterpriseSQL);
	
	public void setUpdateEnterpriseSQL(String updateEnterpriseSQL);
	
	public String getUpdateEnterpriseSQL(); 
	
	public List collectEnterprises();
	
	public void saveEnterprise(Enterprise enterprise);

	public String getSelectEnterpriseSQL();

	public void setSelectEnterpriseSQL(String selectEnterpriseSQL);
	
	//-------------------------------the table examination----------------
	
	public String getInsertExaminationSQL();

	public void setInsertExaminationSQL(String insertExaminationSQL);

	public ExaminationSelect getExaminationSelect();

	public void setExaminationSelect(ExaminationSelect examinationSelect);

	public String[] getExaminationTableDefs();

	public void setExaminationTableDefs(String[] examinationTableDefs);

	public String getPrimaryKeyExaminationSQL();

	public void setPrimaryKeyExaminationSQL(String primaryKeyExaminationSQL);
	
	public void setUpdateExaminationSQL(String updateExaminationSQL);
	
	public String getUpdateExaminationSQL(); 
	
	public List collectExaminationList();
	
	public void saveExamination(Examination examination);

	public String getSelectExaminationSQL();

	public void setSelectExaminationSQL(String selectExaminationSQL);
	
	//------------------------------the table Index---------------------------
	
	public String getInsertIndexSQL();

	public void setInsertIndexSQL(String insertIndexSQL);

	public IndexSelect getIndexSelect();

	public void setIndexSelect(IndexSelect indexSelect);

	public String[] getIndexTableDefs();

	public void setIndexTableDefs(String[] indexTableDefs);

	public String getPrimaryKeyIndexSQL();

	public void setPrimaryKeyIndexSQL(String primaryKeyIndexSQL);
	
	public void setUpdateIndexSQL(String updateIndexSQL);
	
	public String getUpdateIndexSQL(); 
	
	public List collectIndexList();
	
	public void saveIndex(Index index);

	public String getSelectIndexSQL();

	public void setSelectIndexSQL(String selectIndexSQL);
	
	//-------------------------skjemakonto-------------
	
	public String getInsertQuestionareAccountSQL();

	public void setInsertQuestionareAccountSQL(String insertQuestionareAccountSQL);

	public QuestionareAccountSelect getQuestionareAccountSelect();

	public void setQuestionareAccountSelect(QuestionareAccountSelect questionareAccountSelect);

	public String[] getQuestionareAccountTableDefs();

	public void setQuestionareAccountTableDefs(String[] questionareAccountTableDefs);

	public String getPrimaryKeyQuestionareAccountSQL();

	public void setPrimaryKeyQuestionareAccountSQL(String primaryKeyQuestionareAccountSQL);
	
	public void setUpdateQuestionareAccountSQL(String updateQuestionareAccountSQL);
	
	public String getUpdateQuestionareAccountSQL(); 
	
	public List collectQuestionareAccountList();
	
	public void saveQuestionareAccount(QuestionareAccount 	questionareAccount);

	public String getSelectQuestionareAccountSQL();

	public void setSelectQuestionareAccountSQL(String selectQuestionareAccountSQL);
	
	//----------------------------LitteraturReferance----------------------------------------
	
	public String getInsertLiteraryReferenceSQL();

	public void setInsertLiteraryReferenceSQL(String insertLiteraryReferenceSQL);

	public LiteraryReferenceSelect getLiteraryReferenceSelect();

	public void setLiteraryReferenceSelect(LiteraryReferenceSelect literaryReferenceSelect);

	public String[] getLiteraryReferenceTableDefs();

	public void setLiteraryReferenceTableDefs(String[] literaryReferenceTableDefs);

	public String getPrimaryKeyLiteraryReferenceSQL();

	public void setPrimaryKeyLiteraryReferenceSQL(String primaryKeyLiteraryReferenceSQL);
	
	public void setUpdateLiteraryReferenceSQL(String updateLiteraryReferenceSQL);
	
	public String getUpdateLiteraryReferenceSQL();
	
	public List collectLiteraryReferences();
	
	public void saveLiteraryReference(LiteraryReference literaryReference);

	public String getSelectLiteraryReferenceSQL();

	public void setSelectLiteraryReferenceSQL(String selectLiteraryReferenceSQL);

	public List getSubjectlineforSubjects(Long qId);
	public List collectNokkelord(Long qId);
	
}

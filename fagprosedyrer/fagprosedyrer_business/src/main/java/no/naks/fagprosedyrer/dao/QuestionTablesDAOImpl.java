/**
 * 
 */
package no.naks.skjemabank.dao;


import java.sql.Types;
import java.util.Iterator;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;
import no.naks.skjemabank.model.Division;
import no.naks.skjemabank.model.Enterprise;
import no.naks.skjemabank.model.Examination;
import no.naks.skjemabank.model.ExaminationOwner;
import no.naks.skjemabank.model.ExaminationType;
import no.naks.skjemabank.model.Index;
import no.naks.skjemabank.model.IndexImpl;
import no.naks.skjemabank.model.Informant;
import no.naks.skjemabank.model.InformantImpl;
import no.naks.skjemabank.model.InputType;
import no.naks.skjemabank.model.InstitutionType;
import no.naks.skjemabank.model.LiteraryReference;
import no.naks.skjemabank.model.Owner;
import no.naks.skjemabank.model.PatientGroup;
import no.naks.skjemabank.model.QuestionImpl;
import no.naks.skjemabank.model.QuestionareAccount;
import no.naks.skjemabank.model.Region;
import no.naks.skjemabank.model.Subject;
import no.naks.skjemabank.model.SubjectImpl;
import no.naks.skjemabank.model.SubjectLine;
import no.naks.skjemabank.model.SubjectLineImpl;
import no.naks.skjemabank.model.User;




/**
 * @author ojn
 * This class is responsible for all dialog with Questionare database all administrative tables.
 * Denne klassen lagrer og henter
 * 			- pasientgruppe til databasen
 * 			- inputType til databasen (answer alternatives)
 * 			- examinationType til databasen
 * 
 */
public class QuestionTablesDAOImpl extends AbstractAdmintablesDAO implements QuestionTablesDAO {
	
	
	private String insertInputTypeSQL;
	private String updateInputTypeSQL;
	private String selectInputTypeSQL;
	private String primaryKeyInputTypeSQL;
	private String [] inputTypeTableDefs;
	private InputTypeSelect inputTypeSelect = null;
	
	private String insertExaminationOwnerSQL;
	private String updateExaminationOwnerSQL;
	private String selectExaminationOwnerSQL;
	private String primaryKeyExaminationOwnerSQL;
	private String [] examinationOwnerTableDefs;
	private ExaminationOwnerSelect examinationOwnerSelect = null;
	
	private String insertInstitutionTypeSQL;
	private String updateInstitutionTypeSQL;
	private String selectInstitutionTypeSQL;
	private String primaryKeyInstitutionTypeSQL;
	private String [] institutionTypeTableDefs;
	private InstitutionTypeSelect institutionTypeSelect = null;
	private String selectQuestionForIndeksSQL;
//	private String[]questionTableDefs;
	
	private String updateInformantSQL;
	private String selectInformantSQL;
	private String selectNokkelordskjemalinjeSQL;
	private String primaryKeyInformantSQL;
	private String [] informantTableDefs;
	private String insertInformantSQL;
	private InformantSelect informantSelect = null;
	private QuestionSelect questionSelect = null;
	
	private String insertOwnerSQL;
	private String updateOwnerSQL;
	private String selectOwnerSQL;
	private String primaryKeyOwnerSQL;
	private String [] ownerTableDefs;
	private OwnerSelect ownerSelect = null;
	
	private String updateSubjectSQL;
	private String selectSubjectSQL;
	private String selectSubjectAllSQL;
	private String deleteSubjectSQL;
	private String deleteNokkelordskjemalinjeSQL;
	private String primaryKeySubjectSQL;
	private String [] subjectTableDefs;
	private String insertSubjectSQL;
	private String deleteSubjectlineSQL;
	private String deleteSubjectlineSubjectSQL;
	private SubjectSelect subjectSelect = null;
	
	private String updateSubjectlineSQL;
	private String insertSubjectlineSQL;
	
	private String selectquestionSubjectSQL;
	private String[] sporsmallinjeTableDefs; 
	
	private SubjectlineSelect subjectlineSelect = null;
	private String updateUserSQL;
	private String selectUserSQL;
	private String primaryKeyUserSQL;
	private String [] userTableDefs;
	private String insertUserSQL;
	private UserSelect userSelect = null;
	
	
	private String updateRegionSQL;
	private String selectRegionSQL;
	private String primaryKeyRegionSQL;
	private String [] regionTableDefs;
	private String insertRegionSQL;
	private RegionSelect regionSelect = null;
	
	
	private String updateDivisionSQL;
	private String selectDivisionSQL;
	private String primaryKeyDivisionSQL;
	private String [] divisionTableDefs;
	private String insertDivisionSQL;
	private DivisionSelect divisionSelect = null;
	
	private String updateEnterpriseSQL;
	private String selectEnterpriseSQL;
	private String primaryKeyEnterpriseSQL;
	private String [] enterpriseTableDefs;
	private String insertEnterpriseSQL;
	private EnterpriseSelect enterpriseSelect = null;

	private String updateExaminationSQL;
	private String selectExaminationSQL;
	private String primaryKeyExaminationSQL;
	private String [] examinationTableDefs;
	private String insertExaminationSQL;
	private ExaminationSelect examinationSelect = null;
	
	private String updateIndexSQL;
	private String selectIndexSQL;
	private String primaryKeyIndexSQL;
	private String [] indexTableDefs;
	private String insertIndexSQL;
	private IndexSelect indexSelect = null;
	
	private String insertQuestionareAccountSQL;
	private String updateQuestionareAccountSQL;
	private String selectQuestionareAccountSQL;
	private String primaryKeyQuestionareAccountSQL;
	private String [] questionareAccountTableDefs;
	private QuestionareAccountSelect questionareAccountSelect = null;
	
	private String insertLiteraryReferenceSQL;
	private String updateLiteraryReferenceSQL;
	private String selectLiteraryReferenceSQL;
	private String primaryKeyLiteraryReferenceSQL;
	private String [] literaryReferenceTableDefs;
	private LiteraryReferenceSelect literaryReferenceSelect = null;
	
	
	private Tablesupdate tablesUpdate = null;
	private String [] primaryKeyTablesDefs;
	

	
	public String getDeleteSubjectSQL() {
		return deleteSubjectSQL;
	}
	public void setDeleteSubjectSQL(String deleteSubjectSQL) {
		this.deleteSubjectSQL = deleteSubjectSQL;
	}
	
	public String getDeleteNokkelordskjemalinjeSQL() {
		return deleteNokkelordskjemalinjeSQL;
	}
	public void setDeleteNokkelordskjemalinjeSQL(
			String deleteNokkelordskjemalinjeSQL) {
		this.deleteNokkelordskjemalinjeSQL = deleteNokkelordskjemalinjeSQL;
	}
	public String getDeleteSubjectlineSQL() {
		return deleteSubjectlineSQL;
	}
	public void setDeleteSubjectlineSQL(String deleteSubjectlineSQL) {
		this.deleteSubjectlineSQL = deleteSubjectlineSQL;
	}
	public String getDeleteSubjectlineSubjectSQL() {
		return deleteSubjectlineSubjectSQL;
	}
	public void setDeleteSubjectlineSubjectSQL(String deleteSubjectlineSubjectSQL) {
		this.deleteSubjectlineSubjectSQL = deleteSubjectlineSubjectSQL;
	}
	public String getUpdateSubjectlineSQL() {
		return updateSubjectlineSQL;
	}
	public void setUpdateSubjectlineSQL(String updateSubjectlineSQL) {
		this.updateSubjectlineSQL = updateSubjectlineSQL;
	}
	public String getInsertSubjectlineSQL() {
		return insertSubjectlineSQL;
	}
	public void setInsertSubjectlineSQL(String insertSubjectlineSQL) {
		this.insertSubjectlineSQL = insertSubjectlineSQL;
	}
	public String[] getPrimaryKeyTablesDefs() {
		return primaryKeyTablesDefs;
	}
	public void setPrimaryKeyTablesDefs(String[] primaryKeyTablesDefs) {
		this.primaryKeyTablesDefs = primaryKeyTablesDefs;
	}
	public Tablesupdate getTablesUpdate() {
		return tablesUpdate;
	}
	public void setTablesUpdate(Tablesupdate tablesUpdate) {
		this.tablesUpdate = tablesUpdate;
	}
	
	public String getSelectNokkelordskjemalinjeSQL() {
		return selectNokkelordskjemalinjeSQL;
	}
	public void setSelectNokkelordskjemalinjeSQL(
			String selectNokkelordskjemalinjeSQL) {
		this.selectNokkelordskjemalinjeSQL = selectNokkelordskjemalinjeSQL;
	}
	public InputTypeSelect getInputTypeSelect() {
		return inputTypeSelect;
	}
	public void setInputTypeSelect(InputTypeSelect inputTypeSelect) {
		this.inputTypeSelect = inputTypeSelect;
	}
	public String[] getInputTypeTableDefs() {
		return inputTypeTableDefs;
	}

	public void setInputTypeTableDefs(String[] inputTypeTableDefs) {
		this.inputTypeTableDefs = inputTypeTableDefs;
	}

	public String getInsertInputTypeSQL() {
		return insertInputTypeSQL;
	}

	public void setInsertInputTypeSQL(String insertInputTypeSQL) {
		this.insertInputTypeSQL = insertInputTypeSQL;
	}

	public String getPrimaryKeyInputTypeSQL() {
		return primaryKeyInputTypeSQL;
	}

	public void setPrimaryKeyInputTypeSQL(String primaryKeyInputTypeSQL) {
		this.primaryKeyInputTypeSQL = primaryKeyInputTypeSQL;
	}

	public String getSelectInputTypeSQL() {
		return selectInputTypeSQL;
	}

	public void setSelectInputTypeSQL(String selectInputTypeSQL) {
		this.selectInputTypeSQL = selectInputTypeSQL;
	}

	public String getUpdateInputTypeSQL() {
		return updateInputTypeSQL;
	}

	public void setUpdateInputTypeSQL(String updateInputTypeSQL) {
		this.updateInputTypeSQL = updateInputTypeSQL;
	}

	/*
	public String[] getQuestionTableDefs() {
		return questionTableDefs;
	}
	public void setQuestionTableDefs(String[] questionTableDefs) {
		this.questionTableDefs = questionTableDefs;
	}
	*/
	public QuestionSelect getQuestionSelect() {
		return questionSelect;
	}
	public void setQuestionSelect(QuestionSelect questionSelect) {
		this.questionSelect = questionSelect;
	}
	
//-------------------------------------------------------------------------------
	//-----------------institusjonstype manipulation
	
	
	
	public String[] getInstitutionTypeTableDefs() {
		return institutionTypeTableDefs;
	}
	
	public void setInstitutionTypeTableDefs(String[] institutionTypeTableDefs) {
		this.institutionTypeTableDefs = institutionTypeTableDefs;
	}
	public String getInsertInstitutionTypeSQL() {
		return insertInstitutionTypeSQL;
	}
	public void setInsertInstitutionTypeSQL(String insertInstitutionTypeSQL) {
		this.insertInstitutionTypeSQL = insertInstitutionTypeSQL;
	}
	public String getPrimaryKeyInstitutionTypeSQL() {
		return primaryKeyInstitutionTypeSQL;
	}
	public void setPrimaryKeyInstitutionTypeSQL(
			String primaryKeyInstitutionTypeSQL) {
		this.primaryKeyInstitutionTypeSQL = primaryKeyInstitutionTypeSQL;
	}
	public String getSelectInstitutionTypeSQL() {
		return selectInstitutionTypeSQL;
	}
	public void setSelectInstitutionTypeSQL(String selectInstitutionTypeSQL) {
		this.selectInstitutionTypeSQL = selectInstitutionTypeSQL;
	}
	public String getUpdateInstitutionTypeSQL() {
		return updateInstitutionTypeSQL;
	}
	public void setUpdateInstitutionTypeSQL(String updateInstitutionTypeSQL) {
		this.updateInstitutionTypeSQL = updateInstitutionTypeSQL;
	}
	public InstitutionTypeSelect getInstitutionTypeSelect(){
		return institutionTypeSelect;
	}
	public void setInstitutionTypeSelect(InstitutionTypeSelect institutionTypeSelect) {
		this.institutionTypeSelect = institutionTypeSelect;
		}
	//----------------ExaminationOwner---
	public String[] getExaminationOwnerTableDefs() {
		return examinationOwnerTableDefs;
	}
	
	public void setExaminationOwnerTableDefs(String[] examinationOwnerTableDefs) {
		this.examinationOwnerTableDefs = examinationOwnerTableDefs;
	}
	public String getInsertExaminationOwnerSQL() {
		return insertExaminationOwnerSQL;
	}
	public void setInsertExaminationOwnerSQL(String insertExaminationOwnerSQL) {
		this.insertExaminationOwnerSQL = insertExaminationOwnerSQL;
	}
	public String getPrimaryKeyExaminationOwnerSQL() {
		return primaryKeyExaminationOwnerSQL;
	}
	public void setPrimaryKeyExaminationOwnerSQL(
			String primaryKeyExaminationOwnerSQL) {
		this.primaryKeyExaminationOwnerSQL = primaryKeyExaminationOwnerSQL;
	}
	public String getSelectExaminationOwnerSQL() {
		return selectExaminationOwnerSQL;
	}
	public void setSelectExaminationOwnerSQL(String selectExaminationOwnerSQL) {
		this.selectExaminationOwnerSQL = selectExaminationOwnerSQL;
	}
	public String getUpdateExaminationOwnerSQL() {
		return updateExaminationOwnerSQL;
	}
	public void setUpdateExaminationOwnerSQL(String updateExaminationOwnerSQL) {
		this.updateExaminationOwnerSQL = updateExaminationOwnerSQL;
	}
	public ExaminationOwnerSelect getExaminationOwnerSelect(){
		return examinationOwnerSelect;
	}
	public void setExaminationOwnerSelect(ExaminationOwnerSelect examinationOwnerSelect) {
		this.examinationOwnerSelect = examinationOwnerSelect;
		}
	
	//---------------------informant------------------------
	
	
	public InformantSelect getInformantSelect() {
		return informantSelect;
	}

	public void setInformantSelect(InformantSelect informantSelect) {
		this.informantSelect = informantSelect;
	}

	public String[] getInformantTableDefs() {
		return informantTableDefs;
	}

	public void setInformantTableDefs(String[] informantTableDefs) {
		this.informantTableDefs = informantTableDefs;
	}

	public String getInsertInformantSQL() {
		return insertInformantSQL;
	}

	public void setInsertInformantSQL(String insertInformantSQL) {
		this.insertInformantSQL = insertInformantSQL;
	}

	public String getPrimaryKeyInformantSQL() {
		return primaryKeyInformantSQL;
	}

	public void setPrimaryKeyInformantSQL(String primaryKeyInformantSQL) {
		this.primaryKeyInformantSQL = primaryKeyInformantSQL;
	}

	public String getSelectInformantSQL() {
		return selectInformantSQL;
	}

	public void setSelectInformantSQL(String selectInformantSQL) {
		this.selectInformantSQL = selectInformantSQL;
	}

	public String getUpdateInformantSQL() {
		return updateInformantSQL;
	}

	public void setUpdateInformantSQL(String updateInformantSQL) {
		this.updateInformantSQL = updateInformantSQL;
	}

	//-------------------------------------------ownerrrrrrrrrrrrrr--------------
	
	
	public String getInsertOwnerSQL() {
		return insertOwnerSQL;
	}
	
	public void setInsertOwnerSQL(String insertOwnerSQL) {
		this.insertOwnerSQL = insertOwnerSQL;
	}
	
	public OwnerSelect getOwnerSelect() {
		return ownerSelect;
	}
	public void setOwnerSelect(OwnerSelect ownerSelect) {
		this.ownerSelect = ownerSelect;
	}
	
	public String[] getOwnerTableDefs() {
		return ownerTableDefs;
	}
	
	public void setOwnerTableDefs(String[] ownerTableDefs) {
		this.ownerTableDefs = ownerTableDefs;
	}
	
	public String getPrimaryKeyOwnerSQL() {
		return primaryKeyOwnerSQL;
	}
	
	public void setPrimaryKeyOwnerSQL(String primaryKeyOwnerSQL) {
		this.primaryKeyOwnerSQL = primaryKeyOwnerSQL;
	}
	
	public String getSelectOwnerSQL() {
		return selectOwnerSQL;
	}
	
	public void setSelectOwnerSQL(String selectOwnerSQL) {
		this.selectOwnerSQL = selectOwnerSQL;
	}
	
	public String getUpdateOwnerSQL() {
		return updateOwnerSQL;
	}
	
	public void setUpdateOwnerSQL(String updateOwnerSQL) {
		this.updateOwnerSQL = updateOwnerSQL;
	}

	
	/**
	 * 
	 * @return the selectQuestionForIndeksSQL
	 */
	
	public String getSelectQuestionForIndeksSQL() {
		return selectQuestionForIndeksSQL;
	}
	
	/**
	 * 
	 * @param selectQuestionForIndeksSQL 
	 */
	public void setSelectQuestionForIndeksSQL(String selectQuestionForIndeksSQL) {
		this.selectQuestionForIndeksSQL = selectQuestionForIndeksSQL;
	}


	/**
	 * collectInputType
	 * This routine collects all InputTypes from db
	 * @return
	 */
	public List collectInputType(){
		inputTypeSelect = new InputTypeSelect(getDataSource(),selectInputTypeSQL,inputTypeTableDefs);
		List inputType = inputTypeSelect.execute();
		return inputType;
	}
	
	public void saveInputType (InputType inputType){
		inputType.setParams();
		String sql = insertInputTypeSQL;
		int[] types = inputType.getTypes();
		Object[]params = inputType.getParams();
		Long id = inputType.getInputTypeId();
		if (id != null){
			sql = updateInputTypeSQL;
			types = inputType.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			inputType.setInputTypeId(getPrimaryKey(primaryKeyInputTypeSQL,primaryKeyTablesDefs ));
		}
	}
	
	/**
	 * collectInputType
	 * This routine collects all InputTypes from db
	 * @return
	 */

	public List collectExaminationOwners(){
		examinationOwnerSelect = new ExaminationOwnerSelect(getDataSource(),selectExaminationOwnerSQL,examinationOwnerTableDefs);
		List examinationOwner = examinationOwnerSelect.execute();
		return examinationOwner;
	}
	
	public void saveExaminationOwner (ExaminationOwner examinationOwner){
		examinationOwner.setParams();
		String sql = insertExaminationOwnerSQL;
		int[] types = examinationOwner.getTypes();
		Object[]params = examinationOwner.getParams();
		Long id = examinationOwner.getExaminationOwnerId();
		if (id != null){
			sql = updateExaminationOwnerSQL;
			types = examinationOwner.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			examinationOwner.setExaminationOwnerId(getPrimaryKey(primaryKeyExaminationOwnerSQL,primaryKeyTablesDefs ));
		}
	}

	
	public List collectInstitutionTypes(){
		institutionTypeSelect = new InstitutionTypeSelect(getDataSource(),selectInstitutionTypeSQL,institutionTypeTableDefs);
		List informant = institutionTypeSelect.execute();
		return informant;
	}
	
	public void saveInstitutionType (InstitutionType institutionType){
		institutionType.setParams();
		String sql = insertInstitutionTypeSQL;
		int[] types = institutionType.getTypes();
		Object[]params = institutionType.getParams();
		Long id = institutionType.getInstitutionTypeId();
		if (id != null){
			sql = updateInstitutionTypeSQL;
			types = institutionType.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			institutionType.setInstitutionTypeId(getPrimaryKey(primaryKeyInstitutionTypeSQL,primaryKeyTablesDefs ));
		}
	}
	
	
	public List collectInformants(){
		informantSelect = new InformantSelect(getDataSource(),selectInformantSQL,informantTableDefs);
		List informant = informantSelect.execute();
		return informant;
	}
	
	
	
	public void saveInformant (Informant informant){
		informant.setParams();
		String sql = insertInformantSQL;
		int[] types = informant.getTypes();
		Object[]params = informant.getParams();
		Long id = informant.getInformantId();
		if (id != null){
			sql = updateInformantSQL;
			types = informant.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			informant.setInformantId(getPrimaryKey(primaryKeyInformantSQL,primaryKeyTablesDefs ));
		}
	}

	
	public List collectOwners(){
		ownerSelect = new OwnerSelect(getDataSource(),selectOwnerSQL,ownerTableDefs);
		List owner = ownerSelect.execute();
		return owner;
	}
	
	public void saveOwner (Owner owner){
		owner.setParams();
		String sql = insertOwnerSQL;
		int[] types = owner.getTypes();
		Object[]params = owner.getParams();
		Long id = owner.getOwnerId();
		if (id != null){
			sql = updateOwnerSQL;
			types = owner.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			owner.setOwnerId(getPrimaryKey(primaryKeyOwnerSQL,primaryKeyTablesDefs ));
		}
	}
	public String getInsertSubjectSQL() {
		return insertSubjectSQL;
	}
	public void setInsertSubjectSQL(String insertSubjectSQL) {
		this.insertSubjectSQL = insertSubjectSQL;
	}
	public String getPrimaryKeySubjectSQL() {
		return primaryKeySubjectSQL;
	}
	public void setPrimaryKeySubjectSQL(String primaryKeySubjectSQL) {
		this.primaryKeySubjectSQL = primaryKeySubjectSQL;
	}
	public String getSelectSubjectSQL() {
		return selectSubjectSQL;
	}
	public void setSelectSubjectSQL(String selectSubjectSQL) {
		this.selectSubjectSQL = selectSubjectSQL;
	}
	public String getSelectSubjectAllSQL() {
		return selectSubjectAllSQL;
	}
	public void setSelectSubjectAllSQL(String selectSubjectAllSQL) {
		this.selectSubjectAllSQL = selectSubjectAllSQL;
	}
	public SubjectSelect getSubjectSelect() {
		return subjectSelect;
	}
	public void setSubjectSelect(SubjectSelect subjectSelect) {
		this.subjectSelect = subjectSelect;
	}
	public String[] getSubjectTableDefs() {
		return subjectTableDefs;
	}
	public void setSubjectTableDefs(String[] subjectTableDefs) {
		this.subjectTableDefs = subjectTableDefs;
	}
	public String getUpdateSubjectSQL() {
		return updateSubjectSQL;
	}
	public void setUpdateSubjectSQL(String updateSubjectSQL) {
		this.updateSubjectSQL = updateSubjectSQL;
	}
	
	public String getSelectquestionSubjectSQL() {
		return selectquestionSubjectSQL;
	}
	public void setSelectquestionSubjectSQL(String selectquestionSubjectSQL) {
		this.selectquestionSubjectSQL = selectquestionSubjectSQL;
	}
	public String[] getSporsmallinjeTableDefs() {
		return sporsmallinjeTableDefs;
	}
	public void setSporsmallinjeTableDefs(String[] sporsmallinjeTableDefs) {
		this.sporsmallinjeTableDefs = sporsmallinjeTableDefs;
	}
	public List getSubjectlineforSubjects(Long qId){
		long quId = qId.longValue();
		int type = Types.INTEGER;
		
		subjectlineSelect = new SubjectlineSelect(getDataSource(),selectquestionSubjectSQL,sporsmallinjeTableDefs);
		subjectlineSelect.declareParameter(new SqlParameter(type));
	
		List<SubjectLineImpl> subjectlines = subjectlineSelect.execute(quId);
	
		return subjectlines;
		
	}
	
	public List collectSubjects(Long skjemaId){
		long skjId = 0;
		if (skjemaId != null)
			skjId = skjemaId.longValue();
		int type = Types.INTEGER;
		subjectSelect = new SubjectSelect(getDataSource(),selectSubjectSQL,subjectTableDefs);
		subjectSelect.declareParameter(new SqlParameter(type));
		List<SubjectImpl> subjects = subjectSelect.execute(skjId);
		for (SubjectImpl subject : subjects ){
			subject.setSubjectList(getSubjectlineforSubjects(subject.getSubjectId()));
			
		}
		return subjects;
	}
	
	public List collectNokkelord(Long qId){
		int type = Types.INTEGER;
		informantSelect = new InformantSelect(getDataSource(), selectNokkelordskjemalinjeSQL, informantTableDefs);
		informantSelect.declareParameter(new SqlParameter(type));
		List<InformantImpl> nokkelOrdList = informantSelect.execute(qId);
		return nokkelOrdList;
	}
	
	public List collectAllSubjects(){
		subjectSelect = new SubjectSelect(getDataSource(),selectSubjectAllSQL,subjectTableDefs);
		List<SubjectImpl> subjects = subjectSelect.execute();
		for (SubjectImpl subject : subjects ){
			subject.setSubjectList(getSubjectlineforSubjects(subject.getSubjectId()));
			
		}
		return subjects;
	}
	
	public void saveSubject (Subject subject){
		subject.setParams();
		String sql = insertSubjectSQL;
		int[] types = subject.getTypes();
		Object[]params = subject.getParams();
		Long id = subject.getSubjectId();
		if (id != null){
			sql = updateSubjectSQL;
			types = subject.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			subject.setSubjectId(getPrimaryKey(primaryKeySubjectSQL,primaryKeyTablesDefs ));
		}
	}
	/* updateSubjectline
	 * This routine updates the table tbpsporsmalinje
	 * @see no.naks.skjemabank.dao.QuestionTablesDAO#updateSubjectline(no.naks.skjemabank.model.SubjectLine)
	 */
	public void updateSubjectline(SubjectLine subjectLine){
		subjectLine.setParams();
		String sql = updateSubjectlineSQL;
		int[] types = subjectLine.getUtypes();
		Object[]params = subjectLine.getParams();
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
	}
	public void insertSubjectline(SubjectLine subjectLine){
		subjectLine.setNewparams();
		String sql = insertSubjectlineSQL;
		int[] types = subjectLine.getTypes();
		Object[]params = subjectLine.getParams();
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		
	}
	public void deleteSubjectline(Long questionId){
		int type = Types.INTEGER;
		String sql= deleteSubjectlineSQL;
		long id = questionId.longValue();
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
	}
	
	public void deleteSubjectlineSubject(Long sId){
		int type = Types.INTEGER;
		String sql= deleteSubjectlineSubjectSQL;
		long id = sId.longValue();
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
	}
	
	public void deleteSubject(Long subjectId){
		int type = Types.INTEGER;
		String sql= deleteSubjectSQL;
		long id = subjectId.longValue();
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
	}
	
	public void deleteNokkelordskjemalinje(Long informantid){
		int type = Types.INTEGER;
		String sql =  deleteNokkelordskjemalinjeSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(), sql) ;
		tablesUpdate.declareParameter(new SqlParameter(type)) ;
		tablesUpdate.delete(informantid) ;
		
		
	}
	//--------------------------------Users------------------
	public List collectUsers() {
		
		userSelect = new UserSelect(getDataSource(),selectUserSQL,userTableDefs);
		List users = userSelect.execute();
		return users;
	}
	
	public void saveUser(User user) {
		
		user.setParams();
		String sql = insertUserSQL;
		int[] types = user.getTypes();
		Object[]params = user.getParams();
		Long id = user.getUserId();
		if (id != null){
			sql = updateUserSQL;
			types = user.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			user.setUserId(getPrimaryKey(primaryKeyUserSQL,primaryKeyTablesDefs ));
		}
	}
	
	//--the setter and getter for the declared variables+ save and collect
	public String getInsertUserSQL() {
		
		return insertUserSQL;
	}
	public String getPrimaryKeyUserSQL() {
		
		return primaryKeyUserSQL;
	}
	public String getSelectUserSQL() {
	
		return selectUserSQL;
	}
	public String getUpdateUserSQL() {
		
		return updateUserSQL;
	}
	public UserSelect getUserSelect() {
		
		return userSelect;
	}
	public String[] getUserTableDefs() {
		
		return userTableDefs;
	}

	public void setInsertUserSQL(String insertUserSQL) {
		 
		this.insertUserSQL=insertUserSQL;
		
	}
	public void setPrimaryKeyUserSQL(String primaryKeyUserSQL) {
	
		this.primaryKeyUserSQL=primaryKeyUserSQL;
	}
	public void setSelectUserSQL(String selectUserSQL) {
		this.selectUserSQL=selectUserSQL;
		
	}
	public void setUpdateUserSQL(String updateUserSQL) {
		  this.updateUserSQL=updateUserSQL;
		
	}
	public void setUserSelect(UserSelect userSelect) {
			this.userSelect=userSelect;
		
	}
	public void setUserTableDefs(String[] userTableDefs) {
		
		this.userTableDefs=userTableDefs;
		
	}
	
	//---------------getter and setter 
	public String getInsertRegionSQL() {
		return insertRegionSQL;
	}
	public void setInsertRegionSQL(String insertRegionSQL) {
		this.insertRegionSQL = insertRegionSQL;
	}
	public String getPrimaryKeyRegionSQL() {
		return primaryKeyRegionSQL;
	}
	public void setPrimaryKeyRegionSQL(String primaryKeyRegionSQL) {
		this.primaryKeyRegionSQL = primaryKeyRegionSQL;
	}
	
	public RegionSelect getRegionSelect() {
		return regionSelect;
	}
	public void setRegionSelect(RegionSelect region) {
		this.regionSelect = region;
	}
	public String[] getRegionTableDefs() {
		return regionTableDefs;
	}
	public void setRegionTableDefs(String[] regionTableDefs) {
		this.regionTableDefs = regionTableDefs;
	}
	public String getSelectRegionSQL() {
		return selectRegionSQL;
	}
	public void setSelectRegionSQL(String selectRegionSQL) {
		this.selectRegionSQL = selectRegionSQL;
	}
	public String getUpdateRegionSQL() {
		return updateRegionSQL;
	}
	public void setUpdateRegionSQL(String updateRegionSQL) {
		this.updateRegionSQL = updateRegionSQL;
	}
	
	
public List collectregions() {
		
		regionSelect = new RegionSelect(getDataSource(),selectRegionSQL,regionTableDefs);
		List regions = regionSelect.execute();
		return regions;
	}
	
	public void saveRegion(Region region) {
		
		region.setParams();
		String sql = insertRegionSQL;
		int[] types = region.getTypes();
		Object[]params = region.getParams();
		Long id = region.getRegionId();
		if (id != null){
			sql = updateRegionSQL;
			types = region.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			region.setRegionId(getPrimaryKey(primaryKeyRegionSQL,primaryKeyTablesDefs ));
		}
	}
	//---------------------division---------------

	//---------------------division---------------
	public DivisionSelect getDivisionSelect() {
		return divisionSelect;
	}
	public void setDivisionSelect(DivisionSelect divisionSelect) {
		this.divisionSelect = divisionSelect;
	}
	public String[] getDivisionTableDefs() {
		return divisionTableDefs;
	}
	public void setDivisionTableDefs(String[] divisionTableDefs) {
		this.divisionTableDefs = divisionTableDefs;
	}
	public String getInsertDivisionSQL() {
		return insertDivisionSQL;
	}
	public void setInsertDivisionSQL(String insertDivisionSQL) {
		this.insertDivisionSQL = insertDivisionSQL;
	}
	public String getPrimaryKeyDivisionSQL() {
		return primaryKeyDivisionSQL;
	}
	public void setPrimaryKeyDivisionSQL(String primaryKeyDivisionSQL) {
		this.primaryKeyDivisionSQL = primaryKeyDivisionSQL;
	}
	public String getSelectDivisionSQL() {
		return selectDivisionSQL;
	}
	public void setSelectDivisionSQL(String selectDivisionSQL) {
		this.selectDivisionSQL = selectDivisionSQL;
	}
	public String getUpdateDivisionSQL() {
		return updateDivisionSQL;
	}
	public void setUpdateDivisionSQL(String updateDivisionSQL) {
		this.updateDivisionSQL = updateDivisionSQL;
	}
	
	
public List collectdivisions() {
	
		divisionSelect = new DivisionSelect(getDataSource(),selectDivisionSQL,divisionTableDefs);
		List divisions = divisionSelect.execute();
		return divisions;
	}
	
	public void saveDivision(Division division) {
		
		division.setParams();
		String sql = insertDivisionSQL;
		int[] types = division.getTypes();
		Object[]params = division.getParams();
		Long id = division.getDivisionId();
		if (id != null){
			sql = updateDivisionSQL;
			types = division.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			division.setDivisionId(getPrimaryKey(primaryKeyDivisionSQL,primaryKeyTablesDefs ));
		}
	}
	
	//-----------------------------------
	public EnterpriseSelect getEnterpriseSelect() {
		return enterpriseSelect;
	}
	public void setEnterpriseSelect(EnterpriseSelect enterpriseSelect) {
		this.enterpriseSelect = enterpriseSelect;
	}
	public String[] getEnterpriseTableDefs() {
		return enterpriseTableDefs;
	}
	public void setEnterpriseTableDefs(String[] enterpriseTableDefs) {
		this.enterpriseTableDefs = enterpriseTableDefs;
	}
	public String getInsertEnterpriseSQL() {
		return insertEnterpriseSQL;
	}
	public void setInsertEnterpriseSQL(String insertEnterpriseSQL) {
		this.insertEnterpriseSQL = insertEnterpriseSQL;
	}
	public String getPrimaryKeyEnterpriseSQL() {
		return primaryKeyEnterpriseSQL;
	}
	public void setPrimaryKeyEnterpriseSQL(String primaryKeyEnterpriseSQL) {
		this.primaryKeyEnterpriseSQL = primaryKeyEnterpriseSQL;
	}
	public String getSelectEnterpriseSQL() {
		return selectEnterpriseSQL;
	}
	public void setSelectEnterpriseSQL(String selectEnterpriseSQL) {
		this.selectEnterpriseSQL = selectEnterpriseSQL;
	}
	public String getUpdateEnterpriseSQL() {
		return updateEnterpriseSQL;
	}
	public void setUpdateEnterpriseSQL(String updateEnterpriseSQL) {
		this.updateEnterpriseSQL = updateEnterpriseSQL;
	}
	
	
public List collectEnterprises() {
		
		enterpriseSelect = new EnterpriseSelect(getDataSource(),selectEnterpriseSQL,enterpriseTableDefs);
		List enterprises = enterpriseSelect.execute();
		return enterprises;
	}
	
	public void saveEnterprise(Enterprise enterprise) {
		
		enterprise.setParams();
		String sql = insertEnterpriseSQL;
		int[] types = enterprise.getTypes();
		Object[]params = enterprise.getParams();
		Long id = enterprise.getEnterpriseId();
		if (id != null){
			sql = updateEnterpriseSQL;
			types = enterprise.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			enterprise.setEnterpriseId(getPrimaryKey(primaryKeyEnterpriseSQL,primaryKeyTablesDefs ));
		}
	}
	
	
	//getter and setter for 
	
	public ExaminationSelect getExaminationSelect() {
		return examinationSelect;
	}
	public void setExaminationSelect(ExaminationSelect examinationSelect) {
		this.examinationSelect = examinationSelect;
	}
	public String[] getExaminationTableDefs() {
		return examinationTableDefs;
	}
	public void setExaminationTableDefs(String[] examinationTableDefs) {
		this.examinationTableDefs = examinationTableDefs;
	}
	public String getInsertExaminationSQL() {
		return insertExaminationSQL;
	}
	public void setInsertExaminationSQL(String insertExaminationSQL) {
		this.insertExaminationSQL = insertExaminationSQL;
	}
	public String getPrimaryKeyExaminationSQL() {
		return primaryKeyExaminationSQL;
	}
	public void setPrimaryKeyExaminationSQL(String primaryKeyExaminationSQL) {
		this.primaryKeyExaminationSQL = primaryKeyExaminationSQL;
	}
	public String getSelectExaminationSQL() {
		return selectExaminationSQL;
	}
	public void setSelectExaminationSQL(String selectExaminationSQL) {
		this.selectExaminationSQL = selectExaminationSQL;
	}
	public String getUpdateExaminationSQL() {
		return updateExaminationSQL;
	}
	public void setUpdateExaminationSQL(String updateExaminationSQL) {
		this.updateExaminationSQL = updateExaminationSQL;
	}


public List collectExaminationList() {
		
		examinationSelect = new ExaminationSelect(getDataSource(),selectExaminationSQL,examinationTableDefs);
		List examinationlist = examinationSelect.execute();
		return examinationlist;
	}
	
	public void saveExamination(Examination examination) {
		
		examination.setParams();
		String sql = insertExaminationSQL;
		int[] types = examination.getTypes();
		Object[]params = examination.getParams();
		Long id = examination.getExaminationId();
		if (id != null){
			sql = updateExaminationSQL;
			types = examination.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			examination.setExaminationId(getPrimaryKey(primaryKeyExaminationSQL,primaryKeyTablesDefs ));
		}
	}
	//-----------------
	public String getUpdateIndexSQL() {
		return updateIndexSQL;
	}
	
	public void setUpdateIndexSQL(String updateIndexSQL) {
		this.updateIndexSQL = updateIndexSQL;
	}
	
	public String getSelectIndexSQL() {
		return selectIndexSQL;
	}
	
	public void setSelectIndexSQL(String selectIndexSQL) {
		this.selectIndexSQL = selectIndexSQL;
	}
	
	public String getPrimaryKeyIndexSQL() {
		return primaryKeyIndexSQL;
	}
	
	public void setPrimaryKeyIndexSQL(String primaryKeyIndexSQL) {
		this.primaryKeyIndexSQL = primaryKeyIndexSQL;
	}
	
	public String[] getIndexTableDefs() {
		return indexTableDefs;
	}
	
	public void setIndexTableDefs(String[] indexTableDefs) {
		this.indexTableDefs = indexTableDefs;
	}
	
	public String getInsertIndexSQL() {
		return insertIndexSQL;
	}
	
	public void setInsertIndexSQL(String insertIndexSQL) {
		this.insertIndexSQL = insertIndexSQL;
	}
	
	public IndexSelect getIndexSelect() {
		return indexSelect;
	}
	
	public void setIndexSelect(IndexSelect indexSelect) {
		this.indexSelect = indexSelect;
	}
	
	
	public List collectIndexList() {
		indexSelect = new IndexSelect(getDataSource(),selectIndexSQL,indexTableDefs);
		List indexlist = indexSelect.execute();
		Iterator itrIndeksList = indexlist.iterator();
		while(itrIndeksList.hasNext()){
			Index index =(IndexImpl) itrIndeksList.next();
	//		getQuestionsForIndeks(index);
		}
		return indexlist;
		
	}
	
	
	/**
	 * getQuestions
	 * This routine collects all questions related to a indeksid
	 * @param qId
	 * @return
	 */
	/*
	 private void getQuestionsForIndeks(Index index){
		
		Long indeksId = index.getIndexId();
		
		int type = Types.INTEGER;
		questionSelect = new QuestionSelect(getDataSource(),selectQuestionForIndeksSQL,questionTableDefs);
		questionSelect.declareParameter(new SqlParameter(type));
		List questions = questionSelect.execute(indeksId);
		
		index.setQuestionsForIndeksList(questions);
		
	}
	*/
	public void saveIndex(Index index) {
		
		index.setParams();
		String sql = insertIndexSQL;
		int[] types = index.getTypes();
		Object[]params = index.getParams();
		Long id = index.getIndexId();
		if (id != null){
			sql = updateIndexSQL;
			types = index.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
		
			index.setIndexId(getPrimaryKey(primaryKeyIndexSQL,primaryKeyTablesDefs));
		}
	}

//--------------------QuestionareAccount-----------------------

	public String getInsertQuestionareAccountSQL() {
		return insertQuestionareAccountSQL;
		}
	
	public void setInsertQuestionareAccountSQL(String insertQuestionareAccountSQL) {
		this.insertQuestionareAccountSQL = insertQuestionareAccountSQL;
		}
	
	public String getUpdateQuestionareAccountSQL() {
		return updateQuestionareAccountSQL;
		}
	
	public void setUpdateQuestionareAccountSQL(String updateQuestionareAccountSQL) {
		this.updateQuestionareAccountSQL = updateQuestionareAccountSQL;
		}
	
	public String getSelectQuestionareAccountSQL() {
		return selectQuestionareAccountSQL;
		}
	
	public void setSelectQuestionareAccountSQL(String selectQuestionareAccountSQL) {
		this.selectQuestionareAccountSQL = selectQuestionareAccountSQL;
		}
	
	public String getPrimaryKeyQuestionareAccountSQL() {
		return primaryKeyQuestionareAccountSQL;
		}
	
	public void setPrimaryKeyQuestionareAccountSQL(
		String primaryKeyQuestionareAccountSQL) {
		this.primaryKeyQuestionareAccountSQL = primaryKeyQuestionareAccountSQL;
		}
	
	public String[] getQuestionareAccountTableDefs() {
		return questionareAccountTableDefs;
		}
	
	public void setQuestionareAccountTableDefs(String[] questionareAccountTableDefs) {
		this.questionareAccountTableDefs = questionareAccountTableDefs;
		}
	
	public QuestionareAccountSelect getQuestionareAccountSelect() {
		return questionareAccountSelect;
		}
	
	public void setQuestionareAccountSelect(
		QuestionareAccountSelect questionareAccountSelect) {
		this.questionareAccountSelect = questionareAccountSelect;
		}
	
	
	public List collectQuestionareAccountList() {
		questionareAccountSelect = new QuestionareAccountSelect(getDataSource(),selectQuestionareAccountSQL,questionareAccountTableDefs);
		List questionareAccountList = questionareAccountSelect.execute();
		return questionareAccountList;
		}
	
	
	public void saveQuestionareAccount(QuestionareAccount questionareAccount) {
		questionareAccount.setParams();
		String sql = insertQuestionareAccountSQL;
		int[] types = questionareAccount.getTypes();
		Object[]params = questionareAccount.getParams();
		Long id = questionareAccount.getQuestionareAccountId();
		if (id != null){
			sql = updateQuestionareAccountSQL;
			types = questionareAccount.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			questionareAccount.setQuestionareAccountId(getPrimaryKey(primaryKeyQuestionareAccountSQL,primaryKeyTablesDefs));
			System.out.println("har lagret sjemakonto fra questionatable4sDaotable"+ questionareAccount.getAccountName());
	}
	}
	
/**
 * This routine: setter and getter for the operations on the litteratur table
 */

	
	public String getInsertLiteraryReferenceSQL() {
		return insertLiteraryReferenceSQL;
	}
	public void setInsertLiteraryReferenceSQL(String insertLiteraryReferenceSQL) {
		this.insertLiteraryReferenceSQL = insertLiteraryReferenceSQL;
	}
	public String getUpdateLiteraryReferenceSQL() {
		return updateLiteraryReferenceSQL;
	}
	public void setUpdateLiteraryReferenceSQL(String updateLiteraryReferenceSQL) {
		this.updateLiteraryReferenceSQL = updateLiteraryReferenceSQL;
	}
	public String getSelectLiteraryReferenceSQL() {
		return selectLiteraryReferenceSQL;
	}
	public void setSelectLiteraryReferenceSQL(String selectLiteraryReferenceSQL) {
		this.selectLiteraryReferenceSQL = selectLiteraryReferenceSQL;
	}
	public String getPrimaryKeyLiteraryReferenceSQL() {
		return primaryKeyLiteraryReferenceSQL;
	}
	public void setPrimaryKeyLiteraryReferenceSQL(
			String primaryKeyLiteraryReferenceSQL) {
		this.primaryKeyLiteraryReferenceSQL = primaryKeyLiteraryReferenceSQL;
	}
	public String[] getLiteraryReferenceTableDefs() {
		return literaryReferenceTableDefs;
	}
	public void setLiteraryReferenceTableDefs(String[] literaryReferenceTableDefs) {
		this.literaryReferenceTableDefs = literaryReferenceTableDefs;
	}
	public LiteraryReferenceSelect getLiteraryReferenceSelect() {
		return literaryReferenceSelect;
	}
	public void setLiteraryReferenceSelect(
			LiteraryReferenceSelect literaryReferenceSelect) {
		this.literaryReferenceSelect = literaryReferenceSelect;
	}
	
	// spørs om vi trenger en liste over LiteraryReferences
	public List collectLiteraryReferences(){
		literaryReferenceSelect= new LiteraryReferenceSelect(getDataSource(),selectLiteraryReferenceSQL,literaryReferenceTableDefs);
		List literaryList = literaryReferenceSelect.execute();
		return literaryList;
	}
	
	public void saveLiteraryReference (LiteraryReference literaryReference){
		literaryReference.setParams();
		String sql = insertLiteraryReferenceSQL;
		int[] types = literaryReference.getTypes();
		Object[]params = literaryReference.getParams();
		Long id = literaryReference.getLiteraryReferenceId();
		if (id != null){
			sql = updateLiteraryReferenceSQL;
			types = literaryReference.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			literaryReference.setLiteraryReferenceId(getPrimaryKey(primaryKeyLiteraryReferenceSQL,primaryKeyTablesDefs ));
		}
	}
	
	
	
}	
	
	
	
	
	
	
	
	
	


	
	
	
	
	


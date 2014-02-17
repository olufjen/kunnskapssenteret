package no.naks.skjemabank.service;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;


/**
 * Denne klassen er en implementasjon av klassen QuestionTablesService
 * 
 * Følgende tabeller blir berørt i db:
 * 		- PatientGroup
 * 		- InputType
 **/
public class QuestionTablesServiceImpl implements QuestionTablesService {
	
	private QuestionTablesDAO questionTablesDAO;
	
	public QuestionTablesDAO getQuestionTablesDAO() {
		return questionTablesDAO;
	}

	public void setQuestionTablesDAO(QuestionTablesDAO questionTablesDAO) {
		this.questionTablesDAO = questionTablesDAO;
	}
	

	public List collectInputType(){
		return questionTablesDAO.collectInputType();
	}
	
	public void saveInputType(InputType inputType){
		questionTablesDAO.saveInputType(inputType);
	}



	public List collectExaminationOwners(){
		return questionTablesDAO.collectExaminationOwners();
	}
	public void saveExaminationOwner(ExaminationOwner exam) {
		questionTablesDAO.saveExaminationOwner(exam);
		
	}

	public List collectInstitutionTypes() {
	
		return questionTablesDAO.collectInstitutionTypes();
	}

	public void saveInstitutionType(InstitutionType institut) {
		questionTablesDAO.saveInstitutionType(institut);
		
	}

	public List collectInformants() {
		return questionTablesDAO.collectInformants();
		
	}

	public void saveInformant(Informant informant) {
		questionTablesDAO.saveInformant(informant);
		
	}
	
	public List collectSubjects(Long skjemaId) {
		return questionTablesDAO.collectSubjects(skjemaId);
		
	}

	public List collectAllSubjects() {
		return questionTablesDAO.collectAllSubjects();
		
	}
	public void saveSubject(Subject subject) {
		questionTablesDAO.saveSubject(subject);
		
	}
	public void updateSubjectline(SubjectLine subjectLine){
		questionTablesDAO.updateSubjectline(subjectLine);
	}
	public void insertSubjectline(SubjectLine subjectLine){
		questionTablesDAO.insertSubjectline(subjectLine);
	}
	public void deleteSubjectline(Long questionId){
		questionTablesDAO.deleteSubjectline(questionId);
	}	
	public void deleteSubjectlineSubject(Long sId){
		questionTablesDAO.deleteSubjectlineSubject(sId) ;
	}
	public void deleteSubject(Long subjectId){
		questionTablesDAO.deleteSubject(subjectId);	
	}

	public void deleteNokkelordskjemalinje(Long informantid){
		questionTablesDAO.deleteNokkelordskjemalinje(informantid) ;
	}
	public List collectOwners() {
		
		return questionTablesDAO.collectOwners();
	}

	public void saveOwner(Owner owner) {
		 questionTablesDAO.saveOwner(owner);
		
	}
	
	
public List collectUsers() {
		
		return questionTablesDAO.collectUsers();
	}

	public void saveUser(User user) {
		 questionTablesDAO.saveUser(user);
		
	}

	public List collectregions() {
		
		return questionTablesDAO.collectregions();
	}

	public void saveRegion(Region reg) {
		
		questionTablesDAO.saveRegion(reg);
		
	}
	
	public List collectdivisions() {
		
		return questionTablesDAO.collectdivisions();
	}

	public void saveDivision(Division div) {
		
		questionTablesDAO.saveDivision(div);
		
	}

	public List collectEnterprises() {
		// TODO Auto-generated method stub
		return questionTablesDAO.collectEnterprises();
	}

	public void saveEnterprise(Enterprise enterprise) {
		questionTablesDAO.saveEnterprise(enterprise);
		
	}
	
	public List collectExaminationList() {
		
		return questionTablesDAO.collectExaminationList();
	}

	public void saveExamination(Examination examination) {
		questionTablesDAO.saveExamination(examination);
		
	}
	
	public List collectIndexList() {
		
		return questionTablesDAO.collectIndexList();
	}

	public void saveIndex(Index index) {
		questionTablesDAO.saveIndex(index);
	}

	
	public List collectQuestionareAccountList() {
		
		return questionTablesDAO.collectQuestionareAccountList();
	}

	
	public void saveQuestionareAccount(QuestionareAccount questionareAccount) {
		
		questionTablesDAO.saveQuestionareAccount(questionareAccount);
		
	}
	
	public List collectLiteraryReferenceList(){
		return questionTablesDAO.collectLiteraryReferences();
	}
	
	public void  saveLiteraryReference(LiteraryReference literaryReference){
		questionTablesDAO.saveLiteraryReference(literaryReference);
	}
	public List getSubjectlineforSubjects(Long qId){
		return questionTablesDAO.getSubjectlineforSubjects(qId) ;
	}
	
	public List collectNokkelord(Long qId){
		return questionTablesDAO.collectNokkelord(qId) ;
	}

}
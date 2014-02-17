package no.naks.skjemabank.dao;

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
import no.naks.skjemabank.model.Answer;
import no.naks.skjemabank.model.AnswerImpl;
import no.naks.skjemabank.model.AnswerLine;
import no.naks.skjemabank.model.AnswerLineImpl;
import no.naks.skjemabank.model.Index;
import no.naks.skjemabank.model.IndexImpl;
import no.naks.skjemabank.model.Question;
import no.naks.skjemabank.model.QuestionImpl;
import no.naks.skjemabank.model.Questionare;
import no.naks.skjemabank.model.QuestionareComment;
import no.naks.skjemabank.model.QuestionareCommentImpl;
import no.naks.skjemabank.model.QuestionareImpl;
import no.naks.skjemabank.model.QuestionareLine;
import no.naks.skjemabank.model.QuestionareLineImpl;
import no.naks.skjemabank.model.Subject;
import no.naks.skjemabank.model.SubjectImpl;
import no.naks.skjemabank.model.SubjectLine;
import no.naks.skjemabank.model.SubjectLineImpl;

/**
 * Denne klassen lagrer og henter spørreskjema, spørsmål og svaralterativer til databasen 
 * @author ojn 
 *
 */
public class QuestionDAOImpl extends AbstractAdmintablesDAO implements QuestionDAO {

	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	private String insertQuestionareSQL;
	private String updateQuestionareSQL;
	private String cloneQuestionareSQL;
	private String selectQuestionareSQL;
	private String selectQuestionarehaveidSQL;
	private String selectInstrumenterSQL;
	private String deleteQuestionareSQL;
	private String deleteskjemalinjeforquestionareSQL;
	private String deletenokkelordskjemalinjeforquestionareSQL;
	private String deleteskjemakommentarforquestionareSQL ;
	private String deleteundersokelseforquestionareSQL ;
	private String deletelitteraturreferanseforquestionareSQL;
	private String deleteIndeksLinjeForQuestionareSQL;
	private String deleteindeksforquestionareSQL;
	private String deleteSvarlinjeForQuestionareSQL;
	private String deleteSvarskalaForQuestionareSQL;

	private String insertQuestinarelineSQL;
	private String updateQuestionarelineSQL;
	private String updateQuestionsOrderSQL;
	private String updateAnswerlineOrderSQL;
	private String selectQuestionareLineSQL;
	private String insertQuestionSQL;
	private String updateQuestionSQL;
	private String updateQuestionPartidSQL;
	private String selectQuestionSQL;
	
	private String deleteQuestionSQL;
	private String deleteIndekslinjeForQuestionSQL;
	private String selectAllQuestionsSQL;
	private String selectQuestionshavepartSQL;
	private String deleteAnswerforQuestionSQL;
	private String deleteQuestionarelineforquestionSQL;
	private String primaryKeyQuestionSQL;
	private String primaryKeyQuestinareSQL;
	private String primaryKeyforquestionarelineSQL;
	private String selectcommentsforquestionareSQL;
	private String insertcommentforquestionareSQL;
	private String updatecommentforquestionareSQL;
	private String primarykeyforCommentsSQL;
	private String selectquestionareforindexSQL;
	private String selectQuestionForIndeksSQL;
	private String insertSubjectSQL;
	private String updateSubjectSQL;
	private String selectSubjectSQL;
	private String selectSubjectsforQuestionSQL;
	private String primaryKeySubjectSQL;
	private String selectquestionSubjectSQL;
	private String insertquestionSubjectSQL;
	private String updatequestionSubjectSQL;
	private String insertindexSQL; //insertindexSQL
	private String updateindexSQL;
	private String insertindexlineSQL;
	private String deleteIndexlineSQL;
	private String deleteTemalinjeSQL;
	private String deleteIndexSQL;
	/**
	 * 
	 */
	private String primaryKeyIndexSQL;

	private String[] sporsmallinjeTableDefs; 
	private String[] subjectTableDefs;
	private String[] indexTableDefs;
	private String[] primaryKeyTablesDefs;
	private String[]questionTableDefs;
	private String[]questionareTableDefs;
	private String[]commentforquestionareTableDefs; 
	
	private String insertnokkelordskjemalinjeSQL;
	private String selectnokkelordskjemalinjeSQL;
	private String[] nokkelordskjemaTableDefs;
	
	/**
	 * 
	 */
	private String[]questionarelineTableDefs;
	private String insertAnswerSQL;
	private String updateAnswerSQL;
	private String selectAnswerSQL;
	private String selectAnswerforQuestionSQL;
	
	private String primaryKeyAnswerSQL;
	private String primaryKeyAnswerlineSQL;
	private String[] answerTableDefs; 
	private String[]answerlineTableDefs;
	private String insertAnswerlineSQL;
	private String updateAnswerlineSQL;
	private String updateAnswerlineForQuestionSQL;
	private String updateSvarskalaidSQL;
	private String selectAnswerLineSQL;
	private String selectAnswerlineforquestionSQL;
	private String selectAnswerlineforanswerSQL;
	private String selectAnswerlineforlistSQL;
	private String deleteAnswerlineSQL;
	
	private Tablesupdate tablesUpdate = null;
	private QuestionareSelect questionareSelect = null;
	private QuestionareLineSelect questionarelineSelect = null;
	private QuestionSelect questionSelect = null;
	private AnswerSelect answerSelect = null;
	private AnswerlineSelect answerlineSelect = null;
	private QuestionareCommentSelect questionareCommentselect = null;
	private IndexSelect indexSelect = null;
	private SubjectlineSelect subjectlineSelect = null;
	private SubjectSelect subjectSelect = null;


	
	private void getQuestionarelines(List questionares){
		Iterator questionareIterator = questionares.iterator();
		Questionare questionare = null;
		while (questionareIterator.hasNext()){
			questionare = (QuestionareImpl)questionareIterator.next();
			Long qId = questionare.getSporreSkjemaid();
			questionare.setQuestionareLine(getQuestionareLines(qId)); // wrong name on method
		}
		
	}
	
	private void getQuestionsforQuestionares (List  questionares){
		Iterator questionareIterator = questionares.iterator();
		Questionare questionare = null;
		while (questionareIterator.hasNext()){
			questionare = (QuestionareImpl)questionareIterator.next();
			Long qId = questionare.getSporreSkjemaid();
			questionare.setQuestions(getQuestions(qId));
		}
		
	}
	
	private void getIndexesforQuestionares(List questionares){
		Iterator questionareIterator = questionares.iterator();
		Questionare questionare = null;
		while (questionareIterator.hasNext()){
			questionare = (QuestionareImpl)questionareIterator.next();
			Long qId = questionare.getSporreSkjemaid();
			questionare.setIndexList(getIndexes(qId));
			
		}
	}
	
	public List getIndexes(Long qId) {
		int type = Types.INTEGER;
		indexSelect = new IndexSelect(getDataSource(),selectquestionareforindexSQL,indexTableDefs);
		indexSelect.declareParameter(new SqlParameter(type));
		List indexlist = indexSelect.execute(qId);
		Iterator itrIndeksList = indexlist.iterator();
		while(itrIndeksList.hasNext()){
			Index index =(IndexImpl) itrIndeksList.next();
			getQuestionsForIndeks(index);
		}
		return indexlist;
		
	}
	
	
	/**
	 * getQuestions
	 * This routine collects all questions related to a indeksid
	 * This routine must be reworked to follow new data model OJN 01.10.08
	 * The list of question objects has been removed from the indeks object OJN 07.10.10
	 * @param index
	 * @return
	 */
	private void getQuestionsForIndeks(Index index){
		
		Long indeksId = index.getIndexId();
		List<QuestionImpl> questions = new ArrayList<QuestionImpl>();
		List questionIds = new ArrayList<Long>();
		int type = Types.INTEGER;
		Object[]params = {indeksId,indeksId};
		questionSelect = new QuestionSelect(getDataSource(),selectQuestionForIndeksSQL,questionTableDefs);
		questionSelect.declareParameter(new SqlParameter(type));
		questions = questionSelect.execute(indeksId);
	//	questions = questionSelect.execute(params);
		for (Question question : questions ){
			questionIds.add(question.getSporsmalid());
		}
//		index.setQuestionsForIndeksList(questions);
		index.setQuestionIds(questionIds);
		
	}
	
	public void deleteSvarlinje (Long qId){
		int type = Types.INTEGER;
		String sql = deleteAnswerforQuestionSQL;
		long id = qId.longValue();
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
	}

	
	private void getCommentsforQuestionares(List questionares){
		Iterator questionareIterator = questionares.iterator();
		Questionare questionare = null;
		while (questionareIterator.hasNext()){
			questionare = (QuestionareImpl)questionareIterator.next();
			Long qId = questionare.getSporreSkjemaid();
			questionare.setComments(getComments(qId));
		}
	}
	private List getComments(Long qId){
		long quId = qId.longValue();
		int type = Types.INTEGER;
		questionareCommentselect = new QuestionareCommentSelect(getDataSource(),selectcommentsforquestionareSQL,commentforquestionareTableDefs);
		questionareCommentselect.declareParameter(new SqlParameter(type));
		List comments = questionareCommentselect.execute(quId);
		return comments;
	}
	/**
	 * getQuestions
	 * This routine collects all questions connected to a questionare
	 * @param qId
	 * @return
	 */
	public List getQuestions(Long qId){
		long quId = qId.longValue();
		int type = Types.INTEGER;
		questionSelect = new QuestionSelect(getDataSource(),selectQuestionSQL,questionTableDefs);
		questionSelect.declareParameter(new SqlParameter(type));
		List questions = questionSelect.execute(quId);
		Iterator questionIterator = questions.iterator();
		Question question = null;
		while (questionIterator.hasNext()){
			question = (QuestionImpl)questionIterator.next();
			Long questionId = question.getSporsmalid();
			question.setAnswerLine(getAnswerlineforQuestion(questionId));
			List answerIds = getAnswerIds(question.getAnswerLine());
			question.setAnswers(getAnswerforQuestion(answerIds));
			List subjectLines = getSubjectlineforQuestion(questionId);
			question.setSubjectLine(subjectLines);
			question.setSubjects(getSubjectsforQuestion(subjectLines));
		}
		return questions;
	}
	
	/**
	 * getAnswerIds
	 * This routines returns a list of answerline IDs
	 * It is used to collect all answers to a question
	 * @param answerlines: A list of answerlines
	 * @return a list of answerlineIDs
	 */
	private List getAnswerIds(List answerlines){
		List answerIds = new ArrayList();
		AnswerLine answerline = null;
		Iterator answerlineIterator = answerlines.iterator();
		int index = 0;
		while (answerlineIterator.hasNext()){
			answerline = (AnswerLineImpl)answerlineIterator.next();
			if (!answerIds.contains(answerline.getSvarSkalaid())){
				answerIds.add(index, answerline.getSvarSkalaid());
				index++;
			}
			
		}
		return answerIds;
		
	}
	/**
	 * getQuestionareLines
	 * This routine collects all questionarelines for a particular questionare
	 * @param qId
	 * @return
	 */
	private List getQuestionareLines(Long qId){
		long quId = qId.longValue();
		int type = Types.INTEGER;
		questionarelineSelect = new QuestionareLineSelect(getDataSource(),selectQuestionareLineSQL,questionarelineTableDefs);
		questionarelineSelect.declareParameter(new SqlParameter(type));
		List questionarelines = questionarelineSelect.execute(quId);
		return questionarelines;
	
	}
	/**
	 * getSubjectlineforQuestion
	 * This routines collects all subjectlines for a question.
	 * (tblsporsmalinje)
	 * @param qId
	 * @return a list of subjectlines
	 */
	private List getSubjectlineforQuestion(Long qId){
		long quId = qId.longValue();
		int type = Types.INTEGER;
		List subjectlines = null;
		subjectlineSelect = new SubjectlineSelect(getDataSource(),selectquestionSubjectSQL,sporsmallinjeTableDefs);
		subjectlineSelect.declareParameter(new SqlParameter(type));
		subjectlines = subjectlineSelect.execute(quId);
		return subjectlines;
	}
	/**
	 * getAnswerlineforQuestion
	 * This routine collects all answerlines for a particular question
	 * @param qId
	 * @return
	 */
	private List getAnswerlineforQuestion(Long qId){
		long quId = qId.longValue();
		int type = Types.INTEGER;
		List answerlines = null;
		answerlineSelect = new AnswerlineSelect(getDataSource(),selectAnswerlineforquestionSQL,answerlineTableDefs);
		answerlineSelect.declareParameter(new SqlParameter(type));
		answerlines = answerlineSelect.execute(quId);
		return answerlines;
		
	}
	/**
	 * getAnswerforQuestion
	 * This routine collects all answers (tblsvarskala) for a particular question
	 * @param qId
	 * @return
	 */
	private List getAnswerforQuestion(List ids){
		List answers = null;
		if (ids != null && ids.size() > 0){
			Long qId = (Long)ids.get(0);
			long quId = qId.longValue();
			int type = Types.INTEGER;

			answerSelect = new AnswerSelect(getDataSource(),selectAnswerforQuestionSQL,answerTableDefs);
			answerSelect.declareParameter(new SqlParameter(type));
			answers = answerSelect.execute(quId);
			Iterator answerIterator = answers.iterator();
			while (answerIterator.hasNext()){
				Answer answer = (AnswerImpl)answerIterator.next();
				Long aId = answer.getSvarSkalaid();
				answer.setAnswerLine(getAnswerlineforAnswer(aId));
			}
		}
		return answers;
		
	}
	
	private List getSubjectsforQuestion(List lines){
		List<SubjectImpl>  subjects  = new ArrayList<SubjectImpl>();
		if (lines != null && lines.size() > 0){
			int size = lines.size();
			for(int i=0; i<size; i++){
				List<SubjectImpl>  subject  = null;
				SubjectLine subjectLine = (SubjectLineImpl)lines.get(i);
				Long qId = subjectLine.getSubjectId();
				long quId = qId.longValue();
				int type = Types.INTEGER;
	
				subjectSelect = new SubjectSelect(getDataSource(),selectSubjectsforQuestionSQL,subjectTableDefs);
				subjectSelect.declareParameter(new SqlParameter(type));
				subject = subjectSelect.execute(quId);
				if(subject != null)
					subjects.addAll(subject);
				/*
				Iterator subjectIterator = subjects.iterator();
				while (subjectIterator.hasNext()){
					Subject subject = (SubjectImpl)subjectIterator.next();
					Long sId = subject.getSubjectId();
					answer.setAnswerLine(getAnswerlineforAnswer(aId));
				}
				*/
			}
		}
		return subjects;
		
	}
	
	/**
	 * getSubject
	 * This routine will return the subject on the base of subject id
	 * @param subjectId
	 */
	
	public List getSubject(Long subjectId){
		List<SubjectImpl>  subject  = null;
		long sId = subjectId.longValue();
		int type = Types.INTEGER;
		
		subjectSelect = new SubjectSelect(getDataSource(),selectSubjectsforQuestionSQL,subjectTableDefs);
		subjectSelect.declareParameter(new SqlParameter(type));
		subject = subjectSelect.execute(sId);
		return subject;
	}
	
	/**
	 * saveSubjectline
	 * This routine saves the subjectline belonging to a question
	 * @param subjectLine
	 */
	private void saveSubjectLine(List subjectLine){
		
	}
	/**
	 * getAnswerlineforAnswer
	 * THis routine collects all answerlines for a particular answer
	 * @param aId - answer id
	 * @return
	 */
	private List getAnswerlineforAnswer(Long aId){
		long aid = aId.longValue();
		int type = Types.INTEGER;
		List answerlines = null;
		answerlineSelect = new AnswerlineSelect(getDataSource(),selectAnswerlineforanswerSQL,answerlineTableDefs);
		answerlineSelect.declareParameter(new SqlParameter(type));
		answerlines = answerlineSelect.execute(aid);
		return answerlines;
	}
	
	/**
	 * This routine collects all the list of altsvartekst for a particular answer 
	 * @param aId
	 * @return
	 */
	public List getAnswerlineforList(Long aId){
		long aid = aId.longValue();
		int type= Types.INTEGER;
		List answerlines = null;
		answerlineSelect = new AnswerlineSelect(getDataSource(),selectAnswerlineforlistSQL,answerlineTableDefs);
		answerlineSelect.declareParameter(new SqlParameter(type));
		answerlines = answerlineSelect.execute(aid);
		return answerlines;
	}
	
	
	public String getDeleteIndexlineSQL() {
		return deleteIndexlineSQL;
	}
	public void setDeleteIndexlineSQL(String deleteIndexlineSQL) {
		this.deleteIndexlineSQL = deleteIndexlineSQL;
	}
	public String getInsertindexlineSQL() {
		return insertindexlineSQL;
	}
	
	public String getDeleteTemalinjeSQL() {
		return deleteTemalinjeSQL;
	}
	public void setDeleteTemalinjeSQL(String deleteTemalinjeSQL) {
		this.deleteTemalinjeSQL = deleteTemalinjeSQL;
	}
	
	public String getDeleteIndexSQL() {
		return deleteIndexSQL;
	}
	public void setDeleteIndexSQL(String deleteIndexSQL) {
		this.deleteIndexSQL = deleteIndexSQL;
	}
	
	public void setInsertindexlineSQL(String insertindexlineSQL) {
		this.insertindexlineSQL = insertindexlineSQL;
	}
	public String getInsertindexSQL() {
		return insertindexSQL;
	}
	public void setInsertindexSQL(String insertindexSQL) {
		this.insertindexSQL = insertindexSQL;
	}
	public String getUpdateindexSQL() {
		return updateindexSQL;
	}
	public void setUpdateindexSQL(String updateindexSQL) {
		this.updateindexSQL = updateindexSQL;
	}
	public String getPrimaryKeyIndexSQL() {
		return primaryKeyIndexSQL;
	}
	public void setPrimaryKeyIndexSQL(String primaryKeyIndexSQL) {
		this.primaryKeyIndexSQL = primaryKeyIndexSQL;
	}
	/**
	 * @return the selectAllQuestionsSQL
	 */
	public String getSelectAllQuestionsSQL() {
		return selectAllQuestionsSQL;
	}
	/**
	 * @param selectAllQuestionsSQL the selectAllQuestionsSQL to set
	 */
	public void setSelectAllQuestionsSQL(String selectAllQuestionsSQL) {
		this.selectAllQuestionsSQL = selectAllQuestionsSQL;
	}
	
	/**
	 * @return the selectQuestionshavepartSQL
	 */
	public String getselectQuestionshavepartSQL() {
		return selectQuestionshavepartSQL;
	}
	/**
	 * @param selectAllQuestionsSQL the selectAllQuestionsSQL to set
	 */
	public void setselectQuestionshavepartSQL(String selectQuestionshavepartSQL) {
		this.selectQuestionshavepartSQL = selectQuestionshavepartSQL;
	}
	
	/**
	 * @return the deleteAnswerforQuestionSQL
	 */
	public String getDeleteAnswerforQuestionSQL() {
		return deleteAnswerforQuestionSQL;
	}
	/**
	 * @param deleteAnswerforQuestionSQL the deleteAnswerforQuestionSQL to set
	 */
	public void setDeleteAnswerforQuestionSQL(String deleteAnswerforQuestionSQL) {
		this.deleteAnswerforQuestionSQL = deleteAnswerforQuestionSQL;
	}
	/**
	 * @return the deleteQuestionarelineforquestionSQL
	 */
	public String getDeleteQuestionarelineforquestionSQL() {
		return deleteQuestionarelineforquestionSQL;
	}
	/**
	 * @param deleteQuestionarelineforquestionSQL the deleteQuestionarelineforquestionSQL to set
	 */
	public void setDeleteQuestionarelineforquestionSQL(
			String deleteQuestionarelineforquestionSQL) {
		this.deleteQuestionarelineforquestionSQL = deleteQuestionarelineforquestionSQL;
	}
	/**
	 * @return the deleteQuestionSQL
	 */
	public String getDeleteQuestionSQL() {
		return deleteQuestionSQL;
	}
	/**
	 * @param deleteQuestionSQL the deleteQuestionSQL to set
	 */
	public void setDeleteQuestionSQL(String deleteQuestionSQL) {
		this.deleteQuestionSQL = deleteQuestionSQL;
	}
	
	public String getDeleteIndekslinjeForQuestionSQL() {
		return deleteIndekslinjeForQuestionSQL;
	}
	public void setDeleteIndekslinjeForQuestionSQL(
			String deleteIndekslinjeForQuestionSQL) {
		this.deleteIndekslinjeForQuestionSQL = deleteIndekslinjeForQuestionSQL;
	}
	/**
	 * @return the deleteAnswerlineSQL
	 */
	public String getDeleteAnswerlineSQL() {
		return deleteAnswerlineSQL;
	}
	/**
	 * @param deleteAnswerlineSQL the deleteAnswerlineSQL to set
	 */
	public void setDeleteAnswerlineSQL(String deleteAnswerlineSQL) {
		this.deleteAnswerlineSQL = deleteAnswerlineSQL;
	}
	/**
	 * @return the primaryKeyforquestionarelineSQL
	 */
	public String getPrimaryKeyforquestionarelineSQL() {
		return primaryKeyforquestionarelineSQL;
	}
	/**
	 * @param primaryKeyforquestionarelineSQL the primaryKeyforquestionarelineSQL to set
	 */
	public void setPrimaryKeyforquestionarelineSQL(
			String primaryKeyforquestionarelineSQL) {
		this.primaryKeyforquestionarelineSQL = primaryKeyforquestionarelineSQL;
	}
	/**
	 * @return the primarykeyforCommentsSQL
	 */
	public String getPrimarykeyforCommentsSQL() {
		return primarykeyforCommentsSQL;
	}
	/**
	 * @param primarykeyforCommentsSQL the primarykeyforCommentsSQL to set
	 */
	public void setPrimarykeyforCommentsSQL(String primarykeyforCommentsSQL) {
		this.primarykeyforCommentsSQL = primarykeyforCommentsSQL;
	}
	/**
	 * @return the commentforquestionareTableDefs
	 */
	public String[] getCommentforquestionareTableDefs() {
		return commentforquestionareTableDefs;
	}
	/**
	 * @param commentforquestionareTableDefs the commentforquestionareTableDefs to set
	 */
	public void setCommentforquestionareTableDefs(
			String[] commentforquestionareTableDefs) {
		this.commentforquestionareTableDefs = commentforquestionareTableDefs;
	}
	/**
	 * @return the insertcommentforquestionareSQL
	 */
	public String getInsertcommentforquestionareSQL() {
		return insertcommentforquestionareSQL;
	}
	/**
	 * @param insertcommentforquestionareSQL the insertcommentforquestionareSQL to set
	 */
	public void setInsertcommentforquestionareSQL(
			String insertcommentforquestionareSQL) {
		this.insertcommentforquestionareSQL = insertcommentforquestionareSQL;
	}
	/**
	 * @return the selectcommentsforquestionareSQL
	 */
	public String getSelectcommentsforquestionareSQL() {
		return selectcommentsforquestionareSQL;
	}
	/**
	 * @param selectcommentsforquestionareSQL the selectcommentsforquestionareSQL to set
	 */
	public void setSelectcommentsforquestionareSQL(
			String selectcommentsforquestionareSQL) {
		this.selectcommentsforquestionareSQL = selectcommentsforquestionareSQL;
	}
	/**
	 * @return the updatecommentforquestionareSQL
	 */
	public String getUpdatecommentforquestionareSQL() {
		return updatecommentforquestionareSQL;
	}
	/**
	 * @param updatecommentforquestionareSQL the updatecommentforquestionareSQL to set
	 */
	public void setUpdatecommentforquestionareSQL(
			String updatecommentforquestionareSQL) {
		this.updatecommentforquestionareSQL = updatecommentforquestionareSQL;
	}
	/**
	 * @return the selectAnswerlineforanswerSQL
	 */
	public String getSelectAnswerlineforanswerSQL() {
		return selectAnswerlineforanswerSQL;
	}
	/**
	 * @param selectAnswerlineforanswerSQL the selectAnswerlineforanswerSQL to set
	 */
	public void setSelectAnswerlineforanswerSQL(String selectAnswerlineforanswerSQL) {
		this.selectAnswerlineforanswerSQL = selectAnswerlineforanswerSQL;
	}
	
	
	public String getSelectAnswerlineforlistSQL() {
		return selectAnswerlineforlistSQL;
	}
	public void setSelectAnswerlineforlistSQL(String selectAnswerlineforlistSQL) {
		this.selectAnswerlineforlistSQL = selectAnswerlineforlistSQL;
	}
	/**
	 * @return the selectAnswerforQuestionSQL
	 */
	public String getSelectAnswerforQuestionSQL() {
		return selectAnswerforQuestionSQL;
	}
	/**
	 * @param selectAnswerforQuestionSQL the selectAnswerforQuestionSQL to set
	 */
	public void setSelectAnswerforQuestionSQL(String selectAnswerforQuestionSQL) {
		this.selectAnswerforQuestionSQL = selectAnswerforQuestionSQL;
	}
	/**
	 * @return the selectAnswerlineforquestionSQL
	 */
	public String getSelectAnswerlineforquestionSQL() {
		return selectAnswerlineforquestionSQL;
	}
	/**
	 * @param selectAnswerlineforquestionSQL the selectAnswerlineforquestionSQL to set
	 */
	public void setSelectAnswerlineforquestionSQL(
			String selectAnswerlineforquestionSQL) {
		this.selectAnswerlineforquestionSQL = selectAnswerlineforquestionSQL;
	}
	/**
	 * @return the selectAnswerLineSQL
	 */
	public String getSelectAnswerLineSQL() {
		return selectAnswerLineSQL;
	}
	/**
	 * @param selectAnswerLineSQL the selectAnswerLineSQL to set
	 */
	public void setSelectAnswerLineSQL(String selectAnswerLineSQL) {
		this.selectAnswerLineSQL = selectAnswerLineSQL;
	}
	/**
	 * @return the selectQuestionareLineSQL
	 */
	public String getSelectQuestionareLineSQL() {
		return selectQuestionareLineSQL;
	}
	/**
	 * @param selectQuestionareLineSQL the selectQuestionareLineSQL to set
	 */
	public void setSelectQuestionareLineSQL(String selectQuestionareLineSQL) {
		this.selectQuestionareLineSQL = selectQuestionareLineSQL;
	}
	/**
	 * @return the selectQuestionareSQL
	 */
	public String getSelectQuestionareSQL() {
		return selectQuestionareSQL;
	}



	/**
	 * @param selectQuestionareSQL the selectQuestionareSQL to set
	 */
	public void setSelectQuestionareSQL(String selectQuestionareSQL) {
		this.selectQuestionareSQL = selectQuestionareSQL;
	}

	
	public String getSelectQuestionarehaveidSQL() {
		return selectQuestionarehaveidSQL;
	}

	public void setSelectQuestionarehaveidSQL(String selectQuestionarehaveidSQL) {
		this.selectQuestionarehaveidSQL = selectQuestionarehaveidSQL;
	}

	public String getSelectInstrumenterSQL() {
		return selectInstrumenterSQL;
	}
	public void setSelectInstrumenterSQL(String selectInstrumenterSQL) {
		this.selectInstrumenterSQL = selectInstrumenterSQL;
	}
	/**
	 * @return the answerlineTableDefs
	 */
	public String[] getAnswerlineTableDefs() {
		return answerlineTableDefs;
	}



	/**
	 * @param answerlineTableDefs the answerlineTableDefs to set
	 */
	public void setAnswerlineTableDefs(String[] answerlineTableDefs) {
		this.answerlineTableDefs = answerlineTableDefs;
	}



	/**
	 * @return the questionarelineTableDefs
	 */
	public String[] getQuestionarelineTableDefs() {
		return questionarelineTableDefs;
	}



	/**
	 * @param questionarelineTableDefs the questionarelineTableDefs to set
	 */
	public void setQuestionarelineTableDefs(String[] questionarelineTableDefs) {
		this.questionarelineTableDefs = questionarelineTableDefs;
	}



	/**
	 * @return the questionareTableDefs
	 */
	public String[] getQuestionareTableDefs() {
		return questionareTableDefs;
	}

/**
 * 
 * @return Delete the Questionare
 */

	public String getDeleteQuestionareSQL() {
		return deleteQuestionareSQL;
	}
	public void setDeleteQuestionareSQL(String deleteQuestionareSQL) {
		this.deleteQuestionareSQL = deleteQuestionareSQL;
	}
	
	public String getDeleteskjemalinjeforquestionareSQL() {
		return deleteskjemalinjeforquestionareSQL;
	}

	public String getDeleteSvarlinjeForQuestionareSQL() {
		return deleteSvarlinjeForQuestionareSQL;
	}

	public void setDeleteSvarlinjeForQuestionareSQL(
			String deleteSvarlinjeForQuestionareSQL) {
		this.deleteSvarlinjeForQuestionareSQL = deleteSvarlinjeForQuestionareSQL;
	}

	public String getDeleteSvarskalaForQuestionareSQL() {
		return deleteSvarskalaForQuestionareSQL;
	}

	public void setDeleteSvarskalaForQuestionareSQL(
			String deleteSvarskalaForQuestionareSQL) {
		this.deleteSvarskalaForQuestionareSQL = deleteSvarskalaForQuestionareSQL;
	}

	public void setDeleteskjemalinjeforquestionareSQL(
			String deleteskjemalinjeforquestionareSQL) {
		this.deleteskjemalinjeforquestionareSQL = deleteskjemalinjeforquestionareSQL;
	}
	public String getDeletenokkelordskjemalinjeforquestionareSQL() {
		return deletenokkelordskjemalinjeforquestionareSQL;
	}
	public void setDeletenokkelordskjemalinjeforquestionareSQL(
			String deletenokkelordskjemalinjeforquestionareSQL) {
		this.deletenokkelordskjemalinjeforquestionareSQL = deletenokkelordskjemalinjeforquestionareSQL;
	}
	public String getDeleteskjemakommentarforquestionareSQL() {
		return deleteskjemakommentarforquestionareSQL;
	}
	public void setDeleteskjemakommentarforquestionareSQL(
			String deleteskjemakommentarforquestionareSQL) {
		this.deleteskjemakommentarforquestionareSQL = deleteskjemakommentarforquestionareSQL;
	}
	public String getDeleteundersokelseforquestionareSQL() {
		return deleteundersokelseforquestionareSQL;
	}
	public void setDeleteundersokelseforquestionareSQL(
			String deleteundersokelseforquestionareSQL) {
		this.deleteundersokelseforquestionareSQL = deleteundersokelseforquestionareSQL;
	}
	/**
	 * @param questionareTableDefs the questionareTableDefs to set
	 */
	public void setQuestionareTableDefs(String[] questionareTableDefs) {
		this.questionareTableDefs = questionareTableDefs;
	}

	public String getDeletelitteraturreferanseforquestionareSQL() {
		return deletelitteraturreferanseforquestionareSQL;
	}
	public void setDeletelitteraturreferanseforquestionareSQL(
			String deletelitteraturreferanseforquestionareSQL) {
		this.deletelitteraturreferanseforquestionareSQL = deletelitteraturreferanseforquestionareSQL;
	}
	
	
	
	public String getDeleteindeksforquestionareSQL() {
		return deleteindeksforquestionareSQL;
	}

	public void setDeleteindeksforquestionareSQL(
			String deleteindeksforquestionareSQL) {
		this.deleteindeksforquestionareSQL = deleteindeksforquestionareSQL;
	}

	public String getDeleteIndeksLinjeForQuestionareSQL() {
		return this.deleteIndeksLinjeForQuestionareSQL;
	}
	
	public void setDeleteIndeksLinjeForQuestionareSQL(String newDeleteIndeksLinjeForQuestionareSQL) {
		this.deleteIndeksLinjeForQuestionareSQL = newDeleteIndeksLinjeForQuestionareSQL;
	}
	
	/**
	 * @return the primaryKeyAnswerlineSQL
	 */
	public String getPrimaryKeyAnswerlineSQL() {
		return primaryKeyAnswerlineSQL;
	}



	/**
	 * @param primaryKeyAnswerlineSQL the primaryKeyAnswerlineSQL to set
	 */
	public void setPrimaryKeyAnswerlineSQL(String primaryKeyAnswerlineSQL) {
		this.primaryKeyAnswerlineSQL = primaryKeyAnswerlineSQL;
	}

	/**
	 * @return the primaryKeyQuestinareSQL
	 */
	public String getPrimaryKeyQuestinareSQL() {
		return primaryKeyQuestinareSQL;
	}



	/**
	 * @param primaryKeyQuestinareSQL the primaryKeyQuestinareSQL to set
	 */
	public void setPrimaryKeyQuestinareSQL(String primaryKeyQuestinareSQL) {
		this.primaryKeyQuestinareSQL = primaryKeyQuestinareSQL;
	}



	/**
	 * @return the insertAnswerlineSQL
	 */
	public String getInsertAnswerlineSQL() {
		return insertAnswerlineSQL;
	}



	/**
	 * @param insertAnswerlineSQL the insertAnswerlineSQL to set
	 */
	public void setInsertAnswerlineSQL(String insertAnswerlineSQL) {
		this.insertAnswerlineSQL = insertAnswerlineSQL;
	}



	/**
	 * @return the insertQuestinarelineSQL
	 */
	public String getInsertQuestinarelineSQL() {
		return insertQuestinarelineSQL;
	}



	/**
	 * @param insertQuestinarelineSQL the insertQuestinarelineSQL to set
	 */
	public void setInsertQuestinarelineSQL(String insertQuestinarelineSQL) {
		this.insertQuestinarelineSQL = insertQuestinarelineSQL;
	}



	/**
	 * @return the insertQuestionareSQL
	 */
	public String getInsertQuestionareSQL() {
		return insertQuestionareSQL;
	}



	/**
	 * @param insertQuestionareSQL the insertQuestionareSQL to set
	 */
	public void setInsertQuestionareSQL(String insertQuestionareSQL) {
		this.insertQuestionareSQL = insertQuestionareSQL;
	}

	/**
	 * @return the updateAnswerlineSQL
	 */
	public String getUpdateAnswerlineSQL() {
		return updateAnswerlineSQL;
	}



	/**
	 * @param updateAnswerlineSQL the updateAnswerlineSQL to set
	 */
	public void setUpdateAnswerlineSQL(String updateAnswerlineSQL) {
		this.updateAnswerlineSQL = updateAnswerlineSQL;
	}

  	public String getUpdateAnswerlineForQuestionSQL() {
		return updateAnswerlineForQuestionSQL;
	}

	public void setUpdateAnswerlineForQuestionSQL(
			String updateAnswerlineForQuestionSQL) {
		this.updateAnswerlineForQuestionSQL = updateAnswerlineForQuestionSQL;
	}

	public String getUpdateSvarskalaidSQL() {
		return updateSvarskalaidSQL;
	}

	public void setUpdateSvarskalaidSQL(String updateSvarskalaidSQL) {
		this.updateSvarskalaidSQL = updateSvarskalaidSQL;
	}

	/**
	 * @return the updateQuestionarelineSQL
	 */
	public String getUpdateQuestionarelineSQL() {
		return updateQuestionarelineSQL;
	}



	/**
	 * @param updateQuestionarelineSQL the updateQuestionarelineSQL to set
	 */
	public void setUpdateQuestionarelineSQL(String updateQuestionarelineSQL) {
		this.updateQuestionarelineSQL = updateQuestionarelineSQL;
	}

	public void setCloneQuestionareSQL(String newCloneQuestionareSQL) {
		this.cloneQuestionareSQL = newCloneQuestionareSQL;
	}
	
	public String getCloneQuestionareSQL() {
		return this.cloneQuestionareSQL;
	}
	
	public String getupdateQuestionsOrderSQL() {
		return updateQuestionsOrderSQL;
	}
	
	public void setupdateQuestionsOrderSQL(
			String updateQuestionsOrderSQL) {
		this.updateQuestionsOrderSQL = updateQuestionsOrderSQL;
	}

	
	public String getUpdateAnswerlineOrderSQL() {
		return updateAnswerlineOrderSQL;
	}

	public void setUpdateAnswerlineOrderSQL(String updateAnswerlineOrderSQL) {
		this.updateAnswerlineOrderSQL = updateAnswerlineOrderSQL;
	}

	/**
	 * @return the updateQuestionareSQL
	 */
	public String getUpdateQuestionareSQL() {
		return updateQuestionareSQL;
	}

	/**
	 * @param updateQuestionareSQL the updateQuestionareSQL to set
	 */
	public void setUpdateQuestionareSQL(String updateQuestionareSQL) {
		this.updateQuestionareSQL = updateQuestionareSQL;
	}



	/**
	 * @return the answerTableDefs
	 */
	public String[] getAnswerTableDefs() {
		return answerTableDefs;
	}



	/**
	 * @param answerTableDefs the answerTableDefs to set
	 */
	public void setAnswerTableDefs(String[] answerTableDefs) {
		this.answerTableDefs = answerTableDefs;
	}



	/**
	 * @return the insertAnswerSQL
	 */
	public String getInsertAnswerSQL() {
		return insertAnswerSQL;
	}



	/**
	 * @param insertAnswerSQL the insertAnswerSQL to set
	 */
	public void setInsertAnswerSQL(String insertAnswerSQL) {
		this.insertAnswerSQL = insertAnswerSQL;
	}



	/**
	 * @return the primaryKeyAnswerSQL
	 */
	public String getPrimaryKeyAnswerSQL() {
		return primaryKeyAnswerSQL;
	}



	/**
	 * @param primaryKeyAnswerSQL the primaryKeyAnswerSQL to set
	 */
	public void setPrimaryKeyAnswerSQL(String primaryKeyAnswerSQL) {
		this.primaryKeyAnswerSQL = primaryKeyAnswerSQL;
	}



	/**
	 * @return the selectAnswerSQL
	 */
	public String getSelectAnswerSQL() {
		return selectAnswerSQL;
	}



	/**
	 * @param selectAnswerSQL the selectAnswerSQL to set
	 */
	public void setSelectAnswerSQL(String selectAnswerSQL) {
		this.selectAnswerSQL = selectAnswerSQL;
	}



	/**
	 * @return the updateAnswerSQL
	 */
	public String getUpdateAnswerSQL() {
		return updateAnswerSQL;
	}



	/**
	 * @param updateAnswerSQL the updateAnswerSQL to set
	 */
	public void setUpdateAnswerSQL(String updateAnswerSQL) {
		this.updateAnswerSQL = updateAnswerSQL;
	}



	/**
	 * @return the tablesUpdate
	 */
	public Tablesupdate getTablesUpdate() {
		return tablesUpdate;
	}



	/**
	 * @param tablesUpdate the tablesUpdate to set
	 */
	public void setTablesUpdate(Tablesupdate tablesUpdate) {
		this.tablesUpdate = tablesUpdate;
	}


	/**
	 * @return the primaryKeyQuestionSQL
	 */
	public String getPrimaryKeyQuestionSQL() {
		return primaryKeyQuestionSQL;
	}



	/**
	 * @param primaryKeyQuestionSQL the primaryKeyQuestionSQL to set
	 */
	public void setPrimaryKeyQuestionSQL(String primaryKeyQuestionSQL) {
		this.primaryKeyQuestionSQL = primaryKeyQuestionSQL;
	}



	/**
	 * @return the primaryKeyTablesDefs
	 */
	public String[] getPrimaryKeyTablesDefs() {
		return primaryKeyTablesDefs;
	}



	/**
	 * @param primaryKeyTablesDefs the primaryKeyTablesDefs to set
	 */
	public void setPrimaryKeyTablesDefs(String[] primaryKeyTablesDefs) {
		this.primaryKeyTablesDefs = primaryKeyTablesDefs;
	}



	/**
	 * @return the insertQuestionSQL
	 */
	public String getInsertQuestionSQL() {
		return insertQuestionSQL;
	}



	/**
	 * @param insertQuestionSQL the insertQuestionSQL to set
	 */
	public void setInsertQuestionSQL(String insertQuestionSQL) {
		this.insertQuestionSQL = insertQuestionSQL;
	}
	/**
	 * @return the questionTableDefs
	 */
	public String[] getQuestionTableDefs() {
		return questionTableDefs;
	}



	/**
	 * @param questionTableDefs the questionTableDefs to set
	 */
	public void setQuestionTableDefs(String[] questionTableDefs) {
		this.questionTableDefs = questionTableDefs;
	}


	
	public String getSelectQuestionForIndeksSQL() {
		return selectQuestionForIndeksSQL;
	}
	public void setSelectQuestionForIndeksSQL(String selectQuestionForIndeksSQL) {
		this.selectQuestionForIndeksSQL = selectQuestionForIndeksSQL;
	}
	public String getSelectquestionareforindexSQL() {
		return selectquestionareforindexSQL;
	}
	public void setSelectquestionareforindexSQL(String selectquestionareforindexSQL) {
		this.selectquestionareforindexSQL = selectquestionareforindexSQL;
	}
	public String[] getIndexTableDefs() {
		return indexTableDefs;
	}
	public void setIndexTableDefs(String[] indexTableDefs) {
		this.indexTableDefs = indexTableDefs;
	}

	public IndexSelect getIndexSelect() {
		return indexSelect;
	}
	
	public String getInsertSubjectSQL() {
		return insertSubjectSQL;
	}
	public void setInsertSubjectSQL(String insertSubjectSQL) {
		this.insertSubjectSQL = insertSubjectSQL;
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
	public String getInsertquestionSubjectSQL() {
		return insertquestionSubjectSQL;
	}
	public void setInsertquestionSubjectSQL(String insertquestionSubjectSQL) {
		this.insertquestionSubjectSQL = insertquestionSubjectSQL;
	}
	public String getUpdatequestionSubjectSQL() {
		return updatequestionSubjectSQL;
	}
	public void setUpdatequestionSubjectSQL(String updatequestionSubjectSQL) {
		this.updatequestionSubjectSQL = updatequestionSubjectSQL;
	}
	public String[] getSporsmallinjeTableDefs() {
		return sporsmallinjeTableDefs;
	}
	public void setSporsmallinjeTableDefs(String[] sporsmallinjeTableDefs) {
		this.sporsmallinjeTableDefs = sporsmallinjeTableDefs;
	}
	public String getSelectSubjectSQL() {
		return selectSubjectSQL;
	}
	public void setSelectSubjectSQL(String selectSubjectSQL) {
		this.selectSubjectSQL = selectSubjectSQL;
	}
	public String getPrimaryKeySubjectSQL() {
		return primaryKeySubjectSQL;
	}
	public void setPrimaryKeySubjectSQL(String primaryKeySubjectSQL) {
		this.primaryKeySubjectSQL = primaryKeySubjectSQL;
	}
	public String[] getSubjectTableDefs() {
		return subjectTableDefs;
	}
	public void setSubjectTableDefs(String[] subjectTableDefs) {
		this.subjectTableDefs = subjectTableDefs;
	}
	public void setIndexSelect(IndexSelect indexSelect) {
		this.indexSelect = indexSelect;
	}
	
	
	public String getSelectSubjectsforQuestionSQL() {
		return selectSubjectsforQuestionSQL;
	}
	public void setSelectSubjectsforQuestionSQL(String selectSubjectsforQuestionSQL) {
		this.selectSubjectsforQuestionSQL = selectSubjectsforQuestionSQL;
	}
	/**
	 * @return the selectQuestionSQL
	 */
	public String getSelectQuestionSQL() {
		return selectQuestionSQL;
	}

	public String getInsertnokkelordskjemalinjeSQL() {
		return insertnokkelordskjemalinjeSQL;
	}
	public void setInsertnokkelordskjemalinjeSQL(
			String insertnokkelordskjemalinjeSQL) {
		this.insertnokkelordskjemalinjeSQL = insertnokkelordskjemalinjeSQL;
	}
	
	public String getSelectnokkelordskjemalinjeSQL() {
		return selectnokkelordskjemalinjeSQL;
	}
	public void setSelectnokkelordskjemalinjeSQL(
			String selectnokkelordskjemalinjeSQL) {
		this.selectnokkelordskjemalinjeSQL = selectnokkelordskjemalinjeSQL;
	}

	public String[] getNokkelordskjemaTableDefs() {
		return nokkelordskjemaTableDefs;
	}
	public void setNokkelordskjemaTableDefs(String[] nokkelordskjemaTableDefs) {
		this.nokkelordskjemaTableDefs = nokkelordskjemaTableDefs;
	}
	/**
	 * @param selectQuestionSQL the selectQuestionSQL to set
	 */
	public void setSelectQuestionSQL(String selectQuestionSQL) {
		this.selectQuestionSQL = selectQuestionSQL;
	}



	/**
	 * @return the updateQuestionSQL
	 */
	public String getUpdateQuestionSQL() {
		return updateQuestionSQL;
	}



	/**
	 * @param updateQuestionSQL the updateQuestionSQL to set
	 */
	public void setUpdateQuestionSQL(String updateQuestionSQL) {
		this.updateQuestionSQL = updateQuestionSQL;
	}

	
	
	
	public String getUpdateQuestionPartidSQL() {
		return updateQuestionPartidSQL;
	}

	public void setUpdateQuestionPartidSQL(String updateQuestionPartidSQL) {
		this.updateQuestionPartidSQL = updateQuestionPartidSQL;
	}

	/**
	 * deleteAnswerline
	 * This routine removes a record from the table tblsvarlinje
	 * @param aId id of record
	 */
	public void deleteAnswerline(Long aId){
		int type = Types.INTEGER;
		String sql = deleteAnswerlineSQL;
		long id = aId.longValue();
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
	}
	
	
	/**
	 * deleteAnswerline
	 * This routine removes a record from the table tblsvarlinje 
	 * and check if record already exist than new record insert
	 * into the tblsvarskala and update the tblsvarlinje  
	 * @param aId id of record
	 */
	public void deleteAnswerline(Long aId,Long qId,Answer answer, boolean newSvarSakala){
		int type = Types.INTEGER;
		String sql = deleteAnswerlineSQL;
		long id = aId.longValue();
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
		
//		if(newSvarSakala){
			int ansSize = 0;
			List answerLineList = getAnswerlineforQuestion(qId);
			if(answerLineList != null){
				ansSize = answerLineList.size();
			}
			int x=0;
			if(ansSize>0){
				Iterator answerLineItr = answerLineList.iterator();
				AnswerLine ansLine = new AnswerLineImpl();
				String answersId="";
				while(answerLineItr.hasNext()){
					x++;
					ansLine =(AnswerLine) answerLineItr.next();
					long ansId = ansLine.getSvarLinjeid().longValue();
					if(ansSize==1){
						answer.setAnchorOne(ansLine.getAltSvartekst());
						answer.setAnchorTwo(ansLine.getAltSvartekst());
					}else if(x==1){
						answer.setAnchorOne(ansLine.getAltSvartekst());
					}else if(x==ansSize){
						answer.setAnchorTwo(ansLine.getAltSvartekst());
					}
					answersId= answersId+ ansId + "," ;
				}
				updateAnswerlineOrder(answersId);
				if(newSvarSakala){
					answer.setSvarSkalaid(null) ;
				}
				answer.setAntSvarkategorier(ansSize);
				answer.setInputTypeid(answer.getInputTypeid()) ;
				saveAnswer(answer);
				ansLine.setSvarSkalaid(answer.getSvarSkalaid());
				ansLine.setSporsmalid(qId);
				updateSvarskalaidInAnswerline(ansLine);
				}
//			}
		}
	
	/**
	 * deleteQuestion
	 * This routine removes a question from the table tblsporsmal and all its links in 
	 * the tables tblskjemalinje and tblsvarlinje
	 * @param qId
	 */
	public void deleteQuestion(String qId){

		int type = Types.VARCHAR;
		
		questionSelect = new QuestionSelect(getDataSource(),deleteQuestionSQL,questionTableDefs);
		
		questionSelect.declareParameter(new SqlParameter(type));
		questionSelect.execute(qId);
		
	/*	
		String sql = deleteQuestionSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(qId);
	*/	
	}


	/**
	 * Clone a questionare with all questions and related stuff.
	 * @param questionareId - the id of the questionare to be cloned (source).
	 * @return id of the questionare created (the clone)
	 */
	public Long cloneQuestionare(Long questionareId) {

		if (questionareId == null) {
			String msg = "questionareId was null.";
			logger.error(msg);
			throw new RuntimeException(msg);
		}
		
		final String PROC_NAME = "clonequestionare";
		final String INPUT_PARAM_NAME = "sourcequestionareid";
		final String RETURN_PARAM_NAME = "newQuestionareId";
		
		// Declaration of param names and their type (not value).
		Map inputParamsMap = new HashMap();
		Map outputParamsMap = new HashMap();
		
		inputParamsMap.put(INPUT_PARAM_NAME, Types.INTEGER);
		outputParamsMap.put(RETURN_PARAM_NAME, Types.INTEGER);
		
		FunctionExecImpl fe = new FunctionExecImpl(getDataSource(), PROC_NAME
				, inputParamsMap, outputParamsMap);
		
		// Parameter values map, the names and type of values must
		// match content in inputParamsMap.
		Map paramsMap = new HashMap();
		paramsMap.put(INPUT_PARAM_NAME, questionareId);
		
		Map returnMap = fe.execute(paramsMap);

		return new Long((Integer)returnMap.get(RETURN_PARAM_NAME));
	}
	
	/**
	 * deleteQuestionare
	 * this routine removes a questionare from the tble tblsporreskjema
	 * tblsvarskala,tblsvarlinje,tblskjemalinje,tblskjemakommentar,
	 * tblnokkelord,tblundersokelse,tbllitteraturreferanse,tblindekslinje,
	 * tblindeks
	 * @param qId - questionare Id
	 */
	public void deleteQuestionare(Long qId){
		int type = Types.INTEGER;
		long id = qId.longValue();
		
		String sql= "";
	
		sql= deleteskjemakommentarforquestionareSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
		
		sql= deletenokkelordskjemalinjeforquestionareSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
		
		sql= deleteundersokelseforquestionareSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
		
		sql= deletelitteraturreferanseforquestionareSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
		
		sql = deleteIndeksLinjeForQuestionareSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(), sql);
		tablesUpdate.declareParameter(new SqlParameter(type));  
		tablesUpdate.delete(id);
		
		sql= deleteindeksforquestionareSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
/*
		sql= deleteSvarskalaForQuestionareSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
*/ 		

		sql= deleteSvarlinjeForQuestionareSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);

		sql = deleteskjemalinjeforquestionareSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
		
		sql= deleteQuestionareSQL;
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
		tablesUpdate.declareParameter(new SqlParameter(type));
		tablesUpdate.delete(id);
	}
	
	/**
	 * collectAllQuestions
	 * This routine returns a list of all questions from the table tblsporsmal
	 * @return
	 */
	public List collectAllQuestion(){
		questionSelect = new QuestionSelect(getDataSource(),selectAllQuestionsSQL,questionTableDefs);
	
		List questions = questionSelect.execute();
		Iterator questionIterator = questions.iterator();
		Question question = null;
		while (questionIterator.hasNext()){
			question = (QuestionImpl)questionIterator.next();
			Long questionId = question.getSporsmalid();
			question.setAnswerLine(getAnswerlineforQuestion(questionId));
			List answerIds = getAnswerIds(question.getAnswerLine());
			question.setAnswers(getAnswerforQuestion(answerIds));
		}
		
		return questions;
	}
	
	/**
	 * collectAllQuestionshavepart
	 * This routine returns a list of all questions from the table tblsporsmal which have partid
	 * @return
	 */
	public List collectAllQuestionhavepart(Long partId){
		int type = Types.INTEGER;
		questionSelect = new QuestionSelect(getDataSource(),selectQuestionshavepartSQL,questionTableDefs);
		questionSelect.declareParameter(new SqlParameter(type));
		List questions = questionSelect.execute(partId);
		if(questions.isEmpty())
			questions=null;
		return questions;
	}
	
	/**
	 * collectAllanswers
	 * This routine returns a list of all answers from the table tblsvarskala 
	 * @return
	 */
	public List collectAllanswers(){
		List answers = new ArrayList();
		AnswerSelect answerSelect = new AnswerSelect(getDataSource(),selectAnswerSQL,answerTableDefs);
		answers = answerSelect.execute();
		Iterator answerIterator = answers.iterator();
		while (answerIterator.hasNext()){
			Answer answer = (AnswerImpl)answerIterator.next();
			Long aId = answer.getSvarSkalaid();
			answer.setAnswerLine(getAnswerlineforAnswer(aId));
			answer.checkAnswerlines();
		}
		return answers;
	}

	/**
	 * Worker function that either collect only questionares, or also their
	 * lines, questions, questions comments and indexes, depending on the deep param.
	 * @param deep - wether to collect connected information as well, or just the
	 * questionares themselves. If deep Questionare.fulfilled = true;
	 * @return List of Questionares
	 * @see collectQuestionares() 
	 * @see collectQuestionaresShallow()
	 */
	private List collectQuestionares(boolean deep) {
		
		List  questionares = null;
		questionareSelect = new QuestionareSelect(getDataSource(),selectQuestionareSQL,questionareTableDefs);
		questionares = questionareSelect.execute();
			if (deep) {
			getQuestionarelines(questionares);
			getQuestionsforQuestionares(questionares);
			getCommentsforQuestionares(questionares);
			getIndexesforQuestionares(questionares);
		}
		
		for (Questionare questionare : (List<Questionare>) questionares) {
			questionare.setFulfilled(deep);
			if (questionare.getIndexList() != null &&  !questionare.getIndexList().isEmpty()){
				for (Index index : (List<Index>) questionare.getIndexList()){
					for (Long qId : (List<Long>) index.getQuestionIds()){
						for (Question question : (List<Question>) questionare.getQuestions()){
							if (question.getSporsmalid().longValue() == qId.longValue()){
								index.getQuestionsForIndeksList().add(question);
							}
						}
					}
				}
			}
			}
		return questionares;
		
	}
	
	
	
	
	public List collectQuestionareHaveId(Long quId) {
		
		List  questionares = null;
		int type = Types.INTEGER;
		questionareSelect = new QuestionareSelect(getDataSource(),selectQuestionarehaveidSQL,questionareTableDefs);
		questionareSelect.declareParameter(new SqlParameter(type));
		questionares = questionareSelect.execute(quId);
			getQuestionarelines(questionares);
			getQuestionsforQuestionares(questionares);
			getCommentsforQuestionares(questionares);
			getIndexesforQuestionares(questionares);
		
		for (Questionare questionare : (List<Questionare>) questionares) {
			questionare.setFulfilled(true);
			if (questionare.getIndexList() != null &&  !questionare.getIndexList().isEmpty()){
				for (Index index : (List<Index>) questionare.getIndexList()){
					for (Long qId : (List<Long>) index.getQuestionIds()){
						for (Question question : (List<Question>) questionare.getQuestions()){
							if (question.getSporsmalid().longValue() == qId.longValue()){
								index.getQuestionsForIndeksList().add(question);
							}
						}
					}
				}
			}
		}
		return questionares;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see no.naks.skjemabank.dao.QuestionDAO#collectQuestionares()
	 */
	public List collectQuestionares() {
		logger.warn("collectQuestionares: deep retrieval of all questionares invoked. slow and memory-consuming. consider rewrite.");
		return collectQuestionares(true);
	}
	
	/* (non-Javadoc)
	 * @see no.naks.skjemabank.dao.QuestionDAO#collectQuestionaresShallow()
	 */
	public List<Questionare> collectQuestionaresShallow() {
		List<Questionare> questionares = null;
		try {
			questionares = (List<Questionare>) collectQuestionares(false);
		} catch (ClassCastException cce) {
			logger.error("Wrong type of questionare list.", cce);
			throw cce;
		}
		return questionares;
	}

	/**
	 * Fulfill (fill in) a (ONE) questionare that has only been retrieved by collectQuestionaresShallow
	 * the way it would have been filled in if it had been collected with the fullblown 
	 * (eg. retrieve-everything-in-the-db) collectQuestionares method that we want to avoid.
	 * If ID is null it is assumed new questionare and hence no relevant info to retrieve from db, 
	 * eg. fulfillment is skipped. 
	 * @param questionare
	 * @return questionare fulfilled 
	 */
	public Questionare fulfillQuestionare(Questionare questionare) {
		questionare.setFulfilled(true);
		Long qId = questionare.getSporreSkjemaid();
		// only fulfill existing questionare, new one will not have any interesing info in db.
		if (qId != null) {
			questionare.setQuestionareLine(getQuestionareLines(qId));
			questionare.setQuestions(getQuestions(qId));
			questionare.setComments(getComments(qId));
			questionare.setIndexList(getIndexes(qId));
		}
		return questionare;
	}
	
	/* (non-Javadoc)
	 * @see no.naks.skjemabank.dao.QuestionDAO#collectInstrumenter()
	 */
	public List collectInstrumenter(){
		List instrumenter = new ArrayList();
		questionareSelect = new QuestionareSelect(getDataSource(),selectInstrumenterSQL,questionareTableDefs);
		instrumenter = questionareSelect.execute();
	
		return instrumenter;
		
	}
	
	/* (non-Javadoc)
	 * @see no.naks.skjemabank.dao.QuestionDAO#saveAll(no.naks.skjemabank.model.Questionare, no.naks.skjemabank.model.Question, no.naks.skjemabank.model.QuestionareLine, no.naks.skjemabank.model.Answer, no.naks.skjemabank.model.AnswerLine)
	 */
	public void saveAll(Questionare questionare,Question question,QuestionareLine questionareline,Answer answer,AnswerLine answerline){
		questionareline.setSporreSkjemaid(questionare.getSporreSkjemaid());
		saveQuestionare(questionare);
		saveQuestionareComments(questionare);
		questionareline.setSporsmalid(question.getSporsmalid());
		saveQuestion(question);
		questionareline.setSporsmalNumber(question.getQuestionNumber());
		saveQuestionareline(questionareline,questionare.getSporreSkjemaid(),question.getSporsmalid());
		if(answer != null){
			int answerLineSize = 0;
			if (answer.getAnswerLine() != null) // Sjekk null OLJ 16.12.10
				answerLineSize = answer.getAnswerLine().size();
			if(answerLineSize > 1){
				List answerLineList = answer.getAnswerLine();
				Iterator ansItr = answerLineList.iterator();
				AnswerLine answerLineItr=(AnswerLine) ansItr.next();
				answer.setAnchorOne(answerLineItr.getAltSvartekst());
			}
			saveAnswer(answer);
		}
		if(answerline != null){
			answerline.setSporsmalid(question.getSporsmalid());
			answerline.setSvarSkalaid(answer.getSvarSkalaid());
			saveAnswerline(answerline); // Gjør insert eller update av et enkelt svaralternativ
			updateSvarskalaidInAnswerline(answerline);
		}
	}
	
	
	private void updateSvarskalaidInAnswerline(AnswerLine answerLine){
			Long svarskalaId = answerLine.getSvarSkalaid();
			Long questionId = answerLine.getSporsmalid();
			String sql = updateSvarskalaidSQL;
			int[] types =  {Types.INTEGER, Types.INTEGER};
			Object[]params = {svarskalaId,questionId};
			tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
			tablesUpdate.insert(params);
	}
	
	/* (non-Javadoc)
	 * @see no.naks.skjemabank.dao.QuestionDAO#saveAll(no.naks.skjemabank.model.Questionare, no.naks.skjemabank.model.Question, no.naks.skjemabank.model.QuestionareLine, no.naks.skjemabank.model.Answer)
	 */
	public void saveAll(Questionare questionare,Question question,QuestionareLine questionareline,Answer answer){
		questionareline.setSporreSkjemaid(questionare.getSporreSkjemaid());
		saveQuestionare(questionare);
		saveQuestionareComments(questionare);
		questionareline.setSporsmalid(question.getSporsmalid());
		saveQuestion(question);
		questionareline.setSporsmalNumber(question.getQuestionNumber());
		saveQuestionareline(questionareline,questionare.getSporreSkjemaid(),question.getSporsmalid());
		saveAnswer(answer);
	//	List answerlines = answer.getAnswerLine();
	//	int antAns = answer.getAntSvarkategorier().intValue();
	//	saveAnswerlines(answerlines, question.getSporsmalid(),answer.getSvarSkalaid(),antAns);  // qaa made change 08/12/2010
		saveAnswerlines(answer, question);
		// Gjør alltid insert av svaralternativer
	}
	/**
	 * saveQuestionareandQuestion
	 * This method saves both questionnaire and a question
	 * It is called when a user wants to save a question to a new questionnaire
	 * @param questionare
	 * @param question
	 */
	public void saveQuestionareandQuestion(Questionare questionare,Question question,QuestionareLine questionareline){
		saveQuestionare(questionare);
		saveQuestionareComments(questionare);
		saveQuestion(question);
		questionareline.setSporsmalid(question.getSporsmalid());
		questionareline.setSporsmalNumber(question.getQuestionNumber());
		saveQuestionareline(questionareline,questionare.getSporreSkjemaid(),question.getSporsmalid());

	}
	
	public void saveQuestionare(Questionare questionare){
		
		questionare.setParams();
		String sql = insertQuestionareSQL;
		int[] types = questionare.getTypes();
		Object[]params = questionare.getParams();
		Long id = questionare.getSporreSkjemaid();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
       
		questionare.setSkjemaDato(date);
		if (id != null){
			sql = updateQuestionareSQL;
			types = questionare.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			questionare.setSporreSkjemaid(getPrimaryKey(primaryKeyQuestinareSQL,primaryKeyTablesDefs ));
			
		}
     }
	public void saveQuestionareComments(Questionare questionare){
		List comments = questionare.getComments();
		if (comments != null && comments.size() > 0){
			Iterator commentIterator = comments.iterator();
			while (commentIterator.hasNext()){
				QuestionareComment comment = (QuestionareCommentImpl)commentIterator.next();
				comment.setQuestionareId(questionare.getSporreSkjemaid());
				Long id = comment.getCommentId();
				String sql = insertcommentforquestionareSQL;
				int[] types = comment.getTypes();
				comment.setParams();
				if (id != null){
					sql = updatecommentforquestionareSQL;
					types = comment.getUtypes();
				}
				Object[]params = comment.getParams();
				tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
				tablesUpdate.insert(params);
				if (id == null){
					comment.setCommentId((getPrimaryKey(primarykeyforCommentsSQL,primaryKeyTablesDefs )));
				}
			}
		
			
		}
		
	}
	public void saveQuestionlarelines(List<QuestionareLineImpl> questionarelines) {
		for (QuestionareLineImpl questionline : questionarelines ){
			saveQuestionareline(questionline,questionline.getSporreSkjemaid(),questionline.getSporsmalid());
		}
	}
	public void saveQuestionareline(QuestionareLine questionare,Long questonareid,Long questionid){
		if (questionare != null){
			String sql = insertQuestinarelineSQL;
			int[] types = questionare.getTypes();
			
			Long id = questionare.getSkjemalinjeid();
//			Long id = questionare.getSporsmalid();

			if (id != null){
				sql = updateQuestionarelineSQL;
				questionare.setuParams();
				types = questionare.getUtypes();
			}
			if (id == null){
				questionare.setSporreSkjemaid(questonareid);
				questionare.setSporsmalid(questionid);
				questionare.setParams();

			}

			Object[]params = questionare.getParams();
			tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
			tablesUpdate.insert(params);
			if (id == null){
				questionare.setSkjemalinjeid(getPrimaryKey(primaryKeyforquestionarelineSQL,primaryKeyTablesDefs));
			}
		}
		
	}
	
	
	public void updateQuestionarelineOrder(String qIdArray){
		int type = Types.VARCHAR;
		
		questionarelineSelect = new QuestionareLineSelect(getDataSource(),updateQuestionsOrderSQL,questionarelineTableDefs);
		
		questionarelineSelect.declareParameter(new SqlParameter(type));
		questionarelineSelect.execute(qIdArray);
	}
	
	public void updateAnswerlineOrder(String aIdArray){
		int type = Types.VARCHAR;
		answerlineSelect= new AnswerlineSelect(getDataSource(), updateAnswerlineOrderSQL, answerlineTableDefs);
		answerlineSelect.declareParameter(new SqlParameter(type)) ;
		answerlineSelect.execute(aIdArray);
	}
	
	/* (non-Javadoc)
	 * @see no.naks.skjemabank.dao.QuestionDAO#saveQuestion(no.naks.skjemabank.model.Question)
	 */
	public void saveQuestion(Question question) {
		if (question != null){
			question.setParams();
			String sql = insertQuestionSQL;
			int[] types = question.getTypes();
			Object[]params = question.getParams();
//			if (question.getIndeksid() == null)
//			question.setIndeksid(new Long(0));
			Long id = question.getSporsmalid();
			if (id != null){
			
				sql = updateQuestionSQL;
				types = question.getUtypes();
				tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
				tablesUpdate.insert(params);
				
				/* check if the question shownotes is 0 than all 
				   questions that have partid of this question will be removed.
				*/
				if(question.getShowNotes()==0){ 
					int[] type = {Types.INTEGER};
					Object[] paramsObj = {id};
					sql = updateQuestionPartidSQL;
					types = question.getUtypes();
					tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,type);
					tablesUpdate.insert(paramsObj);
				}
				
			}else{
				tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
				tablesUpdate.insert(params);
			}
			if (id == null){
				question.setSporsmalid(getPrimaryKey(primaryKeyQuestionSQL,primaryKeyTablesDefs ));

			}
		}
	}
	public void saveAnswer(Answer answer){
		if (answer != null){
			answer.setParams();
			String sql = insertAnswerSQL;
			int[] types = answer.getTypes();
			Object[]params = answer.getParams();
			Long id = answer.getSvarSkalaid();
			if (id != null){
				sql = updateAnswerSQL;
				types = answer.getUtypes();
			}
			tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
			tablesUpdate.insert(params);
			if (id == null){
				answer.setSvarSkalaid(getPrimaryKey(primaryKeyAnswerSQL,primaryKeyTablesDefs ));

			}		
		}
	}
	public void saveAnswerline(AnswerLine answerLine){
			answerLine.setParams();
			String sql = insertAnswerlineSQL;
			int[] types = answerLine.getTypes();
			Object[]params = answerLine.getParams();
			Long id = answerLine.getSvarLinjeid();
			if (id != null){
				sql = updateAnswerlineSQL;
				types = answerLine.getUtypes();
			}
			tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
			tablesUpdate.insert(params);
			if (id == null){
				answerLine.setSvarLinjeid(getPrimaryKey(primaryKeyAnswerlineSQL,primaryKeyTablesDefs ));

			}		
	}

	/**
	 * saveAnswerlines
	 * This routine inserts new answerlines to a question
	 * It is called from
	 * @param answerlines
	 * @param qId
	 * @param aId
	 * @param antAns
	 */
	/*	  // qaa delete this routine
	public void saveAnswerlines(List answerlines,Long qId,Long aId, int antAns){
		if (answerlines != null){
			Iterator lineIterator = answerlines.iterator();
			int i = 0;
			while (lineIterator.hasNext() && i < antAns){
				AnswerLine answerline = (AnswerLineImpl) lineIterator.next();
				String sql = insertAnswerlineSQL;
				answerline.setInsert(true);
				int[] types = answerline.getTypes();
				if (answerline.getNextQuestionnr() == null)
					answerline.setNextQuestionnr(new Integer(0));
				
			//	if (answerline.getSvarLinjeid() != null && qId != null){
			//			sql = updateAnswerlineSQL;
			//			answerline.setInsert(false);
			//			types = answerline.getUtypes();
			//	}
				
					int nr = answerline.getSvarRekkefolge();
					answerline.setSporsmalid(qId);
					answerline.setSvarSkalaid(aId);
					answerline.setSvarRekkefolge(new Integer(nr+1));
					answerline.setParams();
		
					i++;

			
		//		Long id = answerline.getSvarLinjeid();
		//		if (id != null){
		//		sql = updateAnswerlineSQL;
		//		types = answerline.getUtypes();
		//	}
			
				Object[]params = answerline.getParams();
				tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
				tablesUpdate.insert(params);
				if (answerline.getSvarLinjeid() == null)
					answerline.setSvarLinjeid(getPrimaryKey(primaryKeyAnswerlineSQL,primaryKeyTablesDefs ));
			}
		}
	}
	
	*/
	/**
	 * saveAnswerlines
	 * This routine inserts new answerlines to a question
	 * It is called from
	 * @param ans
	 * @param question
	 */
	private void saveAnswerlines(Answer answer, Question question){
		List answerlines = answer.getAnswerLine();
		int antAns = answer.getAntSvarkategorier().intValue();
		long qId = question.getSporsmalid();
	
		if (answerlines != null){
			int ansSize = 0;
			List answerLineList = question.getAnswerLine();
			if(answerLineList != null){
				ansSize = answerLineList.size();
			}
			if(ansSize > 0 ){
				answer.setSvarSkalaid(null) ;
				Iterator ansItr = answerLineList.iterator();
				AnswerLine ansLine = (AnswerLineImpl) ansItr.next();
				answer.setAnchorOne(ansLine.getAltSvartekst()) ;
				saveAnswer(answer) ;
			}
			long aId = answer.getSvarSkalaid();
			Iterator lineIterator = answerlines.iterator();
			int i = 0;
			int nr = ansSize;
			while (lineIterator.hasNext() && i < antAns){
				AnswerLine answerline = (AnswerLineImpl) lineIterator.next();
				String sql = insertAnswerlineSQL;
				answerline.setInsert(true);
				int[] types = answerline.getTypes();
				if (answerline.getNextQuestionnr() == null)
					answerline.setNextQuestionnr(new Integer(0));
				/*
				if (answerline.getSvarLinjeid() != null && qId != null){
						sql = updateAnswerlineSQL;
						answerline.setInsert(false);
						types = answerline.getUtypes();
				}
				*/
			//		int nr = answerline.getSvarRekkefolge();
					nr++;
					i++;
					answerline.setSporsmalid(qId);
					answerline.setSvarSkalaid(aId);
					answerline.setSvarRekkefolge(new Integer(nr));
					answerline.setParams();
					

				/*
				Long id = answerline.getSvarLinjeid();
				if (id != null){
				sql = updateAnswerlineSQL;
				types = answerline.getUtypes();
			}
				 */
				Object[]params = answerline.getParams();
				tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
				tablesUpdate.insert(params);
				if (answerline.getSvarLinjeid() == null)
					answerline.setSvarLinjeid(getPrimaryKey(primaryKeyAnswerlineSQL,primaryKeyTablesDefs ));
				
				if(antAns == i){
					answer.setAnchorTwo(answerline.getAltSvartekst());
					answer.setAntSvarkategorier(nr);
					saveAnswer(answer) ;
					
					Object[]ansParams = {answer.getSvarSkalaid(), question.getSporsmalid()};
					int[] ansTypes ={Types.INTEGER,Types.INTEGER} ;
					sql = updateAnswerlineForQuestionSQL;
					tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,ansTypes);
					tablesUpdate.insert(ansParams);
				}
			}
		}
	}
	
	
	/**
	 * saveIndexline
	 * This routine saves a new record to the table tblindekslinje
	 * @param indexid
	 * @param questionId
	 */
	public void saveIndexline(Index index){
		String sql = insertindexlineSQL;
		if (index.getQuestionIds() != null && index.getQuestionIds().size() > 0 ){
			int types[] = {Types.INTEGER,Types.INTEGER};
			tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
			for (int i = 0;i<index.getQuestionIds().size();i++){
				Object[] params = {index.getIndexId(),index.getQuestionIds().get(i)};
		
				tablesUpdate.insert(params);
			}
		}
	}
	/**
	 * deleteIndexline
	 * This routine removes a record from the table tblindekslinje
	 * @param indexId
	 */
	public void deleteIndexline(Long indexId){
		int type = Types.INTEGER;
		String sql = deleteIndexlineSQL;
		if (indexId != null){
			long id = indexId.longValue();
			tablesUpdate = new TablesUpdateImpl(getDataSource(),sql);
			tablesUpdate.declareParameter(new SqlParameter(type));
			tablesUpdate.delete(id);
		}
	}
	
	/**
	 * deleteIndex
	 * This routine removes the index data from tblindeks,tblindekslinje and tbltemalinje 
	 * @param indexId
	 */
	public void deleteIndex(Long indexId){
		if(indexId != null){
			int type = Types.INTEGER;
			long id = indexId.longValue();
			
			String sql = deleteIndexlineSQL;
			tablesUpdate = new TablesUpdateImpl(getDataSource(), sql);
			tablesUpdate.declareParameter(new SqlParameter(type));
			tablesUpdate.delete(id) ;
			
			sql = deleteTemalinjeSQL;
			tablesUpdate = new TablesUpdateImpl(getDataSource(), sql);
			tablesUpdate.declareParameter(new SqlParameter(type));
			tablesUpdate.delete(id) ;
			
			sql = deleteIndexSQL;
			tablesUpdate = new TablesUpdateImpl(getDataSource(), sql);
			tablesUpdate.declareParameter(new SqlParameter(type));
			tablesUpdate.delete(id) ;
			
		}
		
		
		
	}
	
	/**
	 * saveIndex
	 * This routine saves the index to the database
	 * @param index The Index to be saved
	 * @param questionId The id of the question it belong to (if any)
	 */
	public void saveIndex(Index index){
		if (index != null){
			index.setParams();
			String sql = insertindexSQL;
			int[] types = index.getTypes();
			Object[]params = index.getParams();
			Long id = index.getIndexId();
			if (id != null){
				sql = updateindexSQL;
				types = index.getUtypes();
			}
			tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
			tablesUpdate.insert(params);
			if (id == null){
				index.setIndexId(getPrimaryKey(primaryKeyIndexSQL,primaryKeyTablesDefs ));
			}
			saveIndexline(index);
		}
		
	}
	
	/**
	 * saveNokkelordSkjemalinje
	 * This method save the informanid and sporreskjemaid
	 * into the databes table tblnokkelordskjemalinje.
	 */
	
	public void saveNokkelordSkjemalinje(LineKey lineKey){
		long nokkelordId = lineKey.getTableoneKey();
		long sporreSkjemId = lineKey.getTabletwoKey() ;
		int type = Types.INTEGER;
		Object[] selectParams = {nokkelordId, sporreSkjemId};
		LineKeySelect lineKeySelect = new LineKeySelect(getDataSource(),selectnokkelordskjemalinjeSQL,nokkelordskjemaTableDefs);
		lineKeySelect.declareParameter(new SqlParameter(type));
		lineKeySelect.declareParameter(new SqlParameter(type));
		List<LineKeyImpl> lineKeyList = new ArrayList<LineKeyImpl>();
		lineKeyList = lineKeySelect.execute(selectParams) ;
		if(lineKeyList.size()==0){
			lineKey.setParams();
			String sql = insertnokkelordskjemalinjeSQL;
			int[] types = lineKey.getTypes();
			Object[]params = lineKey.getParams();
			tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
			tablesUpdate.insert(params);
		}
	}
}

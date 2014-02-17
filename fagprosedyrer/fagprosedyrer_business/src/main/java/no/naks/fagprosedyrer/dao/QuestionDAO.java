
package no.naks.skjemabank.dao;


import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;



import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;
import no.naks.rammeverk.kildelag.model.LineKey;
import no.naks.skjemabank.model.Answer;
import no.naks.skjemabank.model.AnswerLine;

import no.naks.skjemabank.model.Index;
import no.naks.skjemabank.model.Question;
import no.naks.skjemabank.model.Questionare;
import no.naks.skjemabank.model.QuestionareLine;
import no.naks.skjemabank.model.QuestionareLineImpl;

/**
 * 
 * 
 *
 * @author ojn
 *
 */
public interface QuestionDAO {
	
	
/**
	 * @return the answerTableDefs
	 */
	public String[] getAnswerTableDefs();

	/**
	 * @param answerTableDefs the answerTableDefs to set
	 */
	public void setAnswerTableDefs(String[] answerTableDefs);

/**
	 * @return the insertAnswerSQL
	 */
	public String getInsertAnswerSQL();

/**
	 * @param insertAnswerSQL the insertAnswerSQL to set
	 */
	public void setInsertAnswerSQL(String insertAnswerSQL);

	/**
	 * @return the primaryKeyAnswerSQL
	 */
	public String getPrimaryKeyAnswerSQL();

	/**
	 * @param primaryKeyAnswerSQL the primaryKeyAnswerSQL to set
	 */
	public void setPrimaryKeyAnswerSQL(String primaryKeyAnswerSQL);

	/**
	 * @return the selectAnswerSQL
	 */
	public String getSelectAnswerSQL();

	/**
	 * return the list of altsvartekst for a particular answer
	 * @param aId
	 * @return
	 */
	public List getAnswerlineforList(Long aId);
	/**
	 * @param selectAnswerSQL the selectAnswerSQL to set
	 */
	public void setSelectAnswerSQL(String selectAnswerSQL);

	public String getDeleteSvarlinjeForQuestionareSQL();

	public void setDeleteSvarlinjeForQuestionareSQL(String deleteSvarlinjeForQuestionareSQL);

	public String getDeleteSvarskalaForQuestionareSQL();

	public void setDeleteSvarskalaForQuestionareSQL(String deleteSvarskalaForQuestionareSQL);

	/**
	 * @return the updateAnswerSQL
	 */
	public String getUpdateAnswerSQL();

	/**
	 * @param updateAnswerSQL the updateAnswerSQL to set
	 */
	public void setUpdateAnswerSQL(String updateAnswerSQL);

	/**
	 * @return the tablesUpdate
	 */
	public Tablesupdate getTablesUpdate();

/**
	 * @param tablesUpdate the tablesUpdate to set
	 */
	public void setTablesUpdate(Tablesupdate tablesUpdate);
	
	/**
	 * @return the primaryKeyQuestionSQL
	 */
	public String getPrimaryKeyQuestionSQL();

	/**
	 * @param primaryKeyQuestionSQL the primaryKeyQuestionSQL to set
	 */
	public void setPrimaryKeyQuestionSQL(String primaryKeyQuestionSQL);

	/**
	 * @return the primaryKeyTablesDefs
	 */
	public String[] getPrimaryKeyTablesDefs();
	/**
	 * @param primaryKeyTablesDefs the primaryKeyTablesDefs to set
	 */
	public void setPrimaryKeyTablesDefs(String[] primaryKeyTablesDefs);
	/**
	 * @return the insertQuestionSQL
	 */
	public String getInsertQuestionSQL();
	/**
	 * @param insertQuestionSQL the insertQuestionSQL to set
	 */
	public void setInsertQuestionSQL(String insertQuestionSQL);
	/**
	 * @return the questionTableDefs
	 */
	public String[] getQuestionTableDefs();
	/**
	 * @param questionTableDefs the questionTableDefs to set
	 */
	public void setQuestionTableDefs(String[] questionTableDefs);
	

	/**
	 * @return the selectQuestionSQL
	 */
	public String getSelectQuestionSQL();
	/**
	 * @param selectQuestionSQL the selectQuestionSQL to set
	 */
	public void setSelectQuestionSQL(String selectQuestionSQL);
	/**
	 * @return the updateQuestionSQL
	 */
	public String getUpdateQuestionSQL();
	/**
	 * @param updateQuestionSQL the updateQuestionSQL to set
	 */
	public void setUpdateQuestionSQL(String updateQuestionSQL);
	
	/**
	 * @return the selectAnswerLineSQL
	 */
	public String getSelectAnswerLineSQL();
	/**
	 * @param selectAnswerLineSQL the selectAnswerLineSQL to set
	 */
	public void setSelectAnswerLineSQL(String selectAnswerLineSQL);
	
	/**
	 * @return the selectAnswerlineforquestionSQL
	 */
	public String getSelectAnswerlineforquestionSQL();
	
	
	public String getInsertSubjectSQL();
	public void setInsertSubjectSQL(String insertSubjectSQL);
	public String getUpdateSubjectSQL();
	public void setUpdateSubjectSQL(String updateSubjectSQL);
	public String getSelectSubjectSQL();
	public void setSelectSubjectSQL(String selectSubjectSQL);
	public String getPrimaryKeySubjectSQL();
	public void setPrimaryKeySubjectSQL(String primaryKeySubjectSQL);
	public String[] getSubjectTableDefs();
	public void setSubjectTableDefs(String[] subjectTableDefs);
	
	public String getSelectquestionSubjectSQL();
	public void setSelectquestionSubjectSQL(String selectquestionSubjectSQL);
	public String getInsertquestionSubjectSQL();
	public void setInsertquestionSubjectSQL(String insertquestionSubjectSQL);
	public String getUpdatequestionSubjectSQL();
	public void setUpdatequestionSubjectSQL(String updatequestionSubjectSQL);
	public String[] getSporsmallinjeTableDefs();
	public void setSporsmallinjeTableDefs(String[] sporsmallinjeTableDefs);
	
	public String getSelectSubjectsforQuestionSQL();
	public void setSelectSubjectsforQuestionSQL(String selectSubjectsforQuestionSQL);
	public String getInsertindexlineSQL();
	public void setInsertindexlineSQL(String insertindexlineSQL);
	public String getInsertindexSQL();
	public void setInsertindexSQL(String insertindexSQL);
	public String getUpdateindexSQL();
	public void setUpdateindexSQL(String updateindexSQL);
	public String getPrimaryKeyIndexSQL();
	public void setPrimaryKeyIndexSQL(String primaryKeyIndexSQL);
	
	/**
	 * @param selectAnswerlineforquestionSQL the selectAnswerlineforquestionSQL to set
	 */
	public void setSelectAnswerlineforquestionSQL(String selectAnswerlineforquestionSQL);
	/**
	 * Deep retrieve of all questionares. Use with care, retrieves all information connected to all questionares.
	 * You most probably won't need this.
	 * @return List of questionares with connected information included.
	 */
	public List collectQuestionares();
	public List collectQuestionareHaveId(Long quId); 
	public Questionare fulfillQuestionare(Questionare questionare);
	/**
	 * Shallow retrieve of all questionares. For use on the frontpage and other places when a list of
	 * only questionare information is needed without list of questions etc. 
	 * @return List of questionares without connected questions, indexes, comments and so on.
	 */
	public List<Questionare> collectQuestionaresShallow();
	public List collectInstrumenter();
	public List collectAllQuestion();
	public List collectAllQuestionhavepart(Long partId);
	public List collectAllanswers();
	public void saveQuestion(Question question);
	public void saveAnswer(Answer answer);
	public void saveQuestionareline(QuestionareLine questionare,Long questonareid,Long questionid);
	
	/**
	 * saveAll
	 * This routine is used to save a specific answerline
	 * @param questionare actual Questionare 
	 * @param question actual question
	 * @param questionareline actual questionare line
	 * @param answer actual answer scale
	 * @param answerline actual answer line
	 */
	public void saveAll(Questionare questionare,Question question,QuestionareLine questionareline,Answer answer,AnswerLine answerline);
	/**
	 * saveAll
	 * This routine is used to save a complete answer alternative
	 * @param questionare
	 * @param question
	 * @param questionareline
	 * @param answer
	 */
	public void saveAll(Questionare questionare,Question question,QuestionareLine questionareline,Answer answer);
	public void saveAnswerline(AnswerLine answer);
	/**
	 * saveQuestionareandQuestion
	 * This method saves both questionnaire and a question
	 * It is called when a user wants to save a question to a new questionnaire
	 * @param questionare
	 * @param question
	 */
	public void saveQuestionareandQuestion(Questionare questionare,Question question,QuestionareLine questionareline);
	/**
	 * deleteAnswerline
	 * This routine removes a record from the table tblsvarlinje
	 * @param aId id of record
	 */
	public void deleteAnswerline(Long aId);
	/**
	 * deleteQuestion
	 * This routine removes a question from the table tblsporsmal and all its links in 
	 * the tables tblskjemalinje and tblsvarlinje
	 * @param qId
	 */
	public void deleteQuestion(String qId);
	
	/**
	 * deleteQuestionare
	 * this routine removes a questionare from the table tblsporreskjema
	 * @param id
	 */
	public void deleteQuestionare(Long qId);
	/**
	 * Given id of a questionare, copy that questionare with all
	 * connected questions and stuff, and return id of the new/clone questionare.
	 * @param questionareId
	 * @return id of cloned new questionare
	 */
	public Long cloneQuestionare(Long questionareId);
	public void saveIndexline(Index index);
	public void deleteIndexline(Long indexId);
	public void deleteIndex(Long indexId);
	/**
	 * saveIndex
	 * This routine saves the index to the database
	 * @param index The Index to be saved
	 * @param questionId The id of the question it belong to (if any)
	 */
	public void saveIndex(Index index);
	
	/**
	 * saveQuestionarelines
	 * This routine saves a list of questionarelines.
	 * It is normally done when the order of questions has been changed
	 * @param questionarelines
	 */
	public void saveQuestionlarelines(List<QuestionareLineImpl> questionarelines);
	public void saveQuestionare(Questionare questionare);
	
	public void saveNokkelordSkjemalinje(LineKey lineKey);
	public void updateQuestionarelineOrder(String qIdArray);
	public void updateAnswerlineOrder(String aIdArray);
	public List getSubject(Long subjectId);
	public void deleteSvarlinje (Long qId);
	
	public List getQuestions(Long qId);
	public void deleteAnswerline(Long aId,Long qId,Answer answer, boolean newSvarSakala);
}

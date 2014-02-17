package no.naks.skjemabank.model;

import java.util.List;

public interface Question {

	/**
	 * @return the questionNumber
	 */
	public Integer getQuestionNumber();
	/**
	 * @param questionNumber the questionNumber to set
	 */
	public void setQuestionNumber(Integer questionNumber);

	public Long getPartid() ;
	public void setPartid(Long partid); 
	/**
	 * @return the kortversjonTekst
	 */
	public String getKortversjonTekst();
	/**
	 * @param kortversjonTekst the kortversjonTekst to set
	 */
	public void setKortversjonTekst(String kortversjonTekst);
	/**
	 * @return the notes
	 */
	public String getNotes();
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes);
	/**
	 * @return the sporsmalid
	 */
	public Long getSporsmalid();
	/**
	 * @param sporsmalid the sporsmalid to set
	 */
	public void setSporsmalid(Long sporsmalid);
	/**
	 * @return the sporsmalTekst
	 */
	public String getSporsmalTekst();
	/**
	 * @param sporsmalTekst the sporsmalTekst to set
	 */
	public void setSporsmalTekst(String sporsmalTekst);

	/**
	 * @return the answerLine
	 */
	public List<AnswerLine> getAnswerLine();
	/**
	 * @param answerLine the answerLine to set
	 */
	public void setAnswerLine(List<AnswerLine> answerLine);
	/**
	 * @return the answers
	 */
	public List<Answer> getAnswers();
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<Answer> answers);
	/**
	 * @return the questionareLine
	 */
	public List getQuestionareLine();
	/**
	 * @param questionareLine the questionareLine to set
	 */
	public void setQuestionareLine(List questionareLine);
	/**
	 * @return the questionares
	 */
	public List getQuestionares();
	/**
	 * @param questionares the questionares to set
	 */
	public void setQuestionares(List questionares);
	/**
	 * @return the subjectLine
	 */
	public List getSubjectLine();
	/**
	 * @param subjectLine the subjectLine to set
	 */
	public void setSubjectLine(List subjectLine);
	/**
	 * @return the subjects
	 */
	public List getSubjects();
	/**
	 * @param subjects the subjects to set
	 */
	public void setSubjects(List subjects);
	/**
	 * @return the showNotes
	 */
	public Integer getShowNotes();
	/**
	 * @param showNotes the showNotes to set
	 */
	
	/**
	 * @return the noofAnswers
	 */
	public Integer getNoofAnswers();
	/**
	 * @param noofAnswers the noofAnswers to set
	 */
	public void setNoofAnswers(Integer noofAnswers);
	
	public Subject getSubject();
	
	
	/**
	 * @return the noofSvar
	 */
	public int getNoofSvar();
	/**
	 * @param noofSvar the noofSvar to set
	 */
	public void setNoofSvar(int noofSvar);
	public void setShowNotes(Integer showNotes);
	public void setParams();
	public int[] getTypes();
	public Object[] getParams();
	public int[] getUtypes();

	public boolean isMainQuestion();
	public boolean isPartQuestion();
	public boolean isSingleQuestion();

}

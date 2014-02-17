package no.naks.skjemabank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import no.naks.rammeverk.kildelag.model.AbstractModel;
/**
 * Denne klassen representerer sporsmalstabellen i db.
 * @author ojn
 *
 */
public abstract class AbstractQuestion extends AbstractModel implements Question, Serializable {

	private Long sporsmalid;
	
	private Long partid;
	private String sporsmalTekst;
	private String kortversjonTekst;
	private String notes;
	private Integer showNotes;
	private Integer questionNumber; // Spørsmølsnummer er satt nør spørsmølet er knyttet til et spørreskjema

	
	protected List questionareLine; // Liste fra skjemalinjetabell for spørsmølet
	protected List questionares; // Liste over skjema spørsmølet er knyttet til
	protected List<AnswerLine> answerLine; // Liste fra svarlinjetabell for spørsmøl
	protected List<Answer> answers; // Liste over alternative svar til spørsmølet
	protected List subjectLine; // Liste fra sporsmalinjetabell (temalinje) for spørsmølet
	protected List subjects; // Liste over tema for spørsmølet
	protected Integer noofAnswers = null; // No of answers to a particular question
	protected int noofSvar = 0;
	
	
	/**
	 * @return the noofSvar
	 */
	public int getNoofSvar() {
		return noofSvar;
	}
	/**
	 * @param noofSvar the noofSvar to set
	 */
	public void setNoofSvar(int noofSvar) {
		this.noofSvar = noofSvar;
	}
	/**
	 * @return the noofAnswers
	 */
	public Integer getNoofAnswers() {
		return noofAnswers;
	}
	/**
	 * @param noofAnswers the noofAnswers to set
	 */
	public void setNoofAnswers(Integer noofAnswers) {
		this.noofAnswers = noofAnswers;
	}
	/**
	 * @return the questionNumber
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}
	/**
	 * @param questionNumber the questionNumber to set
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}
	/**
	 * @return the indeksid
	 */

	

	public Long getPartid() {
		return partid;
	}

	
	public void setPartid(Long partid) {
		this.partid = partid;
	} 
	/**
	 * @return the kortversjonTekst
	 */
	public String getKortversjonTekst() {
		return kortversjonTekst;
	}
	/**
	 * @param kortversjonTekst the kortversjonTekst to set
	 */
	public void setKortversjonTekst(String kortversjonTekst) {
		this.kortversjonTekst = kortversjonTekst;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the sporsmalid
	 */
	public Long getSporsmalid() {
		return sporsmalid;
	}
	/**
	 * @param sporsmalid the sporsmalid to set
	 */
	public void setSporsmalid(Long sporsmalid) {
		this.sporsmalid = sporsmalid;
	}
	/**
	 * @return the sporsmalTekst
	 */
	public String getSporsmalTekst() {
		return sporsmalTekst;
	}
	/**
	 * @param sporsmalTekst the sporsmalTekst to set
	 */
	public void setSporsmalTekst(String sporsmalTekst) {
		this.sporsmalTekst = sporsmalTekst;
	}
	/**
	 * @return the answerLine
	 */
	public List getAnswerLine() {
		return answerLine;
	}
	/**
	 * @param answerLine the answerLine to set
	 */
	public void setAnswerLine(List answerLine) {
		if (this.answerLine == null)
			this.answerLine= new ArrayList<AnswerLine>();
		this.answerLine.clear();
		if (answerLine != null)
			this.answerLine.addAll(answerLine);
//		this.answerLine = answerLine;
		int ant = this.answerLine.size();
		noofSvar = ant;
		noofAnswers = new Integer(ant);
	}
	/**
	 * @return the answers
	 */
	public List getAnswers() {
		return answers;
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List answers) {
		if (this.answers == null)
			this.answers= new ArrayList<Answer>();
		this.answers.clear();
		if (answers != null)
			this.answers.addAll(answers);

	//	this.answers = answers;
	}
	/**
	 * @return the questionareLine
	 */
	public List getQuestionareLine() {
		return questionareLine;
	}
	/**
	 * @param questionareLine the questionareLine to set
	 */
	public void setQuestionareLine(List questionareLine) {
		if (this.questionareLine == null)
			this.questionareLine= new ArrayList<QuestionareLine>();
		this.questionareLine.clear();
		if (questionareLine != null)
			this.questionareLine.addAll(questionareLine);
	//	this.questionareLine = questionareLine;
	}
	/**
	 * @return the questionares
	 */
	public List getQuestionares() {
		return questionares;
	}
	/**
	 * @param questionares the questionares to set
	 */
	public void setQuestionares(List questionares) {
		if (this.questionares == null)
			this.questionares= new ArrayList<Questionare>();
		this.questionares.clear();
		if (questionares != null)
			this.questionares.addAll(questionares);
		
	//	this.questionares = questionares;
	}
	/**
	 * @return the subjectLine
	 */
	public List getSubjectLine() {
		return subjectLine;
	}
	/**
	 * @param subjectLine the subjectLine to set
	 */
	public void setSubjectLine(List subjectLine) {
		if (this.subjectLine == null)
			this.subjectLine= new ArrayList();
		this.subjectLine.clear();
		if (subjectLine != null)
			this.subjectLine.addAll(subjectLine);

//		this.subjectLine = subjectLine;
	}
	/**
	 * @return the subjects
	 */
	public List getSubjects() {
		return subjects;
	}
	/**
	 * @param subjects the subjects to set
	 */
	public void setSubjects(List subjects) {
		if (this.subjects == null)
			this.subjects= new ArrayList<Subject>();
		this.subjects.clear();
		if (subjects != null)
			this.subjects.addAll(subjects);
//		this.subjects = subjects;
	}
	/**
	 * @return the showNotes
	 */
	public Integer getShowNotes() {
		return showNotes;
	}
	/**
	 * @param showNotes the showNotes to set
	 */
	public void setShowNotes(Integer showNotes) {
		this.showNotes = showNotes;
	}
	public Subject getSubject(){
		Subject subject = null;
		if (subjects != null && subjects.size() > 0)
			subject = (Subject)subjects.get(0);
		return subject;
	}

	/**
	 * A main question is flagged with (showNotes/visnotater == 1). A main question
	 * may or may not have subquestions.
	 */
	public boolean isMainQuestion()
	{   
		checkQuestionHierarchySanity();
		return getPartid() == null && getShowNotes() == 1;
	}

	/**
	 * A part question / subquestion has a reference to the above main question
	 * in partid, and it is itself not a main question, ie. shownotes == 0.
	 */
	public boolean isPartQuestion()
	{
		checkQuestionHierarchySanity();
		return getPartid() != null && getShowNotes() == 0;
	}
	
	/**
	 * A single question has no main question above (partid == null)
	 * and shownotes == 0.
	 */
	public boolean isSingleQuestion()
	{
		checkQuestionHierarchySanity();
		return getPartid() == null && getShowNotes() == 0;
	}
	
	/**
	 * Check that there aren't inconsistent values in the questions with regards to
	 * hierarchy, ie. that a question doesn't both have a parent question reference in partid
	 * and still is flagged as a main question (showNotes == 1)
	 */
	private void checkQuestionHierarchySanity() 
	{	
		// TODO: use specific exception.
		if (getPartid() != null && getShowNotes() == 1)
			throw new RuntimeException("Question that acts as subquestion (partid != null) and still has showNotes == 1 (main question flag) detected: question id " 
					+ getSporsmalid() + ", bogus parent question: " + getPartid());
	}
	
}

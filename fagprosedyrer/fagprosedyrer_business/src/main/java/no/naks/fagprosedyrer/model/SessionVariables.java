/**
 * 
 */
package no.naks.skjemabank.model;

/**
 * @author ojn
 *
 */
public interface SessionVariables {

	
	public boolean isnTest();
	public void setnTest(boolean nTest);

	public boolean isShowAnswers();
	public void setShowAnswers(boolean showAnswers);
	/**
	 * @return the showInputfield
	 */
	public boolean isShowInputfield();
	/**
	 * @param showInputfield the showInputfield to set
	 */
	public void setShowInputfield(boolean showInputfield);
	/**
	 * @return the alternateForm
	 */
	public boolean isAlternateForm();
	/**
	 * @param alternateForm the alternateForm to set
	 */
	public void setAlternateForm(boolean alternateForm);
	/**
	 * @return the moreText
	 */
	public boolean isMoreText();
	/**
	 * @param moreText the moreText to set
	 */
	public void setMoreText(boolean moreText);
	/**
	 * @return the scaleForm
	 */
	public boolean isScaleForm();
	/**
	 * @param scaleForm the scaleForm to set
	 */
	public void setScaleForm(boolean scaleForm);

	/**
	 * @return the newAnswertext
	 */
	public boolean isNewAnswertext();
	/**
	 * @param newAnswertext the newAnswertext to set
	 */
	public void setNewAnswertext(boolean newAnswertext);
	
	/**
	 * @return the alternateForm1
	 */
	public boolean isAlternateForm1();
	/**
	 * @param alternateForm1 the alternateForm1 to set
	 */
	public void setAlternateForm1(boolean alternateForm1);
	/**
	 * @return the showAnswerScales
	 */
	public boolean isShowAnswerScales();
	/**
	 * @param showAnswerScales the showAnswerScales to set
	 */
	public void setShowAnswerScales(boolean showAnswerScales);
	
	public boolean isNokkelord() ;
	public void setNokkelord(boolean nokkelord);
	public boolean isShowNokkelord();
	public void setShowNokkelord(boolean showNokkelord);
	public boolean isShowMenu();
	public void setShowMenu(boolean showMenu) ;

	public boolean isIndekserList();
	public void setIndekserList(boolean indekserList);
	public boolean isNyIndeks() ;
	public void setNyIndeks(boolean nyIndeks) ;
	
	public boolean isHovedIndekserList() ;
	public void setHovedIndekserList(boolean hovedIndekserList);
	public boolean isHovedNyIndeks() ;
	public void setHovedNyIndeks(boolean hovedNyIndeks) ;
	
}

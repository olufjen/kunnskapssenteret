package no.naks.emok.model;

public interface ILeveransemelding extends IMelding {

	public  boolean isOnskerhjelp();

	public  void setOnskerhjelp(boolean onskerhjelp);

	public  boolean isKanlareav();

	public  void setKanlareav(boolean kanlareav);

	public  boolean isVarslethelsetilsynet();

	public  void setVarslethelsetilsynet(boolean varslethelsetilsynet);

	public  String getRolle();

	public  void setRolle(String rolle);

}
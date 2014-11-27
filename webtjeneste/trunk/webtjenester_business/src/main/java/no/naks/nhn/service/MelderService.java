package no.naks.nhn.service;

import no.naks.emok.model.IBasismelding;
import no.naks.nhn.model.Person;

/**
 * @author olj
 * Dette grensesnittet tar i mot meldinger fra meldeskjema
 */
public interface MelderService {

	public String getSysCommand();

	public void setSysCommand(String sysCommand);

	public String getOpSys();
	
	public boolean isProduction();

	public void setProduction(boolean production);
	public void setOpSys(String opSys);
	public void sendMelding(IBasismelding melding,Person melder,Person leder);
	public void lagreMelding(IBasismelding melding,Person melder);
}
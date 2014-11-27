

/**
 * 
 */
package no.naks.emok.model;


/**
 * Den meldepliktige virksomheten har et ansvar for å følge opp en uønsket pasienthendelse. Det finnes ulike måter å organisere arbeidet på (som egne pasientsikkerhetsutvalg), og ulike verktøy for å undersøke hva som førte til den uønskede hendelsen (for eksempel kjerneårsaksanalyser). I noen tilfeller vil den meldepliktige virksomheten kunne ønske å dele den kunnskapen som kommer frem gjennom slike analyser med andre virksomheter og aktører i spesialisthelsetjensten. Læringsmeldingen er tenkt å være en kanal for å spre de gode læringshistoriene på en rask og effektiv måte. Det vil på sikt kunne være aktuelt for Kunnskapssenteret å etablere en nasjonal søkbar database for slike læringsmeldinger.        .
 */
public class Laringsmelding extends no.naks.emok.model.Leveransemelding implements ILaringsmelding{

	/**
	 * Fritekst. Virksomhetens beskrivelse av problemstilling, tiltak, resultater, læring internt og overføringsverdig til andre.
	 */
	private String laringhistorieinfo;

	/* (non-Javadoc)
	 * @see no.naks.emok.model.ILaringsmelding#getLaringhistorieinfo()
	 */
	public String getLaringhistorieinfo() {
		return laringhistorieinfo;
	}

	/* (non-Javadoc)
	 * @see no.naks.emok.model.ILaringsmelding#setLaringhistorieinfo(java.lang.String)
	 */
	public void setLaringhistorieinfo(String laringhistorieinfo) {
		this.laringhistorieinfo = laringhistorieinfo;
	}
	
}
package no.helsebiblioteket.admin.domain.key;

public class PositionTypeKey {
	private String value;
	
	public PositionTypeKey() {
	}
	public PositionTypeKey(String value) {
		this.value = value;
	}

	public static final PositionTypeKey ambulansearbeider = new PositionTypeKey("ambulansearbeider");
	public static final PositionTypeKey apotektekniker = new PositionTypeKey("apotektekniker");
	public static final PositionTypeKey audiograf = new PositionTypeKey("audiograf");
	public static final PositionTypeKey bioingenior = new PositionTypeKey("bioingenior");
	public static final PositionTypeKey ergoterapeut = new PositionTypeKey("ergoterapeut");
	public static final PositionTypeKey helsefagarbeider = new PositionTypeKey("helsefagarbeider");
	public static final PositionTypeKey fotterapeut = new PositionTypeKey("fotterapeut");
	public static final PositionTypeKey fysioterapeut = new PositionTypeKey("fysioterapeut");
	public static final PositionTypeKey helsesekretar = new PositionTypeKey("helsesekretar");
	public static final PositionTypeKey hjelpepleier = new PositionTypeKey("hjelpepleier");
	public static final PositionTypeKey jordmor = new PositionTypeKey("jordmor");
	public static final PositionTypeKey kiropraktor = new PositionTypeKey("kiropraktor");
	public static final PositionTypeKey klinisk_ernaringsfysiolog = new PositionTypeKey("klinisk_ernaringsfysiolog");
	public static final PositionTypeKey lege = new PositionTypeKey("lege");
	public static final PositionTypeKey omsorgsarbeider = new PositionTypeKey("omsorgsarbeider");
	public static final PositionTypeKey optiker = new PositionTypeKey("optiker");
	public static final PositionTypeKey ortopediingenior = new PositionTypeKey("ortopediingenior");
	public static final PositionTypeKey ortoptist = new PositionTypeKey("ortoptist");
	public static final PositionTypeKey perfusjonist = new PositionTypeKey("perfusjonist");
	public static final PositionTypeKey psykolog = new PositionTypeKey("psykolog");
	public static final PositionTypeKey radiograf = new PositionTypeKey("radiograf");
	public static final PositionTypeKey sykepleier = new PositionTypeKey("sykepleier");
	public static final PositionTypeKey tannhelsesekretar = new PositionTypeKey("tannhelsesekretar");
	public static final PositionTypeKey tannlege = new PositionTypeKey("tannlege");
	public static final PositionTypeKey tannpleier = new PositionTypeKey("tannpleier");
	public static final PositionTypeKey tanntekniker = new PositionTypeKey("tanntekniker");
	public static final PositionTypeKey vernepleier = new PositionTypeKey("vernepleier");
	public static final PositionTypeKey provisorfarmasoyt = new PositionTypeKey("provisorfarmasoyt");
	public static final PositionTypeKey reseptarfarmasoyt = new PositionTypeKey("reseptarfarmasoyt");
	
	// TODO: Remove?
	public static PositionTypeKey[] values() { return PositionTypeKey.values.clone(); }
	// TODO: Remove?
	public static PositionTypeKey valueOf(String name) { return (lookup(name)); }
	
	private static final PositionTypeKey[] values = new PositionTypeKey[] {  
		ambulansearbeider,
		apotektekniker,
		audiograf,
		bioingenior,
		ergoterapeut,
		helsefagarbeider,
		fotterapeut,
		fysioterapeut,
		helsesekretar,
		hjelpepleier,
		jordmor,
		kiropraktor,
		klinisk_ernaringsfysiolog,
		lege,
		omsorgsarbeider,
		optiker,
		ortopediingenior,
		ortoptist,
		perfusjonist,
		psykolog,
		radiograf,
		sykepleier,
		tannhelsesekretar,
		tannlege,
		tannpleier,
		tanntekniker,
		vernepleier,
		provisorfarmasoyt,
		reseptarfarmasoyt};
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String toString() {
		return this.value;
	}
	
	// TODO: Remove?
	private static PositionTypeKey lookup(String lookupString) {
		if (null != lookupString) {
			for (PositionTypeKey positionTypeKey : PositionTypeKey.values) {
				if (lookupString.equals(positionTypeKey.value)) {
					return positionTypeKey;
				}
			}
		}
		return null;
	}
}
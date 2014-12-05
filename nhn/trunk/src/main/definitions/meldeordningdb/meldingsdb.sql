-- ********************************************************************************
-- Generated by:   Select Database Schema File Generator                           
-- Model:          \\Enabler\srv-win-utv-02\Prosjekter\Meldesystem EMOK\0          
-- Filename:       Z:\jobb\sandbox\meldeordningdb\meldingsdb.sql                   
-- Database:       ORACLE                                                          
-- Mapping:        Default                                                         
-- Date:           22 February 2012                                                
-- ********************************************************************************


SET client_encoding = 'UTF8';
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'Standard public schema';


--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: 
--

CREATE PROCEDURAL LANGUAGE plpgsql;


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;


/*
Denne meldingstypen sendes n�r en av attributtene endres. 
Dette skjer automatisk. 

*/
CREATE TABLE pafyllingsmelding
(
    pafyllingid          serial              NOT NULL,
    -- Fritekst svar p� Kunnskapssenterets ettersp�rring  Dette er felt som m� legges til i Avvikssystemene.    svarpaettersporring: Dette er ikke et felt som finnes. Det blir ulogisk i forhold til den interne saksgang om innspill fra Kunnskapssenteret skal avstedkomme svar fra v�re ledere i et eget felt for Kunnskapssenteret. Hvis det er slik at evt spm fra Kunnskapssenteret skal underst�tte den interne �rsaksanalyse og forbedring, m� utfallet legges i v�re interne standardfelter for vurderinger/konklusjon, tiltak osv. Dette feltet antyder at Kunnskapssenteret legger opp til at saksbehandlingen skal v�re en kommunikasjon mellom v�r saksbehandler og Kunnskapssenteret som to parter med likt eierskap til saken. Slik er det ikke. 
    svarpaettersporring  TEXT                NULL,
    -- Virksomhetens klassifisering(er), som saksbehandler har gjort  Tallkode + medf�lgende tekstlig beskrivelse   
    klassifikasjonskode  TEXT                NULL,
    -- Hentes fra saksbehandlingsforl�pet. Saksansvarlig sin vurdering.  
    saksbehandlersvurdering TEXT             NULL,
    -- Tiltak som virksomheten har planlagt.    tiltak: vi kan ha mange tiltak til et avvik. Antar at vi vil sl� sammen alt tekstinnhold fra tiltakene til en tekststreng, med linjeskift og tiltakstittel mellom. Men det at det er et enkelt tekstfelt vil gj�re at Kunnskapssenteret ikke ser all relevant informasjon om tiltakene (status, frist, ansvarlig, evt evaluering).
    tiltak               TEXT                NULL,
    -- Stikkord som virksomheten har gitt saken    saksstikkord: Dette har vi ikke (tror dere har ideen fra Synergi�) Vi har til gjengjeld et "Tittel"-felt som gj�r samme nytten.  
    saksstikkord         TEXT                NULL,
    -- Fritekst annen informasjon
    tilleggsbeskrivelse  TEXT                NULL,
    -- Dette feltet sier status om saken er avsluttet fra MV sin side
    statusmv             TEXT                NULL,
    -- Beskriver tiltak/endringer som du mener kan/b�r gj�res for at noe tilsvarende ikke skal skje igjen. (ikke obligatorisk)
    forslagtiltak        TEXT                NULL,
    -- Hva er ev. gjort for � begrense den ev. skaden p� pasienten/personen. (For eks. gjennomf�rte strakstiltak)  (Ikke obligatorisk)  
    utfortestrakstiltak  TEXT                NULL,
    -- Hvorfor tror du hendelsen skjedde. (Ikke obligatorisk)
    arsaksbeskrivelse    TEXT                NULL,
    -- Hvilke umiddelbare konsekvenser for pasienten fikk dette  Finnes det en klassifikasjonskode for dette, s� �nsker vi det.  (Kode + tekst)   
    konsekvenser         TEXT                NULL,
    laringhistorieinfo   TEXT                NULL,
    datoavsluttet        timestamp_without_time_zone NULL,
    meldingsid           INTEGER              NOT NULL,
    PRIMARY KEY ( pafyllingid )
);

ALTER TABLE public.pafyllingsmelding OWNER TO postgres;

/*
Basismelding er den f�rste meldingen som sendes til Kunnskapssenteret n�r en ny u�nsket pasienthendelse blir registrert
*/
CREATE TABLE basismelding
(
    basismeldingid       serial              NOT NULL,
    -- Sykehus, Sykehusavdeling, post  (laveste enhet) der pasienten var da hendelsen inntraff (ikke obligatorisk)  Dersom det er utfyllende opplysninger om hvor hendelsen inntraff s� �nsker vi dette.
    stedforhendelsen     TIMESTAMP           NULL,
    -- Kort beskrivelse av hendelsen, hva, hvordan (OBLIGATORISK)
    hendelsesbeskrivelse TEXT                NULL,
    -- Hva er ev. gjort for � begrense den ev. skaden p� pasienten/personen. (For eks. gjennomf�rte strakstiltak)  (Ikke obligatorisk)  
    utfortestrakstiltak  TEXT                NULL,
    -- Beskriver tiltak/endringer som du mener kan/b�r gj�res for at noe tilsvarende ikke skal skje igjen. (ikke obligatorisk)
    forslagtiltak        TEXT                NULL,
    -- Hvorfor tror du hendelsen skjedde. (Ikke obligatorisk)
    arsaksbeskrivelse    TEXT                NULL,
    -- Hvilke umiddelbare konsekvenser denne hendelsen fikk (ikke obligatorisk)
    konsekvenser         TEXT                NULL,
    -- Hvordan ble hendelsen oppdaget? (Ikke obligatorisk)
    hvordanoppdaget      TEXT                NULL,
    -- F�dsels�r (NB ikke f�dselsdato) (ikke obligatorisk) Oversettes fra personnummer, hvis mulig  
    arfodt               INTEGER             NULL,
    -- Pasientens kj�nn (ikke obligatorisk) Oversettes fra f�dselsnummer hvis mulig
    kjonn                VARCHAR (2)         NULL,
    -- Tidspunkt (dato og klokkeslett) for n�r hendelsen inntraff (Dersom klokkeslett ikke finnes, angi tid p� d�gnet f. eks. dag, kveld natt) (ikke obligatorisk)
    tidforhendelsen      timestamp_without_time_zone NULL,
    -- Type uhell. Om dette er et nesten uhell (med postensiell pasientskade) eller et uhell med pasientskade. Dette viser alvorlighetsgrad av konsekvensen for pasienten av hendelsen.  Her trenger vi kode+fritekst for type uhell.
    typeuhell            TEXT                NULL,
    -- . Melders vurdering av alvorlighetsgrad  Finnes det en klassifikasjonskode for dette, s� �nsker vi det.  (Kode + tekst)  
    alvorlighetsgrad     TEXT                NULL,
    meldingsid           INTEGER              NOT NULL,
    PRIMARY KEY ( basismeldingid )
);

ALTER TABLE public.basismelding OWNER TO postgres;

/*
Dette er saksbehandler kunnskapssenteret
*/
CREATE TABLE saksbehandler
(
    saksbehandlerid      serial              NOT NULL,
    fornavn              TEXT                NULL,
    etternavn            TEXT                NULL,
    orgid                INTEGER              NOT NULL,
    PRIMARY KEY ( saksbehandlerid )
);

ALTER TABLE public.saksbehandler OWNER TO postgres;

/*
Melding er meldingen som blir overf�rt fra Meldepliktig virksomhet. 
Den blir en del av saken som opprettes. Det g�r mange meldinger p� en sak. Alle meldinger som blir sendt tilbake til meldepliktig virksomhet blr regisrtert her.
*/

CREATE TABLE melding
(
    meldingsid           serial              NOT NULL,
    datomottatt          timestamp_without_time_zone NULL,
    saksid               serial              NOT NULL,
    meldingstypeid       serial              NOT NULL,
    mvid                 serial              NOT NULL,
    onskerhjelp          VARCHAR (1)         NULL,
    kanlareav            VARCHAR (1)         NULL,
    varslethelsetilsynet VARCHAR (1)         NULL,
    rolle                TEXT                NULL,
    kontaktinfo          TEXT                NULL,
    PRIMARY KEY ( meldingsid )
);

ALTER TABLE public.melding OWNER TO postgres;
/*
Representerer alle typer meldinger som Kunnskapssenteret kan sende
*/
CREATE TABLE responsmelding
(
    responsid            serial              NOT NULL,
    -- Henter tekst fra et sett av standardtekster tilgjengelig  
    standardtekst        TEXT                NOT NULL,
    datosendt            timestamp_without_time_zone NULL,
    meldingstekst        TEXT                NULL,
    klassifikasjonskode  TEXT                NULL,
    klassifikasjonstekst TEXT                NULL,
    mvid                 INTEGER              NOT NULL,
    saksid               INTEGER              NOT NULL,
    meldingsid           INTEGER              NOT NULL,
    PRIMARY KEY ( responsid )
);

ALTER TABLE public.responsmelding OWNER TO postgres;

CREATE TABLE saksbehandlerlinje
(
    saksid               INTEGER              NOT NULL,
    saksbehandlerid      INTEGER              NOT NULL
);


ALTER TABLE public.saksbehandlerlinje OWNER TO postgres;
/*
Kan dette hentes fra andre kilder?
*/
CREATE TABLE meldepliktigvirksomhet
(
    mvid                 serial              NOT NULL,
    herid                INTEGER             NULL,
    organisasjonsnummer  INTEGER             NULL,
    PRIMARY KEY ( mvid )
);

ALTER TABLE public.meldepliktigvirksomhet OWNER TO postgres;

/*
Dette representerer et klassifikasjonssystem for meldinger. Det baserer seg �p� WHO sin struktur over hendelsestyper
*/
CREATE TABLE klassifikasjoner
(
    klassifikasjonsid    serial              NOT NULL,
    klassifikasjonskode  TEXT                NULL,
    klassifikasjonstekst TEXT                NULL,
    masterklassifikasjonsid INTEGER           NOT NULL,
    PRIMARY KEY ( klassifikasjonsid )
);

ALTER TABLE public.klassifikasjoner OWNER TO postgres;

/*
Sak representerer hendelsen som blir rapportert
*/
CREATE TABLE sak
(
    saksid               serial              NOT NULL,
    datoetablert         timestamp_without_time_zone NULL,
    PRIMARY KEY ( saksid )
);

ALTER TABLE public.sak OWNER TO postgres;

CREATE TABLE klassifikasjonlinje
(
    klassifikasjonsid    INTEGER              NOT NULL,
    saksid               INTEGER              NOT NULL
);

ALTER TABLE public.klassifikasjonslinje OWNER TO postgres;

/*
Dette representerer de meldingstyper som finnes i klassestrukturen over meldinger.
*/
CREATE TABLE meldingstype
(
    meldingstypeid       serial              NOT NULL,
    meldingstypekode     TEXT                NULL,
    meldingstypetekst    TEXT                NULL,
    PRIMARY KEY ( meldingstypeid )
);

ALTER TABLE public.meldingstype OWNER TO postgres;

ALTER TABLE pafyllingsmelding
    ADD CONSTRAINT meldingekstra FOREIGN KEY ( meldingsid ) REFERENCES melding ( meldingsid );

ALTER TABLE basismelding
    ADD CONSTRAINT meldingbasis FOREIGN KEY ( meldingsid ) REFERENCES melding ( meldingsid );

ALTER TABLE saksbehandler
    ADD CONSTRAINT Relationship26 FOREIGN KEY ( orgid ) REFERENCES organisasjon ( orgid );

ALTER TABLE melding
    ADD CONSTRAINT hfmelding FOREIGN KEY ( mvid ) REFERENCES meldepliktigvirksomhet ( mvid );

ALTER TABLE melding
    ADD CONSTRAINT meldingstype FOREIGN KEY ( meldingstypeid ) REFERENCES meldingstype ( meldingstypeid );

ALTER TABLE melding
    ADD CONSTRAINT sakmelding FOREIGN KEY ( saksid ) REFERENCES sak ( saksid );

ALTER TABLE responsmelding
    ADD CONSTRAINT meldingrespons FOREIGN KEY ( meldingsid ) REFERENCES melding ( meldingsid );

ALTER TABLE responsmelding
    ADD CONSTRAINT responshf FOREIGN KEY ( mvid ) REFERENCES meldepliktigvirksomhet ( mvid );

ALTER TABLE responsmelding
    ADD CONSTRAINT responssak FOREIGN KEY ( saksid ) REFERENCES sak ( saksid );

ALTER TABLE saksbehandlerlinje
    ADD CONSTRAINT saksaksbehandler FOREIGN KEY ( saksid ) REFERENCES sak ( saksid );

ALTER TABLE saksbehandlerlinje
    ADD CONSTRAINT saksbehandlersak FOREIGN KEY ( saksbehandlerid ) REFERENCES saksbehandler ( saksbehandlerid );

ALTER TABLE klassifikasjoner
    ADD CONSTRAINT delklassifikasjon FOREIGN KEY ( masterklassifikasjonsid ) REFERENCES klassifikasjoner ( klassifikasjonsid );

ALTER TABLE klassifikasjonlinje
    ADD CONSTRAINT hendelestypesak FOREIGN KEY ( klassifikasjonsid ) REFERENCES klassifikasjoner ( klassifikasjonsid );

ALTER TABLE klassifikasjonlinje
    ADD CONSTRAINT sakhendelesestype FOREIGN KEY ( saksid ) REFERENCES sak ( saksid );

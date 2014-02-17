--
-- PostgreSQL database dump
--

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

--
-- Name: clonequestionare(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION clonequestionare(sourcequestionareid integer) RETURNS integer
    AS $$
	DECLARE
		newQuestionareId  INTEGER = 0;
		newQuestionId INTEGER = 0;
		newQuestionareLineId INTEGER = 0;
		newAnswerScaleId INTEGER = 0;
		newThemeId INTEGER = 0;
	
		skjemalinjeSourceCursor CURSOR FOR 
			select * from tblskjemalinje tsl where tsl.sporreskjemaid = sourceQuestionareId; 

		skjemalinjeSource RECORD;

		sporsmallinjeSourceCursor CURSOR (sourceSporsmalId INTEGER) FOR
			select * from tblsporsmalinje spl where spl.sporsmalid = sourceSporsmalId;

		sporsmallinjeSource RECORD;
	BEGIN

		-- clone the questionare itself, and record the new id
 		insert into tblsporreskjema (
			sporreskjemaid
			,instrumentid
			,kontoid
			,eierid
			,skjemanavn
			,skjemadato
			,kommentarskjema
			,skjemakode
			,instrumentflag
		)
		select 
			nextval('tblsporreskjema_sporreskjemaid_seq')
			,tsk.instrumentid 
			,tsk.kontoid
			,tsk.eierid
			,tsk.skjemanavn || ' (Klone)'
			,now()
			,tsk.kommentarskjema
			,tsk.skjemakode
			,tsk.instrumentflag 
		from tblsporreskjema tsk
		where tsk.sporreskjemaid = sourceQuestionareId;
 
		newQuestionareId = currval('tblsporreskjema_sporreskjemaid_seq');
		
		-- TODO: Handle cloning of instrument		

		-- loop thru' all related questions and create copies of all questions and skjemalinjes
		OPEN skjemalinjeSourceCursor;

			LOOP
				FETCH skjemalinjeSourceCursor INTO skjemalinjeSource;

				IF NOT FOUND THEN
					EXIT; -- exit loop
				END IF;

				RAISE NOTICE 'Cloning question.';

				-- copy question
				insert into tblsporsmal (
					sporsmalid
					,partid
					,sporsmaltekst
					,kortversjontekst
					,notater
					,visnotater
					--,sporsmalid1 --???
				)
				select
					nextval('tblsporsmal_sporsmalid_seq')
					,spm.partid
					,spm.sporsmaltekst
					,spm.kortversjontekst
					,spm.notater
					,spm.visnotater
					--,spm.sporsmalid1 --???
				from tblsporsmal spm
				where spm.sporsmalid = skjemalinjeSource.sporsmalid;

				newQuestionId = currval('tblsporsmal_sporsmalid_seq');

				-- TODO: handle subquestion references here (partid)

				-- Copy answeralternatives and answerscale (all copied, no reuse).
				-- It is assumed that all rows in tblsvarlinje connected to current
				-- question is also connected to only one row in tblsvarskala.

				insert into tblsvarskala (
					svarskalaid
					,inputtypeid
					,antsvarkategorier
					,anker1
					,anker2
					,laveste
					,hoyeste
					,intervall
				)
				select
					nextval('tblsvarskala_svarskalaid_seq')
					,sk1.inputtypeid
					,sk1.antsvarkategorier
					,sk1.anker1
					,sk1.anker2
					,sk1.laveste
					,sk1.hoyeste
					,sk1.intervall
				from tblsvarskala sk1
				where sk1.svarskalaid = 
					(select sl2.svarskalaid 
						from tblsvarlinje sl2 
						where sl2.sporsmalid = skjemalinjeSource.sporsmalid
						limit 1);

				newAnswerScaleId = currval('tblsvarskala_svarskalaid_seq');

				RAISE NOTICE 'Cloned svarskala.';

				insert into tblsvarlinje (
					svarlinjeid
					,sporsmalid
					,svarskalaid
					,altsvartekst
					,svarrekkefolge
					,maxverdi
					,minverdi
					,notatfelt
					,nestesporsmalnr -- ???
				)
				select
					nextval('tblsvarlinje_svarlinjeid_seq')
					,newQuestionId
					,newAnswerScaleId
					,sl2.altsvartekst
					,sl2.svarrekkefolge
					,sl2.maxverdi
					,sl2.minverdi
					,sl2.notatfelt
					,sl2.nestesporsmalnr -- ?? is this a ref to another sporsmalid ??
				from tblsvarlinje sl2
				where sl2.sporsmalid = skjemalinjeSource.sporsmalid;

				RAISE NOTICE 'Cloned svarlinjes(s).';
				
				-- copy tblsporsmallinje and tbltema

				OPEN sporsmallinjeSourceCursor(skjemalinjeSource.sporsmalid);

					LOOP
						FETCH sporsmallinjeSourceCursor INTO sporsmallinjeSource;

						IF NOT FOUND THEN
							EXIT; -- exit loop
						END IF;

						insert into tbltema (
							temaid
							,tematekst
							,tematittel
							,skjemaid
						)
						select
							nextval('tbltema_temaid_seq')
							,tematekst
							,tematittel
							,newQuestionareId
						from tbltema t1
						where t1.temaid = sporsmallinjeSource.temaid;

						RAISE NOTICE 'Cloned tema.';

						newThemeId = currval('tbltema_temaid_seq');

						insert into tblsporsmalinje (
							sporsmalid
							,temaid
						)
						values
						(
							newQuestionId
							,newThemeId
						);

						RAISE NOTICE 'Cloned sporsmalinje.';

					END LOOP;

				CLOSE sporsmallinjeSourceCursor;

				-- connect to copied questionare via tblskjemalinje

				insert into tblskjemalinje (
					sporreskjemaid
					,sporsmalid
					,skjemakommentar
					,sporsmalnummer
					,obligatorisk
				)
				select
					newQuestionareId
					,newQuestionId
					,sl1.skjemakommentar 
					,sl1.sporsmalnummer
					,sl1.obligatorisk
				from tblskjemalinje sl1
				where sl1.skjemalinjeid = skjemalinjeSource.skjemalinjeid;

				RAISE NOTICE 'Cloned skjemalinje.';
					
				newQuestionareLineId = currval('tblskjemalinje_skjemalinjeid_seq');

				--clone comments on the questionare

				insert into tblskjemakommentar (
					skjemakommentarid
					,sporreskjemaid
					,kommentartekst
					,kommentardato
				)
				select
					nextval('tblskjemakommentar_skjemakommentarid_seq')
					,newQuestionareId
					,sko1.kommentartekst
					,sko1.kommentardato -- now()?
				from tblskjemakommentar sko1
				where sko1.sporreskjemaid = newQuestionareId;

				RAISE NOTICE 'Cloned skjemakommentar.';
	
			END LOOP;

		CLOSE skjemalinjeSourceCursor;

		RETURN newQuestionareId;
	END
	
	

$$
    LANGUAGE plpgsql;


ALTER FUNCTION public.clonequestionare(sourcequestionareid integer) OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: tblsporsmal; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblsporsmal (
    sporsmalid serial NOT NULL,
    partid integer,
    sporsmaltekst text NOT NULL,
    kortversjontekst text,
    notater text,
    visnotater integer
);


ALTER TABLE public.tblsporsmal OWNER TO postgres;

--
-- Name: deletesporsmal(text); Type: FUNCTION; Schema: public; Owner: innsiden
--

CREATE FUNCTION deletesporsmal(spid text) RETURNS SETOF tblsporsmal
    AS $$

	BEGIN
 
		DELETE from tblsvarlinje WHERE sporsmalid = spId;
		DELETE from tblskjemalinje WHERE sporsmalid = spId;
		DELETE from tblindekslinje WHERE sporsmalid = spId;
		DELETE from tblsporsmalinje WHERE sporsmalid = spId;
		UPDATE tblsporsmal set partid=null WHERE partid=spId;
		DELETE from tblsporsmal WHERE sporsmalid = spId;
		
	END

$$
    LANGUAGE plpgsql;


ALTER FUNCTION public.deletesporsmal(spid text) OWNER TO innsiden;

--
-- Name: tblskjemalinje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblskjemalinje (
    skjemalinjeid serial NOT NULL,
    sporreskjemaid integer NOT NULL,
    sporsmalid integer NOT NULL,
    skjemakommentar text,
    sporsmalnummer integer,
    obligatorisk integer
);


ALTER TABLE public.tblskjemalinje OWNER TO postgres;

--
-- Name: updatesporsmalorder(text); Type: FUNCTION; Schema: public; Owner: innsiden
--

CREATE FUNCTION updatesporsmalorder(txt text) RETURNS SETOF tblskjemalinje
    AS $$
	Declare
		counter  INTEGER =1;
		temp INTEGER = 1;
		value text='';
	BEGIN
		WHILE (counter<=4) LOOP
			value = split_part(txt, ',', temp);
			update tblskjemalinje set sporsmalnummer =temp where sporsmalid =value ;
			temp = temp+1;
		
			IF value = '' THEN
				counter=5;
			END IF;
			
		END LOOP;
	
	END
	
	

$$
    LANGUAGE plpgsql;


ALTER FUNCTION public.updatesporsmalorder(txt text) OWNER TO innsiden;

--
-- Name: tblsvarlinje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblsvarlinje (
    svarlinjeid serial NOT NULL,
    sporsmalid integer NOT NULL,
    svarskalaid integer NOT NULL,
    altsvartekst text,
    svarrekkefolge integer,
    maxverdi integer,
    minverdi integer,
    notatfelt text,
    nestesporsmalnr integer
);


ALTER TABLE public.tblsvarlinje OWNER TO postgres;

--
-- Name: updatesvarrekkefolge(text); Type: FUNCTION; Schema: public; Owner: innsiden
--

CREATE FUNCTION updatesvarrekkefolge(txt text) RETURNS SETOF tblsvarlinje
    AS $$
	Declare
		counter  INTEGER =1;
		seq INTEGER = 1;
		value text='';
	BEGIN
		WHILE (counter<=4) LOOP
			value = split_part(txt, ',', seq);
			update tblsvarlinje set svarrekkefolge= seq where svarlinjeid=value;
			
			seq = seq+1;
		
			IF value = '' THEN
				counter=5;
			END IF;
			
		END LOOP;
	
	END
	
	

$$
    LANGUAGE plpgsql;


ALTER FUNCTION public.updatesvarrekkefolge(txt text) OWNER TO innsiden;

--
-- Name: tblavdelingkontolinje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblavdelingkontolinje (
    institusjonid integer NOT NULL,
    kontoid integer NOT NULL
);


ALTER TABLE public.tblavdelingkontolinje OWNER TO postgres;

--
-- Name: tblbruker; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblbruker (
    brukerid serial NOT NULL,
    epost text NOT NULL,
    passord text NOT NULL
);


ALTER TABLE public.tblbruker OWNER TO postgres;

--
-- Name: tblbruker_brukerid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblbruker', 'brukerid'), 1, false);


--
-- Name: tbleier; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbleier (
    eierid serial NOT NULL,
    eiernavn text,
    eiersted text
);


ALTER TABLE public.tbleier OWNER TO postgres;

--
-- Name: tbleier_eierid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tbleier', 'eierid'), 1, false);


--
-- Name: tblforetak; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblforetak (
    foretakid serial NOT NULL,
    foretaknavn text NOT NULL,
    foretaksadresse text,
    telefon text,
    epost text,
    regionid integer NOT NULL,
    foretakdato timestamp without time zone
);


ALTER TABLE public.tblforetak OWNER TO postgres;

--
-- Name: tblforetak_foretakid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblforetak', 'foretakid'), 1, false);


--
-- Name: tblindeks; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblindeks (
    indeksid serial NOT NULL,
    subindeks integer,
    sporreskjemaid integer,
    indeksnummer integer,
    indekstekst text,
    formel text
);


ALTER TABLE public.tblindeks OWNER TO postgres;

--
-- Name: tblindeks_indeksid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblindeks', 'indeksid'), 1, false);


--
-- Name: tblindekslinje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblindekslinje (
    indeksid integer NOT NULL,
    sporsmalid integer NOT NULL
);


ALTER TABLE public.tblindekslinje OWNER TO postgres;

--
-- Name: tblinputtype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblinputtype (
    inputtypeid serial NOT NULL,
    feltbeskrivelse text
);


ALTER TABLE public.tblinputtype OWNER TO postgres;

--
-- Name: tblinputtype_inputtypeid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblinputtype', 'inputtypeid'), 1, false);


--
-- Name: tblinstitusjon; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblinstitusjon (
    institusjonid serial NOT NULL,
    foretakid integer NOT NULL,
    undersokelseid integer NOT NULL,
    institusjonstypeid integer NOT NULL,
    insitusjonsnavn text,
    addresse text,
    avdelingtelefon text,
    institusjonsdato character(25)
);


ALTER TABLE public.tblinstitusjon OWNER TO postgres;

--
-- Name: tblinstitusjon_institusjonid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblinstitusjon', 'institusjonid'), 1, false);


--
-- Name: tblinstitusjonstype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblinstitusjonstype (
    institusjonstypeid serial NOT NULL,
    institusjonstype text
);


ALTER TABLE public.tblinstitusjonstype OWNER TO postgres;

--
-- Name: tblinstitusjonstype_institusjonstypeid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblinstitusjonstype', 'institusjonstypeid'), 1, false);


--
-- Name: tblkontobrukerlinje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblkontobrukerlinje (
    kontoid integer NOT NULL,
    brukerid integer NOT NULL
);


ALTER TABLE public.tblkontobrukerlinje OWNER TO postgres;

--
-- Name: tbllitteraturreferanse; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbllitteraturreferanse (
    littrefid serial NOT NULL,
    sporreskjemaid integer NOT NULL,
    undersokelseid integer NOT NULL,
    referanse text
);


ALTER TABLE public.tbllitteraturreferanse OWNER TO postgres;

--
-- Name: tbllitteraturreferanse_littrefid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tbllitteraturreferanse', 'littrefid'), 1, false);


--
-- Name: tblnokkelord; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblnokkelord (
    informantid serial NOT NULL,
    nokkelord text
);


ALTER TABLE public.tblnokkelord OWNER TO postgres;

--
-- Name: tblnokkelord_informantid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblnokkelord', 'informantid'), 1, false);


--
-- Name: tblnokkelordlinje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblnokkelordlinje (
    informantid integer NOT NULL,
    undersokelseid integer NOT NULL
);


ALTER TABLE public.tblnokkelordlinje OWNER TO postgres;

--
-- Name: tblnokkelordskjemalinje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblnokkelordskjemalinje (
    informantid integer NOT NULL,
    sporreskjemaid integer NOT NULL
);


ALTER TABLE public.tblnokkelordskjemalinje OWNER TO postgres;

--
-- Name: tblregion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblregion (
    regionid serial NOT NULL,
    regionnavn text NOT NULL
);


ALTER TABLE public.tblregion OWNER TO postgres;

--
-- Name: tblregion_regionid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblregion', 'regionid'), 1, false);


--
-- Name: tblskjemakommentar; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblskjemakommentar (
    skjemakommentarid serial NOT NULL,
    sporreskjemaid integer NOT NULL,
    kommentartekst text,
    kommentardato timestamp without time zone
);


ALTER TABLE public.tblskjemakommentar OWNER TO postgres;

--
-- Name: tblskjemakommentar_skjemakommentarid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblskjemakommentar', 'skjemakommentarid'), 1, false);


--
-- Name: tblskjemakonto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblskjemakonto (
    kontoid serial NOT NULL,
    kontonavn text,
    opprettetdato timestamp without time zone
);


ALTER TABLE public.tblskjemakonto OWNER TO postgres;

--
-- Name: tblskjemakonto_kontoid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblskjemakonto', 'kontoid'), 1, false);


--
-- Name: tblskjemalinje_skjemalinjeid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblskjemalinje', 'skjemalinjeid'), 39, true);


--
-- Name: tblsporreskjema; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblsporreskjema (
    sporreskjemaid serial NOT NULL,
    instrumentid integer,
    kontoid integer,
    eierid integer,
    skjemanavn text,
    skjemadato timestamp without time zone,
    kommentarskjema text,
    skjemakode character varying(10),
    instrumentflag character varying(3)
);


ALTER TABLE public.tblsporreskjema OWNER TO postgres;

--
-- Name: tblsporreskjema_sporreskjemaid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblsporreskjema', 'sporreskjemaid'), 2, true);


--
-- Name: tblsporsmal_sporsmalid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblsporsmal', 'sporsmalid'), 39, true);


--
-- Name: tblsporsmalinje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblsporsmalinje (
    sporsmalid integer NOT NULL,
    temaid integer NOT NULL
);


ALTER TABLE public.tblsporsmalinje OWNER TO postgres;

--
-- Name: tblsvarlinje_svarlinjeid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblsvarlinje', 'svarlinjeid'), 187, true);


--
-- Name: tblsvarskala; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblsvarskala (
    svarskalaid serial NOT NULL,
    inputtypeid integer NOT NULL,
    antsvarkategorier integer,
    anker1 text,
    anker2 text,
    laveste integer,
    hoyeste integer,
    intervall integer
);


ALTER TABLE public.tblsvarskala OWNER TO postgres;

--
-- Name: tblsvarskala_svarskalaid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblsvarskala', 'svarskalaid'), 41, true);


--
-- Name: tbltema; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbltema (
    temaid serial NOT NULL,
    tematekst text,
    tematittel text,
    skjemaid integer
);


ALTER TABLE public.tbltema OWNER TO postgres;

--
-- Name: tbltema_temaid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tbltema', 'temaid'), 1, true);


--
-- Name: tbltemalinje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbltemalinje (
    temaid integer NOT NULL,
    indeksid integer NOT NULL
);


ALTER TABLE public.tbltemalinje OWNER TO postgres;

--
-- Name: tblundersokelse; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblundersokelse (
    undersokelseid serial NOT NULL,
    sporreskjemaid integer NOT NULL,
    undersokelseseierid integer NOT NULL,
    undersokelesesnavn text,
    arstall integer,
    publiseringdato timestamp without time zone,
    datainnsamlingsar integer,
    hensikt text
);


ALTER TABLE public.tblundersokelse OWNER TO postgres;

--
-- Name: tblundersokelse_undersokelseid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblundersokelse', 'undersokelseid'), 1, false);


--
-- Name: tblundersokelseseier; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblundersokelseseier (
    undersokelseseierid serial NOT NULL,
    eiernavn text
);


ALTER TABLE public.tblundersokelseseier OWNER TO postgres;

--
-- Name: tblundersokelseseier_undersokelseseierid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('tblundersokelseseier', 'undersokelseseierid'), 1, false);


--
-- Data for Name: tblavdelingkontolinje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblavdelingkontolinje (institusjonid, kontoid) FROM stdin;
\.


--
-- Data for Name: tblbruker; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblbruker (brukerid, epost, passord) FROM stdin;
\.


--
-- Data for Name: tbleier; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbleier (eierid, eiernavn, eiersted) FROM stdin;
\.


--
-- Data for Name: tblforetak; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblforetak (foretakid, foretaknavn, foretaksadresse, telefon, epost, regionid, foretakdato) FROM stdin;
\.


--
-- Data for Name: tblindeks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblindeks (indeksid, subindeks, sporreskjemaid, indeksnummer, indekstekst, formel) FROM stdin;
\.


--
-- Data for Name: tblindekslinje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblindekslinje (indeksid, sporsmalid) FROM stdin;
\.


--
-- Data for Name: tblinputtype; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblinputtype (inputtypeid, feltbeskrivelse) FROM stdin;
1	Radioboks ordinalskala
2	Radioboks svaralternativ
3	Flersvaralternativ
4	Inntastingsfelt
5	Tekstboks
\.


--
-- Data for Name: tblinstitusjon; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblinstitusjon (institusjonid, foretakid, undersokelseid, institusjonstypeid, insitusjonsnavn, addresse, avdelingtelefon, institusjonsdato) FROM stdin;
\.


--
-- Data for Name: tblinstitusjonstype; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblinstitusjonstype (institusjonstypeid, institusjonstype) FROM stdin;
\.


--
-- Data for Name: tblkontobrukerlinje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblkontobrukerlinje (kontoid, brukerid) FROM stdin;
\.


--
-- Data for Name: tbllitteraturreferanse; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbllitteraturreferanse (littrefid, sporreskjemaid, undersokelseid, referanse) FROM stdin;
\.


--
-- Data for Name: tblnokkelord; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblnokkelord (informantid, nokkelord) FROM stdin;
\.


--
-- Data for Name: tblnokkelordlinje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblnokkelordlinje (informantid, undersokelseid) FROM stdin;
\.


--
-- Data for Name: tblnokkelordskjemalinje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblnokkelordskjemalinje (informantid, sporreskjemaid) FROM stdin;
\.


--
-- Data for Name: tblregion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblregion (regionid, regionnavn) FROM stdin;
\.


--
-- Data for Name: tblskjemakommentar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblskjemakommentar (skjemakommentarid, sporreskjemaid, kommentartekst, kommentardato) FROM stdin;
\.


--
-- Data for Name: tblskjemakonto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblskjemakonto (kontoid, kontonavn, opprettetdato) FROM stdin;
\.


--
-- Data for Name: tblskjemalinje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblskjemalinje (skjemalinjeid, sporreskjemaid, sporsmalid, skjemakommentar, sporsmalnummer, obligatorisk) FROM stdin;
1	1	1	 	1	0
2	1	2	 	2	0
3	1	3	 	3	0
4	1	4	 	4	0
5	1	5	 	5	0
6	1	6	 	6	0
7	1	7	 	7	0
8	1	8	 	8	0
9	1	9	 	9	0
10	1	10	 	10	0
11	1	11	 	11	0
12	1	12	 	12	0
13	1	13	 	13	0
14	1	14	 	14	0
15	1	15	 	15	0
16	1	16	 	16	0
17	1	17	 	17	0
18	1	18	 	18	0
19	1	19	 	19	0
20	1	20	 	20	1
21	1	21	 	21	1
22	1	22	 	22	1
25	2	25	 	1	0
26	2	26	 	2	0
29	2	29	 	5	0
36	2	36	 	12	1
38	2	38	 	14	1
39	2	39	 	15	1
27	2	27	 	3	0
28	2	28	 	4	0
30	2	30	 	6	1
31	2	31	 	7	1
32	2	32	 	8	0
33	2	33	 	9	1
34	2	34	 	10	1
35	2	35	 	11	1
37	2	37	 	13	1
\.


--
-- Data for Name: tblsporreskjema; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblsporreskjema (sporreskjemaid, instrumentid, kontoid, eierid, skjemanavn, skjemadato, kommentarskjema, skjemakode, instrumentflag) FROM stdin;
1	\N	\N	\N	Habiliteringstiltak i Petö-studien	2010-11-04 13:02:51.475685	Hensikten med denne loggføringen er å samle informasjon om hvilke habiliteringstiltak barnet har mottatt den siste uka. I habiliteringstiltak inngår alle ekstra tiltak og støtteordninger som kan ytes til barn med CP for å støtte opp under barnet og familien. 		\N
2	\N	\N	\N	Meldeskjema for pasienter og pårørende (svangerskap, fødsel, barsel) 	2010-11-04 14:24:05.81809	Meldeskjema for pasienter og pårørende		\N
\.


--
-- Data for Name: tblsporsmal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblsporsmal (sporsmalid, partid, sporsmaltekst, kortversjontekst, notater, visnotater) FROM stdin;
1	\N	Hvor mange ganger har barnet trent på grovmotoriske ferdigheter (som å løfte holdet, sitte, gå, stå) den siste uken?			0
2	\N	Hvem har gjennomført treningen? 			0
3	\N	Hvor mange ganger har barnet trent håndmotoriske ferdigheter (som å gripe, slippe, klippe, tre perler, tegne) den siste uken?			0
4	\N	Hvem har gjennomført treningen? 			0
5	\N	Hvor mange ganger har barnet trent på tale og kommunikasjon (lyder, ord, benevne ting, synge, be om noe) den siste uken?			0
6	\N	Har barnet trent på alternative eller supplerende måter å kommunisere på (som tegn til tale, bruk av bilder, piktogram, talemaskin)?			0
7	\N	Hvem har gjennomført treningen? 			0
8	\N	Hvor mange ganger har barnet trent på å spise og drikke den siste uken?			0
10	\N	Hvor mange ganger har barnet trent på å kle av og på seg den siste uken?			0
9	\N	Hvem har gjennomført treningen? 			0
11	\N	Hvem har gjennomført treningen? 			0
12	\N	Hvor mange ganger har barnet trent på lekeferdigheter (som å bygge, puslespill, leke med dokker, rollelek) den siste uken?			0
13	\N	Hvem har gjennomført treningen? 			0
14	\N	Hvor mange ganger har barnet trent på sosiale ferdigheter (som lek med barn eller voksne, delta i samtaler) den siste uken?			0
15	\N	Hvem har gjennomført treningen? 			0
16	\N	Hvor mange ganger har barnet deltatt i fysisk aktivitet (som å svømme, ri, alliddrett, ake)			0
17	\N	Har dere deltatt på foreldrekurs eller annen opplæring i løpet av den siste uken?			0
18	\N	Hva var tema for opplæringen?			0
21	\N	Hvor ofte har dere brukt sang, rytme, rim eller regler som barnet har lært på Petö-kurs i treningen den siste uken?			0
20	\N	Hvor ofte har barnet brukt Petö-hjelpemidler (som stol, ribbevegg, gripetak på bordet) i treningen den siste uken?			0
22	\N	Er det noe du vil informere om eller utdype vedrørende barnets trening eller andre habiliteringstiltak som har foregått denne uken? 			0
19	\N	Hvilke fagpersoner og/eller instanser har dere hatt kontakt med denne uken? 			0
29	\N	Etter din mening, hvor alvorlig var det du melder om? 			0
36	\N	Hvis du vil at vi skal kontakte stedet der det  du melder om skjedde, må du gi oss en dato for hendelsen			0
39	\N	Dersom du tilleggsopplysninger, så kan du skrive disse her			0
25	\N	Gjelder meldingen svangerskapet, fødselen eller barselperioden?			0
28	\N	Hvor i behandlingskjeden skjedde det du melder om?			0
30	\N	Hvor lenge varte skaden? (ikke besvar dette hvis det du melder om er et nestenuhell)			0
31	\N	Hvordan har det gått med den skadede? (Ikke besvar dette om det du melder om er et nestenuhell)			0
32	\N	Hvem oppdaget det du melder om?\t			0
33	\N	Hva skjedde?			0
35	\N	Hvis du vil at vi skal kontakte stedet der det du melder om skjedde må du gi oss navn og adresse til behandlingsstedet Hvis du ikke vil at vi skal kontakte behandlingsstedet skal du ikke besvare dette spørsmålet			0
26	\N	Hvilken relasjon har du til den gravide/fødende?		Vi trenger opplysninger om deg som melder fra	1
27	\N	Ble noen skadet?		Du kan melde fra både dersom noen ble skadet og dersom du mener noen kunne ha blitt skadet pga det du melder om.	1
34	\N	Har du forbedringsforslag?		Har du forbedringsforslag eller forslag om hvordan det kan forhindres at det du melder om skal skje igjen?	1
37	\N	Hvem har du meldt fra til?		Har du meldt fra til andre om dette som du nå melder til oss?	1
38	\N	E-postadresse (telfonnummer kan brukes dersom du ikke har epost-adresse)		Er du villig til å gi oss tilleggsopplysninger på E-post dersom vi trenger det? Hvis ja, oppgi E-postadresse. Husk at dette er frivillig.	1
\.


--
-- Data for Name: tblsporsmalinje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblsporsmalinje (sporsmalid, temaid) FROM stdin;
20	1
\.


--
-- Data for Name: tblsvarlinje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblsvarlinje (svarlinjeid, sporsmalid, svarskalaid, altsvartekst, svarrekkefolge, maxverdi, minverdi, notatfelt, nestesporsmalnr) FROM stdin;
1	1	1	Ingen	1	0	0		0
2	1	1	1-2 ganger	2	0	0		0
3	1	1	3-6 ganger	3	0	0		0
4	1	1	Hver dag	4	0	0		0
5	1	1	Flere ganger daglig	5	0	0		0
6	1	1	Vet ikke	6	0	0		0
7	2	2	Foreldre	1	0	0		0
8	2	2	Assistent	2	0	0		0
9	2	2	Førskolelærer/spesialpedagog	3	0	0		0
10	2	2	Fysioterapeut	4	0	0		0
11	2	2	Andre	5	0	0		0
12	2	2	Vet ikke	6	0	0		0
13	3	1	Ingen	1	0	0		0
14	3	1	1-2 ganger	2	0	0		0
15	3	1	3-6 ganger	3	0	0		0
16	3	1	Hver dag	4	0	0		0
17	3	1	Flere ganger daglig	5	0	0		0
18	3	1	Vet ikke	6	0	0		0
19	4	3	Foreldre	1	0	0		0
20	4	3	Assistent	2	0	0		0
21	4	3	Førskolelærer/spesialpedagog	3	0	0		0
22	4	3	Ergoterapeut	4	0	0		0
23	4	3	Fysioterapeut	5	0	0		0
24	4	3	Andre	6	0	0		0
25	4	3	Vet ikke	7	0	0		0
26	5	1	Ingen	1	0	0		0
27	5	1	1-2 ganger	2	0	0		0
28	5	1	3-6 ganger	3	0	0		0
29	5	1	Hver dag	4	0	0		0
30	5	1	Flere ganger daglig	5	0	0		0
31	5	1	Vet ikke	6	0	0		0
32	6	4	Ja	1	0	0		0
33	6	4	Nei	2	0	0		0
34	7	3	Foreldre	1	0	0		0
39	7	3	Andre	6	0	0		0
40	7	3	Vet ikke	7	0	0		0
35	7	5	Andre familiemedlemmer	2	0	0		0
36	7	3	Assistent	3	0	0		0
37	7	3	Førskolelærer/spesialpedagog	4	0	0		0
38	7	3	Ergoterapeut	5	0	0		0
41	8	1	Ingen	1	0	0		0
42	8	1	1-2 ganger	2	0	0		0
43	8	1	3-6 ganger	3	0	0		0
44	8	1	Hver dag	4	0	0		0
45	8	1	Flere ganger daglig	5	0	0		0
46	8	1	Vet ikke	6	0	0		0
47	9	3	Foreldre	1	0	0		0
50	9	3	Ergoterapeut	4	0	0		0
51	9	3	Fysioterapeut	5	0	0		0
52	9	3	Andre	6	0	0		0
53	9	3	Vet ikke	7	0	0		0
48	9	7	Andre familiemedlemmer	2	0	0		0
49	9	3	Assistent	3	0	0		0
54	10	1	Ingen	1	0	0		0
55	10	1	1-2 ganger	2	0	0		0
56	10	1	3-6 ganger	3	0	0		0
57	10	1	Hver dag	4	0	0		0
58	10	1	Flere ganger daglig	5	0	0		0
59	10	1	Vet ikke	6	0	0		0
60	11	9	Foreldre	1	0	0		0
61	11	9	Andre familiemedlemmer	2	0	0		0
62	11	9	Assistent	3	0	0		0
63	11	9	Ergoterapeut	4	0	0		0
64	11	9	Fysioterapeut	5	0	0		0
65	11	9	Førskolelærer/spesialpedagog	6	0	0		0
66	11	9	Andre	7	0	0		0
67	11	9	Vet ikke	8	0	0		0
68	12	1	Ingen	1	0	0		0
69	12	1	1-2 ganger	2	0	0		0
70	12	1	3-6 ganger	3	0	0		0
71	12	1	Hver dag	4	0	0		0
72	12	1	Flere ganger daglig	5	0	0		0
73	12	1	Vet ikke	6	0	0		0
74	13	3	Foreldre	1	0	0		0
79	13	3	Andre	6	0	0		0
80	13	3	Vet ikke	7	0	0		0
75	13	11	Andre familiemedlemmer	2	0	0		0
76	13	3	Assistent	3	0	0		0
77	13	3	Førskolelærer/spsialpedagog	4	0	0		0
78	13	3	Ergoterapeut	5	0	0		0
81	14	1	Ingen	1	0	0		0
82	14	1	1-2 ganger	2	0	0		0
83	14	1	3-6 ganger	3	0	0		0
84	14	1	Hver dag	4	0	0		0
85	14	1	Flere ganger daglig	5	0	0		0
86	14	1	Vet ikke	6	0	0		0
87	15	2	Foreldre	1	0	0		0
91	15	2	Andre	5	0	0		0
92	15	2	Vet ikke	6	0	0		0
88	15	13	Andre familiemedlemmer	2	0	0		0
89	15	2	Assistent	3	0	0		0
90	15	2	Førskolelærer/spsialpedagog	4	0	0		0
93	16	1	Ingen	1	0	0		0
94	16	1	1-2 ganger	2	0	0		0
95	16	1	3-6 ganger	3	0	0		0
96	16	1	Hver dag	4	0	0		0
97	16	1	Flere ganger daglig	5	0	0		0
98	16	1	Vet ikke	6	0	0		0
99	17	4	Ja	1	0	0		0
100	17	4	Nei	2	0	0		0
101	18	16	Tema	0	3	0	 	0
102	19	17	Fysioterapeut	1	0	0		0
104	19	17	Ergoterapeut	3	0	0		0
105	19	17	Lege	4	0	0		0
106	19	17	Psykolog	5	0	0		0
107	19	17	Koordinator (ansvarsgruppe/individuell plan)	6	0	0		0
108	19	17	NAV	7	0	0		0
109	19	17	Barnehabiliteringstjenesten	8	0	0		0
110	19	17	Andre	9	0	0		0
111	20	1	Ingen	1	0	0		0
112	20	1	1-2 ganger	2	0	0		0
113	20	1	3-6 ganger	3	0	0		0
114	20	1	Hver dag	4	0	0		0
115	20	1	Flere ganger daglig	5	0	0		0
116	20	1	Vet ikke	6	0	0		0
117	21	1	Ingen	1	0	0		0
118	21	1	1-2 ganger	2	0	0		0
119	21	1	3-6 ganger	3	0	0		0
120	21	1	Hver dag	4	0	0		0
121	21	1	Flere ganger daglig	5	0	0		0
122	21	1	Vet ikke	6	0	0		0
123	22	20	Ønsker å informere om	0	4	0	 	0
103	19	17	Spesialpedagog	2	0	0		0
128	25	22	Svangerskapet	1	\N	0		0
129	25	22	Fødselen	2	\N	0		0
130	25	22	Barselperioden (annet enn amming)	3	\N	0		0
131	25	22	Amming	4	\N	0		0
132	26	23	Meg selv	1	\N	0		0
133	26	23	Min datter	2	\N	0		0
134	26	23	Min ektefelle/partner	3	\N	0		0
135	26	23	Et familiemedlem (annet enn datter/ektefelle/partner)	4	\N	0		0
136	26	23	Andre	5	\N	0		0
137	27	24	Nei	1	\N	0		0
138	27	24	Ja, mor	2	\N	0		0
139	27	24	Ja, barn	3	\N	0		0
140	27	24	Ja, andre	4	\N	0		0
141	28	27	Hos fastlegen	1	\N	0		0
142	28	27	På legevakten	2	\N	0		0
143	28	27	Hos jordmor (utenfor sykehus)	3	\N	0		0
144	28	27	På fødeavdeling	4	\N	0		0
145	28	27	På fødestue	5	\N	0		0
146	28	27	På barselavdeling	6	0	0		0
147	28	27	Hjemme	7	0	0		0
148	28	27	Under transport til/fra fødested	8	0	0		0
149	28	27	På helsestasjon	9	0	0		0
150	28	27	Annet	10	0	0		0
151	29	28	Mindre alvorlig	1	\N	0		0
152	29	28	Alvorlig	2	\N	0		0
153	29	28	Livstruende	3	\N	0		0
154	29	28	Dødsfall	4	\N	0		0
155	30	30	Mindre enn en uke	1	\N	0		0
156	30	30	En uke til en måned	2	\N	0		0
157	30	30	Mer enn en måned til et halvt år	3	\N	0		0
158	30	30	Mer enn et halvt år	4	\N	0		0
159	30	30	Mer enn ett år	5	\N	0		0
160	30	30	Vet ikke/ikke avklart	6	0	0		0
161	31	32	Helt frisk	1	\N	0		0
162	31	32	Varige skader som er milde	2	\N	0		0
163	31	32	Varige skader som er alvorlige	3	\N	0		0
164	31	32	Skadene førte til død	4	\N	0		0
165	31	32	Annet	5	\N	0		0
166	31	32	Vet ikke/ikke avklart	6	0	0		0
167	32	33	den gravide/fødende	1	\N	0		0
168	32	33	Pårørende	2	\N	0		0
169	32	33	Helsepersonell	3	\N	0		0
170	32	33	Andre	4	\N	0		0
171	32	33	Vet ikke	5	\N	0		0
175	36	37	Dato for hendelsen (frivillig)	0	1	0	 	0
176	37	39	Har ikke meldt dette til noen andre	1	\N	0		0
177	37	39	Helsetilsynet	2	\N	0		0
178	37	39	Fylkeslegen	3	\N	0		0
179	37	39	Pasientombudet	4	\N	0		0
180	37	39	Helseinstitusjonen der det du melder om hendte	5	\N	0		0
181	37	39	Norsk pasientskadeerstatning (NPE)	6	0	0		0
182	37	39	Andre	7	0	0		0
183	38	40	E-post eller telefonnummer (frivillig)	0	1	0	 	0
184	39	41	Tilleggsopplysninger	0	3	0	 	0
185	33	34	Beskriv med egne ord hva som skjedde	0	3	0	 	0
186	34	35	Beskriv med dine egne ord hva du synes bør gjøres for at dette ikke skal skje igjen/skje med andre.	0	3	0	 	0
187	35	36	Navn og adresse på stedet der det du melder om skjedde (frivillig)	0	2	0	 	0
\.


--
-- Data for Name: tblsvarskala; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblsvarskala (svarskalaid, inputtypeid, antsvarkategorier, anker1, anker2, laveste, hoyeste, intervall) FROM stdin;
5	2	0	Andre familiemedlemmer		0	0	0
6	2	0			0	0	0
7	2	0	Andre familiemedlemmer		0	0	0
8	2	0			0	0	0
9	3	8	Foreldre	Vet ikke	0	0	0
10	2	0			0	0	0
11	3	0	Andre familiemedlemmer		0	0	0
3	3	7	Foreldre	Vet ikke	0	0	0
12	2	0			0	0	0
13	3	0	Andre familiemedlemmer		0	0	0
2	3	6	Foreldre	Vet ikke	0	0	0
14	2	0			0	0	0
4	2	2	Ja	Nei	0	0	0
15	2	0			0	0	0
16	5	1	Tema	Tema	0	0	0
18	2	0			0	0	0
19	2	0			0	0	0
1	2	6	Ingen	Vet ikke	0	0	0
20	5	1	Ønsker å informere om	Ønsker å informere om	0	0	0
17	3	9	Fysioterapeut	Andre	0	0	0
23	2	5	Meg selv	Andre	0	0	0
25	2	5	Hos fastlegen	På fødestue	0	0	0
26	2	9	Hos fastlegen	På helsestasjon	0	0	0
28	2	4	Mindre alvorlig	Dødsfall	0	0	0
29	2	5	Mindre enn en uke	Mer enn ett år	0	0	0
31	2	5	Helt frisk	Annet	0	0	0
37	5	1	Dato for hendelsen (frivillig)	Dato for hendelsen (frivillig)	0	0	0
38	3	5	Har ikke meldt dette til noen andre	Helseinstitusjonen der det du melder om hendte	0	0	0
40	5	1	E-post eller telefonnummer (frivillig)	E-post eller telefonnummer (frivillig)	0	0	0
41	5	1	Tilleggsopplysninger	Tilleggsopplysninger	0	0	0
22	3	4	Svangerskapet	Amming	0	0	0
24	3	4	Nei	Ja, andre	0	0	0
27	3	10	Hos fastlegen	Annet	0	0	0
30	2	6	Mindre enn en uke	Vet ikke/ikke avklart	0	0	0
32	2	6	Helt frisk	Vet ikke/ikke avklart	0	0	0
33	3	5	den gravide/fødende	Vet ikke	0	0	0
34	5	1	Beskriv med egne ord hva som skjedde	Beskriv med egne ord hva som skjedde	0	0	0
35	5	1	Beskriv med dine egne ord hva du synes bør gjøres for at dette ikke skal skje igjen/skje med andre.	Beskriv med dine egne ord hva du synes bør gjøres for at dette ikke skal skje igjen/skje med andre.	0	0	0
36	5	1	Navn og adresse på stedet der det du melder om skjedde (frivillig)	Navn og adresse på stedet der det du melder om skjedde (frivillig)	0	0	0
39	3	7	Har ikke meldt dette til noen andre	Andre	0	0	0
\.


--
-- Data for Name: tbltema; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbltema (temaid, tematekst, tematittel, skjemaid) FROM stdin;
1		Fylles bare ut etter at dere har vært på Petø-kurs 	1
\.


--
-- Data for Name: tbltemalinje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbltemalinje (temaid, indeksid) FROM stdin;
\.


--
-- Data for Name: tblundersokelse; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblundersokelse (undersokelseid, sporreskjemaid, undersokelseseierid, undersokelesesnavn, arstall, publiseringdato, datainnsamlingsar, hensikt) FROM stdin;
\.


--
-- Data for Name: tblundersokelseseier; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tblundersokelseseier (undersokelseseierid, eiernavn) FROM stdin;
\.


--
-- Name: tblavdelingkontolinje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblavdelingkontolinje
    ADD CONSTRAINT tblavdelingkontolinje_pkey PRIMARY KEY (institusjonid, kontoid);


--
-- Name: tblbruker_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblbruker
    ADD CONSTRAINT tblbruker_pkey PRIMARY KEY (brukerid);


--
-- Name: tbleier_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbleier
    ADD CONSTRAINT tbleier_pkey PRIMARY KEY (eierid);


--
-- Name: tblforetak_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblforetak
    ADD CONSTRAINT tblforetak_pkey PRIMARY KEY (foretakid);


--
-- Name: tblindeks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblindeks
    ADD CONSTRAINT tblindeks_pkey PRIMARY KEY (indeksid);


--
-- Name: tblindekslinje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblindekslinje
    ADD CONSTRAINT tblindekslinje_pkey PRIMARY KEY (indeksid, sporsmalid);


--
-- Name: tblinputtype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblinputtype
    ADD CONSTRAINT tblinputtype_pkey PRIMARY KEY (inputtypeid);


--
-- Name: tblinstitusjon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblinstitusjon
    ADD CONSTRAINT tblinstitusjon_pkey PRIMARY KEY (institusjonid);


--
-- Name: tblinstitusjonstype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblinstitusjonstype
    ADD CONSTRAINT tblinstitusjonstype_pkey PRIMARY KEY (institusjonstypeid);


--
-- Name: tblkontobrukerlinje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblkontobrukerlinje
    ADD CONSTRAINT tblkontobrukerlinje_pkey PRIMARY KEY (kontoid, brukerid);


--
-- Name: tbllitteraturreferanse_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbllitteraturreferanse
    ADD CONSTRAINT tbllitteraturreferanse_pkey PRIMARY KEY (littrefid);


--
-- Name: tblnokkelord_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblnokkelord
    ADD CONSTRAINT tblnokkelord_pkey PRIMARY KEY (informantid);


--
-- Name: tblnokkelordlinje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblnokkelordlinje
    ADD CONSTRAINT tblnokkelordlinje_pkey PRIMARY KEY (informantid, undersokelseid);


--
-- Name: tblnokkelordskjemalinje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblnokkelordskjemalinje
    ADD CONSTRAINT tblnokkelordskjemalinje_pkey PRIMARY KEY (informantid, sporreskjemaid);


--
-- Name: tblregion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblregion
    ADD CONSTRAINT tblregion_pkey PRIMARY KEY (regionid);


--
-- Name: tblskjemakommentar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblskjemakommentar
    ADD CONSTRAINT tblskjemakommentar_pkey PRIMARY KEY (skjemakommentarid);


--
-- Name: tblskjemakonto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblskjemakonto
    ADD CONSTRAINT tblskjemakonto_pkey PRIMARY KEY (kontoid);


--
-- Name: tblskjemalinje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblskjemalinje
    ADD CONSTRAINT tblskjemalinje_pkey PRIMARY KEY (skjemalinjeid);


--
-- Name: tblsporreskjema_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblsporreskjema
    ADD CONSTRAINT tblsporreskjema_pkey PRIMARY KEY (sporreskjemaid);


--
-- Name: tblsporsmal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblsporsmal
    ADD CONSTRAINT tblsporsmal_pkey PRIMARY KEY (sporsmalid);


--
-- Name: tblsporsmalinje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblsporsmalinje
    ADD CONSTRAINT tblsporsmalinje_pkey PRIMARY KEY (sporsmalid, temaid);


--
-- Name: tblsvarlinje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblsvarlinje
    ADD CONSTRAINT tblsvarlinje_pkey PRIMARY KEY (svarlinjeid);


--
-- Name: tblsvarskala_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblsvarskala
    ADD CONSTRAINT tblsvarskala_pkey PRIMARY KEY (svarskalaid);


--
-- Name: tbltema_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbltema
    ADD CONSTRAINT tbltema_pkey PRIMARY KEY (temaid);


--
-- Name: tbltemalinje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbltemalinje
    ADD CONSTRAINT tbltemalinje_pkey PRIMARY KEY (temaid, indeksid);


--
-- Name: tblundersokelse_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblundersokelse
    ADD CONSTRAINT tblundersokelse_pkey PRIMARY KEY (undersokelseid);


--
-- Name: tblundersokelseseier_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblundersokelseseier
    ADD CONSTRAINT tblundersokelseseier_pkey PRIMARY KEY (undersokelseseierid);


--
-- Name: avdelingkonto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblavdelingkontolinje
    ADD CONSTRAINT avdelingkonto FOREIGN KEY (institusjonid) REFERENCES tblinstitusjon(institusjonid);


--
-- Name: brukerkonto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblkontobrukerlinje
    ADD CONSTRAINT brukerkonto FOREIGN KEY (brukerid) REFERENCES tblbruker(brukerid);


--
-- Name: delav; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblundersokelse
    ADD CONSTRAINT delav FOREIGN KEY (sporreskjemaid) REFERENCES tblsporreskjema(sporreskjemaid);


--
-- Name: foretakavdeling; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblinstitusjon
    ADD CONSTRAINT foretakavdeling FOREIGN KEY (foretakid) REFERENCES tblforetak(foretakid);


--
-- Name: gjennomforti; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblinstitusjon
    ADD CONSTRAINT gjennomforti FOREIGN KEY (undersokelseid) REFERENCES tblundersokelse(undersokelseid);


--
-- Name: indekssporsmal; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblindekslinje
    ADD CONSTRAINT indekssporsmal FOREIGN KEY (indeksid) REFERENCES tblindeks(indeksid);


--
-- Name: indekstema; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbltemalinje
    ADD CONSTRAINT indekstema FOREIGN KEY (indeksid) REFERENCES tblindeks(indeksid);


--
-- Name: inputtype; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblsvarskala
    ADD CONSTRAINT inputtype FOREIGN KEY (inputtypeid) REFERENCES tblinputtype(inputtypeid);


--
-- Name: institusjonstype; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblinstitusjon
    ADD CONSTRAINT institusjonstype FOREIGN KEY (institusjonstypeid) REFERENCES tblinstitusjonstype(institusjonstypeid);


--
-- Name: instrument; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblsporreskjema
    ADD CONSTRAINT instrument FOREIGN KEY (instrumentid) REFERENCES tblsporreskjema(sporreskjemaid);


--
-- Name: knotobruker; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblkontobrukerlinje
    ADD CONSTRAINT knotobruker FOREIGN KEY (kontoid) REFERENCES tblskjemakonto(kontoid);


--
-- Name: kontoavdeling; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblavdelingkontolinje
    ADD CONSTRAINT kontoavdeling FOREIGN KEY (kontoid) REFERENCES tblskjemakonto(kontoid);


--
-- Name: nokkelskjema; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblnokkelordskjemalinje
    ADD CONSTRAINT nokkelskjema FOREIGN KEY (informantid) REFERENCES tblnokkelord(informantid);


--
-- Name: nokkelundersokelse; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblnokkelordlinje
    ADD CONSTRAINT nokkelundersokelse FOREIGN KEY (informantid) REFERENCES tblnokkelord(informantid);


--
-- Name: part; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblsporsmal
    ADD CONSTRAINT part FOREIGN KEY (partid) REFERENCES tblsporsmal(sporsmalid);


--
-- Name: regionforetak; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblforetak
    ADD CONSTRAINT regionforetak FOREIGN KEY (regionid) REFERENCES tblregion(regionid);


--
-- Name: skjemaeier; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblsporreskjema
    ADD CONSTRAINT skjemaeier FOREIGN KEY (eierid) REFERENCES tbleier(eierid);


--
-- Name: skjemaindeks; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblindeks
    ADD CONSTRAINT skjemaindeks FOREIGN KEY (sporreskjemaid) REFERENCES tblsporreskjema(sporreskjemaid);


--
-- Name: skjemakommentar; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblskjemakommentar
    ADD CONSTRAINT skjemakommentar FOREIGN KEY (sporreskjemaid) REFERENCES tblsporreskjema(sporreskjemaid);


--
-- Name: skjemakonto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblsporreskjema
    ADD CONSTRAINT skjemakonto FOREIGN KEY (kontoid) REFERENCES tblskjemakonto(kontoid);


--
-- Name: skjemanokkel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblnokkelordskjemalinje
    ADD CONSTRAINT skjemanokkel FOREIGN KEY (sporreskjemaid) REFERENCES tblsporreskjema(sporreskjemaid);


--
-- Name: skjemareferanse; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbllitteraturreferanse
    ADD CONSTRAINT skjemareferanse FOREIGN KEY (sporreskjemaid) REFERENCES tblsporreskjema(sporreskjemaid);


--
-- Name: skjemasporsmal; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblskjemalinje
    ADD CONSTRAINT skjemasporsmal FOREIGN KEY (sporreskjemaid) REFERENCES tblsporreskjema(sporreskjemaid);


--
-- Name: sporsmalindeks; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblindekslinje
    ADD CONSTRAINT sporsmalindeks FOREIGN KEY (sporsmalid) REFERENCES tblsporsmal(sporsmalid);


--
-- Name: sporsmalskjema; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblskjemalinje
    ADD CONSTRAINT sporsmalskjema FOREIGN KEY (sporsmalid) REFERENCES tblsporsmal(sporsmalid);


--
-- Name: sporsmalsvar; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblsvarlinje
    ADD CONSTRAINT sporsmalsvar FOREIGN KEY (sporsmalid) REFERENCES tblsporsmal(sporsmalid);


--
-- Name: sporsmaltema; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblsporsmalinje
    ADD CONSTRAINT sporsmaltema FOREIGN KEY (sporsmalid) REFERENCES tblsporsmal(sporsmalid);


--
-- Name: subindeks; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblindeks
    ADD CONSTRAINT subindeks FOREIGN KEY (subindeks) REFERENCES tblindeks(indeksid);


--
-- Name: svarsporsmal; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblsvarlinje
    ADD CONSTRAINT svarsporsmal FOREIGN KEY (svarskalaid) REFERENCES tblsvarskala(svarskalaid);


--
-- Name: temaindeks; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbltemalinje
    ADD CONSTRAINT temaindeks FOREIGN KEY (temaid) REFERENCES tbltema(temaid);


--
-- Name: temasporsmal; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblsporsmalinje
    ADD CONSTRAINT temasporsmal FOREIGN KEY (temaid) REFERENCES tbltema(temaid);


--
-- Name: undersokelseeier; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblundersokelse
    ADD CONSTRAINT undersokelseeier FOREIGN KEY (undersokelseseierid) REFERENCES tblundersokelseseier(undersokelseseierid);


--
-- Name: undersokelsenokkel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblnokkelordlinje
    ADD CONSTRAINT undersokelsenokkel FOREIGN KEY (undersokelseid) REFERENCES tblundersokelse(undersokelseid);


--
-- Name: undersokelsereferanse; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbllitteraturreferanse
    ADD CONSTRAINT undersokelsereferanse FOREIGN KEY (undersokelseid) REFERENCES tblundersokelse(undersokelseid);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: tblsporsmal; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblsporsmal FROM PUBLIC;
REVOKE ALL ON TABLE tblsporsmal FROM postgres;
GRANT ALL ON TABLE tblsporsmal TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblsporsmal TO innsiden;


--
-- Name: tblskjemalinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblskjemalinje FROM PUBLIC;
REVOKE ALL ON TABLE tblskjemalinje FROM postgres;
GRANT ALL ON TABLE tblskjemalinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblskjemalinje TO innsiden;


--
-- Name: tblsvarlinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblsvarlinje FROM PUBLIC;
REVOKE ALL ON TABLE tblsvarlinje FROM postgres;
GRANT ALL ON TABLE tblsvarlinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblsvarlinje TO innsiden;


--
-- Name: tblavdelingkontolinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblavdelingkontolinje FROM PUBLIC;
REVOKE ALL ON TABLE tblavdelingkontolinje FROM postgres;
GRANT ALL ON TABLE tblavdelingkontolinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblavdelingkontolinje TO innsiden;


--
-- Name: tblbruker; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblbruker FROM PUBLIC;
REVOKE ALL ON TABLE tblbruker FROM postgres;
GRANT ALL ON TABLE tblbruker TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblbruker TO innsiden;


--
-- Name: tblbruker_brukerid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblbruker_brukerid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblbruker_brukerid_seq FROM postgres;
GRANT ALL ON TABLE tblbruker_brukerid_seq TO postgres;
GRANT ALL ON TABLE tblbruker_brukerid_seq TO innsiden;


--
-- Name: tbleier; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tbleier FROM PUBLIC;
REVOKE ALL ON TABLE tbleier FROM postgres;
GRANT ALL ON TABLE tbleier TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tbleier TO innsiden;


--
-- Name: tbleier_eierid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tbleier_eierid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tbleier_eierid_seq FROM postgres;
GRANT ALL ON TABLE tbleier_eierid_seq TO postgres;
GRANT SELECT ON TABLE tbleier_eierid_seq TO utsiden;
GRANT ALL ON TABLE tbleier_eierid_seq TO innsiden;


--
-- Name: tblforetak; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblforetak FROM PUBLIC;
REVOKE ALL ON TABLE tblforetak FROM postgres;
GRANT ALL ON TABLE tblforetak TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblforetak TO innsiden;


--
-- Name: tblforetak_foretakid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblforetak_foretakid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblforetak_foretakid_seq FROM postgres;
GRANT ALL ON TABLE tblforetak_foretakid_seq TO postgres;
GRANT ALL ON TABLE tblforetak_foretakid_seq TO innsiden;


--
-- Name: tblindeks; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblindeks FROM PUBLIC;
REVOKE ALL ON TABLE tblindeks FROM postgres;
GRANT ALL ON TABLE tblindeks TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblindeks TO innsiden;


--
-- Name: tblindeks_indeksid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblindeks_indeksid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblindeks_indeksid_seq FROM postgres;
GRANT ALL ON TABLE tblindeks_indeksid_seq TO postgres;
GRANT SELECT ON TABLE tblindeks_indeksid_seq TO utsiden;
GRANT ALL ON TABLE tblindeks_indeksid_seq TO innsiden;


--
-- Name: tblindekslinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblindekslinje FROM PUBLIC;
REVOKE ALL ON TABLE tblindekslinje FROM postgres;
GRANT ALL ON TABLE tblindekslinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblindekslinje TO innsiden;


--
-- Name: tblinputtype; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblinputtype FROM PUBLIC;
REVOKE ALL ON TABLE tblinputtype FROM postgres;
GRANT ALL ON TABLE tblinputtype TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblinputtype TO innsiden;


--
-- Name: tblinputtype_inputtypeid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblinputtype_inputtypeid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblinputtype_inputtypeid_seq FROM postgres;
GRANT ALL ON TABLE tblinputtype_inputtypeid_seq TO postgres;
GRANT SELECT ON TABLE tblinputtype_inputtypeid_seq TO utsiden;
GRANT ALL ON TABLE tblinputtype_inputtypeid_seq TO innsiden;


--
-- Name: tblinstitusjon; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblinstitusjon FROM PUBLIC;
REVOKE ALL ON TABLE tblinstitusjon FROM postgres;
GRANT ALL ON TABLE tblinstitusjon TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblinstitusjon TO innsiden;


--
-- Name: tblinstitusjon_institusjonid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblinstitusjon_institusjonid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblinstitusjon_institusjonid_seq FROM postgres;
GRANT ALL ON TABLE tblinstitusjon_institusjonid_seq TO postgres;
GRANT SELECT ON TABLE tblinstitusjon_institusjonid_seq TO utsiden;
GRANT ALL ON TABLE tblinstitusjon_institusjonid_seq TO innsiden;


--
-- Name: tblinstitusjonstype; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblinstitusjonstype FROM PUBLIC;
REVOKE ALL ON TABLE tblinstitusjonstype FROM postgres;
GRANT ALL ON TABLE tblinstitusjonstype TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblinstitusjonstype TO innsiden;


--
-- Name: tblinstitusjonstype_institusjonstypeid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblinstitusjonstype_institusjonstypeid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblinstitusjonstype_institusjonstypeid_seq FROM postgres;
GRANT ALL ON TABLE tblinstitusjonstype_institusjonstypeid_seq TO postgres;
GRANT SELECT ON TABLE tblinstitusjonstype_institusjonstypeid_seq TO utsiden;
GRANT ALL ON TABLE tblinstitusjonstype_institusjonstypeid_seq TO innsiden;


--
-- Name: tblkontobrukerlinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblkontobrukerlinje FROM PUBLIC;
REVOKE ALL ON TABLE tblkontobrukerlinje FROM postgres;
GRANT ALL ON TABLE tblkontobrukerlinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblkontobrukerlinje TO innsiden;


--
-- Name: tbllitteraturreferanse; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tbllitteraturreferanse FROM PUBLIC;
REVOKE ALL ON TABLE tbllitteraturreferanse FROM postgres;
GRANT ALL ON TABLE tbllitteraturreferanse TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tbllitteraturreferanse TO innsiden;


--
-- Name: tbllitteraturreferanse_littrefid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tbllitteraturreferanse_littrefid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tbllitteraturreferanse_littrefid_seq FROM postgres;
GRANT ALL ON TABLE tbllitteraturreferanse_littrefid_seq TO postgres;
GRANT SELECT ON TABLE tbllitteraturreferanse_littrefid_seq TO utsiden;
GRANT ALL ON TABLE tbllitteraturreferanse_littrefid_seq TO innsiden;


--
-- Name: tblnokkelord; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblnokkelord FROM PUBLIC;
REVOKE ALL ON TABLE tblnokkelord FROM postgres;
GRANT ALL ON TABLE tblnokkelord TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblnokkelord TO innsiden;


--
-- Name: tblnokkelord_informantid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblnokkelord_informantid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblnokkelord_informantid_seq FROM postgres;
GRANT ALL ON TABLE tblnokkelord_informantid_seq TO postgres;
GRANT SELECT ON TABLE tblnokkelord_informantid_seq TO utsiden;
GRANT ALL ON TABLE tblnokkelord_informantid_seq TO innsiden;


--
-- Name: tblnokkelordlinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblnokkelordlinje FROM PUBLIC;
REVOKE ALL ON TABLE tblnokkelordlinje FROM postgres;
GRANT ALL ON TABLE tblnokkelordlinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblnokkelordlinje TO innsiden;


--
-- Name: tblnokkelordskjemalinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblnokkelordskjemalinje FROM PUBLIC;
REVOKE ALL ON TABLE tblnokkelordskjemalinje FROM postgres;
GRANT ALL ON TABLE tblnokkelordskjemalinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblnokkelordskjemalinje TO innsiden;


--
-- Name: tblregion; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblregion FROM PUBLIC;
REVOKE ALL ON TABLE tblregion FROM postgres;
GRANT ALL ON TABLE tblregion TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblregion TO innsiden;


--
-- Name: tblregion_regionid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblregion_regionid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblregion_regionid_seq FROM postgres;
GRANT ALL ON TABLE tblregion_regionid_seq TO postgres;
GRANT ALL ON TABLE tblregion_regionid_seq TO innsiden;


--
-- Name: tblskjemakommentar; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblskjemakommentar FROM PUBLIC;
REVOKE ALL ON TABLE tblskjemakommentar FROM postgres;
GRANT ALL ON TABLE tblskjemakommentar TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblskjemakommentar TO innsiden;


--
-- Name: tblskjemakommentar_skjemakommentarid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblskjemakommentar_skjemakommentarid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblskjemakommentar_skjemakommentarid_seq FROM postgres;
GRANT ALL ON TABLE tblskjemakommentar_skjemakommentarid_seq TO postgres;
GRANT SELECT ON TABLE tblskjemakommentar_skjemakommentarid_seq TO utsiden;
GRANT ALL ON TABLE tblskjemakommentar_skjemakommentarid_seq TO innsiden;


--
-- Name: tblskjemakonto; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblskjemakonto FROM PUBLIC;
REVOKE ALL ON TABLE tblskjemakonto FROM postgres;
GRANT ALL ON TABLE tblskjemakonto TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblskjemakonto TO innsiden;


--
-- Name: tblskjemakonto_kontoid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblskjemakonto_kontoid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblskjemakonto_kontoid_seq FROM postgres;
GRANT ALL ON TABLE tblskjemakonto_kontoid_seq TO postgres;
GRANT SELECT ON TABLE tblskjemakonto_kontoid_seq TO utsiden;
GRANT ALL ON TABLE tblskjemakonto_kontoid_seq TO innsiden;


--
-- Name: tblskjemalinje_skjemalinjeid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblskjemalinje_skjemalinjeid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblskjemalinje_skjemalinjeid_seq FROM postgres;
GRANT ALL ON TABLE tblskjemalinje_skjemalinjeid_seq TO postgres;
GRANT SELECT ON TABLE tblskjemalinje_skjemalinjeid_seq TO utsiden;
GRANT ALL ON TABLE tblskjemalinje_skjemalinjeid_seq TO innsiden;


--
-- Name: tblsporreskjema; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblsporreskjema FROM PUBLIC;
REVOKE ALL ON TABLE tblsporreskjema FROM postgres;
GRANT ALL ON TABLE tblsporreskjema TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblsporreskjema TO innsiden;


--
-- Name: tblsporreskjema_sporreskjemaid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblsporreskjema_sporreskjemaid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblsporreskjema_sporreskjemaid_seq FROM postgres;
GRANT ALL ON TABLE tblsporreskjema_sporreskjemaid_seq TO postgres;
GRANT SELECT ON TABLE tblsporreskjema_sporreskjemaid_seq TO utsiden;
GRANT ALL ON TABLE tblsporreskjema_sporreskjemaid_seq TO innsiden;


--
-- Name: tblsporsmal_sporsmalid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblsporsmal_sporsmalid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblsporsmal_sporsmalid_seq FROM postgres;
GRANT ALL ON TABLE tblsporsmal_sporsmalid_seq TO postgres;
GRANT SELECT ON TABLE tblsporsmal_sporsmalid_seq TO utsiden;
GRANT ALL ON TABLE tblsporsmal_sporsmalid_seq TO innsiden;


--
-- Name: tblsporsmalinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblsporsmalinje FROM PUBLIC;
REVOKE ALL ON TABLE tblsporsmalinje FROM postgres;
GRANT ALL ON TABLE tblsporsmalinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblsporsmalinje TO innsiden;


--
-- Name: tblsvarlinje_svarlinjeid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblsvarlinje_svarlinjeid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblsvarlinje_svarlinjeid_seq FROM postgres;
GRANT ALL ON TABLE tblsvarlinje_svarlinjeid_seq TO postgres;
GRANT SELECT ON TABLE tblsvarlinje_svarlinjeid_seq TO utsiden;
GRANT ALL ON TABLE tblsvarlinje_svarlinjeid_seq TO innsiden;


--
-- Name: tblsvarskala; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblsvarskala FROM PUBLIC;
REVOKE ALL ON TABLE tblsvarskala FROM postgres;
GRANT ALL ON TABLE tblsvarskala TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblsvarskala TO innsiden;


--
-- Name: tblsvarskala_svarskalaid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblsvarskala_svarskalaid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblsvarskala_svarskalaid_seq FROM postgres;
GRANT ALL ON TABLE tblsvarskala_svarskalaid_seq TO postgres;
GRANT SELECT ON TABLE tblsvarskala_svarskalaid_seq TO utsiden;
GRANT ALL ON TABLE tblsvarskala_svarskalaid_seq TO innsiden;


--
-- Name: tbltema; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tbltema FROM PUBLIC;
REVOKE ALL ON TABLE tbltema FROM postgres;
GRANT ALL ON TABLE tbltema TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tbltema TO innsiden;


--
-- Name: tbltema_temaid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tbltema_temaid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tbltema_temaid_seq FROM postgres;
GRANT ALL ON TABLE tbltema_temaid_seq TO postgres;
GRANT SELECT ON TABLE tbltema_temaid_seq TO utsiden;
GRANT ALL ON TABLE tbltema_temaid_seq TO innsiden;


--
-- Name: tbltemalinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tbltemalinje FROM PUBLIC;
REVOKE ALL ON TABLE tbltemalinje FROM postgres;
GRANT ALL ON TABLE tbltemalinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tbltemalinje TO innsiden;


--
-- Name: tblundersokelse; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblundersokelse FROM PUBLIC;
REVOKE ALL ON TABLE tblundersokelse FROM postgres;
GRANT ALL ON TABLE tblundersokelse TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblundersokelse TO innsiden;


--
-- Name: tblundersokelse_undersokelseid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblundersokelse_undersokelseid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblundersokelse_undersokelseid_seq FROM postgres;
GRANT ALL ON TABLE tblundersokelse_undersokelseid_seq TO postgres;
GRANT SELECT ON TABLE tblundersokelse_undersokelseid_seq TO utsiden;
GRANT ALL ON TABLE tblundersokelse_undersokelseid_seq TO innsiden;


--
-- Name: tblundersokelseseier; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblundersokelseseier FROM PUBLIC;
REVOKE ALL ON TABLE tblundersokelseseier FROM postgres;
GRANT ALL ON TABLE tblundersokelseseier TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblundersokelseseier TO innsiden;


--
-- Name: tblundersokelseseier_undersokelseseierid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblundersokelseseier_undersokelseseierid_seq FROM PUBLIC;
REVOKE ALL ON TABLE tblundersokelseseier_undersokelseseierid_seq FROM postgres;
GRANT ALL ON TABLE tblundersokelseseier_undersokelseseierid_seq TO postgres;
GRANT SELECT ON TABLE tblundersokelseseier_undersokelseseierid_seq TO utsiden;
GRANT ALL ON TABLE tblundersokelseseier_undersokelseseierid_seq TO innsiden;


--
-- PostgreSQL database dump complete
--


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

-- Function: updatesporsmalorder(text)

-- DROP FUNCTION updatesporsmalorder(text);

CREATE OR REPLACE FUNCTION updatesporsmalorder(txt text)
  RETURNS SETOF tblskjemalinje AS
$BODY$
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
	
	

$BODY$
  LANGUAGE plpgsql VOLATILE;
ALTER FUNCTION updatesporsmalorder(text) OWNER TO innsiden;

-- Function: updatesvarrekkefolge(text)

-- DROP FUNCTION updatesvarrekkefolge(text);

CREATE OR REPLACE FUNCTION updatesvarrekkefolge(txt text)
  RETURNS SETOF tblsvarlinje AS
$BODY$
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
	
	

$BODY$
  LANGUAGE plpgsql VOLATILE;
ALTER FUNCTION updatesvarrekkefolge(text) OWNER TO innsiden;

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
-- Name: tbleier; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbleier (
    eierid serial NOT NULL,
    eiernavn text,
    eiersted text
);


ALTER TABLE public.tbleier OWNER TO postgres;

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
-- Name: tblinstitusjonstype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblinstitusjonstype (
    institusjonstypeid serial NOT NULL,
    institusjonstype text
);


ALTER TABLE public.tblinstitusjonstype OWNER TO postgres;

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
-- Name: tblnokkelord; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblnokkelord (
    informantid serial NOT NULL,
    nokkelord text
);


ALTER TABLE public.tblnokkelord OWNER TO postgres;

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
-- Name: tblskjemakonto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblskjemakonto (
    kontoid serial NOT NULL,
    kontonavn text,
    opprettetdato timestamp without time zone
);


ALTER TABLE public.tblskjemakonto OWNER TO postgres;

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
-- Name: tblsporsmalinje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblsporsmalinje (
    sporsmalid integer NOT NULL,
    temaid integer NOT NULL
);


ALTER TABLE public.tblsporsmalinje OWNER TO postgres;

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
-- Name: tblundersokelseseier; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblundersokelseseier (
    undersokelseseierid serial NOT NULL,
    eiernavn text
);


ALTER TABLE public.tblundersokelseseier OWNER TO postgres;

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
-- Name: tblsvarlinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblsvarlinje FROM PUBLIC;
REVOKE ALL ON TABLE tblsvarlinje FROM postgres;
GRANT ALL ON TABLE tblsvarlinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblsvarlinje TO innsiden;


--
-- Name: tblskjemalinje; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblskjemalinje FROM PUBLIC;
REVOKE ALL ON TABLE tblskjemalinje FROM postgres;
GRANT ALL ON TABLE tblskjemalinje TO postgres;
GRANT INSERT,SELECT,UPDATE,DELETE,REFERENCES,TRIGGER ON TABLE tblskjemalinje TO innsiden;


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


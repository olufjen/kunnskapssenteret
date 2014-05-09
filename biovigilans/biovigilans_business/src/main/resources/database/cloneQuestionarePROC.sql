/*
 * Clone a questionare with all related data, based on a given sporreskjemaid
 *
 * Indeks and nokkelord not supported yet due to flaky spec...
 */

CREATE OR REPLACE FUNCTION cloneQuestionare(sourceQuestionareId INTEGER)
  RETURNS integer AS
$BODY$
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
	
	

$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION cloneQuestionare(sourceQuestionareId integer) OWNER TO postgres;
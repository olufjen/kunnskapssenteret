--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: tblinputtype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tblinputtype (
    inputtypeid integer NOT NULL,
    feltbeskrivelse text
);


ALTER TABLE public.tblinputtype OWNER TO postgres;

--
-- Name: tblinputtype_inputtypeid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tblinputtype_inputtypeid_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.tblinputtype_inputtypeid_seq OWNER TO postgres;

--
-- Name: tblinputtype_inputtypeid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tblinputtype_inputtypeid_seq OWNED BY tblinputtype.inputtypeid;


--
-- Name: tblinputtype_inputtypeid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tblinputtype_inputtypeid_seq', 5, true);


--
-- Name: inputtypeid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE tblinputtype ALTER COLUMN inputtypeid SET DEFAULT nextval('tblinputtype_inputtypeid_seq'::regclass);


--
-- Data for Name: tblinputtype; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tblinputtype (inputtypeid, feltbeskrivelse) VALUES (1, 'Radioboks ordinalskala');
INSERT INTO tblinputtype (inputtypeid, feltbeskrivelse) VALUES (2, 'Radioboks svaralternativ');
INSERT INTO tblinputtype (inputtypeid, feltbeskrivelse) VALUES (3, 'Flersvaralternativ');
INSERT INTO tblinputtype (inputtypeid, feltbeskrivelse) VALUES (4, 'Inntastingsfelt');
INSERT INTO tblinputtype (inputtypeid, feltbeskrivelse) VALUES (5, 'Tekstboks');


--
-- Name: tblinputtype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tblinputtype
    ADD CONSTRAINT tblinputtype_pkey PRIMARY KEY (inputtypeid);


--
-- Name: tblinputtype; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tblinputtype FROM PUBLIC;
REVOKE ALL ON TABLE tblinputtype FROM postgres;
GRANT ALL ON TABLE tblinputtype TO postgres;
GRANT ALL ON TABLE tblinputtype TO innsiden;


--
-- Name: tblinputtype_inputtypeid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE tblinputtype_inputtypeid_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE tblinputtype_inputtypeid_seq FROM postgres;
GRANT ALL ON SEQUENCE tblinputtype_inputtypeid_seq TO postgres;
GRANT SELECT ON SEQUENCE tblinputtype_inputtypeid_seq TO utsiden;
GRANT ALL ON SEQUENCE tblinputtype_inputtypeid_seq TO innsiden;


--
-- PostgreSQL database dump complete
--


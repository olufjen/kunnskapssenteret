--
-- Init-script for database helsebiblioteket-administration
--

SET client_encoding = 'UTF8';
SET check_function_bodies = false;
SET client_min_messages = warning;

COMMENT ON SCHEMA public IS 'Standard public schema';
SET search_path = public, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;


-- Create tables

CREATE TABLE hb_user (
    id serial NOT NULL,
    hb_username text,
    password text,
    hb_user_role_id integer
);
ALTER TABLE public.hb_user OWNER TO postgres;

CREATE TABLE hb_user_role (
    id serial NOT NULL,
    name text,
    description text
);
ALTER TABLE public.hb_user_role OWNER TO postgres;


-- define primary and foreign keys

ALTER TABLE ONLY hb_user
    ADD CONSTRAINT hb_user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY hb_user_role
    ADD CONSTRAINT hb_user_role_pkey PRIMARY KEY (id);
    
ALTER TABLE ONLY hb_user
    ADD CONSTRAINT fk_hb_user_role_id FOREIGN KEY (hb_user_role_id) REFERENCES hb_user_role(id);


-- grants for tables

REVOKE ALL ON TABLE hb_user FROM PUBLIC;
REVOKE ALL ON TABLE hb_user FROM postgres;
GRANT ALL ON TABLE hb_user TO postgres;
GRANT ALL ON TABLE hb_user TO helsebiblioteket;

REVOKE ALL ON TABLE hb_user_role FROM PUBLIC;
REVOKE ALL ON TABLE hb_user_role FROM postgres;
GRANT ALL ON TABLE hb_user_role TO postgres;
GRANT ALL ON TABLE hb_user_role TO helsebiblioteket;

-- grants for sequences
-- Short info on sequences in general:
-- sequences are auto-generated when a table field of type "serial" is created
-- sequences automatically get a name assigned by the following rule: "serialname"+_seq
-- sequences are referred to ad tables
-- )

REVOKE ALL ON TABLE hb_user_id_seq FROM PUBLIC;
REVOKE ALL ON TABLE hb_user_id_seq FROM postgres;
GRANT ALL ON TABLE hb_user_id_seq TO postgres;
GRANT ALL ON TABLE hb_user_id_seq TO helsebiblioteket;

REVOKE ALL ON TABLE hb_user_role_id_seq FROM PUBLIC;
REVOKE ALL ON TABLE hb_user_role_id_seq FROM postgres;
GRANT ALL ON TABLE hb_user_role_id_seq TO postgres;
GRANT ALL ON TABLE hb_user_role_id_seq TO helsebiblioteket;
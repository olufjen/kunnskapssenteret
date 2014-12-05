-- ********************************************************************************
-- Generated by:   Select Database Schema File Generator                           
-- Model:          \\Enabler\srv-win-utv-02\Prosjekter\Meldesystem EMOK\0          
-- Filename:       Z:\jobb\sandbox\meldeordningdb\addresser.sql                    
-- Database:       ORACLE                                                          
-- Mapping:        Default                                                         
-- Date:           20 February 2012                                                
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



CREATE TABLE Requisition
(
    Requistionid         serial              NOT NULL,
    LastChanged          timestamp without time zone            NULL,
    Authorizationid      INTEGER              NOT NULL,
    PRIMARY KEY ( Requistionid )
);

ALTER TABLE public.Requisition OWNER TO postgres;

CREATE TABLE Department
(
    Departmentid         serial              NOT NULL,
    Organisationid       INTEGER              NOT NULL,
    PRIMARY KEY ( Departmentid )
);

ALTER TABLE public.Department OWNER TO postgres;

CREATE TABLE Period
(
    periodid             serial              NOT NULL,
    periodFrom                 timestamp without time zone            NOT NULL,
    periodTo                   timestamp without time zone            NOT NULL,
    Communicationpartyid INTEGER              NOT NULL,
    Authorizationid      INTEGER              NOT NULL,
    Spesialityid         INTEGER              NOT NULL,
    Requistionid         INTEGER              NOT NULL,
    Hprinformationid     INTEGER              NOT NULL,
    PRIMARY KEY ( periodid )
);

ALTER TABLE public.Period OWNER TO postgres;

CREATE TABLE Organization
(
    OrganizationNumber   INTEGER             NOT NULL,
    Organisationid       serial              NOT NULL,
    Communicationpartyid INTEGER              NOT NULL,
    PRIMARY KEY ( Organisationid )
);

ALTER TABLE public.Organization OWNER TO postgres;

CREATE TABLE Person
(
    Personid             serial              NOT NULL,
    Organisationpersonid INTEGER              NOT NULL,
    BirthDate            timestamp without time zone            NOT NULL,
    FirstName            TEXT                NOT NULL,
    LastChanged          timestamp without time zone            NOT NULL,
    LastName             TEXT                NOT NULL,
    MiddleName           TEXT                NOT NULL,
    PRIMARY KEY ( Personid )
);

ALTER TABLE public.Person OWNER TO postgres;

CREATE TABLE HPRInformation
(
    Hprinformationid     serial              NOT NULL,
    HPRNo                TEXT                NOT NULL,
    Personid             INTEGER              NOT NULL,
    LastChanged          timestamp without time zone            NULL,
    PRIMARY KEY ( Hprinformationid )
);

ALTER TABLE public.HPRInformation OWNER TO postgres;

CREATE TABLE Speciality
(
    Spesialityid         serial              NOT NULL,
    LastChanged          timestamp without time zone            NULL,
    Authorizationid      INTEGER              NOT NULL,
    PRIMARY KEY ( Spesialityid )
);

ALTER TABLE public.Speciality OWNER TO postgres;

CREATE TABLE Organisationpersonline
(
    Organisationid       INTEGER              NOT NULL,
    Organisationpersonid INTEGER              NOT NULL
);

ALTER TABLE public.Organisationpersonline OWNER TO postgres;

CREATE TABLE Departmentline
(
    Departmentid         INTEGER              NOT NULL,
    Organisationpersonid INTEGER              NOT NULL
);

ALTER TABLE public.Departmentline OWNER TO postgres;

CREATE TABLE Code
(
    codeid               serial              NOT NULL,
    CodeText             TEXT                NOT NULL,
    CodeValue            TEXT                NOT NULL,
    OID                  INTEGER             NOT NULL,
    SimpleType           TEXT                NOT NULL,
    Organisationid       INTEGER              NOT NULL,
    Communicationpartyid INTEGER              NOT NULL,
    Departmentid         INTEGER              NOT NULL,
    Organisationpersonid INTEGER              NOT NULL,
    Authorizationid      INTEGER              NOT NULL,
    Spesialityid         INTEGER              NOT NULL,
    Requistionid         INTEGER              NOT NULL,
    PRIMARY KEY ( codeid )
);

ALTER TABLE public.Code OWNER TO postgres;

CREATE TABLE CitizenId
(
    CitizenIdType        TEXT                NOT NULL,
    Id                   TEXT                NOT NULL,
    Sex                  TEXT                NOT NULL,
    Personid             INTEGER              NOT NULL,
    citizenid            serial              NOT NULL,
    PRIMARY KEY ( citizenid )
);

ALTER TABLE public.CitizenId OWNER TO postgres;

CREATE TABLE CommunicationParty
(
    Communicationpartyid serial              NOT NULL,
    Active               SMALLINT             NOT NULL,
    DisplayName          TEXT                NOT NULL,
    HerId                INTEGER             NOT NULL,
    LastChanged          timestamp without time zone            NOT NULL,
    Name                 TEXT                NOT NULL,
    ParentHerId          INTEGER             NOT NULL,
    ParentName           TEXT                NOT NULL,
    ParentOrganizationNumber INTEGER         NOT NULL,
    ServerTime           timestamp without time zone            NOT NULL,
    Type                 TEXT                NOT NULL,
    PRIMARY KEY ( Communicationpartyid )
);

ALTER TABLE public.CommunicationParty OWNER TO postgres;

CREATE TABLE PhysicalAddress
(
    Physicaladdressid    serial              NOT NULL,
    City                 TEXT                NOT NULL,
    Description          TEXT                NOT NULL,
    Inherited            smallint             NOT NULL,
    LastChanged          timestamp without time zone            NOT NULL,
    PostalCode           INTEGER             NOT NULL,
    Postbox              TEXT                NOT NULL,
    StreetAddress        TEXT                NOT NULL,
    TypeCodeValue        TEXT                NOT NULL,
    Communicationpartyid INTEGER              NOT NULL,
    PRIMARY KEY ( Physicaladdressid )
);

ALTER TABLE public.PhysicalAddress OWNER TO postgres;

CREATE TABLE OrganizationPerson
(
    Organisationpersonid serial              NOT NULL,
    Title                TEXT                NOT NULL,
    Communicationpartyid INTEGER              NOT NULL,
    PRIMARY KEY ( Organisationpersonid )
);

ALTER TABLE public.OrganizationPerson OWNER TO postgres;

CREATE TABLE nhnAuthorization
(
    Authorizationid      serial              NOT NULL,
    Hprinformationid     INTEGER              NOT NULL,
    LastChanged          timestamp without time zone            NULL,
    PRIMARY KEY ( Authorizationid )
);

ALTER TABLE public.nhnAuthorization OWNER TO postgres;

CREATE TABLE ElectronicAddress
(
    Electronicaddressid  serial              NOT NULL,
    Address              TEXT                NOT NULL,
    Inherited            smallint             NOT NULL,
    LastChanged          timestamp without time zone            NOT NULL,
    TypeCodeValue        TEXT                NOT NULL,
    Communicationpartyid INTEGER              NOT NULL,
    PRIMARY KEY ( Electronicaddressid )
);

ALTER TABLE public.ElectronicAddress OWNER TO postgres;

CREATE TABLE Service
(
    LocationDescription  TEXT                NOT NULL,
    Organisationid       INTEGER              NOT NULL,
    serviceid            serial              NOT NULL,
    PRIMARY KEY ( serviceid )
);

ALTER TABLE public.Service OWNER TO postgres;

-- ALTER TABLE ONLY tblbackground
--    ADD CONSTRAINT tblbackground_pkey PRIMARY KEY (baselineid);




ALTER TABLE Requisition
    ADD CONSTRAINT authorisationrequistion FOREIGN KEY ( Authorizationid ) REFERENCES nhnAuthorization ( Authorizationid );

ALTER TABLE Department
    ADD CONSTRAINT organisationdepartment FOREIGN KEY ( Organisationid ) REFERENCES Organization ( Organisationid );

ALTER TABLE Period
    ADD CONSTRAINT HPRInformationperiod FOREIGN KEY ( Hprinformationid ) REFERENCES HPRInformation ( Hprinformationid );

ALTER TABLE Period
    ADD CONSTRAINT authorisationperiod FOREIGN KEY ( Authorizationid ) REFERENCES nhnAuthorization ( Authorizationid );

ALTER TABLE Period
    ADD CONSTRAINT communicationpartyperiod FOREIGN KEY ( Communicationpartyid ) REFERENCES CommunicationParty ( Communicationpartyid );

ALTER TABLE Period
    ADD CONSTRAINT requistionperiod FOREIGN KEY ( Requistionid ) REFERENCES Requisition ( Requistionid );

ALTER TABLE Period
    ADD CONSTRAINT spesialityperiod FOREIGN KEY ( Spesialityid ) REFERENCES Speciality ( Spesialityid );

ALTER TABLE Organization
    ADD CONSTRAINT Relationship12 FOREIGN KEY ( Communicationpartyid ) REFERENCES CommunicationParty ( Communicationpartyid );

ALTER TABLE Person
    ADD CONSTRAINT orgpersonperson FOREIGN KEY ( Organisationpersonid ) REFERENCES OrganizationPerson ( Organisationpersonid );

ALTER TABLE HPRInformation
    ADD CONSTRAINT personhpr FOREIGN KEY ( Personid ) REFERENCES Person ( Personid );

ALTER TABLE Speciality
    ADD CONSTRAINT authspes FOREIGN KEY ( Authorizationid ) REFERENCES nhnAuthorization ( Authorizationid );

ALTER TABLE Organisationpersonline
    ADD CONSTRAINT organisationpersonorganisation FOREIGN KEY ( Organisationpersonid ) REFERENCES OrganizationPerson ( Organisationpersonid );

ALTER TABLE Organisationpersonline
    ADD CONSTRAINT organisationtoperson FOREIGN KEY ( Organisationid ) REFERENCES Organization ( Organisationid );

ALTER TABLE Departmentline
    ADD CONSTRAINT departmentorganisasjonperson FOREIGN KEY ( Departmentid ) REFERENCES Department ( Departmentid );

ALTER TABLE Departmentline
    ADD CONSTRAINT orgainsationpersondepartement FOREIGN KEY ( Organisationpersonid ) REFERENCES OrganizationPerson ( Organisationpersonid );

ALTER TABLE Code
    ADD CONSTRAINT authorisationpfofession FOREIGN KEY ( Authorizationid ) REFERENCES nhnAuthorization ( Authorizationid );

ALTER TABLE Code
    ADD CONSTRAINT communicationpartycode FOREIGN KEY ( Communicationpartyid ) REFERENCES CommunicationParty ( Communicationpartyid );

ALTER TABLE Code
    ADD CONSTRAINT departementcode FOREIGN KEY ( Departmentid ) REFERENCES Department ( Departmentid );

ALTER TABLE Code
    ADD CONSTRAINT organisationcode FOREIGN KEY ( Organisationid ) REFERENCES Organization ( Organisationid );

ALTER TABLE Code
    ADD CONSTRAINT organisationpersoncode FOREIGN KEY ( Organisationpersonid ) REFERENCES OrganizationPerson ( Organisationpersonid );

ALTER TABLE Code
    ADD CONSTRAINT requistiontype FOREIGN KEY ( Requistionid ) REFERENCES Requisition ( Requistionid );

ALTER TABLE Code
    ADD CONSTRAINT spesialitytype FOREIGN KEY ( Spesialityid ) REFERENCES Speciality ( Spesialityid );

ALTER TABLE CitizenId
    ADD CONSTRAINT personcitizen FOREIGN KEY ( Personid ) REFERENCES Person ( Personid );

ALTER TABLE PhysicalAddress
    ADD CONSTRAINT communicationpartyphysicaladdress FOREIGN KEY ( Communicationpartyid ) REFERENCES CommunicationParty ( Communicationpartyid );

ALTER TABLE OrganizationPerson
    ADD CONSTRAINT Relationship11 FOREIGN KEY ( Communicationpartyid ) REFERENCES CommunicationParty ( Communicationpartyid );

ALTER TABLE nhnAuthorization
    ADD CONSTRAINT HPRInformationauthorisation FOREIGN KEY ( Hprinformationid ) REFERENCES HPRInformation ( Hprinformationid );

ALTER TABLE ElectronicAddress
    ADD CONSTRAINT communicationpartyelectronicaddress FOREIGN KEY ( Communicationpartyid ) REFERENCES CommunicationParty ( Communicationpartyid );

ALTER TABLE Service
    ADD CONSTRAINT organisationservice FOREIGN KEY ( Organisationid ) REFERENCES Organization ( Organisationid );

-- ********************************************************************************
-- Generated by:   Select Database Schema File Generator                           
-- Model:          \\Enabler\srv-win-utv-02\Prosjekter\helsebibliotek admin\0      
-- Filename:       C:\databasemodel_oracle2.sql                                    
-- Database:       ORACLE                                                          
-- Mapping:        Default                                                         
-- Date:           04 December 2008                                                
-- ********************************************************************************
-- Modified by Leif Torger Grøndahl, nokc.no:
-- added serials as primary keys
-- added grants for ownership for all tables

CREATE TABLE tbl_ip_address
(
    ip_address_from      VARCHAR             NOT NULL,
    ip_address_to        VARCHAR             NULL,
    ip_address_id        SERIAL             NOT NULL,
    org_unit_id          INTEGER             NULL,
    last_changed         TIMESTAMP           NULL,
    PRIMARY KEY ( ip_address_id )
);

--ALTER TABLE tbl_ip_address
--  ADD CONSTRAINT tbl_ip_address_unique UNIQUE(ip_address_from, ip_address_to, org_unit_id);


CREATE TABLE tbl_contact_information
(
    postal_address       VARCHAR             NULL,
    postal_code          VARCHAR             NULL,
    telephone_number     VARCHAR             NULL,
    postal_location      VARCHAR             NULL,
    email                VARCHAR             NULL,
    contact_information_id SERIAL           NOT NULL,
    last_changed         TIMESTAMP           NULL,
    PRIMARY KEY ( contact_information_id )
);



CREATE TABLE tbl_profile
(
    receive_newsletter   BOOLEAN                 NULL,
    participate_survey   BOOLEAN                 NULL,
    profile_id           SERIAL             NOT NULL,
    last_changed         TIMESTAMP           NULL,
    PRIMARY KEY ( profile_id )
);


CREATE TABLE tbl_org_unit
(
    org_unit_id          SERIAL             NOT NULL,
    org_unit_parent_id   INTEGER             NULL,
    descr                VARCHAR             NULL,
    org_type_id          INTEGER             NOT NULL,
    contact_information_id INTEGER           NULL,
    person_id            INTEGER             NULL,
    last_changed         TIMESTAMP           NULL,
    PRIMARY KEY ( org_unit_id )
);


CREATE TABLE tbl_org_unit_name
(
    org_unit_name_id     SERIAL             NOT NULL,
    language_code	     VARCHAR             NULL,
    name                 VARCHAR             NULL,
    org_unit_id          INTEGER             NOT NULL,
    category	 		 VARCHAR			 NOT NULL,
    last_changed         TIMESTAMP           NULL,
    PRIMARY KEY ( org_unit_name_id )
);

ALTER TABLE tbl_org_unit_name
	ADD CHECK (category in ('SHORT', 'NORMAL'));

ALTER TABLE tbl_org_unit_name
	ADD CHECK (language_code in ('en', 'no'));

ALTER TABLE tbl_org_unit_name
  ADD CONSTRAINT tbl_org_unit_name_unique UNIQUE (language_code, name, org_unit_id, category);


CREATE TABLE tbl_database_patches
(
    database_version     VARCHAR             NULL      UNIQUE,
    patch_name           VARCHAR             NULL      UNIQUE,
    patch_date           TIMESTAMP           NULL,
    database_patch_id    SERIAL             NOT NULL,
    PRIMARY KEY ( database_patch_id )
);

ALTER TABLE tbl_database_patches
  ADD CONSTRAINT tbl_database_patches_unique UNIQUE (database_version, patch_name);


CREATE TABLE tbl_org_type_reg
(
    org_type_id          SERIAL             NOT NULL,
    descr                VARCHAR             NULL,
    name                 VARCHAR             NOT NULL,
    key                  VARCHAR             NOT NULL  UNIQUE,
    PRIMARY KEY ( org_type_id )
);



CREATE TABLE tbl_access
(
    access_type_id       INTEGER             NOT NULL,
    user_id              INTEGER             NULL,
    user_role_id         INTEGER             NULL,
    valid_from           TIMESTAMP           NULL,
    valid_to             TIMESTAMP           NULL,
    access_id            SERIAL              NOT NULL,
    resource_id          INTEGER             NULL,
    org_type_id          INTEGER             NULL,
    provided_by_org_unit INTEGER             NULL,
    org_unit_id          INTEGER             NULL,
    last_changed         TIMESTAMP           NULL,
    org_unit_as_resource_id INTEGER          NULL,
    PRIMARY KEY ( access_id )
);



CREATE TABLE tbl_action
(
    action_id            SERIAL             NOT NULL,
    action_performed     TIMESTAMP           NOT NULL,
    user_id              INTEGER             NULL,
    resource_id          INTEGER             NOT NULL,
    org_unit_id          INTEGER             NULL,
    access_type_id       INTEGER             NOT NULL,
    PRIMARY KEY ( action_id )
);

ALTER TABLE tbl_action
  ADD CONSTRAINT tbl_action_unique UNIQUE (action_performed, user_id, resource_id, org_unit_id, access_type_id);



CREATE TABLE tbl_system_reg
(
    name                 VARCHAR             NULL,
    descr                VARCHAR             NULL,
    key                  VARCHAR             NOT NULL  UNIQUE,
    system_id            SERIAL             NOT NULL,
    PRIMARY KEY ( system_id )
);



CREATE TABLE tbl_supplier_source
(
    supplier_source_name VARCHAR             NULL,
    url                  VARCHAR             NULL,
    supplier_source_id   SERIAL             NOT NULL,
    last_changed         TIMESTAMP           NULL,
    PRIMARY KEY ( supplier_source_id )
);



CREATE TABLE tbl_resource
(
    resource_id          SERIAL             NOT NULL,
    supplier_source_id   INTEGER             NULL,
    resource_type_id     INTEGER             NOT NULL,
    org_unit_id          INTEGER             NULL,
    last_changed         TIMESTAMP           NULL,
    PRIMARY KEY ( resource_id )
);

ALTER TABLE tbl_resource
  ADD CONSTRAINT tbl_resource_unique UNIQUE (supplier_source_id, resource_type_id);


CREATE TABLE tbl_user
(
    user_id              SERIAL             NOT NULL,
    username             VARCHAR             NOT NULL  UNIQUE,
    password             VARCHAR             NOT NULL,
    org_unit_id          INTEGER             NULL,
    person_id            INTEGER             NULL,
    last_changed         TIMESTAMP           NULL,
    PRIMARY KEY ( user_id )
);



CREATE TABLE tbl_user_role_reg
(
    name                 VARCHAR             NOT NULL,
    descr                VARCHAR             NULL,
    user_role_id         SERIAL             NOT NULL,
    system_id            INTEGER             NOT NULL,
    key                  VARCHAR             NOT NULL,
    PRIMARY KEY ( user_role_id )
);

ALTER TABLE tbl_user_role_reg
  ADD CONSTRAINT tbl_user_role_reg_unique UNIQUE (key, system_id);



CREATE TABLE tbl_user_role
(
    user_role_id         INTEGER             NOT NULL,
    user_id              INTEGER             NOT NULL,
    last_changed         TIMESTAMP           NULL,
    PRIMARY KEY ( user_role_id, user_id )
);



CREATE TABLE tbl_position_type_reg
(
    position_type_id     SERIAL             NOT NULL,
    descr                VARCHAR             NULL,
    name                 VARCHAR             NULL,
    key                  VARCHAR             NOT NULL,
    org_type_id          INTEGER             NULL,
    PRIMARY KEY ( position_type_id )
);

ALTER TABLE tbl_position_type_reg
  ADD CONSTRAINT tbl_position_type_reg_unique UNIQUE (key, org_type_id);


CREATE TABLE tbl_access_type_reg
(
    access_type_id       SERIAL             NOT NULL,
    descr                VARCHAR             NULL,
    name                 VARCHAR             NULL,
    key                  VARCHAR             NOT NULL,
    category             VARCHAR             NOT NULL,
    PRIMARY KEY ( access_type_id )
);

ALTER TABLE tbl_access_type_reg
  ADD CONSTRAINT tbl_access_type_reg_unique UNIQUE (key, category);

ALTER TABLE tbl_access_type_reg
  ADD CHECK (category in ('GRANT', 'DENY'));



CREATE TABLE tbl_resource_type_reg
(
    resource_type_id     SERIAL             NOT NULL,
    descr                VARCHAR             NULL,
    name                 VARCHAR             NULL,
    key                  VARCHAR             NOT NULL  UNIQUE,
    PRIMARY KEY ( resource_type_id )
);



CREATE TABLE tbl_person
(
    person_id            SERIAL             NOT NULL,
    first_name           VARCHAR             NULL,
    last_name            VARCHAR             NULL,
    student_number       VARCHAR             NULL,
    hpr_number           VARCHAR             NULL,
    contact_information_id INTEGER           NULL,
    profile_id           INTEGER             NULL,
    position_type_id     INTEGER             NULL,
    employer			 VARCHAR			 NULL,
    last_changed         TIMESTAMP           NULL,
    PRIMARY KEY ( person_id )
);



ALTER TABLE tbl_ip_address
    ADD CONSTRAINT ip_addresses_for_organization FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_org_unit
    ADD CONSTRAINT contact_information_for_organization FOREIGN KEY ( contact_information_id ) REFERENCES tbl_contact_information ( contact_information_id );

ALTER TABLE tbl_org_unit
    ADD CONSTRAINT contact_person_for_organization FOREIGN KEY ( person_id ) REFERENCES tbl_person ( person_id );

ALTER TABLE tbl_org_unit
    ADD CONSTRAINT organizations_for_org_type FOREIGN KEY ( org_type_id ) REFERENCES tbl_org_type_reg ( org_type_id );

ALTER TABLE tbl_org_unit
    ADD CONSTRAINT parent_organization FOREIGN KEY ( org_unit_parent_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_org_unit_name
    ADD CONSTRAINT names_for_org FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_for_access_type FOREIGN KEY ( access_type_id ) REFERENCES tbl_access_type_reg ( access_type_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_for_org FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_for_orgtype FOREIGN KEY ( org_type_id ) REFERENCES tbl_org_type_reg ( org_type_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_for_role FOREIGN KEY ( user_role_id ) REFERENCES tbl_user_role_reg ( user_role_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_for_user FOREIGN KEY ( user_id ) REFERENCES tbl_user ( user_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_to_org FOREIGN KEY ( org_unit_as_resource_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_to_resource FOREIGN KEY ( resource_id ) REFERENCES tbl_resource ( resource_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT provided_by FOREIGN KEY ( provided_by_org_unit ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_action
    ADD CONSTRAINT access_type_for_action FOREIGN KEY ( access_type_id ) REFERENCES tbl_access_type_reg ( access_type_id );

ALTER TABLE tbl_action
    ADD CONSTRAINT actions_by_org FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_action
    ADD CONSTRAINT actions_by_user FOREIGN KEY ( user_id ) REFERENCES tbl_user ( user_id );

ALTER TABLE tbl_action
    ADD CONSTRAINT resource_action FOREIGN KEY ( resource_id ) REFERENCES tbl_resource ( resource_id );

ALTER TABLE tbl_resource
    ADD CONSTRAINT resource_of_type_supplier_source FOREIGN KEY ( supplier_source_id ) REFERENCES tbl_supplier_source ( supplier_source_id );

ALTER TABLE tbl_resource
    ADD CONSTRAINT resources_offered_by_org FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_resource
    ADD CONSTRAINT valid_resources FOREIGN KEY ( resource_type_id ) REFERENCES tbl_resource_type_reg ( resource_type_id );

ALTER TABLE tbl_user
    ADD CONSTRAINT user_person FOREIGN KEY ( person_id ) REFERENCES tbl_person ( person_id );

ALTER TABLE tbl_user
    ADD CONSTRAINT users_for_organization FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_user_role_reg
    ADD CONSTRAINT roles_for_system FOREIGN KEY ( system_id ) REFERENCES tbl_system_reg ( system_id );

ALTER TABLE tbl_user_role
    ADD CONSTRAINT roles_for_user FOREIGN KEY ( user_id ) REFERENCES tbl_user ( user_id );

ALTER TABLE tbl_user_role
    ADD CONSTRAINT users_for_role FOREIGN KEY ( user_role_id ) REFERENCES tbl_user_role_reg ( user_role_id );

ALTER TABLE tbl_position_type_reg
    ADD CONSTRAINT positions_for_orgtype FOREIGN KEY ( org_type_id ) REFERENCES tbl_org_type_reg ( org_type_id );

ALTER TABLE tbl_person
    ADD CONSTRAINT Profile_for_person FOREIGN KEY ( profile_id ) REFERENCES tbl_profile ( profile_id );

ALTER TABLE tbl_person
    ADD CONSTRAINT contact_information_for_person FOREIGN KEY ( contact_information_id ) REFERENCES tbl_contact_information ( contact_information_id );

ALTER TABLE tbl_person
    ADD CONSTRAINT positions_for_person FOREIGN KEY ( position_type_id ) REFERENCES tbl_position_type_reg ( position_type_id );



    
ALTER TABLE tbl_access 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_access_type_reg 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_action 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_contact_information 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_database_patches 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_ip_address 
	OWNER TO helsebiblioteket;
ALTER TABLE tbl_org_type_reg 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_org_unit 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_org_unit_name 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_person 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_profile 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_resource 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_resource_type_reg 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_supplier_source 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_system_reg 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_user 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_user_role 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_user_role_reg 
	OWNER TO helsebiblioteket;
	
ALTER TABLE tbl_position_type_reg 
	OWNER TO helsebiblioteket;
-- ********************************************************************************
-- Generated by:   Select Database Schema File Generator                           
-- Model:          \\Enabler\srv-win-utv-02\Prosjekter\helsebibliotek admin\0      
-- Filename:       C:\helsebiblioteket_admin_oracle.sql                            
-- Database:       ORACLE                                                          
-- Mapping:        Default                                                         
-- Date:           12 November 2008                                                
-- ********************************************************************************
-- Modified by Leif Torger Gr�ndahl, nokc.no:
-- added serials as primary keys
-- added grants for ownership for all tables

CREATE TABLE tbl_ip_address
(
    ip_address_from      VARCHAR             NULL,
    ip_address_to        VARCHAR             NULL,
    ip_address_id        INTEGER             NOT NULL,
    org_unit_id          SERIAL             NOT NULL,
    PRIMARY KEY ( ip_address_id )
);



CREATE TABLE tbl_contact_information
(
    postal_address       VARCHAR             NULL,
    postal_code          VARCHAR             NULL,
    telephone_number     VARCHAR             NULL,
    postal_location      VARCHAR             NULL,
    org_unit_id          INTEGER             NOT NULL,
    person_id            INTEGER             NOT NULL  UNIQUE,
    email                VARCHAR             NULL,
    contact_information_id SERIAL           NOT NULL,
    PRIMARY KEY ( contact_information_id )
);



CREATE TABLE tbl_profile
(
    receive_newsletter   BIT                 NULL,
    participate_survey   BIT                 NULL,
    person_id            INTEGER             NOT NULL  UNIQUE
);



CREATE TABLE tbl_org_unit
(
    org_unit_id          SERIAL             NOT NULL,
    org_unit_parent_id   INTEGER             NOT NULL,
    name                 VARCHAR             NULL,
    descr                VARCHAR             NULL,
    name_short           VARCHAR             NULL,
    org_type_id          INTEGER             NOT NULL  UNIQUE,
    PRIMARY KEY ( org_unit_id )
);



CREATE TABLE tbl_org_unit_contract
(
    org_unit_customer_id INTEGER             NOT NULL,
    org_unit_supplier_id INTEGER             NOT NULL,
    org_unit_contract_id SERIAL             NOT NULL,
    org_type_id          INTEGER             NOT NULL  UNIQUE,
    PRIMARY KEY ( org_unit_contract_id )
);



CREATE TABLE tbl_database_patches
(
    database_version     VARCHAR             NULL,
    patch_name           VARCHAR             NULL,
    patch_date           TIMESTAMP           NULL,
    database_patch_id    SERIAL             NOT NULL,
    PRIMARY KEY ( database_patch_id )
);



CREATE TABLE tbl_org_type_reg
(
    org_type_id          SERIAL             NOT NULL,
    descr                VARCHAR             NULL,
    name                 VARCHAR             NOT NULL  UNIQUE,
    key                  VARCHAR             NULL,
    PRIMARY KEY ( org_type_id )
);



CREATE TABLE tbl_access
(
    access_type_id       INTEGER             NOT NULL,
    user_id              INTEGER             NOT NULL  UNIQUE,
    user_role_id         INTEGER             NOT NULL  UNIQUE,
    valid_from           TIMESTAMP           NULL,
    valid_to             TIMESTAMP           NULL,
    org_unit_contract_id INTEGER             NOT NULL  UNIQUE,
    access_id            SERIAL             NOT NULL,
    resource_id          INTEGER             NOT NULL  UNIQUE,
    PRIMARY KEY ( access_id )
);



CREATE TABLE tbl_user_role_category
(
    user_role_category_id SERIAL            NOT NULL,
    descr                VARCHAR             NULL,
    name                 VARCHAR             NULL,
    key                  VARCHAR             NULL,
    PRIMARY KEY ( user_role_category_id )
);



CREATE TABLE tbl_action
(
    action_id            SERIAL             NOT NULL,
    action_performed     TIMESTAMP           NOT NULL,
    user_id              INTEGER             NOT NULL  UNIQUE,
    access_id            INTEGER             NOT NULL  UNIQUE,
    resource_id          INTEGER             NOT NULL  UNIQUE,
    PRIMARY KEY ( action_id )
);



CREATE TABLE tbl_system_reg
(
    name                 VARCHAR             NOT NULL,
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
    PRIMARY KEY ( supplier_source_id )
);



CREATE TABLE tbl_resource
(
    resource_id          SERIAL             NOT NULL,
    supplier_source_id   INTEGER             NOT NULL  UNIQUE,
    resource_type_id     INTEGER             NOT NULL,
    org_unit_id          INTEGER             NOT NULL,
    PRIMARY KEY ( resource_id )
);



CREATE TABLE tbl_user
(
    user_id              SERIAL             NOT NULL,
    username             VARCHAR             NULL,
    password             VARCHAR             NULL,
    org_unit_id          INTEGER             NOT NULL,
    PRIMARY KEY ( user_id )
);



CREATE TABLE tbl_user_role_reg
(
    name                 VARCHAR             NOT NULL,
    descr                VARCHAR             NULL,
    user_role_id         SERIAL             NOT NULL,
    user_role_category_id INTEGER            NOT NULL,
    system_id            INTEGER             NOT NULL  UNIQUE,
    key                  VARCHAR             NULL,
    PRIMARY KEY ( user_role_id )
);



CREATE TABLE tbl_user_role
(
    user_role_id         INTEGER             NOT NULL,
    user_id              INTEGER             NOT NULL,
    PRIMARY KEY ( user_role_id, user_id )
);



CREATE TABLE tbl_access_type_reg
(
    access_type_id       SERIAL             NOT NULL,
    descr                VARCHAR             NULL,
    name                 VARCHAR             NOT NULL  UNIQUE,
    key                  VARCHAR             NULL,
    PRIMARY KEY ( access_type_id )
);



CREATE TABLE tbl_resource_type_reg
(
    resource_type_id     SERIAL             NOT NULL,
    descr                VARCHAR             NULL,
    name                 VARCHAR             NULL,
    key                  VARCHAR             NULL,
    PRIMARY KEY ( resource_type_id )
);



CREATE TABLE tbl_person
(
    person_id            SERIAL             NOT NULL,
    first_name           VARCHAR             NULL,
    last_name            VARCHAR             NULL,
    student_number       INTEGER             NULL,
    hpr_number           INTEGER             NULL,
    org_unit_id          INTEGER             NOT NULL,
    user_id              INTEGER             NOT NULL  UNIQUE,
    PRIMARY KEY ( person_id )
);



ALTER TABLE tbl_ip_address
    ADD CONSTRAINT ip_addresses_for_organization FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_contact_information
    ADD CONSTRAINT contact_information_for_organization FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_contact_information
    ADD CONSTRAINT contact_information_for_person FOREIGN KEY ( person_id ) REFERENCES tbl_person ( person_id );

ALTER TABLE tbl_profile
    ADD CONSTRAINT profile_for_person FOREIGN KEY ( person_id ) REFERENCES tbl_person ( person_id );

ALTER TABLE tbl_org_unit
    ADD CONSTRAINT organizations_for_org_type FOREIGN KEY ( org_type_id ) REFERENCES tbl_org_type_reg ( org_type_id );

ALTER TABLE tbl_org_unit
    ADD CONSTRAINT parent_organization FOREIGN KEY ( org_unit_parent_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_org_unit_contract
    ADD CONSTRAINT contracts_for_customer FOREIGN KEY ( org_unit_customer_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_org_unit_contract
    ADD CONSTRAINT contracts_for_supplier FOREIGN KEY ( org_unit_supplier_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_org_unit_contract
    ADD CONSTRAINT contracts_for_organization_type FOREIGN KEY ( org_type_id ) REFERENCES tbl_org_type_reg ( org_type_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_for_access_type FOREIGN KEY ( access_type_id ) REFERENCES tbl_access_type_reg ( access_type_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_for_contract FOREIGN KEY ( org_unit_contract_id ) REFERENCES tbl_org_unit_contract ( org_unit_contract_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_for_role FOREIGN KEY ( user_role_id ) REFERENCES tbl_user_role_reg ( user_role_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_for_user FOREIGN KEY ( user_id ) REFERENCES tbl_user ( user_id );

ALTER TABLE tbl_access
    ADD CONSTRAINT access_to_resource FOREIGN KEY ( resource_id ) REFERENCES tbl_resource ( resource_id );

ALTER TABLE tbl_action
    ADD CONSTRAINT access_action FOREIGN KEY ( access_id ) REFERENCES tbl_access ( access_id );

ALTER TABLE tbl_action
    ADD CONSTRAINT actions_by_user FOREIGN KEY ( user_id ) REFERENCES tbl_user ( user_id );

ALTER TABLE tbl_action
    ADD CONSTRAINT resource_action FOREIGN KEY ( resource_id ) REFERENCES tbl_resource ( resource_id );

ALTER TABLE tbl_resource
    ADD CONSTRAINT resource_of_type_supplier_source FOREIGN KEY ( supplier_source_id ) REFERENCES tbl_supplier_source ( supplier_source_id );

ALTER TABLE tbl_resource
    ADD CONSTRAINT resources_offered_by_supplier FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_resource
    ADD CONSTRAINT valid_resources FOREIGN KEY ( resource_type_id ) REFERENCES tbl_resource_type_reg ( resource_type_id );

ALTER TABLE tbl_user
    ADD CONSTRAINT Users_for_organization FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_user_role_reg
    ADD CONSTRAINT roles_for_category FOREIGN KEY ( user_role_category_id ) REFERENCES tbl_user_role_category ( user_role_category_id );

ALTER TABLE tbl_user_role_reg
    ADD CONSTRAINT roles_for_system FOREIGN KEY ( system_id ) REFERENCES tbl_system_reg ( system_id );

ALTER TABLE tbl_user_role
    ADD CONSTRAINT roles_for_user FOREIGN KEY ( user_id ) REFERENCES tbl_user ( user_id );

ALTER TABLE tbl_user_role
    ADD CONSTRAINT users_for_role FOREIGN KEY ( user_role_id ) REFERENCES tbl_user_role_reg ( user_role_id );

ALTER TABLE tbl_person
    ADD CONSTRAINT contact_person_for_organization FOREIGN KEY ( org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

ALTER TABLE tbl_person
    ADD CONSTRAINT user_person FOREIGN KEY ( user_id ) REFERENCES tbl_user ( user_id );


ALTER TABLE tbl_access OWNER TO helsebiblioteket;
ALTER TABLE tbl_access_type_reg OWNER TO helsebiblioteket;
ALTER TABLE tbl_action OWNER TO helsebiblioteket;
ALTER TABLE tbl_contact_information OWNER TO helsebiblioteket;
ALTER TABLE tbl_database_patches OWNER TO helsebiblioteket;
ALTER TABLE tbl_ip_address OWNER TO helsebiblioteket;
ALTER TABLE tbl_org_type_reg OWNER TO helsebiblioteket;
ALTER TABLE tbl_org_unit OWNER TO helsebiblioteket;
ALTER TABLE tbl_org_unit_contract OWNER TO helsebiblioteket;
ALTER TABLE tbl_person OWNER TO helsebiblioteket;
ALTER TABLE tbl_profile OWNER TO helsebiblioteket;
ALTER TABLE tbl_resource OWNER TO helsebiblioteket;
ALTER TABLE tbl_resource_type_reg OWNER TO helsebiblioteket;
ALTER TABLE tbl_supplier_source OWNER TO helsebiblioteket;
ALTER TABLE tbl_system_reg OWNER TO helsebiblioteket;
ALTER TABLE tbl_user OWNER TO helsebiblioteket;
ALTER TABLE tbl_user_role OWNER TO helsebiblioteket;
ALTER TABLE tbl_user_role_category OWNER TO helsebiblioteket;
ALTER TABLE tbl_user_role_reg OWNER TO helsebiblioteket;

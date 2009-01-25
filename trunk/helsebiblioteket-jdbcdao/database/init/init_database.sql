INSERT INTO tbl_system_reg (name, descr, key) VALUES ('Helsebiblioteket - administration', 'Administration of organizations, IP-addresses, users and access', 'helsebiblioteket_admin');

INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Administrator', 'Administrator with access to all functionality', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'administrator');
INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Health personell', 'Authorized health personell with health personnel number', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'health_personnel');
INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Student', 'Student with valid student number', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'student');
INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Health personell other', 'Authorized health personell without health personnel number', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'health_personnel_other');

INSERT INTO tbl_access_type_reg (descr, name, key, category) VALUES ('general access permission', 'general', 'general', 'GRANT');
INSERT INTO tbl_access_type_reg (descr, name, key, category) VALUES ('general denial of access', 'general', 'general', 'DENY');

INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (select nextval('tbl_org_type_reg_org_type_id_seq'), 'Health enterprise', 'health enterprise', 'health_enterprise');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (select nextval('tbl_org_type_reg_org_type_id_seq'), 'Public administration', 'public administration', 'public_administration');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (select nextval('tbl_org_type_reg_org_type_id_seq'), 'Teaching', 'teaching', 'teaching');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (select nextval('tbl_org_type_reg_org_type_id_seq'), 'Others', 'others', 'others');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (select nextval('tbl_org_type_reg_org_type_id_seq'), 'Content supplier', 'content supplier', 'content_supplier');

INSERT INTO tbl_resource_type_reg (descr, name, key) VALUES ('Supplier source', 'supplier source', 'supplier_source');

insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Ambulansearbeider', 'ambulansearbeider', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Apotektekniker', 'apotektekniker', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Audiograf', 'audiograf', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Bioingeniør', 'bioingenior', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Ergoterapeut', 'ergoterapeut', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Helsefagarbeider', 'helsefagarbeider', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Fotterapeut', 'fotterapeut', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Fysioterapeut', 'fysioterapeut', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Helsesekretær', 'helsesekretar', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Hjelpepleier', 'hjelpepleier', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Jordmor', 'jordmor', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Kiropraktor', 'kiropraktor', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Klinisk ernæringsfysiolog', 'klinisk_ernaringsfysiolog', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Lege', 'lege', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Omsorgsarbeider', 'omsorgsarbeider', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Optiker', 'optiker', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Ortopediingeniør', 'ortopediingenior', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Ortoptist', 'ortoptist', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Perfusjonist', 'perfusjonist', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Psykolog', 'psykolog', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Radiograf', 'radiograf', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Sykepleier', 'sykepleier', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Tannhelsesekretær', 'tannhelsesekretar', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Tannlege', 'tannlege', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Tannpleier', 'tannpleier', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Tanntekniker', 'tanntekniker', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Vernepleier', 'vernepleier', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Provisorfarmasøyt', 'provisorfarmasoyt', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Reseptarfarmasøyt', 'reseptarfarmasoyt', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
--select * from tbl_position_type_reg

INSERT INTO tbl_system_reg (name, descr, key) VALUES ('Helsebiblioteket - administration', 'Administration of organizations, IP-addresses, users and access', 'helsebiblioteket_admin');

INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Administrator', 'Administrator with access to all functionality', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'administrator');
INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Health personell', 'Authorized health personell with health personnel number', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'health_personnel');
INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Student', 'Student with valid student number', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'student');
INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Health personnel other', 'Authorized health personell without health personnel number', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'health_personnel_other');

INSERT INTO tbl_access_type_reg (descr, name, key, category) VALUES ('general access permission', 'general', 'general', 'GRANT');
INSERT INTO tbl_access_type_reg (descr, name, key, category) VALUES ('general denial of access', 'general', 'general', 'DENY');

INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (nextval('tbl_org_type_reg_org_type_id_seq'), 'Health enterprise', 'health enterprise', 'health_enterprise');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (nextval('tbl_org_type_reg_org_type_id_seq'), 'Public administration', 'public administration', 'public_administration');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (nextval('tbl_org_type_reg_org_type_id_seq'), 'Teaching', 'teaching', 'teaching');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (nextval('tbl_org_type_reg_org_type_id_seq'), 'Other', 'other', 'other');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (nextval('tbl_org_type_reg_org_type_id_seq'), 'Content supplier', 'content supplier', 'content_supplier');

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
insert into tbl_position_type_reg (descr, name, key, org_type_id) values ('', 'Ingen', 'none', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));

insert into tbl_person (first_name, last_name, last_changed) values ('Kjell', 'Tjensvoll',now());
insert into tbl_org_unit (org_type_id, org_unit_parent_id,contact_information_id,person_id, last_changed) values ((select org_type_id from tbl_org_type_reg where key = 'health_enterprise'),NULL,NULL,((select currval('tbl_person_person_id_seq'))),now());
insert into tbl_org_unit_name (language_code, name, category, org_unit_id, last_changed) values('no','Helsebiblioteket', 'NORMAL',((select currval('tbl_org_unit_org_unit_id_seq'))),now() );
insert into tbl_user (username, password, person_id, org_unit_id, last_changed) values ('kjelltjensvoll', 'kjshfkwebfkb23d', ((select currval('tbl_person_person_id_seq'))), ((select currval('tbl_org_unit_org_unit_id_seq'))),now());
insert into tbl_user_role (user_role_id,user_id, last_changed) values((select user_role_id from tbl_user_role_reg where key='administrator' and system_id=(select system_id from tbl_system_reg where key='helsebiblioteket_admin')),(select currval('tbl_user_user_id_seq')),now());



-------- Inserting all supplier organizations --------

-- inserting PubMed:
insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, null, now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'PubMed', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL',now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('pubmed', 'www.ncbi.nlm.nih.gov',	'http://www.ncbi.nlm.nih.gov/sites/entrez?otool=bibsys&holding=inohelib_fft_ndi&myncbishare=helsebiblioteket', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now()); 

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('pubmed', 'ncbi.nlm.nih.gov',	'http://ncbi.nlm.nih.gov/sites/entrez?otool=bibsys&holding=inohelib_fft_ndi&myncbishare=helsebiblioteket', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting GIN:
insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, null, now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'Guidelines International Network', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL', now());
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'GIN', (select currval('tbl_org_unit_org_unit_id_seq')), 'SHORT', now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('gin', 'www.g-i-n.net',	'http://www.g-i-n.net', now());

insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('gin', 'g-i-n.net',	'http://g-i-n.net', now());

insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting NEL:
insert into tbl_contact_information (postal_address, postal_code, postal_location, telephone_number, email, last_changed) 
values ('', '', '', '', 'anders.skjeggestad@nhi.no', now());

insert into tbl_person (first_name, last_name, student_number, hpr_number, contact_information_id, profile_id, position_type_id, last_changed)
values ('Andreas', 'Skjeggestad', null, null, (select currval('tbl_contact_information_contact_information_id_seq')), null, null, now());

insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, (select currval('tbl_person_person_id_seq')), now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('no', 'Norsk Elektronisk Legehåndbok', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL', now());
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('no', 'NEL', (select currval('tbl_org_unit_org_unit_id_seq')), 'SHORT', now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('nel', 'www.legehandboka.no',	'http://www.legehandboka.no', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('nel', 'legehandboka.no',	'http://legehandboka.no', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting ProQuest:
insert into tbl_contact_information (postal_address, postal_code, postal_location, telephone_number, email, last_changed) 
values ('', '', '', '', 'tomislaw.dalic@proquest.co.uk', now());

insert into tbl_person (first_name, last_name, student_number, hpr_number, contact_information_id, profile_id, position_type_id, last_changed)
values ('Tomislaw', 'Dalic', null, null, (select currval('tbl_contact_information_contact_information_id_seq')), null, null, now());

insert into tbl_contact_information (postal_address, postal_code, postal_location, telephone_number, email, last_changed) 
values ('', '', '', '', 'helsebiblioteket@proquest.co.uk', now());

insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), (select currval('tbl_contact_information_contact_information_id_seq')), (select currval('tbl_person_person_id_seq')), now());	

insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'ProQuest', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL', now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('proquest', 'www.proquest.com',	'http://www.proquest.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('proquest', 'proquest.com',	'http://proquest.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('proquest', 'proquest.umi.com',	'http://proquest.umi.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting Ovid:
insert into tbl_contact_information (postal_address, postal_code, postal_location, telephone_number, email, last_changed) 
values ('', '', '', '', 'support@ovid.com', now());

insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), (select currval('tbl_contact_information_contact_information_id_seq')), null, now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'Ovid', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL',now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('ovid', 'gateway.ovid.com',	'http://gateway.ovid.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('ovid', 'gateway.tx.ovid.com',	'http://gateway.tx.ovid.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('ovid', 'gateway.uk.ovid.com',	'http://gateway.uk.ovid.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('ovid', 'ovidsp.ovid.com',	'http://ovidsp.ovid.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('ovid', 'ovidsp.uk.ovid.com',	'http://ovidsp.uk.ovid.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('ovid', 'ovidsp.tx.ovid.com',	'http://ovidsp.tx.ovid.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting The Lancet Norway:
insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, null, now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'The Lancet Norway', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL',now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('thelancetnorway', 'thelancetnorway.com',	'http://thelancetnorway.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('thelancetnorway', 'www.thelancetnorway.com',	'http://www.thelancetnorway.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting American Psychological Association:
insert into tbl_contact_information (postal_address, postal_code, postal_location, telephone_number, email, last_changed) 
values ('', '', '', '', 'nlader@apa.org', now());

insert into tbl_person (first_name, last_name, student_number, hpr_number, contact_information_id, profile_id, position_type_id, last_changed)
values ('', '', null, null, (select currval('tbl_contact_information_contact_information_id_seq')), null, null, now());

insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, (select currval('tbl_person_person_id_seq')), now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'APA', (select currval('tbl_org_unit_org_unit_id_seq')), 'SHORT',now());

insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'American Psychological Association', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL',now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('apa', 'apa.org',	'http://apa.org', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('apa', 'www.apa.org',	'http://www.apa.org', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('apa', 'psycnet.apa.org',	'http://psycnet.apa.org', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('apa', 'content.apa.org',	'http://content.apa.org', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('apa', 'psycinfo.apa.org',	'http://psycinfo.apa.org', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting psycnet:
insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, null, now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'psycnet', (select currval('tbl_org_unit_org_unit_id_seq')), 'SHORT', now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('psycnet', 'psycnet.org', 'http://psycnet.org', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('psycnet', 'www.psycnet.org', 'http://www.psycnet.org', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting swetswise:
insert into tbl_contact_information (postal_address, postal_code, postal_location, telephone_number, email, last_changed) 
values ('', '', '', '', 'tnilsen@no.swets.com', now());

insert into tbl_person (first_name, last_name, student_number, hpr_number, contact_information_id, profile_id, position_type_id, last_changed)
values ('', '', null, null, (select currval('tbl_contact_information_contact_information_id_seq')), null, null, now());

insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, (select currval('tbl_person_person_id_seq')), now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'Swetswise', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL',now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('swetswise', 'swetswise.com', 'http://swetswise.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('swetswise', 'www.swetswise.com', 'http://www.swetswise.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting medicinescomplete:
insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, null, now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'MedicinesComplete', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL',now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('medicinescomplete', 'medicinescomplete.com',	'http://medicinescomplete.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('medicinescomplete', 'www.medicinescomplete.com',	'http://www.medicinescomplete.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting Lexi-Comp:
insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, null, now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'Lexi-Comp', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL', now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('lexicomp', 'online.lexi.com',	'http://online.lexi.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('lexicomp', 'www.online.lexi.com',	'http://www.online.lexi.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting Journals of the royal college of psychiatrists:
insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, null, now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'Journals of the royal college of psychiatrists', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL',now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('rcpsych', 'rcpsych.org', 'http://rcpsych.org', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('rcpsych', 'bjp.rcpsych.org', 'http://bjp.rcpsych.org', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting Sciencedirect:
insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, null, now());	
	
insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'ScienceDirect', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL',now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('sciencedirect', 'sciencedirect.com', 'http://sciencedirect.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('sciencedirect', 'www.sciencedirect.com', 'http://www.sciencedirect.com', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());


-- inserting Canadian Psychological Association:
insert into tbl_org_unit (descr, org_type_id, contact_information_id, person_id, last_changed) 
values ('', (select org_type_id from tbl_org_type_reg where key='content_supplier'), null, null, now());	

insert into tbl_org_unit_name (language_code, name, org_unit_id, category, last_changed)
values ('en', 'Canadian Psychological Association', (select currval('tbl_org_unit_org_unit_id_seq')), 'NORMAL',now());
	
insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('cpa', 'cpa.ca', 'http://cpa.ca', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

insert into tbl_supplier_source (proxy_database_name, supplier_source_name, url, last_changed)
values ('cpa', 'www.cpa.ca', 'http://www.cpa.ca', now());
insert into tbl_resource (supplier_source_id, resource_type_id, org_unit_id, last_changed)
values ((select currval('tbl_supplier_source_supplier_source_id_seq')), (select resource_type_id from tbl_resource_type_reg where key='supplier_source'), (select currval('tbl_org_unit_org_unit_id_seq')), now());

-------- Done inserting all supplier organizations --------



-- Access for organization types:
-- Leif Torger: På min TODO list


-- Access for roles:
-- Leif Torger: På min TODO list
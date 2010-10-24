-- This script inserts initial data into the database
-- TODO: Do not use!
--DELETE FROM tbl_resource_type_reg;
--DELETE FROM tbl_access_type_reg;
--DELETE FROM tbl_org_type_reg;
--DELETE FROM tbl_user_role_reg;
--DELETE FROM tbl_system_reg;

insert into tbl_org_unit (descr, org_type_id) values ('UVUS', (select org_type_id from tbl_org_type_reg where key = 'health_enterprise'));
insert into tbl_org_unit_name (language_code, category, name, org_unit_id) values ('no', 'SHORT', 'Ullevål universitetssykehus', (select org_unit_id from tbl_org_unit where descr = 'UVUS'));
insert into tbl_org_unit_name (language_code, category, name, org_unit_id) values ('en', 'SHORT', 'Ullevaal University Hospital', (select org_unit_id from tbl_org_unit where descr = 'UVUS'));
insert into tbl_org_unit_name (language_code, category, name, org_unit_id) values ('no', 'NORMAL', 'Ullevål universitetssykehus', (select org_unit_id from tbl_org_unit where descr = 'UVUS'));
insert into tbl_org_unit_name (language_code, category, name, org_unit_id) values ('en', 'NORMAL', 'Ullevaal University Hospital', (select org_unit_id from tbl_org_unit where descr = 'UVUS'));

insert into tbl_person (first_name, last_name) values ('Fredrik', 'Sørensen');
insert into tbl_person (first_name, last_name) values ('Leif Torger II', 'Grøndahl');

insert into tbl_user (username, password, org_unit_id, person_id) values ('fredrik@hb.no', 'qwerty', (select org_unit_id from tbl_org_unit where descr = 'UVUS'), (select person_id from tbl_person where first_name = 'Fredrik' and last_name = 'Sørensen'));
insert into tbl_user (username, password, org_unit_id, person_id) values ('leif@hb.no', 'qwerty', (select org_unit_id from tbl_org_unit where descr = 'UVUS'), (select person_id from tbl_person where first_name = 'Leif Torger II' and last_name = 'Grøndahl'));


insert into tbl_user_role(user_role_id, user_id) values ((select user_role_id from tbl_user_role_reg where key='student'), (select user_id from tbl_user where username = 'fredrik@hb.no'));
insert into tbl_user_role(user_role_id, user_id) values ((select user_role_id from tbl_user_role_reg where key='administrator'), (select user_id from tbl_user where username = 'leif@hb.no'));

-- This is not needed when error is fixed in init_database.sql
-- update tbl_user_role_reg set key = 'student', name='Student' where key = 'students'
-- update tbl_org_type_reg set descr = 'Others', name='other', key='other' where key = 'others'

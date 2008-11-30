-- This script inserts initial data into the database

-- TODO: init database

-- Some corrections
-- TODO: Some of this is wrong!
ALTER TABLE tbl_org_unit DROP COLUMN org_type_id;
ALTER TABLE tbl_org_unit ADD COLUMN org_type_key character varying;
ALTER TABLE tbl_org_unit ALTER COLUMN org_type_key SET STORAGE PLAIN;
ALTER TABLE tbl_org_unit ALTER COLUMN org_type_key SET NOT NULL;
--ALTER TABLE tbl_org_unit DROP CONSTRAINT organizations_for_org_type;
ALTER TABLE tbl_org_type_reg  ADD UNIQUE (key);
ALTER TABLE tbl_org_unit
  ADD CONSTRAINT organizations_for_org_type FOREIGN KEY (org_type_key)
      REFERENCES tbl_org_type_reg (key) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE tbl_org_unit DROP COLUMN org_unit_parent_id ;
ALTER TABLE tbl_org_unit ADD COLUMN org_unit_parent_id integer;
ALTER TABLE tbl_org_unit ALTER COLUMN org_unit_parent_id SET STORAGE PLAIN;

-- THIS MUST BE DONE
alter table tbl_user add person_id integer;
alter table tbl_user add foreign key(person_id) references tbl_person (person_id);
alter table tbl_person drop user_id;
alter table tbl_person drop org_unit_id;
alter table tbl_user_role_reg drop  user_role_category_id;
alter table tbl_user_role_reg drop  system_id;

-- Some test data
insert into tbl_org_type_reg (descr, name, key) values ('Kommentar for helseforetak', 'Helseforetak', 'hftk');
insert into tbl_org_unit (name, descr, name_short, org_type_key) values ('Ullevål universitetssykehus', '', 'Ullevål UvS', 'hftk');
insert into tbl_user (username, password, org_unit_id) values ('test@example.org', 'qwerty', 3);
insert into tbl_user (username, password, org_unit_id) values ('leif@hb.no', 'qwerty', 3);
insert into tbl_person (first_name, last_name) values ('Fredrik', 'Sørensen');
insert into tbl_person (first_name, last_name) values ('Leif Torger', 'Grøndahl');
--select * from tbl_person; --1,2
--select * from tbl_user; --2,3
update tbl_user set person_id = 1 where user_id = 2;  
update tbl_user set person_id = 2 where user_id = 3;

insert into tbl_user_role_reg(name, descr, key) values ('Administrator', '', 'ADMN');
insert into tbl_user_role_reg(name, descr, key) values ('Health Personnel', '', 'HPNR');
insert into tbl_user_role_reg(name, descr, key) values ('Student', '', 'STUD');
insert into tbl_user_role_reg(name, descr, key) values ('Health or public worker', '', 'ANST');

insert into tbl_user_role(user_id, user_role_id) values (2, 4);
insert into tbl_user_role(user_id, user_role_id) values (2, 5);
insert into tbl_user_role(user_id, user_role_id) values (3, 3);


-- EXTRA: NOT NEEDED! FIX STUPID ERROR ABOVE!
update tbl_org_unit set org_type_id = 1;

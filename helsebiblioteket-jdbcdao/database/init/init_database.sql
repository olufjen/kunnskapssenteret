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


-- Some test data
insert into tbl_org_type_reg (descr, name, key) values ('Kommentar for helseforetak', 'Helseforetak', 'hftk')
insert into tbl_org_unit (name, descr, name_short, org_type_key) values ('Ullevål universitetssykehus', '', 'Ullevål UvS', 'hftk')
insert into tbl_user (username, password, org_unit_id) values ('test@example.org', 'qwerty', 3)

--
-- Creating new organization types
--

insert into tbl_org_type_reg (org_type_id, descr, name, key) values
(6, 'Helsebiblioteket', 'Helsebiblioteket', 'helsebiblioteket');
insert into tbl_org_type_reg (org_type_id, descr, name, key) values
(7, 'Primary care', 'primary care', 'primary_care');
insert into tbl_org_type_reg (org_type_id, descr, name, key) values
(8, 'University', 'university', 'university');
update tbl_org_type_reg set descr='Government', name='government',
key='government' where key = 'public_administration';

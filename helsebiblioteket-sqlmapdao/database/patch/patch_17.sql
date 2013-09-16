insert into tbl_database_patches (database_version, patch_name, patch_date)
values ('17', 'new columns for tbl_contact_information, new role for administrators in member organizations', now());

ALTER TABLE tbl_contact_information ADD COLUMN logo bytea;
ALTER TABLE tbl_contact_information ADD COLUMN info character varying;
ALTER TABLE tbl_contact_information ADD COLUMN logo_contenttype character varying;
ALTER TABLE tbl_contact_information ADD COLUMN logo_name character varying;

insert into tbl_user_role_reg (name, descr, system_id, key)
values ('Member organization admin', 'Administrator for member organizations', 1, 'member_org_admin');

commit;
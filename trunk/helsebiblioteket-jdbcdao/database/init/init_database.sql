INSERT INTO tbl_system_reg (name, descr, key, system_id) VALUES ('Helsebiblioteket - administration', 'Administration of organizations, IP-addresses, users and access', 'helsebiblioteket_admin', 1);

INSERT INTO tbl_user_role_reg (name, descr, user_role_id, system_id, key) VALUES ('Administrator', 'Administrator with access to all functionality', 1, 1, 'administrator');
INSERT INTO tbl_user_role_reg (name, descr, user_role_id, system_id, key) VALUES ('Health personell', 'Authorized health personell with health personnel number', 2, 1, 'health_personnel');
INSERT INTO tbl_user_role_reg (name, descr, user_role_id, system_id, key) VALUES ('Students', 'Students with valid student number', 3, 1, 'students');
INSERT INTO tbl_user_role_reg (name, descr, user_role_id, system_id, key) VALUES ('Health personell other', 'Authorized health personell without health personnel number', 4, 1, 'health_personnel_other');

INSERT INTO tbl_access_type_reg (access_type_id, descr, name, key, category) VALUES (1, 'general access permission', 'general', 'general', 'GRANT');
INSERT INTO tbl_access_type_reg (access_type_id, descr, name, key, category) VALUES (2, 'general denial of access', 'general', 'general', 'DENY');

INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (1, 'Health enterprise', 'health enterprise', 'health_enterprise');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (2, 'Public administration', 'public administration', 'public_administration');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (3, 'Teaching', 'teaching', 'teaching');
INSERT INTO tbl_org_type_reg (org_type_id, descr, name, key) VALUES (4, 'Others', 'others', 'others');

INSERT INTO tbl_resource_type_reg (resource_type_id, descr, name, key) VALUES (1, 'Supplier source', 'supplier source', 'supplier_source');
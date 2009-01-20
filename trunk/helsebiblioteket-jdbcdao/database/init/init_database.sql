INSERT INTO tbl_system_reg (name, descr, key) VALUES ('Helsebiblioteket - administration', 'Administration of organizations, IP-addresses, users and access', 'helsebiblioteket_admin');

INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Administrator', 'Administrator with access to all functionality', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'administrator');
INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Health personell', 'Authorized health personell with health personnel number', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'health_personnel');
INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Student', 'Student with valid student number', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'student');
INSERT INTO tbl_user_role_reg (name, descr, system_id, key) VALUES ('Health personell other', 'Authorized health personell without health personnel number', (select system_id from tbl_system_reg where key = 'helsebiblioteket_admin'), 'health_personnel_other');

INSERT INTO tbl_access_type_reg (descr, name, key, category) VALUES ('general access permission', 'general', 'general', 'GRANT');
INSERT INTO tbl_access_type_reg (descr, name, key, category) VALUES ('general denial of access', 'general', 'general', 'DENY');

INSERT INTO tbl_org_type_reg (descr, name, key) VALUES ('Health enterprise', 'health enterprise', 'health_enterprise');
INSERT INTO tbl_org_type_reg (descr, name, key) VALUES ('Public administration', 'public administration', 'public_administration');
INSERT INTO tbl_org_type_reg (descr, name, key) VALUES ('Teaching', 'teaching', 'teaching');
INSERT INTO tbl_org_type_reg (descr, name, key) VALUES ('Others', 'others', 'others');

INSERT INTO tbl_resource_type_reg (descr, name, key) VALUES ('Supplier source', 'supplier source', 'supplier_source');

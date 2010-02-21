ALTER TABLE tbl_user ADD admin_org_unit_id INTEGER NULL DEFAULT NULL;

ALTER TABLE tbl_user
    ADD CONSTRAINT admin_org_unit_id_for_tbl_user FOREIGN KEY ( admin_org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );

INSERT INTO tbl_user_role_reg (name, descr, user_role_id, system_id, key) VALUES ('Organization admin', 'Organization administrator', 5, 1, 'org_admin');

--SELECT * FROM tbl_user WHERE username = ''
--SELECT * FROM tbl_user_role WHERE user_id = X
--INSERT INTO tbl_user_role (user_role_id, user_id, last_changed) VALUES (5, X, now());
--SELECT * FROM tbl_org_unit_name WHERE name = ''
--UPDATE tbl_user SET admin_org_unit_id = Y WHERE user_id = X;

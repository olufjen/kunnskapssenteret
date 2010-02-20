ALTER TABLE tbl_org_unit ADD org_user_id INTEGER NULL DEFAULT NULL;

ALTER TABLE tbl_org_unit
    ADD CONSTRAINT org_user_id_for_tbl_org_unit FOREIGN KEY ( org_user_id ) REFERENCES tbl_user ( user_id );

INSERT INTO tbl_user_role_reg (name, descr, user_role_id, system_id, key) VALUES ('Organization admin', 'Organization administrator', 5, 1, 'org_admin');

--select * from tbl_user where username = ''
--select * from tbl_user_role where user_id = X
--INSERT INTO tbl_user_role (user_role_id, user_id, last_changed) VALUES (5, X, now());

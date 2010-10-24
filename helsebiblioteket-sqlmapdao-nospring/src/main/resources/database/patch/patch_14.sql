ALTER TABLE tbl_supplier_source ADD url VARCHAR DEFAULT NULL;
ALTER TABLE tbl_supplier_source ADD host VARCHAR DEFAULT NULL;

ALTER TABLE tbl_supplier_source
  DROP CONSTRAINT tbl_supplier_source_unique;

update tbl_supplier_source set url = domain;
update tbl_supplier_source set host = domain;

ALTER TABLE tbl_supplier_source
  ADD CONSTRAINT tbl_supplier_source_unique UNIQUE (supplier_source_name, domain, host);

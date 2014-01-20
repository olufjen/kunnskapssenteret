--DROP TABLE tbl_proxy_hits;

CREATE TABLE tbl_proxy_hits
(
    mem_org_unit_id      INTEGER                     NULL,
    sup_org_unit_id      INTEGER                     NULL,
    year                 CHAR(4)                     NOT NULL,
    month                CHAR(2)                     NOT NULL,
    day_of_month         CHAR(2)                     NOT NULL,
    day_of_week          CHAR(1)                     NOT NULL,
    hour                 CHAR(2)                     NOT NULL,
    count                INTEGER                     NOT NULL,
    multiple_members     BOOLEAN    DEFAULT FALSE    NOT NULL,
    last_changed         TIMESTAMP                   NOT NULL
);

ALTER TABLE tbl_proxy_hits
    ADD CONSTRAINT tbl_proxy_hits_key UNIQUE ( mem_org_unit_id, sup_org_unit_id,
                                               multiple_members, year, month,
                                               day_of_month, hour );
ALTER TABLE tbl_proxy_hits
    ADD CONSTRAINT mem_org_unit_id_for_poxy_hits FOREIGN KEY ( mem_org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );
ALTER TABLE tbl_proxy_hits
    ADD CONSTRAINT sup_org_unit_id_for_poxy_hits FOREIGN KEY ( sup_org_unit_id ) REFERENCES tbl_org_unit ( org_unit_id );
ALTER TABLE tbl_proxy_hits 
	OWNER TO helsebiblioteket;
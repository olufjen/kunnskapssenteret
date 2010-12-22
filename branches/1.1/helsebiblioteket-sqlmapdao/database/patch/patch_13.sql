--DROP TABLE tbl_config;

CREATE TABLE tbl_config
(
    config_id            SERIAL                      NOT NULL,
    groupname            VARCHAR                     NOT NULL,
    property             VARCHAR                     NOT NULL,
    property_value       VARCHAR                     NOT NULL,
    last_changed         TIMESTAMP                   NOT NULL,
    PRIMARY KEY ( config_id )
);

ALTER TABLE tbl_config
    ADD CONSTRAINT tbl_config_key UNIQUE ( groupname, property, property_value );

ALTER TABLE tbl_config 
	OWNER TO helsebiblioteket;

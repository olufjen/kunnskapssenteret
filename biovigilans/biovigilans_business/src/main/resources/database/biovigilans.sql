-- ======================================================================
-- ===   Sql Script for Database : PostgreSQL db
-- ===
-- === Build : 11
-- ======================================================================

CREATE TABLE Giver
  (
    giverId                          serial,
    kjonn                            varchar(2),
    alder                            varchar(10),
    vekt                             int4,
    givererfaring                    text,
    tidligerekomplikasjonjanei       text,
    tidligerekomplikasjonforklaring  text,
    givererfaringaferese             text,

    primary key(giverId)
  );

-- ======================================================================

CREATE TABLE Donasjon
  (
    donasjonsid               serial,
    donasjonssted             text,
    komplisertvenepunksjon    text,
    tappetype                 text,
    tappevarighet             text,
    lokalisasjonvenepunksjon  text,
    maltidfortapping          text,
    giverId                   int,

    primary key(donasjonsid),

    foreign key(giverId) references Giver(giverId) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Pasient
  (
    pasient_id        serial,
    kjonn             varchar(2),
    aldersGruppe      varchar(10),
    inneliggendePoli  varchar(2),
    avdeling          text,

    primary key(pasient_id)
  );

-- ======================================================================

CREATE TABLE Antistoff
  (
    antistoffId           serial,
    antistoffKode         text,
    antistoffbeskrivelse  text,
    pasient_id            int,

    primary key(antistoffId),

    foreign key(pasient_id) references Pasient(pasient_id) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Transfusjon
  (
    transfusjonsid           serial,
    transfusjondato          timestamptz,
    transfusjonsklokkeslett  time,
    hastegrad                text,
    feiltranfudert           text,
    indikasjon               text,
    antallenheter            int2,
    pasient_id               int,

    primary key(transfusjonsid),

    foreign key(pasient_id) references Pasient(pasient_id) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Vigilansmelding
  (
    meldeid                   serial,
    datoforhendelse           timestamptz,
    klokkesletthendelse       time,
    datooppdaget              timestamptz,
    donasjonoverforing        timestamptz,
    sjekklistesaksbehandling  text,
    supplerendeopplysninger   text,
    meldingsdato              timestamptz,

    primary key(meldeid)
  );

-- ======================================================================

CREATE TABLE Blodprodukt
  (
    blodProduktId    serial,
    blodtype         text,
    alderprodukt     timestamptz,
    tappetype        text,
    blodprodukt      text,
    produktegenskap  text,
    antalenheter     int2,
    transfusjonsId   int,

    primary key(blodProduktId),

    foreign key(transfusjonsId) references Transfusjon(transfusjonsid) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Giverkomplikasjon
  (
    stedforkomplikasjon                text,
    behandlingssted                    text,
    tidfrarapporteringtilkomplikasjon  int4,
    tilleggsopplysninger               text,
    alvorlighetsgrad                   text,
    kliniskresultat                    text,
    varighetkomplikasjon               text,
    donasjonsid                        int,
    meldeid                            int,

    primary key(meldeid),

    foreign key(donasjonsid) references Donasjon(donasjonsid) match FULL on delete CASCADE,
    foreign key(meldeid) references Vigilansmelding(meldeid) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Giveroppfolging
  (
    giveroppfolgingid              serial,
    klassifikasjongiveroppfolging  text,
    giveroppfolgingbeskrivelse     text,
    avregistrering                 text,
    meldeid                        int,

    primary key(giveroppfolgingid),

    foreign key(meldeid) references Giverkomplikasjon(meldeid) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Komplikasjonsdiagnosegiver
  (
    komplikasjonsdiagnoseId   serial,
    lokalskadearm             text,
    systemiskbivirkning       text,
    annenreaksjon             text,
    lokalskadebeskrivelse     text,
    bivirkningbeskrivelse     text,
    annenreaksjonbeskrivelse  text,
    kommentar                 text,
    meldeid                   int,

    primary key(komplikasjonsdiagnoseId),

    foreign key(meldeid) references Giverkomplikasjon(meldeid) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Sykdom
  (
    sykdomnavn    text,
    symptomer     text,
    diagnosekode  text,
    sykdomId      serial,
    pasient_id    int,

    primary key(sykdomId),

    foreign key(pasient_id) references Pasient(pasient_id) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Pasientkomplikasjon
  (
    klassifikasjon                           text,
    meldeid                                  int,
    transfusjonsid                           int,
    tidfrapabegynttrasfusjontilkomplikasjon  text,
    alvorlighetsgrad                         text,
    kliniskresultat                          text,
    arsakssammenheng                         text,

    primary key(meldeid),

    foreign key(meldeid) references Vigilansmelding(meldeid) on delete SET NULL,
    foreign key(transfusjonsid) references Transfusjon(transfusjonsid) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Symptomer
  (
    symptomId              serial,
    symptomklassifikasjon  text,
    symptombeskrivelse     text,
    meldeid                int,
     primary key(symptomId),
    foreign key(meldeid) references Pasientkomplikasjon(meldeid) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Utredning
  (
    utredningId               serial,
    utredningsklassifikasjon  text,
    utredningbeskrivelse      text,
    blodtypeserologisk        text,
    hemolyseparameter         text,
    lga                       text,
    posedyrking               text,
    posedyrkingpositiv        text,
    meldeid                   int,

    primary key(utredningId),

    foreign key(meldeid) references Pasientkomplikasjon(meldeid) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Komplikasjonsklassifikasjon
  (
    klassifikasjonsid           serial,
    klassifikasjon              text,
    klassifikasjonsbeskrivelse  text,
    meldeid                     int,

    primary key(klassifikasjonsid),

    foreign key(meldeid) references Pasientkomplikasjon(meldeid) on delete CASCADE
  );

-- ======================================================================


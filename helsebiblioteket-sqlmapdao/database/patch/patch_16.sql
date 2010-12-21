--
-- Adding ordering to position types
--

alter table tbl_position_type_reg add ordering integer;

update tbl_position_type_reg set ordering = 10 where key = 'ambulansearbeider';
update tbl_position_type_reg set ordering = 20 where key = 'apotektekniker';
update tbl_position_type_reg set ordering = 30 where key = 'audiograf';
update tbl_position_type_reg set ordering = 40 where key = 'bioingenior';
update tbl_position_type_reg set ordering = 50 where key = 'ergoterapeut';
update tbl_position_type_reg set ordering = 60 where key = 'fotterapeut';
update tbl_position_type_reg set ordering = 70 where key = 'fysioterapeut';
update tbl_position_type_reg set ordering = 80 where key = 'helsefagarbeider';
update tbl_position_type_reg set ordering = 90 where key = 'helsesekretar';
update tbl_position_type_reg set ordering = 100 where key = 'hjelpepleier';
update tbl_position_type_reg set ordering = 110 where key = 'jordmor';
update tbl_position_type_reg set ordering = 120 where key = 'kiropraktor';
update tbl_position_type_reg set ordering = 130 where key = 'klinisk_ernaringsfysiolog';
update tbl_position_type_reg set ordering = 140 where key = 'lege';
update tbl_position_type_reg set ordering = 150 where key = 'omsorgsarbeider';
update tbl_position_type_reg set ordering = 160 where key = 'optiker';
update tbl_position_type_reg set ordering = 170 where key = 'ortopediingenior';
update tbl_position_type_reg set ordering = 180 where key = 'ortoptist';
update tbl_position_type_reg set ordering = 190 where key = 'perfusjonist';
update tbl_position_type_reg set ordering = 200 where key = 'provisorfarmasoyt';
update tbl_position_type_reg set ordering = 210 where key = 'psykolog';
update tbl_position_type_reg set ordering = 220 where key = 'radiograf';
update tbl_position_type_reg set ordering = 230 where key = 'reseptarfarmasoyt';
update tbl_position_type_reg set ordering = 240 where key = 'sykepleier';
update tbl_position_type_reg set ordering = 250 where key = 'tannhelsesekretar';
update tbl_position_type_reg set ordering = 260 where key = 'tannlege';
update tbl_position_type_reg set ordering = 270 where key = 'tannpleier';
update tbl_position_type_reg set ordering = 280 where key = 'tanntekniker';
update tbl_position_type_reg set ordering = 290 where key = 'vernepleier';
update tbl_position_type_reg set ordering = 300 where key = 'none';

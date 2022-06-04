INSERT INTO province VALUES ('Ancona', 1, 'AN');
INSERT INTO province VALUES ('Macerata', 1, 'MC');
INSERT INTO province VALUES ('Pesaro Urbino', 1, 'PU');
INSERT INTO province VALUES ('Ascoli Piceno', 1, 'AP');
INSERT INTO province VALUES ('Alessandria', 13, 'AL');
INSERT INTO province VALUES ('Asti', 13, 'AT');
INSERT INTO province VALUES ('Biella', 13, 'BI');
INSERT INTO province VALUES ('Cuneo', 13, 'CN');
INSERT INTO province VALUES ('Novara', 13, 'NO');
INSERT INTO province VALUES ('Vercelli', 13, 'VC');
INSERT INTO province VALUES ('Torino', 13, 'TO');
INSERT INTO province VALUES ('Agrigento', 11, 'AG');
INSERT INTO province VALUES ('Caltanissetta', 11, 'CL');
INSERT INTO province VALUES ('Catania', 11, 'CT');
INSERT INTO province VALUES ('Enna', 11, 'EN');
INSERT INTO province VALUES ('Messina', 11, 'ME');
INSERT INTO province VALUES ('Palermo', 11, 'PA');
INSERT INTO province VALUES ('Ragusa', 11, 'RG');
INSERT INTO province VALUES ('Siracusa', 11, 'SR');
INSERT INTO province VALUES ('Trapani', 11, 'TP');
INSERT INTO province VALUES ('Catanzaro', 7, 'CZ');
INSERT INTO province VALUES ('Cosenza', 7, 'CS');
INSERT INTO province VALUES ('Crotone', 7, 'KR');
INSERT INTO province VALUES ('Reggio Calabria', 7, 'RC');
INSERT INTO province VALUES ('Vibo Valentia', 7, 'VV');
INSERT INTO province VALUES ('Verbania', 13, 'VB');
INSERT INTO province VALUES ('Matera', 3, 'MT');
INSERT INTO province VALUES ('Potenza', 3, 'PZ');
INSERT INTO province VALUES ('Bari', 6, 'BA');
INSERT INTO province VALUES ('Brindisi', 6, 'BR');
INSERT INTO province VALUES ('Foggia', 6, 'FG');
INSERT INTO province VALUES ('Lecce', 6, 'LE');
INSERT INTO province VALUES ('Taranto', 6, 'TA');
INSERT INTO province VALUES ('Avellino', 8, 'AV');
INSERT INTO province VALUES ('Benevento', 8, 'BN');
INSERT INTO province VALUES ('Caserta', 8, 'CE');
INSERT INTO province VALUES ('Napoli', 8, 'NA');
INSERT INTO province VALUES ('Salerno', 8, 'SA');
INSERT INTO province VALUES ('Frosinone', 9, 'FR');
INSERT INTO province VALUES ('Latina', 9, 'LT');
INSERT INTO province VALUES ('Rieti', 9, 'RI');
INSERT INTO province VALUES ('Roma', 9, 'RM');
INSERT INTO province VALUES ('Viterbo', 9, 'VT');
INSERT INTO province VALUES ('Chieti', 2, 'CH');
INSERT INTO province VALUES ('L''Aquila', 2, 'AQ');
INSERT INTO province VALUES ('Pescara', 2, 'PE');
INSERT INTO province VALUES ('Teramo', 2, 'TE');
INSERT INTO province VALUES ('Arezzo', 12, 'AR');
INSERT INTO province VALUES ('Firenze', 12, 'FI');
INSERT INTO province VALUES ('Grosseto', 12, 'GR');
INSERT INTO province VALUES ('Livorno', 12, 'LI');
INSERT INTO province VALUES ('Lucca', 12, 'LU');
INSERT INTO province VALUES ('Massa Carrara', 12, 'MS');
INSERT INTO province VALUES ('Pisa', 12, 'PI');
INSERT INTO province VALUES ('Pistoia', 12, 'PT');
INSERT INTO province VALUES ('Siena', 12, 'SI');
INSERT INTO province VALUES ('Bologna', 14, 'BO');
INSERT INTO province VALUES ('Ferrara', 14, 'FE');
INSERT INTO province VALUES ('Forlì Cesena', 14, 'FC');
INSERT INTO province VALUES ('Modena', 14, 'MO');
INSERT INTO province VALUES ('Parma', 14, 'PR');
INSERT INTO province VALUES ('Piacenza', 14, 'PC');
INSERT INTO province VALUES ('Ravenna', 14, 'RA');
INSERT INTO province VALUES ('Reggio Emilia', 14, 'RE');
INSERT INTO province VALUES ('Rimini', 14, 'RN');
INSERT INTO province VALUES ('Belluno', 17, 'BL');
INSERT INTO province VALUES ('Padova', 17, 'PD');
INSERT INTO province VALUES ('Rovigo', 17, 'RO');
INSERT INTO province VALUES ('Treviso', 17, 'TV');
INSERT INTO province VALUES ('Venezia', 17, 'VE');
INSERT INTO province VALUES ('Verona', 17, 'VR');
INSERT INTO province VALUES ('Vicenza', 17, 'VI');
INSERT INTO province VALUES ('Gorizia', 15, 'GO');
INSERT INTO province VALUES ('Pordenone', 15, 'PN');
INSERT INTO province VALUES ('Udine', 15, 'UD');
INSERT INTO province VALUES ('Trieste', 15, 'TS');
INSERT INTO province VALUES ('Aosta', 16, 'AO');
INSERT INTO province VALUES ('Cagliari', 10, 'CA');
INSERT INTO province VALUES ('Nuoro', 10, 'NU');
INSERT INTO province VALUES ('Oristano', 10, 'OR');
INSERT INTO province VALUES ('Sassari', 10, 'SS');
INSERT INTO province VALUES ('Genova', 18, 'GE');
INSERT INTO province VALUES ('Imperia', 18, 'IM');
INSERT INTO province VALUES ('Savona', 18, 'SV');
INSERT INTO province VALUES ('La Spezia', 18, 'SP');
INSERT INTO province VALUES ('Isernia', 4, 'IS');
INSERT INTO province VALUES ('Campobasso', 4, 'CB');
INSERT INTO province VALUES ('Perugia', 20, 'PG');
INSERT INTO province VALUES ('Terni', 20, 'TR');
INSERT INTO province VALUES ('Bergamo', 19, 'BG');
INSERT INTO province VALUES ('Brescia', 19, 'BS');
INSERT INTO province VALUES ('Como', 19, 'CO');
INSERT INTO province VALUES ('Cremona', 19, 'CR');
INSERT INTO province VALUES ('Lecco', 19, 'LC');
INSERT INTO province VALUES ('Lodi', 19, 'LO');
INSERT INTO province VALUES ('Mantova', 19, 'MN');
INSERT INTO province VALUES ('Milano', 19, 'MI');
INSERT INTO province VALUES ('Pavia', 19, 'PV');
INSERT INTO province VALUES ('Sondrio', 19, 'SO');
INSERT INTO province VALUES ('Varese', 19, 'VA');
INSERT INTO province VALUES ('Trento', 5, 'TN');
INSERT INTO province VALUES ('Bolzano', 5, 'BZ');
INSERT INTO province VALUES ('Prato', 12, 'PO');
INSERT INTO province VALUES ('Carbonia Iglesias', 10, 'CI');
INSERT INTO province VALUES ('Medio Campidano', 10, 'VS');
INSERT INTO province VALUES ('Ogliastra', 10, 'OG');
INSERT INTO province VALUES ('Olbia Tempio', 10, 'OT');


INSERT INTO centrivaccinali VALUES('cv gallarate aeronautica militare', 'HUB', 'VIA', 'Milano', '115', 'Gallarate', 'VA', 21013);
CREATE TABLE vaccinati_cv_gallarate_aeronautica_militare (nomecittadino varchar(50), cognomecittadino varchar(50), codicefiscale varchar(50), data DATE, vaccino varchar(20), idvaccinazione SMALLINT, PRIMARY KEY(codicefiscale), FOREIGN KEY(idvaccinazione) REFERENCES idunivoci(idvaccinazione), FOREIGN KEY(codicefiscale) REFERENCES idunivoci(codicefiscale));

INSERT INTO centrivaccinali VALUES('ospedale giuseppe casati', 'OSPEDALIERO', 'VIA', 'Luigi Settembrini', '1', 'Rho', 'MI', 22017);
CREATE TABLE vaccinati_ospedale_giuseppe_casati (nomecittadino varchar(50), cognomecittadino varchar(50), codicefiscale varchar(50), data DATE, vaccino varchar(20), idvaccinazione SMALLINT, PRIMARY KEY(codicefiscale), FOREIGN KEY(idvaccinazione) REFERENCES idunivoci(idvaccinazione), FOREIGN KEY(codicefiscale) REFERENCES idunivoci(codicefiscale));

INSERT INTO centrivaccinali VALUES('palazzo delle scintille', 'HUB', 'PIAZZA', 'Sei Febbraio', '1', 'Milano', 'MI', 20145);
CREATE TABLE vaccinati_palazzo_delle_scintille (nomecittadino varchar(50), cognomecittadino varchar(50), codicefiscale varchar(50), data DATE, vaccino varchar(20), idvaccinazione SMALLINT, PRIMARY KEY(codicefiscale), FOREIGN KEY(idvaccinazione) REFERENCES idunivoci(idvaccinazione), FOREIGN KEY(codicefiscale) REFERENCES idunivoci(codicefiscale));

INSERT INTO centrivaccinali VALUES('multimedica marelli', 'HUB', 'VIALE', 'Edison Tommaso', '50', 'Sesto San Giovani', 'MI', 20099);
CREATE TABLE vaccinati_multimedica_marelli (nomecittadino varchar(50), cognomecittadino varchar(50), codicefiscale varchar(50), data DATE, vaccino varchar(20), idvaccinazione SMALLINT, PRIMARY KEY(codicefiscale), FOREIGN KEY(idvaccinazione) REFERENCES idunivoci(idvaccinazione), FOREIGN KEY(codicefiscale) REFERENCES idunivoci(codicefiscale));

INSERT INTO centrivaccinali VALUES('centro vaccinale ats milano', 'AZIENDALE', 'VIA', 'Giuseppe Pecchio', '19', 'Milano', 'MI', 20131);
CREATE TABLE vaccinati_centro_vaccinale_ats_milano (nomecittadino varchar(50), cognomecittadino varchar(50), codicefiscale varchar(50), data DATE, vaccino varchar(20), idvaccinazione SMALLINT, PRIMARY KEY(codicefiscale), FOREIGN KEY(idvaccinazione) REFERENCES idunivoci(idvaccinazione), FOREIGN KEY(codicefiscale) REFERENCES idunivoci(codicefiscale));

INSERT INTO centrivaccinali VALUES('humanitas mater domini', 'OSPEDALIERO', 'VIA', 'Gerenzano', '2', 'Castellanza', 'VA', 21053);
CREATE TABLE vaccinati_humanitas_mater_domini(nomecittadino varchar(50), cognomecittadino varchar(50), codicefiscale varchar(50), data DATE, vaccino varchar(20), idvaccinazione SMALLINT, PRIMARY KEY(codicefiscale), FOREIGN KEY(idvaccinazione) REFERENCES idunivoci(idvaccinazione), FOREIGN KEY(codicefiscale) REFERENCES idunivoci(codicefiscale));

INSERT INTO sintomi VALUES(0, 'Mal di testa', 'Cefalea intensa, sbalzi di pressione e vertigini');
INSERT INTO sintomi VALUES(1, 'Dolori intestinali', 'Colite, dolori severi e/o improvvisi nella zona dell''addome');
INSERT INTO sintomi VALUES(2, 'Spossatezza', 'Affaticamenti e debolezza eccessivi durante la giornata, colpi di sonno');
INSERT INTO sintomi VALUES(3, 'Mancamenti', 'Sincope postuma alla somministrazione del vaccino');
INSERT INTO sintomi VALUES(4, 'Nausea', 'Emesi eccessiva e costante durante tutta la giornata, febbre e tachicardia');

INSERT INTO idunivoci VALUES(4813, 'NVLNHL43R06A669H', 'cv gallarate aeronautica militare');
INSERT INTO idunivoci VALUES(487, 'BRNFLV64D46A465K', 'centro vaccinale ats milano');
INSERT INTO idunivoci VALUES(94, 'GRRSRA78L46M279D', 'multimedica marelli');
INSERT INTO idunivoci VALUES(2787, 'VLTGLI86R24A786H', 'ospedale giuseppe casati');
INSERT INTO idunivoci VALUES(145, 'MLTGCR57T17C052G', 'humanitas mater domini');
INSERT INTO idunivoci VALUES(1954, 'MSZPLA67D59C707N', 'humanitas mater domini');
INSERT INTO idunivoci VALUES(2845, 'DPSPQL56M59C040H', 'cv gallarate aeronautica militare');
INSERT INTO idunivoci VALUES(675, 'VZZMSM66C25F205C', 'palazzo delle scintille');
INSERT INTO idunivoci VALUES(25, 'VLLMNL63L54E514B', 'cv gallarate aeronautica militare');
INSERT INTO idunivoci VALUES(692, 'BSCNDR77D26H264F', 'palazzo delle scintille');

INSERT INTO utenti VALUES('Elena', 'e', 'BRNLNE00L41F704I', 'elena', 'bernucci');
INSERT INTO utenti VALUES('Luca', 'l', 'CLMLCU99H10E514Q', 'luca', 'clementi');
INSERT INTO utenti VALUES('Federico', 'f', 'RCLFRC89P02L469I', 'federico', 'ercoli');


INSERT INTO utenti VALUES('Nicholas', 'n', 'NVLNHL43R06A669H', 'nicholas', 'noviello');
INSERT INTO utenti VALUES('Flavia', 'f', 'BRNFLV64D46A465K', 'flavia', 'bernardi');
INSERT INTO utenti VALUES('Sara', 's', 'GRRSRA78L46M279D', 'sara', 'gerardi');
INSERT INTO utenti VALUES('Giulio', 'g', 'VLTGLI86R24A786H', 'giulio', 'veltroni');
INSERT INTO utenti VALUES('Giancarlo', 'gian', 'MLTGCR57T17C052G', 'giancarlo', 'maltagliati');
INSERT INTO utenti VALUES('Paola', 'p', 'MSZPLA67D59C707N', 'paola', 'musazzi');
INSERT INTO utenti VALUES('Pasqualina', 'pa', 'DPSPQL56M59C040H', 'pasqualina', 'dipasquale');
INSERT INTO utenti VALUES('Massimo', 'm', 'VZZMSM66C25F205C', 'massimo', 'vezzani');
INSERT INTO utenti VALUES('Manuela', 'manu', 'VLLMNL63L54E514B', 'manuela', 'villani');
INSERT INTO utenti VALUES('Andrea', 'a', 'BSCNDR77D26H264F', 'andrea', 'bosco');

INSERT INTO cittadinivaccinati VALUES('Nicholas', 'nick971@live.it', 4813);
INSERT INTO cittadinivaccinati VALUES('Flavia', 'f.bernrdi@gmail.com', 487);
INSERT INTO cittadinivaccinati VALUES('Sara', 'ss-gerardi@libero.it', 94);
INSERT INTO cittadinivaccinati VALUES('Giulio', 'veltroni.official@outlook.com', 2787);
INSERT INTO cittadinivaccinati VALUES('Giancarlo', 'malta.gianky@gmail.com', 145);
INSERT INTO cittadinivaccinati VALUES('Paola', 'p.musazzi@virgilio.it', 1954);
INSERT INTO cittadinivaccinati VALUES('Pasqualina', 'dipa.lina@libero.it', 2845);
INSERT INTO cittadinivaccinati VALUES('Massimo', 'max.vez@gmail.com', 675);
INSERT INTO cittadinivaccinati VALUES('Manuela', 'manu-villi@gmail.com', 25);
INSERT INTO cittadinivaccinati VALUES('Andrea', 'ing.a.bosco@polimi.it', 692);

INSERT INTO vaccinati_cv_gallarate_aeronautica_militare VALUES('nicholas', 'noviello', 'NVLNHL43R06A669H', '2021-04-22', 'PFIZER', 4813);
INSERT INTO vaccinati_centro_vaccinale_ats_milano VALUES('flavia', 'bernardi', 'BRNFLV64D46A465K', '2021-03-21', 'PFIZER', 487);
INSERT INTO vaccinati_multimedica_marelli VALUES('sara', 'gerardi', 'GRRSRA78L46M279D', '2021-12-24', 'MODERNA', 94);
INSERT INTO vaccinati_ospedale_giuseppe_casati VALUES('giulio', 'veltroni', 'VLTGLI86R24A786H', '2021-05-01', 'JOHNSONANDJOHNSON', 2787);
INSERT INTO vaccinati_humanitas_mater_domini VALUES('giancarlo', 'maltagliati', 'MLTGCR57T17C052G', '2021-03-14', 'PFIZER', 145);
INSERT INTO vaccinati_humanitas_mater_domini VALUES('paola', 'musazzi', 'MSZPLA67D59C707N', '2022-01-04', 'PFIZER', 1954);
INSERT INTO vaccinati_cv_gallarate_aeronautica_militare VALUES('pasqualina', 'dipasquale', 'DPSPQL56M59C040H', '2021-03-05', 'ASTRAZENECA', 2845);
INSERT INTO vaccinati_palazzo_delle_scintille VALUES('massimo', 'vezzani', 'VZZMSM66C25F205C', '2021-05-12', 'MODERNA', 675);
INSERT INTO vaccinati_cv_gallarate_aeronautica_militare VALUES('manuela', 'villani', 'VLLMNL63L54E514B', '2021-04-03', 'JOHNSONANDJOHNSON', 25);
INSERT INTO vaccinati_palazzo_delle_scintille VALUES('andrea', 'bosco', 'BSCNDR77D26H264F', '2021-03-15', 'PFIZER', 692);

INSERT INTO segnalazioni VALUES(58472, 0, 'Nicholas', 'cv gallarate aeronautica militare', 3, 'Ho avuto difficoltà a dormire');
INSERT INTO segnalazioni VALUES(69382, 0, 'Flavia', 'centro vaccinale ats milano', 5, 'Non riuscivo a tenere gli occhi aperti, fitte lancinanti');
INSERT INTO segnalazioni VALUES(03928, 1, 'Sara', 'multimedica marelli', 4, 'Dolori all''addome, avevo la nausea, ho rimesso più volte');
INSERT INTO segnalazioni VALUES(57294, 1, 'Giulio', 'ospedale giuseppe casati', 1, 'Dolore sopportabile, sparito con un anti dolorifico');
INSERT INTO segnalazioni VALUES(91823, 2, 'Giancarlo', 'humanitas mater domini', 2, 'Mi sono sentito debilitato il giorno dopo, dolore articolare');
INSERT INTO segnalazioni VALUES(13245, 3, 'Manuela', 'cv gallarate aeronautica militare', 2, 'Abbassamento della pressione, vertigini');
INSERT INTO segnalazioni VALUES(28374, 4, 'Pasqualina', 'cv gallarate aeronautica militare', 4, 'Nausea severa, non sono riuscita a lavorare');

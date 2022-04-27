CREATE TABLE province (
  nome varchar(20) NOT NULL,
  idregione SMALLINT NOT NULL,
  sigla varchar(2) PRIMARY KEY
);

CREATE TABLE centrivaccinali (
	  nome varchar PRIMARY KEY,
	  tipologia varchar NOT NULL,
	  qualificatore varchar NOT NULL,
	  strada varchar NOT NULL,
	  civico varchar NOT NULL,
	  comune varchar NOT NULL,
	  provincia varchar NOT NULL,
	  cap varchar NOT NULL,
	    FOREIGN KEY(provincia) REFERENCES province(sigla)
);


CREATE TABLE utenti (
	  userid varchar PRIMARY KEY,
	  pass varchar NOT NULL,
	  codicefiscale varchar NOT NULL,
	  nome varchar NOT NULL,
	  cognome varchar NOT NULL
);

CREATE TABLE idunivoci (
	  idvaccinazione SMALLINT PRIMARY KEY,
	  codicefiscale varchar UNIQUE,
	  centrovaccinale varchar NOT NULL,
	    FOREIGN KEY(centrovaccinale) REFERENCES centrivaccinali(nome)
);

CREATE TABLE cittadinivaccinati (
	  userid varchar PRIMARY KEY,
	  email varchar NOT NULL,
	  idvaccinazione SMALLINT NOT NULL,
	        FOREIGN KEY(userid) REFERENCES utenti(userid),
	        FOREIGN KEY(idvaccinazione) REFERENCES idunivoci(idvaccinazione)
);



CREATE TABLE sintomi (
	  idsintomo SERIAL PRIMARY KEY,
	  sintomo varchar NOT NULL,
	  descrizione varchar NOT NULL
);

CREATE TABLE segnalazioni (
      idsegnalazione SERIAL PRIMARY KEY,
	  idsintomo SMALLINT,
	  userid varchar,
	  centrovaccinale varchar,
	  severita SMALLINT CHECK(severita BETWEEN 1 AND 5) NOT NULL,
	  descrizione varchar(256),
	       FOREIGN KEY(idsintomo) REFERENCES sintomi(idsintomo),
           FOREIGN KEY(userid) REFERENCES cittadinivaccinati(userid),
	       FOREIGN KEY(centrovaccinale) REFERENCES centrivaccinali(nome)
);
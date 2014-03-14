CREATE TABLE APP.BISOGNI
(
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
bisogno VARCHAR (1024) NOT NULL,
descrizione LONG VARCHAR ,
PRIMARY KEY (id)
) ;

INSERT INTO APP.BISOGNI (bisogno) values 
('Uscire dalla solitudine');
INSERT INTO APP.BISOGNI (bisogno) values 
('Avere a disposizione un locale in parrocchia dove incontrarsi con altri parrocchiani');
INSERT INTO APP.BISOGNI (bisogno) values 
('Rete di solidarieta (sostegno reciproco in caso di necessita)');
INSERT INTO APP.BISOGNI (bisogno) values 
('Assistenza fisica per i seguenti motivi di salute:');
INSERT INTO APP.BISOGNI (bisogno) values 
('Partecipare a gruppi di preghiera');
INSERT INTO APP.BISOGNI (bisogno) values 
('Imparare ad usare uno smartphone collegato a Internet, un tablet, un computer');
INSERT INTO APP.BISOGNI (bisogno) values 
('Bisogni economici fondamentali (cibo,alloggio,cure mediche,lavoro):');


CREATE TABLE APP.RISORSE
(
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
risorsa VARCHAR (1024) NOT NULL,
descrizione LONG VARCHAR ,
PRIMARY KEY (id)
) ;


INSERT INTO APP.RISORSE (risorsa) values 
('Fare volontariato nel seguente modo:');
INSERT INTO APP.RISORSE (risorsa) values 
('Autista');
INSERT INTO APP.RISORSE (risorsa) values 
('Assistenza fisica per motivi di salute');
INSERT INTO APP.RISORSE (risorsa) values 
('Fare compagnia');
INSERT INTO APP.RISORSE (risorsa) values 
('Segreteria in parrocchia');
INSERT INTO APP.RISORSE (risorsa) values 
('Lavori di manutenzione');
INSERT INTO APP.RISORSE (risorsa) values 
('Cucinare');
INSERT INTO APP.RISORSE (risorsa) values 
('Disbrigo pratiche');
INSERT INTO APP.RISORSE (risorsa) values 
('Giardinaggio');
INSERT INTO APP.RISORSE (risorsa) values 
('Pulizie');

CREATE TABLE APP.ANAGRAFICA
(
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
nome VARCHAR (1024),
cognome VARCHAR (1024),
eta INTEGER,
sesso VARCHAR (10),
professione VARCHAR (1024),
appartenenza VARCHAR (1024),
indirizzo VARCHAR (1024),
citta VARCHAR (1024),
telefono VARCHAR (200),
cellulare VARCHAR (200),
email VARCHAR (200),
note LONG VARCHAR ,
id_risorse_soddisf VARCHAR (200),
PRIMARY KEY (id)
) ;

INSERT INTO APP.ANAGRAFICA (nome,cognome,eta,sesso,professione,appartenenza,indirizzo,citta,telefono,cellulare,email,id_risorse_soddisf) values 
('Pippo','Crispino',45,'M','Ingegnere','Parrocchia S. Maria Mediatrice','Via Roma 34','Milano','02/34521346','324/563455','pippo@pippo.it','Non trovate');

CREATE TABLE APP.ANAG_RISORSE
(
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
id_anagrafica INTEGER NOT NULL ,
id_risorsa INTEGER NOT NULL,
PRIMARY KEY (id)
) ;

INSERT INTO APP.ANAG_RISORSE (id_anagrafica,id_risorsa) values 
(1,1);

CREATE TABLE APP.ANAG_BISOGNI
(
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
id_anagrafica INTEGER NOT NULL ,
id_bisogno INTEGER NOT NULL,
PRIMARY KEY (id)
) ;

INSERT INTO APP.ANAG_BISOGNI (id_anagrafica,id_bisogno) values 
(1,1);


DROP DATABASE IF EXISTS studyMe;
CREATE DATABASE studyMe;

USE studyMe;

DROP DATABASE IF EXISTS utente;
CREATE TABLE utente(
nomeUtente varchar(20) primary key,
password varchar(30) not null,
email varchar(50) not null,
tipo varchar(20) not null
);

DROP DATABASE IF EXISTS categoria;
CREATE TABLE categoria(
 nomeCategoria varchar(30) primary key,
 foto varchar(300) not null
);

DROP DATABASE IF EXISTS sottoCategoria;
CREATE TABLE sottoCategoria(
	idSottocat varchar(6) primary key,
    nomeSott  varchar(30) not null
);

DROP DATABASE IF EXISTS pacchetto;
CREATE TABLE pacchetto(
codicePacchetto varchar(6) primary key,
categoria	varchar(20) not null,
idSott  varchar(6) not null,
prezzo double not null,
descrizione varchar(1000) not null,
titolo varchar(200) not null,
foto varchar(300) not null,
nelCatalogo boolean not null,
approvato int default 0,
foreign key (categoria) references categoria(nomeCategoria)
	on update cascade
    on delete cascade,
foreign key (idSott) references sottoCategoria(idSottocat)
		on update cascade
        on delete cascade);
        
DROP DATABASE IF EXISTS lezioni;
CREATE TABLE lezioni(
url  varchar(100) primary key,
titolo varchar(200) not null,
durata varchar(20)not null,
codiceP varchar(6),
approvato int default 0,
foreign key (codiceP) references pacchetto(codicePacchetto)
		on update cascade
        on delete cascade);

#Ordine = Pacchetti aggiunti al carrello
DROP DATABASE IF EXISTS ordine;
CREATE TABLE ordine(
numOrdine int primary key not null auto_increment,
nomeCliente varchar(20) not null,
dataOdierna date not null,
foreign key(nomeCliente) references utente(nomeUtente)
		on update cascade
        on delete cascade);

#Acquisto = Pacchetto con ordine effettuato, cioè acquistato. (E' nella libreria)
DROP DATABASE IF EXISTS acquisto;
CREATE TABLE acquisto(
numAcquisto int auto_increment primary key,
numOrdine int not null,
codiceP varchar(6) not null,
titoloPacchetto varchar(50) not null,
prezzo double not null,             
foreign key (numOrdine) references ordine(numOrdine)
	on update cascade
    on delete cascade,
foreign key (codiceP) references pacchetto(codicePacchetto)
	on update cascade
    on delete cascade);

DROP DATABASE IF EXISTS recensione;
CREATE TABLE recensione(
idRecensione int auto_increment primary key,
userCliente varchar(20) not null,
codiceP varchar(6) not null,
commento varchar(500) not null,
titolo varchar(300) not null,
foreign key (userCliente) references utente(nomeUtente)
	on update cascade
    on delete cascade,
foreign key (codiceP) references pacchetto(codicePacchetto)
	on update cascade
    on delete cascade);
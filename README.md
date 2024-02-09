L'esercizio richiede di sviluppare un'applicazione mobile che simuli parte di un sistema per la gestione delle rilevazioni di velocità delle autostrade italiane.
L'applicazione deve gestire automobili, autovelox e le relative rilevazioni di velocità. 
Gli utenti possono cercare autovelox per città, inserire nuove rilevazioni e visualizzare statistiche sugli autovelox nelle città selezionate.

l progetto è stato implementato in Java versione 17 e, in particolare, è stato usato il framework Spring-boot.
Le entita sono persistite su un database mySQL e in Java sono state modellate mediante l'utilizzo dell'Entity Manager. Sono state utilizzate le annotazioni Spring-Data-JPA; mentre la parte rest è stata sviluppata mediante l'utilizzo di Spring-boot-starter-web.
Per migliorare la leggibilità del codice e ridurre la sua verbosità, è stata integrata l'estensione Lombok, che automatizza la generazione di getter, setter e costruttori.

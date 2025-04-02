CREATE TABLE TriptopGebruiker
(
    voornaam       varchar(265) NOT NULL,
    achternaam     varchar(265) NOT NULL,
    tussenvoegsels varchar(265) NULL,
    email          varchar(265) NOT NULL,
    access_token   varchar(MAX) NOT NULL,
    gebruikersId   INT AUTO_INCREMENT PRIMARY KEY
);
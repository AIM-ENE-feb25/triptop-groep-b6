CREATE TABLE TriptopGebruiker
(
    voornaam       varchar(265) NOT NULL,
    achternaam     varchar(265) NOT NULL,
    tussenvoegsels varchar(265) NULL,
    email          varchar(265) NOT NULL,
    gebruikersId   INT PRIMARY KEY
);

CREATE TABLE Betaling
(
    betalingId INT AUTO_INCREMENT PRIMARY KEY,
    gebruikersId INT NOT NULL,
    valuta VARCHAR(265) NOT NULL,
    bedrag DECIMAL(18,2) NOT NULL,
    FOREIGN KEY (gebruikersId) REFERENCES TriptopGebruiker(gebruikersId)
);

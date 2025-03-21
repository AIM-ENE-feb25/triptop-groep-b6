### 8.1. ADR-001 Database

#### Context 
Er moet een database worden gekozen voor de TripTop applicatie, het moet voor een PoC zijn. Hierin worden gebruikersgegevens/reisinformatie opgeslagen worden.

#### Considered Options
| **Forces**            | Microsoft SQL | No-SQL | H2 in memory |
| -------------------   | ------------- | ------ | ------------ |
| Eenvoudigheid         | +            | +      | +           |
| Snelheid              | -             | +      | ++           |
| Ease of development   | 0             | 0      | +            |
| Ervaring met database | ++            | 0      | ++           |


#### Decision
Na een teams-call besloten we te kiezen voor de H2 in memory database. We hebben hiervoor gekozen omdat we allemaal gewend zijn aan SQL. Ook is het in memory, wat developen makkelijker maakt. Gegevens worden gewist na afsluiten van de applicatie. Persistentie is ook niet nodig, het is puur voor een PoC applicatie.

#### Status 
Accepted

#### Consequences
De gekozen database is in-memory, waardoor deze niet geschikt is voor een volledige applicatie in de praktijk. Dit is nadelig als de PoC uitgewerkt wordt. Het voordeel is dat het ontwikkelen van de applicatie versneld, doordat gegevens worden gewist na het opnieuw opstarten van de applicatie.
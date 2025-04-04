### 8.5. ADR-005 Adapter Design Pattern voor Fault Tolerance

Hoort bij ontwerpvraag:
- Fault Tolerance: Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?



#### Context

Voor de applicatie moet een design pattern gekozen die gemakkelijk fouten kan afhandelen als externe services niet bereikbaar zijn.

#### Considered Options
| Forces                       | Adapter Pattern | Factory Pattern | Strategy Pattern |
| ---------------------------- | --------------- | --------------- | ---------------- |
| Overzicht                    | +               | 0               | +                |
| Compatibiliteit met services | ++              | -               | 0                |
| Onderhoudbaarheid            | +               | +               | +                |

#### Decision

We besloten om "Adapter Pattern" toe te passen. Het laat je externe services aanroepen zonder de implementatie van de externe services te gebruiken. Dit versimpelt de onderhoudbaarheid en complexiteit van de code. Dit is nuttig omdat de applicatie afhankelijk van vele externe services is.

#### Status
Accepted

#### Consequences
De adapter pattern kan overbodig zijn voor een PoC applicatie. Voor de PoC wordt niet elk functionaliteit uitgewerkt. Wel is het goed voor een werkende applicatie die beschikbaar wordt gesteld voor het publiek, omdat dit onderhoudbaarheid versterkt.
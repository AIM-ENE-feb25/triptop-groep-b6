# Gebruik van public transit API

## Context
Voor ons project hebben we een API nodig die openbaar vervoersgegevens kan leveren, zoals dienstregelingen en routes.

## Considered Options

| Force | Google Transit API | NS API | 9292 API |
|------------|-----------------|--------|---------|
| **Dekking** | ++ | - | + |
| **Realtime data** | ++ | + | + |
| **Kosten** | - | 0 | 0 |
| **Gebruiksgemak** | ++ | + | + |
| **Integratiemogelijkheden** | ++ | + | 0 |
| **Ondersteuning en documentatie** | ++ | + | - |
| **Schaalbaar** | ++ | + | 0 |
| **Nederland-specifieke data** | 0 | ++ | ++ |

## Decision
We hebben gekozen voor Google Transit, omdat wij hier de meeste voordelen in zien. Het is niet specifiek voor Nederlandse data, maar werkt wel goed in Nederland. Met zicht op de toekomst is dit ook de meest schaalbare API voor eventuele internationale ambities. Het is ook de API met de meeste dekking, de beste realtime data, het meest gebruiksvriendelijk en met de meeste integratiemogelijkheden en documentatie.

## Consequences
Het gevolg van het kiezen van deze API is dat we gemakkelijk routes met het OV kunnen voorstellen. Aangezien we ook de Google Maps API gebruiken, kan dit goed samenwerken met elkaar. Je kan hierdoor een route invullen en dan de daarbij horende OV-mogelijkheden bekijken.

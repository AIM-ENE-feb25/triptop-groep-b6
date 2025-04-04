# Booking naar backend of frontend

## Context
Booking is een applicatie die het mogelijk maakt om een hotelkamer te boeken. De vraag is of de api via de frontend of backend moet lopen.

## Considered Options
| Criteria                     | Frontend | Backend |
|------------------------------|----------|---------|
| **Beveiliging**              | -        | ++      |
| **Controle en validatie**    | 0        | ++      |
| **Prestaties (Latency)**     | ++       | -       |
| **Schaalbaarheid**          | -        | ++      |
| **Makkelijk te beheren**     | 0        | +       |
| **Complexiteit**            | +        | 0       |
| **Netwerkverkeer**          | ++       | 0       |
| **Schaalbaarheid**          | -        | ++      |

## Decision
Backend-aanroepen is voor ons de beste keuze omdat we het beheren van externe services vooral via de backend willen laten lopen, omdat het soms complexe logica vereisen of we informatie willen opslaan in eigen database.

## Consequences
De gevolgen van deze beslissing zijn dat we de API-aanroepen via de backend moeten laten lopen. Dit biedt meer controle over de gegevens en betere beveiliging door exceptions etc.
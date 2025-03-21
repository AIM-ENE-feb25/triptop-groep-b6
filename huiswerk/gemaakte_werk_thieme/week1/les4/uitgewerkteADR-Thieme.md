# Identity provider API keuze

Datum: 21-03-2025

## Status

Accepted

## Context

Er moet een identityprovider komen voor de triptop website. Deze moet moet dus een inlog functie hebben. Het is meer
werk om zelf een loginsyteem te maken vergeleken met bestaande API’s zoals Google Oauth2, Microsoft Oauth2 of Discord
Oauth2.

## Considered Options

| Forces                 | Google Oauth2 | Microsoft Oauth2 | Discord Oauth2 |
|------------------------|---------------|------------------|----------------|
| Populariteit           | ++            | +                | +`*`           |
| Gebruikers informatie  | ++            | +                | -              |
| Beveiliging            | ++            | ++               | 0 `**`         |
| Eenvoudige intergratie | +             | 0                | ++             |
| Kosten                 | 0             | 0                | +              |

`*` -> Populair onder mensen die gebruik maken van discord, alleen ligt bij deze casus buiten de scope.
`**` -> Geschikter voor informele authenticatie, mist geavanceerde beveiligingsmaatregelen.

## Decision

Het gebruik van een identity provider moet goed beveiligd zijn voor een productie-applicatie. Tevens omdat het team nu
aan het kijken is naar het maken van prototypes ligt beveiliging nu op een lager pitje, maar deze wordt niet vergeten.
Doordat Google over het algemeen populairder is vergeleken met Discord en Microsoft is de keuze van het team geworden
dat Google de identityprovider van Triptop wordt.

## Consequences

 * Bij het veranderen van de scope naar een professionelere setting dan een Reisboekings applicatie wordt het door het
team aangeraden om te veranderen van Google Oauth2 naar Microsoft Oauth2.
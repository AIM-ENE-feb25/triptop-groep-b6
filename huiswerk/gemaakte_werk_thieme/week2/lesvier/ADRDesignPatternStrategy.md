# Design pattern keuze 1: Strategy

## Status

Accepted

## Context

Bij het ontwikkelen van de Triptop applicatie zijn we afhankelijk van externe services, zoals Google, Stripe, etc. Door
wijzigingen in de API van deze services kan het zo zijn dat er aanzienlijke aanpassingen gedaan moeten worden aan de
applicatie. Met name in de architectuur zoals we het nu hebben opgezet (Frontend - Backend) kan een verandering in bijv.
de Google Oauth API impact hebben op de frontend als de backend deze wijziging niet opvangt.

Om de impact hiervan te minimaliseren en flexibiliteit in de backend te waarborgen, is er een design pattern nodig dat
de API-interacties loskoppelt van de interne logica en de frontend.

## Considered Options

| Forces                                                                       | Strategy Pattern | Adapter Pattern | Observer Pattern |
|------------------------------------------------------------------------------|------------------|-----------------|------------------|
| Flexibiliteit; Hoe past het patroon zich aan bij API-wijzigingen?            | ++               | +               | ++               |
| Onderhoudbaarheid; Hoe eenvoudig is het om uitbreidingen te maken?           | +                | ++              | +                |  
| Complexiteit; Hoeveel extra code en beheer is er nodig?                      | -                | 0               | -                |
| Runtime-selectie; Ondersteunt het een dynamische selectie van implementaties | ++               | -               | ++               |

## Decision

Het Strategy Pattern is gekozen omdat het de meeste flexibiliteit biedt en de onderhoudbaarheid van de code bevordert.
Het Strategy Pattern zorgt ervoor dat de API-interacties losgekoppeld worden van de interne logica en de frontend. Dit
maakt het mogelijk om de API-interacties te wijzigen zonder dat de frontend hier iets van merkt. De backend kan
dynamisch de juiste implementatie kiezen op basis van de configuratie.

## Consequences

* Verhoogde flexibiliteit en minder impact bij API-wijzigingen.
* Extra complexiteit in het beheren van strategieën en configuraties.
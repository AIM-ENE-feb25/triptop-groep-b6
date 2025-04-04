# Pressure Cooker Julius Morselt

## Ontwerp vraag
Hoe kunnen we dynamisch beslissen of een bouwsteen geboekt moet worden via een externe service of intern beheerd moet worden?

<br>

## Welk component wil ik hiervoor gaan gebruiken?
De TransitAPI, ik wil gaan kijken naar de Transit API.

<br>

## Lijst van componenten
|Component|Naam|Beschrijving|
|----------|----|------------|
|Externe API|Transit API|Dit is de externe API die aangeroepen gaat worden.|
|Controller|TransitController|Hier worden de endpoints ingesteld.|
|Service|TransitService|Hier komt alle logica voor de endpoints, zoals onder andere authenticatie en het omzetten van het request naar de gepaste JSON informatie.|
|Domein|TransitDomein|In dit domein worden variablen aangemaakt voor alle belangrijke dingen uit de request die je wilt opslaan.|

<br>

## Lijst van interfaces
|Interface|Methodes|Parameters|Return Waardes|
|-|-|-|-|
|`ITansitService`|`GetDeparturesFromParams()`|`String fromLatitude`, `String fromLongitude`, `String departure`|`String allDepartures`|
||`GetRoutesFromParams()`|`String fromLatitude`, `String fromLongitude`, `String toLatitude`, `String toLongitude`|`String allPossibleRoutes`|
||`FilterStringResponse`|`String typeResponse`|`JsonObject allDeparturesJSON` of `JsonObject allPossibleRoutesJSON`|
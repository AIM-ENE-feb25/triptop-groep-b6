# Ontwerpvraag:

Wie roept een specifieke externe service aan, gebeurt dat vanuit de front-end of vanuit de back-end? Welke redenen zijn er om voor de ene of de andere aanpak te kiezen?


# API's:    
- IdentityProvider
- MapsAPI


# Componenten:

### **Controller Layer**
- **TripTopController** – Handelt API-verzoeken van de front-end af en routeert ze naar de juiste services.
- **AuthController** – Behandelt authenticatie- en autorisatieverzoeken via **IdentityProviderService**.

### **Service Layer**
- **TripTopService** – Bevat de logica voor het ophalen, verwerken en opslaan van reisgegevens.
- **IdentityProviderService** – Beheert gebruikersauthenticatie en autorisatie met OAuth2.
- **MapsService** – Behandelt de interactie met **Google Maps API** voor routes en locaties.
- **ExternalApiService** – Algemene service die externe API’s aanroept.

### **Repository Layer**
- **TripTopRepository** – Beheert de opslag en ophalen van reisgegevens.
- **UserRepository** – Beheert gebruikersgegevens en bijbehorende authenticatie-informatie.

### **Utility & Config**
- **TripTopMapper** – Converteert externe API-responses naar interne modellen en andersom.
- **TripTopConfigProvider** – Beheert API-URL’s en configuratie-instellingen.

# Lijst:

| Interface                  | Methode                                     | Parameters                                           | Returnwaarde                     | Beschrijving |
|----------------------------|---------------------------------------------|------------------------------------------------------|----------------------------------|--------------|
| **AuthProviderService**     | `authenticateUser`                         | `String token`                                      | `UserDetails`                   | Valideert de OAuth2-token en geeft gebruikersgegevens terug. |
|                            | `getCurrentUser`                           | `String token`                                      | `UserDetails`                   | Haalt de ingelogde gebruiker op aan de hand van de token. |
|                            | `refreshToken`                             | `String refreshToken`                               | `String newToken`               | Ververst het OAuth2-token als het verlopen is. |
|                            | `logoutUser`                               | `String token`                                      | `void`                          | Invalideert het token en logt de gebruiker uit. |
| **MapsService**            | `getRoute`                                 | `String startLocation, String endLocation`         | `RouteDetails`                  | Haalt routegegevens op tussen twee locaties. |
|                            | `getPlaceDetails`                          | `String placeId`                                    | `PlaceDetails`                  | Haalt details op over een specifieke locatie. |
|                            | `searchPlaces`                             | `String query, String location, int radius`        | `List<PlaceDetails>`            | Zoekt locaties op basis van zoekopdracht en locatie. |
|                            | `getNearbyPublicTransport`                 | `String location, int radius`                      | `List<TransportOption>`         | Geeft openbaar vervoer-opties in de buurt. |
| **ExternalApiService**      | `callApi`                                  | `String endpoint, Map<String, String> params`      | `String responseData`           | Algemene methode om externe API’s aan te roepen. |
| **TripTopService**         | `fetchUserRoute`                           | `String userId, String start, String destination`  | `RouteResponse`                 | Haalt een route op voor een specifieke gebruiker. |
|                            | `saveRouteHistory`                         | `String userId, RouteDetails route`                | `void`                          | Slaat de routegeschiedenis van een gebruiker op. |
| **TripTopRepository**      | `saveRoute`                                | `RouteDetails route`                               | `void`                          | Slaat een route op in de database. |
|                            | `getRecentRoutes`                          | `String userId, int limit`                         | `List<RouteDetails>`            | Haalt de recent bezochte routes van een gebruiker op. |
| **UserRepository**         | `findUserByToken`                          | `String token`                                     | `UserDetails`                   | Zoekt een gebruiker op basis van een token. |
|                            | `saveUser`                                 | `UserDetails user`                                 | `void`                          | Slaat een nieuwe gebruiker op in de database. |
| **TripTopConfigProvider**  | `getGoogleMapsApiKey`                      | `void`                                            | `String apiKey`                 | Haalt de API-sleutel voor Google Maps op. |
|                            | `getIdentityProviderUrl`                   | `void`                                            | `String providerUrl`            | Haalt de URL van de Identity Provider op. |

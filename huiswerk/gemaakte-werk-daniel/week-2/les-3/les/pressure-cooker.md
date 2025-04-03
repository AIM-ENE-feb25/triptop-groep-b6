# Pressure Cooker
**Ontwerpvraag:** Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt?

## Voorbereiding
Lijst van componenten:
- Een externe API (Stripe)
- Service class (PaymentService)
- Een controller (PaymentController)
- Een repository? (PaymentRepository)

## 1. Bepalen van belangrijke componenten
Lijst van componenten:
- Een externe API (Stripe)
    - Verantwoordelijkheid: Het verwerken van een betaling met de gegeven betalingsgegevens
    - Principle: Single responsibility
- Service class (PaymentService)
    - Verantwoordelijkheid: Logica in de backend voor het verwerken van betalingen.
    - Principle: Law of demeter
- Een controller (PaymentController)
    - Verantwoordelijkheid: Ontvangen en verwerken van requests. Hoort de bijbehorende logica van de service laag aan te roepen
    - Principle: Information hiding
- Een repository? (PaymentRepository)
    - Verantwoordelijkheid: Slaat betalingsgegevens op in de database.
    - Principle: Single responsibility

## 2. Interfaces beschrijven

| Interfaces              | Methodes       | Parameters              | Return          |
| ----------------------- | -------------- | ----------------------- | --------------- |
| PaymentServiceInterface | isEnoughMoney  | Payment (domein object) | Bool true/false |
|                         |                | currentTaxPercentage    |                 |
|                         |                | Price (domein object)   |                 |
|                         | processPayment | Payment                 | void            |
|                         | checkPayment   | Payment                 | Bool true/false |



## 3. Volgorde van aanroepen

Zie [diagram](dynamic-component-diagram.puml)


# Stripe
Voor betalingssysteem
## Handige Bronnen
- API: https://docs.stripe.com/api

- Test gegevens: https://docs.stripe.com/testing 

## Curl
### Test betaling van 500 gbp
**cURL**
```cmd
curl https://api.stripe.com/v1/payment_intents ^
  -u "sk_test_51R50t1Q9JjyTDaBHH83oxB3T1Bsk5I5zFZ7id3gGnRNlx4Cdml4wT5Go5Ca7HUSbpzeqzLTOhiuQOPzx4I8KPtQa00tKmBe7wr:" ^
  -d amount=500 ^
  -d currency=gbp ^
  -d payment_method=pm_card_visa ^
  -d "payment_method_types[]"=card
```

**Response**
```json
{
  "id": "pi_3R52MrQ9JjyTDaBH1qAzTHIn",
  "object": "payment_intent",
  "amount": 500,
  "amount_capturable": 0,
  "amount_details": {
    "tip": {}
  },
  "amount_received": 0,
  "application": null,
  "application_fee_amount": null,
  "automatic_payment_methods": null,
  "canceled_at": null,
  "cancellation_reason": null,
  "capture_method": "automatic_async",
  "client_secret": "pi_3R52MrQ9JjyTDaBH1qAzTHIn_secret_FtgkNYq35qLAvFCkC3cJLTuZl",
  "confirmation_method": "automatic",
  "created": 1742551197,
  "currency": "gbp",
  "customer": null,
  "description": null,
  "invoice": null,
  "last_payment_error": null,
  "latest_charge": null,
  "livemode": false,
  "metadata": {},
  "next_action": null,
  "on_behalf_of": null,
  "payment_method": "pm_1R52MrQ9JjyTDaBHyopArzUm",
  "payment_method_configuration_details": null,
  "payment_method_options": {
    "card": {
      "installments": null,
      "mandate_options": null,
      "network": null,
      "request_three_d_secure": "automatic"
    }
  },
  "payment_method_types": [
    "card"
  ],
  "processing": null,
  "receipt_email": null,
  "review": null,
  "setup_future_usage": null,
  "shipping": null,
  "source": null,
  "statement_descriptor": null,
  "statement_descriptor_suffix": null,
  "status": "requires_confirmation",
  "transfer_data": null,
  "transfer_group": null
}
```


### Authentication

**cURL**
```cmd
curl https://api.stripe.com/v1/charges ^
  -u sk_test_51R50t1Q9JjyTDaBHH83oxB3T1Bsk5I5zFZ7id3gGnRNlx4Cdml4wT5Go5Ca7HUSbpzeqzLTOhiuQOPzx4I8KPtQa00tKmBe7wr:
# The colon prevents curl from asking for a password.
```
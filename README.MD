# Basic Currencies API
Use https://api.exchangerate.host service.

### Request Sample
#### GET
```shell
curl --request GET \
  --url 'http://localhost:8080/api/currencies?startDate=2023-02-20&endDate=2023-02-28&baseCurrency=USD&targetCurrencies=EUR%2CGBP%2CUZS&amount=2'
```
#### POST
```shell
curl --request POST \
--url http://localhost:8080/api/currencies \
--header 'Content-Type: application/json' \
--data '{
"startDate": "2023-02-20",
"endDate": "2023-02-28",
"baseCurrency": "USD",
"targetCurrencies": ["EUR", "GBP", "UZS"],
"amount": 2
}'
```
#### Parameters
- startDate - required. Starting date of currencies data
- endDate - required. End date of currencies data
- baseCurrency - optional(default - USD). Base currency for rates
- targetCurrencies - optional(default - all). Target currencies to show in exchange rates
- amount - optional(default - 1.0). Amount used in calculation of exchange rates


#### Response Sample

```json
{
  "startDate": "2023-02-20",
  "endDate": "2023-02-28",
  "baseCurrency": "USD",
  "targetCurrencies": [
    "EUR",
    "GBP",
    "UZS"
  ],
  "amount": 2.0,
  "rates": {
    "2023-02-20": {
      "EUR": 1.871425,
      "GBP": 1.660602,
      "UZS": 22621.706818
    },
    "2023-02-21": {
      "EUR": 1.877982,
      "GBP": 1.651281,
      "UZS": 22631.424728
    },
    "2023-02-22": {
      "EUR": 1.885458,
      "GBP": 1.660589,
      "UZS": 22608.461535
    },
    "2023-02-23": {
      "EUR": 1.886295,
      "GBP": 1.663726,
      "UZS": 22603.818421
    },
    "2023-02-24": {
      "EUR": 1.89511,
      "GBP": 1.673093,
      "UZS": 22702.74643
    },
    "2023-02-25": {
      "EUR": 1.891002,
      "GBP": 1.67449,
      "UZS": 22674.023505
    },
    "2023-02-26": {
      "EUR": 1.896206,
      "GBP": 1.67419,
      "UZS": 22673.590573
    },
    "2023-02-27": {
      "EUR": 1.886099,
      "GBP": 1.6595,
      "UZS": 22685.045405
    },
    "2023-02-28": {
      "EUR": 1.885432,
      "GBP": 1.65953,
      "UZS": 22689.556218
    }
  }
}
```
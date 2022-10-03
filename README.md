# customer-rewards-system

# Sample Request and Response
**URL -> http://localhost:8080/api/rewards/

**HTTP METHOD -> POST

**Request -> 
```json
{
    "transactions":[
        {
            "customerName":"John",
            "month":"April",
            "billAmount":120
        },
         {
            "customerName":"John",
            "month":"April",
            "billAmount":120
        },
         {
            "customerName":"John",
            "month":"May",
            "billAmount":120
        },
        {
            "customerName":"Smith",
            "month":"May",
            "billAmount":100
        }
    ]
}
```
**Response
```json
{
    "customerRewards": [
        {
            "customerName": "Smith",
            "monthlyRewards": [
                {
                    "amountSpent": 100,
                    "points": 2,
                    "month": "May"
                }
            ],
            "totalRewardsPoints": 2
        },
        {
            "customerName": "John",
            "monthlyRewards": [
                {
                    "amountSpent": 120,
                    "points": 3,
                    "month": "May"
                },
                {
                    "amountSpent": 240,
                    "points": 6,
                    "month": "April"
                }
            ],
            "totalRewardsPoints": 9
        }
    ]
}
```

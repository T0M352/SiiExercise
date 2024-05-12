# 1. Create a new product
URL(POST): http://localhost:8080/api/addProduct

JSON body:
```JSON
{
    "name": "testProduct",
    "description": "desc",
    "regularPrice": 2000,
    "currency": "PLN"
}
```

# 2. Get all products
URL(GET): http://localhost:8080/api/getAllProducts

# 3. Update product data
URL(PUT): http://localhost:8080/api/updateProduct

JSON body:
```JSON
{
    "name": "testProduct",
    "description": "editedDesc",
    "regularPrice": 2000,
    "currency": "PLN"
}
```
# 4. Create a new promo code.
URL(POST): http://localhost:8080/api/createPromocode

JSON body:
```JSON
{
    "promoCode": "ASDDFAAA",
    "expirationDate": "2024-05-30",
    "discountAmount": 250,
    "currency": "PLN",
    "maxUsages": 10
}
```

# 5. Get all promo codes.
URL(GET): http://localhost:8080/api/getAllCodes

# 6. Get one promo code's details by providing the promo code. The detail should also contain the number of usages.
URL(GET): http://localhost:8080/api/getPromocodeDetails/ASDDFAAA

# 7. Get the discount price by providing a product and a promo code.
URL(POST): http://localhost:8080/api/calculateDiscount

JSON BODY:
```JSON
{
    "promoCode": "ASDDFAAA",
    "productName": "testProduct"
}
```
# 8. Simulate purchase
URL(POST): http://localhost:8080/api/purchase

JSON BODY:
```JSON
{
    "promoCode": "ASDDFAAA",
    "productName": "testProduct"
}
```
# 9. [Optional] A sales report: number of purchases and total value by currency 
URL(GET): http://localhost:8080/api/salesRaport





# Testing remaining functionality:

# 10. If promo code doesn't exist, return HTTP 404.
URL(POST): http://localhost:8080/api/calculateDiscount

JSON BODY:
```JSON
{
    "promoCode": "AAAAAAA",
    "productName": "testProduct"
}
```

# 11. If promo code expired, return the regular price with warning
URL(POST): http://localhost:8080/api/createPromocode

JSON body:
```JSON
{
    "promoCode": "ABC",
    "expirationDate": "2023-05-30",
    "discountAmount": 250,
    "currency": "PLN",
    "maxUsages": 10
}
```
URL(POST): http://localhost:8080/api/calculateDiscount

JSON BODY:
```JSON
{
    "promoCode": "ABC",
    "productName": "testProduct"
}
```

# 12.If currencies doesn't match, return the regular price with warning.

URL(POST): http://localhost:8080/api/createPromocode

JSON body:
```JSON
{
    "promoCode": "ABCeur",
    "expirationDate": "2024-05-30",
    "discountAmount": 250,
    "currency": "EUR",
    "maxUsages": 10
}
```
URL(POST): http://localhost:8080/api/calculateDiscount

JSON BODY:
```JSON
{
    "promoCode": "ABCeur",
    "productName": "testProduct"
}
```
# 13. If the maximal number of usages was achieved, return the regular price with warning.
URL(POST): http://localhost:8080/api/createPromocode

JSON body:
```JSON
{
    "promoCode": "ABCmax",
    "expirationDate": "2024-05-30",
    "discountAmount": 250,
    "currency": "PLN",
    "maxUsages": 0
}
```
URL(POST): http://localhost:8080/api/calculateDiscount

JSON BODY:
```JSON
{
    "promoCode": "ABCmax",
    "productName": "testProduct"
}
```
# 14.[Optional] Implement a second type of promo code based on percentage.

(Discount amount on object type 1 is the percentage of discount)

URL(POST): http://localhost:8080/api/createPromocode

JSON body:
```JSON
{
    "promoCode": "ABCproc",
    "expirationDate": "2024-05-30",
    "discountAmount": 50,
    "currency": "PLN",
    "maxUsages": 10,
    "type": 1
}
```

URL(POST): http://localhost:8080/api/calculateDiscount
JSON BODY:
```JSON
{
    "promoCode": "ABCproc",
    "productName": "testProduct"
}
```

# Description
     
This is a simple API documentation from the JAVA Course.

Here we have the basic CRUD endpoints and further additional.
In this API, you can create your own account with also IBAN to make (backlog) international transference.

**NOTE:** There is a configuration for security that check the transfer limit by account while doing any financial action when the period it out of 8AM ~ 8PM.

# Functions
Once created a user, you can retrieve the account information by doing a search by ID.
Also, you can update the name or the account transfer limit and delete this account.

You can deposit, withdraw money, do a transference and check one or all statements created.

**BACKLOG:**
- Create a validator to check the account if it already exists by account number, IBAN and further details
- Handle other exceptions
- Add transference enum type when do transference
- Setup LOG to registry the errors
- Create a hierarchy access on the endpoints

Technologies used:
- Swagger (Open API)
- Springboot 3.2
- JAVA 17
- Maven
- Heroku (Production)
- JawsDB Maria (Production)
- MySQL (Development)
- MySQL Workbench (Development)
- H2 DB (local)

# Swagger:
https://app.swaggerhub.com/apis-docs/caiquesandrade/BankAPI/1.0.0#/

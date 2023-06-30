# lending-repayment-api
## About

This is a Lending repayment API that allows customers to:

- Request for loans
- Repay Loans(Partial/full repayments)
- Clear Defaulted/old loans that have been there for more than 6 months

This app is built using Java(Spring Boot)


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

- Java 17 sdk

- Postman

### Installing

A step by step series of examples that tell you how to get a development environment running

- Clone the project repository


HTTPS: `git clone https://github.com/BILLthebuilder/lending-repayment-api.git`

SSH: `git clone git@github.com:BILLthebuilder/lending-repayment-api.git`

- Change the directory

`cd loan-repayment-api`

- To compile a local build

```bash
./mvnw clean compile package
```

- To run a regular development build

```bash
java -jar /your_clone_directory/loan-repayment-api/target/0.0.1.jar
```

### The Working API Endpoints

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/5176138-0a21acd7-7c76-49d6-a409-00f7a1aa5b52?action=collection%2Ffork&collection-url=entityId%3D5176138-0a21acd7-7c76-49d6-a409-00f7a1aa5b52%26entityType%3Dcollection%26workspaceId%3Df99137e8-f0b4-4850-8b9e-2fa166538946)

#### Auth Endpoints(Coming soon)

| Request | Endpoint              | Function                |
|---------|-----------------------|-------------------------|
| POST    | `/api/v1/auth/signup` | Register a new user     |
| POST    | `/api/v1/auth/signin` | Login a registered user |

#### Loan endpoints

| Request | Endpoint                | Function                  |
|---------|-------------------------|---------------------------|
| POST    | `/api/v1/loans/request` | Create a loan request     |
| PUT     | `/api/v1/loans/topup`   | Topup an existing loan    |
| PUT     | `/api/v1/loans/repay`   | Repay a loan              |
| DELETE  | `/api/v1/loans/clear`   | Clear old/defaulted loans |
|         | `/api/v1/loans/x`       |                           |
|         | `/api/v1/loans/x`       |                           |
|         | `/api/v1/loans/x`       |                           |
|         | `/api/v1/loans/x`       |                           |



## Running the tests

```bash
./mvnw test
```

## Deployment

[//]: # (- The API is deployed [here]&#40;https://automart-api.herokuapp.com/docs&#41; on heroku)
- Coming soon
## Built With

- [Spring](https://spring.io) - The web framework used

## Versioning

- Version 1(v1) of the API 

- To view the swagger docs visit the below url after loading the project locally. 
- Remember to change the port accordingly

```bash
http://localhost:8081/swagger-ui/index.html
```

## Authors

### Bill Kariri

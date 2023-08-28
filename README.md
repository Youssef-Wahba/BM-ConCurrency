# ConCurrency exhange app 🚀🤑

## **Overview 💼**

The currency conversion app aims to provide users with real-time and accurate currency
conversion rates. It will support a wide range of currencies and offer a
user friendly interface. The app will be developed for multiple platforms, including web, Android,
and iOS.

## **Deployed via AWS** [BM-ConCurrency](http://ec2-18-134-206-213.eu-west-2.compute.amazonaws.com)

![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

# **Service Functionality** 🧠

- A Backend Rest-API designed using MVC Design Architecture.
- Allow users to convert between different currencies.
- Fetch real-time exchange rates.
- Regularly update the rates to reflect the most recent changes in the currency market..
- Mark specific currencies as their favorites for quick access.
- Error and Exception handling.
- Documentation using swagger and openai
- Validation layer
- Caching with redis
- Unit testing

```
|   .gitignore
|   mvnw
|   mvnw.cmd
|   pom.xml
|   README.md
|
+---.github
|   \---workflows
|           maven.yml
|
+---.idea
|       .gitignore
|       compiler.xml
|       encodings.xml
|       jarRepositories.xml
|       misc.xml
|       uiDesigner.xml
|       vcs.xml
|       workspace.xml
|
+---.mvn
|   \---wrapper
|           maven-wrapper.jar
|           maven-wrapper.properties
|
+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---bm
|   |   |           \---concurrency
|   |   |               |   ConCurrencyApplication.java
|   |   |               |
|   |   |               +---client
|   |   |               |       ExchangeRateClient.java
|   |   |               |
|   |   |               +---constants
|   |   |               |   \---enums
|   |   |               |           Currency.java
|   |   |               |
|   |   |               +---controllers
|   |   |               |       CurrencyController.java
|   |   |               |
|   |   |               +---exception
|   |   |               |       CurrencyApiException.java
|   |   |               |       GlobalExceptionHandler.java
|   |   |               |       ResourceNotFoundException.java
|   |   |               |
|   |   |               +---payload
|   |   |               |   +---DTOs
|   |   |               |   |       CompareDto.java
|   |   |               |   |       ConvertDTO.java
|   |   |               |   |       CurrencyDTO.java
|   |   |               |   |
|   |   |               |   +---error
|   |   |               |   |       ErrorDetailsDTO.java
|   |   |               |   |       ValidationErrorDTO.java
|   |   |               |   |
|   |   |               |   \---response
|   |   |               |           CompareResponse.java
|   |   |               |           ConversionResponse.java
|   |   |               |           CurrencyListResponse.java
|   |   |               |           ExchangeRateResponse.java
|   |   |               |
|   |   |               \---service
|   |   |                   |   IConcurrencyService.java
|   |   |                   |
|   |   |                   \---serviceImp
|   |   |                           ConcurrencyService.java
|   |   |
|   |   \---resources
|   |           application-dev.properties
|   |           application-prod.properties
|   |           application.properties
|   |
|   \---test
|       \---java
|           \---com
|               \---bm
|                   \---concurrency
|                           ConCurrencyApplicationTests.java
|
\---target
    +---classes
    |   |   application-dev.properties
    |   |   application-prod.properties
    |   |   application.properties
    |   |
    |   \---com
    |       \---bm
    |           \---concurrency
    |               |   ConCurrencyApplication.class
    |               |
    |               +---client
    |               |       ExchangeRateClient.class
    |               |
    |               +---constants
    |               |   \---enums
    |               |           Currency.class
    |               |
    |               +---controllers
    |               |       CurrencyController.class
    |               |
    |               +---exception
    |               |       CurrencyApiException.class
    |               |       GlobalExceptionHandler.class
    |               |       ResourceNotFoundException.class
    |               |
    |               +---payload
    |               |   +---DTOs
    |               |   |       CompareDto.class
    |               |   |       ConvertDTO.class
    |               |   |       CurrencyDTO.class
    |               |   |
    |               |   +---error
    |               |   |       ErrorDetailsDTO.class
    |               |   |       ValidationErrorDTO.class
    |               |   |
    |               |   \---response
    |               |           CompareResponse.class
    |               |           ConversionResponse.class
    |               |           CurrencyListResponse.class
    |               |           ExchangeRateResponse.class
    |               |
    |               \---service
    |                   |   IConcurrencyService.class
    |                   |
    |                   \---serviceImp
    |                           ConcurrencyService.class
    |
    \---generated-sources
        \---annotations
```

# **Tech Stack ⚡**

- Programming Language: Java 8
- Backend Framework: Spring Boot v3.1.2
- Deployment: AWS
- API Documentation: Swagger via OpenApi 3.0
- Caching: Redis

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

[![Maven](https://badgen.net/badge/icon/maven?icon=maven&label)](https://https://maven.apache.org/)

![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)

# **Features to add in the future 💭**

- Supporting more currencies

# API Documentation 📝 via [Swagger](http://ec2-18-134-206-213.eu-west-2.compute.amazonaws.com/swagger-ui/index.html)

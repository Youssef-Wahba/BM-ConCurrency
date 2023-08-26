# ConCurrency exhange app 🤑🤑

## **Deployed via AWS** [BM-ConCurrency](https://shopping-app-bm.herokuapp.com)

![Heroku](https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white)

# **Service Functionality** 🧠

- A Backend Rest-API designed using MVC Design Architecture.
- allow users to convert between different currencies.
- fetch real-time exchange rates.
- regularly update the rates to reflect the most recent changes in the currency market..
- mark specific currencies as their favorites for quick access.
- Error and Exception handling.
- Supporting logging in different layers like Controller and Service.

```
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── bm
    │   │           ├── config
    │   │           │   └── SecurityConfig.java
    │   │           ├── controller
    │   │           │   ├── CategoryController.java
    │   │           │   ├── LoginController.java
    │   │           │   ├── ProductController.java
    │   │           │   ├── ProfileController.java
    │   │           │   └── RegistrationController.java
    │   │           ├── dto
    │   │           │   ├── CustomUserDetails.java
    │   │           │   ├── LoginRequest.java
    │   │           │   ├── LoginResponse.java
    │   │           │   ├── ProductDto.java
    │   │           │   └── UpdateRequest.java
    │   │           ├── entity
    │   │           │   ├── Category.java
    │   │           │   ├── Product.java
    │   │           │   └── User.java
    │   │           ├── exception
    │   │           │   ├── ErrorResponse.java
    │   │           │   └── Errors.java
    │   │           ├── filter
    │   │           │   └── JwtFilter.java
    │   │           ├── repository
    │   │           │   ├── CategoryRepository.java
    │   │           │   ├── ProductRepository.java
    │   │           │   └── UserRepository.java
    │   │           ├── service
    │   │           │   ├── CategoryService.java
    │   │           │   ├── impl
    │   │           │   │   ├── CategoryServiceImpl.java
    │   │           │   │   ├── LoginServiceImpl.java
    │   │           │   │   ├── ProductServiceImpl.java
    │   │           │   │   ├── ProfileServiceImpl.java
    │   │           │   │   ├── RegistrationServiceImpl.java
    │   │           │   │   └── UserServiceImpl.java
    │   │           │   ├── LoginService.java
    │   │           │   ├── ProductService.java
    │   │           │   ├── ProfileService.java
    │   │           │   ├── RegistrationService.java
    │   │           │   └── UserService.java
    │   │           ├── ShoppingApplication.java
    │   │           └── util
    │   │               └── JwtUtil.java
    │   └── resources
    │       └── application.properties
```

# **Tech Stack ⚡**

- Programming Language: Java 8
- Backend Framework: Spring Boot v3.1.2
- Deployment: AWS
- API Documentation: Swagger via OpenApi 3.0
- Logger: SLF4J

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

[![Maven](https://badgen.net/badge/icon/maven?icon=maven&label)](https://https://maven.apache.org/)

![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

# **Features to add in the future 💭**

- Supporting more currencies

# API Documentation 📝 via [|Swagger|](https://shopping-app-bm.herokuapp.com/swagger-ui/index.html#/)

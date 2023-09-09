# job-centre-backend 

job-centre-backend  - это серверная часть приложения, предназначенного для управления вакансиями и заявками на вакансии. Сервер предоставляет возможность регистрации как безработным, так и работодателям, и обеспечивает взаимодействие между ними.

## Описание

    Регистрация и авторизация: Пользователи могут зарегистрироваться как безработные соискатели или работодатели. У каждой категории пользователей есть свои права и функции.

    Поиск работы: Безработные пользователи могут просматривать доступные вакансии, настраивать фильтры для поиска подходящих вакансий и подавать заявки на них.

    Найм персонала: Работодатели могут создавать вакансии, редактировать их описание, а также просматривать заявки от потенциальных соискателей.

### Возможности безработных:

- Регистрация аккаунта.
- Настройка фильтров для поиска подходящих вакансий.
- Подача заявок на вакансии.
- Просмотр статуса заявок и общение с работодателями.

### Возможности работодателей:

- Регистрация аккаунта.
- Создание и редактирование вакансий.
- Ответ на заявки безработных.
- Управление списком вакансий и кандидатами.

### Зависимости

Проект использует следующие зависимости:

- [org.hibernate.common:hibernate-commons-annotations:6.0.6.Final](https://mvnrepository.com/artifact/org.hibernate.common/hibernate-commons-annotations/6.0.6.Final) - Для работы с Hibernate Annotations.
- [org.springframework.boot:spring-boot-starter-validation:3.1.1](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation/3.1.1) - Набор стартеров Spring Boot для валидации данных.
- [org.springframework.boot:spring-boot-starter-data-redis](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-redis) - Spring Boot стартер для работы с Redis.
- [org.springframework.boot:spring-boot-starter-security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security) - Spring Boot стартер для обеспечения безопасности.
- [org.springframework.boot:spring-boot-starter-web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web) - Spring Boot стартер для веб-приложений.
- [org.springframework.boot:spring-boot-starter-data-jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa) - Spring Boot стартер для работы с JPA (Java Persistence API).
- [io.jsonwebtoken:jjwt-api:0.11.5](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api/0.11.5) - API для работы с JSON Web Tokens (JWT).
- [io.jsonwebtoken:jjwt-impl:0.11.5](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl/0.11.5) - Реализация JSON Web Tokens (JWT).
- [io.jsonwebtoken:jjwt-jackson:0.11.5](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson/0.11.5) - Поддержка JSON Web Tokens (JWT) для Jackson.
- [org.projectlombok:lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok) - Библиотека Lombok для упрощения Java кода.
- [org.springframework.boot:spring-boot-devtools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools) - Spring Boot DevTools для разработки.
- [org.postgresql:postgresql](https://mvnrepository.com/artifact/org.postgresql/postgresql) - Драйвер PostgreSQL для базы данных.
- [org.springframework.boot:spring-boot-configuration-processor](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor) - Процессор конфигурации Spring Boot.
- [org.projectlombok:lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok) - Библиотека Lombok для упрощения Java кода.
- [org.springframework.boot:spring-boot-starter-test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test) - Spring Boot стартер для модульного тестирования.
- [org.springframework.security:spring-security-test](https://mvnrepository.com/artifact/org.springframework.security/spring-security-test) - Модульное тестирование Spring Security.

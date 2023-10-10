# job-centre-backend

job-centre-backend is the server-side component of an application designed for managing job vacancies and job applications. The server provides registration capabilities for both job seekers and employers and facilitates interaction between them.

## Description

Registration and Authentication: Users can register as either unemployed job seekers or employers, each with their own rights and functions.

Job Search: Unemployed users can view available job vacancies, configure filters to find suitable job listings, and submit applications for them.

Hiring Personnel: Employers can create job vacancies, edit their descriptions, and review applications from potential candidates.

### Features for Job Seekers:

- Account registration.
- Configuration of filters to search for suitable job vacancies.
- Submission of job applications.
- Viewing application status and communication with employers.
- Editing passport data.
- Secure storage of confidential information.
- Saving a working session.

### Features for Employers:

- Account registration.
- Creation and editing of job vacancies.
- Responding to applications from unemployed individuals.
- Managing the list of job vacancies and candidates.
- Convenient editing of vacancy visibility.

### Dependencies

The project uses the following dependencies:

- [org.hibernate.common:hibernate-commons-annotations:6.0.6.Final](https://mvnrepository.com/artifact/org.hibernate.common/hibernate-commons-annotations/6.0.6.Final) - For working with Hibernate Annotations.
- [org.springframework.boot:spring-boot-starter-validation:3.1.1](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation/3.1.1) - A set of Spring Boot starters for data validation.
- [org.springframework.boot:spring-boot-starter-data-redis](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-redis) - Spring Boot starter for working with Redis.
- [org.springframework.boot:spring-boot-starter-security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security) - Spring Boot starter for security features.
- [org.springframework.boot:spring-boot-starter-web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web) - Spring Boot starter for web applications.
- [org.springframework.boot:spring-boot-starter-data-jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa) - Spring Boot starter for working with JPA (Java Persistence API).
- [io.jsonwebtoken:jjwt-api:0.11.5](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api/0.11.5) - API for working with JSON Web Tokens (JWT).
- [io.jsonwebtoken:jjwt-impl:0.11.5](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl/0.11.5) - Implementation of JSON Web Tokens (JWT).
- [io.jsonwebtoken:jjwt-jackson:0.11.5](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson/0.11.5) - Support for JSON Web Tokens (JWT) with Jackson.
- [org.projectLombok:lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok) - Lombok library for simplifying Java code.
- [org.springframework.boot:spring-boot-devtools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools) - Spring Boot DevTools for development.
- [org.postgresql:postgresql](https://mvnrepository.com/artifact/org.postgresql/postgresql) - PostgreSQL database driver.
- [org.springframework.boot:spring-boot-configuration-processor](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor) - Spring Boot configuration processor.
- [org.springframework.boot:spring-boot-starter-test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test) - Spring Boot starter for modular testing.
- [org.springframework.security:spring-security-test](https://mvnrepository.com/artifact/org.springframework.security/spring-security-test) - Modular testing for Spring Security.

## Contributing

Contributions to the development of this application are welcome. If you would like to contribute, please follow the [Contribution Guidelines](https://github.com/DarlingInSteam/job-centre-backend/blob/master/CODE_OF_CONDUCT.md).

## License

This project is licensed under the [MIT License](https://github.com/DarlingInSteam/job-centre-backend/blob/master/LICENSE).


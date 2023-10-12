-- Создание ENUM для уровней образования на английском с комментариями на русском
CREATE TYPE "education_level" AS ENUM (
    'PRIMARY', -- НАЧАЛЬНОЕ ОБЩЕЕ ОБРАЗОВАНИЕ
    'BASIC', -- ОСНОВНОЕ ОБЩЕЕ ОБРАЗОВАНИЕ
    'SECONDARY', -- СРЕДНЕЕ ОБЩЕЕ ОБРАЗОВАНИЕ
    'SECONDARY_PROFESSIONAL_QUALIFIED', -- СРЕДНЕЕ ПРОФЕССИОНАЛЬНОЕ ОБРАЗОВАНИЕ ПО ПРОГРАММАМ ПОДГОТОВКИ КВАЛИФИЦИРОВАННЫХ РАБОЧИХ (СЛУЖАЩИХ)
    'SECONDARY_PROFESSIONAL_SPECIALIST', -- СРЕДНЕЕ ПРОФЕССИОНАЛЬНОЕ ОБРАЗОВАНИЕ ПО ПРОГРАММАМ ПОДГОТОВКИ СПЕЦИАЛИСТОВ СРЕДНЕГО ЗВЕНА
    'BACHELOR', -- ВЫСШЕЕ ОБРАЗОВАНИЕ – БАКАЛАВРИАТ
    'MASTER', -- ВЫСШЕЕ ОБРАЗОВАНИЕ – СПЕЦИАЛИТЕТ ИЛИ МАГИСТРАТУРА
    'POSTGRADUATE' -- ВЫСШЕЕ ОБРАЗОВАНИЕ – ПОДГОТОВКА КАДРОВ ВЫСШЕЙ КВАЛИФИКАЦИИ ПО ПРОГРАММАМ ПОДГОТОВКИ НАУЧНО-ПЕДАГОГИЧЕСКИХ КАДРОВ В АСПИРАНТУРЕ (АДЪЮНКТУРЕ), ПО ПРОГРАММАМ ОРДИНАТУРЫ, ПО ПРОГРАММАМ АССИСТЕНТУРЫ-СТАЖИРОВКИ
    );

CREATE TYPE "role" AS ENUM (
    'UNEMPLOYED',
    'EMPLOYED'
    );

CREATE TABLE "users" (
    "id" BIGSERIAL PRIMARY KEY,
    "username" varchar(20),
    "phone" VARCHAR(20),
    "password" VARCHAR(100),
    "role" role
);

CREATE TABLE "password_reset_tokens" (
    "id" BIGSERIAL PRIMARY KEY,
    "user_id" BIGINT,
    "token"  varchar(100),
    "expiry_date" TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE "refresh_tokens" (
    "id" BIGSERIAL PRIMARY KEY,
    "user_id" BIGINT,
    "token" varchar UNIQUE,
    "expiry_date" TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE "passports" (
    "id" SERIAL PRIMARY KEY,
    "passport_number" VARCHAR(20) UNIQUE NOT NULL,
    "passport_issue_date" VARCHAR(255),
    "date_of_birth" VARCHAR(255),
    "passport_issued_by" VARCHAR(255),
    "address" TEXT,
    "photo" TEXT
);

CREATE TABLE "unemployed" (
    "id" SERIAL PRIMARY KEY,
    "passport_id" BIGINT,
    "user_id" BIGINT,
    "full_name" VARCHAR(255) NOT NULL,
    "age" INT,
    "education_level" education_level,
    "educational_institution" VARCHAR(255),
    "education_document_data" VARCHAR(255),
    "specialty" VARCHAR(255),
    "work_experience" INT,
    "registration_date" TIMESTAMP WITHOUT TIME ZONE,
    "about_me" TEXT
);

CREATE TABLE "ability" (
    "id" SERIAL PRIMARY KEY,
    "text" VARCHAR(40) UNIQUE
);

CREATE TABLE "unemployed_ability" (
    "ability_id" BIGINT,
    "unemployed_id" BIGINT
);

CREATE TABLE "employers" (
    "id" SERIAL PRIMARY KEY,
    "user_id" BIGINT,
    "employer_name" VARCHAR(255) NOT NULL,
    "address" TEXT,
    "about_company" TEXT,
    "company_photo" VARCHAR(255)
);

CREATE TABLE "employer_job_vacancies" (
    "employer_id" BIGINT,
    "vacancy_id" BIGINT
);

CREATE TABLE "job_requirements" (
    "id" SERIAL PRIMARY KEY,
    "education_level" education_level,
    "age_range" INT[],
    "work_experience" INT
);

CREATE TABLE "job_vacancies" (
    "id" SERIAL PRIMARY KEY,
    "job_type" VARCHAR(255),
    "job_title" VARCHAR(255) NOT NULL,
    "salary" INT,
    "requirements_id" INT,
    "archived" BOOLEAN DEFAULT FALSE
);

CREATE TABLE "vacancies_apply_unemployed" (
    "vacancy_id" BIGINT,
    "unemployed_id" BIGINT
);

CREATE TABLE "employment_history" (
    "id" SERIAL PRIMARY KEY,
    "unemployed_id" INT,
    "job_vacancy_id" INT,
    "employer_id" INT,
    "employment_date" TIMESTAMP WITHOUT TIME ZONE,
    "termination_date" TIMESTAMP WITHOUT TIME ZONE,
    "termination_reason" VARCHAR(255),
    "archived" BOOLEAN DEFAULT FALSE
);

-- TODO
CREATE TABLE "job_title" (
    "id" SERIAL PRIMARY KEY
);

-- TODO
CREATE TABLE "specialist" (
    "id" SERIAL PRIMARY KEY
);

ALTER TABLE "unemployed_ability" ADD FOREIGN KEY ("unemployed_id") REFERENCES "unemployed" ("id");

ALTER TABLE "unemployed_ability" ADD FOREIGN KEY ("ability_id") REFERENCES "ability" ("id");

ALTER TABLE "refresh_tokens" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "vacancies_apply_unemployed" ADD FOREIGN KEY ("vacancy_id") REFERENCES "job_vacancies" ("id");

ALTER TABLE "vacancies_apply_unemployed" ADD FOREIGN KEY ("unemployed_id") REFERENCES "unemployed" ("id");

ALTER TABLE "employer_job_vacancies" ADD FOREIGN KEY ("employer_id") REFERENCES "employers" ("id");

ALTER TABLE "employer_job_vacancies" ADD FOREIGN KEY ("vacancy_id") REFERENCES "job_vacancies" ("id");

ALTER TABLE "password_reset_tokens" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "job_vacancies" ADD FOREIGN KEY ("requirements_id") REFERENCES "job_requirements" ("id");

ALTER TABLE "unemployed" ADD FOREIGN KEY ("passport_id") REFERENCES "passports" ("id");

ALTER TABLE "employment_history" ADD FOREIGN KEY ("unemployed_id") REFERENCES "unemployed" ("id");

ALTER TABLE "employment_history" ADD FOREIGN KEY ("job_vacancy_id") REFERENCES "job_vacancies" ("id");

ALTER TABLE "employment_history" ADD FOREIGN KEY ("employer_id") REFERENCES "employers" ("id");
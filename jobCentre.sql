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
    "id" SERIAL PRIMARY KEY,
    "phone" VARCHAR(20),
    "password" VARCHAR(100),
    "role" role
);

CREATE TABLE "unemployed" (
    "id" SERIAL PRIMARY KEY,
    "user_id" INT,
    "full_name" VARCHAR(255) NOT NULL,
    "age" INT,
    "passport_number" VARCHAR(20) UNIQUE NOT NULL,
    "passport_issue_date" TIMESTAMP WITHOUT TIME ZONE,
    "passport_issued_by" VARCHAR(255),
    "address" TEXT,
    "photo" TEXT,
    "education_level" education_level,
    "educational_institution" VARCHAR(255),
    "education_document_data" VARCHAR(255),
    "specialty" VARCHAR(255),
    "work_experience" INT,
    "registration_date" TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE "employers" (
     "id" SERIAL PRIMARY KEY,
     "user_id" INT,
     "employer_name" VARCHAR(255) NOT NULL,
     "address" TEXT
);

CREATE TABLE "job_requirements" (
    "id" SERIAL PRIMARY KEY,
    "education_level" education_level,
    "age_range" INT[],
    "work_experience" INT,
    "job_vacancy_id" INT
);

CREATE TABLE "job_vacancies" (
     "id" SERIAL PRIMARY KEY,
     "job_type" VARCHAR(255),
     "job_title" VARCHAR(255) NOT NULL,
     "employer_id" INT,
     "salary" INT,
     "requirements_id" INT,
     "archived" BOOLEAN DEFAULT FALSE
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

ALTER TABLE "job_requirements" ADD FOREIGN KEY ("job_vacancy_id") REFERENCES "job_vacancies" ("id");

ALTER TABLE "job_vacancies" ADD FOREIGN KEY ("employer_id") REFERENCES "employers" ("id");

ALTER TABLE "job_vacancies" ADD FOREIGN KEY ("requirements_id") REFERENCES "job_requirements" ("id");

ALTER TABLE "employment_history" ADD FOREIGN KEY ("unemployed_id") REFERENCES "unemployed" ("id");

ALTER TABLE "employment_history" ADD FOREIGN KEY ("job_vacancy_id") REFERENCES "job_vacancies" ("id");

ALTER TABLE "employment_history" ADD FOREIGN KEY ("employer_id") REFERENCES "employers" ("id");
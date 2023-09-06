-- Создание ENUM для уровней образования на английском с комментариями на русском
CREATE TYPE education_level AS ENUM (
    'Primary', -- Начальное общее образование
    'Basic', -- Основное общее образование
    'Secondary', -- Среднее общее образование
    'SecondaryProfessionalQualified', -- Среднее профессиональное образование по программам подготовки квалифицированных рабочих (служащих)
    'SecondaryProfessionalSpecialist', -- Среднее профессиональное образование по программам подготовки специалистов среднего звена
    'Bachelor', -- Высшее образование – бакалавриат
    'Master', -- Высшее образование – специалитет или магистратура
    'Postgraduate' -- Высшее образование – подготовка кадров высшей квалификации по программам подготовки научно-педагогических кадров в аспирантуре (адъюнктуре), по программам ординатуры, по программам ассистентуры-стажировки
);


CREATE TABLE "unemployed" (
    "id" SERIAL PRIMARY KEY,
    "full_name" VARCHAR(255) NOT NULL,
    "age" INT,
    "passport_number" VARCHAR(20) UNIQUE NOT NULL,
    "passport_issue_date" DATE,
    "passport_issued_by" VARCHAR(255),
    "address" TEXT,
    "phone" VARCHAR(20),
    "photo" BYTEA,
    "education_level" education_level,
    "educational_institution" VARCHAR(255),
    "education_document_data" VARCHAR(255),
    "specialty" VARCHAR(255),
    "work_experience" INT,
    "registration_date" DATE
);

CREATE TABLE "employers" (
     "id" SERIAL PRIMARY KEY,
     "employer_name" VARCHAR(255) NOT NULL,
     "address" TEXT,
     "phone" VARCHAR(20)
);

CREATE TABLE "job_requirements" (
    "id" SERIAL PRIMARY KEY,
    "education_level" education_level,
    "age_range" INT[],
    "work_experience" INT,
    "job_type" VARCHAR(255),
    "job_vacancy_id" INT
);

CREATE TABLE "job_vacancies" (
     "id" SERIAL PRIMARY KEY,
     "job_type" VARCHAR(255),
     "job_title" VARCHAR(255) NOT NULL,
     "employer_id" INT,
     "salary" "DECIMAL(10, 2)",
     "requirements_id" INT,
     "archived" BOOLEAN DEFAULT FALSE
);

CREATE TABLE "employment_history" (
    "id" SERIAL PRIMARY KEY,
    "unemployed_id" INT,
    "job_vacancy_id" INT,
    "employer_id" INT,
    "employment_date" DATE,
    "termination_date" DATE,
    "termination_reason" VARCHAR(255),
    "archived" BOOLEAN DEFAULT FALSE
);

ALTER TABLE "job_requirements" ADD FOREIGN KEY ("job_vacancy_id") REFERENCES "job_vacancies" ("id");

ALTER TABLE "job_vacancies" ADD FOREIGN KEY ("employer_id") REFERENCES "employers" ("id");

ALTER TABLE "job_vacancies" ADD FOREIGN KEY ("requirements_id") REFERENCES "job_requirements" ("id");

ALTER TABLE "employment_history" ADD FOREIGN KEY ("unemployed_id") REFERENCES "unemployed" ("id");

ALTER TABLE "employment_history" ADD FOREIGN KEY ("job_vacancy_id") REFERENCES "job_vacancies" ("id");

ALTER TABLE "employment_history" ADD FOREIGN KEY ("employer_id") REFERENCES "employers" ("id");
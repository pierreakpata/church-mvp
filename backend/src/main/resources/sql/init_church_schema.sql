------------------------------------------------------------
-- MEMBERS
------------------------------------------------------------
CREATE TABLE IF NOT EXISTS members (
    id               SERIAL PRIMARY KEY,
    first_name       VARCHAR(100) NOT NULL,
    last_name        VARCHAR(100) NOT NULL,
    gender           VARCHAR(10),
    birth_date       DATE,
    email            VARCHAR(150),
    phone_number     VARCHAR(30),
    address          VARCHAR(255),
    impact_family_id INT, -- FK ajoutée plus tard
    joined_at        DATE DEFAULT CURRENT_DATE,
    status           VARCHAR(50) DEFAULT 'ACTIVE'
);

------------------------------------------------------------
-- IMPACT FAMILIES
------------------------------------------------------------
CREATE TABLE IF NOT EXISTS impact_families (
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(150) NOT NULL,
    supervisor_id  INT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_families_supervisor
        FOREIGN KEY (supervisor_id)
        REFERENCES members(id)
        ON DELETE SET NULL
);

------------------------------------------------------------
-- Ajout de la FK manquante sur members → impact_families
------------------------------------------------------------
ALTER TABLE members
    ADD CONSTRAINT fk_members_family
    FOREIGN KEY (impact_family_id)
    REFERENCES impact_families(id)
    ON DELETE SET NULL;

------------------------------------------------------------
-- DEPARTMENTS
------------------------------------------------------------
CREATE TABLE IF NOT EXISTS departments (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    description TEXT,
    leader_id   INT,

    CONSTRAINT fk_departments_leader
        FOREIGN KEY (leader_id)
        REFERENCES members(id)
        ON DELETE SET NULL
);

------------------------------------------------------------
-- MEMBER_DEPARTMENTS
------------------------------------------------------------
CREATE TABLE IF NOT EXISTS member_departments (
    member_id      INT NOT NULL,
    department_id  INT NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (member_id, department_id),

    CONSTRAINT fk_member_departments_member
        FOREIGN KEY (member_id)
        REFERENCES members(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_member_departments_department
        FOREIGN KEY (department_id)
        REFERENCES departments(id)
        ON DELETE CASCADE
);

------------------------------------------------------------
-- TRAININGS
------------------------------------------------------------
CREATE TABLE IF NOT EXISTS trainings (
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(150) NOT NULL,
    description TEXT,
    start_date  DATE,
    end_date    DATE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

------------------------------------------------------------
-- MEMBER_TRAININGS
------------------------------------------------------------
CREATE TABLE IF NOT EXISTS member_trainings (
    member_id   INT NOT NULL,
    training_id INT NOT NULL,
    completed   BOOLEAN DEFAULT FALSE,

    PRIMARY KEY (member_id, training_id),

    CONSTRAINT fk_member_trainings_member
        FOREIGN KEY (member_id)
        REFERENCES members(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_member_trainings_training
        FOREIGN KEY (training_id)
        REFERENCES trainings(id)
        ON DELETE CASCADE
);

------------------------------------------------------------
-- WORSHIP ATTENDANCE
------------------------------------------------------------
-- NOTE : users vient du schéma global public
-- Si tu veux que les users soient multi-tenant, je te fournis la version adaptée.
------------------------------------------------------------

CREATE TABLE IF NOT EXISTS worship_attendance (
    id             SERIAL PRIMARY KEY,
    service_date   DATE NOT NULL,
    men_count      INT DEFAULT 0,
    women_count    INT DEFAULT 0,
    children_count INT DEFAULT 0,
    total          INT GENERATED ALWAYS AS (men_count + women_count + children_count) STORED,
    created_by     INT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_worship_attendance_user
        FOREIGN KEY (created_by)
        REFERENCES public.users(id)
        ON DELETE SET NULL
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE role (
    id BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(25) NOT NULL UNIQUE,
    description VARCHAR(50) NOT NULL,
    access VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE account (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(500) NOT NULL,
    role_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT fk_account_x_role FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE OR REPLACE FUNCTION update_timestamps()
    RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'UPDATE' THEN
        NEW.updated_at = NOW();
    END IF;
    IF TG_OP = 'INSERT' THEN
        NEW.created_at = NOW();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER account_update_timestamps
    BEFORE INSERT OR UPDATE ON account
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamps();

CREATE TRIGGER role_update_timestamps
    BEFORE INSERT OR UPDATE ON role
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamps();

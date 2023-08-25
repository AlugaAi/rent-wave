CREATE TABLE track_error (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    payload VARCHAR(500) NOT NULL,
    message VARCHAR(250) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TRIGGER track_error_update_timestamps
    BEFORE INSERT OR UPDATE ON track_error
    FOR EACH ROW
EXECUTE FUNCTION update_timestamps();
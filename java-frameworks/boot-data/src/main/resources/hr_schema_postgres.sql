--create tables
BEGIN;

ALTER TABLE employees ADD COLUMN     creation_date      TIMESTAMP  NULL;
ALTER TABLE employees ADD COLUMN     last_modified_date      TIMESTAMP  NULL;

COMMIT;


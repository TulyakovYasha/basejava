CREATE TABLE resume
(
  uuid      CHAR(36) PRIMARY KEY NOT NULL,
  full_name TEXT                 NOT NULL
);

CREATE TABLE contact
(
  id          SERIAL,
  resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        TEXT     NOT NULL,
  value       TEXT     NOT NULL
);
CREATE UNIQUE INDEX contact_uuid_type_index
  ON contact (resume_uuid, type);
create table sections
(
  id            serial   not null
    constraint sections_pk
      primary key,
  uuid_section  char(36) not null
    constraint sections_resume_uuid_fk
      references resume
      on delete cascade,
  section_type  text     not null,
  section_value text     not null
);

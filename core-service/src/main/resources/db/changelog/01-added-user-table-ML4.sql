CREATE TABLE t_user (
  id            TEXT    NOT NULL,
  username      TEXT    NOT NULL,
  password      TEXT    NOT NULL,
  email         TEXT    NOT NULL,
  birth_date    DATE,
  info          TEXT,

  CONSTRAINT pk PRIMARY KEY (id),
  CONSTRAINT username_email_key UNIQUE (username, email)
);
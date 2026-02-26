CREATE TABLE public.users
(
    id    SERIAL  PRIMARY KEY,
    name  TEXT    NOT NULL,
    email TEXT    NOT NULL,
    age   INTEGER NOT NULL,
    created_at  DATE NOT NULL
);



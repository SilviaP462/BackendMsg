
CREATE TABLE IF NOT EXISTS public2.users
(
    id_user bigint NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default",
    token character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id_user),
    CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username)
    );


CREATE TABLE IF NOT EXISTS public2.files
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    pic_byte oid,
    type character varying(255) COLLATE pg_catalog."default",
    id_user bigint,
    CONSTRAINT files_pkey PRIMARY KEY (id),
    CONSTRAINT fk9smkbto3n79wg62ai5gofb12c FOREIGN KEY (id_user)
    REFERENCES public2.users (id_user) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );
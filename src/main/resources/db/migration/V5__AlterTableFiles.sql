
DROP TABLE public2.files;

CREATE TABLE IF NOT EXISTS public2.files
(
    id bigint NOT NULL DEFAULT nextval('files_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    pic_byte oid,
    type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT files_pkey PRIMARY KEY (id)
    );
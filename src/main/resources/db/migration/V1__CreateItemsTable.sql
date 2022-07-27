
CREATE TABLE IF NOT EXISTS item
(
    id_item bigint NOT NULL,
    type character varying(255),
    name character varying(255),
    status integer,
    CONSTRAINT item_pkey PRIMARY KEY (id_item)
)
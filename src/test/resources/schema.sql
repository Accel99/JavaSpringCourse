DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS product;

DROP SEQUENCE IF EXISTS product_id_seq;
DROP SEQUENCE IF EXISTS sales_id_seq;

CREATE SEQUENCE product_id_seq
  START WITH 4
  INCREMENT BY 1
  MINVALUE 1;

CREATE SEQUENCE sales_id_seq
  START WITH 7
  INCREMENT BY 1
  MINVALUE 1;

CREATE TABLE product
(
    id INTEGER DEFAULT product_id_seq.nextval NOT NULL,
    name CHARACTER VARYING(1000),
    CONSTRAINT product_pk PRIMARY KEY (id)
);

CREATE TABLE sales
(
    id INTEGER DEFAULT sales_id_seq.nextval NOT NULL,
    product_id INTEGER NOT NULL,
    count INTEGER NOT NULL,
    price INTEGER NOT NULL,
    CONSTRAINT sales_pk PRIMARY KEY (id)
);

ALTER TABLE sales
    ADD FOREIGN KEY (product_id)
    REFERENCES product(id);

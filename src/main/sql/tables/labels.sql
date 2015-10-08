DROP TABLE LABEL;
DROP TABLE LABEL_TRANSLATION;
DROP TABLE LANGUAGE;
DROP TABLE ESPDMETA;

CREATE TABLE LABEL
(
label_id NUMBER(10),
label_code VARCHAR(45 CHAR),
date_modified DATE,
date_added DATE
);

CREATE TABLE LABEL_TRANSLATION
(
label_translation_id NUMBER(10),
label_id NUMBER(10),
language_id NUMBER(10),
label_translation_text VARCHAR(500),
date_modified DATE,
date_added DATE
);

CREATE TABLE LANGUAGE
(
language_id NUMBER(10),
language_code VARCHAR(45 CHAR),
language_name VARCHAR(150 CHAR),
date_modified DATE,
date_added DATE

);

CREATE TABLE ESPDMETA
(
meta_id NUMBER(10),
identifier VARCHAR(100 CHAR),
title_code VARCHAR(100 CHAR),
tooltip_code VARCHAR(100 CHAR),
date_modified DATE,
date_added DATE
);

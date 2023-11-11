INSERT INTO users(id, name, email, password, created, modified, last_login, token, is_active)
VALUES('709b3333-5b7e-4de2-97e1-8143f8a487e3', 'Jorge Diaz', 'jorgediaz@nisum.cl', '12345678', '2010-11-10', '2010-11-10', '2010-11-10', 'Bearer eyassdf.asdvspol.asdvewaaa', 1);

INSERT INTO phones(id, number, city_code, country_code, user_id, created, modified)
VALUES('9d5c69af-ce71-41be-989a-98b025bd73dd', '990930992', '01', '+51', '709b3333-5b7e-4de2-97e1-8143f8a487e3', '2010-11-10', '2010-11-10');
INSERT INTO users(id, name, email, password, created, modified, last_login, token, is_active)
VALUES('709b3333-5b7e-4de2-97e1-8143f8a487e3', 'Jorge Diaz', 'jorgediaz@nisum.cl', 'Jorge1234.', '2010-11-10', '2010-11-10', '2010-11-10', 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3MDliMzMzMy01YjdlLTRkZTItOTdlMS04MTQzZjhhNDg3ZTMiLCJyb2xlIjoiVVNFUiIsImV4cCI6MTY5OTczODc3OCwiaWF0IjoxNjk5NzM4NDc4fQ.a4L-sePU50IZ5Y9J4K9Vl5aODs2I3tZAVPHhZ0VVBi9JB8rnEsYCjmZZZrAA88xiH68f73NBEMiFpOaNK-jqnw', 1);
INSERT INTO users(id, name, email, password, created, modified, last_login, token, is_active)
VALUES('a99be12e-e1e9-4ae4-9290-b18c739b53cb', 'Luis Perez', 'luisperez@nike.cl', 'LuisPerez.', '2015-11-10', null, '2020-12-15', 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3MDliMzMzMy01YjdlLTRkZTItOTdlMS04MTQzZjhhNDg3ZTMiLCJyb2xlIjoiVVNFUiIsImV4cCI6MTY5OTc0MjA2OCwiaWF0IjoxNjk5NzQxNzY4fQ.GqQO3Mfj9NSSoDeOeAjydc7zzBDiwxXiOp6yDaer-wVtvoMaVusBvh2tT-ry6i5co1JAZVq5KPNOH9zSespBDA', 0);
INSERT INTO users(id, name, email, password, created, modified, last_login, token, is_active)
VALUES('0ce3ef80-7ec7-452c-87fd-288aa7d3caf2', 'Martin Garcia', 'martingarcia@espn.cl', 'martinG_1234', '2018-11-10', null, '2022-09-09', 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3MDliMzMzMy01YjdlLTRkZTItOTdlMS04MTQzZjhhNDg3ZTMiLCJyb2xlIjoiVVNFUiIsImV4cCI6MTY5OTc0MjIwMywiaWF0IjoxNjk5NzQxOTAzfQ._Pi71z0Hc4ndzohLicDQPuWmg-rQk4bnY-vs3mCkpfF6Q9SkQgbb8Fm6Aw_iAWVdUB3q_t_EU1k5VXF5ct75Vw', 1);


INSERT INTO phones(id, number, city_code, country_code, user_id, created, modified)
VALUES('9d5c69af-ce71-41be-989a-98b025bd73dd', '990930992', '01', '+51', '709b3333-5b7e-4de2-97e1-8143f8a487e3', '2010-11-10', '2010-11-10');
INSERT INTO phones(id, number, city_code, country_code, user_id, created, modified)
VALUES('c368ed07-3d01-4e6f-8a04-49c58f6c3d1b', '912234099', '01', '+51', 'a99be12e-e1e9-4ae4-9290-b18c739b53cb', '2015-11-10', null);
INSERT INTO phones(id, number, city_code, country_code, user_id, created, modified)
VALUES('97d9d06c-ec45-4c46-8cb3-a9b2620a59e9', '912309876', '01', '+51', '0ce3ef80-7ec7-452c-87fd-288aa7d3caf2', '2018-11-10', null);
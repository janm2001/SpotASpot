INSERT INTO
    event(name, description, category, city, location, date_time, is_available, created_by, created_at, last_change)
    VALUES('Programiranje u javi',
           'Online radionica programiranja u javi, link: blabla.com/java',
           'RADIONICA',
           NULL,
           'Online',
           '2023-05-30T15:00:00',
           TRUE,
           (SELECT id FROM _user WHERE username = 'IHorvat'),
           now(),
           now());

INSERT INTO
    event(name, description, category, city, location, date_time, is_available, created_by, created_at, last_change)
VALUES('Predavanje o održivosti proizvoda',
       'Predavanje o održivosti proizvoda na feru, vodi profesor Blabla Blabal.',
       'PREDAVANJE',
       'Zagreb',
       'Unska ul. 3',
       '2023-05-30T16:00:00',
       TRUE,
       (SELECT id FROM _user WHERE username = 'IHorvat'),
       now(),
       now());

INSERT INTO
    event(name, description, category, city, location, date_time, is_available, created_by, created_at, last_change)
VALUES('Dream Theater koncert',
       'Koncert u areni zagreb Dream Theatera',
       'KONCERT',
       'Zagreb',
       'Ul. Vice Vukova 8',
       '2023-05-25T12:00:00',
       TRUE,
       (SELECT id FROM _user WHERE username = 'IHorvat'),
       now(),
       now());

INSERT INTO
    event(name, description, category, city, location, date_time, is_available, created_by, created_at, last_change)
VALUES('Pub kviz u vintagu',
       'Pub kniz u vintagu',
       'KVIZ',
       'Zagreb',
       'Savska cesta 160',
       '2023-06-05T13:00:00',
       TRUE,
       (SELECT id FROM _user WHERE username = 'IHorvat'),
       now(),
       now());

INSERT INTO
    event(name, description, category, city, location, date_time, is_available, created_by, created_at, last_change)
VALUES('Utakmica Dinamo Hajduk',
       'Utakmica u splitu',
       'SPORT',
       'Split',
       '8 Mediteranskih Igara 2',
       '2023-05-05T18:00:00',
       TRUE,
       (SELECT id FROM _user WHERE username = 'IHorvat'),
       now(),
       now());

INSERT INTO
    event(name, description, category, city, location, date_time, is_available, created_by, created_at, last_change)
VALUES('Društvene igre u vintagu',
       'Dođite i zaigrajte društvene igre u vintagu',
       'IGRA',
       'Zagreb',
       'Savska cesta 160',
       '2023-05-01T20:00:00',
       TRUE,
       (SELECT id FROM _user WHERE username = 'IHorvat'),
       now(),
       now());

INSERT INTO
    event(name, description, category, city, location, date_time, is_available, created_by, created_at, last_change)
VALUES('Natjecanje u League of Legendsu na Feru',
       'Dođite i gledajte naš tim u esportsu',
       'NATJECANJE',
       'Zagreb',
       'Unska ul. 3',
       '2023-04-28T20:00:00',
       TRUE,
       (SELECT id FROM _user WHERE username = 'IHorvat'),
       now(),
       now());

INSERT INTO
    event(name, description, category, city, location, date_time, is_available, created_by, created_at, last_change)
VALUES('Standup komičar Dino Merlin',
       'Dođite i gledajte Dinu Merlina na prvom standupu',
       'STANDUP',
       'Zagreb',
       'Ul. Vuke Vukova 69',
       '2023-04-12T20:00:00',
       FALSE,
       (SELECT id FROM _user WHERE username = 'IHorvat'),
       now(),
       now());

INSERT INTO
    event(name, description, category, city, location, date_time, is_available, created_by, created_at, last_change)
VALUES('Donirajte staru odjeću',
       'Dođite i donirajte staru odjeću u kvartu Kvatrić',
       'DOBROTVORNI',
       'Zagreb',
       'Ul. Kvatrića 2',
       '2023-05-16T12:00:00',
       TRUE,
       (SELECT id FROM _user WHERE username = 'IHorvat'),
       now(),
       now());
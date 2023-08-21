--alter sequence doctor_id_seq restart with 100;
--alter sequence patient_id_seq restart with 100;
insert into DOCTOR (name, surname, specialization, pwz, phone, email, doctor_address_id )
values
('Stefan', 'Wątroba', 'INTERNIST', '3245612', '+48 624 215 874', 'watroba@gmail.com','1'),
('Aleksander', 'Udarowski', 'NEUROLOGIST', '2245813', '+48 825 124 741', 'udar@gmail.com','2'),
('Ambroży', 'Soczewka', 'EYEDOCTOR', '1245613', '+48 842 452 425', 'soczewka@gmail.com','3'),
('Alojzy', 'Prostata', 'UROLOGIST', '8255863', '+48 858 258 246', 'prostata@gmail.com','4');


insert into PATIENT (name, surname, pesel, phone, email, date_of_birth, patient_address_id)
values
('Norbert', 'Choroba', '52070997836', '+48 646 214 369', 'choroba@gmail.com', '1952-07-09', '1'),
('Robert', 'Złamany', '67111396321', '+48 658 289 358', 'zlamany@gmail.com', '1967-11-13', '2'),
('Alicja', 'Osteoporoza', '83011863727', '+48 687 963 274', 'osteoporoza@gmail.com', '1983-01-18', '3');



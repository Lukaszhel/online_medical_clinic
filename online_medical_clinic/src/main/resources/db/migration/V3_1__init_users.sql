ALTER TABLE doctor
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES online_medical_clinic_user (user_id);

ALTER TABLE patient
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES online_medical_clinic_user (user_id);

INSERT INTO online_medical_clinic_user (user_id, user_name, email, password, active) VALUES (1, 'stefan_wątroba', 'watroba@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
INSERT INTO online_medical_clinic_user (user_id, user_name, email, password, active) VALUES (2, 'aleksander_udarowski', 'udar@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
INSERT INTO online_medical_clinic_user (user_id, user_name, email, password, active) VALUES (3, 'ambroży_soczewka', 'soczewka@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
INSERT INTO online_medical_clinic_user (user_id, user_name, email, password, active) VALUES (4, 'alojzy_prostata', 'prostata@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);

INSERT INTO online_medical_clinic_user (user_id, user_name, email, password, active) VALUES (5, 'norbert_choroba', 'choroba@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
INSERT INTO online_medical_clinic_user (user_id, user_name, email, password, active) VALUES (6, 'robert_złamany', 'zlamany@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
INSERT INTO online_medical_clinic_user (user_id, user_name, email, password, active) VALUES (7, 'alicja_osteoporoza', 'osteoporoza@gmail.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);

UPDATE doctor SET user_id = 1 WHERE pwz = '3245612';
UPDATE doctor SET user_id = 2 WHERE pwz = '2245813';
UPDATE doctor SET user_id = 3 WHERE pwz = '1245613';
UPDATE doctor SET user_id = 4 WHERE pwz = '8255863';

UPDATE patient SET user_id = 5 WHERE pesel = '52070997836';
UPDATE patient SET user_id = 6 WHERE pesel = '67111396321';
UPDATE patient SET user_id = 7 WHERE pesel = '83011863727';

INSERT INTO online_medical_clinic_role (role_id, role) VALUES (1, 'DOCTOR'), (2, 'PATIENT');

INSERT INTO online_medical_clinic_user_role (user_id, role_id) VALUES (1, 1), (2, 1), (3, 1), (4, 1);
INSERT INTO online_medical_clinic_user_role (user_id, role_id) VALUES (5, 2), (6, 2), (7, 2);

ALTER TABLE doctor
ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE patient
ALTER COLUMN user_id SET NOT NULL;

ALTER sequence online_medical_clinic_user_user_id_seq restart with 8;

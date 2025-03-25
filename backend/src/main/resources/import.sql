INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Alex', 'Brown', 'alex@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Maria', 'Green', 'maria@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);


INSERT INTO tb_instrument (name, created_at) VALUES ('Fluguelhorn', NOW());
INSERT INTO tb_instrument (name, created_at) VALUES ('Trombone', NOW());
INSERT INTO tb_instrument (name, created_at) VALUES ('Trumpet', NOW());
INSERT INTO tb_instrument (name, created_at) VALUES ('Tuba', NOW());

INSERT INTO tb_graduation(name) VALUES ('Recruta');
INSERT INTO tb_graduation(name) VALUES ('Soldado');
INSERT INTO tb_graduation(name) VALUES ('Cabo');
INSERT INTO tb_graduation(name) VALUES ('Sargento');
INSERT INTO tb_graduation(name) VALUES ('Tenente');
INSERT INTO tb_graduation(name) VALUES ('Capitão');
INSERT INTO tb_graduation(name) VALUES ('Major');
INSERT INTO tb_graduation(name) VALUES ('Coronel');
INSERT INTO tb_graduation(name) VALUES ('General');


INSERT INTO tb_tutor (name, created_at) VALUES ('Gilberto Brandt', NOW());
INSERT INTO tb_tutor (name, created_at) VALUES ('Jane Doe', NOW());
INSERT INTO tb_tutor (name, created_at) VALUES ('John Doe', NOW());
INSERT INTO tb_tutor (name, created_at) VALUES ('Mary Doe', NOW());
INSERT INTO tb_tutor (name, created_at) VALUES ('Peter Doe', NOW());
INSERT INTO tb_tutor (name, created_at) VALUES ('Sue Doe', NOW());

INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 1', NOW());
INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 2', NOW());
INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 3', NOW());
INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 4', NOW());
INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 5', NOW());

INSERT INTO tb_school_test (name, description, discipline_id, grade, created_at) VALUES ('School Test 1', 'School Test 1 Description',1, 10, NOW());
INSERT INTO tb_school_test (name, description, discipline_id, grade, created_at) VALUES ('School Test 2', 'School Test 2 Description',2, 9, NOW());
INSERT INTO tb_school_test (name, description, discipline_id, grade, created_at) VALUES ('School Test 3', 'School Test 3 Description',3, 8, NOW());
INSERT INTO tb_school_test (name, description, discipline_id, grade, created_at) VALUES ('School Test 4', 'School Test 4 Description',4, 7, NOW());

INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 1', '123', 'City 1','Bairro 1','State 1','808828', NOW());
INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 2', '456', 'City 2','Bairro 2','State 2','808828', NOW());
INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 3', '789', 'City 3','Bairro 3','State 3','808828', NOW());
INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 4', '1011', 'City 4','Bairro 4','State 4','808828', NOW());
INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 5', '1213', 'City 5','Bairro 5','State 5','808828', NOW());
INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 6', '1415', 'City 6','Bairro 6','State 6','808828', NOW());


INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 1', '99999999', NOW());
INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 2', '88888888', NOW());
INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 3', '77777777', NOW());
INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 4', '66666666', NOW());
INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 5', '55555555', NOW());
INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 6', '44444444', NOW());
INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 7', '33333333', NOW());
INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 8', '22222222', NOW());

INSERT INTO tb_classroom (name, created_at) VALUES ('Turma 1 2025', NOW());
INSERT INTO tb_classroom (name, created_at) VALUES ('Turma 2 2025', NOW());
INSERT INTO tb_classroom (name, created_at) VALUES ('Turma 3 2025', NOW());


INSERT INTO tb_school(name,  created_at) VALUES ('Colégio Social Madre Clélia',  NOW());
INSERT INTO tb_school(name,  created_at) VALUES ('Colégio Social Madre Clélia 2',  NOW());

INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 1','student1@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 2','student2@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 3','student3@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 4','student4@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 5','student5@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 6','student6@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 7','student7@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 8','student8@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 9','student9@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 10','studen10@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 11','student11@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 12','student12@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 13','student13@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 14','student14@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 15','student15@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 16','student16@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 17','student17@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 18','student18@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 19','student19@gmail.com','9999-9999', 1,1,1, NOW());
INSERT INTO tb_student(name, email, phone, class_room_id, instrument_id, graduation_id, created_at) VALUES ('Student 20','student20@gmail.com','9999-9999', 1,1,1, NOW());


INSERT INTO tb_student_tutor (student_id, tutor_id) VALUES (1, 1);
INSERT INTO tb_student_tutor (student_id, tutor_id) VALUES (2, 1);
INSERT INTO tb_student_tutor (student_id, tutor_id) VALUES (3, 2);
INSERT INTO tb_student_tutor (student_id, tutor_id) VALUES (4, 1);
INSERT INTO tb_student_tutor (student_id, tutor_id) VALUES (5, 2);

INSERT INTO tb_tutor_address (tutor_id, address_id) VALUES (1, 1);
INSERT INTO tb_tutor_address (tutor_id, address_id) VALUES (2, 2);
INSERT INTO tb_tutor_address (tutor_id, address_id) VALUES (3, 3);
INSERT INTO tb_tutor_address (tutor_id, address_id) VALUES (4, 4);
INSERT INTO tb_tutor_address (tutor_id, address_id) VALUES (5, 5);

INSERT INTO tb_teacher_address (teacher_id, address_id) VALUES (1, 1);
INSERT INTO tb_teacher_address (teacher_id, address_id) VALUES (2, 2);
INSERT INTO tb_teacher_address (teacher_id, address_id) VALUES (3, 3);
INSERT INTO tb_teacher_address (teacher_id, address_id) VALUES (4, 4);

INSERT INTO tb_teacher_discipline (teacher_id, discipline_id) VALUES (1, 1);
INSERT INTO tb_teacher_discipline (teacher_id, discipline_id) VALUES (2, 2);
INSERT INTO tb_teacher_discipline (teacher_id, discipline_id) VALUES (3, 3);
INSERT INTO tb_teacher_discipline (teacher_id, discipline_id) VALUES (4, 4);



INSERT INTO tb_school_roll_call (date, presence, student_id, justification, created_at, updated_at) VALUES (NOW(), true, 1, 'Justification 1', NOW(), NOW());
INSERT INTO tb_school_roll_call (date, presence, student_id, justification, created_at, updated_at) VALUES (NOW(), true, 2, 'Justification 2', NOW(), NOW());
INSERT INTO tb_school_roll_call (date, presence, student_id, justification, created_at, updated_at) VALUES (NOW(), true, 3, 'Justification 3', NOW(), NOW());
INSERT INTO tb_school_roll_call (date, presence, student_id, justification, created_at, updated_at) VALUES (NOW(), true, 4, 'Justification 4', NOW(), NOW());
INSERT INTO tb_school_roll_call (date, presence, student_id, justification, created_at, updated_at) VALUES (NOW(), true, 5, 'Justification 5', NOW(), NOW());
INSERT INTO tb_school_roll_call (date, presence, student_id, justification, created_at, updated_at) VALUES (NOW(), true, 6, 'Justification 6', NOW(), NOW());
INSERT INTO tb_school_roll_call (date, presence, student_id, justification, created_at, updated_at) VALUES (NOW(), true, 7, 'Justification 7', NOW(), NOW());
INSERT INTO tb_school_roll_call (date, presence, student_id, justification, created_at, updated_at) VALUES (NOW(), true, 8, 'Justification 8', NOW(), NOW());
INSERT INTO tb_school_roll_call (date, presence, student_id, justification, created_at, updated_at) VALUES (NOW(), true, 9, 'Justification 9', NOW(), NOW());
INSERT INTO tb_school_roll_call (date, presence, student_id, justification, created_at, updated_at) VALUES (NOW(), true, 10, 'Justification 10', NOW(), NOW());







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

INSERT INTO tb_school_test (name, description, grade, created_at) VALUES ('School Test 1', 'School Test 1 Description', 10, NOW());
INSERT INTO tb_school_test (name, description, grade, created_at) VALUES ('School Test 2', 'School Test 2 Description', 9, NOW());
INSERT INTO tb_school_test (name, description, grade, created_at) VALUES ('School Test 3', 'School Test 3 Description', 8, NOW());
INSERT INTO tb_school_test (name, description, grade, created_at) VALUES ('School Test 4', 'School Test 4 Description', 7, NOW());

INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 1', '123', 'City 1','Bairro 1','State 1','808828', NOW());
INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 2', '456', 'City 2','Bairro 2','State 2','808828', NOW());
INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 3', '789', 'City 3','Bairro 3','State 3','808828', NOW());
INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 4', '1011', 'City 4','Bairro 4','State 4','808828', NOW());
INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 5', '1213', 'City 5','Bairro 5','State 5','808828', NOW());
INSERT INTO tb_address (street, num, city, neighborhood,  state, zip_code, created_at) VALUES ('Street 6', '1415', 'City 6','Bairro 6','State 6','808828', NOW());

INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 1', NOW());
INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 2', NOW());
INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 3', NOW());
INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 4', NOW());


INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 1', '99999999', NOW());
INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 2', '88888888', NOW());
INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 3', '77777777', NOW());
INSERT INTO tb_teacher (name, phone, created_at) VALUES ('Teacher 4', '66666666', NOW());

INSERT INTO tb_classroom (name, created_at) VALUES ('Turma 1 2025', NOW());
INSERT INTO tb_classroom (name, created_at) VALUES ('Turma 2 2025', NOW());
INSERT INTO tb_classroom (name, created_at) VALUES ('Turma 3 2025', NOW());


INSERT INTO tb_school(name,  created_at) VALUES ('Colégio Social Madre Clélia',  NOW());

INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 1', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 2', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 3', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 4', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 5', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 6', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 7', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 8', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 9', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 10', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 11', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 12', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 13', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 14', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 15', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 16', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 17', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 18', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 19', 1, NOW());
INSERT INTO tb_student(name, class_room_id, created_at) VALUES ('Student 20', 1, NOW());



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







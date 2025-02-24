INSERT INTO tb_instrument (name, created_at) VALUES ('Fluguelhorn', NOW());
INSERT INTO tb_instrument (name, created_at) VALUES ('Trombone', NOW());
INSERT INTO tb_instrument (name, created_at) VALUES ('Trumpet', NOW());
INSERT INTO tb_instrument (name, created_at) VALUES ('Tuba', NOW());

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

INSERT INTO tb_student (name, instrument_id, address_id, created_at) VALUES ('Student 1', 1, 1, NOW());
INSERT INTO tb_student (name, instrument_id, address_id, created_at) VALUES ('Student 2', 2, 2, NOW());
INSERT INTO tb_student (name, instrument_id, address_id, created_at) VALUES ('Student 3', 3, 3, NOW());


INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (1, 1);
INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (1, 2);
INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (1, 3);
INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (1, 4);
INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (2, 1);
INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (2, 2);
INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (2, 3);
INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (2, 4);
INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (3, 1);
INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (3, 2);
INSERT INTO tb_student_school_test (student_id, school_test_id) VALUES (3, 3);

INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 1', NOW());
INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 2', NOW());
INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 3', NOW());
INSERT INTO tb_discipline (name, created_at) VALUES ('Discipline 4', NOW());


INSERT INTO tb_teacher (name, phone, address_id, created_at) VALUES ('Teacher 1', '99999999', 1, NOW());
INSERT INTO tb_teacher (name, phone, address_id, created_at) VALUES ('Teacher 2', '88888888', 2, NOW());
INSERT INTO tb_teacher (name, phone, address_id, created_at) VALUES ('Teacher 3', '77777777', 3, NOW());
INSERT INTO tb_teacher (name, phone, address_id, created_at) VALUES ('Teacher 4', '66666666', 4, NOW());


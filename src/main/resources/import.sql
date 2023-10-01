
-- Insert data into the Argomenti table
INSERT INTO argomento (nome,slug) VALUES ('Matematica','matematica');
INSERT INTO argomento (nome,slug) VALUES ('Fisica','fisica');
INSERT INTO argomento (nome,slug) VALUES ('Storia','storia');
INSERT INTO argomento (nome,slug) VALUES ('Informatica','informatica');
INSERT INTO argomento (nome,slug) VALUES ('Java','java');
INSERT INTO argomento (nome,slug) VALUES ('Server','server');
-- Insert data into the UnitaApprendimento table
INSERT INTO unita_apprendimento (nome, is_completed,argomenti_argomento_id) VALUES ('Derivata Prima', false,1);
INSERT INTO unita_apprendimento (nome, is_completed,argomenti_argomento_id) VALUES ('Derivata Seconda', false,1);
INSERT INTO unita_apprendimento (nome, is_completed,argomenti_argomento_id) VALUES ('Prima Guerra Mondiale', false,3);


-- insert nozioni

INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('la derivata prima', 1);
INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('la derivata seconda', 2);
INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('La prima guerra mondiale', 3);
INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Il Rapporto incrementale ....',1);


--

-- -- Topics
-- INSERT INTO unita_apprendimento (nome, is_completed) VALUES ('Mathematics', false);
-- INSERT INTO unita_apprendimento (nome, is_completed) VALUES ('Science', false);
-- INSERT INTO unita_apprendimento (nome, is_completed) VALUES ('History', false);
-- INSERT INTO unita_apprendimento (nome, is_completed) VALUES ('Programming', false);
-- INSERT INTO unita_apprendimento (nome, is_completed) VALUES ('Languages', false);




-- -- Concepts for Mathematics
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Addition', 1);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Subtraction', 1);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Multiplication', 1);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Division', 1);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Algebra', 1);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Geometry', 1);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Statistics', 1);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Calculus', 1);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Trigonometry', 1);

-- -- Concepts for Science
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Chemistry', 2);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Physics', 2);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Biology', 2);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Astronomy', 2);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Geology', 2);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Environmental Science', 2);

-- -- Concepts for History
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Ancient Civilizations', 3);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Medieval History', 3);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Renaissance', 3);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('World Wars', 3);

-- -- Concepts for Programming
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Variables and Data Types', 4);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Control Structures', 4);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Functions and Methods', 4);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Object-Oriented Programming', 4);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Database Interaction', 4);

-- -- Concepts for Languages
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('English Grammar', 5);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('French Vocabulary', 5);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Spanish Pronunciation', 5);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('German Phrases', 5);
-- INSERT INTO nozione (testo, unita_apprendimento_id) VALUES ('Japanese Writing Systems', 5);

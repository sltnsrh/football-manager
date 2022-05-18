INSERT INTO players (age, experience_months, first_name, last_name)
VALUES (21, 16, 'Bob', 'Alister'),
       (35, 62, 'John', 'Walker'),
       (27, 35, 'Harry', 'Modies'),
       (37, 85, 'Zack', 'Steffen'),
       (32, 49, 'Scott', 'Carson'),
       (24, 45, 'Kyle', 'Walker'),
       (33, 120,'Nathan', 'Ake'),
       (36, 98, 'Bernardo', 'Silva'),
       (28, 96, 'Phil', 'Pholen'),
       (29, 112,'Oscar', 'Bobb');

INSERT INTO teams (name, count, commission)
VALUES ('Milton', 70000000, 10),
       ('Oxford', 100000000, 7);

INSERT INTO teams_players (team_id, player_id)
VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
       (2, 6), (2, 7), (2, 8), (2, 9), (2, 10);

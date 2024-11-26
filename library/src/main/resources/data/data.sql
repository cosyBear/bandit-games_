-- Insert into Player table
INSERT INTO player (player_id) VALUES
                                   ('2a64794e-d4f3-4e1d-b6c2-4ec876fbf4f7'),
                                   ('3b7a20c8-3f5c-4bc9-99e5-b2348f69cd3d');

-- Insert into Library table linked to players
INSERT INTO library (library_id, player_id) VALUES
                                                ('4c9e3a7f-fb92-4056-803f-0b13ae6b90b3', '2a64794e-d4f3-4e1d-b6c2-4ec876fbf4f7'),
                                                ('9d97e52a-524b-4a64-8c5c-e91eb69bc1ec', '3b7a20c8-3f5c-4bc9-99e5-b2348f69cd3d');

-- Insert into Game table split across two libraries
INSERT INTO game (game_id, game_name, game_type, library_id, image_url, favourite) VALUES
                                                                                       ('70f014ec-b302-4ba2-a981-19e64d234b41', 'Game 1', 'ACTION', '4c9e3a7f-fb92-4056-803f-0b13ae6b90b3', 'https://example.com/game1.jpg', true),
                                                                                       ('5de83a62-df8b-4db3-91b8-3eb71a6b4a3e', 'Game 2', 'STRATEGY', '4c9e3a7f-fb92-4056-803f-0b13ae6b90b3', 'https://example.com/game2.jpg', false),
                                                                                       ('d1a11bce-e276-4855-89b2-df86be3b9ea8', 'Game 3', 'RPG', '4c9e3a7f-fb92-4056-803f-0b13ae6b90b3', 'https://example.com/game3.jpg', true),
                                                                                       ('a485a622-80d1-4f7e-9d71-d89c78e9988d', 'Game 4', 'SIMULATION', '4c9e3a7f-fb92-4056-803f-0b13ae6b90b3', 'https://example.com/game4.jpg', false),
                                                                                       ('116ec43c-05b6-4e9b-8f89-77c35846b89b', 'Game 5', 'PUZZLE', '4c9e3a7f-fb92-4056-803f-0b13ae6b90b3', 'https://example.com/game5.jpg', true),
                                                                                       ('435f3b60-0532-4e1e-812c-dff76088179b', 'Game 6', 'ADVENTURE', '9d97e52a-524b-4a64-8c5c-e91eb69bc1ec', 'https://example.com/game6.jpg', false),
                                                                                       ('3c63c785-8a44-4f26-b391-32e43597f4ad', 'Game 7', 'RACING', '9d97e52a-524b-4a64-8c5c-e91eb69bc1ec', 'https://example.com/game7.jpg', true),
                                                                                       ('6c90a0f4-fc3e-41d8-a76d-7ff23871f0eb', 'Game 8', 'SPORTS', '9d97e52a-524b-4a64-8c5c-e91eb69bc1ec', 'https://example.com/game8.jpg', false),
                                                                                       ('4f98f08e-bfc6-4657-800e-99a57a0d1544', 'Game 9', 'BOARD_GAME', '9d97e52a-524b-4a64-8c5c-e91eb69bc1ec', 'https://example.com/game9.jpg', true),
                                                                                       ('f8325678-1202-47c9-a3f5-998e6a62d2ea', 'Game 10', 'SHOOTER', '9d97e52a-524b-4a64-8c5c-e91eb69bc1ec', 'https://example.com/game10.jpg', false);

-- Insert into Achievement table for specific games
INSERT INTO achievement (achievement_id, achievement_name, achievement_description, image_url, achieved, game_id) VALUES
                                                                                                                      ('7be9f1c6-3a48-4bb1-9a67-07fbf1e9a963', 'Achievement 1', 'Complete level 1', 'https://example.com/ach1.jpg', true, '70f014ec-b302-4ba2-a981-19e64d234b41'),
                                                                                                                      ('1bf57be8-49af-4723-a221-5e50a4d9bcd1', 'Achievement 2', 'Win 10 matches', 'https://example.com/ach2.jpg', false, '5de83a62-df8b-4db3-91b8-3eb71a6b4a3e'),
                                                                                                                      ('6d5b6c9a-fb44-4f94-847a-c841c9ef739f', 'Achievement 3', 'Collect all stars', 'https://example.com/ach3.jpg', true, 'd1a11bce-e276-4855-89b2-df86be3b9ea8'),
                                                                                                                      ('8e7b5749-78a6-4533-91ed-f09f3eb4d1fc', 'Achievement 4', 'Finish story mode', 'https://example.com/ach4.jpg', false, '3c63c785-8a44-4f26-b391-32e43597f4ad');

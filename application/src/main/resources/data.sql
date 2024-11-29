-- Create the main player
INSERT INTO friends.player (id, nickname, first_name, last_name, gender)
VALUES
    ('95592601-d766-4715-a205-fbd13323ccc3', 'TheAce', 'John', 'Doe', 'MALE');

-- Create friends (other players)
INSERT INTO friends.player (id, nickname, first_name, last_name, gender)
VALUES
    ('c56a4180-65aa-42ec-a945-5fd21dec0538', 'Grigor', 'Grigor', 'Smith', 'MALE'),
    ('c56a4181-65aa-42ec-a945-5fd21dec0539', 'Peter', 'Peter', 'Johnson', 'MALE'),
    ('c56a4182-65aa-42ec-a945-5fd21dec0540', 'Alice', 'Alice', 'Williams', 'FEMALE'),
    ('c56a4183-65aa-42ec-a945-5fd21dec0541', 'Rumus', 'Rumus', 'Brown', 'MALE');

-- Establish friendships (link the main player with friends)
INSERT INTO friends.friends (player_id, friend_id)
VALUES
    ('95592601-d766-4715-a205-fbd13323ccc3', 'c56a4180-65aa-42ec-a945-5fd21dec0538'),
    ('95592601-d766-4715-a205-fbd13323ccc3', 'c56a4181-65aa-42ec-a945-5fd21dec0539'),
    ('95592601-d766-4715-a205-fbd13323ccc3', 'c56a4182-65aa-42ec-a945-5fd21dec0540'),
    ('95592601-d766-4715-a205-fbd13323ccc3', 'c56a4183-65aa-42ec-a945-5fd21dec0541');

-- Create lobbies for the friends with valid game_id UUIDs
INSERT INTO friends.lobby (id, player_id, game_id , lobby_status)
VALUES
    ('1d095e14-8c15-4d2b-a2e5-59e7d8ff7129', 'c56a4180-65aa-42ec-a945-5fd21dec0538', '8fae4d9a-8dfb-4d64-95c3-6c6b234e57a1', "Created"),
    ('1d095e15-8c15-4d2b-a2e5-59e7d8ff7130', 'c56a4181-65aa-42ec-a945-5fd21dec0539', '3b3c99a7-e621-401e-89b6-b44f99c12d20', "Created"),
    ('1d095e16-8c15-4d2b-a2e5-59e7d8ff7131', 'c56a4182-65aa-42ec-a945-5fd21dec0540', 'db21cf77-7ed2-4b8c-9aa9-2ac5b20f4a63', "Created"),
    ('1d095e17-8c15-4d2b-a2e5-59e7d8ff7132', 'c56a4183-65aa-42ec-a945-5fd21dec0541', 'a6b95a3f-4095-40c6-aefd-7f6e5fba6011', "Created");


-- Library

-- Insert Players
INSERT INTO library.player (player_id)
VALUES
    ('123e4567-e89b-12d3-a456-426614174000'); -- Add any necessary Player ID(s)

-- Insert Libraries and associate with Players
INSERT INTO library.library (library_id, player_id)
VALUES
    ('456e7891-e89b-12d3-a456-426614174000', '123e4567-e89b-12d3-a456-426614174000');

-- Insert Games and associate with Libraries
INSERT INTO library.game (game_id, game_name, game_type, image_url, favourite, library_id, background_image_url)
VALUES
    ('e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1', 'Chess Master 1', 'BOARD_GAME', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220'),
    ('a2b7c0e4-3d11-4b5b-8297-3e45fc173c81', 'Need for Speed 1', 'RACING', 'https://placehold.co/100x80', true, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220'),
    ('f2d3b5c6-9a21-4d87-b6a1-8cb17de38145', 'Fortnite Battle 1', 'SHOOTER', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220'),
    ('d4f7e6a9-213a-4b8c-9f47-5a01e24c90b2', 'Minecraft Builder 1', 'SANDBOX', 'https://placehold.co/100x80', true, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220'),
    ('b1f5a7d9-14c8-43ba-a71d-0f62d9e39124', 'Portal Puzzle 1', 'PUZZLE', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220'),
    ('f2d3b5c6-9a21-4d87-b6a1-8cb18de38145', 'Fortnite Battle 2', 'SHOOTER', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220'),
    ('d4f7e6a9-213a-4b8c-9f47-5a02e24c90b2', 'Minecraft Builder 2', 'SANDBOX', 'https://placehold.co/100x80', true, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220'),
    ('b1f5a7d9-14c8-43ba-a71d-0f63d9e39124', 'Portal Puzzle 2', 'PUZZLE', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220'),
    ('f2d3b5c6-9a21-4d87-b6a1-8cb17de38245', 'Fortnite Battle 3', 'SHOOTER', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220'),
    ('d4f7e6a9-213a-4b8c-9f47-5a01e24c93b2', 'Minecraft Builder 3', 'SANDBOX', 'https://placehold.co/100x80', true, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220'),
    ('b1f5a7d9-14c8-43ba-a71d-0f62d9e39724', 'Portal Puzzle 3', 'PUZZLE', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220');



-- Insert Achievements and associate with Games
INSERT INTO library.achievement (achievement_id, achievement_name, achievement_description, image_url, achieved, game_id)
VALUES
    ('c7a4f9d3-8e12-4b7c-8a96-7b94e2836c74', 'First Blood', 'Win your first match', 'https://example.com/images/achievement1.png', false, 'e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1'),
    ('ea8f1c32-9b4c-4d5e-93a6-2a67f845b1c3', 'Grandmaster', 'Win 10 matches in a row', 'https://example.com/images/achievement2.png', true, 'e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1'),
    ('f17b9c65-21ea-4a93-b46c-8e217f49c8a3', 'Speed Racer', 'Complete a lap in under 2 minutes', 'https://example.com/images/achievement3.png', true, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('ab4c81e7-f23a-4e5d-a83b-5c74b12f90d7', 'Champion Driver', 'Win 5 consecutive races', 'https://example.com/images/achievement4.png', false, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('9e8f7c61-4b3a-4d7b-a1c6-f3a12b84c7e9', 'Survivor', 'Survive 10 minutes', 'https://example.com/images/achievement5.png', true, 'f2d3b5c6-9a21-4d87-b6a1-8cb17de38145'),
    ('b5c17a8e-4d32-4e7b-a9c8-f4a27b65e3d9', 'Sharp Shooter', 'Eliminate 10 opponents', 'https://example.com/images/achievement6.png', false, 'f2d3b5c6-9a21-4d87-b6a1-8cb17de38145'),
    ('f23a84d7-b19e-4b3c-a5c9-e4a12b76e9f3', 'Builder Extraordinaire', 'Build a massive castle', 'https://example.com/images/achievement7.png', true, 'd4f7e6a9-213a-4b8c-9f47-5a01e24c90b2'),
    ('b8f7c32e-4d93-4e2b-a1c9-f5a73b86c1e2', 'Explorer', 'Find the rare cave', 'https://example.com/images/achievement8.png', false, 'd4f7e6a9-213a-4b8c-9f47-5a01e24c90b2'),
    ('e7a2f9b1-4c32-4d9b-a8c9-f6a31b97e2f3', 'Portal Solver', 'Complete all levels', 'https://example.com/images/achievement9.png', true, 'b1f5a7d9-14c8-43ba-a71d-0f62d9e39124'),
    ('c7f3b12e-4a93-4e7c-a9b8-e3f62b41c9a8', 'Speed Runner', 'Complete the game in under 1 hour', 'https://example.com/images/achievement10.png', false, 'b1f5a7d9-14c8-43ba-a71d-0f62d9e39124');

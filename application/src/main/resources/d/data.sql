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
INSERT INTO friends.lobby (id, player_id, game_id)
VALUES
    ('1d095e14-8c15-4d2b-a2e5-59e7d8ff7129', 'c56a4180-65aa-42ec-a945-5fd21dec0538', '8fae4d9a-8dfb-4d64-95c3-6c6b234e57a1'),
    ('1d095e15-8c15-4d2b-a2e5-59e7d8ff7130', 'c56a4181-65aa-42ec-a945-5fd21dec0539', '3b3c99a7-e621-401e-89b6-b44f99c12d20'),
    ('1d095e16-8c15-4d2b-a2e5-59e7d8ff7131', 'c56a4182-65aa-42ec-a945-5fd21dec0540', 'db21cf77-7ed2-4b8c-9aa9-2ac5b20f4a63'),
    ('1d095e17-8c15-4d2b-a2e5-59e7d8ff7132', 'c56a4183-65aa-42ec-a945-5fd21dec0541', 'a6b95a3f-4095-40c6-aefd-7f6e5fba6011');

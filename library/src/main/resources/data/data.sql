INSERT INTO game_entity (game_id, game_name, game_type, image_url, favourite)
VALUES
    (UUID_TO_BIN('e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1'), 'Chess Master', 'BOARD_GAME', 'https://example.com/images/chess.png', false),
    (UUID_TO_BIN('a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'), 'Need for Speed', 'RACING', 'https://example.com/images/nfs.png', true),
    (UUID_TO_BIN('f2d3b5c6-9a21-4d87-b6a1-8cb17de38145'), 'Fortnite Battle', 'SHOOTER', 'https://example.com/images/fortnite.png', false),
    (UUID_TO_BIN('d4f7e6a9-213a-4b8c-9f47-5a01e24c90b2'), 'Minecraft Builder', 'SANDBOX', 'https://example.com/images/minecraft.png', true),
    (UUID_TO_BIN('b1f5a7d9-14c8-43ba-a71d-0f62d9e39124'), 'Portal Puzzle', 'PUZZLE', 'https://example.com/images/portal.png', false);
INSERT INTO achievement_entity (achievement_id, achievement_name, achievement_description, image_url, achieved, game_id)
VALUES
    (UUID_TO_BIN('c7a4f9d3-8e12-4b7c-8a96-7b94e2836c74'), 'First Blood', 'Win your first match', 'https://example.com/images/achievement1.png', false, UUID_TO_BIN('e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1')),
    (UUID_TO_BIN('ea8f1c32-9b4c-4d5e-93a6-2a67f845b1c3'), 'Grandmaster', 'Win 10 matches in a row', 'https://example.com/images/achievement2.png', true, UUID_TO_BIN('e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1'));
INSERT INTO achievement_entity (achievement_id, achievement_name, achievement_description, image_url, achieved, game_id)
VALUES
    (UUID_TO_BIN('f17b9c65-21ea-4a93-b46c-8e217f49c8a3'), 'Speed Racer', 'Complete a lap in under 2 minutes', 'https://example.com/images/achievement3.png', true, UUID_TO_BIN('a2b7c0e4-3d11-4b5b-8297-3e45fc173c81')),
    (UUID_TO_BIN('ab4c81e7-f23a-4e5d-a83b-5c74b12f90d7'), 'Champion Driver', 'Win 5 consecutive races', 'https://example.com/images/achievement4.png', false, UUID_TO_BIN('a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'));

INSERT INTO achievement_entity (achievement_id, achievement_name, achievement_description, image_url, achieved, game_id)
VALUES
    (UUID_TO_BIN('9e8f7c61-4b3a-4d7b-a1c6-f3a12b84c7e9'), 'Survivor', 'Survive 10 minutes', 'https://example.com/images/achievement5.png', true, UUID_TO_BIN('f2d3b5c6-9a21-4d87-b6a1-8cb17de38145')),
    (UUID_TO_BIN('b5c17a8e-4d32-4e7b-a9c8-f4a27b65e3d9'), 'Sharp Shooter', 'Eliminate 10 opponents', 'https://example.com/images/achievement6.png', false, UUID_TO_BIN('f2d3b5c6-9a21-4d87-b6a1-8cb17de38145'));

INSERT INTO achievement_entity (achievement_id, achievement_name, achievement_description, image_url, achieved, game_id)
VALUES
    (UUID_TO_BIN('f23a84d7-b19e-4b3c-a5c9-e4a12b76e9f3'), 'Builder Extraordinaire', 'Build a massive castle', 'https://example.com/images/achievement7.png', true, UUID_TO_BIN('d4f7e6a9-213a-4b8c-9f47-5a01e24c90b2')),
    (UUID_TO_BIN('b8f7c32e-4d93-4e2b-a1c9-f5a73b86c1e2'), 'Explorer', 'Find the rare cave', 'https://example.com/images/achievement8.png', false, UUID_TO_BIN('d4f7e6a9-213a-4b8c-9f47-5a01e24c90b2'));

INSERT INTO achievement_entity (achievement_id, achievement_name, achievement_description, image_url, achieved, game_id)
VALUES
    (UUID_TO_BIN('e7a2f9b1-4c32-4d9b-a8c9-f6a31b97e2f3'), 'Portal Solver', 'Complete all levels', 'https://example.com/images/achievement9.png', true, UUID_TO_BIN('b1f5a7d9-14c8-43ba-a71d-0f62d9e39124')),
    (UUID_TO_BIN('c7f3b12e-4a93-4e7c-a9b8-e3f62b41c9a8'), 'Speed Runner', 'Complete the game in under 1 hour', 'https://example.com/images/achievement10.png', false, UUID_TO_BIN('b1f5a7d9-14c8-43ba-a71d-0f62d9e39124'));

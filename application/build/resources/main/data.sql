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
    ('c56a4180-65aa-42ec-a945-5fd21dec0538', '95592601-d766-4715-a205-fbd13323ccc3'),
    ('95592601-d766-4715-a205-fbd13323ccc3', 'c56a4181-65aa-42ec-a945-5fd21dec0539'),
    ('c56a4181-65aa-42ec-a945-5fd21dec0539', '95592601-d766-4715-a205-fbd13323ccc3'),
    ('95592601-d766-4715-a205-fbd13323ccc3', 'c56a4182-65aa-42ec-a945-5fd21dec0540'),
    ('c56a4182-65aa-42ec-a945-5fd21dec0540', '95592601-d766-4715-a205-fbd13323ccc3'),
    ('95592601-d766-4715-a205-fbd13323ccc3', 'c56a4183-65aa-42ec-a945-5fd21dec0541'),
    ('c56a4183-65aa-42ec-a945-5fd21dec0541', '95592601-d766-4715-a205-fbd13323ccc3');

-- -- Create lobbies for the friends with valid game_id UUIDs
-- INSERT INTO friends.lobby (id, player_id, game_id , lobby_status)
-- VALUES
-- --     ('1d095e14-8c15-4d2b-a2e5-59e7d8ff7129', '95592601-d766-4715-a205-fbd13323ccc3', 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81', "Created"),
--     ('1d095e15-8c15-4d2b-a2e5-59e7d8ff7130', 'c56a4181-65aa-42ec-a945-5fd21dec0539', 'e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1', "Created"),
--     ('1d095e16-8c15-4d2b-a2e5-59e7d8ff7131', 'c56a4182-65aa-42ec-a945-5fd21dec0540', 'f2d3b5c6-9a21-4d87-b6a1-8cb17de38145', "Created"),
--     ('1d095e17-8c15-4d2b-a2e5-59e7d8ff7132', 'c56a4183-65aa-42ec-a945-5fd21dec0541', 'd4f7e6a9-213a-4b8c-9f47-5a01e24c90b2', "Created");


INSERT INTO lobby.lobby (created_at, game_id, guest_player, lobby_admin, lobby_id, lobby_status_entity)
SELECT
    NOW(),
    game_id,
    NULL,
    player_id,
    id,
    lobby_status
FROM friends.lobby;



-- Library


INSERT INTO library.player (player_id)
VALUES ('c56a4182-65aa-42ec-a945-5fd21dec0540');

INSERT INTO library.library (library_id, player_id)
VALUES ('789f65e4-a89c-45d3-b891-326674f54a10', 'c56a4182-65aa-42ec-a945-5fd21dec0540');


-- Insert a new game for the player's library
INSERT INTO library.game (
    game_id,
    game_name,
    game_type,
    image_url,
    favourite,
    library_id,
    background_image_url,
    description
)
VALUES (
           '34eaf65a-7abc-4e7a-b65f-8c4e675d8f99', -- New game ID
           'Space Conqueror',
           'STRATEGY',
           'https://placehold.co/100x80',
           true, -- Favorite game
           '789f65e4-a89c-45d3-b891-326674f54a10', -- Library ID
           'https://placehold.co/1400x220',
           'A strategy game where players build fleets and conquer the galaxy.'
       );

-- Insert achievements for the new game
INSERT INTO library.achievement (
    achievement_id,
    achievement_name,
    achievement_description,
    image_url,
    achieved,
    game_id
)
VALUES
    (
        '1e8b76c9-4abc-4b2e-9b65-e7c4a65d8f91', -- Achievement ID for "Fleet Admiral"
        'Fleet Admiral',
        'Awarded for creating a fleet of 100 ships.',
        'https://example.com/images/achievement_fleet_admiral.png',
        false,
        '34eaf65a-7abc-4e7a-b65f-8c4e675d8f99' -- Associated with the new game ID
    ),
    (
        '2f9c86d2-5acd-4a3f-a976-f5b2d84e9c02', -- Achievement ID for "First Colony"
        'First Colony',
        'Awarded for establishing the first colony.',
        'https://example.com/images/achievement_first_colony.png',
        false,
        '34eaf65a-7abc-4e7a-b65f-8c4e675d8f99' -- Associated with the new game ID
    ),
    (
        '3c5d7fe3-6bac-4b4e-b87c-d4b2e85f9c13', -- Achievement ID for "Galaxy Ruler"
        'Galaxy Ruler',
        'Awarded for conquering the entire galaxy.',
        'https://example.com/images/achievement_galaxy_ruler.png',
        false,
        '34eaf65a-7abc-4e7a-b65f-8c4e675d8f99' -- Associated with the new game ID
    );


-- Insert Players
INSERT INTO library.player (player_id)
VALUES
    ('95592601-d766-4715-a205-fbd13323ccc3'); -- Add any necessary Player ID(s)

-- Insert Libraries and associate with Players
INSERT INTO library.library (library_id, player_id)
VALUES
    ('456e7891-e89b-12d3-a456-426614174000', '95592601-d766-4715-a205-fbd13323ccc3');-- Insert Games and associate with Libraries


INSERT INTO library.game (game_id, game_name, game_type, image_url, favourite, library_id, background_image_url, description)
VALUES
    ('e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1', 'Chess Master 1', 'BOARD_GAME', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220', 'A timeless strategy board game that sharpens critical thinking and tactical planning.'),
    ('a2b7c0e4-3d11-4b5b-8297-3e45fc173c81', 'Need for Speed 1', 'RACING', 'https://placehold.co/100x80', true, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220', 'High-speed racing with customizable cars and thrilling escape missions.'),
    ('f2d3b5c6-9a21-4d87-b6a1-8cb17de38145', 'Fortnite Battle 1', 'SHOOTER', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220', 'An action-packed battle royale featuring vibrant visuals and unique building mechanics.'),
    ('b1f5a7d9-14c8-43ba-a71d-0f62d9e39124', 'Portal Puzzle 1', 'PUZZLE', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220', 'Innovative puzzle-solving using physics and portals to challenge your wits.'),
    ('f2d3b5c6-9a21-4d87-b6a1-8cb18de38145', 'Fortnite Battle 2', 'SHOOTER', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220', 'Join a fast-paced battle royale with exciting new weapons and tactics.'),
    ('d4f7e6a9-213a-4b8c-9f47-5a02e24c90b2', 'Minecraft Builder 2', 'SANDBOX', 'https://placehold.co/100x80', true, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220', 'Expand your imagination and create intricate worlds in an expansive sandbox.'),
    ('b1f5a7d9-14c8-43ba-a71d-0f63d9e39124', 'Portal Puzzle 2', 'PUZZLE', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220', 'Master challenging puzzles with clever use of physics-based portals.'),
    ('f2d3b5c6-9a21-4d87-b6a1-8cb17de38245', 'Fortnite Battle 3', 'SHOOTER', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220', 'Compete in dynamic battles with new arenas and intense gameplay modes.'),
    ('d4f7e6a9-213a-4b8c-9f47-5a01e24c93b2', 'Minecraft Builder 3', 'SANDBOX', 'https://placehold.co/100x80', true, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220', 'Unleash your creativity and construct extraordinary wonders in this sandbox adventure.'),
    ('b1f5a7d9-14c8-43ba-a71d-0f62d9e39724', 'Portal Puzzle 3', 'PUZZLE', 'https://placehold.co/100x80', false, '456e7891-e89b-12d3-a456-426614174000', 'https://placehold.co/1400x220', 'Solve intricate puzzles with advanced portal mechanics and strategic thinking.');

-- Insert Achievements and associate with Games
INSERT INTO library.achievement (achievement_id, achievement_name, achievement_description, image_url, achieved, game_id)
VALUES
    ('c7a4f9d3-8e12-4b7c-8a96-7b94e2836c74', 'First Blood', 'Win your first match', 'https://example.com/images/achievement1.png', false, 'e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1'),
    ('ea8f1c32-9b4c-4d5e-93a6-2a67f845b1c3', 'Grandmaster', 'Win 10 matches in a row', 'https://example.com/images/achievement2.png', true, 'e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1'),
    ('a7f4c9d2-5b12-4c8b-b896-7f94e2837e64', 'Nitro Master', 'Use nitro boost 50 times in races', 'https://example.com/images/achievement11.png', false, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('b3a8c1f7-6c42-4e9b-b7a9-3d12e785f1c4', 'Drift King', 'Perform 100 successful drifts', 'https://example.com/images/achievement12.png', true, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('c8f7e4a1-9b31-4e6c-a3b9-e4f16d72b8c7', 'Speed Demon', 'Reach a top speed of 200 mph', 'https://example.com/images/achievement13.png', false, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('d9b5f3a7-8e41-4c9b-a2c6-f7b12a84d3e9', 'Race Strategist', 'Win a race without using nitro', 'https://example.com/images/achievement14.png', true, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('e2b4f9a8-3c42-4b7c-b8a7-e6d12f83b9a5', 'Ultimate Racer', 'Win 20 races in career mode', 'https://example.com/images/achievement15.png', false, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('f1c8a7e4-2d93-4b7a-b8e9-e4f31b92d7c5', 'Flawless Victory', 'Win a race without taking any damage', 'https://example.com/images/achievement16.png', true, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('a5f3e9c8-6b21-4d8a-a3e9-f3a16b84c9e8', 'Checkpoint Hero', 'Finish a checkpoint race with 10 seconds remaining', 'https://example.com/images/achievement17.png', false, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('b7e9a4f3-8c31-4e6b-a9c6-f4b32e71a9f7', 'Perfect Drift', 'Achieve a perfect drift score in a race', 'https://example.com/images/achievement18.png', true, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('c3b8f4e1-9d21-4a6b-b7c9-e6a12f83c5d7', 'Time Trial Ace', 'Beat the developer time on a time trial track', 'https://example.com/images/achievement19.png', false, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('d8f9a7c2-4b11-4e8a-b3c6-f7e12a91c9f5', 'Collector', 'Unlock all cars in the game', 'https://example.com/images/achievement20.png', true, 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('9e8f7c61-4b3a-4d7b-a1c6-f3a12b84c7e9', 'Survivor', 'Survive 10 minutes', 'https://example.com/images/achievement5.png', true, 'f2d3b5c6-9a21-4d87-b6a1-8cb17de38145'),
    ('b5c17a8e-4d32-4e7b-a9c8-f4a27b65e3d9', 'Sharp Shooter', 'Eliminate 10 opponents', 'https://example.com/images/achievement6.png', false, 'f2d3b5c6-9a21-4d87-b6a1-8cb17de38145'),
    ('e7a2f9b1-4c32-4d9b-a8c9-f6a31b97e2f3', 'Portal Solver', 'Complete all levels', 'https://example.com/images/achievement9.png', true, 'b1f5a7d9-14c8-43ba-a71d-0f62d9e39124'),
    ('c7f3b12e-4a93-4e7c-a9b8-e3f62b41c9a8', 'Speed Runner', 'Complete the game in under 1 hour', 'https://example.com/images/achievement10.png', false, 'b1f5a7d9-14c8-43ba-a71d-0f62d9e39124');

INSERT INTO store.game (
    game_id, game_name, game_type, image_url, background_image_url, description, price, rating
)
VALUES
    (
        'e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1', 'Chess Master 1', 'BOARD_GAME',
        'https://placehold.co/100x80', 'https://placehold.co/1400x220',
        'A timeless strategy board game that sharpens critical thinking and tactical planning.', 19.99, 4.5
    ),
    (
        'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81', 'Need for Speed 1', 'RACING',
        'https://placehold.co/100x80', 'https://placehold.co/1400x220',
        'High-speed racing with customizable cars and thrilling escape missions.', 29.99, 4.7
    ),
    (
        'f2d3b5c6-9a21-4d87-b6a1-8cb17de38145', 'Fortnite Battle 1', 'SHOOTER',
        'https://placehold.co/100x80', 'https://placehold.co/1400x220',
        'An action-packed battle royale featuring vibrant visuals and unique building mechanics.', 0.00, 4.3
    ),
    (
        'd4f7e6a9-213a-4b8c-9f47-5a01e24c90b2', 'Minecraft Builder 1', 'SANDBOX',
        'https://placehold.co/100x80', 'https://placehold.co/1400x220',
        'An open-world sandbox game that inspires creativity and exploration.', 24.99, 4.6
    ),
    (
        'b1f5a7d9-14c8-43ba-a71d-0f62d9e39124', 'Portal Puzzle 1', 'PUZZLE',
        'https://placehold.co/100x80', 'https://placehold.co/1400x220',
        'Innovative puzzle-solving using physics and portals to challenge your wits.', 14.99, 4.8
    );
-- Insert Achievements
INSERT INTO store.store_achievement (achievement_id, achievement_name, achievement_description, image_url, game_id)
VALUES
    ('c7a4f9d3-8e12-4b7c-8a96-7b94e2836c74', 'First Blood', 'Win your first match', 'https://example.com/images/achievement1.png', 'e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1'),
    ('ea8f1c32-9b4c-4d5e-93a6-2a67f845b1c3', 'Grandmaster', 'Win 10 matches in a row', 'https://example.com/images/achievement2.png', 'e7a1c1d2-f18e-4e6a-b3a5-6dba403b62a1'),

    ('a7f4c9d2-5b12-4c8b-b896-7f94e2837e64', 'Nitro Master', 'Use nitro boost 50 times in races', 'https://example.com/images/achievement11.png', 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('b3a8c1f7-6c42-4e9b-b7a9-3d12e785f1c4', 'Drift King', 'Perform 100 successful drifts', 'https://example.com/images/achievement12.png', 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),
    ('c8f7e4a1-9b31-4e6c-a3b9-e4f16d72b8c7', 'Speed Demon', 'Reach a top speed of 200 mph', 'https://example.com/images/achievement13.png', 'a2b7c0e4-3d11-4b5b-8297-3e45fc173c81'),

    ('9e8f7c61-4b3a-4d7b-a1c6-f3a12b84c7e9', 'Survivor', 'Survive 10 minutes', 'https://example.com/images/achievement5.png', 'f2d3b5c6-9a21-4d87-b6a1-8cb17de38145'),
    ('b5c17a8e-4d32-4e7b-a9c8-f4a27b65e3d9', 'Sharp Shooter', 'Eliminate 10 opponents', 'https://example.com/images/achievement6.png', 'f2d3b5c6-9a21-4d87-b6a1-8cb17de38145'),

    ('f23a84d7-b19e-4b3c-a5c9-e4a12b76e9f3', 'Builder Extraordinaire', 'Build a massive castle', 'https://example.com/images/achievement7.png', 'd4f7e6a9-213a-4b8c-9f47-5a01e24c90b2'),
    ('b8f7c32e-4d93-4e2b-a1c9-f5a73b86c1e2', 'Explorer', 'Find the rare cave', 'https://example.com/images/achievement8.png', 'd4f7e6a9-213a-4b8c-9f47-5a01e24c90b2'),

    ('e7a2f9b1-4c32-4d9b-a8c9-f6a31b97e2f3', 'Portal Solver', 'Complete all levels', 'https://example.com/images/achievement9.png', 'b1f5a7d9-14c8-43ba-a71d-0f62d9e39124'),
    ('c7f3b12e-4a93-4e7c-a9b8-e3f62b41c9a8', 'Speed Runner', 'Complete the game in under 1 hour', 'https://example.com/images/achievement10.png', 'b1f5a7d9-14c8-43ba-a71d-0f62d9e39124');



-- Insert new games into store
INSERT INTO store.game (
    game_id, game_name, game_type, image_url, background_image_url, description, price, rating
)
VALUES
    (
        'f7a1c1d2-4f18-4e6a-b3a5-7dba403b62a1', 'Space Explorer', 'ADVENTURE',
        'https://placehold.co/100x80', 'https://placehold.co/1400x220',
        'An epic journey through the stars, exploring unknown planets and encountering alien civilizations.', 39.99, 4.9
    ),
    (
        'a3a4c1d2-2f12-4e6a-b3a5-7dba503b63a2', 'Zombie Survival', 'SHOOTER',
        'https://placehold.co/100x80', 'https://placehold.co/1400x220',
        'Fight your way through hordes of zombies in this intense survival game. Customize your weapons and tactics to survive.', 29.99, 4.8
    );






INSERT INTO library.game (
    game_id,
    game_name,
    game_type,
    image_url,
    favourite,
    library_id,
    background_image_url,
    description
)
VALUES (
           '5789e55e-08ad-4917-8aea-6863b6825413', -- New game ID for Checkers
           'Checkers',
           'BOARD_GAME',
           'https://placehold.co/100x80',
           false,
           '456e7891-e89b-12d3-a456-426614174000', -- Existing library ID
           'https://placehold.co/1400x220',
           'A classic board game for strategy and tactics.'
       );

-- Insert achievements for the "Checkers" game into the library.achievement table
INSERT INTO library.achievement (
    achievement_id,
    achievement_name,
    achievement_description,
    image_url,
    achieved,
    game_id
)
VALUES
    (
        '3f3f5659-8536-4b53-9fa2-547f22da47c7', -- Achievement ID for "First Move"
        'First Move',
        'Awarded for making your first move in the game.',
        'https://example.com/images/achievement_first_move.png',
        false,
        '5789e55e-08ad-4917-8aea-6863b6825413' -- Associated with Checkers game ID
    ),
    (
        'e95a1823-1628-4217-b283-7f1ba76fd9d0', -- Achievement ID for "Killed First Pawn"
        'Killed First Pawn',
        'Awarded for capturing the first pawn.',
        'https://example.com/images/achievement_killed_first_pawn.png',
        false,
        '5789e55e-08ad-4917-8aea-6863b6825413' -- Associated with Checkers game ID
    );



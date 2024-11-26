-- Insert Players
INSERT INTO player (id, nickname, firstName, lastName, gender)
VALUES
    ('a1e89a1e-76f4-4f3d-8d0e-0f812c5e91b1', 'player1', 'John', 'Doe', 'MALE'),
    ('b2f6bc5d-9911-4028-bd48-fbc1bc8391a2', 'player2', 'Jane', 'Smith', 'FEMALE');

-- Insert Addresses (linked to players)
INSERT INTO address (id, street, city, country, houseNumber, player_id)
VALUES
    ('c3e7cd8f-12d4-4a33-9f9a-0a61b456a3d4', 'Main Street', 'New York', 'USA', '123', 'a1e89a1e-76f4-4f3d-8d0e-0f812c5e91b1'),
    ('d4f8de9a-23e5-4932-bd6b-1b72c567b4e5', 'Park Avenue', 'Los Angeles', 'USA', '456', 'b2f6bc5d-9911-4028-bd48-fbc1bc8391a2');

-- Insert Friends (bidirectional relationships)
INSERT INTO friends (playerId, friendId)
VALUES
    ('a1e89a1e-76f4-4f3d-8d0e-0f812c5e91b1', 'b2f6bc5d-9911-4028-bd48-fbc1bc8391a2'),
    ('b2f6bc5d-9911-4028-bd48-fbc1bc8391a2', 'a1e89a1e-76f4-4f3d-8d0e-0f812c5e91b1');

-- Insert Lobbies (linked to players)
INSERT INTO lobby (id, playerId, gameId)
VALUES
    ('e5a9ef12-34f6-4874-bc7c-2c83d678c5e6', 'a1e89a1e-76f4-4f3d-8d0e-0f812c5e91b1', 'f6ba1023-45e7-4986-cd7d-3d94e789d6f7'),
    ('f6bae123-56f8-4a97-ce8e-4ea5f890e7f8', 'b2f6bc5d-9911-4028-bd48-fbc1bc8391a2', 'g7cbf234-67f9-4ba8-df9f-5fb6g901f8g9');

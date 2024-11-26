INSERT INTO player (id, nickname, first_name, last_name, gender)
VALUES
    ('95592601-d766-4715-a205-fbd13323ccc3', 'TheAce', 'John', 'Doe', 'MALE'),
    ('95592621-d766-4715-a205-fbd13323ccc3', 'Grigor', 'John', 'Doe', 'MALE'),
    ('95592701-d766-4715-a205-fbd13323ccc3', 'Peter', 'John', 'Doe', 'MALE'),
    ('95592821-d766-4715-a205-fbd13323ccc3', 'John', 'John', 'Doe', 'MALE'),
    ('95592301-d766-4715-a205-fbd13323ccc3', 'Rumus', 'John', 'Doe', 'MALE'),
    ('95592121-d766-4715-a205-fbd13323ccc3', 'Victor', 'John', 'Doe', 'MALE'),
    ('95593601-d766-4715-a205-fbd13323ccc3', 'Alice', 'John', 'Doe', 'MALE'),
    ('95542621-d766-4715-a205-fbd13323ccc3', 'Luffy', 'John', 'Doe', 'MALE');

insert into friends(player_id, friend_id)
values
    ('95592601-d766-4715-a205-fbd13323ccc3', '95592621-d766-4715-a205-fbd13323ccc3'),
    ('95592601-d766-4715-a205-fbd13323ccc3', '95592701-d766-4715-a205-fbd13323ccc3'),
    ('95592601-d766-4715-a205-fbd13323ccc3', '95592821-d766-4715-a205-fbd13323ccc3'),
    ('95592601-d766-4715-a205-fbd13323ccc3', '95592301-d766-4715-a205-fbd13323ccc3');

-- Insert Lobbies (linked to players)
INSERT INTO lobby (id, player_id, game_id)
VALUES
    ('e5a9ef12-34f6-4874-bc7c-2c83d678c5e6', '95592601-d766-4715-a205-fbd13323ccc3', 'f6ba1023-45e7-4986-cd7d-3d94e789d6f7'),
    ('f6bae123-56f8-4a97-ce8e-4ea5f890e7f8', '95592621-d766-4715-a205-fbd13323ccc3', '95592701-d766-4715-a205-fbd13323ccc3');

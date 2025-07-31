INSERT INTO users(username, password) VALUES
    ('admin','ISP{youtu.be/Dvf2-PvnNcw}'),
    ('wiener','peter'),
    ('carlos','hotdog')
    ON CONFLICT (username) DO NOTHING;

INSERT INTO products(id, name, description, price) VALUES
   ('p1', 'Widget', 'A vulnerable widget', 100),
   ('p2', 'Gadget', 'A useful gadget', 150),
   ('p3', 'Thingamajig', 'Mysterious and fascinating', 200),
   ('p4', 'Doodad', 'Simple but effective', 80),
   ('p5', 'Whatsit', 'Nobody knows what it does', 60),
   ('p6', 'Contraption', 'A complex contraption', 250),
   ('p7', 'Gizmo', 'Techy and trendy', 300),
   ('p8', 'Doohickey', 'It does something!', 90),
   ('p9', 'Apparatus', 'For your scientific needs', 400),
   ('p10', 'Machine', 'Runs like clockwork', 500)
    ON CONFLICT (id) DO NOTHING;

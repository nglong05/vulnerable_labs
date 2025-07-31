USE task2;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(20),
    password VARCHAR(100),
    age INT,
    student_id VARCHAR(20)
);

INSERT INTO users (username, email, phone, password, age, student_id) VALUES
('Chu Duc Anh', 'chuducanhwork@gmail.com', '0366615151', 'Anhcd05', 20, 'B23DCAT004'),
('Do Cong Du', 'zetmindu@gmail.com', '0986528186', 'dudu0712', 20, 'B23DCAT046'),
('Truong Quoc Dat', 'truongquocdat1978@gmail.com', '0978298138', 'Tdatt3027', 20, 'B23DCAT044'),
('Vu Dang Hai Dang', 'haidang2615@gmail.com', '0946362182', 'vuhaidang123@', 20, 'B23DCAT037'),
('Nguyen Nhan Manh Duc', 'reset475@gmail.com', '0585884893', 'NguyenDuc9999', 20, 'B23DCAT052'),
('Nguyen Huy Hoang', 'hoanglclvt@gmail.com', '0337593946', 'hoangnn0', 20, 'B23DCAT112'),
('Phan Duc Huy', 'duchuyphan70@gmail.com', '0941093514', 'duckhuy', 20, 'B23DCAT133'),
('Duong Thi Ngoc Huyen', 'duongngochuyen106@gmail.com', '0392745986', 'Huyendtn01', 20, 'B23DCA139'),
('Vu Duc Luong', 'luonginfosec@gmai.com', '0974835043', 'Luong4Sec2025', 20, 'B23DCAT177'),
('Ho Sy Manh', 'hosymanh05@gmail.com', '0867785373', 'ManhHacker05', 20, 'B23DCCN528'),
('Luu Tuan Minh', 'luutuanminh01042005@gmail.com', '0392014396', 'MinhTuan420', 20, 'B23DCAT195'),
('Le Tuan Nam', 'letuannam14@gmail.com', '0972090106', 'NamSuper14', 20, 'B23DCAT204'),
('Duong Thien Ngan', 'thiennganduong0802@gmail.com', '0383185335', 'ThienNgan0802', 20, 'B23DCAT209'),
('Dinh Yen Nhi', 'yennhi030305@gmail.com', '0918738608', 'YenNhi303', 20, 'B23DCAT229'),
('Doan Duy Phuc', 'dphuc2304@gmail.com', '0971623861', 'PhucDuy04', 20, 'B23DCAT238'),
('Tran Duy Phuong', 'phuong681875@gmail.com', '0986476599', 'PhuongTran68', 20, 'B23DCAT243'),
('Nguyen Le Dang Quang', 'nldangquang1606@gmail.com', '0965351856', 'QuangDang1606', 20, 'B23DCAT250'),
('Le Thi Phuong Thao', 'lethiphthao125@gmail.com', '0388007519', 'ThaoLe125', 20, 'B23DCAT281'),
('Nguyen Duc Trinh', 'trinhdz19082005@gmail.com', '0974190805', 'TrinhNguyen05', 20, 'B23DCAT301'),
('Phung Quoc Viet', 'phungquocviet0608@gmail.com', '0979616805', 'Viet0608123', 20, 'B23DCAT337'),
('Nguyen Van Long', 'long82510@gmail.com', '0382217003', 'ISP{youtu.be/nhgEuhL0jxI}', 20, 'B23DCAT174'),
('Nguyen Quoc Viet', 'vn1363979@gmail.com', '0369973916', 'VietQ136', 20, 'B23DCKD081');

CREATE TABLE drink (
    name VARCHAR(50),
    price DECIMAL(10,2),
    stock INT,
    rating DECIMAL(3,2)
);

INSERT INTO drink (name, price, stock, rating) VALUES
('Coca-Cola', 1.50, 50, 4.50),
('Pepsi', 1.45, 40, 4.30),
('Sprite', 1.30, 25, 4.00),
('Fanta', 1.60, 35, 4.20),
('Mountain Dew', 1.80, 15, 4.60);

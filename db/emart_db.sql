-- ============================================================
--  emart_db.sql  -  QLST dien may (De so 9)  -  MySQL (XAMPP)
--  Chay trong phpMyAdmin (XAMPP) hoac: mysql -u root < emart_db.sql
-- ============================================================
DROP DATABASE IF EXISTS emart_db;
CREATE DATABASE emart_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE emart_db;

-- ---------- Nguoi dung / tac nhan ----------
CREATE TABLE tblUser9 (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(100) NOT NULL,
    password  VARCHAR(100) NOT NULL,
    fullName  VARCHAR(100),
    address   VARCHAR(100),
    role      VARCHAR(50)          -- Manager | SalesStaff | WarehouseStaff | Customer
);

-- Bang con ke thua tblUser9 (class-table inheritance). User9id vua la PK vua la FK (quan he 1-1).
CREATE TABLE tblManager9 (
    User9id INT PRIMARY KEY,
    FOREIGN KEY (User9id) REFERENCES tblUser9(id)
);
CREATE TABLE tblSalesStaff9 (
    User9id INT PRIMARY KEY,
    FOREIGN KEY (User9id) REFERENCES tblUser9(id)
);
CREATE TABLE tblWarehouseStaff9 (
    User9id INT PRIMARY KEY,
    FOREIGN KEY (User9id) REFERENCES tblUser9(id)
);

CREATE TABLE tblCustomer9 (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    fullName  VARCHAR(100),
    phone     VARCHAR(20),
    email     VARCHAR(100)
);

CREATE TABLE tblDeliveryStaff9 (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    fullName  VARCHAR(100),
    phone     VARCHAR(20)
);

CREATE TABLE tblSupplier9 (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(100),
    address   VARCHAR(100),
    phone     VARCHAR(20),
    email     VARCHAR(100)
);

-- ---------- Danh muc mat hang ----------
CREATE TABLE tblProduct9 (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(150),
    category    VARCHAR(50),
    brand       VARCHAR(50),
    unit        VARCHAR(30),
    price       DOUBLE,
    description VARCHAR(255),
    status      VARCHAR(50)          -- Con hang | Het hang
);

-- ---------- Nhap hang ----------
CREATE TABLE tblImportNote9 (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    importDate  DATE,
    totalAmount DOUBLE,
    Supplier9id INT,
    FOREIGN KEY (Supplier9id) REFERENCES tblSupplier9(id)
);

CREATE TABLE tblImportDetail9 (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    quantity      INT,
    importPrice   DOUBLE,
    ImportNote9id INT,
    Product9id    INT,
    FOREIGN KEY (ImportNote9id) REFERENCES tblImportNote9(id),
    FOREIGN KEY (Product9id)    REFERENCES tblProduct9(id)
);

-- ---------- Ban hang / dat hang truc tuyen ----------
CREATE TABLE tblOrder9 (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    orderDate       DATETIME,
    status          VARCHAR(50),     -- Chua xuat | Da xuat | Da giao
    totalAmount     DOUBLE,
    Customer9id     INT,
    DeliveryStaff9id INT,            -- NULL khi chua gan NV giao hang
    FOREIGN KEY (Customer9id)      REFERENCES tblCustomer9(id),
    FOREIGN KEY (DeliveryStaff9id) REFERENCES tblDeliveryStaff9(id)
);

CREATE TABLE tblOrderDetail9 (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    quantity   INT,
    price      DOUBLE,
    Order9id   INT,
    Product9id INT,
    FOREIGN KEY (Order9id)   REFERENCES tblOrder9(id),
    FOREIGN KEY (Product9id) REFERENCES tblProduct9(id)
);

CREATE TABLE tblInvoice9 (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    exportDate     DATETIME,
    status         VARCHAR(50),
    totalAmount    DOUBLE,
    note           VARCHAR(255),
    Order9id       INT,
    Customer9id    INT,
    FOREIGN KEY (Order9id)    REFERENCES tblOrder9(id),
    FOREIGN KEY (Customer9id) REFERENCES tblCustomer9(id)
);

CREATE TABLE tblInvoiceDetail9 (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    quantity    INT,
    price       DOUBLE,
    Invoice9id  INT,
    Product9id  INT,
    FOREIGN KEY (Invoice9id) REFERENCES tblInvoice9(id),
    FOREIGN KEY (Product9id) REFERENCES tblProduct9(id)
);

-- ============================================================
--  DU LIEU MAU
-- ============================================================
INSERT INTO tblUser9 (username, password, fullName, address, role) VALUES
 ('kh01',  '123', 'Nguyen Van An',  'Ha Noi',    'Customer'),
 ('kho01', '123', 'Tran Thi Kho',   'Ha Noi',    'WarehouseStaff'),
 ('ban01', '123', 'Le Van Ban',     'Ha Noi',    'SalesStaff'),
 ('ql01',  '123', 'Pham Quan Ly',   'Ha Noi',    'Manager');

-- Phan loai nhan vien (ke thua): id 2=kho01, 3=ban01, 4=ql01
INSERT INTO tblWarehouseStaff9 (User9id) VALUES (2);
INSERT INTO tblSalesStaff9 (User9id) VALUES (3);
INSERT INTO tblManager9 (User9id) VALUES (4);

INSERT INTO tblCustomer9 (fullName, phone, email) VALUES
 ('Nguyen Van An',  '0988123456', 'an@gmail.com'),
 ('Tran Thi Binh',  '0977222333', 'binh@gmail.com');

INSERT INTO tblDeliveryStaff9 (fullName, phone) VALUES
 ('Vu Anh Tuan',  '0901111222'),
 ('Do Thu Ha',    '0902333444'),
 ('Ngo Van Giao', '0903555666');

INSERT INTO tblSupplier9 (name, address, phone, email) VALUES
 ('Samsung VN',   'HCM', '02838000000', 'contact@samsung.vn'),
 ('LG Electronics','HN', '02439000000', 'contact@lg.vn');

INSERT INTO tblProduct9 (name, category, brand, unit, price, description, status) VALUES
 ('Tivi Samsung 55 inch 4K', 'Tivi',        'Samsung',  'Chiec', 12500000, 'Smart TV 55 inch', 'Con hang'),
 ('Tu lanh LG Inverter 300L', 'Tu lanh',    'LG',       'Chiec', 9800000,  'Tu lanh 2 canh',   'Con hang'),
 ('May giat Samsung 9kg',     'May giat',   'Samsung',  'Chiec', 7200000,  'Cua truoc inverter','Con hang'),
 ('Dieu hoa LG 12000 BTU',    'Dieu hoa',   'LG',       'Chiec', 8600000,  '1 chieu inverter',  'Con hang'),
 ('Laptop Dell Inspiron 15',  'Laptop',     'Dell',     'Chiec', 15500000, 'Core i5, 8GB RAM',  'Con hang'),
 ('Tivi Sony Bravia 43 inch', 'Tivi',       'Sony',     'Chiec', 9900000,  'Google TV 43 inch', 'Het hang');

-- Don hang truc tuyen mau: 2 don "Chua xuat" + 1 don "Da xuat"
INSERT INTO tblOrder9 (orderDate, status, totalAmount, Customer9id, DeliveryStaff9id) VALUES
 ('2026-01-10 09:30:00', 'Chua xuat', 22300000, 1, NULL),
 ('2026-01-11 14:00:00', 'Chua xuat', 15500000, 2, NULL),
 ('2026-01-05 10:00:00', 'Da xuat',    8600000, 1, 1);

INSERT INTO tblOrderDetail9 (quantity, price, Order9id, Product9id) VALUES
 (1, 12500000, 1, 1),
 (1,  9800000, 1, 2),
 (1, 15500000, 2, 5),
 (1,  8600000, 3, 4);

INSERT INTO tblInvoice9 (exportDate, status, totalAmount, note, Order9id, Customer9id) VALUES
 ('2026-01-05 11:00:00', 'Da xuat', 8600000, 'Giao trong ngay', 3, 1);

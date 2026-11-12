# EMart9 — QLST điện máy (Đề số 9)

App Java Swing theo mô hình **MVC 3 lớp** + **MySQL (XAMPP)**, 
## Cấu trúc 
```
src/com/emart/
  EMartApp9.java            <- main
  model/                    <- lớp thực thể (Model)  — map Chương 2 phân tích
  dao/                      <- DAO (Control)         — DAO9 kết nối + các DAO module
  ui/                       <- JFrame (View)         — login + 2 module
db/emart_db.sql             <- CSDL MySQL + dữ liệu mẫu
```

Kiến trúc 3 lớp: **View (ui) → Control (dao) → Model (model)**, giống `com.restman` của bài mẫu
(View/Control/Model), đổi tên theo đề điện máy + hậu tố `9`.

## Phạm vi (2 module + login)
- Đăng nhập: `MainFrm9`
- Khách hàng → `CustomerMainFrm9` → **Tìm kiếm mặt hàng**: `SearchProductFrm9` → `ProductDetailFrm9`
- NV kho → `WarehouseMainFrm9` → **Duyệt đơn + xuất kho**: `PendingOrderFrm9` →
  `DeliveryStaffListFrm9`, `InvoiceFrm9`

## Cách chạy (sau khi đã cài JDK)
1. **Bật MySQL trong XAMPP** (Control Panel → Start MySQL).
2. **Nạp CSDL**: mở phpMyAdmin (http://localhost/phpmyadmin) → Import → chọn `db/emart_db.sql`.
   (hoặc chạy: `C:\xampp\mysql\bin\mysql -u root < db\emart_db.sql`)
3. **Tải driver** `mysql-connector-j-8.x.x.jar` để vào thư mục `lib/`. (đã có)
4. **Biên dịch & chạy** (từ thư mục EMart9):
   ```
   javac -encoding UTF-8 -cp "lib/*" -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
   java -cp "out;lib/*" com.emart.EMartApp9
   ```
   (hoặc mở project bằng NetBeans/IntelliJ, add jar vào Libraries, Run `EMartApp9`.)

## Tài khoản mẫu (mật khẩu đều là `123`)
| username | role | vào màn |
|---|---|---|
| kh01  | Customer       | CustomerMainFrm9 (tìm kiếm mặt hàng) |
| kho01 | WarehouseStaff | WarehouseMainFrm9 (duyệt đơn + xuất kho) |

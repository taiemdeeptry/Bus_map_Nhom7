# Công nghệ Java

## Đề tài: Xây dựng ứng dụng tra cứu xe buýt cho tỉnh Quảng Bình

<hr>

## DANH SÁCH THÀNH VIÊN

<ul>
 <li>21520260 - Nguyễn Đức Huy</li>
 <li>20520610 - Nguyễn Cương Lĩnh</li>
 <li>21521977 - Nguyễn Tấn Dũng</li>
 <li>20522024 - Nguyễn Đức Toàn</li>
 <li>20521184 - Nguyễn Đăng Điền</li>
</ul> <hr>

## KIẾN TRÚC VÀ CÔNG NGHỆ

### 1. Ngôn ngữ và Công cụ

-   **Ngôn ngữ lập trình**: Java
-   **IDE**: Android Studio
-   **Hệ điều hành**: Android

### 2. Kiến trúc ứng dụng

-   **Mô hình**: MVC (Model-View-Controller)
    -   Model: Các lớp trong package `entities/`
    -   View: Các file layout XML trong `res/layout/`
    -   Controller: Các Activity và lớp xử lý logic

### 3. Thư viện sử dụng

-   **Google Maps SDK**
    -   Maps SDK for Android
    -   Places API
    -   Geocoding API
    -   Directions API
-   **AndroidX**
-   **Material Design Components**

### 4. Cơ sở dữ liệu

-   **SQLite**: Cơ sở dữ liệu local
-   **Các bảng chính**:
    -   users
    -   routes
    -   stations
    -   bus_stops
    -   saved_routes
    -   saved_stations

### 5. Cấu trúc thư mục

```
app/src/main/
├── java/UIT/IE303/BUSMAP/
│   ├── entities/          # Model
│   ├── data/             # Data Access Layer
│   ├── route/            # Route features
│   ├── station/          # Station features
│   ├── user/             # User management
│   ├── address/          # Address search
│   ├── result/           # Search results
│   ├── saved/            # Saved items
│   ├── feedback/         # User feedback
│   └── notification/     # Notifications
└── res/
    └── layout/           # View
```

### 6. Tính năng chính

-   **Xác thực**

    -   Đăng nhập/Đăng ký
    -   Quản lý thông tin người dùng
    -   Lưu trữ local

-   **Bản đồ**

    -   Hiển thị bản đồ Google Maps
    -   Tìm kiếm địa điểm
    -   Tính toán tuyến đường

-   **Quản lý dữ liệu**

    -   Lưu trữ local với SQLite
    -   Cập nhật dữ liệu
    -   Lưu trữ yêu thích

-   **Chatbox**
    -   Hỗ trợ trò chuyện trực tiếp
    -   Gửi và nhận tin nhắn
    -   Lưu trữ lịch sử chat

### 7. Bảo mật

-   Lưu trữ mật khẩu local
-   Xác thực người dùng
-   Kiểm tra quyền truy cập

### 8. UI/UX

-   Material Design
-   Custom components
-   Responsive layouts
-   Navigation drawer
-   Bottom navigation

### 9. Tính năng bổ sung

-   Đánh giá ứng dụng
-   Gửi phản hồi
-   Thông báo
-   Cập nhật dữ liệu

## CÁC THÔNG TIN LIÊN QUAN

[link báo cáo]()

## HƯỚNG DẪN CÀI ĐẶT

-   B1: Cài Android Studio và JDK để chạy app

-   B2: Import project vào Android Studio

-   B3: Nhấn Build app để tự động cài các thư viện và các gói cài đặt

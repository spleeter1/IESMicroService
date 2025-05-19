# IESMicroService

**Eureka Server**

-   spring-cloud-starter-netflix-eureka-server

**API Gateway**

-   spring-boot-starter-webflux
-   spring-cloud-starter-gateway,
-   spring-cloud-starter-netflix-eureka-client

**Microservices**

-   spring-boot-starter-web,
-   spring-cloud-starter-netflix-eureka-client,
-   spring-boot-starter-data-jpa,
-   mysql-connector-java,
-   spring-cloud-starter-openfeign
- lombok nữa

**Port**
- api-gatewayy: 8080
- import-order: 8081
- order: 8082
- statistic: 8083
- product: 8084
- user : 8085

docker exec -it kafka-docker-kafka-1 \
kafka-topics --create \
--topic my-topic \
--bootstrap-server localhost:9092

**Trình tự triển khai JWT Security**

Các bước thực hiện theo thứ tự logic để triển khai bảo mật JWT cho kiến trúc microservices

**Bước 1:** Cập nhật User Service

- Bổ sung chức năng xác thực và tạo JWT token trong user-service hiện có.
- Thêm các dependency cần thiết (Spring Security, JWT)
- Tạo các model classes (JwtRequest, JwtResponse)
- Tạo JwtTokenUtil để xử lý việc tạo và xác thực token
- Tạo AuthController với endpoint đăng nhập
- Cấu hình Spring Security

**Bước 2:** Cấu hình API Gateway
- Cập nhật API Gateway để xác thực JWT token và chuyển tiếp thông tin xác thực đến các service khác.
- Thêm các dependency cần thiết (JWT)
- Tạo JwtUtil để xác thực token
- Tạo JwtAuthenticationFilter để lọc các request
- Cấu hình routes trong application.yml

**Bước 3:** Cấu hình các Service khác
- Cấu hình các service khác để tin tưởng thông tin xác thực từ API Gateway.
- Cập nhật controller để đọc thông tin người dùng từ header
- Thêm logic kiểm tra quyền truy cập dựa trên thông tin người dùng

**Bước 4:** Kiểm thử và triển khai
Kiểm thử toàn bộ luồng xác thực và triển khai hệ thống.

- Kiểm thử đăng nhập và lấy token
- Kiểm thử truy cập các API được bảo vệ
- Kiểm thử truy cập trái phép
- Triển khai lên môi trường production
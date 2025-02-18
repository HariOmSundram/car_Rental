🚗 Car Rental Service
This is a full-stack Car Rental Service application that allows users to browse, book, and manage car rentals efficiently. The platform provides a seamless experience for customers, car rental agencies, and administrators with features like user authentication, agency registration, and payment integration.

🌟 Features
User Authentication – Secure login and registration using JWT.
Role-Based Access Control – Separate dashboards for Customers, Agencies, and Admins.
Car Search & Booking – Filter cars by city, category, and availability.
Agency Management – Register, update, and manage agency profiles and cars.
Payment Integration – Secure payment gateway integration.
Microservices Architecture – Using Spring Cloud Gateway and Discovery for routing and service registry.
Responsive UI – Built with React for a dynamic and user-friendly experience.
🛠️ Tech Stack
Frontend
React – For building a dynamic and responsive UI.
Redux – State management.
Axios – For API calls.
React Hook Form – Form validation and handling.
Backend
Spring Boot – Microservices architecture for modular design.
Spring Security – JWT-based authentication and authorization.
Hibernate – ORM for database operations.
Spring Data JPA – For interacting with the database.
Spring Cloud Gateway – API Gateway for routing requests.
Eureka Discovery – Service registry and discovery.
Database
MySQL – Relational database to store user, car, booking, and payment information.
Build Tools
Maven – Dependency management and build automation for Spring Boot.
npm – Package management for React.
⚙️ Dependencies
Frontend (React)
json
Copy
Edit
"axios": "^1.x.x",
"redux": "^4.x.x",
"react-redux": "^7.x.x",
"react-router-dom": "^6.x.x",
"react-hook-form": "^7.x.x",
"bootstrap": "^5.x.x"
Backend (Spring Boot & Microservices)
Spring Boot Starter Web – To build RESTful APIs.
Spring Boot Starter Security – For JWT authentication.
Spring Boot Starter Data JPA – ORM integration with Hibernate.
Spring Boot Starter Validation – For request validation.
Spring Cloud Starter Gateway – API Gateway.
Spring Cloud Starter Eureka Server – Service Discovery.
MySQL Connector – For database connection.
Lombok – To reduce boilerplate code.
Database (MySQL)
MySQL 8.x.x – Database for storing application data.
Database Schema – Includes tables for Users, Roles, Cars, Bookings, Payments, and Agencies.
📁 Folder Structure
bash
Copy
Edit
car-rental-service/
├── backend/
│   ├── api-gateway/          # Spring Cloud Gateway
│   ├── discovery-server/     # Eureka Discovery Server
│   ├── user-service/         # User management microservice
│   ├── car-service/          # Car and agency management
│   ├── booking-service/      # Booking management
│   └── payment-service/      # Payment integration
└── frontend/
    └── car-rental-frontend/  # React application
⚙️ Configuration
Backend
application.properties
properties
Copy
Edit
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/car_rental_db
spring.datasource.username=root
spring.datasource.password=your_password

# JPA & Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=your_secret_key
jwt.expiration=86400000  # 24 hours

# Eureka Discovery
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
Frontend
.env
env
Copy
Edit
REACT_APP_API_URL=http://localhost:8080/api
🚀 Getting Started
1. Clone the Repository
bash
Copy
Edit
git clone https://github.com/yourusername/car-rental-service.git
cd car-rental-service
2. Database Setup
Create a MySQL database named car_rental_db.
Update the database credentials in application.properties.
The database schema will be auto-generated using Hibernate.
3. Backend Setup
bash
Copy
Edit
cd backend
./mvnw clean install
Start the Eureka Discovery Server:
bash
Copy
Edit
cd discovery-server
./mvnw spring-boot:run
Start the API Gateway:
bash
Copy
Edit
cd api-gateway
./mvnw spring-boot:run
Start Microservices (User, Car, Booking, Payment):
bash
Copy
Edit
cd user-service
./mvnw spring-boot:run
4. Frontend Setup
bash
Copy
Edit
cd frontend/car-rental-frontend
npm install
npm start
The application will be available at: http://localhost:3000
🧪 Testing
Postman – For API testing.
Jest – Unit testing for React components.
🚧 Challenges Faced
Handling Complex State Management – Solved using Redux.
API Integration Issues – Managed with Axios interceptors and error handling.
JWT Token Expiry and Refresh – Implemented custom logic for token renewal.
Cross-Origin Requests (CORS) – Fixed using Spring's CORS configuration.
Service Communication in Microservices – Managed using Eureka Discovery and Spring Cloud Gateway.
🔒 Security Measures
JWT Authentication – Secured APIs with role-based authorization.
Input Validation – Using Spring Validation and React Hook Form.
Password Encryption – BCrypt for storing passwords securely.
📖 Documentation
API Documentation – Created using Swagger.
Code Documentation – Inline comments and Javadoc for Spring Boot services.
📌 Future Enhancements
Implementing .NET Microservices – For better scalability and technology diversification.
Dockerization – Containerizing the application for cloud deployment.
CI/CD Integration – Using Jenkins or GitHub Actions.
Enhanced UI/UX – Improving user experience with better design and animations.
📝 License
This project is licensed under the CDAC.

📧 Contact
For any queries or feedback, feel free to reach out at hariomr615@.com.

# demo-test-assignment

## Overview
This project is a simple CRUD application for managing user entities. It includes endpoints for creating, finding, updating, and deleting users. The application does not include Spring Security implementation.

## Features
- **User Entity:** Represents the user data with attributes such as ID, first name, last name, email, and birth date.
- **User Repository:** Provides CRUD operations for interacting with the database.
- **User Service:** Implements business logic for user-related operations.
- **User Controller:** Handles incoming HTTP requests and calls the appropriate service methods.

## Endpoints
- `POST /create`: Creates a new user with the provided details.
- `GET /find`: Finds users based on the provided birth date range.
- `PUT /update`: Updates an existing user with the provided details.
- `DELETE /delete`: Deletes a user with the specified ID.

## Validation
- **Date Range Validation:** Ensures that the start date is before the end date when searching for users.
- **User Age Validation:** Validates the age of the user during registration. The minimum and maximum age limits are specified in the `application.properties` file.

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Maven

## Setup
1. Clone the repository: `git clone [https://github.com/cloud9cloud9/demo-test-assignment.git]`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. Access the application endpoints using a REST client or web browser.

## Configuration
- The minimum and maximum age limits for user registration are configured in the `application.yml` file.
- Database configuration (if applicable) can be modified in the same file.

## Contributors
- [Vladyslav Nalezhytyi]
- [nalezhitiyvlad@gmail.com]

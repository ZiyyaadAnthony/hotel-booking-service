# Hotel Booking Service API

## Overview

This is a hotel booking service built using Spring Boot. The API allows you to manage hotel bookings by creating, retrieving, updating, and deleting reservations.

## Requirements

- Java 17
- Maven
- Docker

## Running the Application

### Local Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/ZiyyaadAnthony/hotel-booking-service.git
   cd hotel-booking-service
   ```

2. **Run the Application Locally**

   Use the following command to run the application locally:

   ```bash
   mvn spring-boot:run
   ```

   The application will be accessible at `http://localhost:8080/api/bookings`.

### Running with Docker

1. **Build the Docker Image**

   Navigate to your project directory (where the `Dockerfile` is located) and run:

   ```bash
   docker build -t hotel-booking-service .
   ```

2. **Run the Docker Container**

   After building the image, run the following command:

   ```bash
   docker run -p 8080:8080 hotel-booking-service
   ```

   The application will be accessible at `http://localhost:8080/api/bookings`.

## API Endpoints

### 1. Create a Booking

- **Endpoint**: `POST /api/bookings`
- **Request Body**:
  
  ```json
  {
      "BookingId": "1",
      "customerName": "MZ Anthony",
      "numberOfGuests": "3",
      "checkInDate": "2024-10-02",
      "checkOutDate": "2024-10-12"
  }
  ```

- **Response**: Returns the created booking with a status code 201.

### 2. Get All Bookings

- **Endpoint**: `GET /api/bookings`
- **Response**: Returns a list of all bookings with a status code 200.

### 3. Update a Booking

- **Endpoint**: `PUT /api/bookings/{id}`
- **Request Body**:

  ```json
  {
      "customerName": "MZ Anthony",
      "numberOfGuests": "4",
      "checkInDate": "2024-10-05",
      "checkOutDate": "2024-10-15"
  }
  ```

- **Response**: Returns the updated booking with a status code 200.

### 4. Delete a Booking

- **Endpoint**: `DELETE /api/bookings/{id}`
- **Response**: Returns a status code 204 if the deletion was successful.

## Testing the Application

### Unit and Integration Tests

To run the tests, use the following command:

```bash
mvn test
```


## Conclusion

This README provides instructions on how to set up and run the Hotel Booking Service API. For any questions or issues, please feel free to reach out.

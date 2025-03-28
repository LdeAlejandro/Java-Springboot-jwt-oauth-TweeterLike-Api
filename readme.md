# Java JWT OAuth Tweeter-like API

This is a simple Spring Boot application that implements an API resembling a tweeter-like platform with JWT authentication and OAuth2. Users can create and manage tweets, retrieve feeds, and perform CRUD operations, including creating users, all secured by JWT tokens.

## API Endpoints

### 1. **Login**
- **POST** `http://localhost:8080/login`
  
  **Request Body**:
  ```json
  {
      "username": "admin",
      "password": "123"
  }
  ```

**Response**:
- Returns a JWT token in the `Authorization` header (Bearer token).

**Example Response**:
  ```json
  {
      "access_token": "your-jwt-token-here"
  }
  ```

### 2. **Create User**
- **POST** `http://localhost:8080/users`

  **Request Body**:
  ```json
  {
      "username": "juanito",
      "password": "321"
  }
  ```

  **Response**:
    - Anyone can create a new user.

### 3. **Get Feed**
- **GET** `http://localhost:8080/feed?pageSize=3&page=1`

  **Headers**:
    - `Authorization: Bearer <your-jwt-token-here>`

  **Response**:
    - Returns a paginated list of tweets (default page size is 10, adjust using query parameters).

### 4. **Get Users List (Admin Only)**
- **GET** `http://localhost:8080/users`

  **Headers**:
    - `Authorization: Bearer <your-jwt-token-here>`

  **Response**:
    - Returns a list of users, but **only accessible by admin** users.

### 5. **Create Tweet**
- **POST** `http://localhost:8080/tweets`

  **Headers**:
    - `Authorization: Bearer <your-jwt-token-here>`

  **Request Body**:
  ```json
  {
      "content": "This is a new tweet!"
  }
  ```

  **Response**:
    - Creates a new tweet in the system.

### 6. **Delete Tweet**
- **DELETE** `http://localhost:8080/tweets/{tweetId}`

  **Headers**:
    - `Authorization: Bearer <your-jwt-token-here>` (User must be the owner of the tweet or an admin to delete it)

  **Response**:
    - Deletes a tweet if the user is the owner or has an admin role.
---

## Technologies Used
- **Spring Boot**: For building the API.
- **JWT Authentication**: For securing endpoints.
- **OAuth2**: For handling authorization.
- **Mysql Database**: In-memory database for development.
- **Maven**: For project management and dependency management.

---


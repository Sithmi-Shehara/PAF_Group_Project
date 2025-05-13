# PAF Backend Project

This is the backend service for the PAF application, built using Java and Spring Boot. It provides REST APIs for managing posts and notifications.

## Prerequisites

Before you begin, ensure you have met the following requirements:

*   **Java Development Kit (JDK):** Version 17 or later. ([Download OpenJDK](https://openjdk.java.net/))
*   **Apache Maven:** Version 3.6 or later. ([Download Maven](https://maven.apache.org/download.cgi))
*   **MongoDB:** A running instance of MongoDB (version 4.x or later recommended) or a MongoDB Atlas account. ([Install MongoDB](https://docs.mongodb.com/manual/installation/) | [MongoDB Atlas](https://www.mongodb.com/cloud/atlas))

## Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone <your-repository-url>
    cd PAF_Backend 
    ```

2.  **Configure Database Connection:**
    *   Open the `src/main/resources/application.properties` file.
    *   Update the `spring.data.mongodb.uri` property with your MongoDB connection string. If the file doesn't exist, you might need to create it.
      Example:
      ```properties
      spring.data.mongodb.uri=mongodb://localhost:27017/paf_database 
      # Replace 'paf_database' with your desired database name
      ```

3.  **Configure Media Upload Directory:**
    *   Open `src/main/java/com/example/PAF/service/PostService.java`.
    *   Locate the `IMAGE_DIRECTORY` constant.
    *   Update the path to a valid directory on your system where uploaded post media (images/videos) should be stored. Ensure the application has write permissions to this directory.
      ```java
      // Example: Update this path
      private static final String IMAGE_DIRECTORY = "/path/to/your/upload/directory/"; 
      ```

4.  **Build the project:**
    Use Maven to compile the project and download dependencies.
    ```bash
    mvn clean install
    ```

## Running the Application

Once the setup is complete, you can run the application using Maven:

```bash
mvn spring-boot:run
```

The application will start, typically on port 8080 (or as configured in `application.properties`).

## Technologies Used

*   **Java 17**
*   **Spring Boot** (Web, Data MongoDB)
*   **Maven**
*   **MongoDB**

## API Endpoints

The main API endpoints are typically available under the `/api` prefix. Refer to the controller classes for specific paths:

*   `PostController`: `/api/posts` (Manage posts)
*   `NotificationController`: `/api/notifications` (Manage notifications)
*   `CommentController`: `/api/comments` (Manage comments on posts)
*   `LikeController`: `/api/likes` (Manage likes on posts)
*   `LearningProgressUpdateController`: `/api/progress` (Manage learning progress updates, including their comments and likes)

*(Please refer to the specific controller classes for detailed endpoint mappings and request/response formats.)* 
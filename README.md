# Game of Life

This project contains the code for a web application that simulates the [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) cellular automaton. The project is split into two components:

- **frontend**: a React-based user interface that allows users to interact with the game and see its current state.
- **backend**: a Spring Boot-based API that implements the game logic and exposes it through REST endpoints.

## Prerequisites

Before running the application, you need to ensure that you have the following software installed on your machine:

- Node.js (v14 or later)
- npm (v7 or later)
- Java (v17 or later)

## Running the Application

To run the application in development mode, you need to start both the frontend and the backend servers. Follow the steps below:

1. Open a terminal window and navigate to the **backend** directory.
2. Run the following command to build the Spring Boot application:

```
./mvnw clean install
```

3. Once the build is complete, run the following command to start the backend server:

```
./mvnw spring-boot:run
```

4. This will start the backend server at http://localhost:8080/

5. Open a terminal window and navigate to the **frontend** directory.
6. Run the following command to install the required dependencies:

```
npm install
```

7. Once the installation is complete, run the following command to start the frontend server:

```
npm start
```

8. Once both servers are up and running, open your web browser and navigate to [http://localhost:3000](http://localhost:3000) to access the application.

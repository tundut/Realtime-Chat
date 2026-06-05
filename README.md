# Realtime Chat

Realtime Chat is a real-time chat application with a Spring Boot backend and a React + Vite frontend. The app supports user registration, login, conversation listing, real-time messaging over WebSocket, and user search to start a new chat.

## Project Architecture

- `backend/` - Spring Boot application
  - Java 21
  - Spring Web, Spring Security, Spring Data JPA, Spring WebSocket
  - PostgreSQL database
  - JWT authentication
  - REST APIs for auth, conversations, messages, and user search
- `frontend/` - React + Vite application
  - React, Tailwind CSS, Axios, STOMP/WebSocket
  - Chat UI with conversation list, message view, and user search

## Prerequisites

- Java 21
- Maven (or use included `mvnw` / `mvnw.cmd`)
- Node.js + npm
- PostgreSQL

## Backend Configuration

The backend uses PostgreSQL by default. Update `backend/src/main/resources/application.yaml` if needed.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/realtime_chat
    username: postgres
    password: 123456
```

The JWT configuration is also stored in the same file.

## Running the Backend

1. Install dependencies and build:

```bash
cd backend
./mvnw.cmd clean package
```

2. Run the backend server:

```bash
./mvnw.cmd spring-boot:run
```

The backend runs at `http://localhost:8080` by default.

## Running the Frontend

1. Install dependencies:

```bash
cd frontend
npm install
```

2. Start the development server:

```bash
npm run dev
```

The frontend runs on `http://localhost:5173` by default (or the port provided by Vite).

## API Endpoints

- `POST /api/auth/register` - register new user
- `POST /api/auth/login` - login user
- `GET /api/users/me` - get current user info
- `GET /api/users/search?q={query}` - search users by username
- `GET /api/conversations` - get conversation list
- `POST /api/conversation?username={username}` - create or retrieve a conversation with a user
- `GET /api/conversation/{conversationId}` - get conversation details
- `GET /api/messages/{conversationId}` - get messages for a conversation

## Current Features

- JWT-based user registration and login
- Recent conversation list
- Real-time messaging using WebSocket/STOMP
- User search to start a new chat
- Message view and chat UI

## Notes

- The frontend stores JWT tokens in `localStorage` and sends them in the `Authorization` header.
- If API endpoints or ports change, update `frontend/src/api/axiosClient.js`.
- Make sure the backend is running before starting the frontend.

## Folder Structure

- `backend/src/main/java/com/tundut/realtime_chat/` - backend source code
- `backend/src/main/resources/application.yaml` - backend configuration
- `frontend/src/` - frontend source code
- `frontend/src/components/` - React UI components
- `frontend/src/services/` - frontend API service modules

## Recommended Improvements

- Add real-time notifications for new messages
- Improve state management and routing
- Add mobile responsiveness and better UX
- Prepare production deployment with HTTPS and a custom domain

## Useful Commands

- Backend tests and build:

```bash
cd backend
./mvnw.cmd test
./mvnw.cmd package
```

- Frontend lint and build:

```bash
cd frontend
npm run lint
npm run build
```

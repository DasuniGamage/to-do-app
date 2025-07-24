# To-Do Task Web Application

A simple and responsive To-Do Task web app built with React for the frontend and a RESTful API backend.  
Users can add tasks, mark them as completed, and see the latest pending tasks. Completed tasks are automatically hidden.

---

## Features

- Add new tasks with title and description  
- Display latest 5 tasks sorted by creation time  
- Mark tasks as completed (tasks disappear from the list)  
- Responsive UI built with Material-UI (MUI) components  
- Backend integration with REST API endpoints for task management  
- Client-side validation and user-friendly form interactions  
- Unit and integration tests for frontend and backend

---

## Tech Stack

- Frontend: React, Material-UI, React Testing Library, Vitest  
- Backend: Java Spring Boot, REST API  
- API communication: Axios  
- Testing: Vitest, Jest DOM, React Testing Library, JUnit & Mockito (backend)

---
## Getting Started

Follow these steps to run the To-Do Task Web App using Docker only:
### Clone the Repository

```bash
git clone https://github.com/DasuniGamage/to-do-app.git
cd to-do-app
```
### Build and Start All Services

```bash
docker-compose up --build
```
This will:

- Set up a PostgreSQL database
- Build and run the Spring Boot backend
- Build and serve the React frontend

### Access the App

Once all containers are running, open:
- Frontend: http://localhost:3000
- Backend API (for testing): http://localhost:8080/api/tasks/latest

---

## Future Improvements

- Add user authentication and authorization
- Support editing and deleting tasks
- Add filtering and search for tasks
- Implement persistent user sessions and task history
- Improve UI/UX with animations and better accessibility

---

## Contact
For any questions or suggestions, feel free to open an issue or contact dasuninirmani@gmail.com.

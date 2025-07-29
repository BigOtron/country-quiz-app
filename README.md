# Do You Know This Country?

**Do You Know This Country?** is a web-based quiz application that helps users
learn about countries around the world. The quiz includes questions about capitals, 
flags, languages, currencies, regions, and more. All questions are generated in real 
time using the [REST Countries API](https://restcountries.com/).

## 🌍 Target Audience

- Students interested in world geography
- Travelers and culture lovers
- Anyone who enjoys trivia and quiz-based learning

## 🧰 Technologies Used

- **Backend:** Java, Spring Boot, Spring Web, Spring Data JPA
- **Database:** PostgreSQL (Dockerized)
- **API Integration:** REST Countries API
- **Build Tool:** Maven
- **Version Control:** Git & GitHub
- **Security:** Spring Security with basic registration and login

## 🔐 Security & User Features

- Users can **register** and **log in**
- Each user can **take quizzes**
- Quiz **results are saved** to the database and tied to the user
- Authentication is handled with **Spring Security**

## ⚙️ Architecture Overview

- Spring Boot backend handles quiz generation and user interaction
- PostgreSQL stores user data and quiz results
- The REST Countries API provides up-to-date country information

---

## 🎯 My Project Goals

- Build a fun and educational platform using real-world APIs
- Practice building secure, RESTful web applications
- Apply Java and Spring Boot skills in a complete capstone project
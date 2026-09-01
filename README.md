# 🔗 LinkedList — URL Shortener

A production-oriented URL shortening platform built with **Spring Boot**, designed for secure authentication, high-performance redirects, and scalable URL management.

## 🚀 Features

* 🔗 Create & manage short URLs
* ⚡ Redis-powered URL caching
* 🔐 JWT Authentication & Authorization
* 🔑 BCrypt password hashing
* 🌐 Google OAuth 2.0
* 📊 URL analytics
* 🛡️ Spring Security
* 📝 SLF4J + Logback logging
* ✅ Input validation & global exception handling
* 🗄️ PostgreSQL database with JPA/Hibernate
* 🧩 Modular Monolith architecture

## 🛠️ Tech Stack

**Backend:** Java, Spring Boot, Spring Security, JWT, OAuth 2.0

**Database:** PostgreSQL (Supabase), JPA, Hibernate

**Caching:** Redis

**Logging:** SLF4J, Logback

**Build & Tools:** Maven, Git, Docker, Postman

## 🏗️ Architecture

```text
Angular
   ↓
Spring Boot
   │
   ├── Auth
   ├── User
   ├── URL
   ├── Redirect
   └── Analytics
   │
   ├── PostgreSQL
   └── Redis
```

The application follows a **feature-based modular monolith** approach, keeping clear boundaries between business modules and allowing future evolution into microservices.

## ⚡ URL Redirect Flow

```text
Short URL
    ↓
  Redis
    ↓
 Cache Hit → Redirect
    ↓
 Cache Miss
    ↓
PostgreSQL → Redis → Redirect
```

## 🔐 Authentication

Supports:

* Email & Password
* JWT-based authentication
* Google OAuth 2.0
* Role-based authorization
* BCrypt password hashing

## 🗺️ Roadmap

* [ ] Kafka-based asynchronous analytics
* [ ] Rate limiting
* [ ] Dockerized deployment
* [ ] GitHub Actions CI/CD
* [ ] AWS deployment
* [ ] Advanced observability
* [ ] Microservice extraction for high-load modules

## 👨‍💻 Status

🚧 **Actively under development**

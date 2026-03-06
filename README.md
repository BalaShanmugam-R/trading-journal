# 🏦 Trading Journal – Spring Boot Backend

[![Java-17-orange?style=for-the-badge&logo=java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)]
[![SpringBoot-4.0.3-brightgreen?style=for-the-badge&logo=springboot)](https://img.shields.io/badge/SpringBoot-4.0.3-brightgreen?style=for-the-badge&logo=springboot)
[![PostgreSQL-blue?style=for-the-badge&logo=postgresql)](https://img.shields.io/badge/PostgreSQL-blue?style=for-the-badge&logo=postgresql)
[![Maven-Build-red?style=for-the-badge&logo=apachemaven)](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven)
[![SpringDoc-OpenAPI-green?style=for-the-badge&logo=swagger)](https://img.shields.io/badge/SpringDoc-OpenAPI-green?style=for-the-badge&logo=swagger)

## 🚀 Overview

Personal trading journal backend built with **Spring Boot 4.0.3**, **JPA/Hibernate**, and **PostgreSQL**. Tracks trade records with profit/loss analysis, risk-reward ratios, and comprehensive CRUD operations. Designed for intraday options trading with real-time P&L calculations and advanced analytics.

*Tracking personal trades for risk analysis - **not financial advice or trading promotion***
## 🛠 Tech Stack

- **Java 17**
- **Spring Boot 4.0.3** (WebMVC, Data JPA, Security, DevTools)
- **PostgreSQL 42.7.3** (`tradingjournal` schema)
- **Hibernate/JPA** (Native queries, JOINs)
- **Maven**
- **SpringDoc OpenAPI 2.8.5** (Swagger UI)
- **Lombok 1.18.42**

## ✅ Features Implemented

### 📊 Trade Management

- ✅ Full CRUD operations (Create, Read, Update, Delete)
- ✅ Bulk clear/truncate with CASCADE
- ✅ Trade existence checks by composite keys

### 💹 Advanced Analytics

- 💰 Profit/Loss calculations (`buy/sell × quantity`)
- ⚖️ Risk:Reward ratios (`target% / stoploss%`)
- 📈 Captured points (`sell - buy`)
- 🟢 Trade status (PROFIT/LOSS/OPEN)

### 🔍 Data Layer

- 🗄️ Dual-table design: `trade_records` + `trade_summary`
- 🔗 Multi-column JOINs (`strikePrice + optionType + entry/exit`)
- ⚙️ Native SQL queries for complex matching
- 📄 Pagination-ready repositories

## 📡 API Endpoints

Access **Swagger UI**: `http://localhost:8080/swagger-ui.html`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/trades` | Fetch all trades + summaries |
| `POST` | `/api/trades` | Add new trade |
| `PUT` | `/api/trades/{id}` | Update P&L/status |
| `DELETE` | `/api/trades` | Clear all trades |
| `DELETE` | `/api/trades/remove` | Delete specific trade |

**📂 Project Structure**
```
src
├── controller
├── service
├── entity
├── model
├── repository
├── transformer
└── resources
```

### 🧪 Key Challenges Solved
- 🔧 FK constraint truncate → CASCADE + child-first order
- 🔍 JOIN duplicates → DISTINCT + LATERAL LIMIT 1
- 🛠️ DTO constructor mismatches → Object[] row mapping
- ⚡ Native query syntax → Multi-field equality joins
- 📊 Streaming large results → Chunked ID-range queries

### ⚙ Configuration
```
- application.properties:
- # Database
spring.datasource.url=jdbc:postgresql://localhost:5432/tradingjournal
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Swagger(optional)
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
```

### 🧪 Testing
- 🔧 Swagger UI: http://localhost:8080/swagger-ui.html
- 📱 Postman/Insomnia for API testing
- 🧪 Unit/Integration tests (planned)

### 🧠 What I Learned
- ⚙️ Native JOIN optimization for composite keys
- 🐛 JPA pitfalls: DTO constructors, row mapping, cascade deletes
- 🗃️ PostgreSQL: CONCAT for composite keys, LATERAL joins
- 💹 Trading math: Risk:reward, captured points
- 🎨 API design: Immutable fields, partial updates

### 🔜 Upcoming Features
- 🔐 Authentication/JWT integration
- 🔍 Pagination + filters (date, market, P&L)
- 📤 Export CSV/PDF

### 📌 Status

Actively enhancing query performance and adding filters in progress. Continuously improving architecture and adding new features.

## 👨‍💻 Author

**Bala Shanmugam**  
**Java Backend Developer | Trading Beginner Enthusiast**

⭐ **Star this repo if you find it helpful!** 🚀
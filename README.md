#  Loan Origination System (LOS)

##  Overview

This **Loan Origination System (LOS)** is a robust, modular application built using **Spring Boot (multi-module)** architecture. It streamlines the loan application process by integrating with external services to fetch and verify critical KYC and credit information.

---

##  Key Features

-  **Multi-Module Architecture:** Clean separation of concerns with dedicated modules for core business logic, external integrations, and data management.
-  **External Services Integration:**
  - **PAN Details Service** — Fetches and verifies PAN information.
  - **Aadhaar Details Service** — Retrieves customer Aadhaar data for identity verification.
  - **CIBIL Management Service** — Obtains customer CIBIL score and credit history to evaluate loan eligibility.
-  **Loan Eligibility Calculation:** Uses PAN, Aadhaar, and CIBIL data to determine how much loan can be granted to a customer.
-  **Customer Data Verification:** Validates customer KYC details using trusted external APIs.
-  **Extensible & Scalable:** Easily add more services or modules as needed.

---

##  Modules

This project uses a **multi-module** structure:

- **`los-core`** — Core business logic for processing loan applications.
- **`los-api`** — REST API layer to handle client requests.
- **`los-external`** — Integrations with external services (PAN, Aadhaar, CIBIL).
- **`los-data`** — Data persistence layer using Spring Data JPA.
- **`los-gateway`** *(Optional)* — API Gateway for routing requests if using microservices.

---

##  How It Works

1. **Customer submits loan application.**
2. System fetches:
   - PAN details from **PAN Service**
   - Aadhaar details from **Aadhaar Service**
   - CIBIL score & credit details from **CIBIL Service**
3. System verifies KYC and creditworthiness.
4. Calculates eligible loan amount based on:
   - CIBIL score
   - Active loans
   - Income vs. EMI ratio (if implemented)
5. Returns eligibility result and stores the application.

---

##  Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Cloud (for external service calls)**
- **RestTemplate / Feign Client** for API integration
- **MySQL** or any RDBMS
- **Maven** for multi-module build

---

##  Running the Application

1️⃣ Clone the repository:
```bash
git clone https://github.com/Jahnavi-padala/Los_Application.git

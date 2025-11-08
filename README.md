# conference-microservice

Project microservice for management of conferences.

---

## Table of contents

* [About](#about)
* [Architecture & Modules](#architecture--modules)
* [Tech stack](#tech-stack)
* [Prerequisites](#prerequisites)
* [Getting started (local)](#getting-started-local)

  * [1) Clone the repository](#1-clone-the-repository)
  * [2) Start configuration & discovery](#2-start-configuration--discovery)
  * [3) Start gateway and microservices](#3-start-gateway-and-microservices)
  * [4) Start the frontend (admin)](#4-start-the-frontend-admin)
* [Build & run with Maven (alternative)](#build--run-with-maven-alternative)
* [Docker (optional)](#docker-optional)
* [Testing & Health checks](#testing--health-checks)
* [Development notes](#development-notes)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)

---

## About

`conference-microservice` is a multi-module project that implements a conference management platform using a microservices architecture. The repository groups several backend microservices (Java) and a frontend admin UI (TypeScript/HTML/CSS) to manage conferences, keynotes, discovery and gateway responsibilities.


---

## Architecture & Modules

The project is organised into the following top-level modules (folders):

* `conference-config-repo` — Configuration repository for centralized configuration (Spring Cloud Config / config server).
* `discovery-service` — Service discovery (Eureka or similar) to allow services to find each other.
* `gateway-servicec` — API gateway / routing entrypoint for clients.
* `conference-service` — Core conference domain service (conferences CRUD, persistence, business logic).
* `conf-service` (conf-service/conf-service) — Auxiliary configuration or conference-related microservice (name suggests a separate bounded context).
* `keynote-service` — Service that manages keynote sessions / speakers.
* `FrontEnd/conf-admin` — Admin frontend (TypeScript/HTML/CSS) used to manage conferences from a browser.

These module names and structure are taken from the repository layout. citeturn0view0turn3view0turn4view0

---

## Tech stack

* **Backend:** Java (Spring Boot / Spring Cloud concepts implied by naming: config, discovery, gateway).
* **Frontend:** TypeScript / HTML / CSS (admin UI).
* **Build:** Maven (standard for Java projects; modules contain Java sources).
* **Infrastructure (optional):** Docker can be used to containerize services for local testing or deployment.

---

## Prerequisites

* Java JDK 11 or newer
* Maven 3.6+ (or the project wrapper if present)
* Node.js & npm/yarn (for frontend) — only if you will run/build the admin UI locally
* (Optional) Docker & Docker Compose — if you prefer containerized runs

---

## Getting started (local)

> These instructions are intentionally generic so they work with the repository layout. If a module includes its own README or launch script, prefer that.

### 1) Clone the repository

```bash
git clone https://github.com/bones0xab/conference-microservice.git
cd conference-microservice
```

### 2) Start configuration & discovery

1. Start the **configuration server** (if implemented) so other services pull configuration from it:

```bash
cd conference-config-repo
# Build and run using Maven (example)
mvn clean package
mvn spring-boot:run
```

2. Start the **service discovery** server (Eureka or equivalent):

```bash
cd ../discovery-service
mvn clean package
mvn spring-boot:run
```

> The config and discovery services should be available on their configured ports (see each module's `application.yml`/`application.properties`).

### 3) Start gateway and microservices

Start the gateway first, then backend services so they register with discovery:

```bash
# Gateway
cd ../gateway-servicec
mvn clean package
mvn spring-boot:run

# Conference core service
cd ../conference-service/conference
mvn clean package
mvn spring-boot:run

# Auxiliary conf-service
cd ../../conf-service/conf-service
mvn clean package
mvn spring-boot:run

# Keynote service
cd ../../../keynote-service/keynote
mvn clean package
mvn spring-boot:run
```

If the repository uses Maven multi-module layout you may prefer to build from the root and then run each module's jar; check each module for `pom.xml` and the generated target jar.

### 4) Start the frontend (admin)

```bash
cd FrontEnd/conf-admin
# install deps (npm or yarn)
npm install
# run dev server (if a script is present)
npm start
```

Open the admin UI in your browser (the port depends on the frontend configuration — commonly `http://localhost:4200` or `http://localhost:3000`).

---

## Build & run with Maven (alternative)

You can build all Java modules at once from the repository root (if a parent `pom.xml` exists):

```bash
mvn -T 1C clean install
```

Then run each microservice's jar from its `target` folder:

```bash
java -jar path/to/service/target/service-name-0.0.1-SNAPSHOT.jar
```

---

## Docker (optional)

If you want to containerize services, create Dockerfiles for each microservice and a `docker-compose.yml` orchestrating:

* config server
* discovery server
* gateway
* backend services
* frontend

A basic `docker-compose` flow:

```bash
# build images
docker-compose build
# start all services
docker-compose up
```

---

## Testing & Health checks

* Check each service's `/actuator/health` (if Spring Boot Actuator is enabled) or the module's health endpoint.
* Use logs to verify services register with discovery and pull configuration from the config server.

---

## Development notes

* Check each microservice's `application.yml` / `application.properties` for ports and config keys.
* If services use Spring Cloud Config, ensure the `conference-config-repo` is reachable and contains configuration files for each service.
* API contracts: consult controller packages in each service to discover REST endpoints and request/response shapes.

---

## Contributing

Contributions are welcome. Suggested workflow:

1. Fork the repository
2. Create a feature branch (`git checkout -b feat/your-feature`)
3. Make changes and add tests
4. Open a pull request describing the changes

Please follow Java & TypeScript style conventions and include documentation for new modules or endpoints.


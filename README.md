# Product Microservices Cloud System

## Objective
A highly scalable, cloud-native product management ecosystem built with a focus on high availability and modern container orchestration.

----------
## Functional Requirements
*   **Inventory Guard:** Logic to prevent "overselling" (stock validation before invoice generation).
*   **Full CRUD:** Comprehensive management for the product lifecycle.
*   **Automated Invoicing:** Event-driven or synchronous invoice generation upon successful purchase.

----------
## Non-Functional Requirements
*   **Testing:** Minimum **80% unit test coverage** using JUnit 5 and Mockito.
*   **Behavioral Testing:** End-to-end scenarios defined and tested via **Cucumber**.
*   **Scalability:** Horizontal Pod Autoscaling (HPA) ready.

----------
## Architecture
*   **Product Service:** Manages catalog data using **MongoDB 8**.
*   **Order Service:** Handles ACID-compliant transactions using **PostgreSQL 17**.
*   **Discovery & Load Balancing:** Orchestrated via **Kubernetes** services with 3+ replicas per pod.

----------
## Tech Stack
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Quarkus](https://img.shields.io/badge/Quarkus-3.x-blue?logo=quarkus)
![Angular](https://img.shields.io/badge/Angular-21-red?logo=angular)
![Kubernetes](https://img.shields.io/badge/K8s-v1.30+-blue?logo=kubernetes)

# 🏠 Home Services Management System V2 (Spring boot + Hibernate)

This project is a pure Java application using JDBC and PostgreSQL to manage a home service platform. It supports roles like **Admin**, **Customer**, and **Expert**, enabling service listings, user registration, expert approval, order handling, offers, credits, and feedback.

---

## ✅ Features

### 👤 User Management
- Register users with roles: `CUSTOMER` or `EXPERT`
- Unique email validation
- Secure password policy (min 8 characters, letters and numbers)
- Expert statuses: `NEW`, `PENDING_APPROVAL`, `APPROVED`
- Admin can:
    - Approve experts
    - Assign/remove experts from sub-services
- Expert has:
    - Profile image (JPG, ≤ 300KB)
    - Rating (default: 0)
    - Registration timestamp

### 🛠 Service Management
- Admin can define **main services** such as:
    - Interior Design
    - Building Installations
    - Transportation
    - Moving and Logistics
    - Home Appliances
    - Cleaning and Sanitation
- Each main service has sub-services (e.g., Kitchen Appliances under Home Appliances)
- Sub-services include:
    - Description
    - Base price
- Admin can:
    - Create/edit/delete services and sub-services
    - Prevent duplicates or orphan sub-services
    - View service list

### 📦 Order Management
- Customers can:
    - View available services and sub-services
    - Place an order with:
        - Proposed price (≥ base price)
        - Work description
        - Scheduled datetime (must be in the future)
        - Address
- Order statuses:
    - `WAITING_FOR_EXPERTS`
    - `WAITING_FOR_CUSTOMER_SELECTION`
    - `WAITING_FOR_EXPERT_ARRIVAL`
    - `STARTED`
    - `COMPLETED`
    - `PAID`

### 💰 Credit System
- Customers use credit to pay for services
- Experts receive credit when orders are paid
- Credit is tracked per user



# Order API

<details>
<summary>Table of content</summary>

- - [Shipping Microservice Description](#shipping-microservice-description)
  - [Features ✨](#features-)
  - [Project structure](#project-structure)
  - [API Endpoints](#api-endpoints)
  - [API Documentation](#api-documentation)
  - [Database Schema](#database-schema)
  - [Installation & How to use 📥](#installation-)
  - [Running Tests 🧪](#running-tests-)
    - [Tests Structure](#tests-structure)
  - [Tech/Framework used 🧰](#techframework-used-)
  </details>

### Note!: Its part of a graduation project of Fawry internship.

## Orders Microservice Description

The Orders API microservice is an important component of our e-commerce platform, designed to efficiently handle the creation, management, and processing of our customers' orders.
And it encapsulates order-related functionalities.

## Features ✨

you can do this :

- You can list all of your orders between a start and end date for specific customer email.
- You can create Order with items, card number, cvv, customer email, customer location and coupon code .

## Project structure

<details>
<summary>Click to expand!</summary>

```bash
## Project Structure

📦main
 ┣ 📂java
 ┃ ┗ 📂com
 ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┗ 📂orderapis
 ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderController.java
 ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Coupon.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Order.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderItem.java
 ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorDetails.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜GlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RecordNotFoundException.java
 ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponMapper.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderMapper.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderItemMapper.java
 ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┣ 📂bank
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DepositRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WithdawRequest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂coupon
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponModelForRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponModelForResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CouponModelForUseCoupon.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂order
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderModelForRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderModelForResponse.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂orderItem
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderItemModelForRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderItemModelForResponse.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂product
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProductModel.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProductModelForConsume.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂shipping
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ShippingModel.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂store
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StoreModelResponse.java
 ┃ ┃ ┃ ┃ ┣ 📂repo
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponRepo.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderRepo.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderItemRepo.java
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📂impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderItemServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderItemService.java
 ┃ ┃ ┃ ┃ ┗ 📜ShippingApIsApplication.java
 ┃ ┃ ┃ ┃ ┣ 📂util
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CodeGenerator.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RestApiClient.java
 ┗ 📂resources
 ┃ ┣ 📂db
 ┃ ┃ ┗ 📂migration
 ┃ ┃ ┃ ┣ 📜V1__create_order_orderItem_coupon_talbles.sql
 ┃ ┃ ┃ ┗ 📜V2__Change_Coupon_Code_Data_Type.sql
 ┃ ┗ 📜application.properties
```

</details>

</details>

## API Endpoints

| Methods | Endpoints                                |
| :------ | :--------------------------------------- |
| `POST`  | `/orders `                            |
| `GET`   | `/orders/by-customer-and-date-range ` |

## API Documentation

Swagger URL

```
https://order-service-yn10.onrender.com/swagger-ui/index.html#/
```

## Database Schema

- [ERD link!](https://mermaid.live/edit#pako:eNqVk99PwjAQx_-V5p5x2fgxyt4IYCQ6IYg-mCVLs1ZcMtrZXY3I9r9bNgwT9MF7WXufpve927d7SBQXEIDQ05RtNNtGkthYrKazFdk3m0PMJYqN0CTlZHl7mU5MgWordCy2LM1OfD0PZw_rcbgkSnOLOUNxotPZZB6O7wgqZFmc6zRpwafxanIzXpECGZriMn8Q3mSrSLZUx_P1LPyH9EaYhde_wFwrbhKMT8Xa9M0wiSnuLjv6o5czzc3y-zt5XCwX9_-YuTK5kmfSTpVquN7lv8ybp4XlEuN3lhkRKx3nQidCItsIcjbTH7MllXKcsjxKJQF5ZUUbl-XVVblv_4fWEeiAdYj1B7d-q9uMAF_FVkQQ2CUXL8xkGEEkK3uUGVQPO5lAgNqIDpj84J2jSSF4YVlhszmTEOzhA4JB3-nTrktHA-oNu13X68AOAr_nuDZoz_dpb0S9QdWBT6XsBa5D-3Q0Gnqe71JK_WG3vu25hk1JwVNUOmweSP1Oqi9xHO8h)

## Installation

To run the Bank Management System locally, you will need the following:
- Java 17 or higher
- Maven
- PostgreSQL

Once you have the required tools installed, follow these steps to install the Bank Management System:

1. Clone this repository:
    ```shell
        https://github.com/Muhammed-Sameih/OrderAPIs.git
    ```
2. Edit the database configurations in application.properties file.
3. Navigate to the project directory:
    ```shell
    cd OrderAPIs
    ```
4. Build and run the application using Maven
    ```shell
    mvn spring-boot:run
    ```
5. Explore the Application: Once the application is up and running, open your web browser and access it at: `http://localhost:8080`
6. Access API Documentation: Additionally, you can explore the API documentation by navigating to: `http://localhost:8080/swagger-ui.html`. This provides detailed insights into the available API endpoints and functionalities.
## Running Tests 🧪

The testing is done using `Mockito and JUnit `.

<details>
<summary> All </summary>

![coverage](https://github.com/Muhammed-Sameih/OrderAPIs/assets/77055492/23c47023-722f-426a-96b9-5fe4e783c5e3)

</details>

<details>
<summary> OrderRepoTest </summary>

![orderRepoTest](https://github.com/Muhammed-Sameih/OrderAPIs/assets/77055492/84d8ac86-3a1a-4a77-955b-1b1d9f921851)

</details>
<details>
<summary> CouponRepoTest </summary>

![couponRepoTest](https://github.com/Muhammed-Sameih/OrderAPIs/assets/77055492/c5d882c2-b10f-4be9-977b-d8f205dcb0cd)

</details>

<details>
<summary> OrderItemServiceImplTest </summary>

![orderItemServiceTest](https://github.com/Muhammed-Sameih/OrderAPIs/assets/77055492/cb27d1cb-f49b-4af6-8e79-b81357c02ba3)

</details>

<details>
<summary> OrderServiceImplTest</summary>

![orderServiceTest](https://github.com/Muhammed-Sameih/OrderAPIs/assets/77055492/c8340404-1506-4faa-bba0-d68c9f43825b)

</details>
<details>
<summary> CouponServiceImplTest </summary>

![couponServiceTest](https://github.com/Muhammed-Sameih/OrderAPIs/assets/77055492/f70903e2-ebc8-4c19-a7a8-9ff2f2a90d50)

</details>

### Tests Structure

<details>
<summary>Click to expand!</summary>

```bash
📦test
 ┣ 📂java
 ┃ ┗ 📂com
 ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┗ 📂orderapis
 ┃ ┃ ┃ ┃ ┣ 📂repo
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponRepoTest.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderRepoTest.java
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┗ 📂impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CouponServiceImplTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderServiceImplTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderItemServiceImplTest.java
 ┃ ┃ ┃ ┃ ┗ 📜OrderApIsApplicationTests.java
 ┗ 📂resources
 ┃ ┗ 📜application.properties

```

</details>

## Tech/Framework used 🧰

- Java
- Spring Boot
- Spring Data JPA
- Lombok
- PostgreSQL
- Maven 
- Swagger
- Docker
- Intellij

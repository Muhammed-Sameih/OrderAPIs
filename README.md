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
 ┃ ┃ ┃ ┣ 📜V2__create_order_orderItem_coupon_talbles.sql
 ┃ ┃ ┃ ┗ 📜V3__Change_Coupon_Code_Data_Type.sql
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
https://
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

![1](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/fd0bb9f1-4c8e-415f-9632-3975c6ef50c5)

</details>

<details>
<summary> OrderRepoTest </summary>

![6](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/54ea1f03-ecf2-4ff0-b2c7-41cd1547c707)

</details>
<details>
<summary> CouponRepoTest </summary>

![7](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/eea98e76-661b-4387-ae98-7ced629e82ef)

</details>

<details>
<summary> OrderItemServiceImplTest </summary>

![4](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/86915807-0ab7-4909-87e6-9cbbdace371a)

</details>

<details>
<summary> OrderServiceImplTest</summary>

![3](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/62070de9-ea85-4f48-b466-8d52c16a7bfe)

</details>
<details>
<summary> CouponServiceImplTest </summary>

![2](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/74482665-d1a2-4294-b532-7ebfd680b24c)

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

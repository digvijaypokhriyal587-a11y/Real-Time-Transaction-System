# 💳 Real-Time Transaction System

![Java](https://img.shields.io/badge/Java-17-orange)
![JDBC](https://img.shields.io/badge/JDBC-Database-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-green)
![Status](https://img.shields.io/badge/Status-Completed-success)
![License](https://img.shields.io/badge/License-MIT-yellow)

A **Java-based Banking Application** developed using **Java, JDBC, and
MySQL**. The project demonstrates real-time banking operations including
account creation, deposits, withdrawals, balance inquiry, and
transaction history.

------------------------------------------------------------------------

# 📌 Table of Contents

-   Project Overview
-   Features
-   System Architecture
-   Folder Structure
-   Technologies Used
-   Database Schema
-   Installation
-   Usage
-   Screenshots
-   Future Enhancements
-   Skills Learned
-   Contributing
-   License
-   Author

------------------------------------------------------------------------

# 📖 Project Overview

The **Real-Time Transaction System** is a console-based banking
application designed to simulate real banking operations securely using
JDBC and MySQL. It follows Object-Oriented Programming principles and
demonstrates CRUD operations with a relational database.

------------------------------------------------------------------------

# ✨ Features

-   ✅ Create New Account
-   💰 Deposit Money
-   💸 Withdraw Money
-   📊 Balance Inquiry
-   📜 Transaction History
-   🔒 Secure Database Connectivity
-   ⚡ Real-Time Updates
-   🧩 Modular Java Classes

------------------------------------------------------------------------

# 🏗️ System Architecture

``` text
      User
        │
 Console Application
        │
      Java
        │
      JDBC
        │
      MySQL
```

------------------------------------------------------------------------

# 📂 Project Structure

``` text
Real-Time-Transaction-System/
│
├── src/
│   ├── Main.java
│   ├── DatabaseConnection.java
│   ├── Account.java
│   ├── Deposit.java
│   ├── Withdraw.java
│   ├── TransactionHistory.java
│   └── TransactionThread.java
│
├── database/
│   └── banking.sql
│
├── screenshots/
│
├── README.md
└── LICENSE
```

------------------------------------------------------------------------

# ⚙️ Technologies Used

  Technology   Purpose
  ------------ -----------------------
  Java         Core Programming
  JDBC         Database Connectivity
  MySQL        Database
  Eclipse      IDE
  Git          Version Control
  GitHub       Repository Hosting

------------------------------------------------------------------------

# 🗄️ Database Schema

## Accounts Table

  Column       Type
  ------------ ---------
  account_no   INT
  name         VARCHAR
  pin          VARCHAR
  balance      DOUBLE

## Transactions Table

  Column             Type
  ------------------ ----------
  transaction_id     INT
  account_no         INT
  transaction_type   VARCHAR
  amount             DOUBLE
  transaction_date   DATETIME

------------------------------------------------------------------------

# 🚀 Installation

``` bash
git clone https://github.com/digvijaypokhriyal587-a11y/Real-Time-Transaction-System.git

cd Real-Time-Transaction-System
```

1.  Import into Eclipse/IntelliJ.
2.  Install MySQL.
3.  Create the database.
4.  Import `banking.sql`.
5.  Update JDBC username/password.
6.  Run `Main.java`.

------------------------------------------------------------------------

# ▶️ Usage

1.  Launch the application.
2.  Create a new account.
3.  Deposit or withdraw funds.
4.  Check your balance.
5.  View transaction history.

------------------------------------------------------------------------

# 📈 Future Enhancements

-   JavaFX GUI
-   OTP Authentication
-   Admin Dashboard
-   PDF Mini Statement
-   Fund Transfer
-   QR Payments
-   Email Notifications
-   REST API Integration

------------------------------------------------------------------------

# 🧠 Skills Learned

-   Object-Oriented Programming
-   JDBC Connectivity
-   SQL Queries
-   CRUD Operations
-   Exception Handling
-   Multithreading
-   Git & GitHub

------------------------------------------------------------------------

# 🤝 Contributing

1.  Fork the repository.
2.  Create a feature branch.
3.  Commit your changes.
4.  Push to GitHub.
5.  Open a Pull Request.

------------------------------------------------------------------------

# 📄 License

This project is licensed under the **MIT License**.

------------------------------------------------------------------------

# 👨‍💻 Author

**Digvijay Pokhriyal**

-   🎓 B.Tech Computer Science
-   🏫 Graphic Era Hill University
-   💻 Java Developer
-   🌐 GitHub: https://github.com/digvijaypokhriyal587-a11y

------------------------------------------------------------------------

# ⭐ Support

If you found this project useful, please **give it a ⭐ on GitHub**.

Made with ❤️ by **Digvijay Pokhriyal**


## Conclusion

The Real-Time Transaction System demonstrates the implementation of a banking management solution using Java, JDBC, and MySQL. It provides a simple and efficient way to perform banking transactions while maintaining data consistency and real-time database updates.


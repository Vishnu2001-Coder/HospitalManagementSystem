# HospitalManagementSystem
A Java console app for hospital management. Add/search patients and doctors, book appointments, check doctor availability, and store all data in MySQL using JDBC.


Description

Hospital Management System is a console-based Java application that allows hospitals or clinics to manage patients, doctors, and appointments efficiently. The system is built using Java SE, JDBC, and MySQL and demonstrates how to interact with a database for real-world healthcare management.

This project provides:

Patient Management

Add new patients with name, age, gender, and phone number.

View all patients in a neatly formatted table.

Search for a patient using their phone number.

Doctor Management

View all doctors with their department details.

Search for doctors by department.

Appointment Management

Book appointments by selecting a patient and a doctor.

Check doctor availability to avoid overlapping appointments.

Validate dates and times before booking.

Database Integration

All data is stored in a MySQL database.

Tables include Patients, Doctors, and Appointments.

Uses JDBC PreparedStatement to securely interact with the database.

User-Friendly Console Interface

Simple menu-driven system using Scanner for input.

Clear, tabular output for patients and appointments.

Handles invalid inputs for IDs, dates, and times gracefully.

Purpose

This project demonstrates how to build a Java console application with database connectivity, focusing on:

Object-oriented design using classes like Patient, Doctor, and Hospital.

Clean database handling using JDBC with PreparedStatement and ResultSet.

Practical use of input validation, table formatting, and resource management (try-with-resources).

Ideal For

Students learning Java JDBC and database integration.

Beginners wanting a real-world console-based project.

Anyone looking to understand CRUD operations and appointment scheduling logic in Java.

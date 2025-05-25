CREATE DATABASE IF NOT EXISTS school_db;
USE school_db;

CREATE TABLE IF NOT EXISTS students (
    student_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    class varchar(100) not null,
    section VARCHAR(10) NOT NULL,
    balance DECIMAL(10,2) NOT NULL
);
ALTER TABLE students
DROP COLUMN class_name;

CREATE TABLE IF NOT EXISTS payments (
    payment_id VARCHAR(100) PRIMARY KEY,
    student_id VARCHAR(100),
    payment_date DATE NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);
ALTER TABLE payments MODIFY payment_id VARCHAR(50);

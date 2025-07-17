CREATE DATABASE LoanManagementSystem;
USE LoanManagementSystem;
CREATE TABLE Customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone_number VARCHAR(15),
    address TEXT,
    dob DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE Customerscustomers
ADD COLUMN aadhaar_number VARCHAR(12) UNIQUE,
ADD COLUMN pan_number VARCHAR(10) UNIQUE;
ALTER TABLE Customers DROP COLUMN address;
CREATE TABLE Address (
    address_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    street VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    zip_code VARCHAR(10),
    country VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);





CREATE TABLE LoanApplications (
    application_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    loan_amount DECIMAL(12,2) NOT NULL,
    loan_type VARCHAR(50),
    application_date DATE ,
    status ENUM('PENDING', 'APPROVED', 'REJECTED', 'INPROCESS') DEFAULT 'PENDING',
    approved_amount DECIMAL(12,2),
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
    );
ALTER TABLE LoanApplications
ADD COLUMN term_months INT,
ADD COLUMN loan_purpose VARCHAR(20);

CREATE TABLE Income_Details (
    income_id INT PRIMARY KEY AUTO_INCREMENT,
    monthly_income DECIMAL(10,2),
    employment_status VARCHAR(50),
    employer_name VARCHAR(100),
    years_at_job INT
);
drop table Income_Details ;
drop table Address;
CREATE TABLE Income_Details (
    income_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    monthly_income DECIMAL(10,2),
    employment_status VARCHAR(50),
    employer_name VARCHAR(100),
    years_at_job INT,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);
CREATE TABLE pan_details (
    pan_id INT AUTO_INCREMENT PRIMARY KEY,
	customer_id INT NOT NULL,
    pan_number VARCHAR(20),
    full_name VARCHAR(100),
    dob DATE,
    father_name VARCHAR(100),
    status VARCHAR(20),
    issued_on DATE,
    gender VARCHAR(10),
    category VARCHAR(50),
    address VARCHAR(255),  
   FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
CREATE TABLE aadhaar_details (
    aadhaar_id INT AUTO_INCREMENT PRIMARY KEY,
	customer_id INT NOT NULL,
    aadhaar_number VARCHAR(20),
    name VARCHAR(100),
    gender VARCHAR(10),
    dob DATE,
    father_name VARCHAR(100),
    street VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    pincode VARCHAR(10),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
CREATE TABLE cibil_details (
    cibil_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id int,
    cibil_score INT,
    last_updated DATE,
    credit_status VARCHAR(50),
    no_of_enquiries INT,
    no_of_active_accounts INT,
    loan_outstanding DECIMAL(12,2),
    current_emi DECIMAL(12,2),
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);







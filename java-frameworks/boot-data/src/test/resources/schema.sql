CREATE TABLE employees (
                       employee_id INT AUTO_INCREMENT,
                       first_name VARCHAR(20),
                       last_name VARCHAR(25) NOT NULL,
                       email VARCHAR(25) NOT NULL,
                       phone_number VARCHAR(20),
                       hire_date DATE NOT NULL,
                       creation_date DATE NULL,
                       last_modified_date DATE NULL,
                       job_id VARCHAR(10) NULL,
                       salary DECIMAL(8, 2) NOT NULL,
                       commission_pct DECIMAL(2, 2),
                       manager_id INT,
                       department_id INT,
                       PRIMARY KEY (employee_id)
);

CREATE TABLE jobs (
                      job_id VARCHAR(10) NOT NULL,
                      job_title VARCHAR(35) NOT NULL,
                      min_salary DECIMAL(8, 0),
                      max_salary DECIMAL(8, 0),
                      PRIMARY KEY (job_id)
);

CREATE TABLE departments (
                     department_id INT AUTO_INCREMENT,
                     department_name VARCHAR(30) NOT NULL,
                     manager_id INT,
                     location_id INT,
                     PRIMARY KEY (department_id)
);

CREATE TABLE job_history (
                     job_history_id INT AUTO_INCREMENT,
                     employee_id INT NOT NULL,
                     start_date DATE NOT NULL,
                     end_date DATE NOT NULL,
                     job_id VARCHAR(10) NOT NULL,
                     department_id INT NOT NULL,
                     PRIMARY KEY (job_history_id)
);
CREATE TABLE locations(
                    location_id INT AUTO_INCREMENT,
                    street_address VARCHAR(40),
                    postal_code VARCHAR(12),
                    city       VARCHAR(30) NOT NULL,
                    state_province VARCHAR(25),
                    country_id INT NOT NULL,
                    PRIMARY KEY (location_id)
) ;
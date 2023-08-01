#constMach - Construction Machine Rental Project

Overview
constMach is a web-based application built using Java, Servlets, MySQL, and JSP (JavaServer Pages). It aims to streamline the process of renting construction machines within a construction company. The project provides an easy-to-use interface for both workers and administrators. Workers can rent machines and specify the required quantity, while administrators can manage machines, stock, and user accounts.

Features
Machine Rental: Workers can rent construction machines for a specific period and specify the quantity required.

Stock Management: Administrators can add, remove, and update machine details, such as name, category, availability, and rental cost.

User Management: Administrators have the authority to create, update, and delete worker accounts. Workers receive their accounts from administrators when they start working for the company.

Rent History: The system maintains a rent history to track machine rentals, their durations, and the corresponding worker details.

Repeat Rental: After the rental period ends, workers can rent the same machine package again without the need to re-enter the details.

Getting Started
To run constMach on your local machine, follow these steps:

Clone the constMach repository from GitHub:

bash
Copy code
git clone https://github.com/yourusername/constMach.git
Set up the MySQL database:

Create a new database for the application and import the SQL schema provided in the database folder.
Configure the database settings:

Update the database connection parameters in the web.xml file or other relevant configuration files.
Build the project:

Compile the Java code and generate the WAR file.
Deploy the application:

Deploy the generated WAR file to a Java Servlet Container (e.g., Apache Tomcat).
Access the application in your web browser at http://localhost:8080/constMach or the appropriate port.

Usage
Admin
As an admin, log in to the application using your credentials.
From the dashboard, you can manage machines, users, and their accounts.
Add, remove, or update machine details and their availability in the stock.
Create, update, or delete worker accounts and provide them with login credentials.
Worker
Workers will receive their account details from the admin.
Log in using the provided credentials.
Rent a machine by selecting the desired machine package and specifying the quantity needed.
After the rental period ends, return the machine, and you can rent the same package again.

Contributing
If you want to contribute to constMach, thanks alot:

License
constMach is free to copy, modify and use.

Contact
For any questions or inquiries about the project, stef.adamovic@gmail.com.

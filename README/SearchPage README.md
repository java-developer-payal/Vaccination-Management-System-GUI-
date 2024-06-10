
# Search Page
The Search_page class provides functionality to search patient data based on different criteria such as Aadhar number, name, or vaccine name. It connects to a MySQL database to retrieve and display patient information.

# Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL Connector/J (JDBC Driver)
# Setup Instructions
- Database Setup :- 
1. Install MySQL Server: If you haven't already, download and install MySQL Server from the official website.

2. Create Database and Table:

- Open MySQL Command Line Client or MySQL Workbench.

- Create a new database and table using the following commands:
```
CREATE DATABASE vaccination_database_new;

USE vaccination_database_new;

CREATE TABLE registration_data (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    aadhar VARCHAR(12),
    gender VARCHAR(10),
    age INT,
    profession VARCHAR(100),
    temp DECIMAL(5,2),
    address TEXT,
    vaccineName VARCHAR(100),
    bloodPressure VARCHAR(50),
    ContactNumber VARCHAR(15)
);
```
3. Insert Sample Data: Insert some sample data into the registration_data table to test the search functionality.

- Application Setup :-
1. Clone the Repository: Clone this repository to your local machine.
```
git clone https://github.com/java-developer-payal/vaccine-management-system.git
cd vaccine-management-system
```
2. Add MySQL Connector/J to Classpath: Download the MySQL Connector/J from the official website and add it to your project's classpath.

3. Adjust Database Connection Settings:

- Open the Search_page.java file.

- Update the database connection URL, username, and password in the following line:
```
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_database_new", "root", "yourpassword");
```
- Run the Application :-
1. Compile and Run: Compile and run the application using your preferred IDE or command line.
```
javac -cp ".:path/to/mysql-connector-java-x.x.xx-bin.jar" vaccine/system/gui/Search_page.java
java -cp ".:path/to/mysql-connector-java-x.x.xx-bin.jar" vaccine.system.gui.Search_page
```
Replace path/to/mysql-connector-java-x.x.xx-bin.jar with the actual path to the MySQL Connector/J jar file.

# Features
Search Functionality:
- Search by Aadhar number: Ensures the entered value is a valid 12-digit Aadhar number.
- Search by Name: Allows partial and full name searches.
- Search by Vaccine Name: Allows partial and full vaccine name searches.
- Result Display: Displays patient details in a formatted text area.

- Error Handling: Displays appropriate error messages for invalid inputs and database errors.

- Go Back Button: Allows users to navigate back to the main menu.

# GUI Layout
- Search Field: Text field to enter search value.
- Radio Buttons: Options to select search criteria (Aadhar number, name, or vaccine name).
- Search Button: Initiates the search based on the selected criteria.
- Result Area: Displays search results or appropriate messages if no records are found.
- Go Back Button: Navigates back to the main menu.
# Notes
- Ensure the MySQL server is running before starting the application.
- Customize the database connection settings as per your environment.
- The Images/searchpage.jpg is a placeholder; ensure the image exists at the specified path or update the path accordingly.
# Troubleshooting
- No Records Found: Ensure the database is populated with relevant data.
- Database Connection Error: Verify the MySQL server credentials and connection URL.
- Invalid Aadhar Number: Enter a valid 12-digit Aadhar number for search.
# Screenshots 


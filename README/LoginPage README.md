# Login Page


Login_page handles user login to the application using username and password.




## Features 

- User Login: Allows users to log in with a username and password.
- Password Visibility Toggle: Users can toggle the visibility of their password while typing.
- Progress Bar: Indicates the login process.
- Image Display: Shows an image on the login screen.
- Error Handling: Displays error messages for invalid login attempts or empty fields.
- Database Connectivity: Connects to a MySQL database to verify user credentials.
## Prerequisites
- Java Development Kit (JDK): Ensure you have JDK installed on your system.
- MySQL Database: A MySQL database instance running with the vaccination_database_new database and a login_data table.
- MySQL Connector for Java: Ensure you have the MySQL Connector JAR file added to your project.
## Setup and Installation
1. Clone the Repository:
```
git clone https://github.com/your-username/vaccine-management-system-gui.git
```
2. Configure Database:

- Create a MySQL database named vaccination_database_new.
- Create a login_data table with columns username and password.
```
CREATE DATABASE vaccination_database_new;
USE vaccination_database_new;
CREATE TABLE login_data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);
```
- Insert some test data into the login_data table.
```
INSERT INTO login_data (username, password) VALUES ('testuser', 'password123');
```
3. Update Database Credentials:

- Open the Login_page.java file.
- Update the following line with your MySQL username and password:
```
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_database_new", "root", "your_password");
```
4. Run the Application:

Compile and run the VaccineGui class.
```
javac VaccineGui.java
java VaccineGui
```
## Usage
1. Login:

- Enter your username and password.
- Click the "LOGIN" button.
- If the credentials are correct, you will be logged in, and a new Menu_page will be displayed.
- If the credentials are incorrect, an error message will be displayed.
2. Show/Hide Password:

- Check the "Show Password" checkbox to make the password visible.
- Uncheck the checkbox to hide the password.

## Dependencies
- Java Swing: For creating the graphical user interface.
- MySQL Connector/J: For connecting to the MySQL database.
## Screenshots
o Login Page :

<img src="https://github.com/java-developer-payal/Vaccination-Management-System-GUI-/assets/144053983/ce15ad4d-6521-4531-8dd3-3a04020af52e" width="450" height="400" />


<img src="https://github.com/java-developer-payal/Vaccination-Management-System-GUI-/assets/144053983/d18aa726-72c1-42bf-b7b3-6850a51b5b15" width="450" height="400" />

## Author
- Payal Saini - java-developer-payal
- Feel free to fork this project, open issues, and submit pull requests. Contributions are welcome!





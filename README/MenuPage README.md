
# Menu Page 


Menu_page.java is the main class that sets up the GUI and handles user interactions.




## Features 

- Update Data: Modify existing vaccine data records.
- Add New Record: Add new entries for vaccine records.
- View Vaccine Data: Access detailed information about various vaccines.
- View All Data: Display all vaccine-related records.
- Search: Search for specific vaccine records.
- Exit: Close the application.

## Components

- JFrame: The main window.
- JButtons: Six buttons for various functionalities.
- JLabel: A label to display the background image.



## Button Actions
Each button in the menu is associated with an action listener that:

- Disposes the current frame.
- Opens the corresponding page.
- The "Exit" button terminates the application.

## Constructor
- Initializes the JFrame.
- Creates and configures the JButtons.
- Loads and displays an image.

## actionPerformed Method
- Handles button clicks.
- Navigates to the respective pages or exits the application.
## Other Classes
- Update_page: Page for updating existing data.
- Registration_page: Page for adding new records.
- VaccineData_page: Page for viewing detailed vaccine information.
- ViewAllData_page: Page for viewing all data records.
- Search_page: Page for searching specific records.

## Navigating the Application
- Update Data: Click to open the update data page.
- Add New Record: Click to open the new record registration page.
- View Vaccine Data: Click to view detailed vaccine information.
- View All Data: Click to view all vaccine records.
- Search: Click to search for specific records.
- Exit: Click to close the application.
  
## Usage/Examples
Running the Application :-
1. Clone the Repository :
```java
git clone https://github.com//java-developer-payal/Vaccination-Management-System-GUI.git
```
2. Compile the Code :
```java
javac -d bin src/vaccine/system/gui/*.java
```
3. Run the Application :
```java
java -cp bin vaccine.system.gui.Menu_page
```


## Screenshots
o Menu Page :

<img src="https://github.com/java-developer-payal/Vaccination-Management-System-GUI-/assets/144053983/9bc7bca8-b31f-4352-b929-02b57b43de16" width="450" height="400" />

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a feature branch (git checkout -b feature/YourFeature).
3. Commit your changes (git commit -m 'Add YourFeature').
4. Push to the branch (git push origin feature/YourFeature).
5. Open a pull request.

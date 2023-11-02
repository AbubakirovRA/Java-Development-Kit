package EmployeeDirectory;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();

        directory.addEmployee(1, "123-456-7890", "Иван", 5);
        directory.addEmployee(2, "987-654-3210", "Жанна", 3);
        directory.addEmployee(3, "555-555-5555", "Борис", 5);

        SwingUtilities.invokeLater(() -> {
            EmployeeDirectoryGUI gui = new EmployeeDirectoryGUI(directory);
        });
    }
}








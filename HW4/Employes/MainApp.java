package Employes;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();

        directory.addEmployee(1, "123-456-7890", "John", 5);
        directory.addEmployee(2, "987-654-3210", "Jane", 3);
        directory.addEmployee(3, "555-555-5555", "Bob", 5);

        List<EmployeeDirectory.Employee> employees = directory.getAllEmployees();
        System.out.println("All Employees:");
        for (EmployeeDirectory.Employee employee : employees) {
            System.out.println(employee);
        }

        List<EmployeeDirectory.Employee> experiencedEmployees = directory.findEmployeesByExperience(5);
        System.out.println("Employees with 5 years of experience:");
        for (EmployeeDirectory.Employee employee : experiencedEmployees) {
            System.out.println(employee);
        }

        List<String> phoneNumbers = directory.findPhoneNumbersByName("Jane");
        System.out.println("Phone numbers for employees named Jane:");
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }

        EmployeeDirectory.Employee foundEmployee = directory.findEmployeeByEmployeeId(2);
        if (foundEmployee != null) {
            System.out.println("Found employee: " + foundEmployee.getName());
        } else {
            System.out.println("Employee not found");
        }
    }
}

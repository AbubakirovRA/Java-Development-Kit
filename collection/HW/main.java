// Создать класс справочник сотрудников, который содержит внутри коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
// Табельный номер
// Номер телефона
// Имя
// Стаж
// Добавить метод, который ищет сотрудника по стажу (может быть список)
// Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
// Добавить метод, который ищет сотрудника по табельному номеру
// Добавить метод добавление нового сотрудника в справочник сотрудников

import java.util.ArrayList;
import java.util.List;

class Employee {
    private int employeeId;
    private String phoneNumber;
    private String name;
    private int experience;

    public Employee(int employeeId, String phoneNumber, String name, int experience) {
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }
}

class EmployeeDirectory {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> findEmployeesByExperience(int experience) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getExperience() == experience) {
                result.add(employee);
            }
        }
        return result;
    }

    public String findPhoneNumberByName(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                return employee.getPhoneNumber();
            }
        }
        return "Phone number not found for " + name;
    }

    public Employee findEmployeeByEmployeeId(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null; // Employee with the specified employeeId not found
    }
}

public class Main {
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();

        // Добавляем сотрудников в справочник
        directory.addEmployee(new Employee(1, "1234567890", "John", 5));
        directory.addEmployee(new Employee(2, "9876543210", "Alice", 3));
        directory.addEmployee(new Employee(3, "5555555555", "Bob", 7));

        // Поиск сотрудников по стажу
        List<Employee> employeesWithExperience = directory.findEmployeesByExperience(5);
        for (Employee employee : employeesWithExperience) {
            System.out.println("Employee with experience 5: " + employee.getName());
        }

        // Поиск номера телефона по имени
        String phoneNumber = directory.findPhoneNumberByName("Alice");
        System.out.println("Phone number for Alice: " + phoneNumber);

        // Поиск сотрудника по табельному номеру
        Employee employee = directory.findEmployeeByEmployeeId(3);
        if (employee != null) {
            System.out.println("Employee with ID 3: " + employee.getName());
        } else {
            System.out.println("Employee not found with ID 3");
        }
    }
}

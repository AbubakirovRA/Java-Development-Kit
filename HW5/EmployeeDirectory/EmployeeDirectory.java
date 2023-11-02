package EmployeeDirectory;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectory {
    private List<Employee> employees; // Список сотрудников

    // Конструктор класса EmployeeDirectory
    public EmployeeDirectory() {
        employees = new ArrayList<>(); // Инициализируем список сотрудников
    }

    // Метод для добавления нового сотрудника в справочник
    public void addEmployee(int employeeId, String phoneNumber, String name, int experience) {
        Employee employee = new Employee(employeeId, phoneNumber, name, experience); // Создаем объект сотрудника
        employees.add(employee); // Добавляем сотрудника в список
    }

    // Метод для получения всех сотрудников
    public List<Employee> getAllEmployees() {
        return employees; // Возвращаем список всех сотрудников
    }

    // Метод для поиска сотрудников по стажу
    public List<Employee> findEmployeesByExperience(int targetExperience) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getExperience() == targetExperience) {
                result.add(employee);
            }
        }
        return result; // Возвращаем список сотрудников с указанным стажем
    }

    // Метод для поиска номеров телефонов по имени
    public List<String> findPhoneNumbersByName(String targetName) {
        List<String> phoneNumbers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equals(targetName)) {
                phoneNumbers.add(employee.getPhoneNumber());
            }
        }
        return phoneNumbers; // Возвращаем список номеров телефонов для указанного имени
    }

    // Метод для поиска сотрудника по табельному номеру и возврата этого сотрудника
    public Employee findEmployeeByEmployeeId(int targetEmployeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == targetEmployeeId) {
                return employee;
            }
        }
        return null; // Возвращаем null, если сотрудник с указанным табельным номером не найден
    }

    public static class Employee {
        private int employeeId;
        private String phoneNumber;
        private String name;
        private int experience;

        // Конструктор класса Employee
        public Employee(int employeeId, String phoneNumber, String name, int experience) {
            this.employeeId = employeeId;
            this.phoneNumber = phoneNumber;
            this.name = name;
            this.experience = experience;
        }

        // Геттеры для получения свойств сотрудника
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

        // Переопределение метода toString для класса Employee
        @Override
        public String toString() {
            return "Employee{" +
                    "employeeId=" + employeeId +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", name='" + name + '\'' +
                    ", experience=" + experience +
                    '}';
        }
    }
}

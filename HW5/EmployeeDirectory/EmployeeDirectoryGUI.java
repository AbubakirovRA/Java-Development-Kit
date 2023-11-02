package EmployeeDirectory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class EmployeeDirectoryGUI extends JFrame {

  private EmployeeDirectory employeeDirectory;

  private JTextField employeeIdField;
  private JTextField phoneNumberField;
  private JTextField nameField;
  private JTextField experienceField;
  private JTextArea resultArea;

  public EmployeeDirectoryGUI(EmployeeDirectory employeeDirectory) {
    this.employeeDirectory = employeeDirectory;
    setTitle("Employee Directory");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 400);
    setLayout(new BorderLayout());

    createInputPanel();
    createResultPanel();

    setVisible(true);
  }

  private void createInputPanel() {
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new GridLayout(5, 2));

    employeeIdField = new JTextField();
    phoneNumberField = new JTextField();
    nameField = new JTextField();
    experienceField = new JTextField();

    inputPanel.add(new JLabel("Табельный номер:"));
    inputPanel.add(employeeIdField);
    inputPanel.add(new JLabel("Телефон:"));
    inputPanel.add(phoneNumberField);
    inputPanel.add(new JLabel("ФИО:"));
    inputPanel.add(nameField);
    inputPanel.add(new JLabel("Стаж:"));
    inputPanel.add(experienceField);

    JButton addButton = new JButton("Добавить работника");
    addButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            int employeeId = Integer.parseInt(employeeIdField.getText());
            String phoneNumber = phoneNumberField.getText();
            String name = nameField.getText();
            int experience = Integer.parseInt(experienceField.getText());
            employeeDirectory.addEmployee(
              employeeId,
              phoneNumber,
              name,
              experience
            );
            employeeIdField.setText("");
            phoneNumberField.setText("");
            nameField.setText("");
            experienceField.setText("");
          } catch (NumberFormatException ex) {
            // Вывести сообщение об ошибке ввода числа
            JOptionPane.showMessageDialog(
              EmployeeDirectoryGUI.this,
              "Значение стажа указывать цифрой. Введите верное значение.",
              "Ошибка ввода",
              JOptionPane.ERROR_MESSAGE
            );
          }
        }
      }
    );

    inputPanel.add(addButton);

    add(inputPanel, BorderLayout.NORTH);
  }

  private void createResultPanel() {
    resultArea = new JTextArea();
    resultArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(resultArea);

    add(scrollPane, BorderLayout.CENTER);

    JButton listAllButton = new JButton("Список работников");
    listAllButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          List<EmployeeDirectory.Employee> employees = employeeDirectory.getAllEmployees();
          displayEmployees(employees, "Полный список:");
        }
      }
    );

    JButton findExperiencedButton = new JButton(
      "Найти работников со стажем более 5 лет"
    );
    findExperiencedButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          List<EmployeeDirectory.Employee> experiencedEmployees = employeeDirectory.findEmployeesByExperience(
            5
          );
          displayEmployees(
            experiencedEmployees,
            "Работники со стажем 5 лет и более:"
          );
        }
      }
    );

    JButton findPhoneNumbersButton = new JButton("Поиск телефона по имени");
    findPhoneNumbersButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String targetName = nameField.getText();
            List<String> phoneNumbers = employeeDirectory.findPhoneNumbersByName(targetName);
            displayPhoneNumbers(phoneNumbers, "Телефон для работника  " + targetName + ":");
        }
    });

    JButton findEmployeeButton = new JButton(
      "Поиск работника по табельному номеру"
    );
    findEmployeeButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            int employeeId = Integer.parseInt(employeeIdField.getText());
            EmployeeDirectory.Employee foundEmployee = employeeDirectory.findEmployeeByEmployeeId(
              employeeId
            );
            if (foundEmployee != null) {
              resultArea.setText(
                "Найдены работники: " + foundEmployee.getName()
              );
            } else {
              resultArea.setText("Работники не найдены");
            }
          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
              EmployeeDirectoryGUI.this,
              "Неверный табельный номер. Введите верное значение.",
              "Ошибка ввода",
              JOptionPane.ERROR_MESSAGE
            );
          }
        }
      }
    );

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(listAllButton);
    buttonPanel.add(findExperiencedButton);
    buttonPanel.add(findPhoneNumbersButton);
    buttonPanel.add(findEmployeeButton);

    add(buttonPanel, BorderLayout.SOUTH);
  }

  private void displayEmployees(
    List<EmployeeDirectory.Employee> employees,
    String message
  ) {
    resultArea.setText(message + "\n");
    for (EmployeeDirectory.Employee employee : employees) {
      resultArea.append(employee.toString() + "\n");
    }
  }

  private void displayPhoneNumbers(List<String> phoneNumbers, String message) {
    resultArea.setText(message + "\n");
    for (String phoneNumber : phoneNumbers) {
      resultArea.append(phoneNumber + "\n");
    }
  }

  public static void main(String[] args) {
    EmployeeDirectory directory = new EmployeeDirectory();
    SwingUtilities.invokeLater(() -> new EmployeeDirectoryGUI(directory));
  }
}

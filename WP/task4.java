// Задача 5: Обработка исключений
// Цель: Изучить обработку исключений в контексте графических фреймворков.

// Создайте простое графическое приложение с использованием Swing или JavaFX.
// Добавьте обработку событий для некоторых действий пользователя (например, клика по кнопке).
// В обработчике событий добавьте генерацию исключения.
// Реализуйте корректную обработку этого исключения, чтобы приложение продолжало работать после его возникновения.

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class task4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Exception Handling Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        frame.add(panel);

        JButton button = new JButton("Generate Exception");
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Генерируем исключение при клике на кнопку
                    int result = 10 / 0; // Это вызовет деление на ноль и исключение ArithmeticException
                } catch (ArithmeticException ex) {
                    // Обработка исключения: вывод сообщения об ошибке
                    JOptionPane.showMessageDialog(frame, "Ошибка: деление на ноль!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
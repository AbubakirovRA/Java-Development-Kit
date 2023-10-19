// Задача 4: Работа с анонимными классами
// Цель: Получить практический опыт в создании и использовании анонимных классов.

// Создайте интерфейс Clickable с методом click().
// В основном классе программы создайте анонимный класс, реализующий Clickable, и вызовите метод click().

// интерфейс Clickable с методом click()
interface Clickable {
    void click();
}

public class task3 {
    public static void main(String[] args) {
        // Создайте анонимный класс, реализующий интерфейс Clickable, и вызовите метод click()
        Clickable clickable = new Clickable() {
            @Override
            public void click() {
                System.out.println("Button clicked!");
            }
        };

        // Вызов метода click() из анонимного класса
        clickable.click();
    }
}


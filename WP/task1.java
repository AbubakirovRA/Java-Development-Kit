// Задача 1: Реализация интерфейсов и множественное наследование
// Цель: Получить практический опыт в реализации интерфейсов и использовании множественного наследования.

// Создайте два интерфейса: Runnable и Eater. В Runnable определите метод run(), в Eater — eat().
// Создайте класс Animal, который реализует оба интерфейса.
// Реализуйте методы run() и eat() в классе Animal.

// интерфейс Runnable
interface Runnable {
    void run();
}

// интерфейс Eater
interface Eater {
    void eat();

    // Задача 2: Добавьте метод по умолчанию sleep() в интерфейс Eater
    default void sleep() {
        System.out.println("Animal is sleeping");
    }
}

// класс Animal, который реализует оба интерфейса
class Animal implements Runnable, Eater {
    @Override
    public void run() {
        System.out.println("Animal is running");
    }

    @Override
    public void eat() {
        System.out.println("Animal is eating");
    }
}

public class task1 {
    public static void main(String[] args) {
        // экземпляр класса Animal
        Animal animal = new Animal();

        // Вызов методов run() и eat()
        animal.run();
        animal.eat();

        // Задача 2: Вызовите метод sleep() из интерфейса Eater
        animal.sleep();
    }
}
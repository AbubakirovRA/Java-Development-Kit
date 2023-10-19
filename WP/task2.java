// Задача 3: Работа с адаптерами и частичной реализацией интерфейсов
// Цель: Понять принцип работы адаптеров и частичной реализации интерфейсов.

// Создайте интерфейс Listener с большим количеством методов.
// Создайте абстрактный класс Adapter, который реализует интерфейс Listener, оставляя все методы пустыми.
// Создайте класс MouseAdapter, который расширяет Adapter и переопределяет только один метод

// Задача 3: Работа с адаптерами и частичной реализацией интерфейсов

// интерфейс Listener с большим количеством методов
interface Listener {
    void method1();
    void method2();
    void method3();
    // еще методы по необходимости
}

// абстрактный класс Adapter, который реализует интерфейс Listener
abstract class Adapter implements Listener {
    // Оставляем все методы пустыми
    @Override
    public void method1() { }
    
    @Override
    public void method2() { }
    
    @Override
    public void method3() { }
}

// класс MouseAdapter, который расширяет Adapter и переопределяет только один метод
class MouseAdapter extends Adapter {
    @Override
    public void method1() {
        System.out.println("MouseAdapter: Method 1");
    }
}

public class task2 {
    public static void main(String[] args) {
        // экземпляр класса MouseAdapter
        MouseAdapter mouseAdapter = new MouseAdapter();

        // Вызов метода method1() из MouseAdapter
        mouseAdapter.method1();
    }
}
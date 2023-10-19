// Дописать третье задание таким образом, 
// чтобы в идентификатор типа Developer записывался объект Frontender, 
// и далее вызывался метод developGUI(), 
// не изменяя существующие интерфейсы, только код основного класса.

// пересмотрим третью задачу и реализуем ее так, чтобы класс MouseAdapter хранил объект Frontender и мог вызывать его метод developGUI(). 
// Не будем реализовывать интерфейс Listener в классе MouseAdapter, поскольку он будет использоваться для другой цели.

class Frontender {
    public void developGUI() {
        System.out.println("Frontend developer is developing GUI");
    }
}

class MouseAdapter {
    private Frontender frontender;

    public MouseAdapter(Frontender frontender) {
        this.frontender = frontender;
    }

    public void performMouseClick() {
        System.out.println("Mouse clicked");
        frontender.developGUI(); // Вызываем метод developGUI() у объекта Frontender
    }
}

public class Main {
    public static void main(String[] args) {
        Frontender frontender = new Frontender();
        MouseAdapter mouseAdapter = new MouseAdapter(frontender);

        // Вызываем метод performMouseClick() у MouseAdapter, который затем вызывает developGUI() у Frontender
        mouseAdapter.performMouseClick();
    }
}

// В этом коде мы создали MouseAdapter, который принимает объект Frontender через конструктор 
// и может вызывать метод developGUI() этого объекта при необходимости. 
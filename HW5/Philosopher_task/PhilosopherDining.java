package Philosopher_task;
// Есть пять философов (потоки), которые могут либо обедать либо размышлять.
// Каждый философ должен пообедать три раза. Каждый прием пищи длиться 500 миллисекунд
// После каждого приема пищи философ должен размышлять
// Не должно возникнуть общей блокировки
// Философы не должны есть больше одного раза подряд

import java.util.concurrent.Semaphore;

public class PhilosopherDining {

    public static void main(String[] args) {
        int numPhilosophers = 5; // Количество философов
        DinnerTable table = new DinnerTable(numPhilosophers);

        // Создаем массив философов и задаем им явные имена
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher("Философ " + (i + 1), table);
            philosophers[i].start();
        }

        // Ожидание завершения работы всех философов
        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
                System.out.println(philosopher.getName() + " поел " + philosopher.getMealsEaten() + " раза.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Philosopher extends Thread {
        private String name; // Имя философа
        private int mealsEaten = 0; // Количество приемов пищи
        private final DinnerTable table; // Обеденный стол

        public Philosopher(String name, DinnerTable table) {
            this.name = name;
            this.table = table;
            this.setName(name);
        }

        @Override
        public void run() {
            while (mealsEaten < 3) {
                размышлять();
                try {
                    table.eat(this);
                    eat();
                    mealsEaten++;
                    table.release(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void размышлять() {
            System.out.println(name + " размышляет.");
            try {
                // Имитация размышления (случайное время)
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void eat() {
            System.out.println(name + " обедает.");
            try {
                // Имитация еды (500 миллисекунд)
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public int getMealsEaten() {
            return mealsEaten;
        }
    }

    public static class DinnerTable {
        private Semaphore semaphore; // Семафор для управления доступом к столу
        private Philosopher lastEatingPhilosopher; // Философ, который последним начал есть

        public DinnerTable(int numPhilosophers) {
            semaphore = new Semaphore(1); // Используем семафор с ограничением в 1
            lastEatingPhilosopher = null;
        }

        public void eat(Philosopher philosopher) throws InterruptedException {
            semaphore.acquire(); // Философ пытается начать есть, захватывая семафор
            if (lastEatingPhilosopher == philosopher) {
                semaphore.release(); // Философ попытался есть дважды подряд, освобождаем стол
                return;
            }
            lastEatingPhilosopher = philosopher;
            System.out.println(philosopher.getName() + " начал есть.");
        }

        public void release(Philosopher philosopher) {
            lastEatingPhilosopher = null;
            semaphore.release(); // Освобождение семафора после завершения еды
        }
    }
}
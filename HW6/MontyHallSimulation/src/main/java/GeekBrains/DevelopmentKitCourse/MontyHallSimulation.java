// package GeekBrains.DevelopmentKitCourse;

import java.util.HashMap;
import java.util.Random;

public class MontyHallSimulation {
    public static void main(String[] args) {
        int totalSteps = 1000; // Общее количество шагов цикла

        // Создание HashMap для сохранения результатов (шаг цикла -> результат)
        HashMap<Integer, Boolean> results = new HashMap<>();

        // Симуляция парадокса Монти Холла
        simulateMontyHall(totalSteps, results);

        // Вывод статистики
        printStatistics(results);
    }

    private static void simulateMontyHall(int totalSteps, HashMap<Integer, Boolean> results) {
        Random random = new Random();

        for (int step = 1; step <= totalSteps; step++) {
            // Реализация симуляции парадокса Монти Холла

            // 1. Участник делает первоначальный выбор (выбирает дверь)
            int chosenDoor = random.nextInt(3) + 1; // Генерируем случайное число от 1 до 3

            // 2. Ведущий открывает одну из дверей, за которой находится коза
            int carBehindDoor = random.nextInt(3) + 1; // Генерируем место, где находится автомобиль

            // Предоставляем ведущему возможность открыть одну из дверей, за которой находится коза,
            // и которую участник не выбрал
            int doorToOpen;
            do {
                doorToOpen = random.nextInt(3) + 1;
            } while (doorToOpen == chosenDoor || doorToOpen == carBehindDoor);

            // 3. Участник решает изменить свой выбор или остаться при первоначальном
            boolean changeChoice = random.nextBoolean();

            // Если участник решает изменить свой выбор, меняем выбранную дверь
            if (changeChoice) {
                do {
                    chosenDoor = random.nextInt(3) + 1;
                } while (chosenDoor == doorToOpen || chosenDoor == carBehindDoor);
            }

            // 4. Проверка результата
            boolean win = chosenDoor == carBehindDoor;

            // 5. Сохранение результата в HashMap
            results.put(step, win);
        }
    }

    private static void printStatistics(HashMap<Integer, Boolean> results) {
        // Вывод статистики
        long positiveResults = results.values().stream().filter(Boolean::valueOf).count();
        long negativeResults = results.size() - positiveResults;
        double positivePercentage = (double) positiveResults / results.size() * 100;
        double negativePercentage = 100 - positivePercentage;

        // Вывод результатов каждого шага
        System.out.println("\nРезультаты по шагам:");
        results.forEach((step, win) -> {
            System.out.println("Шаг " + step + ": " + (win ? "Победа" : "Поражение"));
        });

        System.out.println();
        System.out.println("Статистика:");
        System.out.println("Позитивных результатов: " + positiveResults + " (" + positivePercentage + "%)");
        System.out.println("Негативных результатов: " + negativeResults + " (" + negativePercentage + "%)");
    }
}

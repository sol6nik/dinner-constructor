package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    System.out.println("До новых встречь!");
                    return;
                default:
                    System.out.println("Команда отсутствует. Пожалуйста выберите команду из списка!");
            }
        }
    }

    // метод для вывода меню
    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    // метод для добавления нового блюда
    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();


        boolean addedSuccessfully = dc.addDish(dishType, dishName);
        if (addedSuccessfully) {
            System.out.println("Блюдо '" + dishName + "' успешно добавлено!");
        }
// Если метод addDish вернул false, уже добавлено, выводим соответствующее сообщение
    }

    // метод для генерации комбинаций блюд
    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> dishTypes = new ArrayList<>();
        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");

        String nextItem;
        boolean validTypes = false;
        while (!validTypes) {
            dishTypes.clear();
            while (!(nextItem = scanner.nextLine()).isEmpty()) {
                dishTypes.add(nextItem);
            }

            // Проверяем, все ли введенные типы блюд содержатся в каталоге
            validTypes = true;
            for (String type : dishTypes) {
                if (!dc.getDishes().containsKey(type)) {
                    System.out.println("Нет блюд типа '" + type + "'. Введите другие:");
                    validTypes = false;
                    break;
                }
            }
        }

// Генерируем комбинации блюд и выводим на экран
        dc.generateCombos(numberOfCombos, dishTypes);
    }
}
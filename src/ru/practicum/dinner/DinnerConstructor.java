package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class DinnerConstructor {
    private HashMap<String, ArrayList<String>> dishes;
    private Random random = new Random();  // Поле класса, создаётся один раз
    private Scanner scanner = new Scanner(System.in);

    public DinnerConstructor() {
        dishes = new HashMap<>();
    }

    // метод для добавления нового блюда
    public boolean addDish(String dishType, String dishName) {
        // Проверяем, есть ли уже такой тип блюда в картотеке
        if (dishes.containsKey(dishType)) {
            ArrayList<String> existingDishes = dishes.get(dishType);
            // Проверяем, не содержится ли уже такое блюдо в списке
            if (existingDishes.contains(dishName)) {
                // Если блюдо уже есть, выводим сообщение и возвращаем false
                System.out.println("Блюдо '" + dishName + "' уже добавлено для типа '" + dishType + "'");
                return false;
            } else {
                existingDishes.add(dishName);
                return true;
            }
        } else {
            final ArrayList<String> newDishList = new ArrayList<>();
            newDishList.add(dishName);
            dishes.put(dishType, newDishList);
            return true;
        }
    }

    // метод для генерации комбинаций блюд
    public void generateCombos(int numberOfCombos, ArrayList<String> dishTypes) {
        for (int i = 1; i <= numberOfCombos; i++) {
            System.out.println("Комбинация блюд " + i + ":");
            for (String dishType : dishTypes) {
                while (!dishes.containsKey(dishType)) {
                    System.out.println("Нет блюд типа '" + dishType + "'. Введите другие типы:");
                    dishType = scanner.nextLine();  // Используем уже созданный объект Scanner
                }
                ArrayList<String> availableDishes = dishes.get(dishType);
                int randomIndex = random.nextInt(availableDishes.size());  // Используем уже созданный объект Random
                String dish = availableDishes.get(randomIndex);
                System.out.println("- " + dish);
            }
            System.out.println();
        }
    }

    public HashMap<String, ArrayList<String>> getDishes() {
        return dishes;
    }
}

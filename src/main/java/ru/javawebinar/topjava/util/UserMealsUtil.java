package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = new HashMap<>();
        for (UserMeal userMeal : meals) {
            map.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), (i1, i2) -> i1 + i2);
        }

        List<UserMealWithExcess> list = new ArrayList<>();
        for (UserMeal userMeal : meals) {
            if (!TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)) continue;
            list.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                    map.getOrDefault(userMeal.getDateTime().toLocalDate(), userMeal.getCalories()) > caloriesPerDay));
        }

        return list;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams

        List<UserMealWithExcess> stream = meals
                .stream()
                .collect(Collectors.groupingBy(u -> u.getDateTime().toLocalDate()))
                .entrySet().stream()
                .collect(Collectors.groupingBy(u -> u.getValue().stream()
                        .mapToInt(UserMeal::getCalories).sum() > caloriesPerDay))
                .entrySet().stream()
                .flatMap(e -> e.getValue().stream()
                        .flatMap(o -> o.getValue().stream())
                        .map(u -> new UserMealWithExcess(u.getDateTime(), u.getDescription(), u.getCalories(), e.getKey())))
                .filter(u -> TimeUtil.isBetweenHalfOpen(u.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
        return stream;

    }
}
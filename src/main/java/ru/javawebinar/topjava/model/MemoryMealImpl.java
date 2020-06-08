package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class MemoryMealImpl implements MealModel {

    private static Map<Integer, Meal> meals = new HashMap<Integer, Meal>(){
        {
          put( 1, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
          put( 2, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
          put( 3,new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
          put( 4,new Meal(LocalDateTime.of(2020, Month.JANUARY, 31,0,0), "Еда на граничное значение",100));
          put( 5,new Meal(LocalDateTime.of(2020, Month.JANUARY, 31,10,0), "Завтрак",1000));
          put( 6,new Meal(LocalDateTime.of(2020, Month.JANUARY, 31,13,0), "Обед",500));
          put( 7,new Meal(LocalDateTime.of(2020, Month.JANUARY, 31,20,0), "Ужин",410));
        }
    };


    private static MemoryMealImpl instance = new MemoryMealImpl();

    public static MemoryMealImpl getInstance() {
        return instance;
    }

    private MemoryMealImpl() {}

    @Override
    public Optional<Meal> delete(long id) {
        return Optional.empty();
    }

    @Override
    public Meal createOrUpdate(String description, LocalDateTime dateTime, int calories) {
        return null;
    }

    @Override
    public Optional<List<Meal>> getAll() {
        return Optional.of(new ArrayList<>(meals.values()));
    }
}

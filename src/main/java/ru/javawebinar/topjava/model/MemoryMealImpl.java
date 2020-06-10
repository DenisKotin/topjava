package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryMealImpl implements MealModel {

    private  AtomicInteger key = new AtomicInteger(0);

    private  List<Meal> meals =  new CopyOnWriteArrayList<Meal>( Arrays.asList(
          new Meal(key.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
          new Meal(key.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
          new Meal(key.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
          new Meal(key.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 31,0,0), "Еда на граничное значение",100),
          new Meal(key.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 31,10,0), "Завтрак",1000),
          new Meal(key.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 31,13,0), "Обед",500),
          new Meal(key.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 31,20,0), "Ужин",410)
        )
    );


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
        return Optional.of(new ArrayList<>(meals));
    }
}

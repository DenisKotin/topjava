package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MealModel {
    void delete(long id);

    boolean create(String description, LocalDateTime dateTime, int calories);

    boolean update(long id, String description, LocalDateTime dateTime, int calories);

    List<Meal> getAll();


}

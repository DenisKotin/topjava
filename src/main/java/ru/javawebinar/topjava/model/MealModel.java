package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MealModel {
    Optional<Meal> delete(long id);

    Meal createOrUpdate(String description, LocalDateTime dateTime, int calories);

    Optional<List<Meal>> getAll();


}

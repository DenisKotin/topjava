package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.TestUtils.*;

public class MealTestData {
    public static final Meal MEAL_1 =
            new Meal(MEAL_USER_1,LocalDateTime.of(2015, 4, 20, 10,0), "Завтрак" ,500);

    public static final Meal MEAL_2 =
            new Meal(MEAL_USER_2, LocalDateTime.of(2015, 04,20,14,0,0), "Обед" ,1000);

    public  static final Meal MEAL_3 =
            new Meal(MEAL_USER_3, LocalDateTime.of(2015, 04,20,19,0,0), "Ужин" ,500);

    public static final Meal MEAL_4 =
            new Meal(MEAL_ADMIN_1,LocalDateTime.of(2015, 4, 20, 10,0), "Ужин_Админ" ,700);

    public static final Meal MEAL_5 =
            new Meal(MEAL_ADMIN_2, LocalDateTime.of(2015, 04,20,14,0,0), "Обед_Адми" ,1200);

    public static final Meal MEAL_6 =
            new Meal(MEAL_ADMIN_3, LocalDateTime.of(2015, 04,20,19,0,0), "Ужин_Адми" ,800);

    public static Meal getNewMeal() {
        return new Meal( LocalDateTime.now(), "New", 333);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}

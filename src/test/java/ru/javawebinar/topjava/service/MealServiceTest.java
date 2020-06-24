package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;

import static org.junit.Assert.assertNull;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.TestUtils.MEAL_USER_1;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealRepository repository;


    @Test
    public void create() {
        Meal newMeal = getNewMeal();
        Meal created = repository.save(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(repository.get(newId, USER_ID), newMeal);
    }

    @Test
    public void updateNotAutorizated() {
        Meal oldMeal = repository.get(MEAL_USER_1, USER_ID);
        oldMeal.setCalories(333);
        assertNull(repository.save(oldMeal, ADMIN_ID));
    }

    @Test
    public void get() {
        Meal meal = repository.get(MEAL_USER_1, USER_ID);
        assertMatch(meal, repository.get(MEAL_USER_1, USER_ID));
    }

    @Test
    public void delete() {
        repository.delete(MEAL_USER_1, USER_ID);
        assertNull(repository.get(MEAL_USER_1, USER_ID));
    }

    @Test
    public void getAll() {
        List <Meal> list = repository.getAll(USER_ID);
        assertMatch(list, MEAL_3, MEAL_2, MEAL_1 );
    }

    @Test
    public void update() {
        Meal oldMeal = repository.get(MEAL_USER_1, USER_ID);
        oldMeal.setCalories(333);
        repository.save(oldMeal, USER_ID);
        assertMatch(oldMeal, repository.get(MEAL_USER_1, USER_ID));
    }


}
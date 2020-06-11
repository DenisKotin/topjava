package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealModel;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.model.MemoryMealImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to mealList.jsp");

        MealModel meals = MemoryMealImpl.getInstance();

        String opString =req.getParameter("action");
        if ("edit".equalsIgnoreCase(opString)){
            System.out.println("___");
        }

        if ("delete".equalsIgnoreCase(opString)){
            int userId = Integer.parseInt(req.getParameter("userId"));
            log.debug("delete"+userId);
            meals.delete(userId);
        }

        //System.out.println(req.getParameter("action"));

        /*
        ну хз пока 2000
         */

        int dayCalories = 2000;


        List<MealTo> mealTo = MealsUtil.filteredByStreams(meals.getAll().get(), LocalTime.MIN, LocalTime.MAX, dayCalories);
        req.setAttribute("maleList", mealTo);

        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }


}

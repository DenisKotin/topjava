package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController {

    private final MealService service;

    public JspMealController(MealService service) {
        this.service = service;
    }


    @GetMapping("/filtered")
    public String filterMeal(Model model, HttpServletRequest request){
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));

        List<Meal> mealsDateFiltered = service.getBetweenInclusive(startDate, endDate, SecurityUtil.authUserId());
        model.addAttribute("meals",MealsUtil.getFilteredTos(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(), startTime, endTime));

        return "meals";
    }

    @GetMapping("")
    public String getMeals(Model model, HttpServletRequest request){
      model.addAttribute("meals", MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()),
                SecurityUtil.authUserCaloriesPerDay()));
        return "meals";
    }

    @GetMapping("/delete")
    public String deleteMeal(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        service.delete(id, SecurityUtil.authUserId());
        return "redirect:/meals";

    }

    @GetMapping("/update")
    public String updateMeal(Model model, HttpServletRequest request){
        String stringId = request.getParameter("id");
        final Meal meal = stringId == null ?
                new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                service.get(Integer.parseInt((String) stringId), SecurityUtil.authUserId());

        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("/postMeal")
    public String postMeal(HttpServletRequest request){
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.isEmpty(request.getParameter("id"))){
            service.create(meal, SecurityUtil.authUserId());
        } else {
            meal.setId(Integer.parseInt(request.getParameter("id")));
            service.update(meal,  SecurityUtil.authUserId());
        }

        return "redirect:/meals";
    }

}

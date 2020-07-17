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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController {

    private final MealService service;

    public JspMealController(MealService service) {
        this.service = service;
    }

    @ModelAttribute
    private void addAttribute(Model model, HttpServletRequest request){
        String id = request.getParameter("id");
        if (id != null) {
            model.addAttribute("id", id);
        }

    }

    @GetMapping("")
    public String getMeals(Model model){
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
    public String updateMeal(Model model){
        Object stringId = model.getAttribute("id");
        final Meal meal = stringId == null ?
                new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                service.get(Integer.parseInt((String) stringId), SecurityUtil.authUserId());

        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("/post")
    private void postMeal(Model model){

//        Meal meal = new Meal(
//                LocalDateTime.parse((String) model.getAttribute("dateTime"),
//                model.getAttribute("description"),22));


   //     Integer.parseInt((String) model.getAttribute("calories"))));



//      //  Meal meal;
//        meal = new Meal(
//                LocalDateTime.parse(model.getAttribute("dateTime"),
//                request.getParameter("description"),
//                Integer.parseInt(request.getParameter("calories")));
//
//
//        request.setCharacterEncoding("UTF-8");
//        Meal meal = new Meal(
//                LocalDateTime.parse(request.getParameter("dateTime")),
//                request.getParameter("description"),
//                Integer.parseInt(request.getParameter("calories")));
//
//        if (StringUtils.isEmpty(request.getParameter("id"))) {
//            mealController.create(meal);
//        } else {
//            mealController.update(meal, getId(request));
//        }
//        response.sendRedirect("meals");


    }



}

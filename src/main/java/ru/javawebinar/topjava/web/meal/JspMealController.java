package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController {

    @Autowired
    private MealRestController controller;

    @GetMapping("")
    public String getMeals(Model model){
        model.addAttribute("meals", controller.getAll());
        return "meals";
    }

    @DeleteMapping("")
    public String deleteMeal(HttpServletRequest request){
        int Id = Integer.parseInt(request.getParameter("id"));
        controller.delete(Id);
        return "redirect:meals";

    }

}

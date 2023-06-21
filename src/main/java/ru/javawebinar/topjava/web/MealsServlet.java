package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {


    private int CALORIES_PER_DAY = 2000;
    private static final Logger log = getLogger(ru.javawebinar.topjava.web.MealsServlet.class);
    private static String EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    List<Meal> meals = new ArrayList<>();
    List<MealTo> mealsTo = new ArrayList<>();

    {
        meals.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String forward = "";
        String action = request.getParameter("action");
        if (action == null) {
            mealsTo = MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
            request.setAttribute("meals", mealsTo);
        } else if (action.equalsIgnoreCase("delete")) {
            String mealId = request.getParameter("mealId");
            meals.remove(Integer.parseInt(mealId));
            forward = LIST_MEAL;
            mealsTo = MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
            request.setAttribute("meals", mealsTo);
        } else if (action.equalsIgnoreCase("update")) {
            forward = EDIT;
            int mealToId = Integer.parseInt(request.getParameter("mealId"));
            MealTo mealTo = mealsTo.get(mealToId);
            request.setAttribute("meal", mealTo);
            request.setAttribute("mealToId", mealToId);
            System.out.println();
        } else if (action.equalsIgnoreCase("listMeal")) {
            forward = LIST_MEAL;
            mealsTo = MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
            request.setAttribute("meals", mealsTo);
        } else if (action.equalsIgnoreCase("create")) {
            forward = EDIT;
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        int calories = Integer.parseInt(req.getParameter("calories"));
        String mealToId = req.getParameter("id");
        if (!mealToId.isEmpty()) {
            meals.remove(Integer.parseInt(mealToId));
        }
        Meal meal = new Meal(localDateTime, description, calories);
        meals.add(meal);
        resp.sendRedirect(req.getRequestURL().toString() + "?action=listMeal");
    }
}


package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryMealRepo;
import ru.javawebinar.topjava.repository.MealRepo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {

    private static final int CALORIES_PER_DAY = 2000;
    private static final Logger log = getLogger(MealsServlet.class);
    private MealRepo mealsRepo;

    @Override
    public void init() {
        mealsRepo = new InMemoryMealRepo();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case ("delete"):
                log.debug("enter in case: delete");
                String mealId = request.getParameter("mealId");
                mealsRepo.delete(Integer.parseInt(mealId));
                List<MealTo> mealsTo = MealsUtil.filteredByStreams(mealsRepo.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
                request.setAttribute("meals", mealsTo);
                response.sendRedirect("meals");
                break;
            case ("update"):
                log.debug("enter in case: update");
                int mealToId = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = mealsRepo.getById(mealToId);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("meal.jsp").forward(request, response);
                break;
            case ("add"):
                log.debug("enter in case: add");
                request.getRequestDispatcher("meal.jsp").forward(request, response);
                break;
            default:
                log.debug("enter in case: default");
                mealsTo = MealsUtil.filteredByStreams(mealsRepo.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
                request.setAttribute("meals", mealsTo);
                request.getRequestDispatcher("meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("enter doPost method in MealsServlet");
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        int calories = Integer.parseInt(req.getParameter("calories"));
        Meal meal = new Meal(null, localDateTime, description, calories);

        if (req.getParameter("id").isEmpty()) {
            mealsRepo.create(meal);
            log.debug("meal has been created");
        } else {
            int id = Integer.parseInt(req.getParameter("id"));
            meal.setId(id);
            mealsRepo.update(meal);
            log.debug("meal has been updated");
        }
        resp.sendRedirect(req.getRequestURL().toString());
        log.debug("send redirect from post to meals");
    }
}


package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryRepoImplementation implements RepoInterface {

    private final List<Meal> mealList = new ArrayList<>();
    private static Integer counter = 0;

    {
        init();
    }

    @Override
    public void create(Integer id, LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(counter, dateTime, description, calories);
        mealList.add(meal);
        increment();
    }

    @Override
    public Meal getById(Integer id) {
        return mealList.stream().filter(m -> Objects.equals(m.getId(), id)).findFirst().get();
    }

    @Override
    public void update(Integer id, LocalDateTime dateTime, String description, int calories) {
        Meal meal = getById(id);
        meal.setDateTime(dateTime);
        meal.setDescription(description);
        meal.setCalories(calories);
    }

    @Override
    public void delete(Integer id) {
        mealList.remove(getById(id));
    }

    @Override
    public List<Meal> getAll() {
        return mealList;
    }

    private void increment() {
        counter++;
    }

    private void init() {
        create(counter, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        create(counter, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
        create(counter, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
        create(counter, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
        create(counter, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
        create(counter, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
        create(counter, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    }
}

package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryRepoImplementation implements RepoInterface {

    private final List<Meal> mealList = new ArrayList<>();

        {
        mealList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        mealList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        mealList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        mealList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        mealList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        mealList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        mealList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    //ToDo Все детали хранения в памяти (коллекция, генерация нового id) должны быть внутри реализации интерфейса и недоступны снаружи.


    @Override
    public void create(Meal meal) {
        mealList.add(meal);
    }

    @Override
    public Meal getById(Integer id) {
        return mealList.stream().filter(m-> Objects.equals(m.getId(), id)).findFirst().get();
    }

    @Override
    public void update(Meal updatedMeal) {
            Meal meal = getById(updatedMeal.getId());
            meal.setCalories(updatedMeal.getCalories());
            meal.setDescription(updatedMeal.getDescription());
            meal.setDateTime(updatedMeal.getDateTime());
    }

    @Override
    public void delete(Integer id) {
        mealList.remove(getById(id));
    }

    @Override
    public List<Meal> getAll() {
        return mealList;
    }
}

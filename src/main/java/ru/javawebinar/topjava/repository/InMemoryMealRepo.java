package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepo implements MealRepo {

    private final ConcurrentMap<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        init();
    }

    @Override
    public Meal create(Meal meal) {
        int currentCounter = counter.getAndIncrement();
        meal.setId(currentCounter);
        mealMap.put(currentCounter, meal);
        return meal;
    }

    @Override
    public Meal getById(int id) {
        return mealMap.get(id);
    }

    @Override
    public Meal update(Meal meal) {
        return mealMap.replace(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        mealMap.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealMap.values());
    }

    private void init() {
        List<Meal> initMeals = List.of(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
        initMeals.forEach(this::create);
    }
}

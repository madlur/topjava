package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealMapRepo implements MealRepo {

    private final ConcurrentMap<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    private Integer counter = 0;

    {
        init();
    }

    @Override
    public synchronized Meal create(Meal meal) {
        meal.setId(counter);
        mealMap.put(counter, meal);
        increment();
        return meal;
    }

    @Override
    public Meal getById(int id) {
        return mealMap.get(id);
    }

    @Override
    public void update(int id, Meal meal) {
        mealMap.put(id, meal);
    }

    @Override
    public void delete(int id) {
        mealMap.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> mealList = new ArrayList<>(mealMap.values());
        return Collections.unmodifiableList(mealList);
    }

    private void increment() {
        AtomicInteger atomicInteger = new AtomicInteger(counter);
        counter = atomicInteger.incrementAndGet();
    }

    private void init() {
        create(new Meal(counter, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        create(new Meal(counter, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        create(new Meal(counter, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        create(new Meal(counter, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        create(new Meal(counter, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        create(new Meal(counter, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        create(new Meal(counter, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }
}

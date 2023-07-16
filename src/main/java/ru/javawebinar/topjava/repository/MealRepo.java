package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepo {

    Meal create(Meal meal);

    Meal getById(int id);

    void update(int id, Meal meal);

    void delete(int id);

    List<Meal> getAll();

}

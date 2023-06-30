package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface RepoInterface {

    void create(Meal meal);

    Meal getById(Integer id);

    void update(Meal meal);

    void delete(Integer index);

    List<Meal> getAll();

}

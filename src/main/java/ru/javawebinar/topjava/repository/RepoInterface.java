package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface RepoInterface {

    void create(Integer id, LocalDateTime dateTime, String description, int calories);

    Meal getById(Integer id);

    void update(Integer id, LocalDateTime dateTime, String description, int calories);

    void delete(Integer index);

    List<Meal> getAll();

}

package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.criteria.DishSearchCriteria;

import java.util.List;

public interface DishService {

    List<Dish> retrieveAllByCriteria(DishSearchCriteria criteria);

    Dish retrieveById(Long id);

    Dish create(Dish dish);

    Dish update(Dish dish);

    void delete(Long id);

}

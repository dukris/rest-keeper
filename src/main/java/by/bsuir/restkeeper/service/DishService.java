package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.criteria.DishSearchCriteria;

import java.util.List;

public interface DishService {

    /**
     * Retrieve all dishes.
     *
     * @param criteria Criteria
     * @return List of dishes
     */
    List<Dish> retrieveAllByCriteria(DishSearchCriteria criteria);

    /**
     * Retrieve all dishes.
     *
     * @param id Id
     * @return List of dishes
     */
    Dish retrieveById(Long id);

    /**
     * Create new dish.
     *
     * @param dish Dish
     * @return Dish
     */
    Dish create(Dish dish);

    /**
     * Update dish.
     *
     * @param dish Dish
     * @return Dish
     */
    Dish update(Dish dish);

    /**
     * Delete dish.
     *
     * @param id Id
     */
    void delete(Long id);

}

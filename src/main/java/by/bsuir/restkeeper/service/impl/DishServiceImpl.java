package by.bsuir.restkeeper.service.impl;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.criteria.DishSearchCriteria;
import by.bsuir.restkeeper.domain.exception.ResourceNotFoundException;
import by.bsuir.restkeeper.persistence.DishRepository;
import by.bsuir.restkeeper.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    public List<Dish> retrieveAllByCriteria(
            final DishSearchCriteria criteria
    ) {
        if (criteria.getAvailability() != null) {
            return this.dishRepository.findByAvailability(
                    criteria.getAvailability()
            );
        } else {
            return this.dishRepository.findAll();
        }
    }


    @Override
    public Dish retrieveById(final Long id) {
        return this.dishRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Dish with id = " + id + " not found!")
                );
    }

    @Override
    @Transactional
    public Dish create(final Dish dish) {
        return this.dishRepository.save(dish);
    }

    @Override
    @Transactional
    public Dish update(final Dish dish) {
        Dish foundDish = this.retrieveById(dish.getId());
        foundDish.setName(dish.getName());
        foundDish.setDescription(dish.getDescription());
        foundDish.setPrice(dish.getPrice());
        foundDish.setAvailability(dish.getAvailability());
        return this.dishRepository.save(foundDish);
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        this.dishRepository.deleteById(id);
    }
}

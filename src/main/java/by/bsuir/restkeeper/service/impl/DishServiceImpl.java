package by.bsuir.restkeeper.service.impl;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.criteria.DishSearchCriteria;
import by.bsuir.restkeeper.domain.exception.ResourceNotFoundException;
import by.bsuir.restkeeper.persistence.DishRepository;
import by.bsuir.restkeeper.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    public List<Dish> retrieveAllByCriteria(DishSearchCriteria criteria) {
        return criteria.getAvailability() != null ?
                dishRepository.findByAvailability(criteria.getAvailability()) :
                dishRepository.findAll();
    }


    @Override
    public Dish retrieveById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish with id = " + id + " not found!"));
    }

    @Override
    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Dish dish) {
        Dish foundDish = retrieveById(dish.getId());
        foundDish.setName(dish.getName());
        foundDish.setPrice(dish.getPrice());
        foundDish.setAvailability(dish.getAvailability());
        return dishRepository.save(foundDish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}

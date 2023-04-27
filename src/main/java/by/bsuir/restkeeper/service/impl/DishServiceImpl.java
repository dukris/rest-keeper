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
    public List<Dish> retrieveAllByCriteria(DishSearchCriteria criteria) {
        return criteria.getAvailability() != null ?
                this.dishRepository.findByAvailability(criteria.getAvailability()) :
                this.dishRepository.findAll();
    }


    @Override
    public Dish retrieveById(Long id) {
        return this.dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish with id = " + id + " not found!"));
    }

    @Override
    @Transactional
    public Dish create(Dish dish) {
        return this.dishRepository.save(dish);
    }

    @Override
    @Transactional
    public Dish update(Dish dish) {
        Dish foundDish = this.retrieveById(dish.getId());
        foundDish.setName(dish.getName());
        foundDish.setDescription(dish.getDescription());
        foundDish.setPrice(dish.getPrice());
        foundDish.setAvailability(dish.getAvailability());
        return this.dishRepository.save(foundDish);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.dishRepository.deleteById(id);
    }
}

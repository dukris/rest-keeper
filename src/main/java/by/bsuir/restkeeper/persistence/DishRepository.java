package by.bsuir.restkeeper.persistence;

import by.bsuir.restkeeper.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findByAvailability(Boolean availability);

}

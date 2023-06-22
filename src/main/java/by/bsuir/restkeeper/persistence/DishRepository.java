package by.bsuir.restkeeper.persistence;

import by.bsuir.restkeeper.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    /**
     * Find dishes be availability.
     *
     * @param availability Availability
     * @return List of dish
     */
    List<Dish> findByAvailability(Boolean availability);

}

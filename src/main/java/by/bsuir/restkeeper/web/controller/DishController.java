package by.bsuir.restkeeper.web.controller;

import by.bsuir.restkeeper.domain.Dish;
import by.bsuir.restkeeper.domain.criteria.DishSearchCriteria;
import by.bsuir.restkeeper.service.DishService;
import by.bsuir.restkeeper.web.dto.DishDto;
import by.bsuir.restkeeper.web.dto.criteria.DishSearchCriteriaDto;
import by.bsuir.restkeeper.web.dto.group.OnCreate;
import by.bsuir.restkeeper.web.dto.group.OnUpdate;
import by.bsuir.restkeeper.web.dto.mapper.DishMapper;
import by.bsuir.restkeeper.web.dto.mapper.criteria.DishSearchCriteriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restkeeper/v1/dishes")
public class DishController {

    private final DishService dishService;
    private final DishMapper dishMapper;
    private final DishSearchCriteriaMapper dishSearchCriteriaMapper;

    /**
     * Get all dishes.
     *
     * @param criteriaDto Criteria
     * @return List of dishes
     */
    @GetMapping
    public List<DishDto> getAllByCriteria(
            final DishSearchCriteriaDto criteriaDto) {
        DishSearchCriteria criteria =
                this.dishSearchCriteriaMapper.toEntity(criteriaDto);
        List<Dish> dishes =
                this.dishService.retrieveAllByCriteria(criteria);
        return this.dishMapper.toDto(dishes);
    }

    /**
     * Get dish by Id.
     *
     * @param id Id
     * @return Dish
     */
    @GetMapping("/{id}")
    public DishDto getById(@PathVariable final Long id) {
        Dish dish = this.dishService.retrieveById(id);
        return this.dishMapper.toDto(dish);
    }

    /**
     * Create a new dish.
     *
     * @param dishDto Dish
     * @return Dish
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@securityExpressions.hasKitchenRole() "
            + "|| @securityExpressions.hasAdminRole()")
    public DishDto create(@Validated(OnCreate.class)
                          @RequestBody final DishDto dishDto) {
        Dish dish = this.dishMapper.toEntity(dishDto);
        dish = this.dishService.create(dish);
        return this.dishMapper.toDto(dish);
    }

    /**
     * Update dish.
     *
     * @param dishDto Dish
     * @return Dish
     */
    @PutMapping("/{id}")
    @PreAuthorize("@securityExpressions.hasKitchenRole() "
            + "|| @securityExpressions.hasAdminRole()")
    public DishDto update(@Validated(OnUpdate.class)
                          @RequestBody final DishDto dishDto) {
        Dish dish = this.dishMapper.toEntity(dishDto);
        dish = this.dishService.update(dish);
        return this.dishMapper.toDto(dish);
    }

    /**
     * Delete dish.
     *
     * @param id Id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@securityExpressions.hasKitchenRole() "
            + "||@securityExpressions.hasAdminRole()")
    public void delete(@PathVariable final Long id) {
        this.dishService.delete(id);
    }

}

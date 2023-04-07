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

    @GetMapping //get dishes by availability
    public List<DishDto> getAllByCriteria(DishSearchCriteriaDto criteriaDto) {
        DishSearchCriteria criteria = dishSearchCriteriaMapper.toEntity(criteriaDto);
        List<Dish> dishes = dishService.retrieveAllByCriteria(criteria);
        return dishMapper.toDto(dishes);
    }

    @GetMapping("/{id}")
    public DishDto getById(@PathVariable Long id) {
        Dish dish = dishService.retrieveById(id);
        return dishMapper.toDto(dish);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DishDto create(@Validated(OnCreate.class) @RequestBody DishDto dishDto) {
        Dish dish = dishMapper.toEntity(dishDto);
        dish = dishService.create(dish);
        return dishMapper.toDto(dish);
    }

    @PutMapping("/{id}")
    public DishDto update(@Validated(OnUpdate.class) @RequestBody DishDto dishDto) {
        Dish dish = dishMapper.toEntity(dishDto);
        dish = dishService.update(dish);
        return dishMapper.toDto(dish);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dishService.delete(id);
    }

}

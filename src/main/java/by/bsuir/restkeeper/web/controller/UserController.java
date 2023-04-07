package by.bsuir.restkeeper.web.controller;

import by.bsuir.restkeeper.domain.Artifact;
import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.domain.criteria.UserSearchCriteria;
import by.bsuir.restkeeper.service.UserService;
import by.bsuir.restkeeper.web.dto.ArtifactDto;
import by.bsuir.restkeeper.web.dto.UserDto;
import by.bsuir.restkeeper.web.dto.criteria.UserSearchCriteriaDto;
import by.bsuir.restkeeper.web.dto.group.OnUpdate;
import by.bsuir.restkeeper.web.dto.mapper.ArtifactMapper;
import by.bsuir.restkeeper.web.dto.mapper.UserMapper;
import by.bsuir.restkeeper.web.dto.mapper.criteria.UserSearchCriteriaMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restkeeper/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final ArtifactMapper artifactMapper;
    private final UserSearchCriteriaMapper userSearchCriteriaMapper;

    @GetMapping
    public List<UserDto> getAllByCriteria(UserSearchCriteriaDto criteriaDto) {
        UserSearchCriteria criteria = userSearchCriteriaMapper.toEntity(criteriaDto);
        List<User> users = userService.retrieveAllByCriteria(criteria);
        return userMapper.toDto(users);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.retrieveById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto,
                          @PathVariable Long id) {
        User user = userMapper.toEntity(userDto);
        user.setId(id);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @SneakyThrows
    @PostMapping ("/{id}/photos")
    public UserDto addPhoto(@Validated ArtifactDto artifactDto,
                            @PathVariable Long id) {
        Artifact artifact = artifactMapper.toEntity(artifactDto);
        User updatedUser = userService.addPhoto(id, artifact);
        return userMapper.toDto(updatedUser);
    }

    @DeleteMapping ("/{id}/photos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhoto(@PathVariable Long id,
                            @RequestParam String filename) {
        userService.deletePhoto(id, filename);
    }


}

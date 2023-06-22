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
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * Get all users.
     *
     * @param criteriaDto Criteria
     * @return List of users
     */
    @GetMapping
    @PreAuthorize("@securityExpressions.hasAdminRole()")
    public List<UserDto> getAllByCriteria(
            final UserSearchCriteriaDto criteriaDto) {
        UserSearchCriteria criteria =
                this.userSearchCriteriaMapper.toEntity(criteriaDto);
        List<User> users = this.userService.retrieveAllByCriteria(criteria);
        return this.userMapper.toDto(users);
    }

    /**
     * Get user by id.
     *
     * @param id Id
     * @return User
     */
    @GetMapping("/{id}")
    @PreAuthorize("@securityExpressions.isUser(#id) "
            + "|| @securityExpressions.hasAdminRole()")
    public UserDto getById(@PathVariable final Long id) {
        User user = this.userService.retrieveById(id);
        return this.userMapper.toDto(user);
    }

    /**
     * Delete user.
     *
     * @param id Id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@securityExpressions.hasAdminRole()")
    public void delete(@PathVariable final Long id) {
        this.userService.delete(id);
    }

    /**
     * Update user.
     *
     * @param userDto User
     * @param id Id
     * @return User
     */
    @PutMapping("/{id}")
    @PreAuthorize("@securityExpressions.isUser(#id) "
            + "|| @securityExpressions.hasAdminRole()")
    public UserDto update(@Validated(OnUpdate.class)
                          @RequestBody final UserDto userDto,
                          @PathVariable final Long id) {
        User user = this.userMapper.toEntity(userDto);
        user.setId(id);
        User updatedUser = this.userService.update(user);
        return this.userMapper.toDto(updatedUser);
    }

    /**
     * Add new photo.
     *
     * @param artifactDto Artifact
     * @param id Id
     * @return User
     */
    @SneakyThrows
    @PostMapping(
            value = "/{id}/photos",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    @PreAuthorize("@securityExpressions.isUser(#id) "
            + "|| @securityExpressions.hasAdminRole()"
    )
    public UserDto addPhoto(@Validated final ArtifactDto artifactDto,
                            @PathVariable final Long id) {
        Artifact artifact = this.artifactMapper.toEntity(artifactDto);
        User updatedUser = this.userService.addPhoto(id, artifact);
        return this.userMapper.toDto(updatedUser);
    }

    /**
     * Delete photo.
     *
     * @param id Id
     * @param filename Filename
     */
    @DeleteMapping("/{id}/photos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@securityExpressions.isUser(#id) "
            + "|| @securityExpressions.hasAdminRole()"
    )
    public void deletePhoto(@PathVariable final Long id,
                            @RequestParam final String filename) {
        this.userService.deletePhoto(id, filename);
    }

}

package by.bsuir.restkeeper.web.dto.criteria;

import by.bsuir.restkeeper.domain.User;
import lombok.Data;

public record UserSearchCriteriaDto(

        User.Role role

) {
}
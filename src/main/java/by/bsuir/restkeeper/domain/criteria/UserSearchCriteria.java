package by.bsuir.restkeeper.domain.criteria;

import by.bsuir.restkeeper.domain.User;
import lombok.Data;

@Data
public class UserSearchCriteria {

    private User.Role role;

}

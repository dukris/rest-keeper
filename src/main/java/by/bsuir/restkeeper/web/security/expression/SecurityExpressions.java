package by.bsuir.restkeeper.web.security.expression;

import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityExpressions {

    private final UserService userService;

    /**
     * Check user's id.
     *
     * @param userId id
     * @return Boolean
     */
    public boolean isUser(final Long userId) {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.retrieveById(userId);
        return authentication.getName().equals(user.getEmail())
                && user.isEnabled();
    }

    /**
     * Check admin role.
     *
     * @return Boolean
     */
    public boolean hasAdminRole() {
        return this.hasRole(User.Role.ROLE_ADMIN);
    }

    /**
     * Check kitchen role.
     *
     * @return Boolean
     */
    public boolean hasKitchenRole() {
        return this.hasRole(User.Role.ROLE_KITCHEN);
    }

    /**
     * Check hall role.
     *
     * @return Boolean
     */
    public boolean hasHallRole() {
        return this.hasRole(User.Role.ROLE_HALL);
    }

    /**
     * Check role.
     *
     * @param role Role
     * @return Boolean
     */
    private boolean hasRole(final User.Role role) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return authentication.getAuthorities().contains(authority);
    }

    /**
     * Check address.
     *
     * @param addressId Id
     * @return Boolean
     */
    public boolean hasAddress(final Long addressId) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.retrieveByEmail(authentication.getName());
        return user.getAddress() != null
                && addressId.equals(user.getAddress().getId());
    }

}

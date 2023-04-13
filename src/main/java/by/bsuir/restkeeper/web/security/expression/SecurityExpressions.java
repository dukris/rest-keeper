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

    public boolean isUser(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.retrieveById(userId);
        return authentication.getName().equals(user.getEmail()) && user.isEnabled();
    }

    public boolean hasAdminRole(){
        return this.hasRole(User.Role.ROLE_ADMIN);
    }

    public boolean hasKitchenRole(){
        return this.hasRole(User.Role.ROLE_KITCHEN);
    }

    public boolean hasHallRole(){
        return this.hasRole(User.Role.ROLE_HALL);
    }

    private boolean hasRole(User.Role role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return authentication.getAuthorities().contains(authority);
    }

    public boolean hasAddress(Long addressId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.retrieveByEmail(authentication.getName());
        return addressId.equals(user.getAddress().getId());
    }



}

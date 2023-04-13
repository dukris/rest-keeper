package by.bsuir.restkeeper.web.controller;


import by.bsuir.restkeeper.domain.AuthEntity;
import by.bsuir.restkeeper.service.AuthenticationService;
import by.bsuir.restkeeper.web.dto.AuthEntityDto;
import by.bsuir.restkeeper.web.dto.group.OnEnable;
import by.bsuir.restkeeper.web.dto.group.OnLogin;
import by.bsuir.restkeeper.web.dto.group.OnRefresh;
import by.bsuir.restkeeper.web.dto.group.OnRegister;
import by.bsuir.restkeeper.web.dto.group.OnUpdatePassword;
import by.bsuir.restkeeper.web.dto.mapper.AuthEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restkeeper/v1/auth")
public class AuthController {

    private final AuthEntityMapper authEntityMapper;
    private final AuthenticationService authenticationService;

    @PostMapping("/register") //name, surname, password, email, passport, role
    public void register(@Validated(OnRegister.class) @RequestBody AuthEntityDto authEntityDto) {
        AuthEntity authEntity = this.authEntityMapper.toEntity(authEntityDto);
        this.authenticationService.register(authEntity);
    }

    @PostMapping("/login") //email, password
    public AuthEntityDto login(@Validated(OnLogin.class) @RequestBody AuthEntityDto authEntityDto) {
        AuthEntity authEntity = this.authEntityMapper.toEntity(authEntityDto);
        AuthEntity returnedAuthEntity = this.authenticationService.login(authEntity);
        return this.authEntityMapper.toDto(returnedAuthEntity);
    }

    @PostMapping("/refresh") //refreshToken
    public AuthEntityDto refresh(@Validated(OnRefresh.class) @RequestBody AuthEntityDto authEntityDto) {
        AuthEntity authEntity = this.authEntityMapper.toEntity(authEntityDto);
        AuthEntity returnedAuthEntity = this.authenticationService.refresh(authEntity);
        return this.authEntityMapper.toDto(returnedAuthEntity);
    }

    @GetMapping("/enable") //enableToken
    public AuthEntityDto enable(@Validated(OnEnable.class) AuthEntityDto authEntityDto) {
        AuthEntity authEntity = this.authEntityMapper.toEntity(authEntityDto);
        AuthEntity returnedAuthEntity = this.authenticationService.enable(authEntity);
        return this.authEntityMapper.toDto(returnedAuthEntity);
    }

    @PreAuthorize("@securityExpressions.isUser(#userId)") //password, new password
    @PostMapping("/users/{userId}/password/update")
    public AuthEntityDto updatePassword(@Validated(OnUpdatePassword.class) @RequestBody AuthEntityDto authEntityDto,
                                        @PathVariable Long userId) {
        AuthEntity authEntity = this.authEntityMapper.toEntity(authEntityDto);
        AuthEntity returnedAuthEntity = this.authenticationService.updatePassword(userId, authEntity);
        return this.authEntityMapper.toDto(returnedAuthEntity);
    }

}

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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
    public String login(@Validated(OnLogin.class) @RequestBody AuthEntityDto authEntityDto, HttpServletResponse response) throws JSONException {
        AuthEntity authEntity = this.authEntityMapper.toEntity(authEntityDto);
        AuthEntity returnedAuthEntity = this.authenticationService.login(authEntity);
        return get(returnedAuthEntity, response);
    }

    private String get(AuthEntity entity, HttpServletResponse response) throws JSONException {
        Cookie cookie = new Cookie("refresh", entity.getRefreshToken());
        cookie.setDomain("localhost");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(604800);

        response.addCookie(cookie);

        return new JSONObject().put("accessToken", entity.getAccessToken())
                .put("expTime", Timestamp.from(Instant.now().plusSeconds(300)).getTime())
                .toString();
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

    @PostMapping("/users/{userId}/password/update") //password, new password
    @PreAuthorize("@securityExpressions.isUser(#userId)")
    public AuthEntityDto updatePassword(@Validated(OnUpdatePassword.class) @RequestBody AuthEntityDto authEntityDto,
                                        @PathVariable Long userId) {
        AuthEntity authEntity = this.authEntityMapper.toEntity(authEntityDto);
        AuthEntity returnedAuthEntity = this.authenticationService.updatePassword(userId, authEntity);
        return this.authEntityMapper.toDto(returnedAuthEntity);
    }

}

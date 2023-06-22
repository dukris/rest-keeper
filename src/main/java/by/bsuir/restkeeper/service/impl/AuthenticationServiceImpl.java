package by.bsuir.restkeeper.service.impl;

import by.bsuir.restkeeper.domain.AuthEntity;
import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.domain.exception.InvalidPasswordException;
import by.bsuir.restkeeper.service.AuthenticationService;
import by.bsuir.restkeeper.service.MailService;
import by.bsuir.restkeeper.service.UserService;
import by.bsuir.restkeeper.service.property.JwtProperty;
import by.bsuir.restkeeper.service.property.RestkeeperProperty;
import by.bsuir.restkeeper.web.security.manager.JwtManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtManager accessJwtManager;
    private final JwtManager refreshJwtManager;
    private final JwtManager enableJwtManager;
    private final AuthenticationManager authManager;
    private final RestkeeperProperty appProperty;
    private final JwtProperty jwtProperty;

    @Override
    @Transactional
    public void register(final AuthEntity authEntity) {
        User user = new User();
        user.setName(authEntity.getName());
        user.setSurname(authEntity.getSurname());
        user.setEmail(authEntity.getEmail());
        user.setPassword(
                this.passwordEncoder.encode(authEntity.getPassword())
        );
        user.setRole(authEntity.getRole());
        user.setPassport(authEntity.getPassport());
        user.setEnabled(false);
        user = this.userService.create(user);
        String enableJwt = this.enableJwtManager.generateToken(user);
        String subject = "Enable profile";
        String link = this.appProperty.getEnable() + enableJwt;
        this.mailService.send(
                user,
                "registerUser.ftl",
                subject,
                " ",
                link
        );
    }

    @Override
    @Transactional(readOnly = true)
    public AuthEntity login(final AuthEntity authEntity) {
        this.authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authEntity.getEmail(),
                        authEntity.getPassword()
                )
        );
        User user = this.userService.retrieveByEmail(authEntity.getEmail());
        String accessJwt = this.accessJwtManager.generateToken(user);
        String refreshJwt = this.refreshJwtManager.generateToken(user);
        return AuthEntity.builder()
                .accessToken(accessJwt)
                .refreshToken(refreshJwt)
                .accessExpTim(this.jwtProperty.getAccessExpirationTime())
                .refreshExpTime(this.jwtProperty.getRefreshExpirationTime())
                .userId(user.getId())
                .roleName(user.getRole().name())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public AuthEntity refresh(final AuthEntity authEntity) {
        String email = this.refreshJwtManager.extractClaim(
                authEntity.getRefreshToken(),
                Claims::getSubject
        );
        User user = this.userService.retrieveByEmail(email);
        String accessJwt = this.accessJwtManager.generateToken(user);
        String refreshJwt = this.refreshJwtManager.generateToken(user);
        return AuthEntity.builder()
                .accessToken(accessJwt)
                .refreshToken(refreshJwt)
                .build();
    }

    @Override
    @Transactional
    public AuthEntity enable(final String enableToken) {
        String email = this.enableJwtManager.extractClaim(
                enableToken,
                Claims::getSubject
        );
        User user = this.userService.enable(email);
        String accessJwt = this.accessJwtManager.generateToken(user);
        String refreshJwt = this.refreshJwtManager.generateToken(user);
        return AuthEntity.builder()
                .name(user.getName())
                .email(email)
                .accessToken(accessJwt)
                .refreshToken(refreshJwt)
                .build();
    }

    @Override
    @Transactional
    public AuthEntity updatePassword(
            final Long userId,
            final AuthEntity authEntity
    ) {
        User user = this.userService.retrieveById(userId);
        if (!BCrypt.checkpw(authEntity.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password!");
        }
        user = this.userService.updatePassword(user,
                this.passwordEncoder.encode(authEntity.getNewPassword()));
        String accessJwt = this.accessJwtManager.generateToken(user);
        String refreshJwt = this.refreshJwtManager.generateToken(user);
        return AuthEntity.builder()
                .accessToken(accessJwt)
                .refreshToken(refreshJwt)
                .build();
    }

}

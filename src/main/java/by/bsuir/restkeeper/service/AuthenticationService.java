package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.AuthEntity;

public interface AuthenticationService {

    void register(AuthEntity authEntity);

    AuthEntity login(AuthEntity authEntity);

    AuthEntity refresh(AuthEntity authEntity);

    AuthEntity enable(AuthEntity authEntity);

    AuthEntity updatePassword(Long userId, AuthEntity authEntity);

}

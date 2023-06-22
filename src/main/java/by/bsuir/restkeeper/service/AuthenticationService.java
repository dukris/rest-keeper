package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.AuthEntity;

public interface AuthenticationService {

    /**
     * Register new user.
     *
     * @param authEntity AuthEntity
     */
    void register(AuthEntity authEntity);

    /**
     * Login.
     *
     * @param authEntity AuthEntity
     * @return AuthEntity
     */
    AuthEntity login(AuthEntity authEntity);

    /**
     * Refresh tokens.
     *
     * @param authEntity AuthEntity
     * @return AuthEntity
     */
    AuthEntity refresh(AuthEntity authEntity);

    /**
     * Enable user.
     *
     * @param enableToken Token
     * @return AuthEntity
     */
    AuthEntity enable(String enableToken);

    /**
     * Update password.
     *
     * @param userId User's id
     * @param authEntity AuthEntity
     * @return AuthEntity
     */
    AuthEntity updatePassword(Long userId, AuthEntity authEntity);

}

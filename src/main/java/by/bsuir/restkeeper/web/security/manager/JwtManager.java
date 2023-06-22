package by.bsuir.restkeeper.web.security.manager;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtManager {

    /**
     * Generate token.
     *
     * @param userDetails UserDetails
     * @return Token
     */
    String generateToken(UserDetails userDetails);

    /**
     * Token validation.
     *
     * @param token Token
     * @return Boolean
     */
    boolean isValidToken(String token);

    /**
     * Extract claims from token.
     *
     * @param token Token
     * @param claimsResolver Resolver
     * @return Claims
     * @param <T> Type
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

}

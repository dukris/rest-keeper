package by.bsuir.restkeeper.web.security.manager;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtManager {

    String generateToken(UserDetails userDetails);

    boolean isValidToken(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

}

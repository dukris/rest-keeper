package by.bsuir.restkeeper.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthEntity {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String newPassword;
    private String accessToken;
    private String refreshToken;
    private String passwordRefreshToken;

}

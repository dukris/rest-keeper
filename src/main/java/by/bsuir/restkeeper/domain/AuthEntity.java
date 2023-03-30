package by.bsuir.restkeeper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthEntity {

    private String password;
    private String newPassword;
    private String accessToken;
    private String refreshToken;
    private String passwordRefreshToken;

}

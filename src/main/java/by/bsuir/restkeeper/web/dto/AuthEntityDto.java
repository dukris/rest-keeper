package by.bsuir.restkeeper.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AuthEntityDto(

        @NotBlank(message = "Password can't be blank!")
        @Length(min = 8, max = 20, message = "Password must include minimum {min} and maximum {max} characters!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,

        @NotBlank(message = "New password can't be blank!")
        @Length(min = 8, max = 20, message = "New password must include minimum {min} and maximum {max} characters!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String newPassword,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String accessToken,

        @NotBlank(message = "Refresh token can't be blank!")
        String refreshToken,

        @NotBlank(message = "Password refresh token can't be blank!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String passwordRefreshToken

) {
}

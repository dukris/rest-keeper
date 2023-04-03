package by.bsuir.restkeeper.web.dto;

import by.bsuir.restkeeper.web.dto.group.OnEnable;
import by.bsuir.restkeeper.web.dto.group.OnLogin;
import by.bsuir.restkeeper.web.dto.group.OnPasswordRefresh;
import by.bsuir.restkeeper.web.dto.group.OnRefresh;
import by.bsuir.restkeeper.web.dto.group.OnRegister;
import by.bsuir.restkeeper.web.dto.group.OnRequestPasswordRefresh;
import by.bsuir.restkeeper.web.dto.group.OnUpdatePassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AuthEntityDto(

        @NotBlank(groups = {OnRegister.class}, message = "Name can't be blank!")
        @Length(min = 2, max = 50, groups = {OnRegister.class}, message = "Name must include minimum {min} and maximum {max} characters!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String name,

        @NotBlank(groups = {OnRegister.class}, message = "Surname can't be blank!")
        @Length(min = 2, max = 50, groups = {OnRegister.class}, message = "Surname must include minimum {min} and maximum {max} characters!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String surname,

        @NotBlank(groups = {OnRegister.class, OnLogin.class, OnRequestPasswordRefresh.class, OnPasswordRefresh.class}, message = "Email can't be blank!")
        @Email(groups = {OnRegister.class, OnLogin.class, OnRequestPasswordRefresh.class, OnPasswordRefresh.class}, message = "Not an email!")
        @Length(min = 6, max = 50, groups = {OnRegister.class, OnLogin.class, OnRequestPasswordRefresh.class, OnPasswordRefresh.class}, message = "Email must include minimum {min} and maximum {max} characters!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String email,

        @NotBlank(groups = {OnRegister.class, OnLogin.class, OnPasswordRefresh.class, OnUpdatePassword.class}, message = "Password can't be blank!")
        @Length(min = 8, max = 20, groups = {OnRegister.class, OnLogin.class, OnPasswordRefresh.class, OnUpdatePassword.class}, message = "Password must include minimum {min} and maximum {max} characters!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,

        @NotBlank(groups = {OnUpdatePassword.class}, message = "New password can't be blank!")
        @Length(min = 8, max = 20, groups = {OnUpdatePassword.class}, message = "New password must include minimum {min} and maximum {max} characters!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String newPassword,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String accessToken,

        @NotBlank(groups = {OnRefresh.class}, message = "Refresh token can't be blank!")
        String refreshToken,

        @NotBlank(groups = {OnEnable.class}, message = "Enable token can't be blank!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String enableToken,

        @NotBlank(groups = {OnPasswordRefresh.class}, message = "Password refresh token can't be blank!")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String passwordRefreshToken

) {
}

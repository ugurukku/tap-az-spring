package com.ugurukku.tapazspring.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CreateUserRequest(
        @Email(message = "Email daxil edin") String email,
        @Size(min = 8, message = "Şifrə uzunluğu minimum 8 olmalıdır!") String password,
        @NotBlank(message = "İstifadəçi adı boş olmamalıdır") String username) {

}

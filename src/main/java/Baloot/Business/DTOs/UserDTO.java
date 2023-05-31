package Baloot.Business.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
public class UserDTO {
    @NonNull

    public String username;
    @NonNull
    public String password;

    @NonNull
    @Email
    public String email;
    @NonNull
    public String birthDate;
    @NonNull
    public String address;
    public Integer credit;
}

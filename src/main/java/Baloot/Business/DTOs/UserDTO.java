package Baloot.Business.DTOs;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDTO {
    @NonNull

    public String username;
    @NonNull
    public String password;


    @Email
    public String email;
    public String birthDate;
    public String address;
    public Integer credit;
}

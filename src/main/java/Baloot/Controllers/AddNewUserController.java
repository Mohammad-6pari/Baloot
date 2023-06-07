package Baloot.Controllers;

import Baloot.Business.DTOs.UserDTO;
import Baloot.Data.Services.ContextLoader;

import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class AddNewUserController {
    @PostMapping("/register")
    public ResponseEntity<?> postLogin(@Valid @RequestBody UserDTO user) {
        var contextManager = ContextLoader.getContextManager();

        var res = contextManager.addNewUser(user);
        if (res == null) {
            return new ResponseEntity<String>("register failed",HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }
}

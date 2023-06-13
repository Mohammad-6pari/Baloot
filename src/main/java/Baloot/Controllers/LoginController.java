package Baloot.Controllers;

import Baloot.Business.DTOs.UserDTO;
import Baloot.Data.Services.ContextLoader;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {
    @GetMapping("/login")
    public ResponseEntity<?> getLogin() {
        var contextManager = ContextLoader.getContextManager();
        if (contextManager.isUserAuthenticated())
            return new ResponseEntity<String>(HttpStatus.OK);
        else
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginController(@RequestBody UserDTO userDto) {
        var contextManager = ContextLoader.getContextManager();
        var res = contextManager.loginUser(userDto.username, userDto.password);
        if (res.equals(403))
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        else if (res.equals(401))
            return new ResponseEntity<String>("username or password is wrong",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<String>(HttpStatus.OK);
    }
}


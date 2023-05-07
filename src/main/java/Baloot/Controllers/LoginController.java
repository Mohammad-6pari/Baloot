package Baloot.Controllers;

import Baloot.Data.Entity.Commodity;
import Baloot.Data.Entity.User;
import Baloot.Data.Services.ContextLoader;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {
    @GetMapping("/login")
    public ResponseEntity<?> getLogin() {
        var contextManager = ContextLoader.getContextManager();

        if (contextManager.isUserAuthenticated())
            return new ResponseEntity<String>("not authenticated",HttpStatus.UNAUTHORIZED);
        else {
            return new ResponseEntity<String>("what the hell should be shown?",HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@RequestParam(required = false) Map<String, String> req) {
        var contextManager = ContextLoader.getContextManager();

        var username = req.get("username");
        var password = req.get("password");
        var res = contextManager.loginUser(username, password);
        if (res == null) {
            return new ResponseEntity<String>("login failed",HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity<String>("login successful", HttpStatus.OK);
    }
}
}

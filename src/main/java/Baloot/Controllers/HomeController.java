package Baloot.Controllers;

import Baloot.Data.Entity.Commodity;
import Baloot.Data.Entity.User;
import Baloot.Data.Services.ContextLoader;

import Baloot.Data.Services.IContextManager;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<?> getHomeController() {
        var contextManager = ContextLoader.getContextManager();

        if (!contextManager.isUserAuthenticated()) {
            return new ResponseEntity<String>("not authenticated",HttpStatus.UNAUTHORIZED);
        } else {
            JSONObject resp = new JSONObject();
            resp.put("user", contextManager.getLoggedinUser());
            return new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
        }
    }
}

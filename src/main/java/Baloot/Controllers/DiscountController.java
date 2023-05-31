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
public class DiscountController {
    @PostMapping("/addDiscount")
    public ResponseEntity<?> postDiscountController(@RequestParam(required = true) Map<String, String> req) {
        var contextManager = ContextLoader.getContextManager();
        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>("not logged in",HttpStatus.UNAUTHORIZED);
        }        else {
            contextManager.applyDiscount(user.getUsername(), req.get("code"));
            return new ResponseEntity<String>("discount added", HttpStatus.OK);
        }
    }
}

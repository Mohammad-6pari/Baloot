package Baloot.Controllers;

import Baloot.Data.Entity.Commodity;
import Baloot.Data.Entity.User;
import Baloot.Data.Services.ContextLoader;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class CreditController {
    @GetMapping("/credit")
    public ResponseEntity<?> getCreditController() {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }        else {
            JSONObject resp = new JSONObject();
            resp.put("username",user.getUsername());
            resp.put("credit",user.getCredit());
            return new ResponseEntity<String>(resp.toString(),HttpStatus.UNAUTHORIZED);

        }
    }
        @PostMapping("/credit/{credit}")
        public ResponseEntity<?> postCreditController(@PathVariable String credit) {

        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        } else {
            user.setCredit(user.getCredit() + Integer.parseInt(credit));
            JSONObject resp = new JSONObject();
            resp.put("username",user.getUsername());
            resp.put("credit",user.getCredit());
            return new ResponseEntity<String>(resp.toString(),HttpStatus.UNAUTHORIZED);
        }
}
}

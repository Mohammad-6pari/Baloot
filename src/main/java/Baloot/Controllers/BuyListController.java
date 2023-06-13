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
@CrossOrigin(origins = "http://localhost:8080")
public class BuyListController {

    @GetMapping("/buyList")
    public ResponseEntity<?> buyListController() {

        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
       if (user == null) {
           return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
       }
       else {
        JSONObject resp = new JSONObject();
        resp.put("user",user.getUsername());
        resp.put("buyList", contextManager.getBuyListByUsername(user.getUsername()));
        resp.put("totalPrice", contextManager.getBuyListTotalPrice(user.getUsername()));

        return new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
       }
    }
}

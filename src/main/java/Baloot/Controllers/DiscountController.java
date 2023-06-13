package Baloot.Controllers;

import Baloot.Business.DTOs.DiscountDTO;
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
    public ResponseEntity<?> postDiscountController(@RequestBody DiscountDTO discountDto) {
        var contextManager = ContextLoader.getContextManager();
        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }        else {
            int result = contextManager.applyDiscount(user.getUsername(),discountDto.code);
            JSONObject resp = new JSONObject();
            if (result==-1)
                resp.put("text","code not found");
            else if (result==0)
                resp.put("text","code already added for user");
            else if (result==1)
                resp.put("text","code added");
            return new ResponseEntity<String>(resp.toString(),HttpStatus.OK);
        }
    }
}

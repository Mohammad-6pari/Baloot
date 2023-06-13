package Baloot.Controllers;

import Baloot.Business.DTOs.BuyListItemDTO;
import Baloot.Business.DTOs.RateCommodityDTO;
import Baloot.Data.Entity.Commodity;
import Baloot.Data.Entity.User;
import Baloot.Data.Services.ContextLoader;

import Baloot.Data.Services.IContextManager;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class PaymentController {
    @GetMapping("/payment")
    public ResponseEntity<?> getPaymentController() {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        } else {
            int submitBuyList=contextManager.submitBuyList(user.getUsername());
            JSONObject resp = new JSONObject();
            if (submitBuyList==0)
                resp.put("text","not enough credit");
            else
                resp.put("text","successfully added");

            return new ResponseEntity<String>(resp.toString(),HttpStatus.OK);
        }
    }
}

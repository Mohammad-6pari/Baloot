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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class RateController {
    @PostMapping("/rateCommodity/{id}")
    public ResponseEntity<?> postRateController(@PathVariable String id,
                                                @RequestParam(required = false) Map<String, String> req) {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>("not logged in",HttpStatus.UNAUTHORIZED);
        }        else {
            var rate = new RateCommodityDTO();
            rate.username = user.getUsername();
            rate.commodityId = Integer.valueOf(id);
            rate.score = Integer.parseInt(req.get("rate"));
            contextManager.rateCommodity(rate);
            return new ResponseEntity<String>("rate submitted", HttpStatus.OK);
        }
    }
}

package Baloot.Controllers;

import Baloot.Data.Services.ContextLoader;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class CommodityController {
    @GetMapping("/commodities/{id}")
    public ResponseEntity<?> commodityController(@PathVariable String id) {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
        else {
            JSONObject resp = new JSONObject();
            var commodity = contextManager.getCommodity(Integer.valueOf(id));
            if (commodity == null)
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            else {
                resp.put("commodityId", commodity.getId().toString());
                resp.put("commodityName", commodity.getName().toString());
                resp.put("commodityPrice", commodity.getPrice().toString());
                resp.put("commodityCategories", commodity.getCategories().toString());
                resp.put("commodityRating", commodity.getRating());
                resp.put("commodityInStock", commodity.getInStock().toString());
                resp.put("suggestions", contextManager.getCommoditySuggestions(commodity.getCategories()));
                resp.put("comments", contextManager.getCommodityComments(commodity.getId()));
            }
            return new ResponseEntity<String>(resp.toString(),HttpStatus.OK);
        }
    }
}

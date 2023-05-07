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
            return new ResponseEntity<String>("not logged in",HttpStatus.UNAUTHORIZED);
        }
        else {
            JSONObject resp = new JSONObject();
            resp.put("username", user.getUsername());
            var commodity = contextManager.getCommodity(Integer.valueOf(id));
            if (commodity == null){
                resp.put("commodity", "");
            }
            else {
                resp.put("commodity", commodity);
                resp.put("suggestions", contextManager.getCommoditySuggestions(commodity.getCategories()));
                resp.put("comments", contextManager.getCommodityComments(commodity.getId()));
            }
            return new ResponseEntity<String>(resp.toString(),HttpStatus.OK);
        }
    }
}

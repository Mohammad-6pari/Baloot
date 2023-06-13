package Baloot.Controllers;

import Baloot.Data.Entity.Commodity;
import Baloot.Data.Services.ContextLoader;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class CommoditiesController {
    @GetMapping("/commodities")
    public ResponseEntity<?> commodities(@RequestParam Map<String, String> req) {
        var contextManager = ContextLoader.getContextManager();
        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        } else {
            var searchBy = req.get("searchBy");
            var query = req.get("q");
            var sortBy = req.get("sortBy");
            List<Commodity> commodities;
            if (query != null) {
                if (searchBy != null && searchBy.equals("name")) {
                    commodities = contextManager.getCommoditiesByName(query);
                } else if (searchBy != null && searchBy.equals("category")) {
                    commodities = contextManager.getCommoditiesByCategory(query);
                } else commodities = contextManager.getCommoditiesList();
            } else commodities = contextManager.getCommoditiesList();
            if (sortBy != null && sortBy.equals("rate")) {
                commodities = commodities.stream()
                        .sorted((c1, c2) -> Float.compare(c1.getRating(), c2.getRating())).collect(Collectors.toList());
            }
            JSONObject resp = new JSONObject();
            resp.put("commodities", commodities);
            return new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
        }
    }
}
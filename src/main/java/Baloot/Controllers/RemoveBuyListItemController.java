package Baloot.Controllers;

import Baloot.Business.DTOs.BuyListItemDTO;
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
public class RemoveBuyListItemController {
    @PostMapping("/removeFromBuyList/{id}")
    public ResponseEntity<?> postRemoveBuyListItemController(@PathVariable String id) {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>("not logged in",HttpStatus.UNAUTHORIZED);
        }        else {
            var buyListItem = new BuyListItemDTO();
            buyListItem.username = user.getUsername();
            buyListItem.commodityId = Integer.valueOf(id);
            contextManager.removeBuyListItem(buyListItem);
            return new ResponseEntity<String>("removed", HttpStatus.OK);
        }
    }
}

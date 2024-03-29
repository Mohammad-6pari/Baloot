package Baloot.Controllers;

import Baloot.Business.DTOs.BuyListItemDTO;
import Baloot.Data.Entity.Commodity;
import Baloot.Data.Entity.User;
import Baloot.Data.Services.ContextLoader;

import Baloot.Data.Services.IContextManager;
import jakarta.validation.Valid;
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
    @PostMapping("/removeFromBuyList")
    public ResponseEntity<?> postRemoveBuyListItemController(@Valid @RequestBody BuyListItemDTO buyListItem) {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        } else {
            var removeResp=contextManager.removeBuyListItem(buyListItem);
            JSONObject resp = new JSONObject();
            if (removeResp==null){
                resp.put("text","not removed");
            }else resp.put("text","removed");

            return new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
        }
    }
}

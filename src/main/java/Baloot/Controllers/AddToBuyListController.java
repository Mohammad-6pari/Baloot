package Baloot.Controllers;

import Baloot.Business.DTOs.BuyListItemDTO;
import Baloot.Data.Services.ContextLoader;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class AddToBuyListController {
    @PostMapping("/addToBuyList/{commodityId}")
    public ResponseEntity<?> addToBuyList(@PathVariable String commodityId,
                                          @Valid @RequestBody BuyListItemDTO buyListItem) {
        var contextManager = ContextLoader.getContextManager();
        var user = contextManager.getLoggedinUser();
        if (user == null){
            return new ResponseEntity<String>("Not logged in",HttpStatus.UNAUTHORIZED);
        }
        else {
        contextManager.addToBuyList(buyListItem);
        return new ResponseEntity<String>("item added to buylist", HttpStatus.CREATED);
        }
    }
}

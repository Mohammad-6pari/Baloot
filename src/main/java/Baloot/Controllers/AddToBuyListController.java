package Baloot.Controllers;

import Baloot.Business.DTOs.BuyListItemDTO;
import Baloot.Data.Services.ContextLoader;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class AddToBuyListController {
    @PostMapping("/addToBuyList")
    public ResponseEntity<?> addToBuyList(@Valid @RequestBody BuyListItemDTO buyListItem) {
        var contextManager = ContextLoader.getContextManager();
        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>("Not logged in", HttpStatus.UNAUTHORIZED);
        } else {
            var addResp = contextManager.addToBuyList(buyListItem);
            if (addResp.equals(-1))
                return new ResponseEntity<String>("item already exists on buylist", HttpStatus.OK);
            else if (addResp.equals(0))
                return new ResponseEntity<String>("item not available", HttpStatus.OK);
            else
                return new ResponseEntity<String>("item added ", HttpStatus.OK);

        }
}
}

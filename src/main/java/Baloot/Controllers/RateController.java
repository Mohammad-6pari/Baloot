package Baloot.Controllers;

import Baloot.Business.DTOs.RateCommodityDTO;
import Baloot.Data.Services.ContextLoader;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class RateController {
    @PostMapping("/rateCommodity")
    public ResponseEntity<?> postRateController(@RequestBody RateCommodityDTO rateCommodityDTO) {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }        else {
            contextManager.rateCommodity(rateCommodityDTO);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }
}

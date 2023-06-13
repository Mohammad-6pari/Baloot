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
public class LogoutController {
    @PostMapping("/logout")
    public ResponseEntity<?> getLogoutController() {
        var contextManager = ContextLoader.getContextManager();
        int res=contextManager.logoutUser();
        if(res==200)
            return new ResponseEntity<String>(HttpStatus.OK);
        else
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

    }
}

package Baloot.Controllers;

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
public class VoteController {
    @PostMapping("/voteComment/{id}")
    public ResponseEntity<?> postVoteController(@PathVariable String id,
                                                @RequestParam(required = true) Map<String, String> req) {

        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>("not logged in",HttpStatus.UNAUTHORIZED);
        }        else {
            var commentId = Integer.valueOf(id);;
            var comment = contextManager.voteComment(user.getUsername(), commentId, Integer.parseInt(req.get("vote")));
            return new ResponseEntity<String>("vote added", HttpStatus.OK);
        }
    }
}

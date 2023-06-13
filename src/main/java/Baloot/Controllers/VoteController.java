package Baloot.Controllers;

import Baloot.Data.Services.ContextLoader;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

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
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }        else {
            var commentId = Integer.valueOf(id);;
            var comment = contextManager.voteComment(user.getUsername(), commentId, Integer.parseInt(req.get("vote")));
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }
}

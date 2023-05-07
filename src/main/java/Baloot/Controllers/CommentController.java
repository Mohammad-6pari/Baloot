package Baloot.Controllers;

import Baloot.Data.Services.ContextLoader;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class CommentController {
    @PostMapping("/addComment/{id}")
    public ResponseEntity<?> commentController(@PathVariable String id,
                                               @RequestParam(required = false) Map<String, String> req) {
        var contextManager = ContextLoader.getContextManager();
        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>("not logged in",HttpStatus.UNAUTHORIZED);
        }else {
            JSONObject resp = new JSONObject();
            resp.put("username",user.getUsername());
            Integer commodityId = Integer.valueOf(id);
            contextManager.addComment(user.getEmail(), commodityId, req.get("comment"));
            return new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
        }
    }
}

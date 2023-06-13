package Baloot.Controllers;

import Baloot.Business.DTOs.CommentDTO;
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
    @PostMapping("/addComment")
    public ResponseEntity<?> commentController(@RequestBody CommentDTO commentDto) {
        var contextManager = ContextLoader.getContextManager();
        var user = contextManager.getLoggedinUser();
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }else {
            var comment = contextManager.addComment(user.getEmail(), commentDto.commodityId,commentDto.text);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }
}

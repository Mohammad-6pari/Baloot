package Baloot.Controllers;

import Baloot.Business.DTOs.UserDTO;
import Baloot.Data.Services.ContextLoader;
import Baloot.Data.Services.Jwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;


import javax.naming.Context;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/callback")
public class CallBackController {
    @GetMapping("")
    public ResponseEntity<String> callback(@RequestParam String code) {
        try {
            Document doc = Jsoup.connect("https://github.com/login/oauth/access_token")
                    .header("Accept", "application/json")
                    .data("client_id", "4f8921917cfb49ce6163")
                    .data("client_secret", "b1eb0f54f8cca3b45ce0819bc5bfc6642e7c3064")
                    .data("code", code)
                    .ignoreContentType(true).post();

            String token_field = "\"access_token\":\"";
            int starting_index = doc.wholeText().indexOf(token_field) + token_field.length();
            int ending_index = doc.wholeText().indexOf("\"", starting_index + 1);
            String access_token = doc.wholeText().substring(starting_index, ending_index);
            doc = Jsoup.connect("https://api.github.com/user")
                    .header("Authorization", "token " + access_token)
                    .ignoreContentType(true).get();

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(doc.wholeText(), new TypeReference<>() {
            });
            String username = map.get("login").toString();
            //String email = map.get("email").toString();
            String created_at = map.get("created_at").toString();
            Date birthDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(created_at);

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.YEAR, -18);
                birthDate = cal.getTime();
            } catch (ParseException ignored) {}

            var contentManager = ContextLoader.getContextManager();
            var user = new UserDTO(username);
            // user.email = email;
            // user.birthDate = birthDate.toString();
            contentManager.addNewUser(user);
            String jwt = Jwt.generateToken(username);
            return new ResponseEntity<String>(jwt, HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

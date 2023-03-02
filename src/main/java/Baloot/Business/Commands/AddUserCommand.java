package Baloot.Business.Commands;

import Baloot.Business.DTOs.UserDTO;
import Baloot.Data.Entity.User;
import Baloot.Data.Services.IContextManager;
import Baloot.Business.Command;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONObject;

public class AddUserCommand extends Command {

    private UserDTO data;
    public AddUserCommand(UserDTO data){
        this.data = data;
    }


    private boolean checkUsernameFormat(String username){
        return username.matches("[a-zA-Z0-9._]+");
    }

    @Override
    public Response execute(IContextManager contextManager) {
        boolean checkUsernameFormat = checkUsernameFormat(this.data.username);
        if(!checkUsernameFormat){
            JSONObject json = new JSONObject();
            json.put("errorMessage", "Invalid username");
            return new Response(ResponseStatus.FAILURE, json);
        }
        User tempUser = contextManager.getUser(this.data.username);
        User result = null;
        if (tempUser == null) {
             result = contextManager.addNewUser(this.data);
        }
        else {
            result = contextManager.updateUser(this.data);
        }
        return new Response(ResponseStatus.SUCCESS, result.toJson());
    }
}

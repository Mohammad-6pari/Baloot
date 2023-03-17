package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONObject;

public class GetUserByUsername extends Command {
    final String username;

    public GetUserByUsername(String username) {
        this.username = username;
    }
    @Override
    public Response execute(IContextManager contextManager) {
        var user = contextManager.getUser(username);
        if (user == null) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "username does not exist");
            return new Response(ResponseStatus.FAILURE, json);
        }
        return new Response(ResponseStatus.SUCCESS, user.toJson());
    }
}

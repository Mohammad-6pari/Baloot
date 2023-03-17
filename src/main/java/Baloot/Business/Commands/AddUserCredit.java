package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONObject;

public class AddUserCredit extends Command {
    private final String username;
    public final Integer amount;

    public AddUserCredit(String username, Integer amount) {
        this.username = username;
        this.amount = amount;
    }

    @Override
    public Response execute(IContextManager contextManager) {
        var user = contextManager.getUser(username);
        if (user == null) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "User not found");
        }
        if (amount <= 0) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "Invalid amount");
        }
        return new Response(ResponseStatus.SUCCESS, contextManager.AddUserCredit(username, amount).toJson());
    }
}

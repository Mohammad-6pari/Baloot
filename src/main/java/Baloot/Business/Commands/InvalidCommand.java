package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONObject;

public class InvalidCommand extends Command {
    @Override
    public Response execute(IContextManager contextManager) {
        JSONObject json = new JSONObject();
        json.put("errorMessage", "Invalid command");
        return new Response(ResponseStatus.FAILURE, json);
    }
}

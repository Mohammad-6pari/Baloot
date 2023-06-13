package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONObject;

public class GetProviderById extends Command {
    private Integer id;

    public GetProviderById(Integer id) {
        this.id = id;
    }
    @Override
    public Response execute(IContextManager contextManager) {
        var provider = contextManager.getProvider(id);
        if (provider == null) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "Provider not found");
            return new Response(ResponseStatus.FAILURE, json);
        }
        return new Response(ResponseStatus.SUCCESS, provider.toJson());
    }
}

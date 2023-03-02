package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetCommoditiesListCommand extends Command {
    @Override
    public Response execute(IContextManager contextManager) {
        JSONObject data = new JSONObject();
        data.put("commoditiesList",new JSONArray(contextManager.getCommoditiesList()));
        return new Response(ResponseStatus.SUCCESS,data);
    }
}

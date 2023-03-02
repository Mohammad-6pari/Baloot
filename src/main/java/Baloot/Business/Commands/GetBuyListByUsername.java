package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Data.Entity.Commodity;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class GetBuyListByUsername extends Command {
    final String data;
    public GetBuyListByUsername(String username) {
        data = username;
    }
    @Override
    public Response execute(IContextManager contextManager) {
        List<Commodity> items = contextManager.getBuyListByUsername(data);
        JSONObject json = new JSONObject();
        json.put("buyList", new JSONArray(items));

        return new Response(ResponseStatus.SUCCESS, json);
    }
}

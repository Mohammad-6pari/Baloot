package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Data.Entity.Commodity;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetCommodityById extends Command {

    private final Integer data;
    public GetCommodityById(Integer id){
        this.data=id;
    }

    @Override
    public Response execute(IContextManager contextManager) {
        Commodity commodity = contextManager.getCommodity(this.data);
        if (commodity==null){
            JSONObject json = new JSONObject();
            json.put("errorMessage", "commodity not found");
            return new Response(ResponseStatus.FAILURE, json);
        }

        JSONObject json = new JSONObject();
        json.put("id", commodity.getId());
        json.put("name", commodity.getName());
        json.put("provider", contextManager.getProvider(commodity.getProviderId()).getName());
        json.put("price", commodity.getPrice());
        json.put("categories", new JSONArray(commodity.getCategories()));
        json.put("rating", commodity.getRating());
        return new Response(ResponseStatus.SUCCESS, json);
    }
}

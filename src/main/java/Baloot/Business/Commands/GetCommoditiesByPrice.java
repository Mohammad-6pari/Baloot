package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetCommoditiesByPrice extends Command {
    private final Integer startPrice;
    private final Integer endPrice;

    public GetCommoditiesByPrice(Integer startPrice, Integer endPrice) {
        this.startPrice = startPrice;
        this.endPrice = endPrice;
    }
    @Override
    public Response execute(IContextManager contextManager) {
        JSONObject data = new JSONObject();
        data.put("commoditiesList",new JSONArray(contextManager.getCommoditiesByPrice(startPrice, endPrice)));
        return new Response(ResponseStatus.SUCCESS,data);
    }
}

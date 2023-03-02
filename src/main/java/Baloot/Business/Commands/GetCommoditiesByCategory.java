package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Business.DTOs.CommodityResultDTO;
import Baloot.Data.Entity.Commodity;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class GetCommoditiesByCategory extends Command {
    final String data;
    public GetCommoditiesByCategory(String category) {
        this.data = category;
    }
    @Override
    public Response execute(IContextManager contextManager) {
        List<Commodity> commodities = contextManager.getCommoditiesByCategory(data);
        List<CommodityResultDTO> items = commodities.stream().map(c -> new CommodityResultDTO(c)).toList();
        JSONObject json = new JSONObject();
        json.put("commoditiesListByCategory", new JSONArray(items));
        return new Response(ResponseStatus.SUCCESS, json);
    }
}

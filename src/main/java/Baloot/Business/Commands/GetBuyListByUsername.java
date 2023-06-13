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
import java.util.stream.Collectors;

public class GetBuyListByUsername extends Command {
    final String data;
    public GetBuyListByUsername(String username) {
        this.data = username;
    }
    @Override
    public Response execute(IContextManager contextManager) {
        List<Commodity> items = contextManager.getBuyListByUsername(this.data);

        List<CommodityResultDTO> resultItems = items.stream().map(item -> new CommodityResultDTO(item)).collect(Collectors.toList());
        JSONObject json = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        for(CommodityResultDTO item:resultItems){
            jsonArr.put(item.toJson());
        }
        json.put("buyList",jsonArr);
        return new Response(ResponseStatus.SUCCESS, json);
    }
}

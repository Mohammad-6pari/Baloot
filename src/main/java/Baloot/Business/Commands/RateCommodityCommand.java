package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Business.DTOs.RateCommodityDTO;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONObject;

public class RateCommodityCommand extends Command {
    private RateCommodityDTO data;
    final Integer MAX_RATE = 10;
    final Integer MIN_RATE = 1;

    public RateCommodityCommand(RateCommodityDTO data) {
        this.data = data;
    }

    @Override
    public Response execute(IContextManager contextManager) {
        if (contextManager.getCommodity(this.data.commodityId) == null || contextManager.getUser(this.data.username) == null) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "Invalid commodity or user");
            return new Response(ResponseStatus.FAILURE, json);
        }
        if (data.score > MAX_RATE || data.score < MIN_RATE) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "Score is not in a valid rage");
            return new Response(ResponseStatus.FAILURE, json);
        }
        return new Response(ResponseStatus.SUCCESS, contextManager.rateCommodity(this.data).toJson());
    }
}

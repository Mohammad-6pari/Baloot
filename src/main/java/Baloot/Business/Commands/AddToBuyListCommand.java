package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Business.DTOs.BuyListItemDTO;
import Baloot.Data.Entity.Commodity;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONObject;

public class AddToBuyListCommand extends Command {
    private BuyListItemDTO data;

    public AddToBuyListCommand(BuyListItemDTO data) {
        this.data = data;
    }
    @Override
    public Response execute(IContextManager contextManager) {
        Commodity commodity = contextManager.getCommodity(this.data.commodityId);
        if (contextManager.getUser(this.data.username) == null) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "user does not exist");
            return new Response(ResponseStatus.FAILURE, json);
        }
        if (commodity == null || commodity.getInStock() == 0) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "Commodity does not exist");
            return new Response(ResponseStatus.FAILURE, json);
        }
        return new Response(ResponseStatus.SUCCESS, contextManager.addToBuyList(this.data).toJson());
    }
}

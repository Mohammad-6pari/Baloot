package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Business.DTOs.BuyListItemDTO;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONObject;

public class RemoveBuyListItemCommand extends Command {
    private BuyListItemDTO data;
    public RemoveBuyListItemCommand(BuyListItemDTO data) {
        this.data = data;
    }
    @Override
    public Response execute(IContextManager contextManager) {
        if (contextManager.getUser(data.username) == null || contextManager.getCommodity(data.commodityId) == null) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "Invalid user or commodity");
            return new Response(ResponseStatus.FAILURE, json);
        }
        if (contextManager.getBuyListItem(data.username, data.commodityId) == null) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "Buy list item does not exist");
            return new Response(ResponseStatus.FAILURE, json);
        }
        return new Response(ResponseStatus.SUCCESS, contextManager.removeBuyListItem(data).toJson());
    }
}

package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Business.DTOs.CommodityDTO;
import Baloot.Data.Entity.Provider;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONObject;

public class AddCommodityCommand extends Command {

    private final CommodityDTO data;
    public AddCommodityCommand(CommodityDTO data) {
        this.data = data;
    }
    @Override
    public Response execute(IContextManager contextManager) {
        final Provider provider = contextManager.getProvider(this.data.providerId);
        if (provider == null) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "Invalid providerId");
            return new Response(ResponseStatus.FAILURE, json);
        } else {
            return new Response(ResponseStatus.SUCCESS, contextManager.addCommodity(this.data).toJson());
        }
    }
}

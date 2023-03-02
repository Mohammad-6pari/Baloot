package Baloot.Business.Commands;
import Baloot.Business.DTOs.ProviderDTO;
import Baloot.Data.Services.IContextManager;
import Baloot.Business.Command;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;

public class AddProviderCommand extends Command {

    private ProviderDTO data;
    public AddProviderCommand(ProviderDTO data){
        this.data = data;
    }

    @Override
    public Response execute(IContextManager contextManager) {
        return new Response(ResponseStatus.SUCCESS, contextManager.addProvider(this.data).toJson());
    }
}

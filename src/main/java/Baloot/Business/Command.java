package Baloot.Business;

import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;

public abstract class Command {

    abstract public Response execute(IContextManager contextManager);
}

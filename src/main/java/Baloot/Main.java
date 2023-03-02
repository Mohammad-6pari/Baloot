package Baloot;

import Baloot.Business.Command;
import Baloot.Data.Services.IContextManager;
import Baloot.Data.Services.Implementations.MemoryContextManager;
import Baloot.Presentation.IView;
import Baloot.Presentation.Implementations.ConsoleView;
import Baloot.Presentation.Response;

public class Main {
    public static void main(String[] args) {
        IView consoleView = new ConsoleView();
        IContextManager contextManager = new MemoryContextManager();

        while(true) {
            Command command = consoleView.getInput();
            Response response = command.execute(contextManager);
            consoleView.output(response);
        }
    }
}
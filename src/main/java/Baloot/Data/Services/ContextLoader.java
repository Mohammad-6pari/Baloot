package Baloot.Data.Services;

import Baloot.Data.Services.Implementations.MemoryContextManager;

public class ContextLoader {
    private static IContextManager contextManager = null;

    public static IContextManager getContextManager() {
        if (contextManager == null) contextManager = new MemoryContextManager();
        return contextManager;
    }
}

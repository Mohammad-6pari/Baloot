package Baloot;

import Baloot.Business.Command;
import Baloot.Business.Commands.GetCommoditiesByCategory;
import Baloot.Business.Commands.GetCommoditiesByPrice;
import Baloot.Business.Commands.GetCommoditiesListCommand;
import Baloot.Business.Commands.GetCommodityById;
import Baloot.Data.Services.IContextManager;
import Baloot.Data.Services.Implementations.MemoryContextManager;
import Baloot.Presentation.IView;
import Baloot.Presentation.Implementations.ConsoleView;
import Baloot.Presentation.Response;
import io.javalin.Javalin;

public class Main {
    public static IContextManager contextManager = new MemoryContextManager();
    public static void main(String[] args) {
        IContextManager contextManager = new MemoryContextManager();

        var app = Javalin.create().start(7070);

        app.get("/commodities", ctx -> ctx.html(processCommand(new GetCommoditiesListCommand())));

        app.get("/commodities/{commodity_id}", ctx ->
                ctx.html(processCommand(new GetCommodityById(Integer.parseInt(ctx.pathParam("commodity_id")))))
        );

        app.get("/provider/{provider_id}", ctx -> ctx.result());

        app.get("/users/{user_id}", ctx -> ctx.result("A user with Id: " + ctx.pathParam("user_id")));

        app.get("/addCredit/{user_id}/{credit}", ctx -> ctx.result("Add " + ctx.pathParam("credit") + " credit to user with Id: " + ctx.pathParam("user_id")));

        app.get("/addToBuyList/{username}/{commodity_id}", ctx -> ctx.result("Add " + ctx.pathParam("commodity_id") + " to buy list of user: " + ctx.pathParam("username")));

        app.get("/removeFromBuyList/{username}/{commodity_id}", ctx -> ctx.result("remove " + ctx.pathParam("commodity_id") + " from buy list of user: " + ctx.pathParam("username")));

        app.get("/rateCommodity/{username}/{commodity_id}/{rate}", ctx -> ctx.result("User " + ctx.pathParam("username") + "rated " + ctx.pathParam("commodity_id") + " " + ctx.pathParam("rate")));

        app.get("/voteComment/{username}/{comment_id}/{vote}", ctx -> ctx.result("User" + ctx.pathParam("username") + "voted " + ctx.pathParam("comment_id") + " " + ctx.pathParam("vote")));

        app.get("/commodities/search/{start_price}/{end_price}", ctx ->
                ctx.html(processCommand(new GetCommoditiesByPrice(Integer.parseInt(ctx.pathParam("start_price")), Integer.parseInt(ctx.pathParam("end_price")))))
        );

        app.get("/commodities/search/{category}", ctx -> ctx.html(processCommand(new GetCommoditiesByCategory(ctx.pathParam("category")))));
    }

    public static String processCommand(Command command) {
        return command.execute(contextManager).getData().toString();
    }
}
package Baloot;

import Baloot.Business.Command;
import Baloot.Business.Commands.*;
import Baloot.Business.DTOs.BuyListItemDTO;
import Baloot.Business.DTOs.RateCommodityDTO;
import Baloot.Data.Services.IContextManager;
import Baloot.Data.Services.Implementations.MemoryContextManager;
import Baloot.Presentation.IView;
import Baloot.Presentation.Implementations.ConsoleView;
import Baloot.Presentation.Response;
import io.javalin.Javalin;

public class Main {
    public static IContextManager contextManager = new MemoryContextManager();
    public static void main(String[] args) {
        var app = Javalin.create().start(7070);

        app.get("/commodities", ctx -> ctx.html(processCommand(new GetCommoditiesListCommand())));

        app.get("/commodities/{commodity_id}", ctx ->
                ctx.html(processCommand(new GetCommodityById(Integer.parseInt(ctx.pathParam("commodity_id")))))
        );

        app.get("/providers/{provider_id}", ctx -> ctx.html(processCommand(new GetProviderById(Integer.parseInt(ctx.pathParam("provider_id"))))));

        app.get("/users/{user_id}", ctx -> ctx.html(processCommand(new GetUserByUsername(ctx.pathParam("user_id")))));

        app.get("/addCredit/{user_id}/{credit}", ctx ->
            ctx.html(processCommand(new AddUserCredit(ctx.pathParam("user_id"), Integer.parseInt(ctx.pathParam("credit")))))
        );

        app.get("/addToBuyList/{username}/{commodity_id}", ctx -> {
            var item = new BuyListItemDTO();
            item.commodityId = Integer.parseInt(ctx.pathParam("commodity_id"));
            item.username = ctx.pathParam("username");
            ctx.html(processCommand(new AddToBuyListCommand(item)));
        });

        app.get("/removeFromBuyList/{username}/{commodity_id}", ctx -> {
            var item = new BuyListItemDTO();
            item.commodityId = Integer.parseInt(ctx.pathParam("commodity_id"));
            item.username = ctx.pathParam("username");
            ctx.html(processCommand(new RemoveBuyListItemCommand(item)));
        });

        app.get("/rateCommodity/{username}/{commodity_id}/{rate}", ctx -> {
            var rateItem = new RateCommodityDTO();
            rateItem.commodityId = Integer.parseInt(ctx.pathParam("commodity_id"));
            rateItem.username = ctx.pathParam("username");
            rateItem.score = Integer.parseInt(ctx.pathParam("rate"));
            ctx.html(processCommand(new RateCommodityCommand(rateItem)));
        });

        app.get("/voteComment/{username}/{comment_id}/{vote}", ctx ->
                ctx.html(processCommand(new VoteCommentCommand(
                        ctx.pathParam("username"),
                        Integer.parseInt(ctx.pathParam("comment_id")),
                        Integer.parseInt(ctx.pathParam("vote"))
                )))
        );

        app.get("/commodities/search/{start_price}/{end_price}", ctx ->
                ctx.html(processCommand(new GetCommoditiesByPrice(Integer.parseInt(ctx.pathParam("start_price")), Integer.parseInt(ctx.pathParam("end_price")))))
        );

        app.get("/commodities/search/{category}", ctx -> ctx.html(processCommand(new GetCommoditiesByCategory(ctx.pathParam("category")))));
    }

    public static String processCommand(Command command) {
        return command.execute(contextManager).getData().toString();
    }
}
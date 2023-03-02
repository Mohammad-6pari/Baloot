package Baloot.Presentation.Implementations;

import Baloot.Business.Command;
import Baloot.Business.Commands.*;
import Baloot.Business.DTOs.*;
import Baloot.Presentation.Commands;
import Baloot.Presentation.IView;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class ConsoleView implements IView {

     public ConsoleView(){

     }

    public Command getInput() {

        Scanner scanner = new Scanner(System.in);
        final String str = scanner.nextLine();

        List<String> commands = Arrays.asList(str.split(" ",2));
        String command = commands.get(0);
        JSONObject json = null;
        if(commands.size()>1){
            json = new JSONObject(commands.get(1));
        }
        switch (command) {
            case Commands.ADD_USER:
                final UserDTO userData = new UserDTO();
                userData.username = json.getString("username");
                userData.password = json.getString("password");
                userData.email = json.getString("password");
                userData.birthDate = json.getString("birthDate");
                userData.address = json.getString("address");
                userData.credit = json.getInt("credit");
                return new AddUserCommand(userData);
            case Commands.ADD_PROVIDER:
                final ProviderDTO providerData = new ProviderDTO();
                providerData.id=json.getInt("id");
                providerData.name=json.getString("name");
                providerData.registryDate=json.getString("registryDate");
                return new AddProviderCommand(providerData);
            case Commands.ADD_COMMODITY:
                final CommodityDTO commodityData = new CommodityDTO();
                commodityData.providerId = json.getInt("providerId");
                commodityData.id = json.getInt("id");
                commodityData.name = json.getString("name");

                final JSONArray categories = json.getJSONArray("categories");
                commodityData.categories =  new ArrayList<>();
                for (int i = 0; i < categories.length(); i++) {
                    commodityData.categories.add(categories.getString(i));
                }

                commodityData.inStock = json.getInt("inStock");
                commodityData.rating = json.getFloat("rating");
                commodityData.price = json.getInt("price");
                return new AddCommodityCommand(commodityData);

            case Commands.GET_COMMODITIES_LIST:
                return new GetCommoditiesListCommand();

            case Commands.RATE_COMMODITY:
                final RateCommodityDTO rateCommodityData = new RateCommodityDTO();
                rateCommodityData.commodityId = json.getInt("commodityId");
                rateCommodityData.username = json.getString("username");
                rateCommodityData.score = json.getInt("score");

                return new RateCommodityCommand(rateCommodityData);

            case Commands.ADD_TO_BUY_LIST:
                final BuyListItemDTO buyListItemData = new BuyListItemDTO();
                buyListItemData.commodityId = json.getInt("commodityId");
                buyListItemData.username = json.getString("username");

                return new AddToBuyListCommand(buyListItemData);

            case Commands.REMOVE_FROM_BUY_LIST:
                final BuyListItemDTO buyListItemDTO = new BuyListItemDTO();
                buyListItemDTO.commodityId = json.getInt("commodityId");
                buyListItemDTO.username = json.getString("username");

                return new RemoveBuyListItemCommand(buyListItemDTO);

            case Commands.GET_COMMODITY_BY_ID:
                return new GetCommodityById(json.getInt("id"));

            case Commands.GET_COMMODITIES_BY_CATEGORY:
                return new GetCommoditiesByCategory(json.getString("category"));

            case Commands.GET_BUY_LIST:
                return new GetBuyListByUsername(json.getString("username"));
            default:
                return new InvalidCommand();
        }
    }
    public void output(Response response) {
        JSONObject json = new JSONObject();
        json.put("success", response.getStatus() == ResponseStatus.SUCCESS);
        json.put("data", response.getData());
        System.out.println(json);
     }
}

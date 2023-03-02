package Baloot;

import Baloot.Business.Command;
import Baloot.Business.Commands.*;
import Baloot.Business.DTOs.*;
import Baloot.Data.Entity.BuyListItem;
import Baloot.Data.Services.IContextManager;
import Baloot.Data.Services.Implementations.MemoryContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class AddToBuyListTest {
    IContextManager contextManager;
    List<String> categories = new ArrayList<>();
    @Before
    public void setUp() {
        contextManager = new MemoryContextManager();
        for(Integer i=0;i<5;i++){
            addUser(i);
            addProvider(i);
            addCommodity(i);
        }

    }

    private void addUser(Integer ind){
        UserDTO user = new UserDTO();
        user.username="username" + ind.toString();
        user.address="add1"+ ind.toString();
        user.birthDate="1-1-1900";
        user.credit=1000+ 100*ind;
        user.email="hii@mail";
        user.password="1234"+ind.toString();
        Command addUser= new AddUserCommand(user);
        Response response = addUser.execute(contextManager);
    }
    private void addProvider(Integer val){
        ProviderDTO provider = new ProviderDTO();
        provider.name="username" + val;
        provider.id=val;
        provider.registryDate="123445";
        Command addProvider= new AddProviderCommand(provider);
        Response response = addProvider.execute(contextManager);
    }
    private void addCommodity(Integer val){
        CommodityDTO commo1 = new CommodityDTO();
        commo1.id= val;
        commo1.name="commodity"+ val.toString();
        commo1.providerId = val%3;
        commo1.price = 1000+ (val*100);
        List<String> tempCategories = new ArrayList<>();
        for (Integer i=0;i<val%2;i++){
            Integer iTemp = i%3;
            tempCategories.add("cate"+iTemp.toString());
        }
        commo1.categories=tempCategories;
        commo1.rating = val;
        commo1.inStock= val;
        Command addCommodity= new AddCommodityCommand(commo1);
        Response response = addCommodity.execute(contextManager);
    }

    @Test
    public void notExistCommodity(){
        BuyListItemDTO buyListItemDTO = new BuyListItemDTO();
        buyListItemDTO.commodityId= 20;
        buyListItemDTO.username="username1";
        Command addToBuyListCommand= new AddToBuyListCommand(buyListItemDTO);
        Response response= addToBuyListCommand.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("errorMessage","Commodity does not exist");
        assertEquals(response.getData().toString(),expectedResult.toString());
        assertEquals(response.getStatus().toString(), ResponseStatus.FAILURE.toString());

    }

    @Test
    public void notStockCommmodity(){
        BuyListItemDTO buyListItemDTO = new BuyListItemDTO();
        buyListItemDTO.commodityId= 0;
        buyListItemDTO.username="username1";
        Command addToBuyListCommand= new AddToBuyListCommand(buyListItemDTO);
        Response response= addToBuyListCommand.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("errorMessage","Commodity does not exist");
        assertEquals(response.getData().toString(),expectedResult.toString());
        assertEquals(response.getStatus().toString(), ResponseStatus.FAILURE.toString());

    }

    @Test
    public void repeatedCommodity(){
        BuyListItemDTO buyListItemDTO = new BuyListItemDTO();
        buyListItemDTO.commodityId= 2;
        buyListItemDTO.username="username2";
        Command addToBuyListCommand= new AddToBuyListCommand(buyListItemDTO);
        Response response= addToBuyListCommand.execute(contextManager);

        BuyListItemDTO buyListItemDTO1 = new BuyListItemDTO();
        buyListItemDTO1.commodityId= 2;
        buyListItemDTO1.username="username2";
        Command addToBuyListCommand1= new AddToBuyListCommand(buyListItemDTO1);
        Response response1= addToBuyListCommand1.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("errorMessage","Commodity exists on buyList");
        assertEquals(response1.getData().toString(),expectedResult.toString());
        assertEquals(response1.getStatus().toString(), ResponseStatus.FAILURE.toString());
    }
    @Test
    public void velidCommodity(){
        BuyListItemDTO buyListItemDTO = new BuyListItemDTO();
        buyListItemDTO.commodityId= 1;
        buyListItemDTO.username="username1";
        Command addToBuyListCommand= new AddToBuyListCommand(buyListItemDTO);
        Response response= addToBuyListCommand.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("providerId",1);
        expectedResult.put("price",1100);
        expectedResult.put("name","commodity1");
        expectedResult.put("rating",1);
        expectedResult.put("inStock",0);
        expectedResult.put("id",1);
        expectedResult.put("categories",new JSONArray().put("cate0"));
        assertEquals(response.getData().toString(),expectedResult.toString());
        assertEquals(response.getStatus().toString(), ResponseStatus.SUCCESS.toString());
    }

    @Test
    public void rateNotExistUser(){
        BuyListItemDTO buyListItemDTO = new BuyListItemDTO();
        buyListItemDTO.commodityId= 2;
        buyListItemDTO.username="username20";
        Command addToBuyListCommand= new AddToBuyListCommand(buyListItemDTO);
        Response response= addToBuyListCommand.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("errorMessage","user does not exist");
        assertEquals(response.getData().toString(),expectedResult.toString());
        assertEquals(response.getStatus().toString(), ResponseStatus.FAILURE.toString());
    }

    @After
    public void removeAddedData(){
        contextManager = null;
    }


}

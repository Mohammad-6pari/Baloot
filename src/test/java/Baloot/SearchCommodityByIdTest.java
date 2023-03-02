package Baloot;

import Baloot.Business.Command;
import Baloot.Business.Commands.*;
import Baloot.Business.DTOs.CommodityDTO;
import Baloot.Business.DTOs.ProviderDTO;
import Baloot.Business.DTOs.UserDTO;
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

public class SearchCommodityByIdTest {
    IContextManager contextManager;
    List<String> categories = new ArrayList<>();
    @Before
    public void setUp() {
        contextManager = new MemoryContextManager();
        for(Integer i=0;i<10;i++){
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
        provider.name="provider" + val;
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
    public void commodityIdNotValid(){
        Command rateCommodityCommand= new GetCommodityById (45);
        Response response= rateCommodityCommand.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("errorMessages","commodity not found");
        assertEquals(response.getData().toString(),expectedResult.toString());
        assertEquals(response.getStatus().toString(),ResponseStatus.FAILURE.toString());
    }

    @Test
    public void searchValidCommoditySc1(){
        Command rateCommodityCommand= new GetCommodityById (3);
        Response response= rateCommodityCommand.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("id",3);
        expectedResult.put("name","commodity3");
        expectedResult.put("provider","provider0");
        expectedResult.put("price",1300);
        expectedResult.put("categories",new JSONArray().put("cate0"));
        expectedResult.put("rating",3);

        assertEquals(response.getData().toString(),expectedResult.toString());
        assertEquals(response.getStatus().toString(),ResponseStatus.SUCCESS.toString());

    }

    @Test
    public void searchValidCommoditySc2(){
        Command rateCommodityCommand= new GetCommodityById (8);
        Response response= rateCommodityCommand.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("id",8);
        expectedResult.put("name","commodity8");
        expectedResult.put("provider","provider2");
        expectedResult.put("price",1800);
        expectedResult.put("categories",new JSONArray());
        expectedResult.put("rating",8);

        assertEquals(response.getData().toString(),expectedResult.toString());
        assertEquals(response.getStatus().toString(),ResponseStatus.SUCCESS.toString());

    }

    @After
    public void removeAddedData(){
        contextManager = null;
    }


}

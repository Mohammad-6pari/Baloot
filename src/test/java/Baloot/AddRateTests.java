package Baloot;

import Baloot.Business.Command;
import Baloot.Business.Commands.*;
import Baloot.Business.DTOs.CommodityDTO;
import Baloot.Business.DTOs.ProviderDTO;
import Baloot.Business.DTOs.RateCommodityDTO;
import Baloot.Business.DTOs.UserDTO;
import Baloot.Data.Services.IContextManager;
import Baloot.Data.Services.Implementations.MemoryContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class AddRateTests {
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
    public void rateInRangeCommodity(){
    RateCommodityDTO rateCommodityDTO = new RateCommodityDTO();
    rateCommodityDTO.score= 6;
    rateCommodityDTO.commodityId=3;
    rateCommodityDTO.username="username2";
    Command rateCommodityCommand= new RateCommodityCommand(rateCommodityDTO);
        Response response= rateCommodityCommand.execute(contextManager);

    JSONObject expectedResult = new JSONObject();
    expectedResult.put("providerId",0);
    expectedResult.put("price",1300);
    expectedResult.put("name","commodity3");
    expectedResult.put("rating",4.5);
    expectedResult.put("inStock",3);
    expectedResult.put("id",3);
    expectedResult.put("categories",new JSONArray().put("cate0"));
    assertEquals(response.getData().toString(),expectedResult.toString());
    assertEquals(response.getStatus().toString(),ResponseStatus.SUCCESS.toString());
    }

    @Test
    public void rateOutRangeCommodity(){
        RateCommodityDTO rateCommodityDTO = new RateCommodityDTO();
        rateCommodityDTO.score= 15; //>10
        rateCommodityDTO.commodityId=1;
        rateCommodityDTO.username="username1";
        Command rateCommodityCommand= new RateCommodityCommand(rateCommodityDTO);
        Response response= rateCommodityCommand.execute(contextManager);
        assertEquals(response.getStatus().toString(),ResponseStatus.FAILURE.toString());

    }

    @Test
    public void updateRateCommoditySc1(){
        RateCommodityDTO rateCommodityDTO = new RateCommodityDTO();
        rateCommodityDTO.score= 2;
        rateCommodityDTO.commodityId=3;
        rateCommodityDTO.username="username5";
        Command rateCommodityCommand= new RateCommodityCommand(rateCommodityDTO);
        rateCommodityCommand.execute(contextManager);

        RateCommodityDTO rateCommodityDTO2 = new RateCommodityDTO();
        rateCommodityDTO2.score= 8;
        rateCommodityDTO2.commodityId=3;
        rateCommodityDTO2.username="username6";
        Command rateCommodityCommand2= new RateCommodityCommand(rateCommodityDTO2);
        Response response2= rateCommodityCommand2.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("providerId",0);
        expectedResult.put("price",1300);
        expectedResult.put("name","commodity3");
        expectedResult.put("rating",5.25);
        expectedResult.put("inStock",3);
        expectedResult.put("id",3);
        expectedResult.put("categories",new JSONArray().put("cate0"));
        assertEquals(response2.getData().toString(),expectedResult.toString());
        assertEquals(response2.getStatus().toString(),ResponseStatus.SUCCESS.toString());

    }
    @Test
    public void rateNotExistCommodity(){
        RateCommodityDTO rateCommodityDTO = new RateCommodityDTO();
        rateCommodityDTO.score= 2;
        rateCommodityDTO.commodityId=50;
        rateCommodityDTO.username="username5";
        Command rateCommodityCommand= new RateCommodityCommand(rateCommodityDTO);
        rateCommodityCommand.execute(contextManager);
        Response response= rateCommodityCommand.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("errorMessage","Invalid commodity or user");
        assertEquals(response.getData().toString(),expectedResult.toString());

    }

    @Test
    public void rateNotExistUser(){
        RateCommodityDTO rateCommodityDTO = new RateCommodityDTO();
        rateCommodityDTO.score= 2;
        rateCommodityDTO.commodityId=3;
        rateCommodityDTO.username="username12";
        Command rateCommodityCommand= new RateCommodityCommand(rateCommodityDTO);
        rateCommodityCommand.execute(contextManager);
        Response response= rateCommodityCommand.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("errorMessage","Invalid commodity or user");
        assertEquals(response.getData().toString(),expectedResult.toString());

    }


}

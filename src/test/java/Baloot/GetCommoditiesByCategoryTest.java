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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class GetCommoditiesByCategoryTest {
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
        if(val==3){
        tempCategories.add("cate"+"1");

        }
        commo1.categories=tempCategories;
        commo1.rating = val;
        commo1.inStock= val;
        Command addCommodity= new AddCommodityCommand(commo1);
        Response response = addCommodity.execute(contextManager);
    }

    @Test
    public void getCommodityNull(){
        Command getCommoditiesByCategory= new GetCommoditiesByCategory ("cate9");
        Response response= getCommoditiesByCategory.execute(contextManager);

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("commoditiesListByCategory",new JSONArray());
        assertEquals(response.getData().toString(),expectedResult.toString());
        assertEquals(response.getStatus().toString(), ResponseStatus.SUCCESS.toString());
    }

    @Test
    public void getValidCommodityListSc1(){

        Command getCommoditiesByCategory= new GetCommoditiesByCategory ("cate1");
        Response response= getCommoditiesByCategory.execute(contextManager);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("id",3);
        expectedResult.put("name","commodity3");
        expectedResult.put("price",1300);
        expectedResult.put("providerId",0);
        expectedResult.put("categories",new JSONArray().put("cate1"));
        expectedResult.put("rating",3);
        expectedResult.put("inStock",3);
        JSONArray expectedArr = new JSONArray().put(expectedResult);
        assertEquals(response.getData().toString(),(new JSONObject().put("commoditiesListByCategory",expectedArr).toString()));
        assertEquals(response.getStatus().toString(), ResponseStatus.SUCCESS.toString());

    }
    @After
    public void removeAddedData(){
        contextManager = null;
    }

}

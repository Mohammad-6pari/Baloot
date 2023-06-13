package Baloot.Business.DTOs;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Baloot.Data.Entity.Commodity;

public class CommodityResultDTO {
    public CommodityResultDTO(Commodity commodity) {
        this.id = commodity.getId();
        this.name = commodity.getName();
        this.providerId = commodity.getProviderId();
        this.price = commodity.getPrice();
        this.categories = commodity.getCategories();
        this.rating = commodity.getRating();
    }
    public Integer id;
    public String name;
    public Integer providerId;
    public Integer price;
    public List<String> categories = new ArrayList<>();
    public float rating;

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("name", this.name);
        json.put("providerId", this.providerId);
        json.put("price", this.price);
        json.put("categories", this.categories);
        json.put("rating", this.rating);
        return json;
    }
}

package Baloot.Data.Entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Commodity implements IEntity {
    private Integer id;
    private String name;
    private Integer providerId;
    private Integer price;
    private List<String> categories;
    public Commodity(Integer id, String name, Integer providerId, Integer price, List<String> categories, float rating, Integer inStock) {
        this.id = id;
        this.name = name;
        this.providerId = providerId;
        this.price = price;
        this.categories = categories;
        this.rating = rating;
        this.inStock = inStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    private float rating;
    private Integer inStock;

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("providerId", this.providerId);
        json.put("name", this.name);
        json.put("price", this.price);
        json.put("inStock", this.inStock);
        json.put("rating", this.rating);
        json.put("categories", new JSONArray(this.categories));
        return json;
    }
}

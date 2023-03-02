package Baloot.Business.DTOs;

import java.util.List;

import Baloot.Data.Entity.Commodity;

public class CommodityResultDTO {
    public CommodityResultDTO(Commodity commodity) {
        id = commodity.getId();
        name = commodity.getName();
        providerId = commodity.getProviderId();
        price = commodity.getPrice();
        categories = commodity.getCategories();
        rating = commodity.getRating();
    }
    public Integer id;
    public String name;
    public Integer providerId;
    public Integer price;
    public List<String> categories;
    public float rating;
}

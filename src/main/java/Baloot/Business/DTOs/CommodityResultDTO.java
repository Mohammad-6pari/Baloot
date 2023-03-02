package Baloot.Business.DTOs;

import java.util.List;

public class CommodityResultDTO {
    public CommodityResultDTO(CommodityDTO commodity) {
        id = commodity.id;
        name = commodity.name;
        providerId = commodity.providerId;
        price = commodity.price;
        categories = commodity.categories;
        rating = commodity.rating;
    }
    public Integer id;
    public String name;
    public Integer providerId;
    public Integer price;
    public List<String> categories;
    public float rating;
}

package Baloot.Data.Services.Implementations;

import Baloot.Business.DTOs.*;
import Baloot.Data.Entity.*;
import Baloot.Data.Services.IContextManager;

import java.util.ArrayList;
import java.util.List;

public class MemoryContextManager implements IContextManager {
    private final List<User> users;
    private final List<Provider> providers;
    private final List<Commodity> commodities;
    private final List<CommodityRate> commodityRates;
    private final List<BuyListItem> buyListItems;


    public MemoryContextManager() {
        this.users = new ArrayList<>();
        this.providers = new ArrayList<>();
        this.commodities = new ArrayList<>();
        this.commodityRates = new ArrayList<>();
        this.buyListItems = new ArrayList<>();
    }
    @Override
    public User addNewUser(UserDTO userDTO) {
        final User user = new User(userDTO.username, userDTO.password, userDTO.email, userDTO.birthDate, userDTO.address, userDTO.credit);
        users.add(user);
        return user;
    }
    @Override
    public User getUser(String username) {
        User tempUser = users.stream().filter(user->username.equals(user.getUsername())).findFirst().orElse(null);
        return tempUser;
    }
    @Override
    public User updateUser(UserDTO userDTO) {
        User tempUser = users.stream().filter(user->userDTO.username.equals(user.getUsername())).findFirst().orElse(null);
        if(tempUser != null){
            tempUser.setPassword(userDTO.password);
            tempUser.setAddress(userDTO.address);
            tempUser.setCredit(userDTO.credit);
            tempUser.setEmail(userDTO.email);
            tempUser.setBirthDate(userDTO.birthDate);
        }
        return tempUser;
    }

    @Override
    public Provider addProvider(ProviderDTO providerDTO) {
        final Provider provider = new Provider(providerDTO.id, providerDTO.name, providerDTO.registryDate);
        providers.add(provider);
        return provider;
    }

    @Override
    public Provider getProvider(Integer id) {
        Provider provider = providers.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        return provider;
    }

    @Override
    public Commodity addCommodity(CommodityDTO commodityDTO) {
        final Commodity commodity = new Commodity(commodityDTO.id, commodityDTO.name, commodityDTO.providerId, commodityDTO.price, commodityDTO.categories, commodityDTO.rating, commodityDTO.inStock);
        commodities.add(commodity);
        return commodity;
    }

    @Override
    public Commodity getCommodity(Integer id) {
        Commodity commodity = commodities.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
        return commodity;
    }

    @Override
    public List<Commodity> getCommoditiesList(){
        return commodities;
    }

    @Override
    public CommodityRate getRate(String username, Integer commodityId) {
        CommodityRate rate = commodityRates.stream().filter(r -> r.getCommodityId().equals(commodityId) && r.getUsername().equals(username)).findFirst().orElse(null);
        return rate;
    }

    @Override
    public Commodity rateCommodity(RateCommodityDTO rateCommodityDTO) {
        CommodityRate rate = getRate(rateCommodityDTO.username, rateCommodityDTO.commodityId);
        if (rate != null) {
            rate.setScore(rateCommodityDTO.score);
        } else {
            rate = new CommodityRate(rateCommodityDTO.username, rateCommodityDTO.commodityId, rateCommodityDTO.score);
            this.commodityRates.add(rate);
        }
        Commodity commodity = getCommodity(rateCommodityDTO.commodityId);
        if (commodity != null && getUser(rateCommodityDTO.username) != null) {
            commodity.setRating((commodity.getRating() + rate.getScore()) / 2);
        }

        return commodity;
    }

    @Override
    public Commodity addToBuyList(BuyListItemDTO buyListItemDTO) {
        Commodity commodity = getCommodity(buyListItemDTO.commodityId);
        if (commodity != null && commodity.getInStock() > 0 && getUser(buyListItemDTO.username) != null) {
            commodity.setInStock(commodity.getInStock() - 1);
            BuyListItem buyListItem = new BuyListItem(buyListItemDTO.username, buyListItemDTO.commodityId);
            buyListItems.add(buyListItem);
        }
        return commodity;
    }

    @Override
    public BuyListItem getBuyListItem(String username, Integer commodityId) {
        return buyListItems.stream().filter(i -> i.getUsername().equals(username) && i.getCommodityId().equals(commodityId)).findFirst().orElse(null);
    }

    @Override
    public BuyListItem removeBuyListItem(BuyListItemDTO buyListItemDTO) {
        BuyListItem buyListItem = new BuyListItem(buyListItemDTO.username, buyListItemDTO.commodityId);
        Commodity commodity = getCommodity(buyListItemDTO.commodityId);
        if (getUser(buyListItemDTO.username) != null && commodity != null) {
            commodity.setInStock(commodity.getInStock() + 1);
            buyListItems.remove(buyListItem);
        }
        return buyListItem;
    }

    @Override
    public List<Commodity> getCommoditiesByCategory(String category) {
        return commodities.stream()
            .filter(c -> c.getCategories().stream().anyMatch(category::contains)).toList();
    }

    @Override
    public List<Commodity> getBuyListByUsername(String username) {
        List<Integer> items = buyListItems.stream().filter(i -> i.getUsername().equals(username)).map(i -> i.getCommodityId()).toList();
        return commodities.stream().filter(c -> items.contains(c.getId())).toList();
    }
}

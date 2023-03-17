package Baloot.Data.Services;

import Baloot.Business.DTOs.*;
import Baloot.Data.Entity.*;

import java.util.List;

public interface IContextManager {
    User addNewUser(UserDTO userDTO);
    User getUser(String username);
    User AddUserCredit(String username, Integer amount);
    User updateUser(UserDTO userDTO);
    Provider addProvider(ProviderDTO providerDTO);
    Provider getProvider(Integer id);
    Commodity addCommodity(CommodityDTO commodityDTO);
    Commodity getCommodity(Integer id);
    CommodityRate getRate(String username, Integer commodityId);

    List<Commodity> getCommoditiesList();
    Commodity rateCommodity(RateCommodityDTO rateCommodityDTO);
    Commodity addToBuyList(BuyListItemDTO buyListItemDTO);
    BuyListItem getBuyListItem(String username, Integer commodityId);
    BuyListItem removeBuyListItem(BuyListItemDTO buyListItemDTO);
    List<Commodity> getCommoditiesByCategory(String category);
    List<Commodity> getCommoditiesByPrice(Integer startPrice, Integer endPrice);
    List<Commodity> getBuyListByUsername(String username);
}

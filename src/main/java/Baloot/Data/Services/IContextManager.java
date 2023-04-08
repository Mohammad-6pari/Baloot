package Baloot.Data.Services;

import Baloot.Business.DTOs.*;
import Baloot.Data.Entity.*;

import java.util.List;

public interface IContextManager {
    User addNewUser(UserDTO userDTO);
    User getUser(String username);
    User loginUser(String username, String password);
    boolean isUserAuthenticated();
    void logoutUser();
    User getLoggedinUser();
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
    List<Commodity> getCommoditySuggestions(List<String> categories);
    List<Commodity> getCommoditiesByCategory(String category);
    List<Commodity> getCommoditiesByPrice(Integer startPrice, Integer endPrice);
    List<Commodity> getCommoditiesByName(String name);
    List<Commodity> getBuyListByUsername(String username);
    Comment voteComment(String username, Integer commentId, Integer vote);
    CommentVote getCommentVote(String username, Integer commentId);
    Comment getComment(Integer commentId);
    Comment addComment(String userEmail, Integer commodityId, String text);
    List<Comment> getCommodityComments(Integer commodityId);
    void submitBuyList(String username);
    Discount getDiscount(String code);
    void applyDiscount(String username, String code);
    Integer getBuyListTotalPrice(String username);
}

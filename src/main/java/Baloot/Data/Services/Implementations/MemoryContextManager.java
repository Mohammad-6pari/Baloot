package Baloot.Data.Services.Implementations;

import Baloot.Business.DTOs.*;
import Baloot.Data.Entity.*;
import Baloot.Data.Services.IContextManager;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MemoryContextManager implements IContextManager {
    private final String BASE_URL = "http://5.253.25.110:5000/api";
    private final String BASE_URL_V2 = "http://5.253.25.110:5000/api/v2";

    private final List<User> users;
    private User loggedinUser;
    private final List<Provider> providers;
    private final List<Commodity> commodities;
    private final List<Comment> comments;
    private final List<CommentVote> commentVotes;
    private final List<CommodityRate> commodityRates;
    private final List<BuyListItem> buyListItems;
    private final List<Discount> discounts;

    private List<Commodity> getCommoditiesFromApi() {
        try {
            Document doc = Jsoup.connect(BASE_URL_V2 + "/commodities").ignoreContentType(true).get();

            JSONArray jsonArray = new JSONArray(doc.body().text());
            List<Commodity> apiCommodities = new ArrayList<>();

            for(int i = 0; i < jsonArray.length(); i++) {
                var commodity = jsonArray.getJSONObject(i);
                var categories = new ArrayList<String>();
                for (int j = 0; j < commodity.getJSONArray("categories").length(); j++) {
                    categories.add(commodity.getJSONArray("categories").getString(j));
                }
                apiCommodities.add(new Commodity(
                        commodity.getInt("id"),
                        commodity.getString("name"),
                        commodity.getInt("providerId"),
                        commodity.getInt("price"),
                        categories,
                        commodity.getFloat("rating"),
                        commodity.getInt("inStock")
                    )
                );
            }

            return apiCommodities;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<Provider> getProvidersFromApi() {
        try {
            Document doc = Jsoup.connect(BASE_URL_V2 + "/providers").ignoreContentType(true).get();
            JSONArray jsonArray = new JSONArray(doc.body().text());
            List<Provider> apiProviders = new ArrayList<>();


            for(int i = 0; i < jsonArray.length(); i++) {
                var provider = jsonArray.getJSONObject(i);
                apiProviders.add(new Provider(
                        provider.getInt("id"),
                        provider.getString("name"),
                        provider.getString("registryDate")
                    )
                );
            }
            return apiProviders;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<User> getUsersFromApi() {
        try {
            Document doc = Jsoup.connect(BASE_URL + "/users").ignoreContentType(true).get();
            JSONArray jsonArray = new JSONArray(doc.body().text());
            List<User> apiUsers = new ArrayList<>();

            for(int i = 0; i < jsonArray.length(); i++) {
                var user = jsonArray.getJSONObject(i);
                apiUsers.add(new User(
                        user.getString("username"),
                        user.getString("password"),
                        user.getString("email"),
                        user.getString("birthDate"),
                        user.getString("address"),
                        user.getInt("credit")
                    )
                );
            }
            return apiUsers;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<Comment> getCommentsFromApi() {
        try {
            Document doc = Jsoup.connect(BASE_URL + "/comments").ignoreContentType(true).get();
            JSONArray jsonArray = new JSONArray(doc.body().text());
            List<Comment> apiComments = new ArrayList<>();

            for(int i = 0; i < jsonArray.length(); i++) {
                var comment = jsonArray.getJSONObject(i);
                apiComments.add(new Comment(
                                i,
                                comment.getString("date"),
                                comment.getString("userEmail"),
                                comment.getInt("commodityId"),
                                comment.getString("text")
                        )
                );
            }
            return apiComments;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<Discount> getDiscountsFromApi() {
        try {
            Document doc = Jsoup.connect(BASE_URL + "/discount").ignoreContentType(true).get();
            JSONArray jsonArray = new JSONArray(doc.body().text());
            List<Discount> apiDiscounts = new ArrayList<>();

            for(int i = 0; i < jsonArray.length(); i++) {
                var discount = jsonArray.getJSONObject(i);
                apiDiscounts.add(new Discount(
                         discount.getString("discountCode"),
                         discount.getInt("discount")
                     )
                );
            }
            return apiDiscounts;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public MemoryContextManager() {
        this.users = getUsersFromApi();
        this.loggedinUser = null;
        this.providers = getProvidersFromApi();
        this.commodities = getCommoditiesFromApi();
        this.comments = getCommentsFromApi();
        this.commentVotes = new ArrayList<>();
        this.commodityRates = new ArrayList<>();
        this.buyListItems = new ArrayList<>();
        this.discounts = getDiscountsFromApi();
    }
    @Override
    public User addNewUser(UserDTO userDTO) {

        final User user = new User(userDTO.username, String.valueOf(userDTO.password.hashCode()), userDTO.email, userDTO.birthDate, userDTO.address, userDTO.credit);
        users.add(user);
        return user;
    }
    @Override
    public User getUser(String username) {
        User tempUser = users.stream().filter(user->username.equals(user.getUsername())).findFirst().orElse(null);
        return tempUser;
    }

    @Override
    public Object loginUser(String username, String password) {
        if (loggedinUser != null) return 403;
        User user = getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedinUser = user;
            return user;
        }
        return 401;
    }

    @Override
    public boolean isUserAuthenticated() {
        return loggedinUser != null;
    }

    @Override
    public int logoutUser() {
        if (loggedinUser==null)
            return 401;
        else{
            loggedinUser = null;
            return 200;
        }
    }

    @Override
    public User getLoggedinUser() {
        return loggedinUser;
    }

    @Override
    public User AddUserCredit(String username, Integer amount) {
        User user = getUser(username);
        if (user != null && amount > 0) {
            user.setCredit(user.getCredit() + amount);
        }
        return user;
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
    public Object addToBuyList(BuyListItemDTO buyListItemDTO) {
        Commodity commodity = getCommodity(buyListItemDTO.commodityId);
        if (getBuyListItem(buyListItemDTO.username, buyListItemDTO.commodityId) != null)
            return -1;
        if (commodity != null && commodity.getInStock() > 0 && getUser(buyListItemDTO.username) != null) {
            commodity.setInStock(commodity.getInStock() - 1);
            BuyListItem buyListItem = new BuyListItem(buyListItemDTO.username, buyListItemDTO.commodityId);
            buyListItems.add(buyListItem);
            return commodity;
        }
        return 0;
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
            var newList = buyListItems.stream()
                .filter(i -> !i.getCommodityId().equals(buyListItemDTO.commodityId) && !i.getUsername().equals(buyListItemDTO.username))
                    .collect(Collectors.toList());
            buyListItems.clear();
            buyListItems.addAll(newList);
            return buyListItem;
        }
        return null;
    }

    private boolean hasSameCategories(Commodity commodity,  List<String> categories) {
        for (String category : categories)
            if (commodity.getCategories().contains(category))
                return true;
        return false;
    }

    private float getSuggestionScore(Commodity commodity, List<String> categories) {
        return commodity.getRating() + (hasSameCategories(commodity, categories) ? 11 : 0);
    }

    @Override
    public List<Commodity> getCommoditySuggestions(List<String> categories) {
        return commodities.stream().sorted(
                (c1, c2) -> Float.compare(getSuggestionScore(c1, categories), getSuggestionScore(c2, categories))
        ).collect(Collectors.toList()).subList(Math.max(commodities.size() - 3, 0), commodities.size());
    }

    @Override
    public List<Commodity> getCommoditiesByCategory(String category) {
        return commodities.stream()
            .filter(c -> c.getCategories().stream().anyMatch(category::contains)).collect(Collectors.toList());
    }

    @Override
    public List<Commodity> getCommoditiesByPrice(Integer startPrice, Integer endPrice) {
        return commodities.stream()
            .filter(c -> c.getPrice() >= startPrice && c.getPrice() <= endPrice).collect(Collectors.toList());
    }

    @Override
    public List<Commodity> getCommoditiesByName(String name) {
        return commodities.stream().filter(c -> c.getName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public List<Commodity> getBuyListByUsername(String username) {
        List<Integer> items = buyListItems.stream().filter(i -> i.getUsername().equals(username)).map(i -> i.getCommodityId()).collect(Collectors.toList());
        return commodities.stream().filter(c -> items.contains(c.getId())).collect(Collectors.toList());
    }

    @Override
    public Comment voteComment(String username, Integer commentId, Integer vote) {
        var commentVote = getCommentVote(username, commentId);
        if (commentVote == null) {
            commentVote = new CommentVote(username, commentId, vote);
            commentVotes.add(commentVote);
        } else {
            commentVote.setVote(vote);
        }
        return getComment(commentId);
    }

    @Override
    public CommentVote getCommentVote(String username, Integer commentId) {
        return commentVotes.stream()
                .filter(v -> Objects.equals(v.getCommentId(), commentId) && v.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    @Override
    public Comment getComment(Integer commentId) {
        return comments.stream().filter(c -> c.getId() == commentId).findFirst().orElse(null);
    }

    @Override
    public Comment addComment(String userEmail, Integer commodityId, String text) {
        Integer lastId = comments.stream().map(Comment::getId).max(Integer::compare).orElse(0);
        var comment = new Comment(
            lastId + 1,
            new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
            userEmail,
            commodityId,
            text
        );
        comments.add(comment);
        return comment;
    }

    private void setCommentLikes(Comment comment) {
        comment.setDislikes(0);
        comment.setLikes(0);
        commentVotes.stream().filter(v -> v.getCommentId().equals(comment.getId()))
                .forEach(v -> {
                    if (v.getVote().equals(1))
                        comment.setLikes(comment.getLikes() + 1);
                    else comment.setDislikes(comment.getDislikes() + 1);
                });
    }
    @Override
    public List<Comment> getCommodityComments(Integer commodityId) {
        return comments.stream().filter(c -> c.getCommodityId().equals(commodityId))
                .peek(this::setCommentLikes).collect(Collectors.toList());
    }

    @Override
    public int submitBuyList(String username) {
        var user = getUser(username);
        var totalPrice = getBuyListTotalPrice(username);
        if (user.getCredit() < totalPrice)
            return 0;

        user.setCredit(user.getCredit() - totalPrice);
        user.useCurrentDiscount();
        var newBuyListItems = buyListItems.stream()
                .filter(i -> !i.getUsername().equals(username)).collect(Collectors.toList());
        buyListItems.clear();
        buyListItems.addAll(newBuyListItems);
        return 1;
    }

    @Override
    public Discount getDiscount(String code) {
        return discounts.stream().filter(d -> d.getCode().equals(code)).findFirst().orElse(null);
    }

    @Override
    public int applyDiscount(String username, String code) {
        var user = getUser(username);
        var discount = getDiscount(code);
        if (discount == null) return -1;
        if (user.getUsedDiscounts().stream().anyMatch(d -> d.getCode().equals(code)))
            return 0;
        user.setCurrentDiscount(discount);
        return 1;
    }

    @Override
    public Integer getBuyListTotalPrice(String username) {
        var sum = getBuyListByUsername(username).stream().mapToInt(Commodity::getPrice).sum();
        var discount = getUser(username).getCurrentDiscount();
        if (discount != null)
            sum = sum * (100 - discount.getDiscount()) / 100;
        return sum;
    }
}

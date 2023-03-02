package Baloot.Data.Entity;

import org.json.JSONObject;

public class BuyListItem implements IEntity {
    private String username;
    private Integer commodityId;

    public BuyListItem(String username, Integer commodityId) {
        this.username = username;
        this.commodityId = commodityId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    public boolean equals(Object o) {
        return this.username == ((BuyListItem)o).username && this.commodityId == ((BuyListItem)o).commodityId;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", this.username);
        json.put("commodityId", this.commodityId);

        return json;
    }
}

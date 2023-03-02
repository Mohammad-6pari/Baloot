package Baloot.Data.Entity;

import org.json.JSONObject;

public class CommodityRate implements IEntity {
    private String username;
    private Integer commodityId;
    private Integer score;

    public CommodityRate(String username, Integer commodityId, Integer score) {
        this.username = username;
        this.commodityId = commodityId;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", this.username);
        json.put("commodityId", this.commodityId);
        json.put("score", this.score);

        return json;
    }
}

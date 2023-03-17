package Baloot.Data.Entity;

import org.json.JSONObject;

public class Comment implements IEntity {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public Comment(Integer id, String date, String userEmail, Integer commodityId, String text) {
        this.id = id;
        this.date = date;
        this.userEmail = userEmail;
        this.commodityId = commodityId;
        this.text = text;
    }

    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private Integer commodityId;
    private String text;

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("userEmail", userEmail);
        json.put("commodityId", commodityId);
        json.put("date", date);
        json.put("text", text);
        return json;
    }
}

package Baloot.Data.Entity;

import org.json.JSONObject;

public class Discount implements IEntity {
    private final String code;
    private final Integer discount;

    public String getCode() {
        return code;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Discount(String code, Integer discount) {
        this.code = code;
        this.discount = discount;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}

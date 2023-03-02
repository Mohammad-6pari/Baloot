package Baloot.Data.Entity;

import org.json.JSONObject;

public class User implements IEntity {
    private final String username;
    private String password;
    private String email;
    private String birthDate;
    private String address;
    private Integer credit;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public User(String username, String password, String email,
                String birthDate, String address, Integer credit) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.credit = credit;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", this.username);
        json.put("password", this.password);
        json.put("email", this.email);
        json.put("address", this.address);
        json.put("birthDate", this.birthDate);
        json.put("credit", this.credit);

        return json;
    }
}

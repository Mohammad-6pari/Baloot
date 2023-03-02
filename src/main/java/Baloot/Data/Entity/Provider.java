package Baloot.Data.Entity;

import org.json.JSONObject;

public class Provider implements IEntity {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(String registryDate) {
        this.registryDate = registryDate;
    }

    private String name;
    private String registryDate;

    public Provider(Integer id,String name, String registryDate) {
        this.id = id;
        this.name = name;
        this.registryDate = registryDate;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("name", this.name);
        json.put("registryDate", this.registryDate);
        return json;
    }
}

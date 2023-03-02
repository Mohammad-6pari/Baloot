package Baloot.Presentation;

import org.json.JSONObject;

public class Response {
    private ResponseStatus status;
    private JSONObject data;

    public Response(ResponseStatus status, JSONObject data) {
        this.status = status;
        this.data = data;
    }

    public ResponseStatus getStatus() {
        return this.status;
    }

    public JSONObject getData() {
        return this.data;
    }
}

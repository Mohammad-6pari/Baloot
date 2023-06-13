package Baloot.Data.Entity;

import org.json.JSONObject;

public class CommentVote implements IEntity {
    private String username;
    private Integer commentId;
    private Integer vote;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public CommentVote(String username, Integer commentId, Integer vote) {
        this.username = username;
        this.commentId = commentId;
        this.vote = vote;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("commentId", commentId);
        json.put("vote", vote);

        return json;
    }
}

package Baloot.Business.Commands;

import Baloot.Business.Command;
import Baloot.Data.Services.IContextManager;
import Baloot.Presentation.Response;
import Baloot.Presentation.ResponseStatus;
import org.json.JSONObject;

public class VoteCommentCommand extends Command {
    private final String username;
    private final Integer commentId;
    private final Integer vote;

    public VoteCommentCommand(String username, Integer commentId, Integer vote) {
        this.username = username;
        this.commentId = commentId;
        this.vote = vote;
    }

    @Override
    public Response execute(IContextManager contextManager) {
        var user = contextManager.getUser(username);
        var comment = contextManager.getComment(commentId);
        if (user == null || comment == null) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "user or comment does not exist");
            return new Response(ResponseStatus.FAILURE, json);
        }
        if (vote != -1 && vote != 0 && vote != 1) {
            JSONObject json = new JSONObject();
            json.put("errorMessage", "Vote should be either 1, 0, or -1");
            return new Response(ResponseStatus.FAILURE, json);
        }
        return new Response(ResponseStatus.SUCCESS, contextManager.voteComment(username, commentId, vote).toJson());
    }
}

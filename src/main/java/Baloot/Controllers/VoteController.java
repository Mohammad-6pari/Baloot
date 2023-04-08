package Baloot.Controllers;

import Baloot.Data.Services.ContextLoader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/voteComment/*")
public class VoteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) resp.sendRedirect("/login");
        else {
            var commentId = Integer.valueOf(req.getPathInfo().split("/")[1]);;
            var comment = contextManager.voteComment(user.getUsername(), commentId, Integer.parseInt(req.getParameter("vote")));
            resp.sendRedirect("/commodities/" + comment.getCommodityId());
        }
    }
}

package Baloot.Controllers;

import Baloot.Data.Services.ContextLoader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addComment/*")
public class CommentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) resp.sendRedirect("/login");
        else {
            req.setAttribute("username", user.getUsername());
            Integer commodityId = Integer.valueOf(req.getPathInfo().split("/")[1]);
            contextManager.addComment(user.getEmail(), commodityId, req.getParameter("comment"));
            resp.sendRedirect("/commodities/" + commodityId);
        }
    }
}

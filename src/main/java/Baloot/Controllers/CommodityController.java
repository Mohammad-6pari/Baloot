package Baloot.Controllers;


import Baloot.Data.Services.ContextLoader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/commodities/*")
public class CommodityController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) resp.sendRedirect("/login");
        else {
            req.setAttribute("username", user.getUsername());
            var commodity = contextManager.getCommodity(Integer.valueOf(req.getPathInfo().split("/")[1]));
            if (commodity == null)
                resp.sendRedirect("/commodities");
            else {
                req.setAttribute("commodity", commodity);
                req.setAttribute("comments", contextManager.getCommodityComments(commodity.getId()));
                var dispatcher = req.getRequestDispatcher("/Commodity.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}

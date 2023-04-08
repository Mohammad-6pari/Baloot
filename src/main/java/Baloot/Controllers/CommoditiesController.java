package Baloot.Controllers;

import Baloot.Data.Entity.Commodity;
import Baloot.Data.Services.ContextLoader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/commodities")
public class CommoditiesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var contextManager = ContextLoader.getContextManager();

        var user = contextManager.getLoggedinUser();
        if (user == null) resp.sendRedirect("/login");
        else {
            req.setAttribute("username", user.getUsername());
            var searchBy = req.getParameter("searchBy");
            var query = req.getParameter("q");
            var sortBy = req.getParameter("sortBy");
            List<Commodity> commodities;
            if (query != null) {
                if (searchBy != null && searchBy.equals("name")) {
                    commodities = contextManager.getCommoditiesByName(query);
                }
                else if (searchBy != null && searchBy.equals("category")) {
                    commodities = contextManager.getCommoditiesByCategory(query);
                }
                else commodities = contextManager.getCommoditiesList();
            } else commodities = contextManager.getCommoditiesList();
            if (sortBy != null && sortBy.equals("rate")) {
                commodities = commodities.stream()
                        .sorted((c1, c2) -> Float.compare(c1.getRating(), c2.getRating())).collect(Collectors.toList());
            }
            req.setAttribute("commodities", commodities);
            var dispatcher = req.getRequestDispatcher("Commodities.jsp");
            dispatcher.forward(req, resp);
        }
    }
}

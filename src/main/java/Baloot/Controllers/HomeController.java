package Baloot.Controllers;


import Baloot.Data.Services.ContextLoader;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class HomeController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var contextManager = ContextLoader.getContextManager();

        if (!contextManager.isUserAuthenticated()) {
            response.sendRedirect("/login");
        } else {
            request.setAttribute("user", contextManager.getLoggedinUser());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}

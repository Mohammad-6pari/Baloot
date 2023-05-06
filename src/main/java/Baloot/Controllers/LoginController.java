//package Baloot.Controllers;
//
//import Baloot.Data.Services.ContextLoader;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet("/login")
//public class LoginController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var contextManager = ContextLoader.getContextManager();
//
//        if (contextManager.isUserAuthenticated())
//            resp.sendRedirect("/");
//        else {
//            var dispatcher = req.getRequestDispatcher("Login.jsp");
//            dispatcher.forward(req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var contextManager = ContextLoader.getContextManager();
//
//        var username = req.getParameter("username");
//        var password = req.getParameter("password");
//        var res = contextManager.loginUser(username, password);
//        if (res == null) resp.sendRedirect("/login");
//        else resp.sendRedirect("/");
//    }
//}

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
//@WebServlet("/logout")
//public class LogoutController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var contextManager = ContextLoader.getContextManager();
//
//        contextManager.logoutUser();
//        resp.sendRedirect("/login");
//    }
//}

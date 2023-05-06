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
//@WebServlet("/credit")
//public class CreditController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var contextManager = ContextLoader.getContextManager();
//
//        var user = contextManager.getLoggedinUser();
//        if (user == null) resp.sendRedirect("/login");
//        else {
//            var dispatcher = req.getRequestDispatcher("/Credit.jsp");
//            dispatcher.forward(req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var contextManager = ContextLoader.getContextManager();
//
//        var user = contextManager.getLoggedinUser();
//        if (user == null) resp.sendRedirect("/login");
//        else {
//            user.setCredit(user.getCredit() + Integer.parseInt(req.getParameter("credit")));
//            resp.sendRedirect("/buyList");
//        }
//    }
//}

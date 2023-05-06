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
//@WebServlet("/payment")
//public class PaymentController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var contextManager = ContextLoader.getContextManager();
//
//        var user = contextManager.getLoggedinUser();
//        if (user == null) resp.sendRedirect("/login");
//        else {
//            contextManager.submitBuyList(user.getUsername());
//            resp.sendRedirect("/buyList");
//        }
//    }
//}

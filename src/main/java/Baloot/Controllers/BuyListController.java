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
//@WebServlet("/buyList")
//public class BuyListController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var contextManager = ContextLoader.getContextManager();
//
//        var user = contextManager.getLoggedinUser();
//        if (user == null) resp.sendRedirect("/login");
//        else {
//            req.setAttribute("user", user);
//            req.setAttribute("buyList", contextManager.getBuyListByUsername(user.getUsername()));
//            req.setAttribute("totalPrice", contextManager.getBuyListTotalPrice(user.getUsername()));
//            var dispatcher = req.getRequestDispatcher("/BuyList.jsp");
//            dispatcher.forward(req, resp);
//        }
//    }
//}

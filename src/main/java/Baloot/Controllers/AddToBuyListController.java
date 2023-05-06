//package Baloot.Controllers;
//
//import Baloot.Business.DTOs.BuyListItemDTO;
//import Baloot.Data.Services.ContextLoader;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet("/addToBuyList/*")
//public class AddToBuyListController extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var contextManager = ContextLoader.getContextManager();
//
//        var user = contextManager.getLoggedinUser();
//        if (user == null) resp.sendRedirect("/login");
//        else {
//            var buyListItem = new BuyListItemDTO();
//            buyListItem.commodityId = Integer.valueOf(req.getPathInfo().split("/")[1]);
//            buyListItem.username = user.getUsername();
//            contextManager.addToBuyList(buyListItem);
//            resp.sendRedirect("/buyList");
//        }
//    }
//}

//package Baloot.Controllers;
//
//import Baloot.Business.DTOs.RateCommodityDTO;
//import Baloot.Data.Services.ContextLoader;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet("/rateCommodity/*")
//public class RateController extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var contextManager = ContextLoader.getContextManager();
//
//        var user = contextManager.getLoggedinUser();
//        if (user == null) resp.sendRedirect("/login");
//        else {
//            var rate = new RateCommodityDTO();
//            rate.username = user.getUsername();
//            rate.commodityId = Integer.valueOf(req.getPathInfo().split("/")[1]);
//            rate.score = Integer.parseInt(req.getParameter("rate"));
//            contextManager.rateCommodity(rate);
//            resp.sendRedirect("/commodities/" + rate.commodityId);
//        }
//    }
//}

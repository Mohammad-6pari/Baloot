<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Baloot.Data.Entity.Commodity" %>
<%@ page import="Baloot.Data.Entity.User" %>

<%
    List<Commodity> buyList = (ArrayList<Commodity>) request.getAttribute("buyList");
    User user = (User) request.getAttribute("user");
    Integer totalPrice = (Integer) request.getAttribute("totalPrice");
%>

<html lang="en"><head>
    <meta charset="UTF-8">
    <title>User</title>
    <style>
        li {
            padding: 5px
        }
        table{
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<ul>
    <li id="username">Username: <%=user.getUsername()%></li>
    <li id="email">Email: <%=user.getEmail()%></li>
    <li id="birthDate">Birth Date: <%=user.getBirthDate()%></li>
    <li id="address"><%=user.getAddress()%></li>
    <li id="credit">Credit: <%=user.getCredit()%></li>
    <li>Current Buy List Price: <%=totalPrice%></li>
    <li>
        <a href="/credit">Add Credit</a>
    </li>
    <li>
        <form action="/payment" method="get">
            <label>Submit & Pay</label>
            <button type="submit">Payment</button>
        </form>
    </li>
</ul>
<table>
    <caption>
        <h2>Buy List</h2>
    </caption>
    <tbody><tr>
        <th>Id</th>
        <th>Name</th>
        <th>Provider Name</th>
        <th>Price</th>
        <th>Categories</th>
        <th>Rating</th>
        <th>In Stock</th>
        <th></th>
        <th></th>
    </tr>
    <% for (Commodity commodity : buyList) { %>
    <tr>
        <td><%=commodity.getId()%></td>
        <td><%=commodity.getName()%></td>
        <td><%=commodity.getProviderId()%></td>
        <td><%=commodity.getPrice()%></td>
        <%
            List<String> categoryList = new ArrayList<>(commodity.getCategories());
            String categoryString = String.join(", ", categoryList);
        %>
        <td><%=categoryString%></td>
        <td><%=commodity.getRating()%></td>
        <td><%=commodity.getInStock()%></td>
        <td><a href="/commodities/<%=commodity.getId()%>">Link</a></td>
        <td>
            <form action="/removeFromBuyList/<%=commodity.getId()%>" method="POST">
                <button type="submit">Remove</button>
            </form>
        </td>
    </tr>
    <% } %>
    </tbody></table>
<br>
<label>Add Your Discount Code:</label>
<form action="/addDiscount" method="post">
    <input type="text" name="code" value="" />
    <button type="submit">submit</button>
</form>
<%if (user.getCurrentDiscount() != null) {%>
    <p>Your Currently activated code: <%=user.getCurrentDiscount().getCode()%>
        (<%=user.getCurrentDiscount().getDiscount()%> %)
    </p>
<%}%>
<br>
</body></html>
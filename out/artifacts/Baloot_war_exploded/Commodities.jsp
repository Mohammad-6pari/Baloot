<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Baloot.Data.Entity.Commodity" %>


<%
    List<Commodity> commodities = (ArrayList<Commodity>) request.getAttribute("commodities");
    String username = (String)request.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Commodities</title>
    <style>
        table{
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<a href="/">Home</a>
<p id="username">username: <%=username%></p>
<br><br>
<form action="/commodities" method="get">
    <label>Search:</label>
    <label>
        <input type="text" name="q" />
    </label>
    <button type="submit" name="searchBy" value="category">Search By Category</button>
    <button type="submit" name="searchBy" value="name">Search By Name</button>
</form>
<br><br>
<form action="/commodities" method="get">
    <label>Sort By:</label>
    <button type="submit" name="sortBy" value="rate">Rate</button>
</form>
<br><br>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Provider Name</th>
        <th>Price</th>
        <th>Categories</th>
        <th>Rating</th>
        <th>In Stock</th>
        <th>Links</th>
    </tr>
    <% for(int i = 0; i < commodities.size(); i+=1) { %>
    <tr>
        <td><%=commodities.get(i).getId()%></td>
        <td><%=commodities.get(i).getName()%></td>
        <td><%=commodities.get(i).getProviderId()%></td>
        <td><%=commodities.get(i).getPrice()%></td>
        <%
            List<String> categoryList = new ArrayList<>(commodities.get(i).getCategories());
            String categoryString = String.join(", ", categoryList);
        %>
        <td><%= categoryString %></td>

        <td><%=commodities.get(i).getRating()%></td>
        <td><%=commodities.get(i).getInStock()%></td>
        <td><a href="/commodities/<%=commodities.get(i).getId()%>">Link</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>
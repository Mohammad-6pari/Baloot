<%@ page import="java.text.Format" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Baloot.Data.Entity.Commodity" %>
<%@ page import="Baloot.Data.Entity.Comment" %>


<%
  Commodity commodity = (Commodity) request.getAttribute("commodity");
  List<Commodity> suggestions = (List<Commodity>)request.getAttribute("suggestions");
  List<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
  String username = (String) request.getAttribute("username");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Commodity</title>
  <style>
    li {
      padding: 5px;
    }
    table {
      width: 100%;
      text-align: center;
    }
  </style>
</head>
<body>
<a href="/">Home</a>
<span>username: <%=username%></span>
<br>
<ul>
  <li id="id">Id: <%=commodity.getId()%> </li>
  <li id="name">Name: <%=commodity.getName()%></li>
  <li id="providerName">Provider Name: <%=commodity.getProviderId()%></li>
  <li id="price">Price: <%=commodity.getPrice()%></li>
  <li id="categories">Categories:
    <% for(String category : commodity.getCategories()) { %>
    <%=category%>,
    <% } %>
  </li>
  <li id="rating">Rating: <%=commodity.getRating()%></li>
  <li id="inStock">In Stock: <%=commodity.getInStock()%></li>
</ul>

<label>Add Your Comment:</label>
<form action="/addComment/<%=commodity.getId()%>" method="POST">
  <input type="text" name="comment" />
  <button type="submit">submit</button>
</form>
<br>
<form action="/rateCommodity/<%=commodity.getId()%>" method="POST">
  <label>Rate(between 1 and 10):</label>
  <input type="number" name="rate" min="1" max="10">
  <button type="submit">Rate</button>
</form>
<br>
<form action="/addToBuyList/<%=commodity.getId()%>" method="POST">
  <button type="submit">Add to BuyList</button>
</form>
<br />
<table>
  <caption><h2>Comments</h2></caption>
  <tr>
    <th>username</th>
    <th>comment</th>
    <th>date</th>
    <th>likes</th>
    <th>dislikes</th>
  </tr>
  <% for(Comment comment : comments) { %>
  <tr>
    <td><%=comment.getUserEmail()%></td>
    <td><%=comment.getText()%></td>
    <td><%=comment.getDate()%></td>
    <td>
      <form action="/voteComment/<%=comment.getId()%>" method="POST">
        <label><%=comment.getLikes()%></label>
        <button type="submit" name="vote" value="1">like</button>
      </form>
    </td>
    <td>
      <form action="/voteComment/<%=comment.getId()%>" method="POST">
        <label><%=comment.getDislikes()%></label>
        <button type="submit" name="vote" value="-1">dislike</button>
      </form>
    </td>
  </tr>
  <% } %>
</table>
<br><br>
<table>
  <caption><h2>Suggested Commodities</h2></caption>
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
  <% for(Commodity suggestion : suggestions) { %>
  <tr>
    <td><%=suggestion.getId()%></td>
    <td><%=suggestion.getName()%></td>
    <td><%=suggestion.getProviderId()%></td>
    <td><%=suggestion.getPrice()%></td>
    <%
      List<String> categoryList = new ArrayList<>(suggestion.getCategories());
      String categoryString = String.join(", ", categoryList);
    %>
    <td><%= categoryString %></td>

    <td><%=suggestion.getRating()%></td>
    <td><%=suggestion.getInStock()%></td>
    <td><a href="/commodities/<%=suggestion.getId()%>">Link</a></td>
  </tr>
  <% } %>
</table>
</body>
</html>

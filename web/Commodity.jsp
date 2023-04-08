<%@ page import="java.text.Format" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Baloot.Data.Entity.Commodity" %>
<%@ page import="Baloot.Data.Entity.Comment" %>


<%
  Commodity commodity = (Commodity) request.getAttribute("commodity");
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
</body>
</html>

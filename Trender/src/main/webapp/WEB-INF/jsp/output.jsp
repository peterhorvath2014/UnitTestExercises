<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <h2>Here is a simple CRUD using Spring MVC and MongoDB.</h2>
 
        <form action="person/save" method="post">
            <input type="hidden" name="id">
            <label for="date">Workinghours date</label>
            <input type="text" id="date" name="date"/>
            <input type="submit" value="Submit"/>
        </form>
 
    <table border="1">
        <c:forEach var="workinghours" items="${workingHoursList}">
            <tr>
                <td>${workinghours.date}</td><td><input type="button" value="delete" onclick="window.location='person/delete?id=${workinghours.id}'"/></td>
            </tr>
        </c:forEach>
    </table>  
</body>
</html>
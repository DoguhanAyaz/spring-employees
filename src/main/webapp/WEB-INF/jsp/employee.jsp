<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>Employees</title>
    
</head>
<body>
<h2> List of Employees</h2>
<table class="table table-striped table-hover table-condensed table-bordered">
    <thead>
    <tr>
        <th style="width: 150px;">Id</th>
        <th style="width: 150px;">Name</th>
        <th style="width: 150px;">Salary</th>
        <th style="width: 150px;">Department</th>
    </tr>
    </thead>
    <tbody>


    <%--@elvariable id="employees" type="com.ayaz.springemployees.Controller.JspController"--%>
    <c:forEach var="employee" items="${employees}">
    <tr>
        <td><c:out value="${employee.id}" /></td>
        <td><c:out value="${employee.name}"/></td>
        <td ><c:out value="${employee.salary}"/></td>
        <td><c:out value="${employee.department}"/></td>

    </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
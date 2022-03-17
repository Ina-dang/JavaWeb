<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/css/bootstrap.min.css" integrity="sha512-Ez0cGzNzHR1tYAv56860NLspgUGuQw16GiOOp/I2LuTmpSK9xDXlgJz3XN4cnpXWDmkNBKXR/VDMTCnAaEooxA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/js/bootstrap.min.js" integrity="sha512-EKWWs1ZcA2ZY9lbLISPz8aGR2+L7JVYqBAYTq5AXgBkSjRSuQEGqWx8R1zAX16KdXPaCjOCaKE8MCpU0wcHlHA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
    <div class="container mt-3">
        <h2>Users info.</h2>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>id</th>
              <th>password</th>
              <th>name</th>
              <th>nickname</th>
              <th>email</th>
              <th>joinDate</th>
              <th>delete</th>
            </tr>
          </thead>
          <tbody>
		  <c:forEach items="${members}" var="member">          
            <tr>
              <td>${member.id}</td>
              <td>${member.pw}</td>
              <td>${member.name}</td>
              <td>${member.nick}</td>
              <td>${member.email}</td>
              <td>${member.joindate}</td>
              <td><a href="remove?id=${member.id}">삭제</a></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
</body>
</html>
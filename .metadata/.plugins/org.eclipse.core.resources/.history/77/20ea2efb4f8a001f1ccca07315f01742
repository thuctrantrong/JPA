<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form action="${pageContext.request.contextPath}/admin/category/update"" method ="POST">
    <input type="text" id="categoryid" name="categoryid" value="${cate.categoryid}" hidden="hidden"><br>
    <label for="fname">Category name:</label><br>
    <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>
    
    <label for="lname">Images:</label><br>
    <c:choose>
        <c:when test="${not empty cate.images}">
            <c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
            <img height="150" width="200" src="${imgUrl}" alt="Category Image" />
        </c:when>
        <c:otherwise>
            <p>No image available</p>
        </c:otherwise>
    </c:choose>
    
    <input type="file" id="images" name="images"><br>
    
    <label for="status">Status:</label><br>
    <input type="text" id="status" name="status" value="${cate.status}"><br>
    
    <br>
    <input type="submit" value="Submit">
</form>

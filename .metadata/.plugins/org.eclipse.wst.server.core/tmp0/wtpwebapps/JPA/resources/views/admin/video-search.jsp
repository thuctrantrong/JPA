<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tìm kiếm Video</title>
</head>
<body>
<h1>Tìm kiếm Video</h1>
<form action="${pageContext.request.contextPath}/admin/video/search" method="post">
    <label for="title">Nhập tiêu đề video:</label>
    <input type="text" id="title" name="title" required>
    <input type="submit" value="Tìm kiếm">
</form>

<c:if test="${not empty vd}">
    <table border="1" width="100%">
        <tr>
            <th>STT</th>
            <th>Poster</th>
            <th>Video ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Views</th>
            <th>Active</th>
            <th>Category</th>
        </tr>
        <tr class="odd gradeX">
            <td>1</td>
            <td>
                <c:if test="${vd.poster.substring(0, 5) != 'https'}"> 
                    <c:url value="/images?fname=${vd.poster}" var="imgUrl"></c:url>
                    <img height="150" width="200" src="${imgUrl}" />
                </c:if>
                <c:if test="${vd.poster.substring(0, 5) == 'https'}">
                    <img height="150" width="200" src="${vd.poster}" />
                </c:if>
            </td>
            <td>${vd.videoId}</td>
            <td>${vd.title}</td>
            <td>${vd.description}</td>
            <td>${vd.views}</td>
            <td>
                <c:choose>
                    <c:when test="${vd.active}">
                        <span>Hoạt động</span>
                    </c:when>
                    <c:otherwise>
                        <span>Khóa</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${vd.category.categoryId}</td>
        </tr>
    </table>
</c:if>

<c:if test="${empty vd}">
    <h3>Không tìm thấy video nào!</h3>
</c:if>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<a href="${pageContext.request.contextPath}/admin/category/add">Add Category</a>
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Images</th>
        <th>CategoryName</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listcate}" var="cate" varStatus="STT">
        <tr class="odd gradeX">
            <td>${STT.index + 1}</td>
            
            <!-- Kiểm tra xem cate.images có rỗng hay không trước khi sử dụng substring -->
            <c:if test="${not empty cate.images}">
                <c:if test="${cate.images.substring(0, 5) == 'https'}">
                    <c:url value="${cate.images}" var="imgUrl"></c:url>
                </c:if>
                <c:if test="${cate.images.substring(0, 5) != 'https'}">
                    <c:url value="/images?fname=${cate.images}" var="imgUrl"></c:url>
                </c:if>
            </c:if>

            <!-- Hiển thị hình ảnh -->
            <td>
                <c:if test="${not empty imgUrl}">
                    <img height="150" width="200" src="${imgUrl}" />
                    <c:out value="${imgUrl}" />
                </c:if>
            </td>

            <td>${cate.categoryname}</td>
            <td>${cate.status}</td>
            <td>
                <c:choose>
                    <c:when test="${cate.status}">
                        <span>Hoạt động</span>
                    </c:when>
                    <c:otherwise>
                        <span>Khóa</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>">Sửa</a>
                | 
                <a href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

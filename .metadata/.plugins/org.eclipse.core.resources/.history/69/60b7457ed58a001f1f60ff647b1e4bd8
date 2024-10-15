<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<a href="${pageContext.request.contextPath}/admin/video/insert">Add Video</a>

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

    <c:forEach items="${listvd}" var="vd" varStatus="STT">
        <tr class="odd gradeX">
            <td>${STT.index + 1}</td>

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
    </c:forEach>
</table>

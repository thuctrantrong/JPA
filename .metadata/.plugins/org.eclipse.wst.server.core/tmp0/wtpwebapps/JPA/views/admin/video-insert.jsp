<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="${pageContext.request.contextPath}/admin/video/update" method="POST" enctype="multipart/form-data">
    <!-- Trường ẩn để lưu video ID -->
    <input type="hidden" id="videoId" name="videoId" value="${vd.videoId}">

    <!-- Nhập tên video -->
    <label for="vdname">Tên video:</label><br>
    <input type="text" id="vdname" name="vdname" value="${vd.title}" required><br>

    <!-- Nhập mô tả video -->
    <label for="vddescription">Mô tả:</label><br>
    <textarea id="vddescription" name="vddescription" rows="4" cols="50">${vd.description}</textarea><br>

	<label for="categoryID">categoryID:</label><br>
    <input type="text" id="categoryID" name="categoryID" value="${vd.category}" required><br>

    <!-- Hiển thị hình ảnh hiện tại (nếu có) -->
    <label for="images">Hình ảnh:</label><br>
    <c:choose>
        <c:when test="${not empty vd.poster}">
            <c:url value="/image?fname=${vd.poster}" var="posterUrl"></c:url>
            <img height="150" width="200" src="${posterUrl}" alt="vd Image" />
        </c:when>
        <c:otherwise>
            <p>Không có hình ảnh</p>
        </c:otherwise>
    </c:choose>
    <input type="file" id="images" name="images"><br>

    <!-- Nhập trạng thái video (Active hay không) -->
    <label for="status">Kích hoạt:</label><br>
    <input type="checkbox" id="status" name="status" <c:if test="${vd.active}">checked</c:if>><br>

    <!-- Nút submit -->
    <br>
    <input type="submit" value="Cập nhật">
</form>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/video/add"
	method="POST" enctype="multipart/form-data">
	<!-- Trường ẩn để lưu video ID -->
	<input type="hidden" id="videoId" name="videoId" value="${vd.videoId}">

	<!-- Nhập tên video -->
	<label for="vdname">Tên video:</label><br> <input type="text"
		id="vdname" name="vdname" value="${vd.title}" required><br>

	<!-- Nhập mô tả video -->
	<label for="vddescription">Mô tả:</label><br>
	<textarea id="vddescription" name="vddescription" rows="4" cols="50"
		required>${vd.description}</textarea>
	<br>

	<!-- Nhập category ID -->
	<label for="categoryID">Category ID:</label><br> <input
		type="text" id="categoryID" name="categoryID"
		value="${vd.category.categoryId}" required><br> <label
		for="poster">Hình ảnh hiện tại:</label><br>
	<c:if test="${vd.poster.substring(0,5)=='https'}">

		<c:url value="${vd.poster}" var="imgUrl"></c:url>

	</c:if>


	<c:if test="${vd.poster.substring(0,5)!='https'}">


		<c:url value="/images?fname=${vd.poster}" var="imgUrl"></c:url>


	</c:if>



	<img height="150" width="200" src="${imgUrl}" /><br>

	<p>Chọn hình ảnh mới (nếu có):</p>
	<input type="file" id="poster" name="poster"><br>

	<!-- Nhập trạng thái video (Active hay không) -->
	<label for="status">Kích hoạt:</label><br> <input type="checkbox"
		id="status" name="status" <c:if test="${vd.active}">checked</c:if>
		value="true"><br>

	<!-- Nút submit -->
	<br> <input type="submit" value="Cập nhật">
</form>

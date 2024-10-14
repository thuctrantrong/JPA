<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<a href="${pageContext.request.contextPath}/admin/video/insert">Add
	Video</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Title</th>
		<th>poster</th>
		<th>videoId</th>
		<th>description</th>
		<th>views</th>
		<th>active</th>
		<th>category</th>
	</tr>
	<c:forEach items="${listcate}" var="vd" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index + 1}</td>

			<td><c:if test="${vd.poster.substring(0, 5) != 'https'}">
					<c:url value="/image?fname=${vd.poster}" var="posterUrl"></c:url>
					<img height="150" width="200" src="${posterUrl}" />
				</c:if> <c:if test="${vd.poster.substring(0, 5) == 'https'}">
					<img height="150" width="200" src="${vd.poster}" />
				</c:if></td>

			<td>${vd.videoId}</td>
			<th>${vd.title}</th>
			<td>${vd.description}</td>
			<td>${vd.views}</td>
			<td>${vd.category}</td>
			<td><c:if test="${vd.active == true}">
					<span>Hoạt động</span>
				</c:if> <c:if test="${vd.active == false }">
					<span>Khóa</span>
				</c:if></td>
		</tr>
	</c:forEach>
</table>

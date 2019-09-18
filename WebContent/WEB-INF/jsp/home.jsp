<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="h-100">
<head>
<meta charset="UTF-8">
<title>Shopping List / Home</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body class="d-flex flex-column h-100">

	<%@ include file="/WEB-INF/fragment/navigation.jspf"%>

	<div class="container h-100 text-center" style="padding:60px;">
		<div	 style="min-height:100%;display:flex;align-items:center">
			<div class="container">
				<h1 class="display-5">Your tasks</h1>
				<div class="list-group w-100 overflow-auto" style="max-height:60vh">
					<c:forEach items="${taskList}" var="task">
						<div class="list-group-item d-flex flex-column align-items-start">
							<div class="d-flex justify-content-between w-100">
								<b class="display-5" style="font-size:25px;vertical-align:bottom;">${task.title}</b>
								<div class="d-flex">
									<form method="get" action="edit" style="padding-right:10px">
										<input type="hidden" name="inputId" value="${task.id}">
										<button class="btn btn-warning">Edit</button>
									</form>
									<form method="post" action="remove">
										<input type="hidden" name="inputId" value="${task.id}">
										<button type="submit" class="btn btn-danger">Remove</button>
									</form>
								</div>
							</div>
							<p style="margin:0">${task.description}</p>
						</div>
					</c:forEach>
				</div>
				<a href="add" class="list-group-item" style="text-decoration:none">
					<button class="btn btn-lg btn-success btn-block">Add Task</button>
				</a>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/fragment/footer.jspf"%>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>
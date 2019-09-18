<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="h-100">
<head>
<meta charset="UTF-8">
<title>Shopping List / Register</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body class="d-flex flex-column h-100">

	<%@ include file="/WEB-INF/fragment/navigation.jspf"%>

	<div class="container h-100 text-center" style="padding:60px;">
		<div class="jumbutron" style="min-height:100%;display:flex;align-items:center">
			<form class="form-signin" style="max-width:300px;margin: 0 auto;" method="post" action="register">
				<h1 class="h3 mb-3 font-weight-normal">Please register</h1>
				<label for="inputEmail" class="sr-only">Email address</label>
				<input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Email address" required autofocus>
				<label for="inputPassword" class="sr-only">Password</label>
				<input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
				<label for="inputConfirmPassword" class="sr-only">Confirm Password</label>
				<input type="password" id="inputConfirmPassword" name="inputConfirmPassword" class="form-control" placeholder="Confirm Password" required>
				<button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top:10px">Register</button>
			</form>
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
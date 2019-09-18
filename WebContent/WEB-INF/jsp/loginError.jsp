<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
<head>
	<meta charset="UTF-8">
	<title>Shopping List / Login</title>
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		crossorigin="anonymous">
	
</head>
<body class="d-flex flex-column h-100">

	<%@ include file="/WEB-INF/fragment/navigation.jspf"%>

	<div class="container h-100 text-center" style="padding:60px;">
		<div class="jumbutron d-flex flex-column justify-content-center" style="min-height:100%;display:flex;align-items:center">
			<div class="alert alert-danger" style="min-width:300px">
					<strong>Failed to login!</strong>
			</div>
			<form name="loginForm" method="post" action="j_security_check" class="form-signin" style="max-width:300px;margin: 0 auto;">
				<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
				<label for="inputEmail" class="sr-only">Email address</label>
				<input type="email" id="inputEmail" name="j_username" class="form-control" placeholder="Email address" required autofocus>
				<label for="inputPassword" class="sr-only">Password</label>
				<input type="password" id="inputPassword" name="j_password" class="form-control" placeholder="Password" required>
				<button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top:10px">Sign in</button>
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
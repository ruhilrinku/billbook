<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Login page</title>
</head>
<body>
<div class="login">
<h1>bill-book Login</h1>

<form method="post" action="./login">
	<input id="username_or_email" name="username" type="text" placeholder="Username"/>
	<input id="password" name="password" type="password" placeholder="Password"/>
	<input name="commit" type="submit" value="submit" class="btn btn-primary btn-block btn-large" />

</form>

<a href="./register">Register New
			User</a>
<script type="text/javascript">
document.getElementById('username_or_email').focus();
</script>
</div>
</body>
</html>

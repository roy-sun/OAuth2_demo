<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			label{
				width:120px;
				display: inline-block;
			}
			input{
				width:250px;
			}
		</style>
	</head>
	<body>
		<form action="${oAuthPage}" method="post">
			<label>redirect_uri:</label><input type="text" readonly="readonly" name="redirect_uri" value="${redirect_uri}"><br>
		    <label>client_id:</label><input type="text" readonly="readonly" name="client_id" value="${client_id}"><br>
		    <label>response_type:</label><input type="text" readonly="readonly" name="response_type" value="${response_type}"><br>
		    <input type="submit" value="去登录授权页面">
		</form>
	</body>
</html>
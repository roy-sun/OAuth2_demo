<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>oAuth Success</title>
		<script src="https://cdn.bootcss.com/jquery/1.9.0/jquery.min.js"></script>
		<style type="text/css">
			input {
				width:250px;
			}
		</style>
	</head>
	<body>
		<h1>B站页面</h1>
	    <label>获得授权码:</label><input type="text" readonly="readonly" name="response_type" value="<%=request.getParameter("code") %>"><br>
	    <label>获得Token:</label><input type="text" readonly="readonly" id="access_token" name="access_token">&nbsp;&nbsp;有效期<span id="expires_in"></span><br>
	    <button id="getUserInfoBtn">通过Token得到用户信息</button>
		<script type="text/javascript">
			$.ajax({
				type:"post",
				data:{
					code:"<%=request.getParameter("code") %>",
					method:"accessToken"
				},
				url:"ClientServlet",
				success:function(data){
					token = JSON.parse(data);
					$("#access_token").val(token.access_token);
					$("#expires_in").text(token.expires_in);
				},
				error:function(error){
					console.error(error);
					$("#token").val("error");
				}
			})
			$("#getUserInfoBtn").click(function(){
				var access_token = $("#access_token").val();
				if(access_token&&access_token!="error"){
					$.ajax({
						type:"post",
						data:{
							access_token:access_token,
							method:"userInfo"
						},
						url:"ClientServlet",
						success:function(data){
							alert(data);
						},
						error:function(error){
							alert("error");
						}
					})
				}
			});
		</script>
	</body>
</html>
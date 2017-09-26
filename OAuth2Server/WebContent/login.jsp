<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">${error}</div>
<form action="${pageContext.request.contextPath}/authorize" method="post">
    用户名：<input type="text" name="username" value="admin"><br/>
    密码：<input type="password" name="password" value="password"><br/>
    <input type="hidden" name="redirect_uri" value="${pageContext.request.contextPath}/clientPage.jsp">
    <input type="hidden" name="client_id" value="client_id">
    <input type="hidden" name="response_type" value="code">
    <input type="submit" value="登录并且授权">
</form>

</body>
</html>
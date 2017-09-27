<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">${error}</div>
<h1>OAuth Server 登录页面</h1>
<form action="${pageContext.request.contextPath}/authorize" method="post">
    用户名：<input type="text" name="username"><br/>
    密码：<input type="password" name="password"><br/>
    <input type="hidden" name="redirect_uri" value="<%=request.getParameter("redirect_uri")%>">
    <input type="hidden" name="client_id" value="<%=request.getParameter("client_id")%>">
    <input type="hidden" name="response_type" value="<%=request.getParameter("response_type")%>">
    <input type="submit" value="登录并且授权">
    
</form>

</body>
</html>
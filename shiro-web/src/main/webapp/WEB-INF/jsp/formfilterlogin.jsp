<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录(表单拦截器验证登录测试)</title>
    <style>.error {
        color: red;
    }</style>
</head>
<body>
<div class="error">${error }</div>
<form action="${pageContext.request.contextPath}/formfilterlogin" method="post">
    用户名：<input type="text" name="username"><br/>
    密&nbsp;&nbsp;码：<input type="password" name="password"><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
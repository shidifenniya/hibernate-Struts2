<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/10/20
  Time: 下午2:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工登录界面</title>
    <script src="jquery-3.2.1.js" type="text/javascript"></script>
</head>
<body>

<img src="codeImg.action" id="code">

</body>
<script>

    $("#code").click(function () {
        $("#code").attr("src", "${pageContext.request.contextPath}/codeImg.action?s=" + Math.random());
    });

</script>

</html>

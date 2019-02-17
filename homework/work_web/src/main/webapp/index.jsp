<%--
  Created by IntelliJ IDEA.
  User: lc199
  Date: 2019/2/1
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script src="jquery/jquery-1.8.3.min.js">--%>

<%--<script>
    $(function () {
        $.post("/user/findUser",{},function (date) {
            if (date == null){
                $("#no").show();
                $("#yes").hide();
            }else {
                $("#yes").show();
                $("#no").hide();
                $("#username").val("欢迎您，"+date.name)
            }
        })
    })
</script>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/user/login" id="no">登陆</a>

</body>
</html>

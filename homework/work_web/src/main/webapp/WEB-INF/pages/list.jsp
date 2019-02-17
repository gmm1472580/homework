<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!--4.0导入jq-->
    <script src="${pageContext.request.contextPath}/jquery/jquery-1.8.3.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteUser(id) {
            if(confirm("您确定要删除吗？")){
                location.href = "${pageContext.request.contextPath}/user/deleteUser?id="+id;
            }
        }

        $(function () {
            //给删除选中按钮添加单击事件
            document.getElementById("delchange").onclick = function () {
                if (confirm("您确定要删除选中条目吗？")) {

                    var flag = false;
                    //判断是否含有选中条目
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {
                            //有条目被选中
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        //有条目被选中
                        //表单提交
                        document.getElementById("form").submit();
                    }

                }

            }
            /*全选*/
            document.getElementById("firstCb").onclick = function () {
                //获取所有的选中框
                var cbs = document.getElementsByName("uid");
                //遍历
                for (var i = 0; i < cbs.length; i++) {
                    //设置这些状态
                    cbs[i].checked = this.checked;

                }

            }
            //获取全选框
            var firstCb=document.getElementById("firstCb");
            //获取所有的选中框
            var cbs = document.getElementsByName("uid");
            //遍历
            for (var i = 0; i < cbs.length; i++) {
                cbs[i].onclick=function () {
                    //如果有一个没有被选中，则全选框不选中
                    if (this.checked == false) {
                        firstCb.checked = false;
                    }
                }
            }



        })

    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <form action="/user/findAll" method="post">
        姓名<input name="name">
        籍贯<input name="adress">
        邮箱<input name="email">
        <input type="submit" value="查询">
    </form>
    <form id="form" action="${pageContext.request.contextPath}/user/delChange" method="post">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pageBean.list}" var="user" varStatus="id">
            <tr>
                <td><input type="checkbox" name="uid" value="${user.id}"></td>
                <td>${id.index+1}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.qq}</td>
                <td>${user.email}</td>
                <td><a class="btn btn-default btn-sm" href="/user/findById?id=${user.id}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="/user/add">添加联系人</a></td>
            <td colspan="4"><a class="btn btn-primary" href="javascript:void(0);" id="delchange">删除选中</a></td>
        </tr>
    </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li><a href="${pageContext.request.contextPath}/user/findAll?currentPage=${1}&rows=5&name=${param.name}&address=${param.address}&email=${param.email}">首页</a></li>
                <li>
                    <a href="${pageContext.request.contextPath}/user/findAll?currentPage=${pageBean.currentPage-1}&rows=5&name=${param.name}&address=${param.address}&email=${param.email}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pageBean.totalPage}" var="i">
                    <li><a href="${pageContext.request.contextPath}/user/findAll?currentPage=${i}&rows=5&name=${param.name}&address=${param.address}&email=${param.email}">${i}</a></li>
                </c:forEach>
                <li>
                    <a href="${pageContext.request.contextPath}/user/findAll?currentPage=${pageBean.currentPage+1>pageBean.totalPage?pageBean.totalPage : pageBean.currentPage+1}&rows=5&name=${param.name}&address=${param.address}&email=${param.email}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/user/findAll?currentPage=${pageBean.totalPage}&rows=5&name=${param.name}&address=${param.address}&email=${param.email}">尾页</a></li>
                <span style="font-size: 25px;margin-left: 5px;">
                    共${pageBean.totalCount}条记录，共${pageBean.totalPage}页
                </span>

            </ul>
        </nav>


    </div>


</div>


</body>
</html>

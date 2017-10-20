<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/10/20
  Time: 上午10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工列表</title>
    <script src="jquery-3.2.1.js" type="text/javascript"></script>
</head>
<body>

<form action="" method="post">

    部门:
    <select id="department" name="departId">
        <option value="-1">--请选择--</option>
        <s:iterator value="#session.departments" var="depart">
            <option value="${depart.id}">${depart.dname}</option>
        </s:iterator>
    </select>
    职务:
    <select id="post" name="postId">
        <option value="-1">--请选择--</option>
    </select>

    <%--<input type="submit" value="查询">--%>

    <table>
        <thead>
        <tr>
            <th>员工姓名</th>
            <th>所属部门</th>
            <th>所属职务</th>
        </tr>
        </thead>
        <tbody id="list"></tbody>
    </table>


    <%--<table>--%>
        <%--<c:choose>--%>
            <%--<c:when test="${not empty staffList}">--%>

                <%--<tr>--%>
                    <%--<td>员工姓名</td>--%>
                    <%--<td>所属部门</td>--%>
                    <%--<td>所属职务</td>--%>
                <%--</tr>--%>

                <%--<c:forEach items="${staffList}" var="staff">--%>

                    <%--<tr>--%>
                        <%--<td>${staff.sname}</td>--%>
                        <%--<td>${staff.department.dname}</td>--%>
                        <%--<td>${staff.post.pname}</td>--%>
                    <%--</tr>--%>

                <%--</c:forEach>--%>

            <%--</c:when>--%>
        <%--</c:choose>--%>
    <%--</table>--%>
</form>
<script>

    $("#department").change(function () {

        $("#post").empty();

        $("#post").append("<option value='-1'>--请选择--</option>");

        $.post("findPost", "departId=" + $("#department").val(), function (date) {

            var _html = "";

            for (var i = 0; i < date.length; i++) {

                _html += "<option value='" + date[i].id + "'>" + date[i].pname + "</option>"

            }

            $("#post").append(_html);

        })

    });

    $("#department,#post").change(function () {

        $("#list").empty();

        $.post("queryStaff",{

            departId:$("#department").val(),

            postId:$("#post").val()

        },function (date) {

            var _html = "";

            for (var i = 0; i < date.length; i++) {

                _html += "<tr><td>"+date[i].sname+"</td><td>"+date[i].department.dname+"</td><td>"+date[i].post.pname+"</td></tr>";

            }

            $("#list").append(_html);

        })


    })

</script>
</body>
</html>

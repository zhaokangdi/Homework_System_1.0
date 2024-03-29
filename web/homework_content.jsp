<%@ page import="main.java.Model.Homework" %>
<%@ page import="java.util.List" %>
<%@ page import="main.java.Model.Student" %>
<%@ page import="main.java.Jdbc.StudentJdbc" %><%--
  Created by IntelliJ IDEA.
  User: 17301
  Date: 2020/3/14
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业内容</title>
    <link rel="icon" type="image/ico">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/materialdesignicons.min.css" rel="stylesheet">
    <link href="./css/style.min.css" rel="stylesheet">
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <aside class="lyear-layout-sidebar">
            <div id="logo" class="sidebar-header">
                <a style="height: 64px"><img src="./images/logo-sidebar.png"/></a>
            </div>
            <div class="lyear-layout-sidebar-scroll">
                <nav class="sidebar-main">
                    <ul class="nav nav-drawer">
                        <li class="nav-item active"><a href="HomeworkListServlet">作业列表</a></li>
                        <li class="nav-item"><a href="StudentListServlet">学生列表</a></li>
                    </ul>
                </nav>
            </div>
        </aside>

        <header class="lyear-layout-header">
            <nav class="navbar navbar-default">
                <div class="topbar">
                    <div class="topbar-left">
                        <div class="lyear-aside-toggler">
                            <span class="lyear-toggler-bar"></span>
                            <span class="lyear-toggler-bar"></span>
                            <span class="lyear-toggler-bar"></span>
                        </div>
                        <span class="navbar-page-title">学生作业</span>
                    </div>

                    <ul class="topbar-right">
                        <li class="dropdown dropdown-profile">
                            <span><%=session.getAttribute("teacher_name")%></span>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <main class="lyear-layout-content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <form action="SubmitHomeworkServlet" method="post" class="row">
                                    <div class="form-group col-md-12">
                                        <label for="homework_title">作业名称</label>
                                        <input type="text" class="form-control" id="homework_title" name="homework_title" value="<%=request.getParameter("homework_title")%>" readonly />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="teacher_name">学生姓名</label>
                                        <input type="text" class="form-control" id="teacher_name" name="teacher_name" value="<%=request.getParameter("student_name")%>" readonly />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="seo_description">作业内容</label>
                                        <textarea class="form-control" id="seo_description" name="content" rows="5" readonly><%=request.getAttribute("content")%></textarea>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <a type="submit" class="btn btn-default" href="TeacherHomeworkSubmitServlet?homework_title=<%=request.getParameter("homework_title")%>">返 回</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="./js/main.min.js"></script>
</body>
</html>

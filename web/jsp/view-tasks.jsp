<%@ page import="uz.pdp.dao.UserDao" %>
<%@ page import="uz.pdp.dao.TaskDao" %>
<%@ page import="uz.pdp.model.Task" %><%--
  Created by IntelliJ IDEA.
  User: Nurbek Abduraximov
  Date: 2/4/2022
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>List of Tasks</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<style>
    .icons{
        width: 100%;
        height: 200px;
        background-color: black;
        color: white;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0 40px;
    }
    a{
        color: white;
        background-color: white;
        padding: 10px 50px;
        border-radius:10px;
        transition: 0.5s;
    }
    a:hover{
        background-color: gray;
    }
    a:active{
        transform: scale(.98);
    }
    i{
        font-size: 25px;
        color: black;
    }
    .card-body{
        background-color: #c9e879;
        border-radius: 15px;
    }
    body{
        background-color: aquamarine;
    }
    .card{
        background-color: aquamarine;
        border-color: aquamarine;
    }
</style>
<body>


<div class="heading container">
    <h1 style="color: cadetblue">My personal website. To Do Application</h1>
</div>


<div class="container d-flex">
    <div style="margin-top:25px;" class="container">
        <a href="/add-task.html" class="btn btn-success">Add task</a>
    </div>

</div>



<div class="d-flex justify-content-around container">
    <%--    ROW OCHILDI--%>
    <div class="row">
        <%
            int id = 0;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("loginCookie")) {
                    id = UserDao.getUserByUsername(cookie.getValue()).getId();
                    break;
                }
            }
            for (Task allTask : TaskDao.getAllTasks()) {
                if (allTask.getUserId()==id){
                    out.println("<div class=\"col-md-4 mt-4\">\n" +
                            "\n" +
                            "                <div class=\"card\">\n" +
                            "                    <div class=\"card-body\">\n" +
                            "                        <h5 class=\"card-title\">"+allTask.getTitle()+"</h5>\n" +
                            "                        <p class=\"card-text\">"+allTask.getDescription()+"</p>\n" +
                            "                        <p class=\"card-text\">"+allTask.getDeadline()+"</p>\n" +
                            "                        <a href=\"/editTask?id="+allTask.getId()+"\"class=\"btn btn-primary\">Edit</a>\n" +
                            "                        <a href=\"/deleteTask?id="+allTask.getId()+"\"class=\"btn btn-danger\">Delete\n" +
                            "                        </a>\n" +
                            "                    </div>\n" +
                            "                </div>\n" +
                            "\n" +
                            "            </div>");
                }
            }
        %>


<%--        <c:forEach var="task" items="${taskList}">--%>

<%--            <div class="col-md-4 mt-4">--%>

<%--                <div class="card">--%>
<%--                    <div class="card-body">--%>
<%--                        <h5 class="card-title">${task.title}</h5>--%>
<%--                        <p class="card-text">${task.description}</p>--%>
<%--                        <p class="card-text">${task.deadline}</p>--%>
<%--                        <a href="<c:url value="/editTask?id=${task.id}"/>" class="btn btn-primary">Edit</a>--%>
<%--                        <a href="<c:url value="/deleteTask?id=${task.id}"/>" class="btn btn-danger">Delete</a>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>
<%--        </c:forEach>--%>
<%--        &lt;%&ndash;    ROW YOPILDI&ndash;%&gt;--%>

    </div>
</div>




<footer id="footer">
<div class="container" style="margin-top:40px">

    <div class="icons">
        <a href="https://www.linkedin.com/in/nurbek-abdurakhimov-1368b7229/"><i class="fab fa-linkedin"></i></a>
        <a href="https://www.instagram.com/abduraximovnurbek0002/"><i class="fab fa-instagram-square"></i></a>
        <a href="https://github.com/Nurbek21"><i class="fab fa-github-square"></i></a>
        <a href="https://telegram.me/thepro1401"><i class="fab fa-telegram"></i></a>
        <a href="https://mail.google.com/mail/u/0/?tab=rm#inbox"> <i class="fas fa-at"></i></a>
        <a href="https://www.facebook.com/nurbek.abduraximov.37"><i class="fab fa-facebook-square"></i></a>
    </div>
</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>


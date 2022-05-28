<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nurbek
  Date: 2/5/2022
  Time: 5:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Edit Task</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>


<c:set var="selectedTask" value="${task}"/>


<form style="width: 600px; margin: 0 auto;" action="/editTask" method="post">
    <div class="form-group" style="margin-top:30px">
        <label for="exampleInputEmail1">Task title</label>
        <input type="text" class="form-control" name="title" id="exampleInputEmail1"  value="${selectedTask.title}">
        <input type='hidden' name='id' value="${selectedTask.id}"/>
    </div>

    <div class="form-group" style="margin-top:30px">
        <label for="exampleInputPassword1">Title description</label>
        <input type="text" class="form-control" name="description" id="exampleInputPassword1" value="${selectedTask.description}">
    </div>

    <div class="form-group" style="margin-top:30px">
        <label for="inputsm">Deadline:</label>
        <input class="form-control" name="deadline" id="inputsm"  type="datetime-local" value="${selectedTask.deadline}">
    </div>
    <button type="submit" class="btn btn-primary" style="margin-top:30px">Submit</button>
    <a href="/tasks" class="btn btn-danger" style="margin-top:30px; margin-left:15px">Back</a>
</form>

</body>
</html>

package uz.pdp.controller;

import uz.pdp.dao.TaskDao;
import uz.pdp.model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet("/editTask")
public class TaskEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        Task taskById = TaskDao.getTaskById(id);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/edit-task.jsp");
        req.setAttribute("task", taskById);
        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        String newTitle = req.getParameter("title");
        String newDescription = req.getParameter("description");
        String newDeadline = req.getParameter("deadline");

        String oldTitle = "";
        Task taskById = TaskDao.getTaskById(id);

        if (taskById != null) {
            oldTitle = taskById.getTitle();
            TaskDao.editTask(id, newTitle, newDescription, LocalDateTime.parse(newDeadline));
        }

        writer.println("<h2>" + oldTitle + " >>> " + newTitle + "</h2>");
        writer.println("<br> " +
                "<a href=\"/tasks\">Back to task list!</a>");
        writer.close();
    }




}




package uz.pdp.controller;

import uz.pdp.dao.TaskDao;
import uz.pdp.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet("/addTask")
public class TaskAddController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        int userId = 0;
        for (Cookie cookie : req.getCookies()){
            if (cookie.getName().equals("loginCookie")){
                userId = UserDao.getUserByUsername(cookie.getValue()).getId();
                break;
            }
        }
        String newTitle = req.getParameter("title");
        String newDescription = req.getParameter("description");
        boolean newStatus = Boolean.parseBoolean(req.getParameter("active"));

        String newDeadline = req.getParameter("deadline");

        if (newDeadline.length() > 0 && newDeadline != null){
            TaskDao.addNewTasks(newTitle, newDescription, newStatus, LocalDateTime.parse(newDeadline), userId);
            writer.println("<h1 style=\"color: green\">");
            writer.println(newTitle + " successfully added!!!");
            writer.println("</h1>");
        }
        else {
            TaskDao.addNewTasks(newTitle, newDescription, newStatus, userId);
            writer.println("<h1 style=\"color: green\">");
            writer.println(newTitle + " successfully added!!!");
            writer.println("</h1>");
        }

        writer.println("<br> " + "<a href=\"/tasks\">Back to task list!</a>");
        writer.close();

    }

}

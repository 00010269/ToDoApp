package uz.pdp.controller;

import uz.pdp.dao.TaskDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteTask")
public class TaskDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);

        if (TaskDao.deleteTaskById(id)) {

            writer.println("<h1 style = color: red>Successfully deleted!!</h1>");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/tasks");
            requestDispatcher.forward(req, resp);
        }
        else {
            writer.println("<h1 style = color: red>Could not delete!</h1>");
        }

        writer.println("<br> " +
                "<a href=\"/tasks\">Back to task list!</a>");

        writer.close();

    }
}




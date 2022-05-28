package uz.pdp.controller;

import uz.pdp.dao.TaskDao;
import uz.pdp.dao.UserDao;
import uz.pdp.model.Task;
import uz.pdp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TaskController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        Cookie[] cookies = req.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginCookie")){
                    username = cookie.getValue();
                    break;
                }
            }
        }

        User userByUsername = UserDao.getUserByUsername(username);
        if (userByUsername != null){
            List<Task> allTasks = TaskDao.getAllTasks();
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/view-tasks.jsp");
            req.setAttribute("taskList", allTasks);
            requestDispatcher.forward(req, resp);
        }
        else {
            Cookie cookie = new Cookie("cookieLogin","");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            resp.sendRedirect("login.html");
        }

    }
}



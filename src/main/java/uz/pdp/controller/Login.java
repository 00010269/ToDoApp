package uz.pdp.controller;

import uz.pdp.dao.UserDao;
import uz.pdp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        String loggingUser = req.getParameter("logUser");
        String loggingUserPassword = req.getParameter("logUserPass");

        User userByUsername = UserDao.getUserByUsername(loggingUser, loggingUserPassword);

        if (userByUsername!= null){
            Cookie cookie =new Cookie("loginCookie", userByUsername.getUsername());
            cookie.setMaxAge(60*60);
            resp.addCookie(cookie);
            resp.sendRedirect("/tasks");

        }else {
            writer.println("<h1>Login or password invalid!!!</h1>");
        }


    }
}

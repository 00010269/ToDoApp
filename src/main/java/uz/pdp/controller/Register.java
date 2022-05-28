package uz.pdp.controller;

import uz.pdp.dao.UserDao;
import uz.pdp.model.Result;
import uz.pdp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.awt.Color.red;

@WebServlet("/register")
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("fName");
        String lastName = req.getParameter("lName");
        String username = req.getParameter("username");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        if (password1.equals(password2)){

            User user = new User();
            user.setUsername(username);
            user.setFirst_name(firstName);
            user.setLast_name(lastName);
            user.setPassword(password1);

            Result result = UserDao.registerUser(user);
            if (result.isSuccess()){
                writer.println("<h1 color: green> Successfully registered!!<h1>");
            }
            else{
                writer.println("<h1 color= 'red'> Could not register!!<h1>");
            }
        }
        else {
            writer.println("<h1 color= 'red'>Password mismatch</h1>");
        }


    }



}

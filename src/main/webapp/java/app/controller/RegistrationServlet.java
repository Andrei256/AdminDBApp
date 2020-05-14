package app.controller;

import app.model.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean admin = Boolean.parseBoolean(req.getParameter("admin"));

        if(name.isEmpty() | login.isEmpty() | password.isEmpty()){
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
        }
        UsersService usersService = new UsersService();

        if (usersService.getUser(login, password) == null) {
            usersService.add(name, login, password, admin);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }
}

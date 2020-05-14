package app.controller;

import app.model.User;
import app.model.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        UsersService usersService = new UsersService();
        if (session.getAttribute("user") != null) {
            if (((User) session.getAttribute("user")).isAdmin()) {
                getServletContext().getRequestDispatcher("/adminPage.jsp").forward(req, resp);
            } else
                getServletContext().getRequestDispatcher("/userPage.jsp").forward(req, resp);
        }

        if (usersService.getUser(login, password) != null) {
            User user = usersService.getUser(login, password);
            if (user.isAdmin()) {
                session.setAttribute("user", user);
                session.setAttribute("usersList", usersService.getUsersList());
                getServletContext().getRequestDispatcher("/adminPage.jsp").forward(req, resp);
            } else {
                session.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/userPage.jsp").forward(req, resp);
            }
        } else
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

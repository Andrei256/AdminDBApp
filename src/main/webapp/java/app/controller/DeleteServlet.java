package app.controller;

import app.model.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        UsersService usersService = new UsersService();
        usersService.deleteUser(id);
        HttpSession session = req.getSession();
        session.setAttribute("usersList", usersService.getUsersList());
        getServletContext().getRequestDispatcher("/adminPage.jsp").forward(req, resp);
    }
}

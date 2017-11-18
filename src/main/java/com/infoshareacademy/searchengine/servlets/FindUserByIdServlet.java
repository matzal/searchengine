package com.infoshareacademy.searchengine.servlets;

import com.infoshareacademy.searchengine.dao.UsersRepositoryDao;
import com.infoshareacademy.searchengine.dao.UsersRepositoryDaoBean;
import com.infoshareacademy.searchengine.domain.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/find-user-by-id")
public class FindUserByIdServlet extends HttpServlet {

    @EJB
    private UsersRepositoryDao usersRepositoryDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (usersRepositoryDao.getUserById(Integer.valueOf(req.getParameter("id"))) == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        else {

            User user = usersRepositoryDao.getUserById(Integer.valueOf(req.getParameter("id")));
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<body style=\"background-color:orange;\">");
            writer.println("<h1>");
            writer.println(user.getId() + "<br />");
            writer.println(user.getName() + "<br />");
            writer.println(user.getSurname() + "<br />");
            writer.println(user.getLogin() + "<br />");
            writer.println(user.getAge() + "<br />");
            writer.println("</h1>");
            writer.println("</body>");
            writer.println("</html>");
        }

    }
}

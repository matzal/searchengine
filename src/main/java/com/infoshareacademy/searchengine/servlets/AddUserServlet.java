package com.infoshareacademy.searchengine.servlets;

import com.infoshareacademy.searchengine.dao.UsersRepositoryDao;
import com.infoshareacademy.searchengine.domain.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("add-user")
public class AddUserServlet extends HttpServlet {

    @EJB
    private UsersRepositoryDao usersRepositoryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            CheckAllParameters(req, resp);
            CheckIfIdAlreadyExist(req, resp);
            usersRepositoryDao.addUser(createUserFromParameters(req));
//            listAllUsers(resp);
        } catch (SomeParametersMissingException e) {
            e.printStackTrace();
        } catch (IdAlreadyExistException e) {
            e.printStackTrace();
        }
    }

    private void CheckAllParameters(HttpServletRequest req, HttpServletResponse resp) throws SomeParametersMissingException {
        if (someParametersMissing(req)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new SomeParametersMissingException();
        }
    }

    private boolean someParametersMissing(HttpServletRequest req) {
        return !allParametersPresent(req);
    }

    private boolean allParametersPresent(HttpServletRequest req) {
        return ((req.getParameter("id") != null)
                && (req.getParameter("name") != null)
                && (req.getParameter("surname") != null)
                && (req.getParameter("age") != null)
                && (req.getParameter("login") != null));
    }

    private void CheckIfIdAlreadyExist(HttpServletRequest req, HttpServletResponse resp) throws IdAlreadyExistException {
        for (User user : usersRepositoryDao.getUsersList()) {
            if (user.getId() == Integer.valueOf(req.getParameter("id"))) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                throw new IdAlreadyExistException();
            }
        }
    }

    private User createUserFromParameters(HttpServletRequest req) {
        User user = new User();
        user.setId(Integer.valueOf(req.getParameter("id")));
        user.setName(req.getParameter("name"));
        user.setSurname(req.getParameter("surname"));
        user.setLogin(req.getParameter("login"));
        user.setAge(Integer.valueOf(req.getParameter("age")));
        return user;
    }

/*    private void listAllUsers(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<body style=\"background-color:orange;\">");
        for (User user : usersRepositoryDao.getUsersList()) {
            writer.println(user.getId() + "<br />");
            writer.println(user.getName() + "<br />");
            writer.println(user.getSurname() + "<br />");
            writer.println(user.getLogin() + "<br />");
            writer.println(user.getAge() + "<br />");
        }
        writer.println("</body>");
        writer.println("</html>");
    }*/

}





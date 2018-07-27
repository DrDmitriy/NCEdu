package ru.ncedu.dmdrozhzhin.jettyServlet.Servlet;

import ru.ncedu.dmdrozhzhin.jettyServlet.jettyServer.ActionUserProfile;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProcessingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("type");
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String balance = req.getParameter("balance");
        ActionUserProfile profile = new ActionUserProfile(action, login, pass, balance);
        profile.execute();
        String responce = profile.getResponce();
        resp.getWriter().print(responce);

    }
}

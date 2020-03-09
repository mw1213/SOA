package loginSite;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

@WebServlet(name = "WelcomeServlet", urlPatterns = "/procesposts")
public class WelcomeServlet extends HttpServlet {

    Vector<List<String>> posts = new Vector<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        posts.add(Arrays.asList(new String[]{request.getParameter("name"), request.getParameter("email"), request.getParameter("message")}));
        session.setAttribute("posty", posts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("posty.jsp");
        dispatcher.forward(request, response);
    }
}

package loginSite;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    Validate validate = new Validate();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        RequestDispatcher rs;
        if(name.equals("")){
            out.println("No username");
            rs = request.getRequestDispatcher("LoginPage.html");
            rs.include(request, response);
        }
        else if(password.equals("")){
            out.println("No password");
            rs = request.getRequestDispatcher("LoginPage.html");
            rs.include(request, response);
        }else {
            if (validate.checkUser(name, password)) {
                response.sendRedirect(request.getContextPath() + "/posty.jsp");
            } else {
                out.println("Username or Password incorrect");
                rs = request.getRequestDispatcher("LoginPage.html");
                rs.include(request, response);
            }
        }
    }
}
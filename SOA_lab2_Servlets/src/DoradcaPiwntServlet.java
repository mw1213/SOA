import doradcaPiwny.BazaPiw;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DoradcaPiwntServlet", urlPatterns = "/piwko")
public class DoradcaPiwntServlet extends HttpServlet {
    private BazaPiw bazaPiw = new BazaPiw();

    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setAttribute("piwa", bazaPiw.getPiwa(request.getParameter("kolor")));
        RequestDispatcher dispatcher = request.getRequestDispatcher("wynik.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

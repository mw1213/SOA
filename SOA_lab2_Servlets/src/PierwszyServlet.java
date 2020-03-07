import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PierwszyServlet")
public class PierwszyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("imie");
        int age = Integer.parseInt(request.getParameter("wiek"));
        out.println("<html>");
        out.println("<head><title> Pierwszy Servlet</title></head>");
        out.println("<body>");
        out.println("<p>Witaj "+name +" masz "+ age +" lat</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}

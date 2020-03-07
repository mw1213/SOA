import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "LiczySredniaServlet", urlPatterns = "/liczGet")
public class LiczySredniaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title> Liczy z Get</title></head>");
        out.println("<body>");

        try {

            int l1 = Integer.parseInt(request.getParameter("l1"));
            int l2 = Integer.parseInt(request.getParameter("l2"));
            int l3 = Integer.parseInt(request.getParameter("l3"));
            int l4 = Integer.parseInt(request.getParameter("l4"));
            int l5 = Integer.parseInt(request.getParameter("l5"));
            int srednia = (l1 + l2 + l3 + l4 + l5) / 5;
            out.println("<p>Średnia z liczb:" + l1 + "," + l2 + "," + l3 + "," + l4 + "," + l5 + " wynosi: " + srednia + "</p>");
        } catch (Exception e){
            out.println("<p>Nie podałeś samych intów</p>");
        }
        out.println("</body>");
        out.println("</html>");
        out.close();

    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");

        Enumeration en=request.getParameterNames();
        List<Double> elements = new ArrayList<>();
        
        while(en.hasMoreElements())
        {
            Object objOri=en.nextElement();

            String param=(String)objOri;

            String value=request.getParameter(param);
            try{
                Double newVal = Double.parseDouble(value);
                elements.add(newVal);
            } catch (Exception e){
                out.println("IN "+param+" you didn't input a number<br>");
            }
        }
        Collections.sort(elements);
        out.println("Posortowana lista liczb:<br>");
        for (Double el:elements) {
            out.println(el+"<br>");
        }
        
        out.close();
    }
}
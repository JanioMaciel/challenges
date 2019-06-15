
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Registration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String backtouser;
        String qcompany = "no company";
        String qzip;
        String act_request = request.getParameter("action");
        String fileinput = request.getParameter("filepath");
        
        CompanyList Companies = new CompanyList();

        if (act_request.equals("default")) {
            Companies.UploadFile(fileinput);
            request.setAttribute("uploadresult", "Default Data uploaded");
            request.getRequestDispatcher("upload.jsp").forward(request, response);
        
        } else if (act_request.equals("source")) {
            Companies.AddData(fileinput);
            request.setAttribute("uploadresult", "Data updated in DB");
            request.getRequestDispatcher("upload.jsp").forward(request, response);
            
        } else {
            qcompany = request.getParameter("company");
            qzip = request.getParameter("zip");

            backtouser = Companies.findSite(qcompany, qzip);
            request.setAttribute("addresstosite", backtouser);
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }

    }
}



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Show() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<head> <meta http-equiv='refresh' content='2'> </head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<table border='1'>");
		 try{
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = (Connection) DriverManager .getConnection("jdbc:mysql://localhost:3306/test","root","root");
	        PreparedStatement create = (PreparedStatement) conn.prepareStatement("select * from name");

	            ResultSet rs;
	            rs = create.executeQuery();
	            while(rs.next()){
	                String name = rs.getString("feed");
	                out.println("<tr>");
	                out.println("<td>"+name+"</td>");
	                out.println("</tr>");
	            }
	            }
	            catch(Exception e){
	                out.println(e);
	            }
		 out.println("</table>");
		 out.println("</center>");
		 out.println("</body>");
	}

}

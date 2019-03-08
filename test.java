import java.io.*;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name= request.getParameter("name");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try
        {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("driver load");
        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");             
        System.out.println("connection stablist");
        PreparedStatement ps = (PreparedStatement) con.prepareStatement("insert into name values(?);");
        ps.setString(1, name);
        ps.executeUpdate();
        System.out.println("data inserted");
        out.println("Data Inserted");
        con.close();
        System.out.println("connection closed");
        }
        catch(Exception e)
        {
           out.println(e);
        }  
		
	}

}

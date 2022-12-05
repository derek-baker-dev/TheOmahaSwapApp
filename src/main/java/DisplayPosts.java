import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Statement;
// Fetch data
/**
 * Servlet implementation class demo3
 */
@WebServlet("/home")
public class DisplayPosts extends HttpServlet {
    private static final long serialVersionUID = 1 ;

    String dns = "ec2-34-230-41-38.compute-1.amazonaws.com";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayPosts() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String sql;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement statement1 = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        String keyword = request.getParameter("authorName");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Omaha Swap";
        String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor = \"aqua\">\n" + //
            "<header align = \"center\"><h1>" + title + "</h1><button onclick=\"window.location.href='/test-swap/InsertData.html'\" style='height:35; width:100;'>Create New Post</button></header>" +
            "<hr style='border-top:3px solid'>");
        
        out.println();


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dns + ":3306/mysql", "matt", "password");
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            System.out.println("Connection Failed!:\n" + e2.getMessage());
        }
        System.out.println("SUCCESS!!!! You made it, take control your database now! *DisplayPosts*");
        System.out.println("Creating statement...");

        sql = "SELECT * FROM product";
        try {

            statement1 = connection.prepareStatement(sql);
     
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {

            rs = statement1.executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        
        try {
            while (rs.next()) {
                //Retrieve by column name
            	int idRes = rs.getInt("pk");
            	String prodNameRes = rs.getString("product_name");
				String postDateRes = rs.getString("posting_date");
				String prodDescRes = rs.getString("product_desc");
				String prodImgRes = rs.getString("product_image");
				String posterCntctRes = rs.getString("poster_contact");
				String posterNameRes = rs.getString("poster_name");
				
				out.println("<div style='padding-left:35%; padding-right:35%;'>");
				out.println("<h2>" + prodNameRes + "</h2><p>" + postDateRes + "</p><br>");
				out.println("<p>" + prodDescRes + "</p><br>");
				if (prodImgRes.contains("http")) {
					out.println("<img src='" + prodImgRes + "' width='500' height='500'>");
				} else {
					out.println("<p>No image available</p>");
				}
				out.println("<h3>Contact Info:</h3><p>" + posterNameRes + ": " + posterCntctRes + "</p>");
				out.println("<form action='DeletePostDetails'><input type='hidden' value=" + idRes + " name='primKey'><input type='submit' value='Delete'/><form>");
				out.println("</div><hr style='border-top:3px solid'>");
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
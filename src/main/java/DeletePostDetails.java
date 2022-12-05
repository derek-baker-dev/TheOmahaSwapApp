import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Fetch data
/**
 * Servlet implementation class demo3
 */
@WebServlet("/DeletePostDetails")
public class DeletePostDetails extends HttpServlet {
	private static final long serialVersionUID = 1;

	String dns = "ec2-34-230-41-38.compute-1.amazonaws.com";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletePostDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sql;
		Connection connection = null;
		Statement statement = null;
		PreparedStatement statement1 = null;
		String primKey = request.getParameter("primKey");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Page Loading...";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

		out.println(docType + //
				"<html>\n" + //
				"<head><title>" + title + "</title></head>\n" + //
				"<body onload=\"setTimeout(function() { document.redirectForm.submit() }, 5000)\" bgcolor = \"##CCCCFF\">\n");
		out.println("<h1>Loading page, please bear with us :) </h1>");
		out.println("<img src='https://www.bearsmart.com/wp-content/uploads/2015/01/bear-look.jpg'>");

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
		System.out.println("SUCCESS!!!! You made it, take control your database now! *DeletePostDetails*");
		System.out.println("Creating statement...");

		sql = "DELETE FROM product WHERE pk = ?";
		// String otherSql = "create table EMP_INFO (EMPId int AUTO_INCREMENT, EmpName
		// varchar(255), Email varchar(255), 'Phone Number' varchar(255))";

		try {

			statement1 = connection.prepareStatement(sql);
			String prodNameVal = primKey;
			statement1.setString(1, prodNameVal);

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			statement1.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		out.println("<form action='home' method=\"post\" name=\"redirectForm\">\r\n"
				+ "</form>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
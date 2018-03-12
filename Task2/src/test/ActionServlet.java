package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  * Servlet implementation class ActionServlet  
 */

public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActionServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/persondata", "root", "root");
			Statement stmt = con.createStatement();
			String keyWord = request.getParameter("keyWord");

			ResultSet rs = stmt.executeQuery("SELECT * from persondata\n" + "WHERE (name LIKE '%" + keyWord + "%' OR\n"
					+ "       address LIKE '%" + keyWord + "%' OR\n" + "       phno LIKE '%" + keyWord + "%' OR\n"
					+ "       salary LIKE '%" + keyWord + "%' OR\n" + "       pension LIKE '%" + keyWord + "%');");

			if (!rs.isBeforeFirst()) { // check if ResultSet is null
				response.getWriter().write("No match found");
			}
			while (rs.next()) {

				response.getWriter()
						.write("Name: " + rs.getString(1) + ";  " + "Address: " + rs.getString(2) + ";  " + "Phno: "
								+ rs.getString(3) + ";  " + "Salary: " + rs.getInt(4) + ";  " + "Pension: "
								+ rs.getInt(5) + ";  ");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}

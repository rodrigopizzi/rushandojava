package br.com.r2dev.r2genda.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection("jdbc:oracle:thin:@10.7.0.108:1527:xe", "martinez", "vfktd0103");

			ps = con.prepareStatement("select *" + 
					"  from usuario u" + 
					" where u.usuario = ?" + 
					"   and u.senha = ?");
			ps.setString(1, usuario);
			ps.setString(2, senha);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				request.getSession().setAttribute("usuario", usuario);
				
				String contextPath = request.getContextPath();
				response.sendRedirect(contextPath + "/index.jsp");
				return;
			}
			
			throw new Exception("Usuário ou senha inválidos.");
			
		} catch (Exception e) {
			e.printStackTrace();
		
			request.setAttribute("erro", e.getMessage());
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}

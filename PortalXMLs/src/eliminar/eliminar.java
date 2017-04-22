package eliminar;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conexion.ConexionSQL;
import java.sql.*;

/**
 * Servlet implementation class Eliminar
 */
@WebServlet(name="/eliminar")
public class eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public eliminar() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html ;charset=UTF-8");
		try (PrintWriter pw = response.getWriter()) {
			
			int ID = Integer.parseInt(request.getParameter("ID"));
			String nomp = request.getParameter("nomp");

		    Connection con=ConexionSQL.getConexion();
			//ResultSet rs=null;		
			Statement st;
			
			try{
				
				st=con.createStatement();
				
				String sql = "DELETE Documento WHERE ID='"+ ID + "'";
				
				st.executeUpdate(sql);
				
				request.getRequestDispatcher("documentosprov.jsp").forward(request, response);
				//RequestDispatcher disp=request.getRequestDispatcher("documentosprov.jsp");
				//disp.forward(request, response);
				//return;
				HttpSession session = request.getSession();
				session.setAttribute("cprov", nomp);
				response.sendRedirect("documentosprov.jsp");

				st.close();
				con.close();

			} catch(Exception e){
				pw.print(e + "");
			}
			}
		
		
		
	}

}

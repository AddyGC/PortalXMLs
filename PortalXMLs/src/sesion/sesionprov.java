
package sesion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import conexion.ConexionSQL;
//import java.sql.*;
import conexion.ControlLog;
import sentenciasSQL.BuscarProv;

//import sentenciasSQL.buscaprov;
//import sesion.provbuscar;

@WebServlet("/sesionprov")
public class sesionprov extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int cantidad;
       
    
    public sesionprov() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		session.removeAttribute("cprov");
		response.sendRedirect("index.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html ;charset=UTF-8");
		try (PrintWriter pw = response.getWriter()) {
		
			//Declarar variable  y obtener datos del formulario
		
			String nomp = request.getParameter("nombreprov");		
			String passp = request.getParameter("passprov");
											
			//Crearemos una lista de errores.
			List<String> errorList = new LinkedList<String>();
			
			if(nomp.length() == 0)
			{
				errorList.add("Usuario");
			}
			
			if(passp.length() == 0)
			{
				errorList.add("Contraseña");
			}
			
			request.setAttribute("Error", errorList);
			
			System.out.println(errorList);
			
			
			if (!errorList.isEmpty() )
			{
				RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
				disp.forward(request, response);
				return;
				
			}
		
			
			// Numero de intentos iniciar.
			ControlLog cl = new ControlLog();
			cantidad = cl.getCantidad();
			
			//String resp=new BuscarProv(nomp, passp).BuscaProv();
			System.out.println("serv" +nomp +  " " + passp);
			BuscarProv buscarprov = new BuscarProv(nomp, passp);
			request.setAttribute("nombreprov", buscarprov);
			//request.setAttribute("passprov", buscarprov);
			int a = buscarprov.Busqueda();
			
			
			
			if( a == 1)
			{
				//response.sendRedirect("documentosprov.jsp");
				//cl.iniciar();
				
				//request.setAttribute("cprov", nomp);
//				//request.setAttribute("dpassprov", passp);
				//RequestDispatcher disp = request.getRequestDispatcher("documentosprov.jsp");
				//disp.forward(request, response);
				//return;
				HttpSession session = request.getSession();
				session.setAttribute("cprov", nomp);
				//request.getSession().setAttribute("cprov", nomp); 
				response.sendRedirect("documentosprov.jsp"); 
				
			}else{
				
				/*if(cantidad >= 3)
				{
					response.sendRedirect("https://www.google.com/");//("index.jsp");
					cl.iniciar();
				} */
				
			//response.sendRedirect("index.jsp");
				
			request.setAttribute("Error", "Usuario y/o constraseña invalido");
				
			RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
			disp.forward(request, response);
			return;
				
				/*pw.println("<form id=\"fomr1\" name=\"form1\" method=\"post\" action=\"index.jsp\" >");
				pw.println("Usuario o password incorrecto <br> le quedan" + (3-cantidad));
				pw.println("<input type=\"submit\" name=\"ingresar\" id=\"ingresar\" value=\"Ingresar\" >");
				pw.println("</form>");*/
				
			}
						
		
			//Confirmar datos con BD
			
			//Connection con = ConexionSQL.getConexion();
			
			
			
			
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
				
		
	}


}

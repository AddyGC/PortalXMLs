package registros;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.io.*;
import javax.servlet.RequestDispatcher;


@WebServlet(name = "registrar.do", urlPatterns = { "/registrar.do" })
public class registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public registrar() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html ;charset=UTF-8");
		try (PrintWriter pw = response.getWriter()) {
			
			String nombre = request.getParameter("nombreuser");
			String rfc = request.getParameter("rfc");
			String direccion = request.getParameter("direccion");
			String num = request.getParameter("num");
			String numint = request.getParameter("numint");
			String colonia = request.getParameter("colonia");
			String poblacion = request.getParameter("poblacion");
			String estado = request.getParameter("estado");
			String pais = request.getParameter("pais");
			String cp = request.getParameter("cp");
			String telefono = request.getParameter("telefono");
			String contacto = request.getParameter("contacto");
			String email = request.getParameter("email");
			String passprov = request.getParameter("passprov");
			String confirmarpassprov = request.getParameter("confirmarpassprov");
			
			
			List<String> errorList = new LinkedList<String>();
			
			if(nombre.length() == 0)
			{
				errorList.add("Nombre");
			}
			
			if(rfc.length() == 0)
			{
				errorList.add("Rfc");
			}
			if(direccion.length() == 0)
			{
				errorList.add("Direccion");
			}
			if(num.length() == 0)
			{
				errorList.add("Número");
			}
			if(numint.length() == 0)
			{
				errorList.add("Número interior");
			}
			if(colonia.length() == 0)
			{
				errorList.add("Colonia");
			}
			if(poblacion.length() == 0)
			{
				errorList.add("Poblacion");
			}
			if(estado.length() == 0)
			{
				errorList.add("Estado");
			}
			if(pais.length() == 0)
			{
				errorList.add("Pais");
			}
			if(cp.length() == 0)
			{
				errorList.add("Codigo Postal");
			}
			if(telefono.length() == 0)
			{
				errorList.add("Telefono");
			}
			if(contacto.length() == 0)
			{
				errorList.add("Contacto");
			}if(email.length() == 0)
			{
				errorList.add("Correo electronico");
			}
			if(passprov.length() == 0)
			{
				errorList.add("Contraseña");
			}
			if(confirmarpassprov.length() == 0)
			{
				errorList.add("Confirmar contraseña");
			}
			
			
			
			request.setAttribute("Error", errorList);
			
			if(!errorList.isEmpty())
			{
				RequestDispatcher disp = request.getRequestDispatcher("regisususario.jsp");
				disp.forward(request, response);
				return;
			}
			
			/*if(passprov != confirmarpassprov){
				
				request.getSession().setAttribute("Error2", "Las constraseñas no coinciden");
				
				RequestDispatcher disp = request.getRequestDispatcher("regisususario.jsp");
				disp.forward(request, response);
				return;
			}*/
			
			/*if (!(passprov != confirmarpassprov)){
				
				//request.setAttribute("Error", "Las contraseñas no coincide, por favor vuelva a registrar");
				
				//RequestDispatcher disp = request.getRequestDispatcher("regisususario.jsp");
				//disp.forward(request, response);
				//return;
				
				request.getSession().setAttribute("Error2", "Las contraseñas no coincide, por favor vuelva a registrar"); 
				response.sendRedirect("regisususario.jsp"); 
				
				
			}*/
			
			
			Classregprov uservalida= new Classregprov(nombre, rfc, direccion, num, numint, colonia,
					poblacion, estado, pais, cp, telefono, contacto, email,
					passprov, confirmarpassprov);
			request.setAttribute("nombre", uservalida);	
			int b = uservalida.uservalida();
			
			if (b == 0)
			{
				
				request.getSession().setAttribute("Error3", "Ya existe un usuario con esta clave"); 
				response.sendRedirect("regisususario.jsp"); 				
				return;
			} else {	
				
				Classregprov userprov= new Classregprov(nombre, rfc, direccion, num, numint, colonia,
						poblacion, estado, pais, cp, telefono, contacto, email,
						passprov, confirmarpassprov);
				request.setAttribute("nombre", userprov);	
				int a = userprov.InsertUsuario();
				
				
				if (a > 0)
				{
					
					request.getSession().setAttribute("nomclave", rfc); 
					request.getSession().setAttribute("nomtipo", "proveedor");
					response.sendRedirect("confirmarreg.jsp"); 
					
				}  else if(a == 0){

					/*RequestDispatcher disp = request.getRequestDispatcher("E.jsp");
					disp.forward(request, response);
					return;*/
					//request.getSession().setAttribute("Error3", "Ya existe un usuario con es clave"); 
					//response.sendRedirect("regisususario.jsp"); 
			     }
			
			}
			
		} catch (Exception e) {	
			
		} // fin catch
	}

}

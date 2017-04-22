package registros;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import javax.servlet.RequestDispatcher;


@WebServlet(name = "registraruser.do", urlPatterns = { "/registraruser.do" })
public class registraruser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final static Logger LOGGER = 
            Logger.getLogger(registraruser.class.getCanonicalName());
   
    
    public registraruser() {
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
			
			String nombreclave = request.getParameter("claveuseradmin");
			String nombre = request.getParameter("nomuseradmin");
			String passu = request.getParameter("passuser");
			String cpassu = request.getParameter("confirmarpassuser");
			
			
			List<String> errorList = new LinkedList<String>();
			
			if(nombreclave.length() == 0)
			{
				errorList.add("Usuario");
			}
			
			if(nombre.length() == 0)
			{
				errorList.add("Nombre");
			}
			if(passu.length() == 0)
			{
				errorList.add("Contraseña");
			}
			if(cpassu.length() == 0)
			{
				errorList.add("Confirmar contraseña");
			}	
			
			
			request.setAttribute("Error", errorList);
			
			if(!errorList.isEmpty())
			{
				RequestDispatcher disp = request.getRequestDispatcher("reguseradmin.jsp");
				disp.forward(request, response);
				return;
			}
			
			/*if (passu != cpassu){
								
				request.setAttribute("2Error", "Las contraseñas no coincide, por favor vuelva a registrar");
				
				RequestDispatcher disp = request.getRequestDispatcher("reguseradmin.jsp");
				disp.forward(request, response);
				return;
				
				//request.getSession().setAttribute("Error2", "Las contraseñas no coincide, por favor vuelva a registrar"); 
				//response.sendRedirect("reguseradmin.jsp"); 
				//return;
			} */
			
			//else {
				
				Classreguser validauser= new Classreguser(nombreclave, nombre, passu, cpassu);
				request.setAttribute("nombre", validauser);	
				int b = validauser.ValidaUser();
				
				LOGGER.log(Level.INFO, "validar usuario", 
		                new Object[]{b});

				
				if ( b == 0){
					
					request.getSession().setAttribute("Error3", "Ya existe un usuario con esta clave"); 
					response.sendRedirect("reguseradmin.jsp"); 				
					return;
				}else{
					
					Classreguser userclve= new Classreguser(nombreclave, nombre, passu, cpassu);
					request.setAttribute("nombre", userclve);	
					int a = userclve.InsertUser();
					
					if (a > 0)
					{
						/*RequestDispatcher disp = request.getRequestDispatcher("confirmarreg.jsp");
						disp.forward(request, response);
						return;*/
						request.getSession().setAttribute("nomclave", nombreclave); 
						request.getSession().setAttribute("nomtipo", "useradmin");
						response.sendRedirect("confirmarreg.jsp"); 
						
					} else if(a == 0)
					{
						/*request.setAttribute("Error3", "Ya existe un usuario con es clave");
						
						RequestDispatcher disp = request.getRequestDispatcher("reguseradmin.jsp");
						disp.forward(request, response);
						return;*/
						
						//request.getSession().setAttribute("Error3", "Ya existe un usuario con es clave"); 
						//response.sendRedirect("reguseradmin.jsp"); 

					}
					
				}
				
			
		} catch (Exception e) {
			
			
		} // fin catch
	}

}

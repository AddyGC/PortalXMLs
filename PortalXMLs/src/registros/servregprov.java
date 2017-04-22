package registros;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registros.servregprov;


@WebServlet(name = "servregprov.do", urlPatterns = { "/servregprov.do" })
public class servregprov extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public servregprov() {
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
		
		/*response.setContentType("text/html ;charset=UTF-8");
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
			String passprovd = request.getParameter("passprovd");
			
			
			List errorList = new LinkedList();
			
			if(nombre.length() == 0)
			{
				errorList.add("nombre");
			}
			if(rfc.length() == 0)
			{
				errorList.add("rfc");
			}
			if(direccion.length() == 0)
			{
				errorList.add("direccion");
			}
			if(num.length() == 0)
			{
				errorList.add("num");
			}
			if(numint.length() == 0)
			{
				errorList.add("numint");
			}
			if(colonia.length() == 0)
			{
				errorList.add("colonia");
			}
			if(poblacion.length() == 0)
			{
				errorList.add("poblacion");
			}
			if(estado.length() == 0)
			{
				errorList.add("estado");
			}
			if(pais.length() == 0)
			{
				errorList.add("pais");
			}
			if(cp.length() == 0)
			{
				errorList.add("cp");
			}
			if(telefono.length() == 0)
			{
				errorList.add("telefono");
			}
			if(contacto.length() == 0)
			{
				errorList.add("contacto");
			}
			if(email.length() == 0)
			{
				errorList.add("email");
			}
			if(passprov.length() == 0)
			{
				errorList.add("passprov");
			}
			if(passprovd.length() == 0)
			{
				errorList.add("passprov");
			}
			
			request.setAttribute("Error", errorList);
			
			if(!errorList.isEmpty())
			{
				RequestDispatcher disp = request.getRequestDispatcher("regiusuario");
				disp.forward(request, response);
				return;
			}
			
			
			
			
			
			
			
		}catch (Exception e) {
			
		
		}*/
	}

}

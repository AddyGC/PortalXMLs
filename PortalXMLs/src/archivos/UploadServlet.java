package archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 0. obtiene la referencia a un objeto del logger
	private final static Logger LOGGER = 
            Logger.getLogger(UploadServlet.class.getCanonicalName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Procesa 
		response.setStatus(405);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Error 405. Método no permitido.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1. Obtener archivo y parámetro para path de destino
		
	    final String path = this.getServletContext().getInitParameter("pathDestino");
	    final Part filePart = request.getPart("file");
	    
	    // 2. Obtiene nombre del archivo
	    final String fileName = filePart.getSubmittedFileName();
	    
	    // 3. Define Streams para salida y entrada
	    final PrintWriter writer = response.getWriter();
    	
	    // 4. Crea FileOutputStream con el path, separador 
    	// de directorio y nombre del archivo
        // Obtiene el archivo transferido
	    try (OutputStream out = new FileOutputStream(new File(path + File.separator + fileName)); 
	    		InputStream filecontent = filePart.getInputStream();) {

	        // 5. Escribe el contenido en el FileOutputStream
	        int read = 0;
	        final byte[] bytes = new byte[1024];
	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }

	        // 6. Agrega al cuerpo de la respuesta una confirmación
	        //AGC/////writer.println(String.format("Archivo %s (%d Bytes) creado exitosamente en %s", fileName, filePart.getSize(), path));
	        
	      //BD
	    	BDupload filerec= new BDupload(fileName, path);
	    	request.setAttribute("fileName", fileName);	
	    	request.setAttribute("path", path);
			int a = filerec.InsertDoc();  // obtenemos el valor del ID del documento registrado en una tabla temporal
			
			
			
			if(a > 0)
			{
				String rfc = request.getParameter("rfc");	
				
				validaXML valXML = new validaXML(a, rfc);				
				request.setAttribute("IDxml", valXML);
				request.setAttribute("rfc", valXML);
				String b =  valXML.lecXML(); // obtenemos el resultado  de la validacion del documento XML (regitro correcto, rfc no coincide con el documento, nombre no sea similar a documento registrado, folio fiscal no es repititvo)
				
				if( b == "El documento no corresponde al proveedor logueado")
				{
					//request.getSession().setAttribute("msj", "El documento no corresponde al proveedor logueado");
					//request.setAttribute("msj",b);
					//response.sendRedirect("subirarchivo.jsp");
					request.getSession().setAttribute("msj", b);
					response.sendRedirect("subirarchivo.jsp");
			
					
				}else if(b == "Ya existe un documento con el mismo nombre")
				{
					request.getSession().setAttribute("msj", b); 
					response.sendRedirect("subirarchivo.jsp");  		
					
				} else if (b == "Ya existe un documento con el mismo Folio Fiscal"){
					
					request.getSession().setAttribute("msj", b); 
					response.sendRedirect("subirarchivo.jsp");  		
					
					}else{
					
						request.getSession().setAttribute("cprov", rfc); 
						response.sendRedirect("documentosprov.jsp");  										
					}
				        
				} else{
				
					a = 0;
			}
			
			
	        
	        LOGGER.log(Level.INFO, "Subiendo archivo {0} y será almacenado en {1}", 
	                new Object[]{fileName, path});
	        
	    } catch (FileNotFoundException ex) {
	        writer.println("<br/> ERROR: " + ex.getMessage());
	        LOGGER.log(Level.SEVERE, "Prolemas durante la subida de archivo. Error: {0}", 
	                new Object[]{ex.getMessage()});
	    } 
	}
}

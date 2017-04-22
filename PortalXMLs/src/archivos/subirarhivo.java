package archivos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;
import java.util.logging.Level;


@WebServlet("/subirarhivo")
public class subirarhivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//****
	private final static Logger LOGGER = 
            Logger.getLogger(subirarhivo.class.getCanonicalName());
   
  
    public subirarhivo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/*response.setStatus(405);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Error 405. Método no permitido.");*/
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
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
	        writer.println(String.format("Archivo %s (%d Bytes) creado exitosamente en %s", fileName, filePart.getSize(), path));
	        
	        LOGGER.log(Level.INFO, "Subiendo archivo {0} y será almacenado en {1}", 
	                new Object[]{fileName, path});
	    } catch (FileNotFoundException ex) {
	        writer.println("<br/> ERROR: " + ex.getMessage());
	        LOGGER.log(Level.SEVERE, "Prolemas durante la subida de archivo. Error: {0}", 
	                new Object[]{ex.getMessage()});
	    } 
	}
}

package archivos;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*;
//import java.io.FileWriter;

import conexion.ConexionSQL;

public class BDupload {
	
	public  String fileName, path;

	public final String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public final String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BDupload(final String fileName, final String path) {
		super();
		this.fileName = fileName;
		this.path = path;
	}
	
	
	
	
	public int InsertDoc(){
		
		int a=0;
		String s=null;
		
		try{
						
			//lectura de archivo
			String rutaarchivo=(path + fileName);
			
			System.out.println(rutaarchivo);///
			
			try{
				
				BufferedReader in =
                        new BufferedReader(new FileReader(rutaarchivo));
				
				//String s;
				
				try{
					
					s = in.readLine();
					
					while (s != null)
                    {
                                             
                        //String getimpuesto(s);
                        
                        //Imprimir 
						System.out.println("texto: " +  s);
                         s = in.readLine();   
                         
                         String Cadena = s;
                         String SubCadena = Cadena.substring(0,17);
                         
                         System.out.println("texto despues de while: " +  SubCadena +"....");
                         
                         //if(SubCadena == "<cfdi:Comprobante"){
                        	 
                        	Connection con=ConexionSQL.getConexion();
         					System.out.println("despues conexion  " +  con +" seccion BDupload");
         					System.out.println(fileName + "   " + path);
         					
         					//iniciarmos conexion a la BD
         					Statement st;
         					PreparedStatement ps;
         					
         					//Ejecuta valores
         					st=con.createStatement();
         					
         					System.out.println("despues createStatement  " +  con + "  st" + st);
         					
         					try{
         						
         					    
         						System.out.println("antes de insert  " +  s);
         						System.out.println("antes de insert  " +  rutaarchivo);
         						
         						
         						//String valor1 = s.getElementsByTagName("rfc").item(0).getText;// Content();
         						
         						
         						ps = con.prepareStatement("INSERT INTO TempDocumento (Documento, NombreDocumento, Ruta)  "
         														+"  VALUES ( ? , ?, ? ) ");
         						ps.setString(1, s);
         						ps.setString(2, fileName);
         						ps.setString(3, rutaarchivo);    						
         						
         						a=ps.executeUpdate();
         						
         					    //Obtenemos el ID
         						ResultSet res=st.executeQuery("Select @@IDENTITY as insertado");
         						
         						int id = 0;
         						
         						while(res.next()){
         							
         							id= res.getInt("insertado");
             						System.out.println("ID insertdo " + id);
             						             						      							
         						}
      						             						
         						a = id;
         						
         						System.out.println("seccion insertar" + "  st" + st);
         					}catch (SQLException e){
         						
         						System.out.println("no se pudo insertar");
         					}                        	 
                        	 
                         //}// fin de if
                    }  // fin de while		
				}finally {
                    in.close();
					
				}
				
				
			}catch(Exception e){
				
				
			}
			
		}catch (Exception e ) {
			
			a=0;
		}
		
		
		return a;
		
	} //insertodc


} //class







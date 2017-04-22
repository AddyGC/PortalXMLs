package archivos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;

//import javax.lang.model.util.Types;

import conexion.ConexionSQL;

public class validaXML {

		public int IDxml;
		public String rfc;

		
		
		
		public int getIDxml() {
			return IDxml;
		}




		public void setIDxml(int iDxml) {
			IDxml = iDxml;
		}




		public String getRfc() {
			return rfc;
		}




		public void setRfc(String rfc) {
			this.rfc = rfc;
		}



		

		public validaXML(int iDxml, String rfc) {
			super();
			IDxml = iDxml;
			this.rfc = rfc;
		}



		

		public String lecXML(){
		//public int validaXML(int IDxml, String rfc)
		   {
			
			String b=null;
			
			System.out.println("seccion validad xml");
			System.out.println(IDxml);
			System.out.println(rfc);
			
			//String resultado=null;
			// validamos que sea el RFC del documento sea igual igual al RFC del proveedor
			
			Connection con=ConexionSQL.getConexion();
			
			//con.setAutoCommit(false);
			
			try {
				
				// Obtenemos el RFC
				CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call xpLeeXML(?,?,?) }");
				
				prcProcedimientoAlmacenado.setInt("IDDocumento", IDxml);
				prcProcedimientoAlmacenado.setString("Campo", "EmisorRFC");
				//prcProcedimientoAlmacenado.setString("Resultado", null);
				prcProcedimientoAlmacenado.registerOutParameter(3, Types.VARCHAR);
				
				
				// ejecutar el SP	
				prcProcedimientoAlmacenado.execute();
				
				// devuelve el valor del parametro de salida del procedimiento
				String resultadorfc = prcProcedimientoAlmacenado.getString( 3 );
				
				System.out.println("resultado procedimiento " + resultadorfc  +  "  "+ rfc );
				
				if (resultadorfc.equals(rfc))
				{			
					System.out.println("El documento si corresponde al proveedor loggeado");
					
					//Obtenemos valores para llenar la tabla documento
					
					String rutaa= null;
					String doc=null;
					String docN=null;
					String docd=null;
					String docuD=null;
					
						//Obtenemos Documento
					
							// Obtenemos el nombre del documento que se este importando
								Statement stN;
								ResultSet rsN;
								
								String sqlN="Select NombreDocumento From TempDocumento Where ID= '" + IDxml+ "'";
														
								stN=con.createStatement();
								rsN = stN.executeQuery(sqlN);
								
								while (rsN.next()) 
								{
									docN=rsN.getString(1);									
									System.out.println("El documento (seccion while) " + docN);
								}
								
							// Buscamos si existe el documento registrado en la tabla oficial "Documento"
								Statement std;
								ResultSet rsd;
								
								String sqld="Select NombreDocumento From Documento Where NombreDocumento= '" + docN + "'";
														
								std=con.createStatement();
								rsd = std.executeQuery(sqld);
								
								System.out.println("... " + rsd);
								
								while (rsd.next()) 
								{
									docd=rsd.getString(1);
									System.out.println("El documento.nombredocumento (seccion while) " );
									System.out.println(docd );
								}
								
								if (  docd == null) {
									
									//Obtenemos UUID´s  y validamos
										//Obtenemos el UUID del documento en proceso
											CallableStatement pauuid = con.prepareCall("{ call xpLeeXML(?,?,?) }");
											
											pauuid.setInt("IDDocumento", IDxml);
											pauuid.setString("Campo", "UUID");
											//prcProcedimientoAlmacenado.setString("Resultado", null);
											pauuid.registerOutParameter(3, Types.VARCHAR);
											
											
											// ejecutar el SP	
											pauuid.execute();
											
											// devuelve el valor del parametro de salida del procedimiento
											String resultadouuid = pauuid.getString( 3 );
											
									
										    //Obtenemos el UUID en tabla documento, si es que existe
											Statement stuD;
											ResultSet rsuD;
											
											String sqluD="Select UUID From Documento Where UUID= '" + resultadouuid + "'";
																	
											stuD=con.createStatement();
											rsuD = stuD.executeQuery(sqluD);
											
											while (rsuD.next()) 
											{
												//docd=Integer.parseInt(rsd.getString(1));
												docuD=rsuD.getString(1);
											}
											
											// validamos
											if ( docuD == null) {
												
												// Obtenemos el dato "documento" del documento que se este importando
												Statement st;
												ResultSet rs;
												
												String sql="Select Documento From TempDocumento Where ID= '" + IDxml+ "'";
																		
												st=con.createStatement();
												rs = st.executeQuery(sql);
												
												while (rs.next()) 
												{
													doc=rs.getString(1);
												}
												
												//Obtenemos ruta
												Statement str;
												ResultSet rsr;
												
												String sqlr="Select Ruta From TempDocumento Where ID= '" + IDxml+ "'";
																		
												str=con.createStatement();
												rsr = str.executeQuery(sqlr);
												
												while (rsr.next()) 
												{
													rutaa=rsr.getString(1);
												}
										
											    //Obtenemos importe
												CallableStatement pai = con.prepareCall("{ call xpLeeXML(?,?,?) }");
												
												pai.setInt("IDDocumento", IDxml);
												pai.setString("Campo", "Importe");
												//prcProcedimientoAlmacenado.setString("Resultado", null);
												pai.registerOutParameter(3, Types.VARCHAR);
												
												
												// ejecutar el SP	
												pai.execute();
												
												// devuelve el valor del parametro de salida del procedimiento
												String resultadoimp = pai.getString( 3 );
										
											    //Obtenemos fechatimbrrado
												CallableStatement paft = con.prepareCall("{ call xpLeeXML(?,?,?) }");
												
												paft.setInt("IDDocumento", IDxml);
												paft.setString("Campo", "FechaTimbrado");
												//prcProcedimientoAlmacenado.setString("Resultado", null);
												paft.registerOutParameter(3, Types.VARCHAR);
												
												
												// ejecutar el SP	
												paft.execute();
												
												// devuelve el valor del parametro de salida del procedimiento
												String resultadofechat = paft.getString( 3 );
										 
												// Obtener fecha actual
												 java.sql.Date ourJavaDateObject = new java.sql.Date (Calendar.getInstance (). getTime (). getTime ());
												
										
												 System.out.println("resultados " + doc + " " + rutaa + " " + resultadorfc + " " + resultadoimp + " " + resultadofechat + " "+ resultadouuid);
												
													//Registrar el tabla documento
													System.out.println("registrar documento");
													
													PreparedStatement ps;
													
													ps = con.prepareStatement("INSERT INTO Documento (Documento, NombreDocumento, Ruta, RFC,  Importe, FechaTimbrado, UUID, FechaAlta, Estatus)  "
																						  +"  VALUES ( ? , ?, ?, ?, ?, ?, ?, ?, ?) ");
													
													ps.setString(1, doc);
													ps.setString(2, docN);
												    ps.setString(3, rutaa); 
												    ps.setString(4, resultadorfc);
												    ps.setString(5, resultadoimp);
												    ps.setString(6, resultadofechat);
												    ps.setString(7, resultadouuid);
												    ps.setDate  (8, ourJavaDateObject);
												    ps.setString(9, "Recibido");
						
												    ps.executeUpdate();	
												    
												    b= "Registro con exito";
												
												
											}//docuD
											else{
												
												b= "Ya existe un documento con el mismo Folio Fiscal";
												System.out.println("Ya existe un documento con el mismo Folio Fiscal");
												
												
											}// else docuD (docuD = validacion de UUID en tabla documento que si existe					
		
								} else {
									
									b= "Ya existe un documento con el mismo nombre";
									System.out.println("Ya existe un documento con el mismo nombre");
									
								}	//docd		    
					
				} else{
					
					b="El documento no corresponde al proveedor logueado";
					System.out.println("El documento no corresponde al proveedor logueado");
				}	
				
				con.close();
				prcProcedimientoAlmacenado.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return b;
		}
		
	}
		
}

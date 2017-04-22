<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ page import="conexion.ConexionSQL" %>
<%@ page import="java.sql.*" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <%-- Importa los CSS y JS de Twitter Bootstrap --%>

	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal XML</title>

</head>
<body>

<hr align="left" noshade="noshade" size="2" width="100%" />
<br><br>

<center><FONT SIZE=5 > Control de Documentos </FONT></center>
<br><br><br><br>

<hr align="left" noshade="noshade" size="2" width="100%" />

<center>
<table  WIDTH=1300 >
	<tr>
		<td>
			<FONT SIZE=3> 	Sus datos: </FONT>
			<%
			try{
				
				String nomp = (String)session.getAttribute("cprov"); 
				System.out.println("pruebas documento");
				System.out.println(nomp);
				
				Connection con=ConexionSQL.getConexion();
				System.out.println("Existe conexion seccion documento " +  con);
				//System.out.println(request.getAttribute("Error"));
				
				//Definir tipo de usuari0
				Statement stTU;
				ResultSet rsTU;
				
				String TipoUsuario=null;
				
				String sqlTipo= "Select UsuarioTipo From Usuario Where Usuario = '" + nomp + "' ";
				
				stTU=con.createStatement();
				rsTU = stTU.executeQuery(sqlTipo);
				
				while(rsTU.next()){
					
					TipoUsuario =rsTU.getString("UsuarioTipo");									
//					System.out.println(" tipo de usuario: " + TipoUsuario);	
				}
				
				System.out.println(" if*****" + sqlTipo);
				System.out.println(" tipo de usuario: " + TipoUsuario);
				
				if (TipoUsuario.equals("Usuario sistema"))
				{
					Statement stu;
					ResultSet rsu;
					
					String sqlu="Select Nombre From Usuario Where Usuario = '" + nomp + "'";
					
					stu=con.createStatement();
					rsu = stu.executeQuery(sqlu);
						
					System.out.println("obtener datos usuario admin");
					
					out.println("<table>");
					while(rsu.next()){
						
						out.println("<tr>");
						out.println("<td>Nombre: </td>");
				        out.println("<td>"+rsu.getObject("Nombre")+"</td>");
				        out.println("</tr>");
					}
					
					out.println("</table>");
					
										
				} else {
					Statement st;
					ResultSet rs;

					String sql="Select Nombre, RFC, Contacto, Telefono, eMail From Prov Where RFC = '" + nomp +"'";
					
					st=con.createStatement();
					rs = st.executeQuery(sql);
						
					System.out.println("armar tabla");
					
					out.println("<table>");
					while(rs.next()){
						
						out.println("<tr>");
						out.println("<td>Nombre: </td>");
				        out.println("<td>"+rs.getObject("Nombre")+"</td>");
				        out.println("</tr>");
				        
				        out.println("<tr>");
				        out.println("<td>RFC: </td>");
				        out.println("<td>"+rs.getObject("RFC")+"</td>");
				        out.println("</tr>");
				        
				        out.println("<tr>");
				        out.println("<td>Nombre contacto: </td>");
				        out.println("<td>"+rs.getObject("Contacto")+"</td>");
				        out.println("</tr>");
				        
				        out.println("<tr>");
				        out.println("<td>Telefono: </td>");
				        out.println("<td>"+rs.getObject("Telefono")+"</td>");
				        out.println("</tr>");
				        
				        out.println("<tr>");
				        out.println("<td>Correo electronico: </td>");
				        out.println("<td>"+rs.getObject("eMail")+"</td>");
				        out.println("</tr>");
					}
					out.println("</table>");
					
					
				}//if tipo usario
				
				
				con.close();
				rsTU.close();
				stTU.close();
							
				
			} catch (SQLException e){
				e.printStackTrace();
			}
			
			%>
		</td>
		
				
		<td>
			
			<%String nomp = (String)session.getAttribute("cprov");  
 				  request.setAttribute("nomp", nomp);
 				  %>
			<form id="subirarchivo" action="subirarchivo.jsp" method="POST" name="subirarchivo">
				<input type="submit" value="Importar archivo"/>		
				<input type="hidden" value="${nomp}" name="nomp"/>
			</form>    
			
		</td>
		

		<td>   
			<form id="sesion" action="modificardatos.jsp" method="POST">
				<input type="submit" value="Modificar datos" />		
				<input type="hidden" value="${nomp}" name="nomp"/>
			</form>
		</td>	
	    
		
		<td>
			
			<form id="sesion" action="Logout.jsp" method="POST">
				<input type="submit" name="session" value="Cerrar sesión" />		
			</form>	 
		 
		</td>
	</tr>
		
	<tr><td colspan="4">
			<%
			try{
				
//				String nomp = (String)session.getAttribute("cprov"); 
				
				Connection con=ConexionSQL.getConexion();
				System.out.println("Existe conexion  " +  con);
				
				//Definir tipo de usario
				
				Statement stut;
				ResultSet rsut;
				
				String TipoUsuario=null;
				
				String sqlTipo= "Select UsuarioTipo From Usuario Where Usuario = '" + nomp + "' ";
				
				stut=con.createStatement();
				rsut = stut.executeQuery(sqlTipo);
				
				while(rsut.next()){
					
					TipoUsuario =rsut.getString("UsuarioTipo");									
					System.out.println(" tipo de usuario: " + TipoUsuario);	
				}
				
				System.out.println(" if*****" + sqlTipo);
				System.out.println(" tipo de usuario: " + TipoUsuario);
				
				if (TipoUsuario.equals("Usuario sistema"))
				{
					Statement stu;
					ResultSet rsu;
					
					String sqlu="Select NombreDocumento, Importe,  FechaTimbrado, UUID, Estatus  From Documento Where RFC= " + "RFC" ;
					
					stu=con.createStatement();
					rsu = stu.executeQuery(sqlu);
						
					System.out.println("obtener datos de todos lor proveedores " + sqlu);
					
					out.println("<table border=1 > <tr>  <td>Documento</td>   <td>Importe</td>   <td>Fecha Timbrado</td>  <td>Folio Fiscal</td> <td>Estatus del documento</td>  </tr>");
					while(rsu.next()){
						
						out.println("<tr>");
						out.println("<td>"+rsu.getObject("NombreDocumento")+"</td>");
						out.println("<td>"+rsu.getObject("Importe")+"</td>");
						out.println("<td>"+rsu.getObject("FechaTimbrado")+"</td>");
						out.println("<td>"+rsu.getObject("UUID")+"</td>");
						out.println("<td>"+rsu.getObject("Estatus")+"</td>");						
						out.println("</tr>");
						
					}
					out.println("</table>");
					
				
					
				} // if tipo usario
				else
				{
				
					Statement stp;
					ResultSet rsp; 
					String sql="Select NombreDocumento, Importe,  FechaTimbrado, UUID, Estatus, ID  From Documento Where RFC= '" + nomp + "'";
					
					stp=con.createStatement();
					rsp= stp.executeQuery(sql);
						
					System.out.println("armar tabla");
					
					out.println("<table border=1> <tr><td>Documento</td> <td></td> <td>Importe</td>  <td></td> <td>Fecha Timbrado</td>  <td></td> <td>UUID</td> <td></td> <td>Estatus</td> <td></td> <td></td> </tr>");
					while(rsp.next()){
						
						out.println("<tr>");
						out.println("<td>"+rsp.getObject("NombreDocumento")+"  </td>");
						out.println("<td>  </td>");
						out.println("<td>"+rsp.getObject("Importe")+"</td>");
						out.println("<td>  </td>");
						out.println("<td>"+rsp.getObject("FechaTimbrado")+"</td>");
						out.println("<td>  </td>");
						out.println("<td>"+rsp.getObject("UUID")+"</td>");
						out.println("<td>  </td>");
						out.println("<td>"+rsp.getObject("Estatus")+"</td>");
						out.println("<td>  </td>");
						out.println("<td>" +
								"<form id='sesion' action='eliminar' method='POST'>"
								+ "<input type='submit' value='Eliminar' />"
								+ "<input type='hidden' value='"+ rsp.getObject("ID") +"' name='ID'>"
     							+ "<input type='hidden' value='"+ nomp +"' name='nomp'>"
							   +"</form>"
							+ " </td>");
						out.println("</tr>");
					}
					out.println("</table>");
				
				
				}//if tipo usario tipo proveedor
				
				con.close();
				rsut.close();
				stut.close();
				
			} catch (SQLException e){
				e.printStackTrace();
			}
			
			%>
		</td>
    
</table>
</center>




</body>
</html>
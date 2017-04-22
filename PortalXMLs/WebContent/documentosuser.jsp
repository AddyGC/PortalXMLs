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


<center><FONT SIZE=5 > Control de Documentos </FONT></center>
<br><br><br><br>

<center>
<table border="1" WIDTH=1300 >
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
				
				
				Statement st;
				ResultSet rs;
				
				String sql="Select Nombre, RFC, Contacto, Telefono, eMail From Prov Where RFC = '" + nomp +"'";
				
				st=con.createStatement();
				rs = st.executeQuery(sql);
					
				System.out.println("armar tabla");
				
				out.println("<table ");
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
				
				con.close();
				rs.close();
				st.close();
				
			} catch (SQLException e){
				e.printStackTrace();
			}
			
			%>
		</td>
		
		<td>
			
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
				<form id="sesion" action="sesionprov" method="POST">
				<input type="button" name="nombreprov" value="consultar" />		
				</form>
			</td>
			
		</td>
		
		<td>
			<form id="sesion" action="logout" method="POST">
				<input type="submit" name="session" value="Cerrar sesión" />		
			</form>	 
		 
		</td></tr>
	<tr><td  colspan="3">
			<%
			try{
				
//				String nomp = (String)session.getAttribute("cprov"); 
				
				Connection con=ConexionSQL.getConexion();
				System.out.println("Existe conexion  " +  con);
				
				Statement st;
				ResultSet rs;
				
				String sql="Select Documento, Subtotal, IVAImporte, Total, Estatus From Documento Where usuario= '" + nomp + "'";
				
				st=con.createStatement();
				rs = st.executeQuery(sql);
					
				System.out.println("armar tabla");
				
				out.println("<table border=\"1\"><tr><td>Documento</td> <td>Subtotal</td> <td>IVA</td> <td>Total</td> <td>Estatus</td>  </tr>");
				while(rs.next()){
					
					out.println("<tr>");
			        out.println("<td>"+rs.getObject("Documento")+"</td>");
			        out.println("<td>"+rs.getObject("SubTotal")+"</td>");
			        out.println("<td>"+rs.getObject("IVAImporte")+"</td>");
			        out.println("<td>"+rs.getObject("Total")+"</td>");
			        out.println("<td>"+rs.getObject("Estatus")+"</td>");
			        out.println("</tr>");
				}
				out.println("</table>");
				
				con.close();
				rs.close();
				st.close();
				
			} catch (SQLException e){
				e.printStackTrace();
			}
			
			%>
		</td>
    </tr>
	<tr><td  colspan="3">y</td></tr>
</table>
</center>

</body>
</html>
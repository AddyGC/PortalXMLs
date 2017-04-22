<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="conexion.ConexionSQL" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal XML</title>
</head>
<body>

<br><br><br><br><br><br><br><br>

<center>
<FONT SIZE=7 COLOR=134CAA> 
¡¡ Ha sido registado de manera exitosa !!
</font>

<br><br><br><br>
<FONT SIZE=5 COLOR=134CAA> 
Sus datos:
</font>

<br><br>
<%
try{
	
	String nomclave = (String)session.getAttribute("nomclave"); 
	String tipo = (String)session.getAttribute("nomclave");
	
	System.out.println("pruebas documento");
	System.out.println(nomclave);
	
	Connection con=ConexionSQL.getConexion();
	System.out.println("Existe conexion seccion documento " +  con);
	//System.out.println(request.getAttribute("Error"));
	
	
	Statement st;
	ResultSet rs;
	
	if(tipo.equals ("proveedor")){
		
		String sql="Select Nombre, RFC  From Prov Where RFC = '" + nomclave +"'";
		
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
	                
		}
		out.println("</table>");
		
		
		
	}else {
		
		String sql="Select Nombre, Usuario  From Usuario Where Usuario = '" + nomclave +"'";
		
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
	        out.println("<td>Usuario: </td>");
	        out.println("<td>"+rs.getObject("Usuario")+"</td>");
	        out.println("</tr>");
	                
		}
		out.println("</table>");
		
		
	}
	
	
	con.close();
	rs.close();
	st.close();
	
} catch (SQLException e){
	e.printStackTrace();
}

%>

<a href="index.jsp"> Ingresar a su cuenta </a>
</center>

</body>
</html>
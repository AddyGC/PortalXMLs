<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="sesion.Classesionprov" %>    
<%@ page import="java.util.*" %>
<%@ page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal XML</title>
</head>
<body>

<br><br><br><br><br>
<center>
	<h2>Iniciar sesi�n</h2>
	
	<FONT SIZE=2 COLOR=134CAA> 	
	<% 	
	
	System.out.println(request.getAttribute("Iniciar index"));
	System.out.println(request.getAttribute("Error"));
	
	if (request.getAttribute("Error") != null){
	
		if(request.getAttribute("Error") == "Usuario y/o constrase�a invalido")
		{
			out.println( request.getAttribute("Error"));
		} else {
		
	%>
		Por favor debe insertar los datos:
    <%	 		 
				
			System.out.println(request.getAttribute("Error")); 
		    out.println( request.getAttribute("Error"));
		}
	}//if
	
	  
	 
	%>
	</FONT> 
	

		
		<form id="sesion" action="sesionprov" method="POST">
			<Table>
				<tr>
					<td>Usuario:</td>
				    <td><input type="text" name="nombreprov" /></td>				
				</tr>
				<tr>
					<td>
						<br>
				 	</td>
				</tr>
				<tr>
					<td> Password:</td>
				    <td><input type="password" name="passprov" /></td>				
				</tr>
				
				<tr>
					<td>
						<br>
				 	</td>
				</tr>
				
				<tr>
					<td align="center"><input type="reset" value="Reset" /></td>
					<td align="center"><input type="submit" value="Iniciar sesi�n" name="registrar" /></td>
				</tr>
			</Table>	
		</form>
		
		<br><br>
		<table>
			<tr>
				<td><a href="regisususario.jsp">Si eres Proveedor, crea tu cuenta aqui</a></td>
				<td></td>	
			</tr>					
			<tr>
				<td><a href="reguseradmin.jsp">Si eres usuario Admnistrador, crea tu cuenta aqui</a></td>
			 </tr>
		</table>
		
	
</center>


 </body>
</html>
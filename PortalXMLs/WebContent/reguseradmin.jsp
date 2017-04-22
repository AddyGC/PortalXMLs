<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal XML</title>
</head>
<body>


<center><FONT SIZE=5 > 	Crear una cuenta </FONT></center>
<br><br><br><br>

<FONT SIZE=2 COLOR=134CAA> 	
	<% 	
	System.out.println("campos faltan" + request.getAttribute("Error"));
	System.out.println("contraseña" + session.getAttribute("Error2"));
	System.out.println("usuario" + session.getAttribute("Error3"));
	
	if (request.getAttribute("Error") != null){
	
		
	%>
		Por favor debe insertar los datos:
    <%	 		 
			 
	    out.println( request.getAttribute("Error"));
		
	}//if
	
	if (session.getAttribute("2Error")  != null){
		
		out.println( request.getAttribute("2Error")); 
		
	} 
	if (session.getAttribute("Error3")  != null){
		
		out.println(session.getAttribute("Error3")); 
		
	} 
	
	%>
	</FONT> 
	    
		<form id="reguser" action="registraruser.do" method="POST">
			<Table >	
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Clave de usuario:</td>
				    <td><input type="text" name="claveuseradmin" /></td>				
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td> Nombre:</td>
				    <td><input type="text" name="nomuseradmin" /></td>				
				</tr>
				
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Contraseña:</td>
				    <td><input type="password" name="passuser" /></td>						
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Confirmar contraseña:</td>
				    <td><input type="password" name="confirmarpassuser" /></td>						
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>						
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td align="center"><input type="submit" value="Registrar" name="registrar" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</Table>		
		</form>

		<form id="sesion" action="index.jsp" method="POST">
			<input type="submit" value="Regresar pagina inicial" />		
		</form>
		

</body>
</html>
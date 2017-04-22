<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal XML</title>


<script language="JavaScript">
function validar()
{
	if (confirmarpassprov != passprov)
	{
	 alert("Las constraseñas no coinciden.");
     return false;
	}
	
	return true;
}
</script>

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
	
	if (session.getAttribute("Error2")  != null){
		
		out.println( session.getAttribute("Error2")); 
		
	} 
	if (session.getAttribute("Error3")  != null){
		
		out.println(session.getAttribute("Error3")); 
		
	} 
	
	%>
		
</FONT> 
    
		<form id="reguser" action="registrar.do" method="POST"  >
			<Table >	
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Nombre:</td>
				    <td><input type="text" name="nombreuser" /></td>				
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td> RFC:</td>
				    <td><input type="text" name="rfc" /></td>				
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Dirección:</td>
				    <td><input type="text" name="direccion" /></td>				
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Número:</td>
				    <td><input type="text" name="num" /></td>				
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Número Interior:</td>
				    <td><input type="text" name="numint" /></td>				
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Colonia:</td>
				    <td><input type="text" name="colonia" /></td>				
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Poblacion:</td>
				    <td><input type="text" name="poblacion" /></td>				
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Estado:</td>
				    <td><input type="text" name="estado" /></td>				
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Pais: </td>
				    <td><input type="text" name="pais" /></td>				
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Codigo Postal: </td>
				    <td><input type="text" name="cp" /></td>				
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Telefono: </td>
				    <td><input type="text" name="telefono" /></td>				
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Contacto: </td>
				    <td><input type="text" name="contacto" /></td>				
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Correo electronico:</td>
				    <td><input type="text" name="email" /></td>				
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Contraseña:</td>
				    <td><input type="password" name="passprov" /></td>						
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Confirmar contraseña:</td>
				    <td><input type="password" name="confirmarpassprov" /></td>						
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
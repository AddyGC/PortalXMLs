<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal XML</title>

<style type="text/css">
	#div3, #div2 {margin: 10px;}
	#div2 {width: 500px;}
</style>
</head>
<body>

<%

System.out.println("subir archivos");
System.out.println(request.getParameter("nomp"));

%>
<br><br>
<center>
<h2>Subir archivo</h2>
 	<div id="form1-section">
 
 <%
 	System.out.println("Parametros para validar");
 	String msg = (String)session.getAttribute("msj"); 
 	System.out.println("Mensaje "  + msg );
 	
 %>
 		<FONT SIZE=2 COLOR=134CAA> 
 		<% String nomp2 = request.getParameter("nomp");
           request.setAttribute("nomp2", nomp2);
           
           //Mostramos mensaje de error
           if( msg != null)
           {
        	   out.println( msg);
           }
           
		%>
		</FONT>
	
		<br><br>
 		
		<form id="upload" action="UploadServlet" method="post" enctype="multipart/form-data">
				<div id="div1">
					<FONT SIZE=4>Archivo: </font>  
					<input type="file" name="file" size="200" /> 					
				</div>
				<div> </div>
				<div id="div2">	
					<input type="submit" value="Enviar documento" />		
					<input type="hidden" value="${nomp2}" name="rfc"/>
				</div>
		</form>
	</div id="div3">
		<form id="sesion" action="documentosprov.jsp" method="POST">
			<input type="submit" value="Regresar a control de documentos" />		
			<input type="hidden" value="${nomp2}" name="cprov"/>
		</form>
	<div>
		
	</div>
</center>
</body>
</html>
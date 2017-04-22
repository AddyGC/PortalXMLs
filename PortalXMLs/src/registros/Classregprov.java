package registros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.ConexionSQL;

public class Classregprov {
	public String nombre, rfc, direccion, num, numint, colonia, poblacion, estado, pais,cp, telefono, contacto, email, passprov, confirmarpassprov;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getNumint() {
		return numint;
	}

	public void setNumint(String numint) {
		this.numint = numint;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassprov() {
		return passprov;
	}

	public void setPassprov(String passprov) {
		this.passprov = passprov;
	}

	public String getConfirmarpassprov() {
		return confirmarpassprov;
	}

	public void setConfirmarpassprov(String confirmarpassprov) {
		this.confirmarpassprov = confirmarpassprov;
	}

	public Classregprov(String nombre, String rfc, String direccion, String num, String numint, String colonia,
			String poblacion, String estado, String pais, String cp, String telefono, String contacto, String email,
			String passprov, String confirmarpassprov) {
		super();
		this.nombre = nombre;
		this.rfc = rfc;
		this.direccion = direccion;
		this.num = num;
		this.numint = numint;
		this.colonia = colonia;
		this.poblacion = poblacion;
		this.estado = estado;
		this.pais = pais;
		this.cp = cp;
		this.telefono = telefono;
		this.contacto = contacto;
		this.email = email;
		this.passprov = passprov;
		this.confirmarpassprov = confirmarpassprov;
	}
	
	
	public int uservalida(){
		
		int b=0;
		
		try{
			Connection con=ConexionSQL.getConexion();
			System.out.println("despues conexion  " +  con +" seccion registrar prov");
			
			Statement st;
			ResultSet rs;
			
			String sql="Select RFC From Prov Where RFC= '" + rfc  +  "'";
			
			System.out.println("seccion valida user  "  + sql);
			
			st=con.createStatement();
			rs = st.executeQuery(sql);
			
			String cveuser = null;
			
			while (rs.next()){
				cveuser = (rs.getString("RFC"));
			}
			
			System.out.println("validar user "  + cveuser);
			
			if( cveuser == null)
			{b=1;
			}
		else {
			b=0;
		}
		
		System.out.println("validar letra" + b );
		
	}catch (Exception e){
		
		b=0;
		System.out.println("validar user error");
	}	
	
	return b;
}
		
	
	public int InsertUsuario(){
		
		int a=0;
		
		try{
			Connection con=ConexionSQL.getConexion();
			System.out.println("despues conexion  " +  con +" seccion registrar prov");
			
			Statement st;
			PreparedStatement ps;
				
			//Ejecuta valores
			st=con.createStatement();
				
			System.out.println("despues createStatement  " +  con + "  st" + st + rfc);
				
			try{
				
				ps = con.prepareStatement("INSERT Prov (Nombre ,RFC ,Direccion ,Direccionnumero ,Direccionnumerointerior ,Colonia ,Poblacion ,Estado ,Pais ,CodigoPostal ,Telefono ,Contacto ,eMail, Estatus, Contrasena ,ContrasenaConfirmacion)  "
												+"  VALUES ( ? ,?, ? ,?, ? ,?, ? ,?, ? ,?, ? ,?, ? ,?,?, ? ) ");
				ps.setString(1, nombre);
				ps.setString(2, rfc);
				ps.setString(3, direccion);
				ps.setString(4, num);
				ps.setString(5, numint);
				ps.setString(6, colonia);
				ps.setString(7, poblacion);
				ps.setString(8, estado);
				ps.setString(9, pais);
				ps.setString(10, cp);
				ps.setString(11, telefono);
				ps.setString(12, contacto);
				ps.setString(13, email);
				ps.setString(14, "ALTA");
				ps.setString(15, passprov);
				ps.setString(16, confirmarpassprov);
				
				a=ps.executeUpdate();
				
				System.out.println("seccion insertar" + "  st" + st);
				
				a=1;
			
			} catch (SQLException e){
				
				System.out.println("no se pudo insertar");
				a=0;
			}
			
		}catch (Exception e ) {
			
			System.out.println("no se pudo conectar");
			a=0;
		}
		
		
		return a;
		
	}
	
	
	
	


}

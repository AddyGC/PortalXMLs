package registros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.ConexionSQL;

public class Classreguser {
	public String nombreclave, nombre, passu, cpassu;

	public String getNombreclave() {
		return nombreclave;
	}

	public void setNombreclave(String nombreclave) {
		this.nombreclave = nombreclave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassu() {
		return passu;
	}

	public void setPassu(String passu) {
		this.passu = passu;
	}

	public String getCpassu() {
		return cpassu;
	}

	public void setCpassu(String cpassu) {
		this.cpassu = cpassu;
	}

	public Classreguser(String nombreclave, String nombre, String passu, String cpassu) {
		super();
		this.nombreclave = nombreclave;
		this.nombre = nombre;
		this.passu = passu;
		this.cpassu = cpassu;
	}

	
public int ValidaUser(){
	
	int b=0;
	
	try{
		Connection con=ConexionSQL.getConexion();
		System.out.println("despues conexion  " +  con +" seccion valida user  "  + nombreclave);
		
		Statement st;
		ResultSet rs;
		
		String sql="Select Usuario From Usuario Where usuario= '" + nombreclave + "'" ;
		
		System.out.println("seccion valida user  "  + sql);
		
		st=con.createStatement();
		rs = st.executeQuery(sql);
		
		String cveuser = null;
		
		while (rs.next()){
			cveuser = (rs.getString("Usuario"));
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
	
public int InsertUser(){
		
		int a=0;
		
		try{
			Connection con=ConexionSQL.getConexion();
			System.out.println("despues conexion  " +  con +" seccion registrar user");			
		
			Statement st;
			PreparedStatement ps;
				
			//Ejecuta valores
			st=con.createStatement();
				
			System.out.println("despues createStatement  " +  con + "  st" + st + nombreclave);
			
			try{
						
				ps = con.prepareStatement("INSERT Usuario (Usuario, Nombre, Contrasena, ContrasenaConfirmacion, Estatus, UsuarioTipo)  "
												+"  VALUES ( ? ,?, ? ,?, ? ,? ) ");
				ps.setString(1, nombreclave);
				ps.setString(2, nombre);
				ps.setString(3, passu);
				ps.setString(4, cpassu);
				ps.setString(5, "ALTA");
				ps.setString(6, "Usuario sistema");				
				
				
				a=ps.executeUpdate();
				
				System.out.println("seccion insertar" + "  st" + st);
				
				a=1;
					
			    }catch (SQLException e){
						
						System.out.println("no se pudo insertar");
						a=0;
				}    
				
			}catch (Exception e){
				
				System.out.println("no se pudo conectar");
				a=0;
			}
		
		return a;
		
	}
	
	
	
	
	

}

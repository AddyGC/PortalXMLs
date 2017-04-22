package sentenciasSQL;

import java.sql.*;
import conexion.ConexionSQL;

public class BuscarProv {
	
	public String nombreprov, passprov;

	public String getNombreprov() {
		return nombreprov;
	}

	public void setNombreprov(String nombreprov) {
		this.nombreprov = nombreprov;
	}

	public String getPassprov() {
		return passprov;
	}

	public void setPassprov(String passprov) {
		this.passprov = passprov;
	}

	public BuscarProv(String nombreprov, String passprov) {
		super();
		this.nombreprov = nombreprov;
		this.passprov = passprov;
	}
	
	
	//public String BuscaProv(){
	public int Busqueda(){
		
		int a=0;
		
			
		try{
			Connection con=ConexionSQL.getConexion();
			System.out.println("seccion buscarprov");
			System.out.println("Existe conexion busqueda  " +  con);
			System.out.printf(nombreprov + "  " + passprov);
			
			Statement st;
			ResultSet rs;
			
			String sql="Select count(Nombre) From Usuario Where usuario= '" + nombreprov + " ' AND contrasena= '" + passprov + "'"; //  AND contrasena='"+ passprov  +"' ";
			
			st=con.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) 
			{ 
				System.out.println("consulta" + "  " + sql );
				
				System.out.println("la consulta es correcta, valida contraseña y usuario ");
				System.out.println("los valores son;" + nombreprov + "  " + passprov );  
				System.out.println(rs.getString(1));
				
				a= Integer.parseInt(rs.getString(1));
				
				System.out.println(a);
			}				
			
			
			con.close();
			rs.close();
			st.close();	
			
		}catch (Exception e){
			
			a=0;
		}
	//return x;
		return a;
	}
	
	
}

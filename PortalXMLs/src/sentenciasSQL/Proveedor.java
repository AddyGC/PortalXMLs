package sentenciasSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.ConexionSQL;

public class Proveedor {
	 
	//public String[] BusTodDatos(){
		public static Consultaprov consultarProv(String Proveedor){

			Consultaprov cprov=null;
							
			try{
				Connection con=ConexionSQL.getConexion();
				System.out.println("Existe conexion  " +  con);
				
				Statement st;
				ResultSet rs;
				
				String sql="Select Nombre, Constrasena, Estatus From Usuario Where usuario= 'x'";
				
				st=con.createStatement();
				rs = st.executeQuery(sql);
				
				while (rs.next()) 
				{ 
					System.out.println("consulta" + "  " + sql );
					
					System.out.println("la consulta es correcta buscar datos prov");
					System.out.println("los valores son;"  );  
					System.out.println(rs.getString(1));
					
					//cprov = new Consultaprov(rs.getString(1), rs.getString(2), rs.getString(3));
					cprov = new Consultaprov(rs.getString(1), rs.getString(2));
					
					
					System.out.println("val "  );
					//System.out.println(a);
				}				
				
				
				con.close();
				rs.close();
				st.close();	
				
			}catch (SQLException e){
				
				//a=0;
				e.printStackTrace();
			}
		//return x;
			return cprov;
		}

}

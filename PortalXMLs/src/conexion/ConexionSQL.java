package conexion;

import java.sql.*;

public class ConexionSQL {

	//protected Connection conexionSQL(){
		public static Connection getConexion(){
			
			Connection con=null;
		
			
			try{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-BT54KJI; databaseName=MiPortal", "sa","Simplifica86");
				
				System.out.println("conexion realizada CONEXINSQL");
				
			return con;
				
			} catch (Exception e){
				
				con=null;
			}
			return con;
			
					
		}
}

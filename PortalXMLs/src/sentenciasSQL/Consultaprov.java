package sentenciasSQL;

//import java.sql.*;
//import conexion.ConexionSQL;

public class Consultaprov {

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

	public Consultaprov(String nombreprov, String passprov) {
		super();
		this.nombreprov = nombreprov;
		this.passprov = passprov;
	}
	
	
}

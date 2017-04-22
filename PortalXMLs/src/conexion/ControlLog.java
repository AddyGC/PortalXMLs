package conexion;

public class ControlLog {

	private int cantidad=0;
	
	public int getCantidad(){
		
		return cantidad;
	}
	
	public void setCantidad(){
		
		this.cantidad=getCantidad()+1;
		
	}
	
	public void iniciar(){
		
		this.cantidad=0;
	}
}

package Gody;

public abstract class Barrio {
	
	private String nombreB;
	private double superficie;
	private int comuna;
	
	public abstract boolean Criticidad();
	public abstract double Densidad();
	

	public String getNombreB() {
		return nombreB;
	}

	public void setNombreB(String nombreB) {
		this.nombreB = nombreB;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	public int getComuna() {
		return comuna;
	}
	public void setComuna(int comuna) {
		this.comuna = comuna;
	}
}

package bou;
public class Residente {
	private String DNI;
	private String nombre;
	private String fnacimiento;
	private String SitLaboral;
	private boolean Tienehijos;
	public boolean isTienehijos() {
		return Tienehijos;
	}
	public void setTienehijos(boolean tienehijos) {
		Tienehijos = tienehijos;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFnacimiento() {
		return fnacimiento;
	}
	public void setFnacimiento(String fnacimiento) {
		this.fnacimiento = fnacimiento;
	}
	public String getSitLaboral() {
		return SitLaboral;
	}
	public void setSitLaboral(String sitLaboral) {
		SitLaboral = sitLaboral;
	}
	public Residente(String dNI, String nombre, String fnacimiento, String sitLaboral, boolean tienehijos) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.fnacimiento = fnacimiento;
		SitLaboral = sitLaboral;
		Tienehijos = tienehijos;
	}
	
	

}

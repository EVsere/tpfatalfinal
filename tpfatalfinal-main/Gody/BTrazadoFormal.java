package Gody;

import java.util.ArrayList;

public class BTrazadoFormal extends Barrio {
	private int edPh;
	private int pAsfalto;
	
	public BTrazadoFormal(int edPh, int pAsfalto,String nombre, double Superficie, int comuna) {
		super();
		this.edPh = edPh;
		this.pAsfalto = pAsfalto;
		setNombreB(nombre);
		setSuperficie(Superficie);
		setComuna(comuna);
	}

	
	private ArrayList<Residente> TformResi= new ArrayList<Residente>();
	
	
	public boolean Criticidad()
	{
		return edPh < 95;
	}
	
	public  double Densidad()
	{
		return TformResi.size() / pAsfalto;
	}
	

	public int getEdPh() {
		return edPh;
	}

	public void setEdPh(int edPh) {
		this.edPh = edPh;
	}

	public int getpAsfalto() {
		return pAsfalto;
	}

	public void setpAsfalto(int pAsfalto) {
		this.pAsfalto = pAsfalto;
	}

	public ArrayList<Residente> getTformResi() {
		return TformResi;
	}

	public void setTformResi(ArrayList<Residente> tformResi) {
		TformResi = tformResi;
	}

	@Override
	public String toString() {
		return "BTrazadoFormal [Nombre=" + getNombreB() + 
		       ", Superficie=" + getSuperficie() + 
		       ", edPh=" + edPh + 
		       ", pAsfalto=" + pAsfalto+ ", comuna =" + getComuna() + "]";
	}
	
}
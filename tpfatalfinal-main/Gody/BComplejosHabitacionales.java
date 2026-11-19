package Gody;

import java.util.ArrayList;

public class BComplejosHabitacionales extends Barrio{

	private int cMono;
	private boolean consorcio;
	public BComplejosHabitacionales(int cMono, boolean consorcio,String nombre, double Superficie, int comuna) {
		super();
		this.cMono = cMono;
		this.consorcio = consorcio;
		setNombreB(nombre);
		setSuperficie(Superficie);
		setComuna(comuna);
	}
	private ArrayList<Residente> CompleResi= new ArrayList<Residente>();
	
	
	public  boolean Criticidad()
	{
		return consorcio;
	}
	
	public  double Densidad()
	{
		return CompleResi.size() / cMono;
	}
	
	public int getcMono() {
		return cMono;
	}
	public void setcMono(int cMono) {
		this.cMono = cMono;
	}
	public boolean isConsorcio() {
		return consorcio;
	}
	public void setConsorcio(boolean consorcio) {
		this.consorcio = consorcio;
	}
	public ArrayList<Residente> getCompleResi() {
		return CompleResi;
	}
	public void setCompleResi(ArrayList<Residente> compleResi) {
		CompleResi = compleResi;
	}

	@Override
	public String toString() {
		return "BComplejosHabitacionales [cMono=" + cMono + ", consorcio=" + consorcio + ", Nombre=" + getNombreB()
				+ ", comuna =" + getComuna() +"]";
	}

	
}

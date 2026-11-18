package Gody;

import java.util.ArrayList;

public class BUrbanizacionPlanificada extends Barrio{
	
	private int nley;
	private boolean aguaPotable;
	public BUrbanizacionPlanificada(int nley, boolean aguaPotable, String nombre, double superficie, int comuna) {
		super();
		this.nley = nley;
		this.aguaPotable = aguaPotable;
		setNombreB(nombre);
		setSuperficie(superficie);
		setComuna(comuna);
	}
	
	private ArrayList<Residente> UrbaResi= new ArrayList<Residente>();

	
	public boolean Criticidad()
	{
		return aguaPotable;
	}
	
	public  double Densidad()
	{
		return UrbaResi.size() / getSuperficie();
	}
	
	public int getNley() {
		return nley;
	}
	public void setNley(int nley) {
		this.nley = nley;
	}
	public boolean isAguaPotable() {
		return aguaPotable;
	}
	public void setAguaPotable(boolean aguaPotable) {
		this.aguaPotable = aguaPotable;
	}
	public ArrayList<Residente> getUrbaResi() {
		return UrbaResi;
	}
	public void setUrbaResi(ArrayList<Residente> urbaResi) {
		UrbaResi = urbaResi;
	}

	@Override
	public String toString() {
		return "BUrbanizacionPlanificada [nley=" + nley + ", aguaPotable=" + aguaPotable + ", Nombre=" + getNombreB()+ ", comuna =" + getComuna()+ "]";
	}

}

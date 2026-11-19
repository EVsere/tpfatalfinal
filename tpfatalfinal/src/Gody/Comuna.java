package Gody;

import java.util.ArrayList;

public class Comuna {
	private String nombreSede;
	private int nCom;
	private String JuntaComunal;
	private ArrayList<Barrio> Barrios= new ArrayList<Barrio>();
	private int CantB = Barrios.size();
	public boolean esDeAtencionPrio() {
		int a =0;
		for(Barrio x:Barrios) {
			if(x.Criticidad()) {
				a++;
			}
		}
		return a>=(Barrios.size()/2);
	}
	public String getNombreSede() {
		return nombreSede;
	}
	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}
	public int getnCom() {
		return nCom;
	}
	public void setnCom(int nCom) {
		this.nCom = nCom;
	}
	public String getJuntaComunal() {
		return JuntaComunal;
	}
	public void setJuntaComunal(String juntaComunal) {
		JuntaComunal = juntaComunal;
	}
	public ArrayList<Barrio> getBarrios() {
		return Barrios;
	}
	public void setBarrios(ArrayList<Barrio> barrios) {
		Barrios = barrios;
	}
	public Comuna(String nombreSede, int nCom, String juntaComunal) {
		super();
		this.nombreSede = nombreSede;
		this.nCom = nCom;
		JuntaComunal = juntaComunal;
	}
	public int getCantB() {
		return CantB;
	}
	public void setCantB(int cantB) {
		CantB = cantB;
	}
}

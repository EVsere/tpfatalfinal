import java.util.ArrayList;

public class Comuna {
	private String nombreSede;
	private int nCom;
	private String JuntaComunal;
	private ArrayList<Barrio> Barrios= new ArrayList<Barrio>();
	public boolean esDeAtencionPrio() {
		int a =0;
		for(Barrio x:Barrios) {
			if(x.Criticidad()) {
				a++;
			}
		}
		return a>=(Barrios.size()/2);
	}

}

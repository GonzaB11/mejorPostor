package paquete;

import java.util.ArrayList;

public class Solucion {
	private ArrayList<Objeto> objetos;
	private int cantHoras;
	private double ganancia;

	public Solucion () {
		objetos= new ArrayList<Objeto>();
	}

	public void agregarObjeto(Objeto obj) {
		objetos.add(obj);
		cantHoras += obj.cantidadHorasReservada();
		ganancia += obj.getOferta();
	}
	
	public boolean seSuperpone (Objeto obj) {
		for (Objeto ob: objetos) {
			if (!(obj.getHoraFin() <= ob.getHoraInicio() || obj.getHoraInicio()>= ob.getHoraFin()))
				return true;			
		}
		return false;
	}

	public int cardinal () {
		return objetos.size();
	}

	public int getCantHoras() {
		return cantHoras;
	}

	public double getGanancia() {
		return ganancia;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Reservas:\n\n");
	    for (Objeto obj : objetos) {
	        sb.append(obj).append("\n");
	    }
	    sb.append("\n");
	    sb.append("Cantidad de Horas: ").append(cantHoras).append("\n");
	    sb.append("Ganancia Total: $").append(ganancia).append("\n");
	    return sb.toString();
	}

}



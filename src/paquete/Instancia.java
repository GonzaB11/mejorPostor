package paquete;

import java.io.Serializable;
import java.util.ArrayList;

public class Instancia implements Serializable {

	private static final long serialVersionUID = 1L;
	private int capacidad;
	private ArrayList<Objeto> objetos;

	public Instancia(int capacidad) {
		this.capacidad = capacidad;
		objetos = new ArrayList<Objeto>();
	}

	public void agregarObjeto(Objeto obj) {
		for (Objeto ob :  objetos) {
			if (ob.getNombre().equals(obj.getNombre()) && !(obj.getHoraFin() <= ob.getHoraInicio() || obj.getHoraInicio() >= ob.getHoraFin()))
				throw new IllegalArgumentException("La misma banda no puede reservar en el mismo horario o en horarios superpuestos.\"");
		}
		objetos.add(obj);
	}
		
	public int get_capacidad() {
		return capacidad;
	}

	public ArrayList<Objeto> get_objetos() {
		ArrayList<Objeto> ret = new ArrayList<Objeto>();
		for (Objeto ob: objetos)
			ret.add(ob);
		return ret;
	}

	public int get_tamano() {
		return objetos.size();
	}
	
}


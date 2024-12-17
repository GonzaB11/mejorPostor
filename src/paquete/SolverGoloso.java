package paquete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SolverGoloso {

	private Instancia instancia;
	private Comparator <Objeto> comparador;

	public SolverGoloso (Instancia instancia, Comparator <Objeto> comparador) {
		this.instancia= instancia;
		this.comparador= comparador;
	}

	public Solucion resolver() {
		Solucion ret = new Solucion();
		for (Objeto obj : objetosOrdenados()) {
			if (!ret.seSuperpone(obj) && (instancia.get_capacidad() >= ret.getCantHoras() + obj.cantidadHorasReservada())) 
				ret.agregarObjeto(obj);
		}
		return ret;
	}

	private ArrayList<Objeto> objetosOrdenados() {
		ArrayList<Objeto> ret = instancia.get_objetos();
		Collections.sort(ret,comparador);;
		return ret;
	}
}

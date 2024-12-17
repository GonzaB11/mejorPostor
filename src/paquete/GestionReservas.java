package paquete;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestionReservas {

	private Instancia instancia;

	public GestionReservas(){
		this.instancia= new Instancia(24);
	}

	public void crearObjeto(String nombre, int horaInicio, int horaFin, double oferta) {
		Objeto obj = new Objeto(nombre, horaInicio, horaFin, oferta);
		instancia.agregarObjeto(obj);
	}
	
	Comparator <Objeto> comparadorPorCociente= new Comparator <Objeto>() {
		@Override
		public int compare(Objeto uno, Objeto otro) {
			return Double.compare(otro.cociente(), uno.cociente());
		}		
	};

	Comparator <Objeto> comparadorPorHoraFin= new Comparator <Objeto>() {
		@Override
		public int compare(Objeto uno, Objeto otro) {
			return uno.getHoraFin() - otro.getHoraFin();
		}		
	};;

	Comparator <Objeto> comparadorPorHoraInicio= new Comparator <Objeto>() {
		@Override
		public int compare(Objeto uno, Objeto otro) {
			return uno.getHoraInicio() - otro.getHoraInicio();
		}		
	};;

	Comparator <Objeto> comparadorPorMayorDuracion= new Comparator <Objeto>() {
		@Override
		public int compare(Objeto uno, Objeto otro) {
			return otro.cantidadHorasReservada()- uno.cantidadHorasReservada();
		}		
	};;

	Comparator <Objeto> comparadorPorMenorDuracion= new Comparator <Objeto>() {
		@Override
		public int compare(Objeto uno, Objeto otro) {
			return uno.cantidadHorasReservada() - otro.cantidadHorasReservada();
		}		
	};;

	Comparator <Objeto> comparadorPorOfertaMaxima= new Comparator <Objeto>() {
		@Override
		public int compare(Objeto uno, Objeto otro ) {
			return Double.compare(otro.getOferta(), uno.getOferta());
		}	
	};;

	@SuppressWarnings("unchecked")
	Comparator<Objeto>[] comparadores = new Comparator[] { 
			comparadorPorCociente,
			comparadorPorHoraFin,
			comparadorPorHoraInicio, 
			comparadorPorMayorDuracion, 
			comparadorPorMenorDuracion,
			comparadorPorOfertaMaxima}
	;

	public Solucion seleccionarMejorSolucion () {
		Solucion mejorSolucion= null;
		double maxGanancia=0;
		for (Comparator<Objeto> c : comparadores) {
			SolverGoloso solver = new SolverGoloso (instancia, c);
			Solucion solucion= solver.resolver();

			if (solucion.getGanancia()> maxGanancia) {
				maxGanancia = solucion.getGanancia();
				mejorSolucion= solucion;
			}
		}
		return mejorSolucion;
	}
	
	public List<String> obtenerOfertas(){
		List<String> ofertas= new ArrayList<>();
		for (Objeto obj : instancia.get_objetos())
			ofertas.add(obj.toString());
		return ofertas;
	}

	public void escrituraSerializacion() {
		try (FileOutputStream fos = new FileOutputStream("ListaOfertas");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(instancia);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void leerSerializacion() {
		File file = new File("ListaOfertas");
		if (!file.exists() || file.length() == 0) {
			return; 
		}
		try (FileInputStream fis = new FileInputStream("ListaOfertas");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			instancia = (Instancia) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}



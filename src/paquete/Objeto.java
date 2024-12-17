package paquete;

import java.io.Serializable;

public class Objeto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int horaInicio, horaFin;
	private double oferta;

	public Objeto (String nombre, int horaInicio, int horaFin, double oferta) {
		if (nombre.isEmpty() || nombre==null) {
			throw new IllegalArgumentException("Nombre no puede estar vacio");
		}
		if (horaInicio < 0 || horaInicio >= 24) {
			throw new IllegalArgumentException("Ingrese una hora valida");
		}
		if (horaFin < 0 || horaFin > 24) {
			throw new IllegalArgumentException("Ingrese una hora valida");
		}	
		if (horaInicio >= horaFin) {
            throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora de fin");
        }
		if (oferta <=0) {
			throw new IllegalArgumentException("La oferta debe ser un número positivo");
		}
		this.nombre= nombre;
		this.horaInicio= horaInicio;
		this.horaFin= horaFin;
		this.oferta= oferta;
	}


	public String getNombre() {
		return nombre;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}

	public double getOferta() {
		return oferta;
	}

	public int cantidadHorasReservada() {
		return horaFin - horaInicio;
	}

	public double cociente () {
		return (double)oferta / this.cantidadHorasReservada();
	}

	@Override
	public String toString() {
	    return "Oferta de: " + nombre + "\nHorario: " + horaInicio + "-" + horaFin + "hs \nOfrece: $" + oferta + "\n";
	}
}

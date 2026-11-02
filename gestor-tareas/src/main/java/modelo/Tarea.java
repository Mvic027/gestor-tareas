package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarea {
	private int id;
	private static int numero = 1;
	private String descripcion;
	private boolean completada;
	private LocalDate fechaLimite;
	
	private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Tarea(String descripcion) {
		this(descripcion, null);
	}

	public Tarea(String descripcion, LocalDate fecha) {
		if (descripcion.isBlank())
			throw new IllegalArgumentException("La descripción no puede estar vacía");
		this.id = numero;
		this.descripcion = descripcion;
		this.completada = false;
		this.fechaLimite = fecha;
		numero++;
	}

	void setId(int id) {
		this.id = id;
	}

	public boolean isCompletada() {
		return completada;
	}

	public void completar() {
		this.completada = true;
	}

	public String getFechaLimite() {
		if(fechaLimite==null) return null;
		return fechaLimite.format(FORMATO);
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	protected void setDescripcion(String desc) {
		 this.descripcion=desc;
	}

	static void reducirNumero() {
		numero--;
	}

	@Override
	public String toString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String marca = " ";
		String fecha = "";
		if (getFechaLimite() != null) {
			fecha = " (" + fechaLimite.format(formato) + ")";
		}
		if (isCompletada()) {
			marca = "x";
		}
		return "[" + id + "] [" + marca + "] " + descripcion + fecha;
	}

	public void setFechaLimite(LocalDate fecha) {
		this.fechaLimite=fecha;
		
	}

	public boolean isAtrasada() {
		if(this.fechaLimite==null) return false;
		return this.fechaLimite.isBefore(LocalDate.now());
	}
}

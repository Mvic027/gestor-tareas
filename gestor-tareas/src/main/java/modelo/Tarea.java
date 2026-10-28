package modelo;

public class Tarea {
	private int id;
	private static int numero = 1;
	private final String descripcion;
	private boolean completada;
	private String fechaLimite;

	public Tarea(String descripcion) {
		this(descripcion, null);
	}

	public Tarea(String descripcion, String fecha) {
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
		return fechaLimite;
	}

	public void setFechaLimite(String fechaLim) {
		this.fechaLimite = fechaLim;
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	static void reducirNumero() {
		numero--;
	}

	@Override
	public String toString() {
		String marca = " ";
		String fecha = "";
		if (getFechaLimite() != null) {
			fecha = " (" + fechaLimite + ")";
		}
		if (isCompletada()) {
			marca = "x";
		}
		return "[" + id + "] [" + marca + "] " + descripcion + fecha;
	}
}



public class Tarea {
	private final int id;
	private static int numero =1 ;
	private final String descripcion;
	private boolean completada;
	private String fecha_lim;
	
	
	public Tarea(String descripcion) {
		this(descripcion,null);
	}
	
	public Tarea(String descripcion, String fecha) {
		this.id=numero;
		this.descripcion=descripcion;
		this.completada=false;
		this.fecha_lim=fecha;
		numero++;
	}


	public boolean isCompletada() {
		return completada;
	}


	public void completar() {
		this.completada = true;
	}


	public String getFecha_lim() {
		return fecha_lim;
	}


	public void setFecha_lim(String fecha_lim) {
		this.fecha_lim = fecha_lim;
	}


	public int getId() {
		return id;
	}


	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		String marca=" ";
		String fecha="";
		if(getFecha_lim()!=null) {
		fecha=" ("+fecha_lim+")";
		}
		if (isCompletada()) {
			marca="x";
		}
		return "["+id+"] ["+marca+"] " + descripcion + fecha;
	}
}

package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import persistencia.AlmacenTareas;

public class GestorTareas {
	private final List<Tarea> tareas = new ArrayList<>();
	private final AlmacenTareas almacen = new AlmacenTareas();

	public GestorTareas() {
		tareas.addAll(almacen.cargar());
	}

	public Tarea anadir(String desc, LocalDate fecha) {
		Tarea tarea = new Tarea(desc, fecha);
		tareas.add(tarea);
		almacen.guardar(tareas);
		return tarea;
	}

	public List<Tarea> getTareas() {
		return List.copyOf(tareas);
	}

	public Tarea buscarTarea(int id) {
		for (Tarea t : tareas) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}

	public void eliminarTarea(Tarea t) {
		if (tareas.remove(t)) {
			actualizarId(t);
			almacen.guardar(tareas);
		}
	}

	public void completarTarea(Tarea t) {
		t.completar();
		almacen.guardar(tareas);
	}

	public void actualizarDescripcion(Tarea t, String desc) {
		t.setDescripcion(desc);
		almacen.guardar(tareas);
	}

	public void actualizarFecha(Tarea t, LocalDate fecha) {
		t.setFechaLimite(fecha);
		almacen.guardar(tareas);
	}

	private void actualizarId(Tarea t) {
		int id = t.getId();
		for (Tarea ta : tareas) {
			if (ta.getId() > id) {
				ta.setId(ta.getId() - 1);
			}
		}
		Tarea.reducirNumero();
	}
}
import java.util.ArrayList;
import java.util.List;

public class GestorTareas {
	private final List<Tarea> tareas = new ArrayList<>(); 
	
	
	public Tarea anadir(String desc, String fech) {
		Tarea tarea;
		if (fech.isEmpty()) {
			 tarea = new Tarea(desc);
		} else {
			 tarea = new Tarea(desc, fech);
		}
		tareas.add(tarea);
		return tarea;
	};

	public void listar() {
		if (tareas.isEmpty()) {
			System.out.println("No hay tareas disponibles");
		} else {
			for (Tarea t : tareas) {
				System.out.println(t);
			}
		}
	}
	
	public Tarea buscarTarea(List<Tarea> tareas, int id) {
		for (Tarea t : tareas) {
			if(t.getId()==id) {
				return t;
			}
		}
		return null;
	}
	
	public boolean completarTarea(int id) {
		Tarea t = buscarTarea(tareas, id);
		if (t!=null) {
		if(t.isCompletada()) {
			System.out.println("La tarea "+ id + " ya estaba completada");
			return false;
		}
		t.completar();
		return true;
		}
		else {
			System.out.println("No se ha encontrado una tarea con ese id");
			return false;
		}
	}
}

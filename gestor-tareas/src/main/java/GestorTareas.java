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

	public List<Tarea> getTareas() {
	    return List.copyOf(tareas);   // copia que no se puede modificar
	}

	public Tarea buscarTarea(int id) {
	    for (Tarea t : tareas) {
	        if (t.getId() == id) {
	            return t;
	        }
	    }
	    return null;
	}

}

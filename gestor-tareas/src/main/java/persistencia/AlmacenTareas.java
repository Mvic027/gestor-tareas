package persistencia;

import modelo.Tarea;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlmacenTareas {
	private final static Path FICHERO = Path.of("tareas.txt");
	private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void guardar(List<Tarea> tareas) {
		List<String> lineas = new ArrayList<>();

		for (Tarea t : tareas) {
			String fecha;
			if (t.getFechaLimite() == null) {
				fecha = "";
			} else {
				fecha = t.getFechaLimite();
			}
			lineas.add(fecha + ";" + t.isCompletada() + ";" + t.getDescripcion());
		}
		try {
			Files.write(FICHERO, lineas);
		} catch (IOException e) {
			System.err.println("Error al guardar las tareas: " + e.getMessage());
		}

	}

	public List<Tarea> cargar() {
		List<Tarea> tareas = new ArrayList<>();

		if (!Files.exists(FICHERO)) {
			return tareas;
		}
		try {
			for (String linea : Files.readAllLines(FICHERO)) {
				String[] partes = linea.split(";", 3);
				String descripccion = partes[2];
				Boolean completada = Boolean.parseBoolean(partes[1]);
				LocalDate fecha;
				if (partes[0].isEmpty()) {
					fecha = null;
				} else {
					fecha = LocalDate.parse(partes[0],FORMATO);
				}
				Tarea t = new Tarea(descripccion, fecha);
				if (completada) {
					t.completar();
				}
				tareas.add(t);
			}
		} catch (IOException e) {
			System.err.println("Error al cargar las tareas: " + e.getMessage());
		}
		return tareas;
	}

}

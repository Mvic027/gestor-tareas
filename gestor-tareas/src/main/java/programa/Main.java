package programa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import modelo.GestorTareas;
import modelo.Tarea;

public class Main {
	private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GestorTareas gestor = new GestorTareas();

		boolean exit = false;
		while (!exit) {
			mostrarMenu();
			int opcion = leerEntero(sc, "Elige una opción: ");

			switch (opcion) {
			case 1 -> anadirTarea(sc, gestor);
			case 2 -> listarTareas(gestor);
			case 3 -> completarTarea(sc, gestor);
			case 4 -> eliminarTarea(sc, gestor);
			case 5 -> editarTarea(sc, gestor);
			case 0 -> {
				System.out.println("Saliendo del programa");
				exit = true;
			}
			default -> System.out.println("Opción no válida, introduzca una de las opciones");
			}
		}
		sc.close();
	}

	// ===================== Menú =====================

	private static void mostrarMenu() {
		System.out.println("\n=== GESTOR DE TAREAS ===");
		System.out.println("1. Añadir tarea");
		System.out.println("2. Listar tareas");
		System.out.println("3. Completar tarea");
		System.out.println("4. Eliminar tarea");
		System.out.println("5. Editar tarea");
		System.out.println("0. Salir");
	}

	// ===================== Opciones =====================

	private static void anadirTarea(Scanner sc, GestorTareas gestor) {
		String desc = leerDescripcion(sc, "Descripción de la tarea: ");
		if (desc == null) {
			return;
		}
		LocalDate fecha = leerFecha(sc, "Fecha límite (dd/mm/aaaa) o Enter para omitir: ");
		Tarea t = gestor.anadir(desc, fecha);
		System.out.println("Tarea creada: " + t);
	}

	private static void listarTareas(GestorTareas gestor) {
		List<Tarea> lista = gestor.getTareas();
		if (lista.isEmpty()) {
			System.out.println("No hay tareas disponibles");
			return;
		}
		System.out.println("Listando tareas:");
		for (Tarea t : lista) {
			String aviso = t.isAtrasada() ? " ¡ATRASADA!" : "";
			System.out.println(t + aviso);
		}
	}

	private static void completarTarea(Scanner sc, GestorTareas gestor) {
		Tarea t = pedirTarea(sc, gestor, "Introduzca el id de la tarea que desea completar: ");
		if (t == null) {
			return;
		}
		if (t.isCompletada()) {
			System.out.println("La tarea " + t.getId() + " ya estaba completada.");
			return;
		}
		gestor.completarTarea(t);
		System.out.println("Completada: " + t);
	}

	private static void eliminarTarea(Scanner sc, GestorTareas gestor) {
		Tarea t = pedirTarea(sc, gestor, "Introduzca el id de la tarea que desea eliminar: ");
		if (t == null) {
			return;
		}
		gestor.eliminarTarea(t);
		System.out.println("Eliminada: " + t);
	}

	private static void editarTarea(Scanner sc, GestorTareas gestor) {
		Tarea t = pedirTarea(sc, gestor, "Introduzca el id de la tarea que desea editar: ");
		if (t == null) {
			return;
		}

		boolean salir = false;
		while (!salir) {
			System.out.println("\nEditando: " + t);
			System.out.println("1. Editar descripción");
			System.out.println("2. Editar fecha límite");
			System.out.println("0. Cancelar");
			int opcion = leerEntero(sc, "Elige una opción: ");

			switch (opcion) {
			case 1 -> {
				String desc = leerDescripcion(sc, "Nueva descripción: ");
				if (desc != null) {
					gestor.actualizarDescripcion(t, desc);
					System.out.println("Tarea actualizada: " + t);
				}
				salir = true;
			}
			case 2 -> {
				LocalDate fecha = leerFecha(sc, "Nueva fecha (dd/mm/aaaa) o Enter para quitarla: ");
				gestor.actualizarFecha(t, fecha);
				System.out.println("Tarea actualizada: " + t);
				salir = true;
			}
			case 0 -> salir = true;
			default -> System.out.println("Opción no válida, introduzca una de las opciones");
			}
		}
	}

	// ===================== Utilidades de lectura =====================

	/**
	 * Pide un id y devuelve la tarea correspondiente, o null si no hay tareas
	 * o no existe ninguna con ese id (mostrando el aviso correspondiente).
	 */
	private static Tarea pedirTarea(Scanner sc, GestorTareas gestor, String mensaje) {
		if (gestor.getTareas().isEmpty()) {
			System.out.println("No hay tareas disponibles");
			return null;
		}
		int id = leerEntero(sc, mensaje);
		Tarea t = gestor.buscarTarea(id);
		if (t == null) {
			System.out.println("No existe ninguna tarea con el id " + id + ".");
		}
		return t;
	}

	/**
	 * Lee una descripción. Devuelve null (y avisa) si está vacía.
	 */
	private static String leerDescripcion(Scanner sc, String mensaje) {
		System.out.print(mensaje);
		String desc = sc.nextLine().trim();
		if (desc.isBlank()) {
			System.out.println("La descripción no puede estar vacía.");
			return null;
		}
		return desc;
	}

	private static int leerEntero(Scanner sc, String mensaje) {
		while (true) {
			System.out.print(mensaje);
			String linea = sc.nextLine().trim();
			try {
				return Integer.parseInt(linea);
			} catch (NumberFormatException e) {
				System.out.println("Introduce un número válido.");
			}
		}
	}

	private static LocalDate leerFecha(Scanner sc, String mensaje) {
		while (true) {
			System.out.print(mensaje);
			String linea = sc.nextLine().trim();
			if (linea.isEmpty()) {
				return null;
			}
			try {
				return LocalDate.parse(linea, FORMATO);
			} catch (DateTimeParseException e) {
				System.out.println("Introduce una fecha válida.");
			}
		}
	}
}
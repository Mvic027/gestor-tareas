package programa;

import java.util.List;
import java.util.Scanner;

import modelo.GestorTareas;
import modelo.Tarea;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GestorTareas gestor = new GestorTareas();

		boolean exit = false;
		while (!exit) {
			System.out.println("\n=== GESTOR DE TAREAS ===");
			System.out.println("1. Añadir tarea");
			System.out.println("2. Listar tareas");
			System.out.println("3. Completar Tarea");
			System.out.println("4. Eliminar Tarea");
			System.out.println("0. Salir");

			int opcion = leerEntero(sc, "Elige una opción: ");

			switch (opcion) {
			case 0: {
				System.out.println("Saliendo del programa");
				exit = true;
				break;
			}
			case 1: {
				System.out.print("Añada una descripción a la tarea que desea añadir: ");
				String desc = sc.nextLine().trim();
				if (desc.isBlank()) {
					System.out.println("La descripción no puede estar vacía.");
					break;
				}
				System.out.print("Fecha límite (dd/mm/aaaa) o Enter para omitir: ");
				String fech = sc.nextLine().trim();

				Tarea t = gestor.anadir(desc, fech);
				System.out.println("Tarea creada: " + t);

				break;
			}
			case 2: {
				System.out.println("Listando tareas:");
				List<Tarea> lista = gestor.getTareas();
				if (lista.isEmpty()) {
					System.out.println("No hay tareas disponibles");
				} else {
					for (Tarea t : lista) {
						System.out.println(t);
					}
				}
				break;
			}
			case 3: {
				int id = leerEntero(sc, "Introduzca el id de la tarea que desea completar: ");

				Tarea t = gestor.buscarTarea(id);
				if (t == null) {
					System.out.println("No existe ninguna tarea con id " + id + ".");
				} else if (t.isCompletada()) {
					System.out.println("La tarea " + id + " ya estaba completada.");
				} else {
					gestor.completarTarea(t);
					System.out.println("Completada: " + t);
				}
				break;
			}
			case 4: {
				if (gestor.getTareas().isEmpty()) {
					System.out.println("No hay tareas que eliminar");
				} 
				else {
					System.out.print("Introduzca el id de la tarea que desea eliminar: ");
					int id = sc.nextInt();
					sc.nextLine();
					Tarea t = gestor.buscarTarea(id);
					if (t == null) {
						System.out.println("No existe ninguna tarea con id " + id + ".");
					} else {
						gestor.eliminarTarea(t);
						System.out.println("Eliminada " + t);

					}
				}
				break;
			}
			default:

				System.out.println("Opción no válida, introduzca una de las opciones");
			}
		}
		sc.close();

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
}

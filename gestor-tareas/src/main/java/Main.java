
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Tarea> tareas = new ArrayList<Tarea>();
		boolean exit = false;
		while (!exit) {
			System.out.println("\n=== GESTOR DE TAREAS ===");
			System.out.println("1. Añadir tarea");
			System.out.println("2. Listar tareas");
			System.out.println("3. Salir");
			System.out.print("Elige una opción: ");
			int opcion = sc.nextInt();
			sc.nextLine();
			switch (opcion) {
			case 1: {
				System.out.print("Añada una descripcion a la tarea que desea añadir:");
				String desc = sc.nextLine();
				System.out.print("Fecha límite (dd/mm/aaaa) o Enter para omitir:");
				String fech = sc.nextLine();
				Tarea tarea;
				if (fech.isEmpty()) {
					tarea = new Tarea(desc);
				} else {
					tarea = new Tarea(desc, fech);
				}
				tareas.add(tarea);
				break;
			}
			case 2: {
				System.out.println("Listando Tareas:");
				if (tareas.isEmpty()) {
					System.out.println("No hay tareas disponibles");
				} else {
					for (Tarea t : tareas) {
						System.out.println(t);
					}
				}
				break;
			}
			case 3: {
				System.out.println("Saliendo del programa");
				exit = true;
				break;
			}
			default:

				System.out.println("Opcion no valida, introduzca una de las opciones");
			}
		}
		sc.close();

	}
}

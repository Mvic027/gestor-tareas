
import java.util.Scanner;

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
			System.out.println("0. Salir");
			System.out.print("Elige una opción: ");
			
			int opcion = sc.nextInt();
			sc.nextLine();
			
			switch (opcion) {
			case 0: {
				System.out.println("Saliendo del programa");
				exit = true;
				break;
			}
			case 1: {
				System.out.print("Añada una descripcion a la tarea que desea añadir:");
				String desc = sc.nextLine();
				System.out.print("Fecha límite (dd/mm/aaaa) o Enter para omitir:");
				String fech = sc.nextLine();
				
				gestor.anadir(desc, fech);
				
				break;
			}
			case 2: {
				System.out.println("Listando Tareas:");
				gestor.listar();
				break;
			}
			case 3: {
				System.out.print("Introduzca el id de la tarea que desea completar: ");
				int id=sc.nextInt();
				gestor.completarTarea(id);
				break;
			}
			default:

				System.out.println("Opcion no valida, introduzca una de las opciones");
			}
		}
		sc.close();

	}
}

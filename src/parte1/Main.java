package parte1;

public class Main {

	public static void main(String[] args) {
		Servicios servicios = new Servicios(null, null);

        // Crear tareas de ejemplo
        Tarea tarea1 = new Tarea("T001", "Tarea Limpieza", 30, false, 2);
        Tarea tarea2 = new Tarea("T002", "Tarea Cocinar", 60, true, 1);
        Tarea tarea3 = new Tarea("T003", "Tarea Estudiar", 120, false, 3);

        // Insertar tareas en el HashMap
        servicios.addTarea(tarea1);
        servicios.addTarea(tarea2);
        servicios.addTarea(tarea3);

        // Imprimir tareas
        System.out.println("Tareas:");
        for (Tarea tarea : servicios.getTareaMapId().values()) {
            System.out.println("  - Id: " + tarea.getId() + ", Nombre: " + tarea.getNombre());
        }

        // Buscar una tarea por id
        String idABuscar = "T001";
        Tarea tareaBuscada = servicios.servicio1(idABuscar);
        if (tareaBuscada != null) {
            System.out.println("\nTarea con id " + idABuscar + ":");
            System.out.println("  - Nombre: " + tareaBuscada.getNombre());
            System.out.println("  - Tiempo Ejecucion: " + tareaBuscada.getTiempoEjecucion() + " minutos");
            System.out.println("  - Es Critica: " + tareaBuscada.isEsCritica());
            System.out.println("  - Nivel Prioridad: " + tareaBuscada.getNivelPrioridad());
        } else {
            System.out.println("\nTarea con id " + idABuscar + " no encontrada");
        }
	}

}

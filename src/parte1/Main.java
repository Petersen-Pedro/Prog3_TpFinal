package parte1;

public class Main {

	public static void main(String[] args) {
		
		Servicios servicios = new Servicios(("./src/archivosCSV/Procesadores.csv"), ("./src/archivosCSV/Tareas.csv"));

        
		servicios.servicio1(null);
		servicios.servicio2(false);
		servicios.servicio2(true);
		servicios.servicio3(33,62);
        
	}

}

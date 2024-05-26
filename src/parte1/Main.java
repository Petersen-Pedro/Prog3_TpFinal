package parte1;

public class Main {

	public static void main(String[] args) {
		
		Servicios servicios = new Servicios(
				("././archivosCSV/Procesadores.csv"), 
				("././archivosCSV/Tareas.csv")
				);
		

		System.out.println("Servicio 1 = 10");
	 	System.out.println(servicios.servicio1("10"));
        //System.out.println(servicios.servicio2(false));
		System.out.println("Servicio 2 = True");
        System.out.println(servicios.servicio2(true));
		System.out.println("Servicio 3 = 33 y 80");
        System.out.println(servicios.servicio3(33,80));
        /*
		servicios.servicio1(null);
		servicios.servicio2(false);
		servicios.servicio2(true);
		servicios.servicio3(33,62);
        */
	}

}

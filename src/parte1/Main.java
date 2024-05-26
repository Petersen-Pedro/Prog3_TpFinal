package parte1;

public class Main {

	public static void main(String[] args) {
		
		//Si ves estas rutas absolutas, es porque me olvide de borrarlas buscando solucionar un error que frenaba todo.
		Servicios servicios = new Servicios(("D:\\eclipse-workspace\\Prog3_TpFinal\\archivosCSV\\Procesadores.csv"), ("D:\\eclipse-workspace\\Prog3_TpFinal\\archivosCSV\\Tareas.csv"));

		System.out.println("Servicio 1 = 10");
	 	System.out.println(servicios.servicio1("10"));
        //System.out.println(servicios.servicio2(false));
		System.out.println("Servicio 2 = True");
        System.out.println(servicios.servicio2(true));
		System.out.println("Servicio 3 = 33 y 62");
        System.out.println(servicios.servicio3(1,100));
        /*
		servicios.servicio1(null);
		servicios.servicio2(false);
		servicios.servicio2(true);
		servicios.servicio3(33,62);
        */
	}

}

package tpe;

public class Main {

	public static void main(String[] args) {
		

		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", 
											"./src/tpe/datasets/Tareas.csv");
		
		System.out.println("X----------------------------------------");
		System.out.println("Servicio 1 = T1");
	 	System.out.println(servicios.servicio1("T1"));
		System.out.println("X----------------------------------------");
		System.out.println("Servicio 2 = True");
        System.out.println(servicios.servicio2(true));
		System.out.println("X----------------------------------------");
		System.out.println("Servicio 3 = 33 y 80");
        System.out.println(servicios.servicio3(33,80));
		System.out.println("X----------------------------------------");
		System.out.println("Servicio 4 backtrakcking = 100");
		System.out.println(servicios.servicio4Backtraking(100));
		System.out.println("X----------------------------------------");
		System.out.println("Servicio 4 Greedy = 100");
		System.out.println(servicios.servicio4Greedy(100));
        
		
        
	}

}

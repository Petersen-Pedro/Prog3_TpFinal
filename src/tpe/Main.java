package tpe;

import tpe.utils.Backtracking_Greedy;

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
		System.out.println("Servicio 4 =");
        Backtracking_Greedy servicio4 = new Backtracking_Greedy();
        
        System.out.println(servicio4.back(null, null, 0));
        
	}

}

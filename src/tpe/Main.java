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
		System.out.println(servicios.servicio4Backtraking(200));
		System.out.println("X----------------------------------------");
		System.out.println("Servicio 4 Greedy = 100");
		System.out.println(servicios.servicio4Greedy(200));
        
		/*
		 	T1;Tarea1;50;true;92
			T2;Tarea2;10;false;31
			T3;Tarea3;100;false;70
			T4;Tarea4;20;true;58
			
			P1;COD_P1;false;2010
			P2;COD_P2;true;2020
			P3;COD_P3;true;2022
			P4;COD_P4;false;2015
		 */
		
		
        
	}

}

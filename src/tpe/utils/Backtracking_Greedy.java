package tpe.utils;

import java.util.LinkedList;

import tpe.Procesador;
import tpe.Tarea;


public class Backtracking_Greedy {
    private int mejorTiempoDeEjecucion;
    private LinkedList<Procesador> mejorSolucion;
    private final int maximoDeCriticas;
    
    public Backtracking_Greedy() {
        this.mejorTiempoDeEjecucion = Integer.MAX_VALUE;
        this.mejorSolucion = new LinkedList<Procesador>();
        this.maximoDeCriticas = 1;
    }

    // CONSIDERANDO EN TODO MOMENTO QUE UNA TAREA ES INDIVISIBLE, 
    // ES DECIR, NO PUEDE SER ASIGNADA A VARIOS PROCESADORES
    
    public LinkedList<Procesador> back( LinkedList<Tarea> tareas, 
    									LinkedList<Procesador> procesadores,
    									int tiempoMaxProcNoRefrigerado) {
        int tiempoActual = 0;
        // un contador de tareasAsignadas que si no es 0 quiere decir que quedan tareas por asignar
        int tareasAsignadas = tareas.size(); 
        
        back(tareas, procesadores, tiempoActual, tareasAsignadas, 
        	 tiempoMaxProcNoRefrigerado);

        if(mejorSolucion.isEmpty()) {
        	System.out.println("Sucedio un error en la carga de tareas a procesadores!!!!");
        }
        return mejorSolucion;
    }

    private void back(LinkedList<Tarea> tareas, 
    				  LinkedList<Procesador> procesadores, int tiempoActual, 
    				  int tareasAsignadas, int tiempoMaxProcNoRefrigerado) {
        if (tareasAsignadas == 0) { 								// cuando no quedan mas tareas que asignar
            if (tiempoActual < this.mejorTiempoDeEjecucion) {      // y el tiempo de la llamda actual es menor que el guardado anteriormente ( mejorTiempoDeEjecucion se inicializa en Integer.MAX_VALUE
                this.mejorTiempoDeEjecucion = tiempoActual;			//mejor tiempo ahora es el de la llamda actual
                actualizarMejorSolucion(procesadores);				//guardo esos procesadores con esas tareas
            }
        } else {
            Tarea tareaActual = tareas.get(tareasAsignadas - 1);     //la tarea arranca en el ultimo indice 

            for (int i = 0; i < procesadores.size(); i++) {			
                Procesador procesador = procesadores.get(i);

                if ((!procesador.getTareas().contains(tareaActual) ) && 		//si el procesador no tiene guardada esa tarea 
                	 (procesador.getTareasCriticas() < maximoDeCriticas) ) {					// y no tiene mas de 2 tareas criticas (utilizo 2 ya que es un valor constante y no se aclara que pueda cambiar )		
                	if(procesador.isEstaRefrigerado()) {				//esto es en caso de q este refrigerado
	                    procesador.agregarTarea(tareaActual);												//agrego la tarea al procesador
	                    int nuevoTiempoProcesador = procesador.getTiempoDeEjecucionAcumulado();				//guardo el tiempo de ejecucion acumulado de mi procesador actual
	                    // tiempoActual cambiar nombre !!
	                    int tiempoMaximoActual = Math.max(tiempoActual, nuevoTiempoProcesador); 		//comparo el tiempo que tengo acumulado de mi procesador con mas tiempo anterior a esta ejecucion
	                     																				//con el procesador de la ejecucion actual , y guarda el mayor de los dos
	                    tareasAsignadas--;											
	                    if (tiempoMaximoActual <= this.mejorTiempoDeEjecucion) {			//mi poda ya que si el tiempo mayor acumulado es mayor a mi mejor tiempo esta rama no me va a dar una mejor solucion!
	                        back(tareas, procesadores, tiempoMaximoActual, 
	                        	 tareasAsignadas, tiempoMaxProcNoRefrigerado);
	                    }

	                    procesador.eliminarTarea(tareaActual); 								//deshago los cambios para poder continuar con la recursion
	                    tareasAsignadas++;
                		}else if( ( procesador.getTiempoDeEjecucionAcumulado() + 
                					tareaActual.getTiempoEjecucion()  ) <= tiempoMaxProcNoRefrigerado) {		//esto es en caso de que NO este refrigerado y el tiempo actual mas la siguiente tarea sea menor a
		                    procesador.agregarTarea(tareaActual);												//al tiempo maximo de refrigeracion
		                    int nuevoTiempoProcesador = procesador.getTiempoDeEjecucionAcumulado();
		                    int tiempoMaximoActual = Math.max(tiempoActual, nuevoTiempoProcesador);
		                  
		                    tareasAsignadas--;
		                    if (tiempoMaximoActual <= this.mejorTiempoDeEjecucion) {
		                        back(tareas, procesadores, tiempoMaximoActual, 
		                        	 tareasAsignadas, tiempoMaxProcNoRefrigerado);
		                    }
		                    procesador.eliminarTarea(tareaActual);
		                    tareasAsignadas++;
                		
                	}
                }
            }
        }
    }

    private void actualizarMejorSolucion(LinkedList<Procesador> procesadores) {
        mejorSolucion.clear();
        for (Procesador p : procesadores) {
            mejorSolucion.add(p.copiar());
        }
    }

    public LinkedList<Procesador> greedy(LinkedList<Tarea> tareas, LinkedList<Procesador> procesadores,int tiempoMaxProcNoRefrigerado) {
		LinkedList<Procesador> s = new LinkedList<>();					
		int cantidadTareas = tareas.size()-1;
		int peorTiempo = Integer.MAX_VALUE;
		int tiempoProcesador = Integer.MAX_VALUE;
		boolean criticas = analizarCriticas(tareas,procesadores.size());
		
		while(cantidadTareas >=0 && criticas == false ) {//mientras tenga tareas y la cantidad de criticas sea menor a la cantidad de criticas que pueden soportar mis procesadores (2 maximo por procesador)
			
			
			for(int i = 0; i<procesadores.size() && cantidadTareas >=0; i++) {		
				
				
				if( ( procesadores.get(i).getTiempoDeEjecucionAcumulado() < peorTiempo ) 						//si el tiempo acumulado que tiene mi procesador es menor 
					&& ( procesadores.get(i).getTiempoDeEjecucionAcumulado() <= tiempoProcesador ) 				//al peor tiempo que tengo guardado que lleva un procesador
					&& (procesadores.get(i).getTareasCriticas() < 2) ) {						//y menor o igual al tiempo de mi procesador anterior y no supera las 2 tareas criticas 
					
					if(procesadores.get(i).isEstaRefrigerado()) {					//si esta refrigerado
						procesadores.get(i).agregarTarea(tareas.get(cantidadTareas));
						tiempoProcesador = procesadores.get(i).getTiempoDeEjecucionAcumulado();
						peorTiempo = Math.max(peorTiempo,tiempoProcesador);			//me quedo con el peor tiempo
						
						cantidadTareas--;			//saco una tarea
					}else if( ( procesadores.get(i).getTiempoDeEjecucionAcumulado() + tareas.get(cantidadTareas).getTiempoEjecucion()  ) <= tiempoMaxProcNoRefrigerado) {  //si NO esta refrigerado que no supere el 
																																								//tiempo maximo de procesadores no refrigerados
						procesadores.get(i).agregarTarea(tareas.get(cantidadTareas));	
						tiempoProcesador = procesadores.get(i).getTiempoDeEjecucionAcumulado();
						peorTiempo = Math.max(peorTiempo,tiempoProcesador);
						
						cantidadTareas--;		//saco una tarea
					}
				}
				
			}
		}
		s.addAll(procesadores);

		
		return s ;
	}

	private boolean analizarCriticas(LinkedList<Tarea> tareas, int cantProcesadores) {
		int contadorCriticas = 0;
		for(Tarea t : tareas) {
			
			if(t.isEsCritica()) {
				contadorCriticas += 1;
			}
			
		}
		if(contadorCriticas > cantProcesadores *2) {
            throw new IllegalStateException("Hay "+contadorCriticas +" criticas para "+ cantProcesadores+" procesadores" );
		}
		
		return false;
	}
}

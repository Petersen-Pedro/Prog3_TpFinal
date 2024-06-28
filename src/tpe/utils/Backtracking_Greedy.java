package tpe.utils;

import java.util.LinkedList;

import tpe.Procesador;
import tpe.Tarea;


public class Backtracking_Greedy {
    private int mejorTiempoDeEjecucion;
    private LinkedList<Procesador> mejorSolucion;
    
    public Backtracking_Greedy() {
        this.mejorTiempoDeEjecucion = Integer.MAX_VALUE;
        this.mejorSolucion = new LinkedList<Procesador>();
    }

    // CONSIDERANDO EN TODO MOMENTO QUE UNA TAREA ES INDIVISIBLE, 
    // ES DECIR, NO PUEDE SER ASIGNADA A VARIOS PROCESADORES
    
    public LinkedList<Procesador> back(LinkedList<Tarea> tareas, LinkedList<Procesador> procesadores,int tiempoMaxProcNoRefrigerado) {
        int tiempoActual = 0;
        int tareasAsignadas = tareas.size(); // un contador de tareasAsignadas que si no es 0 quiere decir que quedan tareas por asignar
        
        back(tareas, procesadores, tiempoActual, tareasAsignadas, tiempoMaxProcNoRefrigerado);
        if(mejorSolucion.isEmpty()) {
        	System.out.println("Mas tareas criticas de lo que pueden soportar los procesadores!!!!!");
        }
        System.out.println("el mejor tiempo es "+this.mejorTiempoDeEjecucion);
        return mejorSolucion;
    }

    private void back(LinkedList<Tarea> tareas, LinkedList<Procesador> procesadores, int tiempoActual, int tareasAsignadas, int tiempoMaxProcNoRefrigerado) {
        if (tareasAsignadas == 0) {
        	System.out.println(procesadores);
        	if (tiempoActual <= this.mejorTiempoDeEjecucion) {
                this.mejorTiempoDeEjecucion = tiempoActual;
                actualizarMejorSolucion(procesadores); 
            }
        } else {
            Tarea tareaActual = tareas.get(tareasAsignadas - 1);

            for (int i = 0; i < procesadores.size(); i++) {
                Procesador procesador = procesadores.get(i);
                
                if (!procesador.getTareas().contains(tareaActual) &&
                    (!tareaActual.isEsCritica() || procesador.getTareasCriticas() < 2) &&
                    (procesador.isEstaRefrigerado() || (procesador.getTiempoDeEjecucionAcumulado() + tareaActual.getTiempoEjecucion()) <= tiempoMaxProcNoRefrigerado)) {
                	
	                    procesador.agregarTarea(tareaActual);
	                    int nuevoTiempoProcesador = procesador.getTiempoDeEjecucionAcumulado();
	                    int tiempoMaximoActual = Math.max(tiempoActual, nuevoTiempoProcesador); // tiempoActual cambiar nombre !!
	                    
	                    tareasAsignadas--;
	                    if (tiempoMaximoActual <= this.mejorTiempoDeEjecucion) {
	                        back(tareas, procesadores, tiempoMaximoActual, tareasAsignadas, tiempoMaxProcNoRefrigerado);
	                    }

	                    procesador.eliminarTarea(tareaActual);
	                    tareasAsignadas++;
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

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
    public LinkedList<Procesador> back( LinkedList<Tarea> tareas, 
    									LinkedList<Procesador> procesadores,
    									int tiempoMaxProcNoRefrigerado) {
        int tiempoActual = 0;
        // un contador de tareasAsignadas que si no es 0 quiere 
        // decir que quedan tareas por asignar
        int tareasAsignadas = tareas.size(); 
        back(tareas, procesadores, tiempoActual, tareasAsignadas, 
        	 tiempoMaxProcNoRefrigerado);
        if(mejorSolucion.isEmpty()) {
        	System.out.println("Mas tareas criticas de lo que pueden "
        					 + "soportar los procesadores!!!!!");
        }
        return mejorSolucion;
    }

    private void back(LinkedList<Tarea> tareas, 
    				  LinkedList<Procesador> procesadores, int tiempoActual, 
    				  int tareasAsignadas, int tiempoMaxProcNoRefrigerado) {
        if (tareasAsignadas == 0) {
            if (tiempoActual < this.mejorTiempoDeEjecucion) {
                this.mejorTiempoDeEjecucion = tiempoActual;
                actualizarMejorSolucion(procesadores);
            }
        } else {
            Tarea tareaActual = tareas.get(tareasAsignadas - 1);

            for (int i = 0; i < procesadores.size(); i++) {
                Procesador procesador = procesadores.get(i);

                if ((!procesador.getTareas().contains(tareaActual) ) && 
                	 (procesador.getTareasCriticas() < 2) ) {
                	if(procesador.isEstaRefrigerado()) {
	                    procesador.agregarTarea(tareaActual);
	                    int nuevoTiempoProcesador = procesador.getTiempoDeEjecucionAcumulado();
	                    // tiempoActual cambiar nombre !!
	                    int tiempoMaximoActual = Math.max(tiempoActual, nuevoTiempoProcesador); 
	                     
	                    tareasAsignadas--;
	                    if (tiempoMaximoActual <= this.mejorTiempoDeEjecucion) {
	                        back(tareas, procesadores, tiempoMaximoActual, 
	                        	 tareasAsignadas, tiempoMaxProcNoRefrigerado);
	                    }

	                    procesador.eliminarTarea(tareaActual);
	                    tareasAsignadas++;
                		}else if( ( procesador.getTiempoDeEjecucionAcumulado() + 
                					tareaActual.getTiempoEjecucion()  ) <= tiempoMaxProcNoRefrigerado) {
		                    procesador.agregarTarea(tareaActual);
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
}

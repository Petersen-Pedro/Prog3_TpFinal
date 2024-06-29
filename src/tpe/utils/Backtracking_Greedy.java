package tpe.utils;

import java.util.Collections;
import java.util.LinkedList;

import tpe.Procesador;
import tpe.Tarea;


public class Backtracking_Greedy {
	private int estados;
	private int peorTiempo;
	/*backTracking atributos*/
	private int mejorTiempoDeEjecucion;
    private LinkedList<Procesador> mejorSolucion;
    
    
    public Backtracking_Greedy() {
        this.mejorTiempoDeEjecucion = Integer.MAX_VALUE;
        this.mejorSolucion = new LinkedList<Procesador>();
        this.estados = 0;
        this.peorTiempo = -1;
    }
	/*
	 	Greedy:
	 	-La estrategia del metodo greedy es:
	 	-ordenar los procesadores poniendo primero los refrigerados(para que guarden las tareas de mayor valor los que no tienen limite)
	 	y ordenando las tareas de manera descendiente poniendo primero las tareas con mayor tiempo.
	 	-recorrer hasta que la lista de tareas este sin mas tareas, y en caso de no poder asignar una tarea se devuelve null
	 	-si se puede asignar una tarea lo va a hacer eligiendo el mejorProcesador para esa tarea el metodo "mejorProcesadorParaInsertar()"
	 	lo que va a hacer es buscar el procesador con menor tiempo acumulado y que cumpla los requisitos del sistema(menos de 2 tareas criticas y 
	 	que no supere x tiempo en caso de no ser refrigerado)
	 	-
	 */

	public LinkedList<Procesador> greedy(LinkedList<Tarea> tareas, LinkedList<Procesador> procesadores, int tiempoMaxProcNoRefrigerado) {
		this.estados = 0;
		this.peorTiempo = 0;
		LinkedList<Procesador> s = new LinkedList<>();
        Collections.sort(tareas, (t1, t2) -> Integer.compare(t2.getTiempoEjecucion(), t1.getTiempoEjecucion()));
        Collections.sort(procesadores, (p1, p2) -> Boolean.compare(p2.isEstaRefrigerado(), p1.isEstaRefrigerado()));
		while(!tareas.isEmpty()) {
		
			Tarea t  = tareas.getFirst();
			
			Procesador procesador = mejorProcesadorParaInsertar(procesadores,t,tiempoMaxProcNoRefrigerado);
			
			if(procesador != null ) {
				procesador.agregarTarea(t);
				tareas.removeFirst();
			}else {
				return null;
			}
			if(procesador.getTiempoDeEjecucionAcumulado()>peorTiempo) {
				this.peorTiempo = procesador.getTiempoDeEjecucionAcumulado();
			}
								
	}
			
		s.addAll(procesadores);
		
		return s;
	}

	
	private Procesador mejorProcesadorParaInsertar(LinkedList<Procesador> procesadores,Tarea tareaAInsertar,int tiempoMaxProcNoRefrigerado) {
		int menorTiempoEjecucion = Integer.MAX_VALUE;
		Procesador mejorProcesador = null;
		for(Procesador p : procesadores) {
			if( ( p.getTiempoDeEjecucionAcumulado() < menorTiempoEjecucion ) &&
				(!tareaAInsertar.isEsCritica() || p.getTareasCriticas() < 2) &&
                (p.isEstaRefrigerado() || (p.getTiempoDeEjecucionAcumulado() + tareaAInsertar.getTiempoEjecucion()) <= tiempoMaxProcNoRefrigerado)) {
				menorTiempoEjecucion = p.getTiempoDeEjecucionAcumulado();
				mejorProcesador = p;
				this.estados++;
			}
		}
		return mejorProcesador;
	}

	public int cantidadDeEstados() {
		return this.estados;
		
	}
	public int getPeorTiempo() {
		return this.peorTiempo;
	}
//BACKTRACKING!

	/*
	 	BackTracking:
	 	-La estrategia del metodo BackTracking es:
	 	-llevar un numero de tareas a asignar(size de la lista de tareas),un tiempo de la busqueda actual y el peorTiempo encontrado hasta el momento.
	 	-va a recorrer hasta que no tenga mas tareas y va a ser una solucion si el tiempo encontrado es mejor que el mejorTiempo de la recursion anterior
 		 En caso de quedar mas tareas a asignar va a recorrer por cada uno de los procesadores preguntando si no contienen la tarea,si respetan la cant maxima de tareas criticas
 		 y si no es refrigerado,que no supere el tiempo maximo.Si cumple se saca una tarea de la lista de tareas a asignar y se va guardando el valor del mayorTiempo,si un procesador 
 		 ya supera el mejorTiempoDeEjecucion se hace poda ya que no va a ser una solucion mejor a la ya encontrada 
	 	-Los procesadores van a guardar sus tareas en caso de haber una mejor solucion.se actualiza la solucion guardada ,poniendo como solucion la mejor manera de guardar tareas en cada procesador.
	 */
	
	
    public LinkedList<Procesador> back(LinkedList<Tarea> tareas, LinkedList<Procesador> procesadores,int tiempoMaxProcNoRefrigerado) {
    	this.estados = 0;
    	this.peorTiempo = 0;
        int tiempoActual = 0;
        int tareasAsignadas = tareas.size(); // un contador de tareasAsignadas que si no es 0 quiere decir que quedan tareas por asignar
        
        back(tareas, procesadores, tiempoActual, tareasAsignadas, tiempoMaxProcNoRefrigerado);
        if(mejorSolucion.isEmpty()) {
        	System.out.println("Mas tareas criticas de lo que pueden soportar los procesadores!!!!!");
        }
        this.peorTiempo = this.mejorTiempoDeEjecucion;
        return mejorSolucion;
    }

    private void back(LinkedList<Tarea> tareas, LinkedList<Procesador> procesadores, int tiempoActual, int tareasAsignadas, int tiempoMaxProcNoRefrigerado) {
        if (tareasAsignadas == 0) {
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
	                    if (tiempoMaximoActual < this.mejorTiempoDeEjecucion) {
	                    	this.estados++;
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
}

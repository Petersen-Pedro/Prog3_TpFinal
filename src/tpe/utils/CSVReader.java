package tpe.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import tpe.Procesador;
import tpe.Servicios;
import tpe.Tarea;

public class CSVReader {
	
	public CSVReader() {
	}
	
	ArrayList<String[]> readContent(String path) {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}
	
	public HashMap<String, Tarea> readTasks(String taskPath, Servicios servicios){
		
		HashMap<String, Tarea> contenidoTareas = new HashMap<String, Tarea>();
		ArrayList<String[]> lines = this.readContent(taskPath);
		
		for (String[] line: lines) {
			String id = line[0].trim();
			String nombre = line[1].trim();
			Integer tiempo = Integer.parseInt(line[2].trim());
			Boolean critica = Boolean.parseBoolean(line[3].trim());
			Integer prioridad = Integer.parseInt(line[4].trim());

			Tarea tarea = new Tarea(id, nombre, tiempo, 
									critica, prioridad);
			contenidoTareas.put(id, tarea);
			servicios.insertCritica(tarea);
			servicios.addTareaPrioridad(tarea);			
		}
		return contenidoTareas;
	}

	public LinkedList<Procesador> readProcessors(String processorPath, Servicios servicios) {
				
		LinkedList<Procesador> Procesadores = new LinkedList<>();
		ArrayList<String[]> lines = this.readContent(processorPath);
		
		for (String[] line: lines) {
			String id = line[0].trim();
			String codigo = line[1].trim();
			Boolean refrigerado = Boolean.parseBoolean(line[2].trim());
			Integer anio = Integer.parseInt(line[3].trim());

			Procesador procesador = new Procesador(id, codigo, refrigerado, anio);
			Procesadores.add(procesador);
		}
		return Procesadores;
		
	}	
}

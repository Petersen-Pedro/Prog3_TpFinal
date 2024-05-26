package parte1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVReader {

	//"Viva la Santa Stackoverflow, mueran los salvajes ChatGPTros"
	
	ArrayList<String[]> leerCSV(String path) {
		ArrayList<String[]> contenido = new ArrayList<String[]>();
		
		File archivo = new File(path);
		FileReader archivoLector = null;
		BufferedReader lineaLector = null;
		
		try {
			archivoLector = new FileReader(archivo);
			lineaLector = new BufferedReader(archivoLector);
			String linea = null;
			while((linea = lineaLector.readLine()) != null) {
				linea = linea.trim();
				contenido.add(linea.split(";"));
			}
		} catch(Exception e ) {
			
		}
		
		return contenido;
	}
}

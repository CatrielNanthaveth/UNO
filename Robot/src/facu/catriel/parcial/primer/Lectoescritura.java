package facu.catriel.parcial.primer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Lectoescritura {

	private String archivoEntrada;
	private String archivoSalida;
	
	public Lectoescritura(String archivoEntrada, String archivoSalida) {
		this.archivoEntrada = archivoEntrada;
		this.archivoSalida = archivoSalida;
	}

	
	public void escribirArchivo(String aEscribir) throws IOException {
		//  preparo el arch de salida
		FileWriter archivo = null;
		PrintWriter salida = null;  
		
		try {
			archivo = new FileWriter(this.archivoSalida);
			salida = new PrintWriter(archivo);  
				  
			salida.print(aEscribir);
							
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			salida.close(); 
		}

	} // Complejidad O(1)
	
	public ArrayList<String> leerArchivo() throws IOException {
		
		// Fichero del que queremos leer
		File fichero = new File(this.archivoEntrada);
		Scanner s = null;
		
		ArrayList<String> datos = new ArrayList<String>();
		try {
			// Leemos el contenido del fichero
			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
				String linea = s.nextLine(); 	// Guardamos la linea en un String
				datos.add(linea);
			}
			
			// Complejidad O(n)
			
	

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.err.println(ex2.getMessage());
			}
		}
		return datos;
	}
	
}

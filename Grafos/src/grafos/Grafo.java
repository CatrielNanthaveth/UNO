package grafos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Grafo {

	private int[][] adyacencia ;
	private Vertice[] nodos;
	private final static int infinito = 100000;
	
	public Grafo(Vertice[] nodos) {
		super();
		this.nodos = nodos;
		this.adyacencia = new int[nodos.length][nodos.length];
		for(int i = 0; i < nodos.length; i++) { 
			for(int j = 0; j < nodos.length; j++) { 
				this.adyacencia[i][j] = infinito;
			}
		}
	}
	
	public void agregarArista(Arista arista) {

		int aux1 = arista.getvDestino().getNumero();
		int aux2 = arista.getvOrigen().getNumero();
		
		
		if(verificarMatriz(aux1, aux2)) {
			this.adyacencia[aux1][aux2] = arista.getPeso();
		} else System.out.println("No es posible agregar la arista debido a la existencia de otra arista.");
	}
	
	public void eliminarArista(Arista arista) throws Exception {
		int aux1 = arista.getvDestino().getNumero();
		int aux2 = arista.getvOrigen().getNumero();
		
		try {
			if(this.adyacencia[aux1][aux2] != infinito) {
				this.adyacencia[aux1][aux2] = infinito;
			} else throw new Exception("No existe tal arista.");
		} catch (IndexOutOfBoundsException ex) {
			System.err.println(ex.getMessage());
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	// Algoritmo de dijkstra, recibe un nodo de origen
	
	public int[] dijkstra(Vertice nodoOrigen) {
		
		// guardo el número del nodo (nombre) de origen en una variable
		int origen = nodoOrigen.getNumero();
		
		int[] distancia = new int[nodos.length];
		boolean[] visitados = new boolean[nodos.length];
		String[] caminos = new String[nodos.length];
		
		for(int i = 0; i < nodos.length; i++) {
			distancia[i] = infinito;
			visitados[i] = false;
		}
		
		distancia[origen] = 0;
		caminos[origen] = "" + origen;
		
		for(int i = 0; i < nodos.length; i++) {
			int mIndex = distanciaMin(distancia, visitados);
			
			visitados[mIndex] = true;
			
			for (int j = 0; j < nodos.length; j++) {

				if (!visitados[j] && this.adyacencia[j][mIndex] > 0 && distancia[mIndex] != infinito){

					if(distancia[mIndex] + this.adyacencia[j][mIndex] < distancia[j]){

						distancia[j] = distancia[mIndex] + this.adyacencia[j][mIndex];

						caminos[j] = caminos[mIndex]+"-"+j;

					}
				}
			}
		}
		
		System.out.println("Caminos: " + caminos[caminos.length - 1]);
		return distancia;
	}
	
	public int distanciaMin(int[] distancias, boolean[] nodosVisitados) {
		
		int mIndex = 0;
		int min = infinito;
		
		for (int i = 0; i < nodos.length; i++)
			if (nodosVisitados[i] == false && distancias[i] <= min) {
				min = distancias[i];
				mIndex = i;
			}
		
		return mIndex;
	}
	
	public boolean verificarMatriz(int origen, int destino) {
		
		if(this.adyacencia[origen][destino] == infinito) {
			return true;
		} else return false;
		
	}

	@Override
	public String toString() {
		return "Grafo [adyacencia=" + Arrays.deepToString(adyacencia) + ", nodos=" + Arrays.toString(nodos) + "]";
	}

	public int getAdyacencia(int a, int b) {
		return adyacencia[a][b];
	}

	public void setAdyacencia(int a, int b, int valor) {
		this.adyacencia[a][b] = valor;
	}

	public Vertice[] getNodos() {
		return nodos;
	}

	public void setNodos(Vertice[] nodos) {
		this.nodos = nodos;
	}
	
	
	
	
	
}

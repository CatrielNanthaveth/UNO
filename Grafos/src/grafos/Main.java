package grafos;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Vertice v1 = new Vertice(0);
		Vertice v2 = new Vertice(1);
		Vertice v3 = new Vertice(2);
		Vertice v4 = new Vertice(3);
		Vertice v5 = new Vertice(4);
		
		
		Arista a1 = new Arista(v1, v2, 5);
		Arista a2 = new Arista(v2, v3, 3);
		Arista a3 = new Arista(v3, v5, 2);
		Arista a4 = new Arista(v3, v4, 4);
		Arista a5 = new Arista(v1, v4, 1);
		Arista a6 = new Arista(v4, v2, 10);
		
		Vertice[] nodos = {v1, v2, v3, v4, v5};
		
		Grafo g1 = new Grafo(nodos);
		
		System.out.println(g1.toString());
		
		g1.agregarArista(a1);
		g1.agregarArista(a2);
		g1.agregarArista(a3);
		g1.agregarArista(a4);
		g1.agregarArista(a5);
		g1.agregarArista(a6);
		
//		g1.eliminarArista(a4);
//		g1.eliminarArista(a5);
		
		System.out.println(g1.toString());
		
		int[] costos = g1.dijkstra(v1);
		System.out.print("Costos: ");
		for(int i = 0; i < nodos.length; i++) {
			System.out.print(costos[i] + "-");
		}

	}

}


/*
 * [-1, 1, -1, -1]
 * [3, -1, 5, -1]
 * [-1, -1, -1, -1]
 * [-1, -1, -1, -1]
 * 
 */

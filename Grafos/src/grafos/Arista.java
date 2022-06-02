package grafos;

public class Arista {

	private Vertice vOrigen;
	private Vertice vDestino;
	private int peso;
	
	public Arista(Vertice vOrigen, Vertice vDestino, int peso) {
		super();
		this.vOrigen = vOrigen;
		this.vDestino = vDestino;
		this.peso = peso;
	}

	public Vertice getvOrigen() {
		return vOrigen;
	}

	public Vertice getvDestino() {
		return vDestino;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return "Arista [vOrigen=" + vOrigen + ", vDestino=" + vDestino + ", peso=" + peso + "]";
	}
	
	
}

package facu.catriel.parcial.primer;

public class Robot {

	private char orientacion;
	private int posX;
	private int posY;
	private int limiteY;
	private int limiteX;
	
	public Robot(char orientacion, int posX, int posY, int x, int y) {
		this.orientacion = orientacion;
		this.posX = posX;
		this.posY = posY;
		this.limiteX = x;
		this.limiteY = y;
	} // Complejidad O(1)

	public void setPosX(int posX) {
		this.posX = posX;
	} // Complejidad O(1)

	public void setPosY(int posY) {
		this.posY = posY;
	} // Complejidad O(1)
	
	public int getPosX() {
		return posX;
	} // Complejidad O(1)
	
	public int getPosY() {
		return posY;
	} // Complejidad O(1) ^

	public String getPosRobot() {
		return this.posX + " " + this.posY;
	}// Complejidad O(1)

	public void avanzar(int pasos) throws Exception {
		
		try {
			if(pasos > this.comprobarLimites(orientacion, pasos)) {
				pasos = this.comprobarLimites(orientacion, pasos);
				System.out.println(pasos);
			}
			
			if(pasos < 0) throw new Exception("El rango de pasos ha de ser entre 0 y 9");
			
			switch(this.orientacion) {
			case 'N':
				this.posY += pasos;
				break;
			case 'E':
				this.posX += pasos;
				break;
			case 'S':
				this.posY -= pasos;
				break;
			case 'O':
				this.posX -= pasos;
				break;
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	} // Complejidad O(1)
	
	public void orientarse(int entero) throws Exception {
		
		switch(this.orientacion) {
			case 'N':
				if(entero == 1 || entero == 5 || entero == 9) this.orientacion = 'E';
				
				if(entero == 2 || entero == 6) this.orientacion = 'S';
				
				if(entero == 3 || entero == 7) this.orientacion = 'O';
				
				if(entero == 4 || entero == 8) this.orientacion = 'N';
				
				break;
			case 'E':
				if(entero == 1 || entero == 5 || entero == 9) this.orientacion = 'S';
				
				if(entero == 2 || entero == 6) this.orientacion = 'O';
				
				if(entero == 3 || entero == 7) this.orientacion = 'N';
				
				if(entero == 4 || entero == 8) this.orientacion = 'E';
				
				break;
			case 'S':
				if(entero == 1 || entero == 5 || entero == 9) this.orientacion = 'O';
			
				if(entero == 2 || entero == 6) this.orientacion = 'N';
				
				if(entero == 3 || entero == 7) this.orientacion = 'E';
				
				if(entero == 4 || entero == 8) this.orientacion = 'S';
					
				break;
			case 'O':
				if(entero == 1 || entero == 5 || entero == 9) this.orientacion = 'N';
				
				if(entero == 2 || entero == 6) this.orientacion = 'E';
				
				if(entero == 3 || entero == 7) this.orientacion = 'S';
				
				if(entero == 4 || entero == 8) this.orientacion = 'O';
				
				break;
			default:
				throw new Exception("El rango de orientaciones ha de ser entre 0 y 9");
			
		} // Complejidad O(1)
	}
	
	public int comprobarLimites(char orientacion, int pasos) {
		switch(orientacion) {
			case 'N':
				if(this.posY + pasos >= this.limiteY) return this.limiteX - this.posY;
				break;
			case 'E':
				if(this.posX + pasos > this.limiteX) return this.limiteY - this.posX;
				break;
			case 'S':
				if(this.posY - pasos < 0) return this.posY;
				break;
			case 'O':
				if(this.posX - pasos < 0) return this.posX;
				break;
		}
		return pasos;
	} // Complejidad O(1)
	
	
	
	
	
}

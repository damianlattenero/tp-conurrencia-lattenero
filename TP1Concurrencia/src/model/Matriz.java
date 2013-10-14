package model;

public class Matriz<E extends ReadWrite> {
	
	int cantFilas;
	int cantColumnas;
	Celda<E> [][] estructura;
	

	private void colocarColumna(int a){
		for(int y = 0; y < this.getCantColumnas();y++){
			 this.getEstructura()[a][y] = new Celda<E>(a,y);
		}
	}
	
	public Matriz(int cantFilas){
		
		for(int x = 0; x < cantFilas;x++){
			this.colocarColumna(x);
		}
	}
	
	public void reservarFila(int nroFilaAModificar){
		for(int x = 0; x < this.getCantColumnas();x++){
			this.getEstructura()[nroFilaAModificar][x].setHayEscritor(true);
		}
	}
	
	//getters y setters
	
	public Celda<E>[][] getEstructura() {
		return estructura;
	}

	public void setEstructura(Celda<E>[][] estructura) {
		this.estructura = estructura;
	}

	
	public int getCantFilas() {
		return cantFilas;
	}
	public void setCantFilas(int cantFilas) {
		this.cantFilas = cantFilas;
	}
	public int getCantColumnas() {
		return cantColumnas;
	}
	public void setCantColumnas(int cantColumnas) {
		this.cantColumnas = cantColumnas;
	}
	
	public Celda<?> dameCeldaDe(int nroFila, int nroColumna){
		return this.getEstructura()[nroFila][nroColumna];
	}

}

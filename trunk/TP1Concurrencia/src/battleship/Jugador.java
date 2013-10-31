package battleship;

import matriz.Matriz;

public class Jugador {
	
	private int puntos;
	private Matriz<Barco> matriz;
	
	public boolean scan(int i, int j){
		return new ScanCelda(i, j).apply(matriz);
	}

}

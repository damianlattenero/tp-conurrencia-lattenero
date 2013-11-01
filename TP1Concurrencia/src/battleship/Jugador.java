package battleship;

import java.util.List;

import matriz.Matriz;

public class Jugador {
	
	private int puntos;
	private Matriz<Barco> matriz;
	
	public boolean scan(int i, int j){
		return new ScanCelda(i, j).apply(matriz);
	}
	
	public List<Integer> scanLine(int nrofila){
		return new ScanLine(nrofila).apply(matriz);
	}
	
	public boolean shoot(int i, int j){
		return new Shoot(i,j).apply(matriz);
	}
	
	public int shootLine(int nrofila){
		return new ShootLine(nrofila).apply(matriz);
	}

}

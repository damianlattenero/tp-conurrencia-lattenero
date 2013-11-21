package battleship;

import matriz.Lector;
import matriz.Matriz;

public class ScanCelda implements Command<Matriz<Barco>,Boolean>, Lector<Barco, Boolean> {
	
	int i, j;
	
	public ScanCelda(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	@Override
	public Boolean apply(Matriz<Barco> m) {
		return m.leerCelda(this, i, j);
	}
	
	@Override
	public Boolean read(Barco e) {
		ServidorBattleship.dormir(1);
		return e != null;
	}

}

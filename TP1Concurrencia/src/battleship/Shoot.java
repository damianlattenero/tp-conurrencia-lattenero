package battleship;

import matriz.Celda;
import matriz.Escritor;
import matriz.Matriz;

public class Shoot extends Thread implements Escritor<Barco>, Command<Matriz<Barco>, Boolean>{
	
	int i, j;
	boolean hundido = false;


	public Shoot(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	@Override
	public Boolean apply(Matriz<Barco> m) {
		m.escribirCelda(this, i, j);
		return hundido;
	}

	@Override
	public void write(Celda<Barco> celda) {
		ServidorBattleship.dormir(2);
		if(celda.getContenido() != null){
			celda.getContenido().setEstaHundido(true);
			hundido = true;
		}
	}

}

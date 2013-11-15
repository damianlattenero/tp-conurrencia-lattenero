package battleship;

import matriz.Celda;
import matriz.Escritor;
import matriz.IteradorEscritor;
import matriz.Matriz;

public class ShootLine extends Thread implements Escritor<Barco>, Command<Matriz<Barco>, Integer>{

	int nrofila;
	int hundidos = 0;

	public ShootLine(int nrofila) {
		super();
		this.nrofila = nrofila;
	}

	@Override
	public Integer apply(Matriz<Barco> m) {
		IteradorEscritor<Barco> it = m.getIteradorEscritorDeFila(nrofila);
		while(it.hasCurrent()){
			it.current().escribir(this);
			it.next();
		}
		return hundidos;
	}

	@Override
	public void write(Celda<Barco> celda) {
		ServidorBattleship.dormir(1);
		if(celda.getContenido() != null){
			celda.getContenido().setEstaHundido(true);
			hundidos++;
		}
	}
	
}
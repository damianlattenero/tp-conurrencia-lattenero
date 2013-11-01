package battleship;

import matriz.Celda;
import matriz.Escritor;
import matriz.IteradorEscritor;
import matriz.Matriz;

public class ShootLine extends Thread implements Escritor<Barco>, Command<Matriz<Barco>, Integer>{

	int nrofila;

	public ShootLine(int nrofila) {
		super();
		this.nrofila = nrofila;
	}

	@Override
	public Integer apply(Matriz<Barco> m) {
		Integer ret = new Integer(0);
		IteradorEscritor<Barco> it = m.getIteradorEscritorDeFila(nrofila);
		while(it.hasCurrent()){
			it.current().escribir(this);
			if(new ScanCelda(nrofila, it.getIndiceActual()).apply(m)){
				ret ++;
			}
			it.next();
		
		}
		return ret;
	}

	@Override
	public void write(Celda<Barco> celda) {
		//No hace Sleep asi tarda la mitad del tiempo
		if(celda.getContenido() != null){
			celda.getContenido().setEstaHundido(true);
		}
	}
	
	
}

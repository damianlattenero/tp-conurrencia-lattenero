package battleship;

import java.util.ArrayList;
import java.util.List;

import matriz.IteradorLector;
import matriz.Lector;
import matriz.Matriz;

public class ScanLine implements Command<Matriz<Barco>, List<Integer>>, Lector<Barco, Boolean>{

	private int nroFila;

	public ScanLine(int nrofila2) {
		this.nroFila =  nrofila2;
	}

	@Override
	public List<Integer> apply(Matriz<Barco> m) {
		List<Integer> ret = new ArrayList<Integer>();		
		IteradorLector<Barco> it = m.getIteradorLectorDeFila(nroFila);
		
		while(it.hasCurrent()){
			if(it.current().leer(this)){
				ret.add(it.getIndiceActual());
			}
			it.next();
		}
		
		return ret;
	}

	@Override
	public Boolean read(Barco e) {
		ServidorBattleship.dormir(0.5);
		return e != null;
	}



	

	

	

}

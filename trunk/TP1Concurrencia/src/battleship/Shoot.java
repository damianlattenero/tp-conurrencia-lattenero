package battleship;

import matriz.Celda;
import matriz.Escritor;
import matriz.Matriz;

public class Shoot extends Thread implements Escritor<Barco>, Command<Matriz<Barco>, Boolean>{
	
	int i, j;


	public Shoot(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	@Override
	public Boolean apply(Matriz<Barco> m) {
		m.escribirCelda(this, i, j);
		return new ScanCelda(i, j).apply(m);
	}

	@Override
	public void write(Celda<Barco> celda) {
		try {
			Thread.sleep(Servidor.UT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(celda.getContenido() != null){
			celda.getContenido().setEstaHundido(true);
		}
	}

	

	

	

}

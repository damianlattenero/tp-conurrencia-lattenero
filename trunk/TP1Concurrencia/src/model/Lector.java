package model;

public class Lector extends Thread{
	
	RowIterator iterador;
	
	@Override
	public void run() {
		
		while(true){
			
			this.leerResultados();
			
		}
		
	}
	
	public void leerResultados(){
		RowIterator it = this.getIterador();
		while(it.hasCurrent()){
			it.getCeldaActual().leer(this);
			it.next();
		}
	}
	
	
	
	public Lector(RowIterator iterador) {
		super();
		this.iterador = iterador;
	}



	public RowIterator getIterador() {
		return iterador;
	}

	public void setIterador(RowIterator iterador) {
		this.iterador = iterador;
	}


}

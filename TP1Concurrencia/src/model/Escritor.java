package model;


public class Escritor extends Thread{

	
	RowIterator iterador;
	
	@Override
	public void run() {
		{

			//this.escribirResultadosSobreFila(new Object());
				
		}
	}
	
	private  void escribirResultadosSobreFila(ReadWrite contenido){
		
		RowIterator it = this.getIterador();
		it.getMatriz().reservarFila(it.getNroFila());
		
		while(it.hasCurrent()){
			it.getCeldaActual().escribir(contenido);
			it.next();
		}
	}
	
	

	public Escritor(RowIterator iterador) {
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

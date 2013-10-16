package model;

public class Lector extends Rol{
	
	RowIterator iterador;
	
	public void leerResultados(){
		RowIterator it = this.getIterador();
		while(it.hasCurrent()){
			it.getCeldaActual().empezarALeer();
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

	public boolean puedoReservarFila(int nroFilaAModificar){
		boolean puedo = true;
		for(int x = 0; x < this.getMatriz().getCantColumnas();x++){
			puedo = puedo && (!this.hayEscritores(x,nroFilaAModificar));
		}
		return puedo;
	}


}

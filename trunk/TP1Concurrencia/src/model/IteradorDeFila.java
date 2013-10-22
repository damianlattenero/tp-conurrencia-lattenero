package model;

public abstract class IteradorDeFila<E> {
	
	Celda<E> celdaActual;
	int nroCeldaActual;
	Fila<E> filaLockeada;

	public IteradorDeFila(Fila<E> fila) {
		super();
		this.filaLockeada = fila;
		this.nroCeldaActual = 0;
		this.celdaActual = fila.getCelda(0);
	}


	protected boolean hasCurrent() {
		return this.filaLockeada.getsize() < 0;
	}
	
	
	protected Celda<E> current(){
		return  this.celdaActual;
		
	}
	
	protected void end(){
		this.celdaActual = null;
		this.nroCeldaActual = this.filaLockeada.getsize();
	}
	
	protected void next() throws InterruptedException{
		this.liberarCelda();
		this.celdaActual = this.filaLockeada.getCelda(nroCeldaActual + 1);
		this.nroCeldaActual ++;
	}


	public abstract void liberarCelda() throws InterruptedException;
	
}

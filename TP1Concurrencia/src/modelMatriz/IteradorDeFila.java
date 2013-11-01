package matriz;

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

	public boolean hasCurrent() {
		return this.nroCeldaActual < this.filaLockeada.getsize();
	}
	
	public Celda<E> current(){
		return  this.celdaActual;
	}
	
	public void end(){
		while(hasCurrent()){
			next();
		}
	}
	
	public void next() {
		this.liberarCelda();
		this.celdaActual = this.filaLockeada.getCelda(nroCeldaActual + 1);
		this.nroCeldaActual ++;
	}


	public abstract void liberarCelda();

	public int getIndiceActual() {
		return nroCeldaActual;
	}
	
	
	
}

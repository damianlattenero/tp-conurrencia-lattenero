package model;

public class RowIterator {
	
	Matriz<?> matriz;
	Celda<?> celdaActual;
	int nroFila;
	
	/**
	 * i = crearIteradorEscritura(m,1)
	 * while(i.hasCurrent)
	 *   Objeto elem = i.current()
	 *   (List) elem.add(3);
	 *   i.next
	 * @param matriz
	 * @param numeroFila
	 */
	
	public RowIterator(Matriz<?> matriz, int numeroFila) {
		super();
		this.matriz = matriz;
		//Al momento de la creacion se debe bloquear sino tiene todos los permisos
		//this.celdaActual = matriz.dameCeldaDe(); chequear
		this.nroFila = numeroFila;
	}


	protected boolean hasCurrent(){
		return this.getCeldaActual().getNroColumna() < this.getMatriz().getCantColumnas();

		//leer current
		//escribir current
	}
	
	
	protected Object current(){
		return  this.getCeldaActual().getContenido();
		
	}
	protected void end(){
		
	}
	
	protected Celda<?> next(){
		return this.getMatriz().dameCeldaDe(this.getNroFila(),this.getCeldaActual().getNroColumna()+ 1);
	}
	
	
	
	
	

	public Celda<?> getCeldaActual() {
		return celdaActual;
	}

	public void setCeldaActual(Celda<?> celdaActual) {
		this.celdaActual = celdaActual;
	}

	public Matriz<?> getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz<?> matriz) {
		this.matriz = matriz;
	}

	public int getNroFila() {
		return nroFila;
	}

	public void setNroFila(int nroFila) {
		this.nroFila = nroFila;
	}
}

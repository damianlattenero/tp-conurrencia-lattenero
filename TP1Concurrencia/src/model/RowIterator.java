package model;

import java.util.List;

public class RowIterator<E extends ReadWrite> {
	
	Celda<E> celdaActual;
	List<Celda<E>> fila;
	int cantElementos;
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

	public RowIterator(Matriz<E> matriz, int nroFila) {
		super();
		this.celdaActual = matriz.dameCeldaDe(0, 0);
		this.fila = matriz.getEstructura().get(nroFila);
		this.cantElementos = fila.size();
	}


	protected boolean hasCurrent(){
		return this.getCantElementos() != 0;

		//leer current
		//escribir current
	}
	
	
	protected Celda<E> current(){
		return  this.getCeldaActual();
		
	}
	
	protected void end(){
		
	}
	
	protected void next(){
		this.setCeldaActual(this.getFila().get(cantElementos -1));
		this.setCantElementos(getCantElementos() -1 );
	}
	
	
	
	
	//get set


	public int getCantElementos() {
		return cantElementos;
	}


	public void setCantElementos(int cantElementos) {
		this.cantElementos = cantElementos;
	}


	public Celda<E> getCeldaActual() {
		return celdaActual;
	}


	public void setCeldaActual(Celda<E> celdaActual) {
		this.celdaActual = celdaActual;
	}


	public List<Celda<E>> getFila() {
		return fila;
	}


	public void setFila(List<Celda<E>> fila) {
		this.fila = fila;
	}


	public int getNroFila() {
		return nroFila;
	}


	public void setNroFila(int nroFila) {
		this.nroFila = nroFila;
	}
	
	
	

}

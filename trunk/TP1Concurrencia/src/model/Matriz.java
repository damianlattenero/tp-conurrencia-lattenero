package model;

import java.util.ArrayList;
import java.util.List;

public class Matriz<E extends ReadWrite> {
	
	int cantFilas;
	int cantColumnas;
	List<List<Celda<E>>> estructura;
	

	public Matriz(int cantFilas, int cantColumnas) {
		super();
		this.cantFilas = cantFilas;
		this.cantColumnas = cantColumnas;
		this.estructura =  new ArrayList<List<Celda<E>>>();
	}


	

	//getters y setters
	
	
	
	public Celda<E> dameCeldaDe(int nroFila, int nroColumna){
		return this.getEstructura().get(nroFila).get(nroColumna);
	}
	
	public synchronized void reservarFila(int nroFila) {
		for(Celda<E> c : this.getEstructura().get(nroFila)){
			c.setHayEscritor(true);
		}
	}
	
	protected boolean hayEscritores(int nroFilaAModificar){
		boolean ret = false;
		for(Celda<?> c : this.getEstructura().get(nroFilaAModificar)){
			ret = ret || c.isHayEscritor();
		}
		return ret;
	}
	
	protected boolean hayLectrores(int nroFilaAModificar){
		boolean ret = false;
		for(Celda<?> c : this.getEstructura().get(nroFilaAModificar)){
			ret = ret || c.getNroLectores() > 0;
		}
		return ret;
	}




	public int getCantFilas() {
		return cantFilas;
	}




	public void setCantFilas(int cantFilas) {
		this.cantFilas = cantFilas;
	}




	public int getCantColumnas() {
		return cantColumnas;
	}




	public void setCantColumnas(int cantColumnas) {
		this.cantColumnas = cantColumnas;
	}




	public List<List<Celda<E>>> getEstructura() {
		return estructura;
	}




	public void setEstructura(List<List<Celda<E>>> estructura) {
		this.estructura = estructura;
	}




	

}

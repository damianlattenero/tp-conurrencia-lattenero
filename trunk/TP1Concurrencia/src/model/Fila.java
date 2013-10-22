package model;

import java.util.ArrayList;
import java.util.List;

public class Fila<E> {
	
	List<Celda<E>> fila;
	
	public Fila(int cantColumnas){
		this.fila = new ArrayList<Celda<E>>();
		for(int j=0; j < cantColumnas; j++) {
			this.fila.add(new Celda<E>());
		}
	}
	
	public IteradorLector<E> getWriterIterator() {
		for(Celda<E> c : this.fila) {
			c.empezarALeer();
		}
		return new IteradorLector<E>(this);
	}

	public IteradorEscritor<E> getReaderIterator() {
		for(Celda<E> c : this.fila) {
			c.empezarAEscribir();
		}
		return new IteradorEscritor<E>(this);
	}
	
	public Celda<E> getCelda(int i) {
		return this.fila.get(i);
	}

	public int getsize() {
		
		return fila.size();
	}

	public void leerCelda(Lector<E> r, int columna) {
		Celda<E> c = this.getCelda(columna);
		c.empezarALeer();
		c.leer(r);
		c.terminarDeLeer();
	}
	
	public void escribirCelda(Escritor<E> w, int columna) {
		Celda<E> c = this.getCelda(columna);
		c.empezarAEscribir();
		c.escribir(w);
		c.terminarDeEscribir();
	}

}
